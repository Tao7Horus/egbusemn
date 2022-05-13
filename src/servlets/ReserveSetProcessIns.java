// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class ReserveSetProcessIns extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final String saupNo = ComStr.checkNull(req.getParameter("saupNo"), "");
        final String Churi = "증빙서류를 첨부하여 방문 또는 우편송부하여 주시기 바랍니다 (20일 이내에 미제출시는 반려처리).";
        if (saupNo.equals("")) {
            Log.debug(String.valueOf(this.getClass().getName()) + " saupNo is null");
            return;
        }
        final UM_RAV_ConuB010c ctl = new UM_RAV_ConuB010c();
        UM_RAE_ConuB010b ett = null;
        String transNo = "";
        try {
            ett = ctl.select_user(saupNo);
            if (ett == null) {
                Log.debug("사업자등록번호[" + saupNo + "]으로 신청일자 max값으로 사용_전자문서상태.전송번호를 찾지 못하였습니다.");
                return;
            }
            transNo = ett.getTransNo();
        }
        catch (Exception e) {
            Log.errors("신규 사용_전자문서상태.전송번호 찾기error발생 :" + e.toString());
        }
        Trx trx = null;
        Connection conn = null;
        OneRowEntity masterEntity = null;
        final DB2TXT_Control dtc = new DB2TXT_Control();
        String saupjaNumber = null;
        Hashtable upcheHash = null;
        int attach = 0;
        try {
            trx = new Trx(this, "usemn");
            conn = trx.getConnection();
            masterEntity = dtc.getReceiveMaster(transNo, conn);
            if (!masterEntity.DataExist) {
                throw new Exception("전송번호[" + transNo + "]에 해당하는 데이타를 조회할 수 없습니다.");
            }
            saupjaNumber = masterEntity.getDataFromName("사업자등록번호");
            upcheHash = dtc.getCheckItem(saupjaNumber, transNo, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " transNo[" + transNo + "]:" + exm.toString());
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
            final String makeitem = (String)upcheHash.get("makeitem");
            if (makeitem.equals("Y")) {
                ++attach;
                Log.debug("제조물품 등록하였을 경우 서류제출::" + attach + "::");
                ++attach;
                Log.debug("제조물품 등록하였을 경우 직접생산증명서, 인감날인::" + attach + "::");
            }
            final String approveNumber = (String)upcheHash.get("approveNumber");
            if (approveNumber.equals("Y")) {
                ++attach;
                Log.debug("형식승인 증명서류사본::" + attach + "::");
            }
            final String totalLicense = (String)upcheHash.get("totalLicense");
            final String bidsseLicense = (String)upcheHash.get("bidsseLicense");
            if (Integer.parseInt(totalLicense) > 0 && !totalLicense.equals(bidsseLicense)) {
                ++attach;
                Log.debug("접수된 면허가 있을때::" + attach + "::");
            }
            final String isNewCompany = ComStr.checkNull(masterEntity.getDataFromName("기업구분2"));
            if (isNewCompany.equals("1")) {
                ++attach;
                Log.debug("창업/벤쳐 확인::" + attach + "::");
            }
            if (!bidsseLicense.equals("0")) {
                Log.debug("용역면허(기타자유업종) 서류제출없음::" + attach + "::");
            }
            final String bidvice = (String)upcheHash.get("bidvice");
            if (bidvice.equals("Y")) {
                ++attach;
                Log.debug("입찰대리인 확인::" + attach + "::");
            }
            if (attach == 0) {
                Log.debug("대기인경우 승인지청을 콜센타로 변경처리함 ::" + attach + "::");
                this.ReserveSetProcApproval(transNo);
            }
            else {
                ++attach;
                Log.debug("인감증명서 확인::" + attach + "::");
                this.ReserveSetProc(transNo, Churi, makeitem);
            }
            out.println(attach);
        }
        catch (Exception exm2) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".transNo[" + transNo + "]:" + exm2.toString());
        }
    }
    
    public void ReserveSetProc(final String transNo, final String Churi, final String makeitem) {
        final ResultSet rs = null;
        PreparedStatement psmt = null;
        Trx trx = null;
        Connection conn = null;
        final StringBuffer sb = new StringBuffer(1024);
        final int count = 0;
        try {
            sb.append("\n    UPDATE 사용_전자문서상태 SET\t");
            if (makeitem.equals("Y")) {
                sb.append("\n    진행상태='0', 처리사유= ?,\t\t\t");
            }
            else {
                sb.append("\n    진행상태='2', 처리사유= ?,\t\t\t");
            }
            sb.append("\n    처리일자 = SYSDATE\t\t\t\t\t");
            sb.append("\n    WHERE 전송번호 = ?\t\t\t\t\t    ");
            sb.append("\n    AND 신청_변경구분 = '1'\t\t\t    ");
            trx = new Trx(this, "usemn");
            conn = trx.getConnection();
            psmt = conn.prepareStatement(sb.toString());
            psmt.setString(1, Churi);
            psmt.setString(2, transNo);
            psmt.executeUpdate();
            if (psmt.executeUpdate() != 1) {
                Log.debug("전송번호[" + transNo + "]신청으로 보류(진행상태:2) Update되지 않았습니다.");
            }
            Log.debug("전송번호[" + transNo + "]신청으로 보류(진행상태:2) Update 되었습니다.");
        }
        catch (SQLException sqle) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".ReserveSetProc():" + sqle.toString());
        }
        catch (Exception exf) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".ReserveSetProc():" + exf.toString());
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
                    trx.close();
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
                trx.close();
            }
            catch (Exception ex6) {}
        }
    }
    
    public void ReserveSetProcApproval(final String transNo) {
        final ResultSet rs = null;
        PreparedStatement psmt = null;
        Trx trx = null;
        Connection conn = null;
        final StringBuffer sb = new StringBuffer(1024);
        final int count = 0;
        try {
            sb.append("\n    UPDATE 사용_전자문서상태 SET\t\t\t\t");
            sb.append("\n    처리일자 = SYSDATE, 승인지청 = '91'\t\t\t");
            sb.append("\n    WHERE 전송번호 = ?\t\t\t\t\t\t\t\t\t");
            sb.append("\n    AND 신청_변경구분 = '1'\t\t\t\t\t\t\t");
            trx = new Trx(this, "usemn");
            conn = trx.getConnection();
            psmt = conn.prepareStatement(sb.toString());
            psmt.setString(1, transNo);
            psmt.executeUpdate();
            if (psmt.executeUpdate() != 1) {
                Log.debug("전송번호[" + transNo + "]신청으로 보류(진행상태:2) Update되지 않았습니다.");
            }
            Log.debug("전송번호[" + transNo + "]신청으로 보류(진행상태:2) Update 되었습니다.");
        }
        catch (SQLException sqle) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".ReserveSetProc():" + sqle.toString());
        }
        catch (Exception exf) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".ReserveSetProc():" + exf.toString());
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
                    trx.close();
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
                trx.close();
            }
            catch (Exception ex6) {}
        }
    }
}
