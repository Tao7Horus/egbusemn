// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.util.StringTokenizer;
import java.util.Enumeration;
import common.util.CommUtil;
import javax.servlet.http.HttpServletRequest;

public class WebUtil
{
    public String getNextPageIndexes(final HttpServletRequest req, String className, final int totalRow, final int page_size, final int page_no) {
        String result = "";
        if (className == null) {
            className = req.getRequestURI();
        }
        final String url = this.getUrl(req, className);
        if (totalRow <= page_size) {
            return result;
        }
        final int startPage = ((page_no % 10 == 0) ? (page_no - 1) : page_no) / 10 * 10 + 1;
        final int totalPage = totalRow / page_size + ((totalRow % page_size != 0) ? 1 : 0);
        final int endPage = (totalPage - startPage >= 10) ? (startPage + 9) : totalPage;
        result = String.valueOf(result) + "<table width=100% border=0 cellpadding=2 cellspacing=2 class=tr><tr><td align=center background=/img/dotlines.gif height=1></td></tr><tr><td align=center class=redc>";
        if (page_no > 10) {
            result = String.valueOf(result) + "<a href='" + url + "&page_no=" + (startPage - 1) + "'><img align=absmiddle src=/img/bu_backarw.gif border=0></a>";
        }
        else if (page_no > 1) {
            result = String.valueOf(result) + "<img align=absmiddle src=/img/bu_backarwn.gif border=0>";
        }
        else {
            result = String.valueOf(result) + "<img align=absmiddle src=/img/bu_backarwn.gif border=0>";
        }
        for (int i = startPage; i <= endPage; ++i) {
            if (page_no == i) {
                result = String.valueOf(result) + "[" + i + "]";
            }
            else {
                result = String.valueOf(result) + "<a href='" + url + "&page_no=" + i + "'>[" + i + "]</a>";
            }
        }
        if (endPage != totalPage) {
            result = String.valueOf(result) + "<a href='" + url + "&page_no=" + (endPage + 1) + "'><img align=absmiddle src=/img/bu_nextarw.gif border=0></a><a href='" + url + "&page_no=" + (endPage + 1) + "'></a>";
        }
        else if (page_no < endPage) {
            result = String.valueOf(result) + "<img align=absmiddle src=/img/bu_nextarwn.gif border=0>";
        }
        else {
            result = String.valueOf(result) + "<img align=absmiddle src=/img/bu_nextarwn.gif border=0>";
        }
        result = String.valueOf(result) + "</td></tr><tr><td align=center background=/img/dotlines.gif height=1></td></tr></table>";
        return result;
    }
    
    public String getUrl(final HttpServletRequest req, final String className) {
        String result = String.valueOf(className) + "?";
        final Enumeration names = req.getParameterNames();
        String temp = null;
        while (names.hasMoreElements()) {
            temp = names.nextElement();
            if (temp.equals("page_no")) {
                continue;
            }
            result = String.valueOf(result) + temp + "=" + CommUtil.escapeHtml(req.getParameter(temp)) + "&";
        }
        return result;
    }
    
    public String getUrl(final HttpServletRequest req, final String className, final String page_no) {
        return String.valueOf(this.getUrl(req, className)) + "&page_no=" + page_no;
    }
    
