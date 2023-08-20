package com.example.BankMega.Ascore.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(indexes = { @Index(name = "reqId", columnList = "reqId"), })
public class LogAscore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reqId;

    private String channel;

    private String param;

    private String value;

    private String req_type;

    private String req_detail;

    private String req_url;

    private Integer result;

    private Boolean is_success;

    private LocalDateTime created_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getIs_success() {
        return is_success;
    }

    public void setIs_success(Boolean is_success) {
        this.is_success = is_success;
    }

    public String getReq_type() {
        return req_type;
    }

    public void setReq_type(String req_type) {
        this.req_type = req_type;
    }

    public String getReq_detail() {
        return req_detail;
    }

    public void setReq_detail(String req_detail) {
        this.req_detail = req_detail;
    }

    public String getReq_url() {
        return req_url;
    }

    public void setReq_url(String req_url) {
        this.req_url = req_url;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
