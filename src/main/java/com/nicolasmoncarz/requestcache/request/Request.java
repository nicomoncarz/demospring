package com.nicolasmoncarz.requestcache.request;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Request {
    
    public Request(String url, String response) {
        this.url = url;
        this.response = response;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String url;

    @Column(length=24000)
    private String response;

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
}
