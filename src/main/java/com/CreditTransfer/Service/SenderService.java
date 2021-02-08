package com.CreditTransfer.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.CreditTransfer.Dao.TransferRepository;
import com.CreditTransfer.Model.MoneySender;

@Service
public class SenderService {
	
	@Autowired
	TransferRepository transferRepo;
	
	
	public boolean addCustomerDetails(MoneySender customer) {

		MoneySender save = transferRepo.save(customer);

		var result = (save!= null) ? true : false;

		return result;

	}
	
	public List<MoneySender> showAllUsers() {
		
		Sort sort = Sort.by("id").ascending();
		List<MoneySender> list = transferRepo.findAll(sort);
		
		return list;
	}
	
	public Map<String, List<?>> showReceivers(int id) {
		
		List<MoneySender> receiver = showAllUsers();
		
		MoneySender sender = transferRepo.findById(id).get();
		
		receiver.remove(sender);
		
		Map<String, List<?>> map = new HashMap<>();
		
		map.put("sender", List.of(sender));
		map.put("receiver", receiver);
		
		return map;
	}

}
