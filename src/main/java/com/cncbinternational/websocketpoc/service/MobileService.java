package com.cncbinternational.websocketpoc.service;

import com.cncbinternational.websocketpoc.dto.AcknowledgeDto;
import com.cncbinternational.websocketpoc.entity.QRCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MobileService {
    public Boolean acknowledge(AcknowledgeDto acknowledgeDto){
        WebClient webClient = WebClient.create();
        QRCode qrcode = new QRCode();
        qrcode.setId(acknowledgeDto.getTxId());
        qrcode.setUsername(acknowledgeDto.getUser());
        return webClient.post()
                .uri("http://localhost:8080/internal/qrcode/callback")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(qrcode), QRCode.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
