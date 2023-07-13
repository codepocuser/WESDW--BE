package com.cncbinternational.websocketpoc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionAuthDto {
    private UUID txID;
    private String authCode;
    private String username;
}
