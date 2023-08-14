package com.ujo.gigi.common.filter;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.util.ContentCachingRequestWrapper;

/**
 * 클라이언트 요청에 대한 정보
 * */
@Component
@RequestScope
@Getter
public class RequestContext {

    private ContentCachingRequestWrapper request;

    public void setRequest(final ContentCachingRequestWrapper request) {
        this.request = request;
    }
}
