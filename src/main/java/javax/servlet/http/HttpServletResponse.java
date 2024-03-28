package javax.servlet.http;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

public class HttpServletResponse implements jakarta.servlet.http.HttpServletResponse {
    private final jakarta.servlet.http.HttpServletResponse originalResponse;

    public HttpServletResponse(jakarta.servlet.http.HttpServletResponse originalResponse) {
        this.originalResponse = originalResponse;
    }

    @Override
    public void addCookie(Cookie cookie) {
        originalResponse.addCookie(cookie);
    }

    @Override
    public boolean containsHeader(String s) {
        return originalResponse.containsHeader(s);
    }

    @Override
    public String encodeURL(String s) {
        return originalResponse.encodeURL(s);
    }

    @Override
    public String encodeRedirectURL(String s) {
        return originalResponse.encodeRedirectURL(s);
    }

    @Override
    public void sendError(int i, String s) throws IOException {
        originalResponse.sendError(i, s);
    }

    @Override
    public void sendError(int i) throws IOException {
        originalResponse.sendError(i);
    }

    @Override
    public void sendRedirect(String s) throws IOException {
        originalResponse.sendRedirect(s);
    }

    @Override
    public void setDateHeader(String s, long l) {
        originalResponse.setDateHeader(s, l);
    }

    @Override
    public void addDateHeader(String s, long l) {
        originalResponse.addDateHeader(s, l);
    }

    @Override
    public void setHeader(String s, String s1) {
        originalResponse.setHeader(s, s1);
    }

    @Override
    public void addHeader(String s, String s1) {
        originalResponse.addHeader(s, s1);
    }

    @Override
    public void setIntHeader(String s, int i) {
        originalResponse.setIntHeader(s, i);
    }

    @Override
    public void addIntHeader(String s, int i) {
        originalResponse.addIntHeader(s, i);
    }

    @Override
    public void setStatus(int i) {
        originalResponse.setStatus(i);
    }

    @Override
    public int getStatus() {
        return originalResponse.getStatus();
    }

    @Override
    public String getHeader(String s) {
        return originalResponse.getHeader(s);
    }

    @Override
    public Collection<String> getHeaders(String s) {
        return originalResponse.getHeaders(s);
    }

    @Override
    public Collection<String> getHeaderNames() {
        return originalResponse.getHeaderNames();
    }

    @Override
    public String getCharacterEncoding() {
        return originalResponse.getCharacterEncoding();
    }

    @Override
    public String getContentType() {
        return originalResponse.getContentType();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return originalResponse.getOutputStream();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return originalResponse.getWriter();
    }

    @Override
    public void setCharacterEncoding(String s) {
        originalResponse.setCharacterEncoding(s);
    }

    @Override
    public void setContentLength(int i) {
        originalResponse.setContentLength(i);
    }

    @Override
    public void setContentLengthLong(long l) {
        originalResponse.setContentLengthLong(l);
    }

    @Override
    public void setContentType(String s) {
        originalResponse.setContentType(s);
    }

    @Override
    public void setBufferSize(int i) {
        originalResponse.setBufferSize(i);
    }

    @Override
    public int getBufferSize() {
        return originalResponse.getBufferSize();
    }

    @Override
    public void flushBuffer() throws IOException {
        originalResponse.flushBuffer();
    }

    @Override
    public void resetBuffer() {
        originalResponse.resetBuffer();
    }

    @Override
    public boolean isCommitted() {
        return originalResponse.isCommitted();
    }

    @Override
    public void reset() {
        originalResponse.reset();
    }

    @Override
    public void setLocale(Locale locale) {
        originalResponse.setLocale(locale);
    }

    @Override
    public Locale getLocale() {
        return originalResponse.getLocale();
    }
}
