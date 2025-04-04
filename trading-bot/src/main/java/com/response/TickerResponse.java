package com.response;

import lombok.Data;

@Data
public class TickerResponse {
  private String symbol;
  private double lastPrice;
}