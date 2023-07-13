package com.cncbinternational.websocketpoc.controller;

import com.cncbinternational.websocketpoc.dto.QRLoginDto;
import com.cncbinternational.websocketpoc.dto.TokenDto;
import com.cncbinternational.websocketpoc.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/external/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/token")
    public ResponseEntity<TokenDto> acknowledge(@RequestBody QRLoginDto qrLoginDto) throws Exception {
        return new ResponseEntity<>(authService.token(qrLoginDto), HttpStatus.ACCEPTED);
    }
}
