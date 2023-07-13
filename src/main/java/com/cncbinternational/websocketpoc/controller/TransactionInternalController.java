package com.cncbinternational.websocketpoc.controller;

import com.cncbinternational.websocketpoc.dto.GenerateDto;
import com.cncbinternational.websocketpoc.entity.QRCode;
import com.cncbinternational.websocketpoc.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/internal/qrcode")
public class TransactionInternalController {

    private final TransactionService transactionService;

    public TransactionInternalController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/callback")
    public ResponseEntity<Boolean> authorize(@RequestBody QRCode profile) {
        return new ResponseEntity<>(transactionService.authorize(profile), HttpStatus.ACCEPTED);
    }


}
