package com.cncbinternational.websocketpoc.controller;

import com.cncbinternational.websocketpoc.dto.AcknowledgeDto;
import com.cncbinternational.websocketpoc.service.MobileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/external/mobile")
public class MobileController {

    private final MobileService mobileService;

    public MobileController(MobileService mobileService) {
        this.mobileService = mobileService;
    }

    @PostMapping("/acknowledge")
    public ResponseEntity<Boolean> acknowledge(@RequestBody AcknowledgeDto acknowledgeDto) {
        return new ResponseEntity<>(mobileService.acknowledge(acknowledgeDto), HttpStatus.ACCEPTED);
    }
}