    public String cssStype() {
        final StringBuffer temp = new StringBuffer("");
        temp.append("<style>");
        temp.append(".retda\t{  font-family: '돋움', '돋움체'; font-size: 9pt; font-weight: normal; text-align: left; font-style: normal; line-height: 12pt;text-decoration: none; color: #000000; background-color:#E0E0E0; border: #333333; border-style: solid; border-top-width: 0.5pt; border-right-width: 0.5pt; border-bottom-width: 0.5pt; border-left-width: 0.5pt; text-indent: 7pt; height: 13.5pt}");
        temp.append(".retdar\t{  font-family: '돋움', '돋움체'; font-size: 9pt;  font-weight: normal;text-align: right; font-style: normal; line-height: 12pt;text-decoration: none; color: #000000; background-color:#E0E0E0; border: #333333; border-style: solid; border-top-width: 0.5pt; border-right-width: 0.5pt; border-bottom-width: 0.5pt; border-left-width: 0.5pt; text-indent: 7pt; height: 13.5ptt}");
        temp.append(".retdac\t{  font-family: '돋움', '돋움체'; font-size: 9pt; font-weight: normal; text-align: center; font-style: normal; line-height: 12pt;text-decoration: none; color: #000000; background-color:#E0E0E0;  border: #333333; border-style: solid; border-top-width: 0.5pt; border-right-width: 0.5pt; border-bottom-width: 0.5pt; border-left-width: 0.5pt; text-indent: 7pt; height: 13.5pt}");
        temp.append(".retdb\t{  font-family: '굴림'; font-size: 9pt; text-align: left; font-style: normal; text-decoration: none; background-color:#FFFFFF ; line-height: 14pt; color: #000000; border: #333333; border-style: solid; border-top-width: 0.5pt; border-right-width: 0.5pt; border-bottom-width: 0.5pt; border-left-width: 0.5pt; text-indent: 7pt; height: 13.5pt}");
        temp.append(".retdbr\t{  font-family: '굴림'; font-size: 9pt; text-align: right; font-style: normal; text-decoration: none; background-color:#FFFFFF ; line-height: 14pt; border: #333333; border-style: solid; border-top-width: 0.5pt; border-right-width: 0.5pt; border-bottom-width: 0.5pt; border-left-width: 0.5pt; text-indent: 7pt; height: 13.5pt}");
        temp.append(".retdbc\t{  font-family: '굴림'; font-size: 9pt; text-align: center; font-style: normal; text-decoration: none; background-color:#FFFFFF ; line-height: 14pt; border: #333333; border-style: solid; border-top-width: 0.5pt; border-right-width: 0.5pt; border-bottom-width: 0.5pt; border-left-width: 0.5pt; text-indent: 7pt; height: 13.5pt}");
        temp.append(".rtdb\t{  font-family: '돋움', '돋움체'; font-size: 9pt; text-align: left; font-style: normal; line-height: 14pt; text-decoration: none; color: #333333; background-color:#FFFFFF;  border:#C6C6C6; border-style: solid; border-top-width: 0.5pt; border-right-width: 0.5pt; border-bottom-width: 0.5pt; border-left-width: 0.5pt; text-indent: 7pt; height: 13.5pt}");
        temp.append(".rtdbr\t{  font-family: '돋움', '돋움체'; font-size: 9pt; text-align: right; font-style: normal; line-height: 14pt; text-decoration: none; color: #333333; background-color:#FFFFFF;   border:#C6C6C6; border-style: solid; border-top-width: 0.5pt; border-right-width: 0.5pt; border-bottom-width: 0.5pt; border-left-width: 0.5pt; text-indent: 7pt; height: 13.5pt}");
        temp.append(".rtdbc\t{  font-family: '돋움', '돋움체'; font-size: 9pt; text-align: center; font-style: normal; line-height: 14pt; text-decoration: none; color: #333333; background-color:#FFFFFF; border:#C6C6C6; border-style: solid; border-top-width: 0.5pt; border-right-width: 0.5pt; border-bottom-width: 0.5pt; border-left-width: 0.5pt; text-indent: 7pt; height: 13.5pt}");
        temp.append(".rtdc\t{  font-family: '돋움', '돋움체'; font-size: 9pt; text-align: left; font-style: normal; line-height: 14pt; text-decoration: none; color: #000000; background-color:#F7F7F7;border: #C6C6C6;  border-style: solid; border-top-width: 0.5pt; border-right-width: 0.5pt; border-bottom-width: 0.5pt; border-left-width: 0.5pt; text-indent: 7pt; height: 13.5pt}");
        temp.append(".rtdcr\t{  font-family: '돋움', '돋움체'; font-size: 9pt; text-align: right; font-style: normal; line-height: 14pt; text-decoration: none; color: #000000; background-color:#F7F7F7; border:#C6C6C6; border-style: solid; border-top-width: 0.5pt; border-right-width: 0.5pt; border-bottom-width: 0.5pt; border-left-width: 0.5pt; text-indent: 7pt; height: 13.5pt}");
        temp.append(".rtdcc\t{  font-family: '돋움', '돋움체'; font-size: 9pt; text-align: center; font-style: normal; line-height: 14pt; text-decoration: none; color: #000000; background-color:#F7F7F7; border: 0.5pt #C6C6C6 solid; text-indent: 7pt; height: 13.5pt}");
        temp.append("</style>");
        return temp.toString();
    }
    
