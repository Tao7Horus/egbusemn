// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.util.Enumeration;
import devpia.dextupload.FileItem;
import java.io.IOException;
import common.Log;
import devpia.dextupload.image.ImageTool;
import java.io.File;
import devpia.dextupload.FileUpload;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletRequest;
import java.util.Hashtable;

public class FileUpManager
{
    private static final int DEFAULT_MAX_POST_SIZE = 30;
    private Hashtable paramHash;
    private Hashtable fileHash;
    String strPath;
    
    public FileUpManager(final ServletRequest servletRequest, final String s, final HttpServletResponse httpServletResponse) throws UploadedFileIOException {
        this((HttpServletRequest)servletRequest, s, httpServletResponse);
    }
    
    public FileUpManager(final ServletRequest servletRequest, final String s, final int n, final HttpServletResponse httpServletResponse) throws UploadedFileIOException {
        this((HttpServletRequest)servletRequest, s, n, httpServletResponse);
    }
    
    public FileUpManager(final ServletRequest servletRequest, final String s, final String s2, final HttpServletResponse httpServletResponse) throws UploadedFileIOException {
        this((HttpServletRequest)servletRequest, s, s2, httpServletResponse);
    }
    
    public FileUpManager(final ServletRequest servletRequest, final String s, final String s2, final int n, final HttpServletResponse httpServletResponse) throws UploadedFileIOException {
        this((HttpServletRequest)servletRequest, s, s2, n, httpServletResponse);
    }
    
    public FileUpManager(final ServletRequest servletRequest, final String s, final String[] array, final HttpServletResponse httpServletResponse) throws UploadedFileIOException {
        this((HttpServletRequest)servletRequest, s, array, httpServletResponse);
    }
    
    public FileUpManager(final ServletRequest servletRequest, final String s, final String[] array, final int n, final HttpServletResponse httpServletResponse) throws UploadedFileIOException {
        this((HttpServletRequest)servletRequest, s, array, n, httpServletResponse);
    }
    
    public FileUpManager(final HttpServletRequest httpServletRequest, final String s, final HttpServletResponse httpServletResponse) throws UploadedFileIOException {
        this(httpServletRequest, s, new String[] { null }, 30, httpServletResponse);
    }
    
    public FileUpManager(final HttpServletRequest httpServletRequest, final String s, final int n, final HttpServletResponse httpServletResponse) throws UploadedFileIOException {
        this(httpServletRequest, s, new String[] { null }, n, httpServletResponse);
    }
    
    public FileUpManager(final HttpServletRequest httpServletRequest, final String s, final String s2, final HttpServletResponse httpServletResponse) throws UploadedFileIOException {
        this(httpServletRequest, s, new String[] { s2 }, 30, httpServletResponse);
    }
    
    public FileUpManager(final HttpServletRequest httpServletRequest, final String s, final String s2, final int n, final HttpServletResponse httpServletResponse) throws UploadedFileIOException {
        this(httpServletRequest, s, new String[] { s2 }, n, httpServletResponse);
    }
    
    public FileUpManager(final HttpServletRequest httpServletRequest, final String s, final String[] array, final HttpServletResponse httpServletResponse) throws UploadedFileIOException {
        this(httpServletRequest, s, array, 30, httpServletResponse);
    }
    
