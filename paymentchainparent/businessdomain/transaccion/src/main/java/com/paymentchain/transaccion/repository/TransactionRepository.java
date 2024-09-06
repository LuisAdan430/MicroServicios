package com.paymentchain.transaccion.repository;

import com.paymentchain.transaccion.entities.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  
    @Query("SELECT c FROM Transaction c WHERE c.accountlban = ?1")
    public Transaction findByAccountLban(String accountlban);
    
    @Query("SELECT t FROM Transaction t WHERE t.accountlban = ?1")
      public List<Transaction> findByIbanAccount(String ibanAccount);
}
