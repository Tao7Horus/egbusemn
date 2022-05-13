// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.util.Enumeration;
import java.util.Vector;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletInputStream;

public class MultipartParser
{
    private ServletInputStream in;
    private String boundary;
    private FilePart lastFilePart;
    private byte[] buf;
    
    public MultipartParser(final HttpServletRequest httpServletRequest, final int n) throws IOException {
        this(httpServletRequest, n, true, true);
    }
    
    public MultipartParser(final HttpServletRequest httpServletRequest, final int n, final boolean b, final boolean b2) throws IOException {
        this.buf = new byte[8192];
        String s = null;
        final String header = httpServletRequest.getHeader("Content-Type");
        final String contentType = httpServletRequest.getContentType();
        if (header == null && contentType != null) {
            s = contentType;
        }
        else if (contentType == null && header != null) {
            s = header;
        }
        else if (header != null && contentType != null) {
            s = ((header.length() > contentType.length()) ? header : contentType);
        }
        if (s == null || !s.toLowerCase().startsWith("multipart/form-data")) {
            throw new IOException("Posted content type isn't multipart/form-data");
        }
        final int contentLength = httpServletRequest.getContentLength();
        if (contentLength > n) {
            throw new IOException("Posted content length of " + contentLength + " exceeds limit of " + n);
        }
        final String boundary = this.extractBoundary(s);
        if (boundary == null) {
            throw new IOException("Separation boundary was not specified");
        }
        ServletInputStream inputStream = httpServletRequest.getInputStream();
        if (b) {
            inputStream = new BufferedServletInputStream(inputStream);
        }
        if (b2) {
            inputStream = new LimitedServletInputStream(inputStream, contentLength);
        }
        this.in = inputStream;
        this.boundary = boundary;
        final String line = this.readLine();
        if (line == null) {
            throw new IOException("Corrupt form data: premature ending");
        }
        if (!line.startsWith(boundary)) {
            throw new IOException("Corrupt form data: no leading boundary: " + line + " != " + boundary);
        }
    }
    
    public Part readNextPart() throws IOException {
        if (this.lastFilePart != null) {
            this.lastFilePart.getInputStream().close();
            this.lastFilePart = null;
        }
        final Vector<String> vector = new Vector<String>();
        final String line = this.readLine();
        if (line == null) {
            return null;
        }
        if (line.length() == 0) {
            return null;
        }
        vector.addElement(line);
        String line2;
        while ((line2 = this.readLine()) != null && line2.length() > 0) {
            vector.addElement(line2);
        }
        if (line2 == null) {
            return null;
        }
        String s = null;
        String s2 = null;
        String s3 = null;
        String s4 = "text/plain";
        final Enumeration<String> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final String s5 = (String)elements.nextElement();
            if (s5.toLowerCase().startsWith("content-disposition:")) {
                final String[] dispositionInfo = this.extractDispositionInfo(s5);
                s = dispositionInfo[1];
                s2 = dispositionInfo[2];
                s3 = dispositionInfo[3];
            }
            else {
                if (!s5.toLowerCase().startsWith("content-type:")) {
                    continue;
                }
                final String contentType = this.extractContentType(s5);
                if (contentType == null) {
                    continue;
                }
                s4 = contentType;
            }
        }
        if (s2 == null) {
            return new ParamPart(s, this.in, this.boundary);
        }
        if (s2.equals("")) {
            s2 = null;
        }
        return this.lastFilePart = new FilePart(s, this.in, this.boundary, s4, s2, s3);
    }
    
    private String extractBoundary(final String s) {
        final int lastIndex = s.lastIndexOf("boundary=");
        if (lastIndex == -1) {
            return null;
        }
        String s2 = s.substring(lastIndex + 9);
        if (s2.charAt(0) == '\"') {
            s2 = s2.substring(1, s2.lastIndexOf(34));
        }
        return "--" + s2;
    }
    
    private String[] extractDispositionInfo(String lowerCase) throws IOException {
        final String[] array = new String[4];
        final String s = lowerCase;
        lowerCase = s.toLowerCase();
        final int index = lowerCase.indexOf("content-disposition: ");
        final int index2 = lowerCase.indexOf(";");
        if (index == -1 || index2 == -1) {
            throw new IOException("Content disposition corrupt: " + s);
        }
        final String substring = lowerCase.substring(index + 21, index2);
        if (!substring.equals("form-data")) {
            throw new IOException("Invalid content disposition: " + substring);
        }
        final int index3 = lowerCase.indexOf("name=\"", index2);
        final int index4 = lowerCase.indexOf("\"", index3 + 7);
        if (index3 == -1 || index4 == -1) {
            throw new IOException("Content disposition corrupt: " + s);
        }
        final String substring2 = s.substring(index3 + 6, index4);
        String substring3 = null;
        String substring4 = null;
        final int index5 = lowerCase.indexOf("filename=\"", index4 + 2);
        final int index6 = lowerCase.indexOf("\"", index5 + 10);
        if (index5 != -1 && index6 != -1) {
            substring3 = (substring4 = s.substring(index5 + 10, index6));
            final int max = Math.max(substring3.lastIndexOf(47), substring3.lastIndexOf(92));
            if (max > -1) {
                substring3 = substring3.substring(max + 1);
            }
        }
        array[0] = substring;
        array[1] = substring2;
        array[2] = substring3;
        array[3] = substring4;
        return array;
    }
    
    private String extractContentType(String lowerCase) throws IOException {
        String substring = null;
        final String s = lowerCase;
        lowerCase = s.toLowerCase();
        if (lowerCase.startsWith("content-type")) {
            final int index = lowerCase.indexOf(" ");
            if (index == -1) {
                throw new IOException("Content type corrupt: " + s);
            }
            substring = lowerCase.substring(index + 1);
        }
        else if (lowerCase.length() != 0) {
            throw new IOException("Malformed line after disposition: " + s);
        }
        return substring;
    }
    
    private String readLine() throws IOException {
        final StringBuffer sb = new StringBuffer();
        int i;
        do {
            i = this.in.readLine(this.buf, 0, this.buf.length);
            if (i != -1) {
                sb.append(new String(this.buf, 0, i, "ISO-8859-1"));
            }
        } while (i == this.buf.length);
        if (sb.length() == 0) {
            return null;
        }
        final int length = sb.length();
        if (sb.charAt(length - 2) == '\r') {
            sb.setLength(length - 2);
        }
        else {
            sb.setLength(length - 1);
        }
        return sb.toString();
    }
}