    public FileUpManager(final HttpServletRequest httpServletRequest, final String s, final String[] array, final int n, final HttpServletResponse httpServletResponse) throws UploadedFileIOException {
        this.paramHash = null;
        this.fileHash = null;
        this.strPath = "/app/bea81/wl81/lib/DEXTUploadJ";
        final FileUpload fileUpload = new FileUpload(httpServletRequest, httpServletResponse);
        try {
            fileUpload.setLicenseFilePath(this.strPath + File.separator + "dextuploadj.config");
            fileUpload.setMaxFileLength(n * 1024 * 1024);
            fileUpload.UploadStart(s);
            final FileItem fileItem = fileUpload.getFileItem("inGam");
            final Enumeration parameterNames = fileUpload.getParameterNames();
            if (parameterNames != null) {
                if (this.paramHash == null) {
                    this.paramHash = new Hashtable();
                }
                while (parameterNames.hasMoreElements()) {
                    final String s2 = (String)parameterNames.nextElement();
                    final String[] parameterValues = fileUpload.getParameterValues(s2);
                    if (parameterValues == null) {
                        this.paramHash.put(s2, new String[] { null });
                    }
                    else {
                        this.paramHash.put(s2, parameterValues);
                    }
                }
            }
            final Enumeration fileItemNames = fileUpload.getFileItemNames();
            if (fileItemNames != null) {
                if (this.fileHash == null) {
                    this.fileHash = new Hashtable();
                }
                while (fileItemNames.hasMoreElements()) {
                    final String s3 = (String)fileItemNames.nextElement();
                    fileItem.getFileName();
                    final String mimeType = fileItem.getMimeType();
                    if (fileItem != null) {
                        if (fileItem.IsUploaded()) {
                            final File file = fileItem.getFile();
                            ImageTool.getImageTool(file);
                            if (ImageTool.IsImage(file)) {
                                fileItem.Save(s, true);
                                if ("image/pjpeg".equals(mimeType) || "image/jpeg".equals(mimeType)) {
                                    this.fileHash.put(s3, new FileUpInfo(s, fileItem.getLastSavedFilePath(), fileItem.getFileName(), fileItem.getFileName(), (long)fileItem.length(), mimeType));
                                }
                                else {
                                    this.fileHash.put(s3, new FileUpInfo((String)null, (String)null, (String)null, 0L, (String)null));
                                }
                            }
                            else {
                                this.fileHash.put(s3, new FileUpInfo((String)null, (String)null, (String)null, 0L, (String)null));
                            }
                            Log.debug("파일경로::" + fileItem.getLastSavedFilePath());
                        }
                        else {
                            Log.debug("파일이 업로드 되지 않았습니다.");
                        }
                    }
                }
            }
        }
        catch (IOException ex) {
            Log.debug("Occurred error on reading or saving file, then uploading failed : " + ex);
            throw new UploadedFileIOException("Occurred error on reading or saving file, then uploading failed : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("Occurred error on reading or saving file, then uploading failed : " + ex2);
            throw new UploadedFileIOException("Occurred error on reading or saving file, then uploading failed : " + ex2.toString());
        }
        finally {
            try {
                if (fileUpload != null) {
                    fileUpload.dispose();
                }
            }
            catch (Exception ex3) {}
        }
    }
    
    public Enumeration getParameterNames() {
        return this.paramHash.keys();
    }
    
    public Enumeration getFileNames() {
        return this.fileHash.keys();
    }
    
    public String getParameter(final String s) {
        return this.getParameter(s, null, true);
    }
    
    public String getParameter(final String s, final String s2) {
        return this.getParameter(s, s2, true);
    }
    
    public String getParameter(final String s, final String s2, final boolean b) {
        try {
            final String[] array = (String[])this.paramHash.get(s);
            if (array[0] == null && array.length == 1) {
                if (b) {
                    return Hangule.toHangule(s2, "8859_1");
                }
                return s2;
            }
            else {
                if (b) {
                    return Hangule.toHangule(array[array.length - 1], "8859_1");
                }
                return array[array.length - 1];
            }
        }
        catch (Exception ex) {
            if (b) {
                return Hangule.toHangule(s2, "8859_1");
            }
            return s2;
        }
    }
    
    public String[] getParameterValues(final String s) {
        return this.getParameterValues(s, null, true);
    }
    
    public String[] getParameterValues(final String s, final String s2) {
        return this.getParameterValues(s, s2, true);
    }
    
    public String[] getParameterValues(final String s, final String s2, final boolean b) {
        try {
            final String[] array = (String[])this.paramHash.get(s);
            if (array[0] != null || array.length != 1) {
                if (b) {
                    for (int n = array.length - 1; 0 <= n; --n) {
                        array[n] = Hangule.toHangule(array[n], "8859_1");
                    }
                }
                return array;
            }
            if (b) {
                return new String[] { Hangule.toHangule(s2, "8859_1") };
            }
            return new String[] { s2 };
        }
        catch (Exception ex) {
            if (b) {
                return new String[] { Hangule.toHangule(s2, "8859_1") };
            }
            return new String[] { s2 };
        }
    }
    
    public String getDirPath(final String s) {
        try {
            return ((FileUpInfo)this.fileHash.get(s)).getDirPath();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public String getFilesystemPath(final String s) {
        try {
            return ((FileUpInfo)this.fileHash.get(s)).getFilesystemPath();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public String getFilesystemName(final String s) {
        try {
            return ((FileUpInfo)this.fileHash.get(s)).getFilesystemName();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public long getFileSize(final String s) {
        try {
            return ((FileUpInfo)this.fileHash.get(s)).getFileSize();
        }
        catch (Exception ex) {
            return -1L;
        }
    }
    
    public String getContentType(final String s) {
        try {
            return ((FileUpInfo)this.fileHash.get(s)).getContentType();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public String getOrginalFileName(final String s) {
        try {
            return Hangule.toHangule(((FileUpInfo)this.fileHash.get(s)).getOrginalFileName(), "8859_1");
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public File getFile(final String s) {
        try {
            return ((FileUpInfo)this.fileHash.get(s)).getFile();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public void clear() {
        try {
            this.paramHash.clear();
        }
        catch (UnsupportedOperationException ex) {}
        try {
            this.fileHash.clear();
        }
        catch (UnsupportedOperationException ex2) {}
    }
    
    public boolean moveTo(final String s, final String s2) throws Exception {
        if (s2 == null || s2.length() == 0) {
            throw new IllegalArgumentException("Not a directory: " + s2);
        }
        final File file = this.getFile(s);
        if (file == null || !file.isFile()) {
            throw new IllegalArgumentException("invalid a name: " + s);
        }
        if (file.getAbsolutePath().equals(s2)) {
            return true;
        }
        if (!s2.substring(0, 1).equals("/") && !s2.substring(0, 1).equals("\\")) {
            throw new IllegalArgumentException("invalid a directory: " + s2);
        }
        final File file2 = new File(s2);
        if (!file2.isDirectory()) {
            return false;
        }
        try {
            if (file.renameTo(new File(file2.getAbsolutePath() + File.separator + file.getName()))) {
                final String fromHangule = Hangule.fromHangule(this.getOrginalFileName(s));
                final String contentType = this.getContentType(s);
                final long fileSize = this.getFileSize(s);
                final File file3 = new File(file2.getAbsolutePath() + File.separator + file.getName());
                this.fileHash.remove(s);
                this.fileHash.put(s, new FileUpInfo(file3.getParent(), file3.toString(), file.getName(), fromHangule, fileSize, contentType));
                return true;
            }
            return false;
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public boolean moveTo(final String s, final String s2, final boolean b) throws Exception {
        if (s2 == null || s2.length() == 0) {
            throw new IllegalArgumentException("Not a directory: " + s2);
        }
        final File file = this.getFile(s);
        if (file == null || !file.isFile()) {
            throw new IllegalArgumentException("invalid a name: " + s);
        }
        String substring;
        if (s2.substring(0, 2).equals("./") || s2.substring(0, 2).equals(".\\")) {
            substring = s2.substring(2);
        }
        else {
            if (s2.substring(0, 1).equals("/") || s2.substring(0, 1).equals("\\")) {
                throw new IllegalArgumentException("invalid a directory: " + s2);
            }
            substring = s2;
        }
        final File file2 = new File(file.getParent() + File.separator + substring);
        if (!file2.isDirectory()) {
            if (!b) {
                return false;
            }
            try {
                if (!file2.mkdir()) {
                    return false;
                }
                if (file.renameTo(new File(file2.getAbsolutePath() + File.separator + file.getName()))) {
                    final String fromHangule = Hangule.fromHangule(this.getOrginalFileName(s));
                    final String contentType = this.getContentType(s);
                    final long fileSize = this.getFileSize(s);
                    this.fileHash.remove(s);
                    this.fileHash.put(s, new FileUpInfo(file.getParent(), file.toString(), file.getName(), fromHangule, fileSize, contentType));
                    return true;
                }
                return false;
            }
            catch (Exception ex) {
                throw ex;
            }
        }
        try {
            if (file.renameTo(new File(file2.getAbsolutePath() + File.separator + file.getName()))) {
                final String fromHangule2 = Hangule.fromHangule(this.getOrginalFileName(s));
                final String contentType2 = this.getContentType(s);
                final long fileSize2 = this.getFileSize(s);
                this.fileHash.remove(s);
                this.fileHash.put(s, new FileUpInfo(file.getParent(), file.toString(), file.getName(), fromHangule2, fileSize2, contentType2));
                return true;
            }
            return false;
        }
        catch (Exception ex2) {
            throw ex2;
        }
    }
    
    public boolean renameTo(final String s, String s2) throws Exception {
        if (s2 == null || s2.length() == 0) {
            throw new IllegalArgumentException("Not a directory: " + s2);
        }
        final File file = this.getFile(s);
        if (file == null || !file.isFile()) {
            throw new IllegalArgumentException("invalid a name: " + s);
        }
        final String name = file.getName();
        if (name.lastIndexOf(".") != -1) {
            final String substring = name.substring(name.lastIndexOf("."));
            final String lowerCase = substring.toLowerCase();
            if (lowerCase.indexOf("jpg") == 1) {
                s2 += lowerCase;
            }
            else {
                s2 += substring;
            }
        }
        try {
            if (file.renameTo(new File(file.getParent() + File.separator + s2))) {
                final String fromHangule = Hangule.fromHangule(this.getOrginalFileName(s));
                final String contentType = this.getContentType(s);
                final long fileSize = this.getFileSize(s);
                final File file2 = new File(file.getParent() + File.separator + s2);
                this.fileHash.remove(s);
                this.fileHash.put(s, new FileUpInfo(file2.getParent(), file2.toString(), file2.getName(), fromHangule, fileSize, contentType));
                return true;
            }
            return false;
        }
        catch (Exception ex) {
            throw ex;
        }
    }
}
