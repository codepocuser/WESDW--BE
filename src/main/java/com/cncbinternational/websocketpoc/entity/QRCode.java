package com.cncbinternational.websocketpoc.entity;

import com.cncbinternational.websocketpoc.enums.Workflow;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "QRCode") //timeToLive = 60
public class QRCode implements Serializable {

    @Id
    @NotNull
    @NotEmpty
    private UUID id;

    @Column
    private String username;

    @Column
    private String authCode;

    @Transient
    @Enumerated(EnumType.STRING)
    private Workflow type;

    @Column
    private Date expires;


}