package com.CreditTransfer.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CreditTransfer.Model.MoneySender;

@Repository
public interface TransferRepository extends JpaRepository<MoneySender, Integer> {

}
