// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.util.Enumeration;
import javax.servlet.ServletRequest;
import java.util.Vector;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import java.util.Hashtable;

public class MultipartRequest
{
    private static final int DEFAULT_MAX_POST_SIZE = 1024;
    protected Hashtable parameters;
    protected Hashtable files;
    
    public MultipartRequest(final HttpServletRequest httpServletRequest, final String s) throws IOException {
        this(httpServletRequest, s, new String[] { null }, 1024);
    }
    
    public MultipartRequest(final HttpServletRequest httpServletRequest, final String s, final int n) throws IOException {
        this(httpServletRequest, s, new String[] { null }, n);
    }
    
    public MultipartRequest(final HttpServletRequest httpServletRequest, final String s, final String[] array) throws IOException {
        this(httpServletRequest, s, array, 1024);
    }
    
    public MultipartRequest(final HttpServletRequest httpServletRequest, final String s, final String[] array, final int n) throws IOException {
        this.parameters = new Hashtable();
        this.files = new Hashtable();
        if (httpServletRequest == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        if (s == null) {
            throw new IllegalArgumentException("saveDirectory cannot be null");
        }
        if (n <= 0) {
            throw new IllegalArgumentException("maxPostSize must be positive");
        }
        final File file = new File(s);
        if (!file.isDirectory() && !file.mkdir()) {
            throw new IllegalArgumentException("Not a directory: " + s);
        }
        if (!file.canWrite()) {
            throw new IllegalArgumentException("Not writable: " + s);
        }
        final MultipartParser multipartParser = new MultipartParser(httpServletRequest, n);
        int n2 = 0;
        Part nextPart;
        while ((nextPart = multipartParser.readNextPart()) != null) {
            final String name = nextPart.getName();
            if (nextPart.isParam()) {
                final String stringValue = ((ParamPart)nextPart).getStringValue();
                Vector<String> vector = (Vector<String>)this.parameters.get(name);
                if (vector == null) {
                    vector = new Vector<String>();
                    this.parameters.put(name, vector);
                }
                vector.addElement(stringValue);
            }
            else {
                if (!nextPart.isFile()) {
                    continue;
                }
                final FilePart filePart = (FilePart)nextPart;
                final String fileName = filePart.getFileName();
                if (fileName != null) {
                    if (array != null && array[0] != null) {
                        try {
                            final String extension = this.getExtension(fileName);
                            if (extension != null) {
                                filePart.setFileName(array[n2++] + "." + extension);
                            }
                            else {
                                filePart.setFileName(array[n2++]);
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException ex) {}
                    }
                    filePart.writeTo(file);
                    this.files.put(name, new UploadedFile(file.toString(), filePart.getFileName(), fileName, filePart.getContentType()));
                }
                else {
                    this.files.put(name, new UploadedFile((String)null, (String)null, (String)null));
                }
            }
        }
    }
    
    public MultipartRequest(final ServletRequest servletRequest, final String s) throws IOException {
        this((HttpServletRequest)servletRequest, s);
    }
    
    public MultipartRequest(final ServletRequest servletRequest, final String s, final int n) throws IOException {
        this((HttpServletRequest)servletRequest, s, n);
    }
    
    public MultipartRequest(final ServletRequest servletRequest, final String s, final String[] array, final int n) throws IOException {
        this((HttpServletRequest)servletRequest, s, array, n);
    }
    
    public Enumeration getParameterNames() {
        return this.parameters.keys();
    }
    
    public Enumeration getFileNames() {
        return this.files.keys();
    }
    
    public String getParameter(final String s) {
        try {
            final Vector<String> vector = (Vector<String>)this.parameters.get(s);
            if (vector == null || vector.size() == 0) {
                return null;
            }
            return vector.elementAt(vector.size() - 1);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public String[] getParameterValues(final String s) {
        try {
            final Vector vector = (Vector)this.parameters.get(s);
            if (vector == null || vector.size() == 0) {
                return null;
            }
            final String[] array = new String[vector.size()];
            vector.copyInto(array);
            return array;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public String getFilesystemName(final String s) {
        try {
            return ((UploadedFile)this.files.get(s)).getFilesystemName();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public String getContentType(final String s) {
        try {
            return ((UploadedFile)this.files.get(s)).getContentType();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public File getFile(final String s) {
        try {
            return ((UploadedFile)this.files.get(s)).getFile();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private String getExtension(final String s) {
        if (s == null) {
            return null;
        }
        int lastIndex;
        if ((lastIndex = s.lastIndexOf(".")) != -1) {
            return s.substring(++lastIndex);
        }
        return null;
    }
    
    public String getOrginalFileName(final String s) {
        try {
            return ((UploadedFile)this.files.get(s)).getOrginalFileName();
        }
        catch (Exception ex) {
            return null;
        }
    }
}
