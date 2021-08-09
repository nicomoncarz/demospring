package com.nicolasmoncarz.requestcache.request;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class RequestTests {
    
    @Test
    public void requestFromTodayIsNotOld() {
        assertFalse((new Request("url", "response", LocalDate.now())).isOld());
    }

    @Test
    public void requestFromYesterdayIsOld() {
        assertTrue((new Request("url", "response", LocalDate.now().minusDays(1))).isOld());
    }
}
