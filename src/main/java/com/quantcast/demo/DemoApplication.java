package com.quantcast.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    static String fileName = "";
    static String date = "";

    @Autowired
    ProcessService processService;


    public static void main(String[] args) {

        if (args.length < 4) {
            System.out.println("Please provide require parameters");
            return;
        }

        if (args.length == 4 && args[0].equals("-f"))
            fileName = args[1];

        if (args.length == 4 && args[2].equals("-d"))
            date = args[3];

        SpringApplication.run(DemoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() throws IOException {
        System.out.println("I have just started up");

        //java -jar .\target\cookie-0.0.1-SNAPSHOT.jar
        // -f F:\vbhalke\dev\workspace\quantcast\cookie\src\main\resources\cookie_log.csv -d 2018-12-09

        //String fileName = "F:\\vbhalke\\dev\\workspace\\demo\\src\\main\\resources\\cookie_log.csv";
        //String date = "2018-12-09";
        if (!fileName.isEmpty() && !date.isEmpty()) {
            processService.action(fileName, date);
        }

    }


    @Override
    public void run(String[] args) throws Exception {
        processService.action(fileName, date);
    }
}
