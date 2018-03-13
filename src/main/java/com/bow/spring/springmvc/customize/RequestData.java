package com.bow.spring.springmvc.customize;

import java.util.Map;

/**
 * @author vv
 * @since 2017/2/3.
 */
public class RequestData {

    private Map<String, String> data;

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public String toString() {
        return "{\"data\":" + data + "}";
    }
}
