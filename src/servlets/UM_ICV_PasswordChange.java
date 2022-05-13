// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ICV_PasswordChange extends HttpServlet
{
    public String[] getParams(final HttpServletRequest req, final String param, final String init) throws UnsupportedEncodingException {
        String[] res = (String[])null;
        res = req.getParameterValues(param);
        if (res != null) {
            for (int i = 0; i < res.length; ++i) {
                if (res[i] == null || res[i].equals("null") || res[i].equals("")) {
                    res[i] = init;
                }
                else {
                    res[i] = new String(res[i]);
                }
            }
        }
        return res;
    }
    
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        throw new Error("Unresolved compilation problems: \n\tThe type CommonMessage is ambiguous\n\tThe type CommonMessage is ambiguous\n\tThe type CommonMessage is ambiguous\n\tThe type CommonMessage is ambiguous\n\tThe type CommonMessage is ambiguous\n\tThe type CommonMessage is ambiguous\n\tThe type CommonMessage is ambiguous\n");
    }
    
    public static String isStrNull(final String value) {
        return (value.trim().length() == 0) ? "" : value.trim();
    }
}
