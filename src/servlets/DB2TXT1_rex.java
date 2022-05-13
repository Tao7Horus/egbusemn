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
            fileWrite = String.valueOf(fileWrite) + "<상호명><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("COM_NM")) + "]]></상호명>" + "<우편번호><![CDATA[" + ComStr.divideComma(ComStr.checkNull(masterEntity.getDataFromName("ZIP_CD")), "-", 3) + "]]></우편번호>" + "<주소><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("ADDR")) + "]]></주소>" + "<담당자명><![CDATA[" + " " + "]]></담당자명>" + "<담당자전화번호><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("PHONE_NO")) + "]]></담당자전화번호>" + "<담당자팩스번호><![CDATA[" + " " + "]]></담당자팩스번호>" + "<문서번호><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("DOC_NO")) + "]]></문서번호>" + "<제목><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("Title")) + "]]></제목>" + "<승인지청><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("CD_NM")) + "]]></승인지청>" + "<신청일자><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("REG_DT")) + "]]></신청일자>" + "<사업자등록번호><![CDATA[" + CommUtil.insert_minus_saupno(masterEntity.getDataFromName("BIZ_REG_NO")) + "]]></사업자등록번호>" + "<대표자명><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("REPR_NM")) + "]]></대표자명>";
            fileWrite = String.valueOf(fileWrite) + "</row>";
            fileWrite = String.valueOf(fileWrite) + "</main>";
            out.println(fileWrite);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".saupNo[" + saupNo + "]:" + exm.toString());
        }
    }
}
