// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.sql.Connection;
import java.io.PrintWriter;
import common.Log;
import common.Trx;
import common.OneRowEntity;
import common.CommEntity;
import java.io.IOException;
import javax.servlet.ServletException;
import common.ComStr;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_COV_Report02_rex extends HttpServlet
{
    String fileWrite;
    int Total;
    int Goods;
    int Const;
    int Servi;
    String substr2000;
    
    public UM_COV_Report02_rex() {
        this.substr2000 = "";
    }
    
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/xml;charset=UTF-8");
        final String last_Gubun = ComStr.checkNull(req.getParameter("Last_Gubun"));
        if (last_Gubun.equals("Request_Register")) {
            this.processRegister(req, res);
            return;
        }
        if (last_Gubun.equals("Modify_Register")) {
            this.processModify(req, res);
        }
    }
    
    public void processRegister(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final PrintWriter out = res.getWriter();
        final CommEntity[] req_regEntity = (CommEntity[])null;
        final CommEntity[] pumEntity = (CommEntity[])null;
        final CommEntity[] upjongEntity = (CommEntity[])null;
        final OneRowEntity reqCntEntity = new OneRowEntity();
        final UM_COV_Report02_Control r02C = new UM_COV_Report02_Control();
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
            trx = new Trx(this);
            conn = trx.getConnection();
            this.fileWrite = "<?xml version='1.0' encoding='UTF-8'?>";
            this.fileWrite = String.valueOf(this.fileWrite) + "<main>";
            this.fileWrite = String.valueOf(this.fileWrite) + "<row>";
            this.fileWrite = String.valueOf(this.fileWrite) + "<????????????><![CDATA[" + start + "]]></????????????>" + "<????????????><![CDATA[" + end + "]]></????????????>";
            this.fileWrite = String.valueOf(this.fileWrite) + "<??????><![CDATA[" + reqCntEntity.getDataFromName("??????") + "]]></??????>" + "<??????><![CDATA[" + reqCntEntity.getDataFromName("??????") + "]]></??????>" + "<??????><![CDATA[" + reqCntEntity.getDataFromName("??????") + "]]></??????>" + "<??????><![CDATA[" + reqCntEntity.getDataFromName("??????") + "]]></??????>" + "<???????????????><![CDATA[" + reqCntEntity.getDataFromName("???????????????") + "]]></???????????????>" + "<????????????><![CDATA[" + reqCntEntity.getDataFromName("????????????") + "]]></????????????>";
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
    }
    
    public void processModify(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final PrintWriter out = res.getWriter();
        final CommEntity[] modi_regEntity = (CommEntity[])null;
        final OneRowEntity modi_cntEntity = new OneRowEntity();
        final UM_COV_Report02_Control report2Control = new UM_COV_Report02_Control();
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
            this.fileWrite = "<?xml version='1.0' encoding='UTF-8'?>";
            this.fileWrite = String.valueOf(this.fileWrite) + "<main>";
            this.fileWrite = String.valueOf(this.fileWrite) + "<row>";
            this.fileWrite = String.valueOf(this.fileWrite) + "<????????????><![CDATA[" + start + "]]></????????????>" + "<????????????><![CDATA[" + end + "]]></????????????>";
            this.fileWrite = String.valueOf(this.fileWrite) + "<??????><![CDATA[" + modi_cntEntity.getDataFromName("??????") + "]]></??????>" + "<??????><![CDATA[" + modi_cntEntity.getDataFromName("??????") + "]]></??????>" + "<??????><![CDATA[" + modi_cntEntity.getDataFromName("??????") + "]]></??????>" + "<??????><![CDATA[" + modi_cntEntity.getDataFromName("??????") + "]]></??????>" + "<???????????????><![CDATA[" + modi_cntEntity.getDataFromName("???????????????") + "]]></???????????????>" + "<????????????><![CDATA[" + modi_cntEntity.getDataFromName("????????????") + "]]></????????????>";
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
    }
}
