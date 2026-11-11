package com.shelton.e_commerce.entity;
import com.fasterxml.jackson.annotation.JsonSerializeAs;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "idempotency_key")
@Entity
@Builder
public class IdempotencyKey {

    @Id
    private UUID key;  // o próprio Idempotency-Key enviado pelo cliente no header

    @Column(nullable = false)
    private String operation;  //  UPDATE_USER", "DELETE_USER

    @Column(nullable = false)
    private UUID resourceId;

    @Lob
    private String responseBody;  // JSON da resposta cacheada fica null nos deletes

    @Column(nullable = false)
    private Instant createdAt;

}
