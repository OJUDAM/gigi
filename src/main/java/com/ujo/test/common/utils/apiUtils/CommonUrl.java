package com.ujo.test.common.utils.apiUtils;

import com.ujo.test.common.utils.StringUtils;

public class CommonUrl {
    private String url;

    private CommonUrl(UrlBuilder builder) {
        this.url = builder.url;
    }

    public String getUrl() {
        return this.url;
    }

    public static class UrlBuilder {
        private String url;

        public UrlBuilder(String url) {
            this.url = url;
        }

        public UrlBuilder setPathParam(String pathParam) {
            //https://~~~.../ 마지막은 슬래쉬로 끝
            this.url += pathParam + "/";

            return this;
        }

        public UrlBuilder startQueryStringParam(String key, String value) {
            this.url = StringUtils.deleteLastChar(this.url);
            this.url += "?" + key + "=" + value;

            return this;
        }

        public UrlBuilder setQueryStringParam(String key, String value) {
            this.url += "&"+key + "=" + value;

            return this;
        }

        public CommonUrl build() {
            return new CommonUrl(this);
        }
    }
}
