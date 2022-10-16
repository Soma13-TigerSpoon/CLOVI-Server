package Soma.CLOVI.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestUtils {
    private static RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

    public static String getClientIpAddress() {
        if(requestAttributes == null) return "0.0.0.0";

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String[] IP_HEADER_CANDIDATES = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
                "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
                "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR"};

        for(String ipHeader : IP_HEADER_CANDIDATES) {
            String ipList = request.getHeader(ipHeader);

            if(ipList != null && ipList.length() != 0 && !ipList.equalsIgnoreCase("unknown")) {
                String ip = ipList.split(",")[0];
                return ip;
            }
        }

        return request.getRemoteAddr();
    }

    public String getRequestUrl() {
        if(requestAttributes == null) return "";

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request.getRequestURL().toString();
    }

    public String getRequestUri() {
        if(requestAttributes == null) return "";

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request.getRequestURI();
    }

    public String getRefererPage() {
        if(requestAttributes == null) return "";

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String referer = (request.getHeader("Referer") != null) ?
                request.getHeader("Referer") : request.getHeader("referer");
        return referer;
    }

    public String getPageQueryString() {
        if(requestAttributes == null) return "";

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request.getQueryString();
    }

    public String getUserAgent() {
        if(requestAttributes == null) return "";

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String userAgent = (request.getHeader("User-Agent") != null) ?
                request.getHeader("User-Agent") : request.getHeader("user-agent");
        return userAgent;
    }

    public String getRequestMethod() {
        if(requestAttributes == null) return "";

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request.getMethod();
    }
}
