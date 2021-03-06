// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.util.Hashtable;
import common.OneRowEntity;
import java.sql.Connection;
import common.util.CommUtil;
import common.Trx;
import common.Log;
import common.ComStr;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class DB2TXT1_rex extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final String saupNo = ComStr.checkNull(req.getParameter("saupNo"), "");
        final String transNo = ComStr.checkNull(req.getParameter("transNo"), "");
        if (saupNo.equals("")) {
            Log.debug(String.valueOf(this.getClass().getName()) + " saupNo is null");
            return;
        }
        if (transNo.equals("")) {
            Log.debug(String.valueOf(this.getClass().getName()) + " transNo is null");
            return;
        }
        Trx trx = null;
        Connection conn = null;
        OneRowEntity masterEntity = null;
        final Hashtable upcheHash = null;
        final DB2TXT1_Rex_Control dtrc = new DB2TXT1_Rex_Control();
        try {
            trx = new Trx(this, "usemn");
            conn = trx.getConnection();
            masterEntity = dtrc.getReceiveMaster(saupNo, conn);
            final boolean dataExist = masterEntity.DataExist;
        }
        catch (Exception ex) {
            return;
        }
        finally {
            if (conn != null) {
                try {
                    trx.close();
                }
                catch (Exception ex2) {}
            }
        }
        if (conn != null) {
            try {
                trx.close();
            }
            catch (Exception ex3) {}
        }
        res.setContentType("text/xml;charset=UTF-8");
        final PrintWriter out = res.getWriter();
        String fileWrite = null;
        final int attach = 0;
        try {
            fileWrite = "<?xml version='1.0' encoding='UTF-8'?>";
            fileWrite = String.valueOf(fileWrite) + "<main>";
            fileWrite = String.valueOf(fileWrite) + "<row>";
            fileWrite = String.valueOf(fileWrite) + "<?????????><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("COM_NM")) + "]]></?????????>" + "<????????????><![CDATA[" + ComStr.divideComma(ComStr.checkNull(masterEntity.getDataFromName("ZIP_CD")), "-", 3) + "]]></????????????>" + "<??????><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("ADDR")) + "]]></??????>" + "<????????????><![CDATA[" + " " + "]]></????????????>" + "<?????????????????????><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("PHONE_NO")) + "]]></?????????????????????>" + "<?????????????????????><![CDATA[" + " " + "]]></?????????????????????>" + "<????????????><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("DOC_NO")) + "]]></????????????>" + "<??????><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("Title")) + "]]></??????>" + "<????????????><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("CD_NM")) + "]]></????????????>" + "<????????????><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("REG_DT")) + "]]></????????????>" + "<?????????????????????><![CDATA[" + CommUtil.insert_minus_saupno(masterEntity.getDataFromName("BIZ_REG_NO")) + "]]></?????????????????????>" + "<????????????><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("REPR_NM")) + "]]></????????????>";
            fileWrite = String.valueOf(fileWrite) + "</row>";
            fileWrite = String.valueOf(fileWrite) + "</main>";
            out.println(fileWrite);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".saupNo[" + saupNo + "]:" + exm.toString());
        }
    }
}
