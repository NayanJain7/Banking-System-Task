package com.CreditTransfer.Service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.CreditTransfer.Dao.TransHistoryRepository;
import com.CreditTransfer.Dao.TransferRepository;
import com.CreditTransfer.Model.MoneySender;
import com.CreditTransfer.Model.TransactionHistory;

@Service
public class TransactionService {
	
	@Autowired
	TransHistoryRepository trRepo;
	@Autowired
	TransferRepository transferRepo;

	public void handleTransaction(int S_id,int R_id,long tansfer_amount) {
		
		  MoneySender sender = transferRepo.findById(S_id).get();
		  MoneySender receiver = transferRepo.findById(R_id).get();
		  
		  sender.setAmount(sender.getAmount()-tansfer_amount);
		  receiver.setAmount(receiver.getAmount()+tansfer_amount);
		  
		  transferRepo.save(sender);
		  transferRepo.save(receiver);
		  //save transaction history details
		  trRepo.save(new TransactionHistory(sender.getName(),receiver.getName(),tansfer_amount));
		  
	}


	public Page<TransactionHistory> historyDetails(int page_no) {
		
		Sort sort = Sort.by("id").descending();
		
		Pageable pageable = PageRequest.of(page_no,6,sort);

		Page<TransactionHistory> history = trRepo.findAll(pageable);
		
		return history;

	}


}
