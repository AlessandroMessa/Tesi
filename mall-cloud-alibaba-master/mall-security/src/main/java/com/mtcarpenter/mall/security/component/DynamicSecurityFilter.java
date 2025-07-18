package com.mtcarpenter.mall.security.component;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class DynamicSecurityFilter extends AbstractSecurityInterceptor implements Filter {

    private final List<String> ignoreUrls;
    private final DynamicSecurityMetadataSource securityMetadataSource;

    public DynamicSecurityFilter(List<String> ignoreUrls,
                                  DynamicSecurityMetadataSource securityMetadataSource,
                                  DynamicAccessDecisionManager accessDecisionManager) {
        this.ignoreUrls = ignoreUrls;
        this.securityMetadataSource = securityMetadataSource;
        super.setAccessDecisionManager(accessDecisionManager);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestUrl = httpRequest.getRequestURI();

        PathMatcher pathMatcher = new AntPathMatcher();
        for (String path : ignoreUrls) {
            if (pathMatcher.match(path, requestUrl)) {
                chain.doFilter(request, response);
                return;
            }
        }

        FilterInvocation fi = new FilterInvocation(request, response, chain);
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return securityMetadataSource;
    }
}
