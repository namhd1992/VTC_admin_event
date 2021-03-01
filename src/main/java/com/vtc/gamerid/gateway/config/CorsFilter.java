package com.vtc.gamerid.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by phucnguyen on 09/03/2017.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
    private List<GrantedAuthority> grantedAuths;
    private Logger logger = LoggerFactory.getLogger(CorsFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        //Set header
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

//        response.addHeader("Access-Control-Allow-Origin", "*");
        List<String> arrOrigin = Arrays.asList("http://localhost:3000", "http://admin.splay.vn", "https://admin.splay.vn");

        String origin = request.getHeader("Origin");

        if(arrOrigin.indexOf(origin) > 0){
            response.addHeader("Access-Control-Allow-Origin", origin);
        }else{
            response.addHeader("Access-Control-Allow-Origin", "*");
        }

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE, PUT");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "content-type,access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
        /*if(((HttpServletRequest) request).getHeader("Content-Type")
                .equals("application/x-www-form-urlencoded")){
            chain.doFilter(request, response);
            return;
        }*/

        /*JSONObject json = new JSONObject();
        if (hasXSSContent(request)) {
            try {
                PrintWriter writer = response.getWriter();
                json.put("statusCode", STATUS_CODE_FALSE);
                json.put("onlyMessage", BAD_REQ);
                json.write(writer);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            chain.doFilter(request, response);
        }*/
    }

    @Override
    public void destroy() {
    }
    /**
     * Verify if a request contains XSS content, if yes return true, otherwise return false
     */
    private boolean hasXSSContent(ServletRequest request) {
        BufferedReader reader;
        StringBuffer buffer = new StringBuffer();
        String line = null;

        try{
            String queryString = ((HttpServletRequest) request).getQueryString();
            if(queryString != null && queryString.trim().length() > 1){
                String[] parameters = queryString.split("&");

                for (String parameter : parameters) {
                    String[] keyValuePair = parameter.split("=");
                    if(!keyValuePair[1].equals(stripXSS(keyValuePair[1]))) return true;
                }
            }
        }catch (Exception e){
        }

        try {
            reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            String originJson = buffer.toString();
            if (originJson.equals(stripXSS(new String(originJson)))) {
                return false;
            }
        }
        catch (Exception e) {
        }

        return true;
    }

    /**
     * Given a JSON String, remove all XSS format.
     * Credit to http://www.javacodegeeks.com/2012/07/anti-cross-site-scripting-xss-filter.html
     */
    private String stripXSS(String value) {
        if (value != null) {
//            value = ESAPI.encoder().canonicalize(value);

            // Avoid null characters
            value = value.replaceAll("", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("(<|%3C)script(>|%3E)(.*?)(<|%3C)/script(>|%3E)", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid anything in a src='...' type of expression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("(<|%3C)/script(>|%3E)", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("(<|%3C)script(.*?)(>|%3E)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid expression(...) expressions
            scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid onload= expressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

        }

        return value;
    }
}
