// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.io.ByteArrayOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Hashtable;

public class Tcookie
{
    private static Hashtable USER_HASH;
    private String ope_Cooks;
    private String[] temp;
    private String sabun;
    private String name;
    private String buseo;
    private String jichong;
    private String jikchek;
    
    static {
        (Tcookie.USER_HASH = new Hashtable()).put("000512", "신경운");
        Tcookie.USER_HASH.put("000201", "박소아");
        Tcookie.USER_HASH.put("000273", "송종인");
        Tcookie.USER_HASH.put("000305", "이은정");
        Tcookie.USER_HASH.put("000310", "김혜경");
    }
    
    public Tcookie(final HttpServletRequest req) {
        this.ope_Cooks = null;
        this.temp = new String[5];
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
    
    private static String decode(final String a) {
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
    
    public String getValidUserCheck() throws Exception {
        if (this.sabun == null || this.sabun.equals("")) {
            throw new Exception("로그인되지 않았습니다.");
        }
        if (!Tcookie.USER_HASH.containsKey(this.sabun)) {
            throw new Exception("조회 권한이 없습니다.");
        }
        return this.sabun;
    }
}
