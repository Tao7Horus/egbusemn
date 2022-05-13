// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;
import common.util.CommonMessage;
import common.Log;
import common.Trx;
import LOGIN.UM_Auth_Check;
import beans.UM_GOB_GovuX010x;
import beans.UM_ADB_GOMAPPING;
import beans.UM_ADB_GONGINFO;
import java.util.Hashtable;
import javax.servlet.ServletRequest;
import com.oreilly.servlet.ParameterParser;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_GOV_GovrX000x extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=euc-kr");
        Trx trx = null;
        Connection con = null;
        final ParameterParser psr = new ParameterParser((ServletRequest)req);
        final String g2bCode = psr.getStringParameter("g2bCode", "");
        final String g2bname = psr.getStringParameter("g2bname", "");
        final String Newg2bCode = psr.getStringParameter("Newg2bCode", "");
        final String Newg2bname = psr.getStringParameter("Newg2bname", "");
        final String Gubun = psr.getStringParameter("Gubun", "");
        final Hashtable logHash = new Hashtable();
        UM_Auth_Check uac = null;
        final UM_ADB_GONGINFO uagInfo = new UM_ADB_GONGINFO();
        final UM_ADB_GOMAPPING uagMapping = new UM_ADB_GOMAPPING();
        final UM_GOB_GovuX010x uggX010 = new UM_GOB_GovuX010x();
        String message = null;
        String sangWiCode = null;
        String userID = null;
        String suyoCode = null;
        String IP = null;
        try {
            uac = new UM_Auth_Check(req, res);
            userID = uac.getID();
            IP = uac.getIP();
            if (!userID.equals("C135370100023")) {
                throw new Exception("프로그램 사용 권한이 없습니다.");
            }
            logHash.put("1", "권한체크 완료");
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            con.setAutoCommit(false);
            if (uagInfo.isMasterCodeExist(Newg2bCode, con)) {
                throw new Exception("신규로 변경하려는 기관코드는 이미 등록되어 있는 코드입니다.");
            }
            if (Gubun.equals("OnlyName")) {
                uggX010.updateGongInfoOnlyName(g2bCode, Newg2bname, con);
                logHash.put("2", "사용_공공기관마스터 기관명만 update");
                suyoCode = uagMapping.getDataSynSyuoMapping(g2bCode, con);
                if (suyoCode == null) {
                    throw new Exception(String.valueOf(g2bCode) + " 코드에 대한 수요기관매핑코드가 존재하지 않습니다.");
                }
                uggX010.updateSuyoOnlyName(suyoCode, Newg2bname, con);
                logHash.put("3", "syn_수요기관 기관명만 update");
            }
            else {
                sangWiCode = uggX010.getDataUsemnStandardInfo(Newg2bCode, con);
                if (sangWiCode == null) {
                    throw new Exception(String.valueOf(Newg2bCode) + " 코드에 대한 최상위 기관코드가 존재하지 않습니다.");
                }
                if (Gubun.equals("OnlyCode")) {
                    uggX010.insertGongNewCode(Newg2bCode, sangWiCode, g2bCode, con);
                    logHash.put("4", "사용_공공기관마스터 select > insert");
                }
                else if (Gubun.equals("Code_Name")) {
                    uggX010.insertGongNewCode(Newg2bCode, sangWiCode, g2bCode, Newg2bname, con);
                    logHash.put("5", "사용_공공기관마스터 select > insert");
                    suyoCode = uagMapping.getDataSynSyuoMapping(g2bCode, con);
                    if (suyoCode == null) {
                        throw new Exception(String.valueOf(g2bCode) + " 코드에 대한 수요기관매핑코드가 존재하지 않습니다.");
                    }
                    uggX010.updateSuyoOnlyName(suyoCode, Newg2bname, con);
                    logHash.put("6", "syn_수요기관 select > insert");
                }
                logHash.put("7", "syn_매핑코드기관 select > insert");
                logHash.put("8", "syn_매핑코드기관 사용여부 N update");
                uggX010.updateGongInfoDeleteYN(g2bCode, con);
                logHash.put("9", "사용_공공기관마스터 삭제여부 Y update");
                uggX010.updateGongInfoUser(Newg2bCode, g2bCode, con);
                logHash.put("10", "사용_사용자 기존마스터 코드 신규로 update");
                uggX010.updateGongInfoFundCode(Newg2bCode, g2bCode, con);
                logHash.put("11", "사용_공공기관회계코드 기존코드 신규로 update");
                uggX010.updateGongInfoMoneyCode(Newg2bCode, g2bCode, con);
                logHash.put("12", "syn_지불_입금계좌 기존코드 신규로 update");
            }
            if (Gubun.equals("OnlyName")) {
                message = "처리되었습니다.<br><br>변경전 : " + g2bname + "<br>변경후: " + Newg2bname;
            }
            else if (Gubun.equals("OnlyCode")) {
                message = "처리되었습니다.<br><br>변경전 : " + g2bCode + "<br>변경후: " + Newg2bCode;
            }
            else if (Gubun.equals("Code_Name")) {
                message = "처리되었습니다.<br><br>변경전 : " + g2bCode + "[" + g2bname + "]<br>변경후: " + Newg2bCode + "[" + Newg2bname + "]";
            }
            Log.debug("기관승인자 118 IP[" + IP + "]: 기관정보 변경처리 : 기존코드[" + g2bCode + "], 신규코드[" + Newg2bCode + "],기존명[" + g2bname + "], 신규명[" + Newg2bname + "],처리자ID[" + userID + "], 구분[" + Gubun + "], 수요기관코드[" + suyoCode + "]");
            con.commit();
            con.setAutoCommit(true);
            CommonMessage.printMsg(null, "1", message, "/jsp/GO/UM_GOJ_ConiX010i.jsp", res);
        }
        catch (Exception ex) {
            try {
                if (con != null) {
                    con.rollback();
                }
            }
            catch (SQLException ex2) {}
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                }
            }
            catch (SQLException ex3) {}
            Log.debug("UM_GOV_GovrX000x.java IP[" + IP + "] 기존코드[" + g2bCode + "], 신규코드[" + Newg2bCode + "],기존명[" + g2bname + "], 신규명[" + Newg2bname + "],처리자ID[" + userID + "], 구분[" + Gubun + "]:" + ex.toString());
            CommonMessage.printMsg(null, "", ex.getMessage(), null, res);
            return;
        }
        finally {
            try {
                if (con != null) {
                    trx.close();
                }
            }
            catch (Exception ex4) {}
        }
        try {
            if (con != null) {
                trx.close();
            }
        }
        catch (Exception ex5) {}
    }
}
