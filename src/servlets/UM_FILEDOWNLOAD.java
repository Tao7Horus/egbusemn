// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.IOException;
import common.Log;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_FILEDOWNLOAD extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final String fileType = req.getParameter("fileType");
        final String imageName = req.getParameter("imageName");
        final String filePath = "/data/USEMN/";
        PrintWriter out = null;
        File file = null;
        BufferedInputStream fin = null;
        BufferedOutputStream fout = null;
        try {
            file = new File(String.valueOf(filePath) + imageName);
            final byte[] buffer = new byte[2048];
            System.out.println("filePath+imageName => " + filePath + imageName);
            if (file.exists()) {
                if (file.isFile()) {
                    if (file.canRead()) {
                        res.setContentType("text/html;charset=euc-kr");
                        res.setHeader("Content-Disposition", "inline; filename=\"" + new String(file.getName().getBytes(), "ISO8859_1") + "\"");
                        res.addHeader("Content-Length", Long.toString(file.length()));
                        fin = new BufferedInputStream(new FileInputStream(file));
                        fout = new BufferedOutputStream((OutputStream)res.getOutputStream());
                        int read = 0;
                        while ((read = fin.read(buffer, 0, buffer.length)) != -1) {
                            fout.write(buffer, 0, read);
                        }
                    }
                    else {
                        Log.debug("???????????? ???????????? Error[UM_FILEDOWNLOAD.java], ?????? ?????? ?????? ??????");
                        Log.debug("parameter:??????[" + fileType + "], ????????????[" + imageName + "]");
                        res.reset();
                        res.setContentType("text/html; charset=euc-kr");
                        out = res.getWriter();
                        out.println("<script language='javascript'>");
                        out.println("\talert('?????? ????????? ????????? ????????????.'); history.go(-1);");
                        out.println("</script>");
                    }
                }
                else {
                    Log.debug("???????????? ???????????? Error[UM_FILEDOWNLOAD.java], ?????? ????????? ??????");
                    Log.debug("parameter:??????[" + fileType + "], ????????????[" + imageName + "]");
                    res.reset();
                    res.setContentType("text/html; charset=euc-kr");
                    out = res.getWriter();
                    out.println("<script language='javascript'>");
                    out.println("\talert('?????? ????????? ????????????.[?????? ??????]'); history.go(-1);");
                    out.println("</script>");
                }
            }
            else {
                Log.debug("???????????? ???????????? Error[UM_FILEDOWNLOAD.java], ????????? ???????????? ??????");
                Log.debug("parameter:??????[" + fileType + "], ????????????[" + imageName + "]");
                res.reset();
                res.setContentType("text/html; charset=euc-kr");
                out = res.getWriter();
                out.println("<script language='javascript'>");
                out.println("\talert('???????????? ????????? ???????????? ????????????.'); history.go(-1);");
                out.println("</script>");
            }
        }
        catch (Exception ex) {
            Log.debug("???????????? ???????????? Error[UM_FILEDOWNLOAD.java]");
            Log.debug("parameter:??????[" + fileType + "], ????????????[" + imageName + "]");
            Log.debug("?????? ?????? : " + ex.toString());
            res.reset();
            res.setContentType("text/html; charset=euc-kr");
            out = res.getWriter();
            out.println("<script language='javascript'>");
            out.println("\talert('?????? ????????? Error ??? ??????????????????.\\r\\n?????? ????????? ????????? ????????????.'); history.go(-1);");
            out.println("</script>");
        }
        finally {
            try {
                if (fout != null) {
                    fout.close();
                }
            }
            catch (IOException ex2) {}
            try {
                if (fin != null) {
                    fin.close();
                }
            }
            catch (IOException ex3) {}
        }
        try {
            if (fout != null) {
                fout.close();
            }
        }
        catch (IOException ex4) {}
        try {
            if (fin != null) {
                fin.close();
            }
        }
        catch (IOException ex5) {}
    }
}
