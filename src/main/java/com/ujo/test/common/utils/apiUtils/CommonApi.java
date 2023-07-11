package com.ujo.test.common.utils.apiUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * http 통신 유틸리티
 * */
public class CommonApi {
    private HttpURLConnection httpURLConnection;
    private URL url;
    private RuntimeException exception;

    private CommonApi(ApiBuilder builder) {
        this.exception = builder.exception;
        this.url = builder.url;
        this.httpURLConnection = (HttpURLConnection) builder.httpURLConnection;
    }

    /**
     * API 호출
     * */
    public String callApi() {
        BufferedReader bufferedReader = null;
        try {
            //호출 응답 코드 200번대 가 아니면 로그 기록 후 예외 던짐
            if (httpURLConnection.getResponseCode() < 200 || httpURLConnection.getResponseCode() > 300) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream(), StandardCharsets.UTF_8));
                //TODO: 로그남기기로 변경해야함
                System.out.println(parsingBuffer(bufferedReader));

                //예외 던짐
                throw this.exception;
            }

            //호출 정상 응답시
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8));
            return parsingBuffer(bufferedReader);
        } catch (IOException e) {
            throw this.exception;
        } finally {
            this.httpURLConnection.disconnect();
        }
    }

    private String parsingBuffer(BufferedReader bufferedReader) throws IOException {
        StringBuilder result = new StringBuilder();
        try {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }

            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            bufferedReader.close();
        }

    }

    public static class ApiBuilder {

        private HttpURLConnection httpURLConnection;
        private URL url;
        private RuntimeException exception;

        public ApiBuilder(String url, RuntimeException exception) {
            try {
                this.exception = exception;
                this.url = new URL(url);
                this.httpURLConnection = (HttpURLConnection) this.url.openConnection();
            } catch (IOException e) {
                throw exception;
            }
        }

        /**
         * request 속성(헤더) 세팅
         */
        public ApiBuilder setRequestMethod(String method) {
            try {
                this.httpURLConnection.setRequestMethod(method);

                return this;
            } catch (ProtocolException e) {
                throw this.exception;
            }
        }

        /**
         * request 속성(헤더) 세팅
         */
        public ApiBuilder setRequestProperty(String key, String value) {
            this.httpURLConnection.setRequestProperty(key, value);

            return this;
        }

        public CommonApi build() {
            return new CommonApi(this);
        }
    }
}
