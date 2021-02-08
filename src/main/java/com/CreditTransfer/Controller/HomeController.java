package com.CreditTransfer.Controller;

import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.CreditTransfer.Model.MoneySender;
import com.CreditTransfer.Model.TransactionHistory;
import com.CreditTransfer.Service.SenderService;
import com.CreditTransfer.Service.TransactionService;


@Controller
public class HomeController {

	@Autowired
	SenderService senderService;

	@Autowired
	TransactionService transactionService;

	@GetMapping("/")
	public String welcomePage() {
		return "home";
	}
	@GetMapping("/add")
	public String addUserPage() {
		return "add_user";
	}

	@PostMapping("/addUser")
	public String addUser(@ModelAttribute("customer") MoneySender customer,Model model) {
		
		boolean addCustomerDetails = senderService.addCustomerDetails(customer);

		if(addCustomerDetails) {
			model.addAttribute("message","user created successfully");
			 return "redirect:/users";
		}
		else {
			model.addAttribute("message","something went wrong");
			return "add_user";
			
		}
	}
	@GetMapping("/users")
	public String showPage(Model model) {
		
		List<MoneySender> list = senderService.showAllUsers();
		
		model.addAttribute("users", list);
		
		return "all_user";
	}

	@PostMapping("/transfer_page/{id}")
	public String showPage(@PathVariable("id") int id, Model model) {

		Map<String, List<?>> map = senderService.showReceivers(id);
		model.addAttribute("receiver", map.get("receiver"));
		model.addAttribute("sender", map.get("sender").get(0));

		return "transfer_page";
	}

	@PostMapping("/transfer/money/{id}")
	public String transferMoney(@PathVariable("id") int S_id, @RequestParam("dropdown") int R_id,
			@RequestParam("debit") long tansfer_amount) {

		transactionService.handleTransaction(S_id, R_id, tansfer_amount);


		return "redirect:/users";
	}

	@GetMapping("/history/{page_no}")
	public String showHistory(Model model, @PathVariable("page_no") int page_no) {

		Page<TransactionHistory> history = transactionService.historyDetails(page_no);

		model.addAttribute("history", history);
		model.addAttribute("current_page", page_no);
		model.addAttribute("total_pages", history.getTotalPages());

		return "transaction_history";

	}

}
