package com.paymentchain.transaccion.service;

import com.paymentchain.transaccion.DTOs.PutTransactionDTO;
import com.paymentchain.transaccion.DTOs.TransactionDTO;
import com.paymentchain.transaccion.entities.Transaction;
import com.paymentchain.transaccion.exception.InvalidValueException;
import com.paymentchain.transaccion.model.enums.Channel;
import com.paymentchain.transaccion.model.enums.Estatus;
import com.paymentchain.transaccion.repository.TransactionRepository;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    
    public Transaction crearTransaction(TransactionDTO transactionDTO){
        if(transactionDTO.getAmount() <= 0){
            throw new InvalidValueException("Value Amount cannot be negative");
        }
         Transaction transaction = new Transaction();
        transaction.setAccountlban(transactionDTO.getAccountlban());
        transaction.setReference(transactionDTO.getReference());
        transaction.setDate(fechaActual());
        transaction.setFee(transactionDTO.getFee());
        transaction.setAmount(montoEnd(transactionDTO.getFee(),transactionDTO.getAmount()));
        if (statusEnd(fechaActual(),transactionDTO.getFecha())){
            transaction.setStatus(Estatus.getStatusByNumber(1));
        }else{
            transaction.setStatus(Estatus.getStatusByNumber(2));
        }
         transaction.setChannel(Channel.getChannelByNumber(transactionDTO.getChannel()));
         return transactionRepository.save(transaction);
    }
    
    
    public Transaction updateTransaction(String accountlban, PutTransactionDTO putTransactionDTO){
        Transaction find = transactionRepository.findByAccountLban(accountlban);
        if(find != null){
            find.setAmount(putTransactionDTO.getAmount());
            find.setChannel(Channel.getChannelByNumber(putTransactionDTO.getChannel()));
            find.setDate(putTransactionDTO.getFecha());
            find.setFee(putTransactionDTO.getFee());
        }
        Transaction save = transactionRepository.save(find);
        return save;
        
    }
    
    
    public static boolean statusEnd(String fechaActual,String fechaTransaction){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate actual = LocalDate.parse(fechaActual,formatter);
        LocalDate transaccion = LocalDate.parse(fechaTransaction, formatter);
        return transaccion.isAfter(actual);
    }
    private String fechaActual(){
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        Date date = Date.from(zonedDateTime.toInstant());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
    
    private Double montoEnd(Double fee,Double amount){
        if(fee > 0){
           amount = amount - fee; 
           return amount;
        }else{
           return amount;
        }
    }
    public List<Transaction> obtenerTransactions(){
        return transactionRepository.findAll();
    }
    
    public Transaction obtenerTransactionById(Long id){
        return transactionRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No se encontro la transaction con ID: " + id));
    }
    
    public void eliminarTransaction(Long id){
        transactionRepository.deleteById(id);
    }
    
}
