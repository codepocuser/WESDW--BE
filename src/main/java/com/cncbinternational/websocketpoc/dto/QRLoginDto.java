package com.cncbinternational.websocketpoc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QRLoginDto {
    private UUID txId;
    private String authCode;
}
