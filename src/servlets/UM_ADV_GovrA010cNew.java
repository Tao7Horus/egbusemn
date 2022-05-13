// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.Connection;
import common.util.CommonMessage;
import common.Log;
import common.Trx;
import secu.lib.MessageDigest;
import secu.lib.Secu;
import beans.UM_URB_CERT010;
import javax.servlet.ServletRequest;
import com.oreilly.servlet.ParameterParser;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADV_GovrA010cNew extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        Trx trx = null;
        Connection con = null;
        final ParameterParser psr = new ParameterParser((ServletRequest)req);
        final UM_URB_CERT010 urc010 = new UM_URB_CERT010();
        final String G2BCODE = psr.getStringParameter("G2BCODE", "");
        final String IDENT = psr.getStringParameter("IDENT", "");
        final String KNAME = psr.getStringParameter("KNAME", "");
        final String IDENTJUMIN = psr.getStringParameter("IDENTJUMIN", "");
        final String ZIPNO = psr.getStringParameter("ZIPNO", "");
        final String COMNO = psr.getStringParameter("COMNO", "");
        final String ADDRS = psr.getStringParameter("ADDRS", "");
        final String ADDRESS2 = psr.getStringParameter("ADDRESS2", "");
        final String FAX = psr.getStringParameter("FAX", "");
        final String MYNAME = psr.getStringParameter("MYNAME", "");
        final String OFFICEDE = psr.getStringParameter("OFFICEDE", "");
        final String JUMIN = psr.getStringParameter("JUMIN", "");
        final String TEL = psr.getStringParameter("TEL", "");
        final String USRID = psr.getStringParameter("USRID", "");
        final String CERT_ORG = psr.getStringParameter("CERT_ORG", "");
        final String HOME = psr.getStringParameter("HOME", "");
        final String recept = psr.getStringParameter("recept", "");
        final String back = psr.getStringParameter("back", "");
        final String dependCode = psr.getStringParameter("dependCode", "");
        final String flag = psr.getStringParameter("flag", "");
        final String approval = psr.getStringParameter("approval", "");
        final String branchOffi1 = psr.getStringParameter("branchOffi1", "");
        final String passwdmodOK = psr.getStringParameter("passwdmodOK", "");
        final String gID = psr.getStringParameter("gID", "");
        final String i07 = psr.getStringParameter("i07", "");
        String i8 = "";
        final String dn = psr.getStringParameter("dn", "");
        final String refername = psr.getStringParameter("refername", "");
        final String refercode = psr.getStringParameter("refercode", "");
        final String comNo = psr.getStringParameter("comNo", "");
        final String g2bCode = psr.getStringParameter("g2bCode", "");
        final Secu secu = new Secu(1);
        final MessageDigest md = new MessageDigest(secu);
        if ("Y".equals(passwdmodOK)) {
            i8 = md.create(i07);
        }
        if ("DNSave".equals(flag)) {
            try {
                trx = new Trx(this);
                con = trx.getConnection();
                con.setAutoCommit(false);
                if (refername != "" && refercode != "") {
                    urc010.updateReceptionCertNew(dn, refername, refercode, comNo, g2bCode, dependCode, con, recept);
                    con.commit();
                    con.setAutoCommit(true);
                    res.sendRedirect("http://muasamcong.mpi.gov.vn:8070/AD/UM_ADJ_GovrL025sNew.jsp?g2bCode=" + g2bCode + "&dependCode=" + refercode + "&saupNo=" + comNo);
                }
            }
            catch (Exception exm) {
                try {
                    if (con != null) {
                        con.rollback();
                    }
                }
                catch (Exception ex) {}
                try {
                    if (con != null) {
                        con.setAutoCommit(true);
                    }
                }
                catch (Exception ex2) {}
                Log.debug("UM_ADV_GovrA010c.java flag[" + flag + "] : " + exm.toString());
                CommonMessage.printMsg("", "4", exm.getMessage(), "", res);
            }
            finally {
                try {
                    if (con != null) {
                        trx.close();
                    }
                }
                catch (Exception ex3) {}
            }
            try {
                if (con != null) {
                    trx.close();
                }
            }
            catch (Exception ex4) {}
        }
    }
}
