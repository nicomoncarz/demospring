package com.nicolasmoncarz.requestcache.cron;

import com.nicolasmoncarz.requestcache.request.RequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CleanJob {

    @Autowired
    private RequestService requestService;

    @Scheduled(cron = "0 0 0 * * ?")
	public void removeOldResponses() {
        requestService.deleteOldResponses();
	}
}
