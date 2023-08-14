package com.ujo.gigi.common.utils.apiUtils;

import com.ujo.gigi.common.utils.StringUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CommonUrl {
    private String url;

    private CommonUrl(UrlBuilder builder) {
        this.url = builder.url;
    }
    
    /**
     * 완성된 Url 반환
     * */
    public String getUrl() {
        return this.url;
    }

    public static class UrlBuilder {
        private String url;

        public UrlBuilder(String url) {
            this.url = url;
        }
        
        /**
         * Path 파라 미터 세팅
         * */
        public UrlBuilder setPathParam(String pathParam) {
            //https://~~~.../ 마지막은 슬래쉬로 끝
            this.url += URLEncoder.encode(pathParam, StandardCharsets.UTF_8) + "/";

            return this;
        }
        
        /**
         * 쿼리 파라미터 세팅 시 처음에는 '?' 구분자 필요하기 떄문에 해당 메소드 부터 호출해야함
         * */
        public UrlBuilder startQueryStringParam(String key, String value) {
            this.url = StringUtils.deleteLastChar(this.url);
            this.url += "?" + key + "=" + value;

            return this;
        }

        /**
         * 두 번째 쿼리 파라미터 부터 세팅 
         * */
        public UrlBuilder setQueryStringParam(String key, String value) {
            if (StringUtils.isEmpty(value)) {
                return this;
            }

            this.url += "&"+key + "=" + value;
            return this;
        }
        
        /**
         *  CommonUrl 객체 반환 
         * */
        public CommonUrl build() {
            return new CommonUrl(this);
        }
    }
}
