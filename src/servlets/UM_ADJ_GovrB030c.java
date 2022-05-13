// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.Connection;
import common.Trx;
import common.util.CommonMessage;
import entity.UM_ADJ_GovuA020b;
import entity.UM_RAE_GovuA010b;
import common.Log;
import common.util.CommUtil;
import beans.UM_URB_CERT010;
import beans.UM_ADB_GovrA010c;
import beans.UM_ADB_GovrB030c;
import beans.UM_RAB_GovuA010p;
import javax.servlet.http.HttpServletResponse;
import Mail.MailServer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADJ_GovrB030c extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        final UM_ADB_GovrB030c uagb030 = new UM_ADB_GovrB030c();
        final UM_URB_CERT010 urc010 = new UM_URB_CERT010();
        final CommUtil cu = new CommUtil();
        final String flag = CommUtil.retSpace(req.getParameter("flag"));
        String license = CommUtil.retSpace(req.getParameter("license"));
        final String coquan = CommUtil.retSpace(req.getParameter("coquan"));
        Log.debug("------------------>>>>> UM_ADJ_GovrB030c coquan:" + coquan);
        if ("".equals(license)) {
            license = "N";
        }
        if ("Cookie".equals(flag)) {
            try {
                res.sendRedirect("http://muasamcong.mpi.gov.vn:8070/RA/UM_RAJ_GovuC011l.jsp?ApprovalCode=" + uagb030.getApprovalCode(CommUtil.retSpace(req.getParameter("ID"))));
            }
            catch (Exception ef) {
                CommonMessage.printMsg(null, "", ef.getMessage(), null, res);
            }
            return;
        }
        Trx trx = null;
        Connection con = null;
        final String g2bCode = cu.retNull(req.getParameter("g2bCode"));
        String instituCode = cu.retNull(req.getParameter("instituCode"));
        String saupNo = cu.retNull(req.getParameter("saupNo"));
        String mathCode = cu.retNull(req.getParameter("mathCode"));
        final String groupNo = cu.retNull(req.getParameter("groupNo"));
        final String departmentNo = cu.retNull(req.getParameter("departmentNo"));
        final String choiceCA = CommUtil.retSpace(req.getParameter("choiceCA"));
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            con.setAutoCommit(false);
            if (instituCode != null) {
                instituCode = uagb030.getInstituNumber(con);
                if ("Z".equals(instituCode)) {
                    instituCode = "Z000000";
                }
            }
            else {
                instituCode = g2bCode;
            }
            if (mathCode == null) {
                if (instituCode.indexOf("Z") != -1) {
                    mathCode = String.valueOf(instituCode.substring(1)) + "0000000";
                }
                else {
                    mathCode = String.valueOf(instituCode) + "000000";
                }
            }
            if (saupNo == null) {
                saupNo = mathCode;
            }
            final String recept = uagb030.getChodalReceptionNumber(con);
            final String maxreceived = urc010.getMaxReceptionID(con);
            final String approvedCode = String.valueOf(maxreceived.substring(2, maxreceived.length())) + req.getParameter("USRID");
            if (coquan != null && coquan.equals("1")) {
                uagb030.insertGigwanMasterCoQuan(instituCode, saupNo, recept, license, approvedCode, req, con, coquan);
            }
            else {
                uagb030.insertGigwanMaster(instituCode, saupNo, recept, license, approvedCode, req, con);
            }
            uagb030.insertGigwanCreditTable(recept, mathCode, req, con);
            uagb030.insertGigwanSertTable(recept, saupNo, approvedCode, instituCode, req, con);
            con.commit();
            con.setAutoCommit(true);
 //=====================================================================
            UM_RAB_GovuA010p ctl1=new UM_RAB_GovuA010p();
            UM_RAE_GovuA010b ettcode = ctl1.select_goma(recept, groupNo, departmentNo);
            UM_ADB_GovrA010c lic=new UM_ADB_GovrA010c();
            UM_ADJ_GovuA020b ettLicense = lic.select_GovernmentList_Confirm(recept); // Lay ma phe duyet dang ky
            
            
            String toEmail=CommUtil.retSpace(req.getParameter("masterMail"));
            
            String maPheDuyet=ettLicense.getdependCode();
            String soDKKD=ettcode.getsaupNo();
            String maCQ=ettcode.getg2bCode();
            String reg_name = ettcode.gettaskmaster();
            String reg_company = ettcode.getgoNameFull();
            //String subject="Thông báo hoàn thành đăng ký thông tin Bên Mời Thầu";
           // String body="Mã phê duyệt: "+maPheDuyet+" \r\n"+"Số ĐKKD: "+soDKKD+" \r\n"+"Mã kinh doanh: "+maKinhDoanh;
    		
            
            MailServer mail=new MailServer();
    		mail.sendRegistrationEmailToMoiThauStep1(toEmail, reg_name, reg_company,maCQ,maPheDuyet,soDKKD);
          //=====================================================================
                res.sendRedirect("http://muasamcong.mpi.gov.vn:8070/RA/UM_RAJ_GovuA020s.jsp?code=" + recept + "&recept1=" + approvedCode + "&g2bCode=" + g2bCode + "&departmentNo=" + departmentNo + "&groupNo=" + groupNo + "&choiceCA=" + choiceCA + "&coquan=" + coquan);
        }
        catch (Exception e2) {
            try {
                if (con != null) {
                    con.rollback();
                }
            }
            catch (Exception e1) {
                CommonMessage.printMsg(null, "", "Roleback " + e1.getMessage(), null, res);
            }
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                }
            }
            catch (Exception e1) {
                CommonMessage.printMsg(null, "", "AutoCommit " + e1.getMessage(), null, res);
            }
            Log.debug("UM_ADJ_GovrB030c : " + e2.toString());
            CommonMessage.printMsg(null, "", " " + e2.getMessage(), null, res);
            try {
                if (con != null) {
                    trx.close();
                }
            }
            catch (Exception ex) {
                CommonMessage.printMsg(null, "", "Try all " + ex.getMessage(), null, res);
            }
            return;
        }
        finally {
            try {
                if (con != null) {
                    trx.close();
                }
            }
            catch (Exception ex) {
                CommonMessage.printMsg(null, "", "Try all " + ex.getMessage(), null, res);
            }
        }
        try {
            if (con != null) {
                trx.close();
            }
        }
        catch (Exception ex) {
            CommonMessage.printMsg(null, "", "Try all " + ex.getMessage(), null, res);
        }
    }
}
