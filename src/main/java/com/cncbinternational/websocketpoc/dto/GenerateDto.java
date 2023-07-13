package com.cncbinternational.websocketpoc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateDto {
    private UUID txId;
    private Date expires;
}
