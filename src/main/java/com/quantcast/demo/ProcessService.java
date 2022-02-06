package com.quantcast.demo;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProcessService {

    public String action(String fileName, String date) throws FileNotFoundException {

        String latestCookie = "";
        if (!date.isEmpty() && !fileName.isEmpty()) {
            // csv to pojo mapping using opencsv
            List<CookieInfo> printedLogList = new CsvToBeanBuilder(new FileReader(fileName))
                    .withType(CookieInfo.class)
                    .withSeparator(',')
                    .build()
                    .parse();

            System.out.println("------------------------------------------");
            printedLogList.forEach(System.out::println);

            Map<String, List<CookieInfo>> dateTimeStamp = new HashMap<>();

            for (CookieInfo cookieInfo : printedLogList) {

                String dateKey = toDate(cookieInfo.getTimestamp().getTime());
                if (dateTimeStamp.get(dateKey) == null)
                    dateTimeStamp.put(dateKey, new ArrayList<>());

                dateTimeStamp.get(dateKey).add(cookieInfo);

            }

            List<CookieInfo> values = dateTimeStamp.get(date);//  passed value from command argument
            if (!Objects.requireNonNull(values).isEmpty()) {
                CookieInfo cookieInfo = values.get(values.size() - 1);
                latestCookie = cookieInfo.getCookie();
                System.out.println("Latest cookie for given date is " + latestCookie);
            }
        }
        return latestCookie;

    }

    private String toDate(long timestamp) {
        Date date = new Date(timestamp);
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

}
