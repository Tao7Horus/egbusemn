// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.util.Hashtable;
import common.OneRowEntity;
import java.sql.Connection;
import entity.UM_RAE_ConuB010b;
import common.Trx;
import beans.UM_RAV_ConuB010c;
import common.Log;
import common.ComStr;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class ReserveSetProcessMod extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final String saupNo = ComStr.checkNull(req.getParameter("saupNo"), "");
        if (saupNo.equals("")) {
            Log.debug(String.valueOf(this.getClass().getName()) + " saupNo is null");
            return;
        }
        int attach = 0;
        final UM_RAV_ConuB010c ctl = new UM_RAV_ConuB010c();
        UM_RAE_ConuB010b ett = null;
        String transNo = "";
        try {
            ett = ctl.select_user(saupNo);
            if (ett == null) {
                Log.debug(String.valueOf(this.getClass().getName()) + ". 사업자등록번호[" + saupNo + "] 데이타 조회 오류 [ 조회 데이타 없음], UM_RAV_ConuB010c.select_user()");
                return;
            }
            transNo = ett.getTransNo();
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ". 사업자등록번호[" + saupNo + "] 데이타 조회 오류 :" + e.toString());
            return;
        }
        Trx trx = null;
        Connection conn = null;
        OneRowEntity masterEntity = null;
        Hashtable upcheHash = null;
        final DB2TXT1_Rex_Control dtrc = new DB2TXT1_Rex_Control();
        try {
            trx = new Trx(this, "usemn");
            conn = trx.getConnection();
            masterEntity = dtrc.getReceiveMaster(saupNo, conn);
            if (!masterEntity.DataExist) {
                throw new Exception("사업자등록번호[" + saupNo + "]에 해당하는 데이타를 조회할 수 없습니다.");
            }
            upcheHash = dtrc.getCheckItem(saupNo, transNo, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " saupNo[" + saupNo + "]:" + exm.toString());
            return;
        }
        finally {
            if (conn != null) {
                try {
                    trx.close();
                }
                catch (Exception ex) {}
            }
        }
        if (conn != null) {
            try {
                trx.close();
            }
            catch (Exception ex2) {}
        }
        res.setContentType("text/xml;charset=UTF-8");
        final PrintWriter out = res.getWriter();
        try {
            final String lawNo = (String)upcheHash.get("lawNo");
            if (lawNo.equals("Y")) {
                ++attach;
                Log.debug("법인등록번호가 13자리가 아닌경우(법인이지만 법인등록번호 이외의 경우) 서류제출::" + attach + "::");
            }
            final String adminAgree = (String)upcheHash.get("adminAgree");
            if (adminAgree.equals("Y")) {
                ++attach;
                Log.debug("행정정보 공동이용 사전동의서에 동의 하지 않은 경우 서류제출::" + attach + "::");
            }
            final String IngamExist = (String)upcheHash.get("IngamExist");
            final String itemCompareNumber = (String)upcheHash.get("itemCompareNumber");
            final String itemCompareDate = (String)upcheHash.get("itemCompareDate");
            final String itemCompareGovernment = (String)upcheHash.get("itemCompareGovernment");
            final String itemMinusAddCntCompare = (String)upcheHash.get("itemMinusAddCntCompare");
            if (Integer.parseInt(itemCompareNumber) <= 0 && Integer.parseInt(itemCompareDate) <= 0 && Integer.parseInt(itemCompareGovernment) <= 0) {
                if (Integer.parseInt(itemMinusAddCntCompare) > 0) {
                    ++attach;
                    Log.debug("형식승인서 변경::" + attach + "::");
                }
            }
            else {
                ++attach;
                Log.debug("형식승인서 추가::" + attach + "::");
            }
            final String receiveLicenseCnt = (String)upcheHash.get("receiveLicenseCnt");
            final String licenseCnt = (String)upcheHash.get("licenseCnt");
            final String receiveYongLicense = (String)upcheHash.get("receiveYongLicense");
            final String licenseMinusreceiveLicenseCnt = (String)upcheHash.get("licenseMinusreceiveLicenseCnt");
            final String licenseDueCnt = (String)upcheHash.get("licenseDueCnt");
            final String licenseDutMinusCnt = (String)upcheHash.get("licenseDutMinusCnt");
            final String licenseYongCnt = (String)upcheHash.get("licenseYongCnt");
            final String licenseYongMinusCnt = (String)upcheHash.get("licenseYongMinusCnt");
            final String proxyMinusCnt = (String)upcheHash.get("proxyMinusCnt");
            if (Integer.parseInt(receiveLicenseCnt) - Integer.parseInt(licenseCnt) == 0) {
                if ((Integer.parseInt(licenseMinusreceiveLicenseCnt) != 0 || Integer.parseInt(licenseDutMinusCnt) > 0) && (Integer.parseInt(receiveLicenseCnt) == 0 || Integer.parseInt(receiveLicenseCnt) != Integer.parseInt(receiveYongLicense))) {
                    ++attach;
                    Log.debug("등록수첩 또는 등록증 변경::" + attach + "::");
                }
            }
            else if (Integer.parseInt(receiveLicenseCnt) - Integer.parseInt(licenseCnt) < 0) {
                if ((Integer.parseInt(licenseMinusreceiveLicenseCnt) != 0 || Integer.parseInt(licenseDutMinusCnt) != 0) && (Integer.parseInt(receiveLicenseCnt) == 0 || Integer.parseInt(receiveLicenseCnt) != Integer.parseInt(receiveYongLicense))) {
                    ++attach;
                    Log.debug("등록수첩 또는 등록증 삭제변경::" + attach + "::");
                }
            }
            else if (Integer.parseInt(receiveLicenseCnt) == 0 || Integer.parseInt(licenseYongCnt) > Integer.parseInt(receiveYongLicense) || Integer.parseInt(licenseYongCnt) == 0 || Integer.parseInt(licenseYongCnt) < Integer.parseInt(receiveLicenseCnt) - Integer.parseInt(licenseCnt)) {
                ++attach;
                Log.debug("등록수첩 또는 등록증 추가::" + attach + "::");
            }
            if (Integer.parseInt(licenseYongCnt) == 0) {
                if (Integer.parseInt(licenseYongMinusCnt) != 0) {
                    Log.debug("용역면허(기타자유업종) 변경 서류제출없음::" + attach + "::");
                }
            }
            else {
                Log.debug("용역면허(기타자유업종) 추가 서류제출없음::" + attach + "::");
            }
            if (Integer.parseInt(proxyMinusCnt) != 0) {
                ++attach;
                Log.debug("입찰대리인 변경::" + attach + "::");
            }
            if (attach == 0) {
                Log.debug("최종 변경처리::" + attach + "::");
                this.ReserveSetProcApproval(transNo);
                out.println(new StringBuffer().append(attach).toString());
                return;
            }
            out.println(new StringBuffer().append(attach).toString());
        }
        catch (Exception exm2) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".saupNo[" + saupNo + "],transNo[" + transNo + "]:" + exm2.toString());
            out.println("error");
        }
    }
    
    private void ReserveSetProc(final String transNo, final String itemTotalCntCompare, final String itemMinusCntCompare) throws Exception {
        final String Churi = "Hãy gửi kèm tài liệu chứng minh trực tiếp hoặc bằng đường bưu điện. (trong vòng 20 ngày sẽ từ chối nếu không trình nộp).";
        Trx tr = null;
        Connection conn = null;
        final ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer(1024);
        final int count = 0;
        try {
            tr = new Trx(this, "usemn");
            conn = tr.getConnection();
            sb.append("\n    UPDATE UM_EDOC_STATE SET\t    ");
            if (Integer.parseInt(itemTotalCntCompare) > 0 || Integer.parseInt(itemMinusCntCompare) > 0) {
                sb.append("\n    PROCESS_ST='0', PROCESS_RSON= ?,\t\t\t");
            }
            else {
                sb.append("\n    PROCESS_ST='2', PROCESS_RSON= ?,\t\t\t");
            }
            sb.append("\n    PROCESS_DT = SYSDATE\t\t\t\t\t");
            sb.append("\n    WHERE TRANS_NO = ?\t\t\t\t\t");
            sb.append("\n    AND MOD_CLS = '2'\t\t\t");
            psmt = conn.prepareStatement(sb.toString());
            psmt.setString(1, Churi);
            psmt.setString(2, transNo);
            if (psmt.executeUpdate() != 1) {
                Log.debug("TRANS_NO[" + transNo + "]변경신청 보류(PROCESS_ST:2) Update되지 않았습니다.");
            }
            else {
                Log.debug("TRANS_NO[" + transNo + "]변경신청 보류(PROCESS_ST:2) Update 되었습니다.");
            }
        }
        catch (Exception exf) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".ReserveSetProc() transNo[" + transNo + "]:" + exf.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (Exception ex2) {}
            }
            if (conn != null) {
                try {
                    tr.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex4) {}
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (conn != null) {
            try {
                tr.close();
            }
            catch (Exception ex6) {}
        }
    }
    
    private void ReserveSetProcApproval(final String transNo) throws Exception {
        Trx tr = null;
        Connection conn = null;
        final ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer(1024);
        final int count = 0;
        try {
            tr = new Trx(this, "usemn");
            conn = tr.getConnection();
            sb.append("\n    UPDATE UM_EDOC_STATE SET\t        ");
            sb.append("\n    PROCESS_DT = SYSDATE, PERMIT_BRANCH = '91' \t");
            sb.append("\n    WHERE TRANS_NO = ?\t\t\t\t\t");
            sb.append("\n    AND MOD_CLS = '2'\t\t\t");
            psmt = conn.prepareStatement(sb.toString());
            psmt.setString(1, transNo);
            if (psmt.executeUpdate() != 1) {
                Log.debug("TRANS_NO[" + transNo + "]변경신청 보류(PROCESS_ST:2) Update되지 않았습니다.");
            }
            else {
                Log.debug("TRANS_NO[" + transNo + "]변경신청 보류(PROCESS_ST:2) Update 되었습니다.");
            }
        }
        catch (Exception exf) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".ReserveSetProc() transNo[" + transNo + "]:" + exf.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (Exception ex2) {}
            }
            if (conn != null) {
                try {
                    tr.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex4) {}
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (conn != null) {
            try {
                tr.close();
            }
            catch (Exception ex6) {}
        }
    }
}
