package com.response;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderFillResponse {
    private double price;
    private BigDecimal qty;
}