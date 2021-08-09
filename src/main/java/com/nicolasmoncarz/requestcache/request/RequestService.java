package com.nicolasmoncarz.requestcache.request;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

import com.nicolasmoncarz.requestcache.HttpUrlConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public Request findOrCreate(String url) {
        List<Request> requestsWithUrl = this.requestRepository.findAll().stream().filter(request -> request.getUrl().equals(url)).collect(Collectors.toList());
        return requestsWithUrl.size() > 0 ? requestsWithUrl.get(0) : new Request(url, HttpUrlConnection.sendGET(url), LocalDate.now());
    }

    private List<Long> listOldRequestsIds() {
        return this.requestRepository.findAll().stream()
            .filter(request -> request.isOld())
            .map(request -> request.getId())
            .collect(Collectors.toList());
    }

    public void deleteOldResponses() {
        this.requestRepository.deleteAllById(listOldRequestsIds());
    }
}
