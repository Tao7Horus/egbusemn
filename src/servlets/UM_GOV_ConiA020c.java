// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import common.ComStr;
import common.Log;
import java.sql.SQLException;
import javax.servlet.ServletRequest;
import com.oreilly.servlet.ParameterParser;
import common.Trx;
import entity.UM_GOE_ConiA030b;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_GOV_ConiA020c extends HttpServlet
{
    public String[] getParams(final HttpServletRequest req, final String param, final String init) throws UnsupportedEncodingException {
        String[] res = (String[])null;
        res = req.getParameterValues(param);
        if (res != null) {
            for (int i = 0; i < res.length; ++i) {
                if (res[i] == null || res[i].equals("null") || res[i].equals("")) {
                    res[i] = init;
                }
                else {
                    res[i] = new String(res[i]);
                }
            }
        }
        return res;
    }
    
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=euc-kr");
        Trx trx = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        Connection con = null;
        PreparedStatement psmt = null;
        PreparedStatement psmt2 = null;
        String sql = null;
        String query1 = null;
        final StringBuffer sb = new StringBuffer();
        final UM_GOE_ConiA030b entity = new UM_GOE_ConiA030b();
        Label_7188: {
            try {
                trx = new Trx(this, "usemn");
                con = trx.getConnection();
                con.setAutoCommit(false);
                final ParameterParser psr = new ParameterParser((ServletRequest)req);
                final String flag = psr.getStringParameter("flag", "");
                final String docNo = psr.getStringParameter("docNo", "");
                final String kigwanCode = psr.getStringParameter("kigwanCode", "");
                final String kigwanNm = psr.getStringParameter("kigwanNm", "");
                final String kigwanDaepyoNm = psr.getStringParameter("kigwanDaepyoNm", "");
                final String chargeNm = psr.getStringParameter("chargeNm", "");
                final String chargeTel = psr.getStringParameter("chargeTel", "");
                final String chargeDepart = psr.getStringParameter("chargeDepart", "");
                final String from = psr.getStringParameter("from", "");
                final String to = psr.getStringParameter("to", "");
                final String consult = psr.getStringParameter("consult", "");
                final String unpairNo = psr.getStringParameter("unpairNo", "");
                final String today = psr.getStringParameter("today", "");
                final String unpairGubun = psr.getStringParameter("unpairGubun", "");
                final String compactNo = psr.getStringParameter("compactNo", "");
                final String compactNoDate = psr.getStringParameter("compactNoDate", "");
                final String notifyKind = psr.getStringParameter("notifyKind", "");
                final String compactNoDiffer = psr.getStringParameter("compactNoDiffer", "");
                final String notifyNm = psr.getStringParameter("notifyNm", "");
                final String notifyNo = psr.getStringParameter("notifyNo", "");
                final String notifyDiffer = psr.getStringParameter("notifyDiffer", "");
                final String notifyDate = psr.getStringParameter("notifyDate", "");
                final String lawNo = psr.getStringParameter("lawNo", "");
                final String upcheNm = psr.getStringParameter("upcheNm", "");
                final String bonsapostNo = psr.getStringParameter("bonsapostNo", "");
                final String bonsaAddr = psr.getStringParameter("bonsaAddr", "");
                final String bonsaAddrNa = psr.getStringParameter("bonsaAddrNa", "");
                final String[] daepyoNm = this.getParams(req, "daepyoNm", "");
                final String[] hdaepyoYN = this.getParams(req, "hdaepyoYN", "");
                final String[] daepyoJumin = this.getParams(req, "daepyoJumin", "");
                final String[] daepyoAddr = this.getParams(req, "daepyoAddr", "");
                final String[] order = this.getParams(req, "order", "");
                final int dpTotalCount = (req.getParameter("dpTotalCount") == null || req.getParameter("dpTotalCount").equals("")) ? 0 : Integer.parseInt(req.getParameter("dpTotalCount"));
                final String businItem = psr.getStringParameter("businItem", "");
                final String licenseNo = psr.getStringParameter("licenseNo", "");
                final String stopGubun = psr.getStringParameter("stopGubun", "");
                final String stopDay = psr.getStringParameter("stopDay", "");
                final String stopSayu = psr.getStringParameter("stopSayu", "");
                final String restartGubun = psr.getStringParameter("restartGubun", "");
                final String restartDay = psr.getStringParameter("restartDay", "");
                final String restartSayu = psr.getStringParameter("restartSayu", "");
                final String punishmentCancelGubun = psr.getStringParameter("punishmentCancelGubun", "");
                final String punishmentCancelDay = psr.getStringParameter("punishmentCancelDay", "");
                final String punishmentCancelSayu = psr.getStringParameter("punishmentCancelSayu", "");
                final String punishmentBasic = psr.getStringParameter("punishmentBasic", "");
                final String punishmentBasicCode = psr.getStringParameter("punishmentBasicCode", "");
                final String punishmentBasicCodeValue = psr.getStringParameter("punishmentBasicCodeValue", "");
                final String dipunishmentBasic = psr.getStringParameter("dipunishmentBasic", "");
                final String punishmentStart = psr.getStringParameter("punishmentStart", "");
                final String punishmentEnd = psr.getStringParameter("punishmentEnd", "");
                final String punishmentPeriod = psr.getStringParameter("punishmentPeriod", "");
                final String cancelDay = psr.getStringParameter("cancelDay", "");
                final String punishmentSayu = psr.getStringParameter("punishmentSayu", "");
                final String punishmentCount = psr.getStringParameter("punishmentCount", "");
                final String country = psr.getStringParameter("country", "");
                final String lakedistrict = psr.getStringParameter("lakedistrict", "");
                final String financeCorporation = psr.getStringParameter("financeCorporation", "");
                final String Code = psr.getStringParameter("Code", "");
                final String punishmentCode = psr.getStringParameter("punishmentCode", "");
                final String code = psr.getStringParameter("code", "");
                final String codeNm = psr.getStringParameter("codeNm", "");
                final String firstId = psr.getStringParameter("firstId", "");
                final String id = psr.getStringParameter("id", "");
                final String gita = psr.getStringParameter("gita", "");
                final String Daepyo = psr.getStringParameter("Daepyo", "");
                final String endDate = psr.getStringParameter("punishmentEnd", "");
                int count = 0;
                Label_1197: {
                    if (flag.equals("stop")) {
                        sb.setLength(0);
                        if (stopDay != null && stopDay.length() > 5) {
                            sb.append("\tUPDATE  ??????_??????????????? SET\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                            sb.append(" \t   \t???????????? = ?, ???????????? = TO_DATE(?, 'YYYY-MM-DD'), ???????????? = ?, ?????????ID = ?\t\t\n");
                            sb.append(" WHERE\t????????????????????? = ?\t\tAND\t???????????? = ?\t\t\t\t\t\t\t\t\t\t\t\n");
                        }
                        else {
                            sb.append("\tUPDATE  ??????_??????????????? SET\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                            sb.append(" \t   \t???????????? = ?,\t???????????? = TO_DATE(?, 'YYYY-MM-DD'), ???????????? = ?,\t\t?????????ID = ?\t\t\t\t\t\t\t\n");
                            sb.append(" WHERE\t????????????????????? = ?\t\tAND\t???????????? = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                        }
                        Label_1245: {
                            try {
                                sql = sb.toString();
                                psmt = con.prepareStatement(sql);
                                if (stopDay != null && stopDay.length() > 5) {
                                    psmt.setString(1, "Y");
                                    psmt.setString(2, stopDay);
                                    psmt.setString(3, stopSayu);
                                    psmt.setString(4, id);
                                    psmt.setString(5, unpairNo);
                                    psmt.setString(6, punishmentCount);
                                }
                                else {
                                    psmt.setString(1, "Y");
                                    psmt.setString(2, punishmentCancelDay);
                                    psmt.setString(3, punishmentCancelSayu);
                                    psmt.setString(4, id);
                                    psmt.setString(5, unpairNo);
                                    psmt.setString(6, punishmentCount);
                                }
                                psmt.executeUpdate();
                                psmt.clearParameters();
                                break Label_1245;
                            }
                            catch (SQLException e) {
                                try {
                                    con.rollback();
                                }
                                catch (SQLException ex) {}
                                if (psmt != null) {
                                    try {
                                        psmt.close();
                                    }
                                    catch (Exception ex2) {}
                                }
                                Log.debug("UM_URV_UserA010c SQLException : Transaction Rollback?????? SQLException ?????????");
                                Log.debug("Exception?????? ?????? : " + e.toString() + e.getErrorCode() + e.getSQLState());
                                e.printStackTrace();
                                res.sendRedirect("/html/error.htm");
                            }
                            break Label_1197;
                        }
                        sb.setLength(0);
                        query1 = "";
                        String cnt = "";
                        try {
                            query1 = " select count(*) from ??????_???????????? where ????????????????????? = '" + unpairNo + "' AND ???????????? = '05'";
                            psmt2 = con.prepareStatement(query1);
                            rs = psmt2.executeQuery();
                            if (rs.next()) {
                                cnt = rs.getString(1);
                                psmt2.clearParameters();
                            }
                            if (cnt.equals("1")) {
                                sb.append(" Delete ??????_???????????? \t\t\t\n");
                                sb.append("  WHERE ????????????????????? = ?       \n");
                                sb.append("\t   AND ???????????? = ?\t            \n");
                                sql = sb.toString();
                                psmt = con.prepareStatement(sql);
                                synchronized (psmt) {
                                    psmt.setString(1, unpairNo.trim());
                                    psmt.setString(2, "05");
                                    psmt.executeUpdate();
                                    psmt.clearParameters();
                                }
                                // monitorexit(psmt)
                            }
                        }
                        catch (SQLException e2) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex3) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex4) {}
                            }
                            Log.debug("UM_URV_UserA010c SQLException : Transaction Rollback?????? SQLException ?????????");
                            Log.debug("Exception?????? ?????? : " + e2.toString() + e2.getErrorCode() + e2.getSQLState());
                            e2.printStackTrace();
                        }
                        con.commit();
                        res.sendRedirect("/jsp/GO/UM_GOJ_ConiB030s.jsp?unpairNo=" + unpairNo + "&punishmentCount=" + punishmentCount + "&flag=stop");
                        break Label_7188;
                    }
                    if (flag.equals("restart")) {
                        sb.setLength(0);
                        sb.append("\tUPDATE  ??????_??????????????? SET\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                        sb.append(" \t   \t???????????? = ?,\t???????????? = TO_DATE(?, 'YYYY-MM-DD'), ???????????? = ?,\t\t?????????ID = ?,\t\t\t\t\t\t\t\n");
                        sb.append(" \t   \t????????????1 = ?,\t????????????2 = ?,\t\t\t\t\t\t ?????????????????? = ?,\t??????????????? = TO_DATE(?, 'YYYY-MM-DD'), \t\n");
                        sb.append(" \t   \t??????????????? = TO_DATE(?, 'YYYY-MM-DD'),\t???????????? = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                        sb.append(" WHERE\t????????????????????? = ?\t\tAND\t???????????? = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                        try {
                            sql = sb.toString();
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, "Y");
                            psmt.setString(2, restartDay);
                            psmt.setString(3, restartSayu);
                            psmt.setString(4, id);
                            psmt.setString(5, punishmentBasic);
                            psmt.setString(6, punishmentBasicCode);
                            psmt.setString(7, dipunishmentBasic);
                            psmt.setString(8, punishmentStart);
                            psmt.setString(9, punishmentEnd);
                            psmt.setString(10, punishmentPeriod);
                            psmt.setString(11, unpairNo);
                            psmt.setString(12, punishmentCount);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                        }
                        catch (SQLException e) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex5) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex6) {}
                            }
                            Log.debug("UM_URV_UserA010c SQLException : Transaction Rollback?????? SQLException ?????????");
                            Log.debug("Exception?????? ?????? : " + e.toString() + e.getErrorCode() + e.getSQLState());
                            e.printStackTrace();
                            res.sendRedirect("/html/error.htm");
                            break Label_1197;
                        }
                        sb.setLength(0);
                        query1 = "";
                        String cnt = "";
                        String date = "";
                        try {
                            query1 = " SELECT COUNT(*) FROM ??????_???????????? WHERE ????????????????????? = '" + unpairNo + "' AND ???????????? = '05'";
                            psmt2 = con.prepareStatement(query1);
                            rs = psmt2.executeQuery();
                            if (rs.next()) {
                                cnt = rs.getString(1);
                                psmt2.clearParameters();
                            }
                            if (cnt.equals("0")) {
                                sb.append("INSERT INTO ??????_???????????? \t\t\t\t\t\t\t\t\t\t\t                  \n");
                                sb.append(" \t\t(?????????????????????, ????????????, ????????????, ????????????, ????????????, ?????????ID, ????????????)  \n");
                                sb.append("\t VALUES (?, ?, ?, ?, ?, ?, sysdate)\t\t\t\t\t\t\t\t\t\t\t\t      \n");
                                sql = sb.toString();
                                psmt = con.prepareStatement(sql);
                                synchronized (psmt) {
                                    psmt.setString(1, unpairNo.trim());
                                    psmt.setString(2, punishmentStart.trim());
                                    psmt.setString(3, punishmentStart.trim());
                                    psmt.setString(4, punishmentEnd.trim());
                                    psmt.setString(5, "05");
                                    psmt.setString(6, id);
                                    psmt.executeUpdate();
                                    psmt.clearParameters();
                                }
                                // monitorexit(psmt)
                                psmt.close();
                            }
                            else {
                                query1 = " SELECT To_Char(????????????, 'YYYYMMDD') FROM ??????_???????????? WHERE ????????????????????? = '" + unpairNo + "' AND ???????????? = '05'";
                                psmt2 = con.prepareStatement(query1);
                                rs2 = psmt2.executeQuery();
                                if (rs2.next()) {
                                    date = rs2.getString(1);
                                    psmt2.clearParameters();
                                }
                                final int date2 = Integer.parseInt(date);
                                final int StopDate = Integer.parseInt(ComStr.replace(endDate, "-", ""));
                                if (date2 < StopDate) {
                                    sb.append("\tUPDATE  ??????_???????????? SET                           \n");
                                    sb.append(" \t   \t???????????? = TO_DATE(?, 'YYYY-MM-DD'),        \n");
                                    sb.append(" \t   \t?????????ID = ?,                               \n");
                                    sb.append(" \t   \t???????????? = sysdate                          \n");
                                    sb.append(" WHERE\t????????????????????? = ?\tAND\t???????????? = ?        \n");
                                    sql = sb.toString();
                                    psmt = con.prepareStatement(sql);
                                    synchronized (psmt) {
                                        psmt.setString(1, punishmentEnd.trim());
                                        psmt.setString(2, id);
                                        psmt.setString(3, unpairNo.trim());
                                        psmt.setString(4, "05");
                                        psmt.executeUpdate();
                                        psmt.clearParameters();
                                    }
                                    // monitorexit(psmt)
                                }
                                rs2.close();
                                psmt.close();
                            }
                        }
                        catch (SQLException e3) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex7) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex8) {}
                            }
                            Log.debug("UM_URV_UserA010c SQLException : Transaction Rollback?????? SQLException ?????????");
                            Log.debug("Exception?????? ?????? : " + e3.toString() + e3.getErrorCode() + e3.getSQLState());
                            e3.printStackTrace();
                        }
                        con.commit();
                        res.sendRedirect("/jsp/GO/UM_GOJ_ConiB030s.jsp?unpairNo=" + unpairNo + "&punishmentCount=" + punishmentCount + "&flag=restart");
                        break Label_7188;
                    }
                    if (flag.equals("punishmentCancel")) {
                        sb.setLength(0);
                        sb.append("\tUPDATE  ??????_??????????????? SET\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                        sb.append(" \t   \t???????????? = ?,\t???????????? = TO_DATE(?, 'YYYY-MM-DD'), ???????????? = ?,\t\t?????????ID = ?\t\t\t\t\t\t\t\n");
                        sb.append(" WHERE\t????????????????????? = ?\t\tAND\t???????????? = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                        try {
                            sql = sb.toString();
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, "Y");
                            psmt.setString(2, punishmentCancelDay);
                            psmt.setString(3, punishmentCancelSayu);
                            psmt.setString(4, id);
                            psmt.setString(5, unpairNo);
                            psmt.setString(6, punishmentCount);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                        }
                        catch (SQLException e) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex9) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex10) {}
                            }
                            Log.debug("UM_URV_UserA010c SQLException : Transaction Rollback?????? SQLException ?????????");
                            Log.debug("Exception?????? ?????? : " + e.toString() + e.getErrorCode() + e.getSQLState());
                            e.printStackTrace();
                            res.sendRedirect("/html/error.htm");
                            break Label_1197;
                        }
                        con.commit();
                        res.sendRedirect("/jsp/GO/UM_GOJ_ConiB030s.jsp?unpairNo=" + unpairNo + "&punishmentCount=" + punishmentCount + "&flag=punishmentCancel");
                        break Label_7188;
                    }
                    if (flag.equals("input")) {
                        query1 = " SELECT COUNT(?????????????????????) aa FROM ??????_??????????????? WHERE ????????????????????? =  ?";
                        psmt2 = con.prepareStatement(query1);
                        psmt2.setString(1, unpairNo);
                        try {
                            synchronized (psmt2) {
                                rs = psmt2.executeQuery();
                                if (rs != null && rs.next()) {
                                    count = rs.getInt("aa") + 1;
                                    sb.setLength(0);
                                    sb.append("insert into ??????_???????????????\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                                    sb.append(" \t\t(?????????????????????,\t\t????????????,\t\t????????????,           ????????????,\t\t????????????,\t\t\t\t\t\n");
                                    sb.append(" \t\t ??????????????????,\t\t\t???????????????,\t\t??????????????????,       ?????????,\t\t\t????????????,                   \n");
                                    sb.append(" \t\t ??????,\t\t\t        ???????????????,\t\t??????????????????,       ????????????,\t\t??????????????????,\t\t\t    \n");
                                    sb.append(" \t\t ????????????1,\t\t        ????????????2,      ??????????????????,       ???????????????,\t\t???????????????,\t\t\t\t\t\n");
                                    sb.append(" \t\t ????????????,\t\t        ???????????????,\t    ????????????????????????,\t??????????????????,\t??????,\t\t\t\t\t\t\n");
                                    sb.append(" \t\t ????????????,\t\t\t\t??????????????????,\t??????????????????,       ????????????,\t\t??????????????????,\t\t        \n");
                                    sb.append(" \t\t ???????????????,\t\t    ???????????????,     ????????????,\t\t\t????????????,\t    ????????????,          \t\t    \n");
                                    sb.append(" \t\t ???????????????)                                                                                            \n");
                                    sb.append("\t VALUES (?, ?, SYSDATE, ?, ?,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                                    sb.append(" \t\t ?, ?, ?, ?, ?, \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                                    sb.append(" \t\t ?, ?, ?, ?, ?, \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                                    sb.append(" \t\t ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'), \t                            \t\t\t\n");
                                    sb.append(" \t\t ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    \n");
                                    sb.append(" \t\t ?, ?, ?, ?, ?,         \t                        \t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                                    sb.append(" \t\t ?, ?, ?, ?, ?,                \t\t\t\t\t\t\t\t\t\t\t\t\t                        \n");
                                    sb.append(" \t\t ?)\t                                                \t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                                    sql = sb.toString();
                                    psmt = con.prepareStatement(sql);
                                    psmt.setString(1, unpairNo.trim());
                                    psmt.setInt(2, count);
                                    psmt.setString(3, "?????????");
                                    psmt.setString(4, compactNo.trim());
                                    psmt.setString(5, compactNoDiffer.trim());
                                    psmt.setString(6, notifyNm);
                                    psmt.setString(7, notifyNo.trim());
                                    psmt.setString(8, upcheNm);
                                    psmt.setString(9, bonsapostNo.trim());
                                    psmt.setString(10, bonsaAddr);
                                    psmt.setString(11, bonsaAddrNa);
                                    psmt.setString(12, notifyKind.trim());
                                    psmt.setString(13, businItem);
                                    psmt.setString(14, licenseNo.trim());
                                    psmt.setString(15, punishmentBasic);
                                    psmt.setString(16, punishmentBasicCode.trim());
                                    psmt.setString(17, dipunishmentBasic);
                                    psmt.setString(18, punishmentStart.trim());
                                    psmt.setString(19, punishmentEnd.trim());
                                    psmt.setString(20, punishmentPeriod.trim());
                                    psmt.setString(21, cancelDay.trim());
                                    psmt.setString(22, Code.trim());
                                    psmt.setString(23, punishmentCode);
                                    psmt.setString(24, gita);
                                    psmt.setString(25, docNo.trim());
                                    psmt.setString(26, lawNo.trim());
                                    psmt.setString(27, notifyDiffer.trim());
                                    psmt.setString(28, "N");
                                    psmt.setString(29, "1230000");
                                    psmt.setString(30, "?????????");
                                    psmt.setString(31, "????????????");
                                    psmt.setString(32, "N");
                                    psmt.setString(33, "N");
                                    psmt.setString(34, compactNoDate);
                                    psmt.setString(35, notifyDate);
                                    psmt.executeUpdate();
                                    psmt.clearParameters();
                                    psmt2.clearParameters();
                                }
                            }
                            // monitorexit(psmt2)
                        }
                        catch (SQLException e) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex11) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex12) {}
                            }
                            Log.debug("UM_URV_UserA010c SQLException : Transaction Rollback?????? SQLException ?????????");
                            Log.debug("Exception?????? ?????? : " + e.toString() + e.getErrorCode() + e.getSQLState());
                            e.printStackTrace();
                            res.sendRedirect("/html/error.htm");
                            break Label_1197;
                        }
                        sb.setLength(0);
                        query1 = "";
                        try {
                            query1 = " SELECT COUNT(?????????????????????) aa FROM ??????_??????????????? WHERE ????????????????????? =  ?";
                            psmt2 = con.prepareStatement(query1);
                            psmt2.setString(1, unpairNo);
                            synchronized (psmt2) {
                                rs = psmt2.executeQuery();
                                if (rs != null && rs.next()) {
                                    sb.append("insert into ??????_??????????????????\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                                    sb.append(" \t\t(?????????????????????,\t\t????????????,\t\t????????????,\t\t??????,\t\t??????????????????,\t\t????????????,\t\t\t\t\n");
                                    sb.append(" \t\t ???????????????,\t\t\t?????????????????????)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                                    sb.append("\t VALUES (?, ?, SYSDATE, ??????.nextval, ?, ?,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                                    sb.append(" \t\t ?, ?)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                                    sql = sb.toString();
                                    psmt = con.prepareStatement(sql);
                                    for (int i = 1; i < dpTotalCount; ++i) {
                                        psmt.setString(1, unpairNo);
                                        psmt.setInt(2, count);
                                        psmt.setString(3, ComStr.replace(daepyoJumin[i], "-", ""));
                                        psmt.setString(4, daepyoNm[i]);
                                        psmt.setString(5, daepyoAddr[i]);
                                        psmt.setString(6, hdaepyoYN[i].trim());
                                        psmt.executeUpdate();
                                        psmt.clearParameters();
                                    }
                                }
                            }
                            // monitorexit(psmt2)
                        }
                        catch (SQLException e) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex13) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex14) {}
                            }
                            Log.debug("UM_URV_UserA010c SQLException : Transaction Rollback?????? SQLException ?????????");
                            Log.debug("Exception?????? ?????? : " + e.toString() + e.getErrorCode() + e.getSQLState());
                            e.printStackTrace();
                        }
                        sb.setLength(0);
                        query1 = "";
                        String cnt = "";
                        String cnt2 = "";
                        String query2 = "";
                        String date3 = "";
                        Label_4682: {
                            try {
                                query1 = " SELECT COUNT(*) FROM ??????_????????????????????? WHERE ????????????????????? = ?";
                                psmt2 = con.prepareStatement(query1);
                                psmt2.setString(1, unpairNo);
                                rs = psmt2.executeQuery();
                                if (rs.next()) {
                                    cnt = rs.getString(1);
                                    psmt2.clearParameters();
                                }
                                psmt2.clearParameters();
                                query2 = " SELECT COUNT(*) FROM ??????_???????????? WHERE ????????????????????? = '" + unpairNo + "' AND ???????????? = '05'";
                                psmt2 = con.prepareStatement(query2);
                                rs = psmt2.executeQuery();
                                if (rs.next()) {
                                    cnt2 = rs.getString(1);
                                    psmt2.clearParameters();
                                }
                                psmt2.clearParameters();
                                if (cnt.equals("1")) {
                                    if (cnt2.equals("0")) {
                                        sb.append("INSERT INTO ??????_???????????? \t\t\t\t\t\t\t\t\t\t\t                 \n");
                                        sb.append(" \t\t(?????????????????????, ????????????, ????????????, ????????????, ????????????, ?????????ID, ????????????) \n");
                                        sb.append("\t VALUES (?, ?, ?, ?, ?, ?, sysdate)\t\t\t\t\t\t\t\t\t\t\t\t     \n");
                                        sql = sb.toString();
                                        psmt = con.prepareStatement(sql);
                                        synchronized (psmt) {
                                            psmt.setString(1, unpairNo.trim());
                                            psmt.setString(2, punishmentStart.trim());
                                            psmt.setString(3, punishmentStart.trim());
                                            psmt.setString(4, punishmentEnd.trim());
                                            psmt.setString(5, "05");
                                            psmt.setString(6, id);
                                            psmt.executeUpdate();
                                            psmt.clearParameters();
                                            // monitorexit(psmt)
                                            break Label_4682;
                                        }
                                    }
                                    query1 = " SELECT To_Char(????????????, 'YYYYMMDD') FROM ??????_???????????? WHERE ????????????????????? = '" + unpairNo + "' AND ???????????? = '05'";
                                    psmt2 = con.prepareStatement(query1);
                                    rs2 = psmt2.executeQuery();
                                    if (rs2.next()) {
                                        date3 = rs2.getString(1);
                                        psmt2.clearParameters();
                                    }
                                    final int date4 = Integer.parseInt(date3);
                                    final int StopDate2 = Integer.parseInt(ComStr.replace(endDate, "-", ""));
                                    if (date4 < StopDate2) {
                                        sb.append(" UPDATE ??????_???????????? SET ???????????? = sysdate, ???????????? = ?, ???????????? = ?, ?????????ID = ?, ???????????? = sysdate \n");
                                        sb.append("  WHERE ????????????????????? = ?                                               \n");
                                        sb.append("    AND ???????????? = ?                                                     \n");
                                        sql = sb.toString();
                                        psmt = con.prepareStatement(sql);
                                        synchronized (psmt) {
                                            psmt.setString(1, punishmentStart.trim());
                                            psmt.setString(2, punishmentEnd.trim());
                                            psmt.setString(3, id);
                                            psmt.setString(4, unpairNo.trim());
                                            psmt.setString(5, "05");
                                            psmt.executeUpdate();
                                            psmt.clearParameters();
                                        }
                                        // monitorexit(psmt)
                                    }
                                    rs2.close();
                                    psmt.close();
                                    psmt2.close();
                                }
                            }
                            catch (SQLException e4) {
                                try {
                                    con.rollback();
                                }
                                catch (SQLException ex15) {}
                                if (psmt != null) {
                                    try {
                                        psmt.close();
                                    }
                                    catch (Exception ex16) {}
                                }
                                Log.debug("UM_URV_UserA010c SQLException : Transaction Rollback?????? SQLException ?????????");
                                Log.debug("Exception?????? ?????? : " + e4.toString() + e4.getErrorCode() + e4.getSQLState());
                                e4.printStackTrace();
                            }
                        }
                        con.commit();
                        res.sendRedirect("/jsp/GO/UM_GOJ_ConiB030m.jsp?unpairNo=" + unpairNo + "&punishmentCount=" + count + "&flag=insert");
                        break Label_7188;
                    }
                    if (flag.equals("update")) {
                        sb.append("UPDATE ??????_??????????????? SET\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                        sb.append(" \t\t ???????????? = SYSDATE,\t???????????? = ?,\t\t???????????? = ?,\t\t\t?????????????????? = ?,   ??????????????? = ?,\t\t\t\t\t\t\n");
                        sb.append(" \t\t ?????????????????? = ?,\t    ????????? = ?,\t\t\t???????????? = ?,\t\t    ?????? = ?,\t\t\t??????????????? = ?,\t\t\t\t\t\t\n");
                        sb.append(" \t\t ?????????????????? = ?,\t    ???????????? = ?,\t\t?????????????????? = ?,\t    ????????????1 = ?,\t\t????????????2 = ?,                  \t\n");
                        sb.append(" \t\t ?????????????????? = ?,\t\t??????????????? = TO_DATE(?, 'YYYY-MM-DD'),\t\t??????????????? = TO_DATE(?, 'YYYY-MM-DD'),\t???????????? = ?,\t\n");
                        sb.append(" \t\t ??????????????? = TO_DATE(?, 'YYYY-MM-DD'),\t\t???????????????????????? = ?,\t?????????????????? = ?,\t?????? = ?,\t\t\t\t\t\t\t\n");
                        sb.append(" \t\t ???????????? = ?,\t\t\t?????????????????? = ?,\t?????????????????? = ?,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                        sb.append(" \t\t ???????????? = ?,\t\t\t???????????? = TO_DATE(?, 'YYYY-MM-DD'),\t\t???????????? = ?,\t\t\t\t\t\t\t\t\t\t\t\n");
                        sb.append(" \t\t ???????????? = ?,\t\t\t???????????? = TO_DATE(?, 'YYYY-MM-DD'),\t\t???????????? = ?,\t\t\t\t\t\t\t\t\t\t\t\n");
                        sb.append(" \t\t ???????????? = ?,\t\t\t???????????? = TO_DATE(?, 'YYYY-MM-DD'),\t\t???????????? = ?,\t\t\t\t\t\t\t\t\t\t\t\n");
                        sb.append(" \t\t ?????????????????? = ?,\t\t??????????????? = ?,                                              \t\t\t\t\t\t\t\t\t\t\n");
                        sb.append(" \t\t ??????????????? = TO_DATE(?, 'YYYY-MM-DD'),\t\t???????????? = TO_DATE(?, 'YYYY-MM-DD')   \t\t\t\t\t\t\t\t\t\t\n");
                        sb.append("\t WHERE ????????????????????? = ?\t\tAND\t\t???????????? = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                        try {
                            sql = sb.toString();
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, "?????????");
                            psmt.setString(2, compactNo);
                            psmt.setString(3, compactNoDiffer);
                            psmt.setString(4, notifyNm);
                            psmt.setString(5, notifyNo);
                            psmt.setString(6, upcheNm);
                            psmt.setString(7, ComStr.replace(bonsapostNo, "-", ""));
                            psmt.setString(8, bonsaAddr);
                            psmt.setString(9, bonsaAddrNa);
                            psmt.setString(10, notifyKind);
                            psmt.setString(11, businItem);
                            psmt.setString(12, licenseNo);
                            psmt.setString(13, punishmentBasic);
                            psmt.setString(14, punishmentBasicCode);
                            psmt.setString(15, dipunishmentBasic);
                            psmt.setString(16, punishmentStart);
                            psmt.setString(17, punishmentEnd);
                            psmt.setString(18, punishmentPeriod);
                            psmt.setString(19, cancelDay);
                            psmt.setString(20, Code);
                            psmt.setString(21, punishmentCode);
                            psmt.setString(22, gita);
                            psmt.setString(23, docNo);
                            psmt.setString(24, lawNo);
                            psmt.setString(25, notifyDiffer);
                            if (stopSayu != null && stopDay.length() > 7) {
                                psmt.setString(26, stopGubun);
                            }
                            else {
                                psmt.setString(26, "N");
                            }
                            psmt.setString(27, stopDay);
                            psmt.setString(28, stopSayu);
                            if (restartSayu != null && restartDay.length() > 7) {
                                psmt.setString(29, restartGubun);
                            }
                            else {
                                psmt.setString(29, "N");
                            }
                            psmt.setString(30, restartDay);
                            psmt.setString(31, restartSayu);
                            if (punishmentCancelSayu != null && punishmentCancelDay.length() > 7) {
                                psmt.setString(32, punishmentCancelGubun);
                            }
                            else {
                                psmt.setString(32, "N");
                            }
                            psmt.setString(33, punishmentCancelDay);
                            psmt.setString(34, punishmentCancelSayu);
                            psmt.setString(35, "1230000");
                            psmt.setString(36, "?????????");
                            psmt.setString(37, notifyDate);
                            psmt.setString(38, compactNoDate);
                            psmt.setString(39, ComStr.replace(unpairNo, "-", ""));
                            psmt.setString(40, punishmentCount);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                        }
                        catch (SQLException e) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex17) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex18) {}
                            }
                            Log.debug("UM_URV_UserA010c SQLException : Transaction Rollback?????? SQLException ?????????");
                            Log.debug("Exception?????? ?????? : " + e.toString() + e.getErrorCode() + e.getSQLState());
                            e.printStackTrace();
                            res.sendRedirect("/html/error.htm");
                            break Label_1197;
                        }
                        sb.setLength(0);
                        final String[] arr_Daepyo = ComStr.getStringToStringArray(Daepyo, "\u007f\u007f");
                        String[] arr_key = (String[])null;
                        try {
                            sb.append(" UPDATE ??????_?????????????????? SET\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                            sb.append(" \t   ?????????????????? = ?,\t???????????? = ?,\t??????????????? = ?,\t\t????????????????????? = ?\t\t\n");
                            sb.append("\t WHERE ????????????????????? = ?\tAND\t????????????=?\tAND ?????? = ?\t\t\t\t\t\t\t\t\n");
                            sql = sb.toString();
                            psmt = con.prepareStatement(sql);
                            for (int M = 0; M < arr_Daepyo.length; ++M) {
                                arr_key = ComStr.getStringToStringArray(arr_Daepyo[M], "|");
                                synchronized (psmt) {
                                    psmt.setString(1, ComStr.replace(arr_key[4], "-", ""));
                                    psmt.setString(2, arr_key[3]);
                                    psmt.setString(3, arr_key[5]);
                                    psmt.setString(4, arr_key[6].trim());
                                    psmt.setString(5, ComStr.replace(arr_key[0], "-", ""));
                                    psmt.setString(6, arr_key[1]);
                                    psmt.setString(7, arr_key[2]);
                                    psmt.executeUpdate();
                                    psmt.clearParameters();
                                }
                                // monitorexit(psmt)
                            }
                            psmt.close();
                        }
                        catch (SQLException e3) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex19) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex20) {}
                            }
                            Log.debug("UM_URV_UserA010c SQLException : Transaction Rollback?????? SQLException ?????????");
                            Log.debug("Exception?????? ?????? : " + e3.toString() + e3.getErrorCode() + e3.getSQLState());
                            e3.printStackTrace();
                        }
                        sb.setLength(0);
                        query1 = "";
                        String cnt3 = "";
                        try {
                            query1 = " select count(*) from ??????_???????????? where ????????????????????? = '" + unpairNo + "' AND ???????????? = '05'";
                            psmt2 = con.prepareStatement(query1);
                            rs = psmt2.executeQuery();
                            if (rs.next()) {
                                cnt3 = rs.getString(1);
                                psmt2.clearParameters();
                            }
                            if (cnt3.equals("1")) {
                                sb.append(" UPDATE  ??????_???????????? SET  \t\t\t                                        \n");
                                sb.append("         ???????????? = TO_DATE(?, 'YYYY-MM-DD'), ???????????? = (?, 'YYYY-MM-DD'),  \n");
                                sb.append("         ?????????ID = ?, ???????????? = sysdate                                    \n");
                                sb.append("  WHERE ????????????????????? = ?                                                   \n");
                                sb.append("\t   AND ???????????? = ?                                                         \n");
                                sql = sb.toString();
                                psmt = con.prepareStatement(sql);
                                synchronized (psmt) {
                                    psmt.setString(1, punishmentStart);
                                    psmt.setString(2, punishmentEnd);
                                    psmt.setString(3, id);
                                    psmt.setString(4, unpairNo);
                                    psmt.setString(5, "05");
                                    psmt.executeUpdate();
                                    psmt.clearParameters();
                                }
                                // monitorexit(psmt)
                            }
                        }
                        catch (SQLException e5) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex21) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex22) {}
                            }
                            Log.debug("UM_URV_UserA010c SQLException : Transaction Rollback?????? SQLException ?????????");
                            Log.debug("Exception?????? ?????? : " + e5.toString() + e5.getErrorCode() + e5.getSQLState());
                            e5.printStackTrace();
                        }
                        con.commit();
                        res.sendRedirect("/jsp/GO/UM_GOJ_ConiB030m.jsp?unpairNo=" + unpairNo + "&punishmentCount=" + punishmentCount + "&flag=up");
                        break Label_7188;
                    }
                    if (flag.equals("DaepyoInsert")) {
                        sb.setLength(0);
                        try {
                            sb.append("insert into ??????_??????????????????\t\t\t\t\t\t\t\t\t        \t\t\n");
                            sb.append(" \t\t(?????????????????????,\t????????????,\t????????????,\t??????,   ?????????????????????)\t    \n");
                            sb.append("\t VALUES (?, ?, SYSDATE, ??????.nextval, ?)   \t\t\t\t\t\t\t    \t    \n");
                            sql = sb.toString();
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, ComStr.replace(unpairNo, "-", ""));
                            psmt.setString(2, punishmentCount);
                            psmt.setString(3, "N");
                            psmt.executeUpdate();
                            psmt.clearParameters();
                        }
                        catch (SQLException e) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex23) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex24) {}
                            }
                            Log.debug("UM_URV_UserA010c SQLException : Transaction Rollback?????? SQLException ?????????");
                            Log.debug("Exception?????? ?????? : " + e.toString() + e.getErrorCode() + e.getSQLState());
                            e.printStackTrace();
                        }
                        con.commit();
                        res.sendRedirect("/jsp/GO/UM_GOJ_ConiB040m.jsp?unpairNo=" + unpairNo + "&punishmentCount=" + punishmentCount);
                        break Label_7188;
                    }
                    if (flag.equals("DaepyoDelete")) {
                        sb.setLength(0);
                        final String[] arr_Daepyo = ComStr.getStringToStringArray(Daepyo, "\u007f\u007f");
                        String[] arr_key = (String[])null;
                        try {
                            sb.append(" DELETE  ??????_??????????????????       ");
                            sb.append("  WHERE  ????????????????????? = ?      ");
                            sb.append("    AND  ???????????? = ?            ");
                            sb.append("    AND  ?????? = ?                ");
                            sql = sb.toString();
                            psmt = con.prepareStatement(sql);
                            for (int M = 0; M < arr_Daepyo.length; ++M) {
                                arr_key = ComStr.getStringToStringArray(arr_Daepyo[M], "|");
                                synchronized (psmt) {
                                    psmt.setString(1, arr_key[0]);
                                    psmt.setString(2, arr_key[1]);
                                    psmt.setString(3, arr_key[2]);
                                    psmt.executeUpdate();
                                    psmt.clearParameters();
                                }
                                // monitorexit(psmt)
                            }
                            psmt.close();
                        }
                        catch (SQLException e3) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex25) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex26) {}
                            }
                            Log.debug("UM_URV_UserA010c SQLException : Transaction Rollback?????? SQLException ?????????");
                            Log.debug("Exception?????? ?????? : " + e3.toString() + e3.getErrorCode() + e3.getSQLState());
                            e3.printStackTrace();
                        }
                        con.commit();
                        res.sendRedirect("/jsp/GO/UM_GOJ_ConiB040m.jsp?unpairNo=" + unpairNo + "&punishmentCount=" + punishmentCount);
                    }
                    break Label_7188;
                }
                return;
            }
            catch (SQLException exc) {
                try {
                    con.rollback();
                    con.setAutoCommit(true);
                }
                catch (SQLException e6) {
                    Log.debug("UM_URV_UserA010c SQLException : Transaction Rollback?????? SQLException ?????????");
                    Log.debug("Exception?????? ?????? : " + e6.toString() + e6.getErrorCode() + e6.getSQLState());
                    e6.printStackTrace();
                }
                Log.debug("UM_URV_UserA010c SQLException : ");
                Log.debug("Exception?????? ?????? : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                exc.printStackTrace();
            }
            catch (Exception exc2) {
                try {
                    con.rollback();
                    con.setAutoCommit(true);
                }
                catch (SQLException e6) {
                    Log.debug("UM_URV_UserA010c Exception : Transaction Rollback?????? Exception ?????????");
                    Log.debug("Exception?????? ?????? : " + e6.toString() + e6.getErrorCode() + e6.getSQLState());
                    e6.printStackTrace();
                }
                Log.debug("UM_URV_UserA010c Exception : ");
                Log.debug("Exception?????? ?????? : " + exc2.toString());
                exc2.printStackTrace();
            }
            finally {
                if (psmt != null) {
                    try {
                        psmt.close();
                    }
                    catch (Exception ex27) {}
                }
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (Exception ex28) {}
                }
                if (trx != null) {
                    try {
                        trx.close();
                    }
                    catch (Exception ex29) {}
                }
            }
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex30) {}
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex31) {}
        }
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex32) {}
        }
    }
}
