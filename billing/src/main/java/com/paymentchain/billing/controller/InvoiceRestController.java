package com.paymentchain.billing.controller;

import com.paymentchain.billing.entities.Invoice;
import com.paymentchain.billing.repository.InvoiceRepository;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billing")
public class InvoiceRestController {
    @Autowired
    InvoiceRepository billingRepository;
    
    @GetMapping
    public List<Invoice> list(){
        return billingRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable long id){
        Optional<Invoice> invoice = billingRepository.findById(id);
        if(invoice.isPresent()){
            return new ResponseEntity<>(invoice.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Invoice input){
        return null;
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Invoice input){
        Invoice save = billingRepository.save(input);
        return ResponseEntity.ok(save);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Optional<Invoice> dto = billingRepository.findById(Long.valueOf(id));
        if(!dto.isPresent()){
            return ResponseEntity.notFound().build();
        }
        billingRepository.delete(dto.get());
        return ResponseEntity.ok().build();
                
    }
    
    
}
