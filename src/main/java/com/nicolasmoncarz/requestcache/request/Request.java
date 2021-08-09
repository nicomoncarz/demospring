package com.nicolasmoncarz.requestcache.request;

import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Request {

    public Request(String url, String response, LocalDate fetchDate) {
        this.url = url;
        this.response = response;
        this.fetchDate = fetchDate;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String url;

    private LocalDate fetchDate;

    @Column(length=24576)
    private String response;

    public Long getId() {
        return id;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public LocalDate getFetchDate() {
        return fetchDate;
    }

    public Boolean isOld() {
        return LocalDate.now().getDayOfMonth() - getFetchDate().getDayOfMonth() > 0; 
    }
}
