package com.paymentchain.transaccion.controller;

import com.paymentchain.transaccion.DTOs.PutTransactionDTO;
import com.paymentchain.transaccion.DTOs.TransactionDTO;
import com.paymentchain.transaccion.entities.Transaction;
import com.paymentchain.transaccion.exception.InvalidValueException;
import com.paymentchain.transaccion.repository.TransactionRepository;
import com.paymentchain.transaccion.service.TransactionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    TransactionRepository transactionRepository;
    
    @PostMapping
    public ResponseEntity<Transaction> crearTransaction (@RequestBody TransactionDTO transactionDTO){
        try{
            Transaction transaction = transactionService.crearTransaction(transactionDTO);
            return ResponseEntity.ok(transaction);
        }catch(InvalidValueException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @PutMapping("/{accountlban}")
     public ResponseEntity<Transaction> put(@PathVariable(name = "accountlban") String accountlban, @RequestBody PutTransactionDTO  putTransactionDTO) {
        Transaction transaction = transactionService.updateTransaction(accountlban, putTransactionDTO);
        return ResponseEntity.ok(transaction);
    }
    @GetMapping
    public ResponseEntity<List<Transaction>> obtenerTodasLasTransaciones(){
        List<Transaction> transaciones = transactionService.obtenerTransactions();
        return ResponseEntity.ok(transaciones);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> obtenerTransacionesById(@PathVariable(name = "id")  Long id){
        Transaction transaction = transactionService.obtenerTransactionById(id);
        return ResponseEntity.ok(transaction);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTransaction(@PathVariable(name = "id")  Long id){
        transactionService.eliminarTransaction(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/customer/transactions")
    public List<Transaction> get(@RequestParam(name = "ibanAccount") String ibanAccount) {
      return transactionRepository.findByIbanAccount(ibanAccount);      
    }
    
}
