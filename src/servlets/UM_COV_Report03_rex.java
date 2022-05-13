// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.sql.Connection;
import java.io.PrintWriter;
import common.Log;
import common.Trx;
import common.CommEntity;
import java.io.IOException;
import javax.servlet.ServletException;
import common.ComStr;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_COV_Report03_rex extends HttpServlet
{
    String fileWrite;
    
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/xml;charset=euc-kr");
        final String last_Gubun = ComStr.checkNull(req.getParameter("Last_Gubun"));
        if (last_Gubun.equals("신청승인리스트")) {
            this.processRegister(req, res);
            return;
        }
        if (last_Gubun.equals("변경승인리스트")) {
            this.processModify(req, res);
        }
    }
    
    public void processRegister(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final PrintWriter out = res.getWriter();
        CommEntity[] req_regEntity = (CommEntity[])null;
        final UM_COV_Report03_Control r03C = new UM_COV_Report03_Control();
        final String saupNo = ComStr.checkNull(req.getParameter("saupNo"));
        final String start = ComStr.checkNull(req.getParameter("start"));
        final String end = ComStr.checkNull(req.getParameter("end"));
        final String fileName = ComStr.checkNull(req.getParameter("fileName"));
        final String ID = ComStr.checkNull(req.getParameter("ID"));
        final String startTime = String.valueOf(start) + "00:00:00";
        final String endTime = String.valueOf(end) + "23:59:59";
        final String mulpum = "";
        final String SaupJaNo = "";
        Trx trx = null;
        Connection conn = null;
        try {
            trx = new Trx(this, "usemn");
            conn = trx.getConnection();
            this.fileWrite = "<?xml version='1.0' encoding='EUC-KR'?>";
            this.fileWrite = String.valueOf(this.fileWrite) + "<main>";
            this.fileWrite = String.valueOf(this.fileWrite) + "<row>";
            this.fileWrite = String.valueOf(this.fileWrite) + "<시작일자><![CDATA[" + start + "]]></시작일자>" + "<종료일자><![CDATA[" + end + "]]></종료일자>";
            req_regEntity = r03C.getRequestList(startTime, endTime, ID, saupNo, conn);
            this.fileWrite = String.valueOf(this.fileWrite) + "<sub1>";
            for (int i = 0, n = req_regEntity.length; i < n; ++i) {
                this.fileWrite = String.valueOf(this.fileWrite) + "<row><N><![CDATA[" + ComStr.checkNull(req_regEntity[i].data[0]) + "]]></N>" + "<상호명><![CDATA[" + ComStr.checkNull(req_regEntity[i].data[1]) + "]]></상호명>" + "<처리일자><![CDATA[" + ComStr.checkNull(req_regEntity[i].data[2]) + "]]></처리일자>" + "<사업자등록번호><![CDATA[" + ComStr.checkNull(req_regEntity[i].data[3]) + "]]></사업자등록번호>" + "<대표자명><![CDATA[" + ComStr.checkNull(req_regEntity[i].data[4]) + "]]></대표자명>" + "<전화번호><![CDATA[" + ComStr.checkNull(req_regEntity[i].data[5]) + "]]></전화번호>" + "<MAIL><![CDATA[" + ComStr.checkNull(req_regEntity[i].data[6]) + "]]></MAIL>" + "<업무구분명><![CDATA[" + ComStr.checkNull(req_regEntity[i].data[7]) + "]]></업무구분명>" + "</row>";
            }
            this.fileWrite = String.valueOf(this.fileWrite) + "</sub1>";
            this.fileWrite = String.valueOf(this.fileWrite) + "</row>";
            this.fileWrite = String.valueOf(this.fileWrite) + "</main>";
        }
        catch (Exception ex) {
            Log.debug(String.valueOf(this.getClass().getName()) + "saupNo [" + saupNo + "]" + ex.toString());
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (Exception ex2) {}
            }
            if (trx != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (conn != null) {
            try {
                conn.close();
            }
            catch (Exception ex4) {}
        }
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex5) {}
        }
        out.println(this.fileWrite);
    }
    
    public void processModify(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final PrintWriter out = res.getWriter();
        CommEntity[] modi_regEntity = (CommEntity[])null;
        final UM_COV_Report03_Control report3Control = new UM_COV_Report03_Control();
        final String saupNo = ComStr.checkNull(req.getParameter("saupNo"));
        final String start = ComStr.checkNull(req.getParameter("start"));
        final String end = ComStr.checkNull(req.getParameter("end"));
        final String fileName = ComStr.checkNull(req.getParameter("fileName"));
        final String ID = ComStr.checkNull(req.getParameter("ID"));
        final String startTime = String.valueOf(start) + "00:00:00";
        final String endTime = String.valueOf(end) + "23:59:59";
        final String tmp_SaupNo = null;
        final int Count = 0;
        Trx trx = null;
        Connection conn = null;
        try {
            trx = new Trx(this, "usemn");
            conn = trx.getConnection();
            modi_regEntity = report3Control.getModifyList(startTime, endTime, ID, saupNo, conn);
            this.fileWrite = "<?xml version='1.0' encoding='EUC-KR'?>";
            this.fileWrite = String.valueOf(this.fileWrite) + "<main>";
            this.fileWrite = String.valueOf(this.fileWrite) + "<row>";
            this.fileWrite = String.valueOf(this.fileWrite) + "<시작일자><![CDATA[" + start + "]]></시작일자>" + "<종료일자><![CDATA[" + end + "]]></종료일자>";
            this.fileWrite = String.valueOf(this.fileWrite) + "<sub1>";
            for (int i = 0, n = modi_regEntity.length; i < n; ++i) {
                this.fileWrite = String.valueOf(this.fileWrite) + "<row><N><![CDATA[" + ComStr.checkNull(modi_regEntity[i].data[0]) + "]]></N>" + "<상호명><![CDATA[" + ComStr.checkNull(modi_regEntity[i].data[1]) + "]]></상호명>" + "<처리일자><![CDATA[" + ComStr.checkNull(modi_regEntity[i].data[2]) + "]]></처리일자>" + "<사업자등록번호><![CDATA[" + ComStr.checkNull(modi_regEntity[i].data[3]) + "]]></사업자등록번호>" + "<대표자명><![CDATA[" + ComStr.checkNull(modi_regEntity[i].data[4]) + "]]></대표자명>" + "<전화번호><![CDATA[" + ComStr.checkNull(modi_regEntity[i].data[5]) + "]]></전화번호>" + "<MAIL><![CDATA[" + ComStr.checkNull(modi_regEntity[i].data[6]) + "]]></MAIL>" + "<업무구분명><![CDATA[" + ComStr.checkNull(modi_regEntity[i].data[7]) + "]]></업무구분명>" + "</row>";
            }
            this.fileWrite = String.valueOf(this.fileWrite) + "</sub1>";
            this.fileWrite = String.valueOf(this.fileWrite) + "</row>";
            this.fileWrite = String.valueOf(this.fileWrite) + "</main>";
        }
        catch (Exception ex) {
            Log.debug(String.valueOf(this.getClass().getName()) + "saupNo [" + saupNo + "]" + ex.toString());
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (Exception ex2) {}
            }
            if (trx != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (conn != null) {
            try {
                conn.close();
            }
            catch (Exception ex4) {}
        }
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex5) {}
        }
        out.println(this.fileWrite);
    }
}
