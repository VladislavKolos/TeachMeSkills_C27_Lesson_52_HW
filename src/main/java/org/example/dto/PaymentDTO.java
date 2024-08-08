package org.example.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private int id;

    private BigDecimal amount;

    private LocalDate paymentDate;

    @Size(min = 4, max = 6)
    private String status;
}