    public static String replace(final String src, final String oldstr, final String newstr) {
        if (src == null) {
            return null;
        }
        final StringBuffer dest = new StringBuffer("");
        final int len = oldstr.length();
        final int srclen = src.length();
        int pos;
        int oldpos;
        for (pos = 0, oldpos = 0; (pos = src.indexOf(oldstr, oldpos)) >= 0; oldpos = pos + len) {
            dest.append(src.substring(oldpos, pos));
            dest.append(newstr);
        }
        if (oldpos < srclen) {
            dest.append(src.substring(oldpos, srclen));
        }
        return dest.toString();
    }
    
    public static String standardComNO(final String comno) {
        if (comno == null) {
            return "";
        }
        final String temp = replace(comno, "-", "");
        return String.valueOf(temp.substring(0, 3)) + "-" + temp.substring(3, 5) + "-" + temp.substring(5);
    }
    
    public static String standardHP(final String hp) {
        try {
            if (hp == null) {
                return "";
            }
            final StringTokenizer st = new StringTokenizer(hp, "-");
            String output = new String();
            if (st.countTokens() < 3) {
                while (st.hasMoreTokens()) {
                    output = String.valueOf(output) + st.nextToken();
                }
                if (output.length() < 10) {
                    return hp;
                }
                if (output.length() == 10) {
                    return String.valueOf(output.substring(0, 3)) + "-" + output.substring(3, 6) + "-" + output.substring(6, 10);
                }
                if (output.length() == 11) {
                    return String.valueOf(output.substring(0, 3)) + "-" + output.substring(3, 7) + "-" + output.substring(7, 11);
                }
                return hp;
            }
            else {
                if (hp.length() > 11) {
                    return hp;
                }
                return hp;
            }
        }
        catch (Exception e) {
            return hp;
        }
    }
    
    public static String standardTel(final String tel) {
        try {
            if (tel == null || tel.length() == 0) {
                return "";
            }
            final StringTokenizer st = new StringTokenizer(tel, "-");
            String output = new String();
            if (st.countTokens() >= 3) {
                return tel;
            }
            while (st.hasMoreTokens()) {
                output = String.valueOf(output) + st.nextToken();
            }
            if (output.length() < 10) {
                if (output.substring(0, 2).equals("02")) {
                    return String.valueOf(output.substring(0, 2)) + "-" + output.substring(2, 5) + "-" + output.substring(5);
                }
                return tel;
            }
            else if (output.substring(0, 2).equals("02")) {
                if (output.length() == 10) {
                    return String.valueOf(output.substring(0, 2)) + "-" + output.substring(2, 6) + "-" + output.substring(6);
                }
                if (output.length() == 9) {
                    return String.valueOf(output.substring(0, 2)) + "-" + output.substring(2, 5) + "-" + output.substring(5);
                }
                return tel;
            }
            else {
                if (output.length() == 11) {
                    return String.valueOf(output.substring(0, 3)) + "-" + output.substring(3, 7) + "-" + output.substring(7);
                }
                if (output.length() == 10) {
                    return String.valueOf(output.substring(0, 3)) + "-" + output.substring(3, 6) + "-" + output.substring(6);
                }
                return tel;
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return tel;
        }
    }
}
