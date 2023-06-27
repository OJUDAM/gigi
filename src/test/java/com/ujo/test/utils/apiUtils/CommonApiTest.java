package com.ujo.test.utils.apiUtils;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CommonApiTest {

    @Test
    public void callApi(){
        String url = "https://apis.openapi.sk.com/puzzle/subway/congestion/stat/train/stations";
        String apiKey = "Z1iuLatVLQ9FsfIlE7R0H1ti9esZmLkUa996f691";
        String station = "/D12";

        url += station;

        CommonApi commonApi = new CommonApi(url, new RuntimeException());

        commonApi.setRequestMethod("GET");

        commonApi.setRequestProperty("Accept", "application/json");
        commonApi.setRequestProperty("appKey", apiKey);

        System.out.println(commonApi.callApi());
        System.out.println("test");
    }
}