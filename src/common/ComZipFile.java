// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.io.FilterOutputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;

public class ComZipFile
{
    private static final int COMPRESSION_LEVEL = 8;
    private static final int BUFFER_SIZE = 2048;
    
    public static void compressFileToZip(final String s, final String s2) throws Exception {
        final File file = new File(s);
        if (!file.isFile() && !file.isDirectory()) {
            throw new Exception("ComZipFile.compressFileToZip() Không tìm thấy thư mục.");
        }
        if (s2.indexOf(".zip") == -1) {
            throw new Exception("Chỉ có thể tạo được 1 ZIP file.");
        }
        OutputStream outputStream = null;
        OutputStream outputStream2 = null;
        ZipArchiveOutputStream zipArchiveOutputStream = null;
        try {
            outputStream = new FileOutputStream(s2);
            outputStream2 = new BufferedOutputStream(outputStream);
            zipArchiveOutputStream = new ZipArchiveOutputStream(outputStream2);
            zipArchiveOutputStream.setLevel(8);
            zipEntry(file, s, zipArchiveOutputStream);
            zipArchiveOutputStream.finish();
        }
        finally {
            if (zipArchiveOutputStream != null) {
                zipArchiveOutputStream.close();
            }
            if (outputStream2 != null) {
                ((FilterOutputStream)outputStream2).close();
            }
            if (outputStream != null) {
                ((FileOutputStream)outputStream).close();
            }
        }
    }
    
    private static void zipEntry(final File file, final String s, final ZipArchiveOutputStream zipArchiveOutputStream) throws Exception {
        if (file.isDirectory()) {
            final File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; ++i) {
                zipEntry(listFiles[i], s, zipArchiveOutputStream);
            }
        }
        else {
            BufferedInputStream bufferedInputStream = null;
            try {
                final String path = file.getPath();
                final String substring = path.substring(s.length() + 1, path.length());
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                final ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(substring);
                zipArchiveEntry.setTime(file.lastModified());
                zipArchiveOutputStream.putArchiveEntry((ArchiveEntry)zipArchiveEntry);
                final byte[] array = new byte[2048];
                int read;
                while ((read = bufferedInputStream.read(array, 0, 2048)) != -1) {
                    zipArchiveOutputStream.write(array, 0, read);
                }
                zipArchiveOutputStream.closeArchiveEntry();
            }
            finally {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            }
        }
    }
    
    public void compressFileToZip(final String[] array, final String[] array2, final String s) throws Exception {
        if (array == null || array2 == null || s == null) {
            throw new Exception("Cần kiểm tra lại giá trị của các tham số truyền vào");
        }
        final int n = 1024;
        final byte[] array3 = new byte[n];
        final String lowerCase = s.toLowerCase();
        try {
            ComUtil.retNullParameterCheck(array);
            ComUtil.retNullParameterCheck(array2);
            if (lowerCase.indexOf(".zip") == -1) {
                throw new Exception("Chỉ có thể tạo ra được 1 ZIP file.outFilename[" + s + "]");
            }
            if (new File(s).exists()) {
                Log.errors(this.getClass().getName() + ".compressFileToZip() Original file exist[" + s + "]");
                return;
            }
            final ZipArchiveOutputStream zipArchiveOutputStream = new ZipArchiveOutputStream((OutputStream)new BufferedOutputStream(new FileOutputStream(s)));
            for (int i = 0; i < array.length; ++i) {
                final BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(array[i]), n);
                zipArchiveOutputStream.putArchiveEntry((ArchiveEntry)new ZipArchiveEntry(array2[i]));
                int read;
                while ((read = bufferedInputStream.read(array3, 0, n)) > 0) {
                    zipArchiveOutputStream.write(array3, 0, read);
                }
                zipArchiveOutputStream.closeArchiveEntry();
                bufferedInputStream.close();
            }
            zipArchiveOutputStream.close();
        }
        catch (Exception ex) {
            Log.errors(this.getClass().getName() + ".compressFileToZip() outFilename[" + s + "]:" + ex.toString());
            throw ex;
        }
    }
}
