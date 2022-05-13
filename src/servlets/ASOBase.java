// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.ByteArrayOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ASOBase
{
    private String ope_Cooks;
    private String[] temp;
    private String sabun;
    private String name;
    private String buseo;
    private String jichong;
    private String jikchek;
    
    public ASOBase(final HttpServletRequest req, final HttpServletResponse res, final String s, final String s1) {
        this.ope_Cooks = null;
        this.temp = new String[5];
        this.sabun = null;
        this.name = null;
        this.buseo = null;
        this.jichong = null;
        this.jikchek = null;
        final Cookie[] cookies = req.getCookies();
        for (int i = 0; i < cookies.length; ++i) {
            if (cookies[i].getName().equals("OPE_Cooks")) {
                this.ope_Cooks = decode(cookies[i].getValue());
            }
        }
        int j = 0;
        int k = 0;
        for (int l = 0; l < this.ope_Cooks.length(); ++l) {
            final char ch = this.ope_Cooks.charAt(l);
            if (ch == '^') {
                if (j == 0) {
                    this.temp[j] = this.ope_Cooks.substring(k, l);
                }
                else {
                    this.temp[j] = this.ope_Cooks.substring(k + 1, l);
                }
                ++j;
                k = l;
            }
        }
        this.sabun = this.temp[0];
        this.name = this.temp[1];
        this.buseo = this.temp[2];
        this.jichong = this.temp[3];
        this.jikchek = this.temp[4];
    }
    
    public boolean isLogin() {
        return this.sabun != null && !this.sabun.equals("") && (this.sabun != null && !this.sabun.equals(""));
    }
    
    public static String decode(final String a) {
        final ByteArrayOutputStream output = new ByteArrayOutputStream(a.length());
        for (int i = 0; i < a.length(); ++i) {
            final int c = a.charAt(i);
            if (c == 43) {
                output.write(32);
            }
            else if (c == 37) {
                final int c2 = Character.digit(a.charAt(++i), 16);
                final int c3 = Character.digit(a.charAt(++i), 16);
                output.write(c2 * 16 + c3);
            }
            else {
                output.write(c);
            }
        }
        return output.toString();
    }
    
    public String getSABUN() {
        return this.sabun;
    }
    
    public String getNAME() {
        return this.name;
    }
    
    public String getBUSEO() {
        return this.buseo;
    }
    
    public String getJICHONG() {
        return this.jichong;
    }
    
    public String getJIKCHEK() {
        return this.jikchek;
    }
}
