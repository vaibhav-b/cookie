package com.quantcast.demo;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter
@Setter
public class CookieInfo {

    @CsvBindByName(column = "cookie")
    private String cookie;

    @CsvBindByName(column = "timestamp", required = true)
    private Timestamp timestamp;

}
