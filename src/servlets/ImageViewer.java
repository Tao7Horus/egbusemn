// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.io.OutputStream;
import common.Log;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.File;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class ImageViewer extends HttpServlet
{
    private static final String RELATIVE_PATH = "../";
    
    public void doGet(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final String imageName = req.getParameter("imageName");
        final String dataSaveDirectory = "/data1/AppData/apps/usemn/seal/";
        File imgFile = null;
        FileInputStream ifo = null;
        ByteArrayOutputStream baos = null;
        OutputStream out = null;
        try {
            imgFile = new File(String.valueOf(dataSaveDirectory) + imageName);
            ifo = new FileInputStream(imgFile);
            baos = new ByteArrayOutputStream();
            final byte[] buf = new byte[1024];
            int readlength = 0;
            while ((readlength = ifo.read(buf)) != -1) {
                baos.write(buf, 0, readlength);
            }
            byte[] imgbuf = (byte[])null;
            imgbuf = baos.toByteArray();
            final int length = imgbuf.length;
            out = (OutputStream)res.getOutputStream();
            out.write(imgbuf, 0, length);
        }
        catch (Exception exm) {
            Log.debug(" 이미지 읽기 에러 :imageName[" + imageName + "]:" + exm.toString());
        }
        finally {
            if (ifo != null) {
                try {
                    ifo.close();
                }
                catch (Exception ex) {}
            }
            if (baos != null) {
                try {
                    baos.close();
                }
                catch (Exception ex2) {}
            }
            if (out != null) {
                try {
                    out.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (ifo != null) {
            try {
                ifo.close();
            }
            catch (Exception ex4) {}
        }
        if (baos != null) {
            try {
                baos.close();
            }
            catch (Exception ex5) {}
        }
        if (out != null) {
            try {
                out.close();
            }
            catch (Exception ex6) {}
        }
    }
}
