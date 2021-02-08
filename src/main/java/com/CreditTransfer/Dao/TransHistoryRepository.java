package com.CreditTransfer.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CreditTransfer.Model.TransactionHistory;

@Repository
public interface TransHistoryRepository extends JpaRepository<TransactionHistory, Integer> {

}
