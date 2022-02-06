package com.quantcast.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ProcessServiceTest {

    ProcessService processService = Mockito.mock(ProcessService.class);

    @Test
    void action() throws FileNotFoundException {
        when(processService.action(anyString(), anyString())).thenReturn("AtY0laUfhglK3lC7");
        String fileName = "F:\\vbhalke\\dev\\workspace\\demo\\src\\main\\resources\\cookie_log.csv";
        String date = "2018-12-09";
        String latestCookie = processService.action(fileName, date);
        assertEquals("AtY0laUfhglK3lC7", latestCookie);
    }
}