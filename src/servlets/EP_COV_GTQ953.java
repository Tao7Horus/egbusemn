// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.io.File;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class EP_COV_GTQ953 extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final String fileName = req.getParameter("fileName");
        final String filePath = "/e-doc/USEMN/FINAN_STATE/";
        PrintWriter out = null;
        final String realFileName = fileName;
        if (fileName == null) {
            return;
        }
        File file = null;
        BufferedInputStream fin = null;
        BufferedOutputStream fout = null;
        try {
            file = new File(String.valueOf(filePath) + fileName);
            final byte[] buffer = new byte[2048];
            if (file.exists()) {
                if (file.isFile()) {
                    if (file.canRead()) {
                        res.setContentType("application/unknown");
                        final String utfRealFileName = URLEncoder.encode(realFileName, "UTF-8").replace('+', ' ');
                        final String utfFileName = URLEncoder.encode(file.getName(), "UTF-8").replace('+', ' ');
                        res.setHeader("Content-Disposition", "attachment; filename=" + utfFileName);
                        res.addHeader("Content-Length", Long.toString(file.length()));
                        fin = new BufferedInputStream(new FileInputStream(file));
                        fout = new BufferedOutputStream((OutputStream)res.getOutputStream());
                        int read = 0;
                        while ((read = fin.read(buffer, 0, buffer.length)) != -1) {
                            fout.write(buffer, 0, read);
                        }
                    }
                    else {
                        res.reset();
                        res.setContentType("text/html; charset=utf-8");
                        out = res.getWriter();
                        out.println("<script language='javascript'>");
                        out.println("\talert('T??m ki???m v??n b???n kh??ng th??nh c??ng.'); history.go(-1);");
                        out.println("</script>");
                    }
                }
                else {
                    res.reset();
                    res.setContentType("text/html; charset=utf-8");
                    out = res.getWriter();
                    out.println("<script language='javascript'>");
                    out.println("\talert('T??m ki???m v??n b???n kh??ng th??nh c??ng.'); history.go(-1);");
                    out.println("</script>");
                }
            }
            else {
                res.reset();
                res.setContentType("text/html; charset=utf-8");
                out = res.getWriter();
                out.println("<script language='javascript'>");
                out.println("\talert('V??n b???n y??u c???u kh??ng t???n t???i.'); history.go(-1);");
                out.println("</script>");
            }
        }
        catch (Exception ex) {
            ex.getMessage().indexOf("Broken pipe");
            res.reset();
            res.setContentType("text/html; charset=utf-8");
            out = res.getWriter();
            out.println("<script language='javascript'>");
            out.println("\talert('C?? l???i x???y ra khi ??ang th???c hi???n.\\r\\nB???n h??y th??? l???i.'); history.go(-1);");
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
