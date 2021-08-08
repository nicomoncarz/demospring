package com.nicolasmoncarz.requestcache.request;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestService requestService;    

    @GetMapping()
    public String getRequestData(@RequestParam(value = "url", defaultValue = "") String url) {
        return requestService.findOrCreate(url).getResponse();
    }

    @ExceptionHandler({ JDBCException.class })
    public String handleException() {
        return "DB Error";
    }
}
