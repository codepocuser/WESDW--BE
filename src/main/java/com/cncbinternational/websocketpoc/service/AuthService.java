package com.cncbinternational.websocketpoc.service;

import com.cncbinternational.websocketpoc.dto.QRLoginDto;
import com.cncbinternational.websocketpoc.dto.TokenDto;
import com.cncbinternational.websocketpoc.entity.QRCode;
import com.cncbinternational.websocketpoc.util.JwtHelper;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {
    private final JwtHelper jwtHelper;
    private final TransactionService transactionService;
    public AuthService(JwtHelper jwtHelper, TransactionService transactionService) {
        this.jwtHelper = jwtHelper;
        this.transactionService = transactionService;
    }


    public TokenDto token(QRLoginDto qrLoginDto) throws Exception {
        Optional<QRCode> relation = transactionService.findRelation(qrLoginDto.getTxId());
        if(relation.isPresent()){
            return new TokenDto(jwtHelper.createJwtToken(relation.get().getUsername()));
        }else{
            throw new Exception("QRCode is expired.");
        }

    }
}
