package com.tony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

  private static final String SUCCESS = "SUCCESS";
  private static final String FAIL = "FAIL";
  @Autowired
  private JdbcTemplate stockJdbcTemplate;

  @PostMapping(value = "/stock", produces = "application/json")
  public String account(String commodityCode, int count) {
    int result = stockJdbcTemplate.update("update storage_tbl set count = count - ? where commodity_code = ?", count,
        commodityCode);
    if (result == 1) {
      return SUCCESS;
    }
    return FAIL;
  }

}
