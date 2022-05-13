// 
// Decompiled by Procyon v0.5.30
// 

package common;

import javax.servlet.RequestDispatcher;
import java.io.BufferedReader;
import java.util.Locale;
import java.io.IOException;
import javax.servlet.ServletInputStream;
import java.security.Principal;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import devpia.dextupload.DEXTUploadException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import devpia.dextupload.FileUpload;

public class FileUploadRequest extends FileUpload implements HttpServletRequest
{
    HttpServletRequest request;
    HttpServletResponse response;
    
    public FileUploadRequest(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) {
        super(httpServletRequest, httpServletResponse);
        this.request = null;
        this.response = null;
    }
    
    public void setCharacterEncoding(final String s) {
        try {
            this.request.setCharacterEncoding(s);
            super.setCharacterEncoding(s);
        }
        catch (DEXTUploadException ex) {
            ex.printStackTrace();
        }
        catch (UnsupportedEncodingException ex2) {
            ex2.printStackTrace();
        }
    }
    
    public String getAuthType() {
        return this.request.getAuthType();
    }
    
    public String getContextPath() {
        return this.request.getContextPath();
    }
    
    public Cookie[] getCookies() {
        return this.request.getCookies();
    }
    
    public long getDateHeader(final String s) {
        return this.request.getDateHeader(s);
    }
    
    public String getHeader(final String s) {
        return this.request.getHeader(s);
    }
    
    public Enumeration getHeaderNames() {
        return this.request.getHeaderNames();
    }
    
    public Enumeration getHeaders(final String s) {
        return this.request.getHeaders(s);
    }
    
    public int getIntHeader(final String s) {
        return this.request.getIntHeader(s);
    }
    
    public String getMethod() {
        return this.request.getMethod();
    }
    
    public String getPathInfo() {
        return this.request.getPathInfo();
    }
    
    public String getPathTranslated() {
        return this.request.getPathTranslated();
    }
    
    public String getQueryString() {
        return this.request.getQueryString();
    }
    
    public String getRemoteUser() {
        return this.request.getRemoteUser();
    }
    
    public String getRequestURI() {
        return this.request.getRequestURI();
    }
    
    public StringBuffer getRequestURL() {
        return this.request.getRequestURL();
    }
    
    public String getRequestedSessionId() {
        return this.request.getRequestedSessionId();
    }
    
    public String getServletPath() {
        return this.request.getServletPath();
    }
    
    public HttpSession getSession() {
        return this.request.getSession();
    }
    
    public HttpSession getSession(final boolean b) {
        return this.request.getSession(b);
    }
    
    public Principal getUserPrincipal() {
        return this.request.getUserPrincipal();
    }
    
    public boolean isRequestedSessionIdFromCookie() {
        return this.request.isRequestedSessionIdFromCookie();
    }
    
    public boolean isRequestedSessionIdFromURL() {
        return this.request.isRequestedSessionIdFromURL();
    }
    
    public boolean isRequestedSessionIdFromUrl() {
        return this.request.isRequestedSessionIdFromUrl();
    }
    
    public boolean isRequestedSessionIdValid() {
        return this.request.isRequestedSessionIdValid();
    }
    
    public boolean isUserInRole(final String s) {
        return this.request.isUserInRole(s);
    }
    
    public Object getAttribute(final String s) {
        return this.request.getAttribute(s);
    }
    
    public Enumeration getAttributeNames() {
        return this.request.getAttributeNames();
    }
    
    public int getContentLength() {
        return this.request.getContentLength();
    }
    
    public String getContentType() {
        return this.request.getContentType();
    }
    
    public ServletInputStream getInputStream() throws IOException {
        return this.request.getInputStream();
    }
    
    public String getLocalAddr() {
        return null;
    }
    
    public String getLocalName() {
        return null;
    }
    
    public int getLocalPort() {
        return 0;
    }
    
    public Locale getLocale() {
        return this.request.getLocale();
    }
    
    public Enumeration getLocales() {
        return this.request.getLocales();
    }
    
    public String getProtocol() {
        return this.request.getProtocol();
    }
    
    public BufferedReader getReader() throws IOException {
        return this.request.getReader();
    }
    
    public String getRealPath(final String s) {
        return this.request.getRealPath(s);
    }
    
    public String getRemoteAddr() {
        return this.request.getRemoteAddr();
    }
    
    public String getRemoteHost() {
        return this.request.getRemoteHost();
    }
    
    public int getRemotePort() {
        return 0;
    }
    
    public RequestDispatcher getRequestDispatcher(final String s) {
        return this.request.getRequestDispatcher(s);
    }
    
    public String getScheme() {
        return this.request.getScheme();
    }
    
    public String getServerName() {
        return this.request.getServerName();
    }
    
    public int getServerPort() {
        return this.request.getServerPort();
    }
    
    public boolean isSecure() {
        return this.request.isSecure();
    }
    
    public void removeAttribute(final String s) {
        this.request.removeAttribute(s);
    }
    
    public void setAttribute(final String s, final Object o) {
        this.request.setAttribute(s, o);
    }
}
