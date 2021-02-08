package com.CreditTransfer.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TransactionHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String senderName;
	private String receiverName;
	private long transferAmount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date transferTime = new Date(System.currentTimeMillis());

	public TransactionHistory(String senderName, String receiverName, long transferAmount) {
		super();
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.transferAmount = transferAmount;
	}
	
	

	
}
