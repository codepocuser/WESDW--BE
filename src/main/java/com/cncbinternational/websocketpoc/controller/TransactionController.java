package com.cncbinternational.websocketpoc.controller;

import com.cncbinternational.websocketpoc.dto.GenerateDto;
import com.cncbinternational.websocketpoc.entity.QRCode;
import com.cncbinternational.websocketpoc.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external/qrcode")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/generate")
    public ResponseEntity<GenerateDto> generate() {
        return new ResponseEntity<>(transactionService.generate(), HttpStatus.CREATED);
    }

    @MessageMapping("/login")
    public void messageHandler(@Payload QRCode qrcode) {
        transactionService.handleSocket(qrcode);

    }

}
