// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import entity.UM_RAM_RcvCode;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import common.Log;
import java.sql.Connection;

public class UM_RAM_RcvRegistry
{
    public boolean gov_verify(final Connection connection, final String s) {
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        boolean b = false;
        int int1 = 0;
        try {
            sb.append(" SELECT\tCOUNT(*) FROM  사용_공공기관마스터\t\t\t\n");
            sb.append(" WHERE\t공공기관코드 ='" + s + "'\n");
            prepareStatement = connection.prepareStatement(sb.toString());
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
            if (int1 > 0) {
                b = true;
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvRegistry.gov_verify(code[" + s + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvRegistry.gov_verify(code[" + s + "]):" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
        }
        return b;
    }
    
    public boolean isExistCode(final Connection connection, final String s) {
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        boolean b = false;
        int int1 = 0;
        try {
            sb.append(" SELECT\tCOUNT(*) FROM  사용_행정표준정보\t\t\t\n");
            sb.append(" WHERE\t행정표준기관코드 ='" + s + "'\n");
            prepareStatement = connection.prepareStatement(sb.toString());
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
            if (int1 > 0) {
                b = true;
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvRegistry.isExistCode(code[" + s + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvRegistry.isExistCode(code[" + s + "]):" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
        }
        return b;
    }
    
    public void StdCodeInsert(final Connection connection, final UM_RAM_RcvCode um_RAM_RcvCode, final String s, final String s2) {
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            if (!s.equals("")) {
                sb.append(" INSERT INTO\t\t사용_행정표준정보\t\t\t\t\t\t\t\t\t\t\t\n");
                sb.append("\t\t\t\t\t(행정표준기관코드,전체명,최하위명,대표코드,우편번호,\t\t\t\n");
                sb.append("\t\t\t\t\t소재지,지번,전화번호,FAX번호,기관장직급명,기관장직위,차수,서열,\t \n");
                sb.append("\t\t\t\t\t차상위기관코드,최상위기관코드,기관유형_대,기관유형_중,기관유형_소, \n");
                sb.append("\t\t\t\t\t생성일자,  폐지일자,폐지구분,변경일자, 변경시간, 변경유형구분 )\t  \n");
                sb.append(" SELECT   ?, ?, 최하위명,대표코드,우편번호,\t\t\t\t\t\t\t\t\t\n");
                sb.append("          소재지, 지번, 전화번호, FAX번호, ?, ?, ?, ?,\t\t\t\t\t\t\n");
                sb.append("          ?, ?, ?, ?, ?,\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                sb.append("          to_date(?,'YYYYMMDD HH24:MI:SS'),폐지일자,'0', sysdate, 변경시간, ?\t\t\t\t\t\t\t\t\n");
                sb.append(" FROM     사용_행정표준정보\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                sb.append(" WHERE\t 행정표준기관코드 = ?\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else {
                sb.append(" INSERT INTO\t\t사용_행정표준정보\t\t\t\t\t\t\t\t\t\t\t\n");
                sb.append("\t\t\t\t\t(행정표준기관코드,전체명,기관장직급명,기관장직위,차수,서열,\t \n");
                sb.append("\t\t\t\t\t차상위기관코드,최상위기관코드,기관유형_대,기관유형_중,기관유형_소, \n");
                sb.append("\t\t\t\t\t생성일자,폐지구분,변경일자,변경유형구분 )\t\t\t\t\t\t\n");
                sb.append(" VALUES  ( ?, ?, ?, ?, ?, ?,\t\t\t\t\t\t\t\t\t\t\t\t\n");
                sb.append("          ?, ?, ?, ?, ?,\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                sb.append("          to_date(?,'YYYYMMDD HH24:MI:SS'),'0', sysdate, ? )\t\t\t\t\n");
            }
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, um_RAM_RcvCode.getgccCode());
            prepareStatement.setString(2, um_RAM_RcvCode.getgoNameFull());
            prepareStatement.setString(3, um_RAM_RcvCode.getmasterTask());
            prepareStatement.setString(4, um_RAM_RcvCode.gettaskCode());
            prepareStatement.setString(5, um_RAM_RcvCode.getdegree());
            prepareStatement.setString(6, um_RAM_RcvCode.getrank());
            prepareStatement.setString(7, um_RAM_RcvCode.getfirstUpper());
            prepareStatement.setString(8, um_RAM_RcvCode.getmostUpper());
            prepareStatement.setString(9, um_RAM_RcvCode.gettypeLarge());
            prepareStatement.setString(10, um_RAM_RcvCode.gettypeMiddle());
            prepareStatement.setString(11, um_RAM_RcvCode.gettypeSmall());
            prepareStatement.setString(12, um_RAM_RcvCode.getcreateDate());
            prepareStatement.setString(13, s2);
            if (!s.equals("")) {
                prepareStatement.setString(14, s);
            }
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvRegistry.StdCodeInsert():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvRegistry.StdCodeInsert():" + ex2.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public void StdCodeUpdate(final Connection connection, final UM_RAM_RcvCode um_RAM_RcvCode, final String s) {
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" UPDATE\t사용_행정표준정보\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\tSET\t\t전체명=?,기관장직급명=?,기관장직위=?,\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t차수=?,서열=?,차상위기관코드=?,최상위기관코드=?,\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t기관유형_대=?,기관유형_중=?,기관유형_소=?,\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t생성일자=to_date(?,'YYYYMMDD HH24:MI:SS'),폐지구분='0',변경일자=sysdate,변경유형구분=?\t\t\t\t\t\n");
            sb.append(" WHERE\t행정표준기관코드 = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, um_RAM_RcvCode.getgoNameFull());
            prepareStatement.setString(2, um_RAM_RcvCode.getmasterTask());
            prepareStatement.setString(3, um_RAM_RcvCode.gettaskCode());
            prepareStatement.setString(4, um_RAM_RcvCode.getdegree());
            prepareStatement.setString(5, um_RAM_RcvCode.getrank());
            prepareStatement.setString(6, um_RAM_RcvCode.getfirstUpper());
            prepareStatement.setString(7, um_RAM_RcvCode.getmostUpper());
            prepareStatement.setString(8, um_RAM_RcvCode.gettypeLarge());
            prepareStatement.setString(9, um_RAM_RcvCode.gettypeMiddle());
            prepareStatement.setString(10, um_RAM_RcvCode.gettypeSmall());
            prepareStatement.setString(11, um_RAM_RcvCode.getcreateDate());
            prepareStatement.setString(12, s);
            prepareStatement.setString(13, um_RAM_RcvCode.getgccCode());
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvRegistry.StdCodeInsert():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvRegistry.StdCodeInsert():" + ex2.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public void StdCodeDelete(final Connection connection, final String s, final String s2) {
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" UPDATE\t사용_행정표준정보\t\t\t\t\t             \n");
            sb.append(" SET\t    폐지구분 = '1', 변경일자=sysdate, 변경유형구분=?,   \n");
            sb.append("\t폐지일자 = (SELECT 생성일자 FROM 사용_행정표준정보수신 WHERE 행정표준기관코드 = ? and DML구분 = 'DEL') \n");
            sb.append(" WHERE\t행정표준기관코드 = ?\t\t\t\t               \n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s2);
            prepareStatement.setString(2, s);
            prepareStatement.setString(3, s);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvRegistry.StdCodeDelete():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvRegistry.StdCodeDelete():" + ex2.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public void StdCodeDeleteTemp(final Connection connection, final String s, final String s2) {
        System.out.println("임의기관!!!");
        System.out.println("코드 : " + s);
        System.out.println("구분 : " + s2);
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" UPDATE\t사용_행정표준정보\t\t\t\t\t             \n");
            sb.append(" SET\t    폐지구분 = '1', 변경일자=sysdate, 변경유형구분=?,   \n");
            sb.append("\t폐지일자 = sysdate \n");
            sb.append(" WHERE\t행정표준기관코드 = ?\t\t\t\t               \n");
            final String string = sb.toString();
            System.out.println(string);
            prepareStatement = connection.prepareStatement(string);
            prepareStatement.setString(1, s2);
            prepareStatement.setString(2, s);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvRegistry.StdCodeDelete():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvRegistry.StdCodeDelete():" + ex2.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public void StdChgInsert(final Connection connection, final String s, final String s2, final String s3, final String s4) {
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            prepareStatement = connection.prepareStatement(" INSERT INTO\t사용_행정표준정보변경 (행정표준기관코드, 이전코드, 변경일자, 이력구분,처리자ID)  values(?, ?,sysdate,?,?)");
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.setString(3, s3);
            prepareStatement.setString(4, s4);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvRegistry.StdChgInsert():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvRegistry.StdChgInsert():" + ex2.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public void GovCodeInsert(final Connection connection, final String s, final String s2, final String s3, final String s4) {
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" INSERT INTO\t\t사용_공공기관마스터\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t\t\t(공공기관코드,공공기관명_전체, 공공기관명_약어, 공공기관명_영문, 사업자등록번호, \n");
            sb.append("\t\t\t\t\t 소관구분, 업태, 업종, 담당자명, 담당자부서명, 담당자전화번호, 담당자팩스번호,   담당자메일주소, \t \n");
            sb.append("\t\t\t\t\t 우편번호,  주소, 나머지주소, 전화번호,  팩스번호,  홈페이지주소,    전수요기관코드,  물품관리관명,  \n");
            sb.append("\t\t\t\t\t 양허코드,  관할지청,특정관리기관,지청구분,  운송거리, 전자인증여부,처리자ID, 지역코드,등록일자,삭제여부,   \n");
            sb.append("\t\t\t\t\t 채권자명, 갱신일자, 금결원승인번호,최상위기관코드, 기관유형구분, 접수번호, 이력코드 )   \n");
            sb.append(" SELECT   ?, ?, 공공기관명_약어, 공공기관명_영문, 사업자등록번호,\t\t\n");
            sb.append("          소관구분, 업태, 업종, 담당자명, 담당자부서명, 담당자전화번호, 담당자팩스번호,담당자메일주소, \t   \n");
            sb.append("          우편번호,  주소, 나머지주소, 전화번호,  팩스번호,  홈페이지주소, 전수요기관코드,  물품관리관명, \t\n");
            sb.append("          양허코드,  관할지청,특정관리기관, 지청구분,  운송거리, 전자인증여부, 처리자ID, 지역코드,등록일자,'N', \n");
            if (s4.equals("03")) {
                sb.append("\t\t\t 채권자명, sysdate, 금결원승인번호,최상위기관코드, 기관유형구분, 접수번호, 이력코드         \n");
            }
            else {
                sb.append("\t\t\t 채권자명, sysdate, 금결원승인번호,최상위기관코드, 기관유형구분, 접수번호, '" + s + "'   \n");
            }
            sb.append(" FROM     사용_공공기관마스터\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\t 공공기관코드  = ?\t\t\t\t\t\t\t\t\t\t\t\t\n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.setString(3, s3);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_RAM_RcvRegistry.GovCodeInsert():" + ex.toString());
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("UM_RAM_RcvRegistry.GovCodeInsert():" + ex2.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public void GovCodeInsert2(final Connection connection, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8) {
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" INSERT INTO\t\t사용_공공기관마스터\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t\t\t(공공기관코드,공공기관명_전체, 공공기관명_약어, 공공기관명_영문, 사업자등록번호, \n");
            sb.append("\t\t\t\t\t 소관구분, 업태, 업종, 담당자명, 담당자부서명, 담당자전화번호, 담당자팩스번호,   담당자메일주소, \t \n");
            sb.append("\t\t\t\t\t 우편번호,  주소, 나머지주소, 전화번호,  팩스번호,  홈페이지주소,    전수요기관코드,  물품관리관명,  \n");
            sb.append("\t\t\t\t\t 양허코드,  관할지청,특정관리기관,지청구분,  운송거리, 전자인증여부,처리자ID, 지역코드,등록일자,삭제여부,   \n");
            sb.append("\t\t\t\t\t 채권자명, 갱신일자, 금결원승인번호,최상위기관코드, 기관유형구분, 접수번호, 이력코드, 기관유형_중, 기관유형_소, 코드체크여부) \n");
            sb.append(" SELECT   ?, ?, 공공기관명_약어, 공공기관명_영문, 사업자등록번호,\t\t\n");
            sb.append("          소관구분, 업태, 업종, 담당자명, 담당자부서명, 담당자전화번호, 담당자팩스번호,담당자메일주소, \t   \n");
            sb.append("          우편번호,  주소, 나머지주소, 전화번호,  팩스번호,  홈페이지주소, 전수요기관코드,  물품관리관명, \t\n");
            sb.append("          양허코드,  관할지청,특정관리기관, 지청구분,  운송거리, 전자인증여부, 처리자ID, 지역코드,등록일자,'N', \n");
            if (s4.equals("03")) {
                sb.append("\t\t\t 채권자명, sysdate, 금결원승인번호, ?, ?, 접수번호, 이력코드, ?, ?, ? \n");
            }
            else {
                sb.append("\t\t\t 채권자명, sysdate, 금결원승인번호, ?, ?, 접수번호, '" + s + "', ?, ?, ? \n");
            }
            sb.append(" FROM     사용_공공기관마스터\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\t 공공기관코드  = ?\t\t\t\t\t\t\t\t\t\t\t\t\n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.setString(3, s5);
            prepareStatement.setString(4, s6);
            prepareStatement.setString(5, s7);
            prepareStatement.setString(6, s8);
            prepareStatement.setString(7, "N");
            prepareStatement.setString(8, s3);
            System.out.println("Parameters : " + s5 + ", " + s6 + ", " + s7 + ", " + s8);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_RAM_RcvRegistry.GovCodeInsert():" + ex.toString());
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("UM_RAM_RcvRegistry.GovCodeInsert():" + ex2.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public void GovCodeUpdate(final Connection connection, final String s, final String s2) {
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" UPDATE\t사용_공공기관마스터\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" SET    \t공공기관명_전체=?, 갱신일자=sysdate\t\t\t\t\t\t\t\n");
            sb.append(" FROM    사용_공공기관마스터\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\t공공기관코드  = ?\t\t\t\t\t\t\t\t\t\t\t\n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s2);
            prepareStatement.setString(2, s);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvRegistry.GovCodeUpdate():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvRegistry.GovCodeUpdate():" + ex2.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public void GovDelete(final Connection connection, final String s) {
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" UPDATE\t사용_공공기관마스터\t\t\t\t\t\n");
            sb.append(" SET\t    삭제여부 = 'Y', 갱신일자=sysdate       \n");
            sb.append(" WHERE\t공공기관코드 = ?\t\t\t\t\t\t\n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvRegistry.GovDelete():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvRegistry.GovDelete():" + ex2.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public void GovChgInsert(final Connection connection, final String s, final String s2, final String s3, final String s4) {
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            prepareStatement = connection.prepareStatement(" INSERT INTO\t사용_공공기관마스터변경\t (공공기관코드, 이전코드, 변경일자, 이력구분,처리자ID)  values(?, ?,sysdate,?,?)");
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.setString(3, s3);
            prepareStatement.setString(4, s4);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvRegistry.GovChgInsert():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvRegistry.GovChgInsert():" + ex2.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public void GovMathUpdate(final Connection connection, final String s, final String s2) {
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" UPDATE\t사용_공공기관회계코드\t\n");
            sb.append(" SET\t    공공기관코드 = ?      \n");
            sb.append(" WHERE\t공공기관코드 = ?      \n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvRegistry.GovMathUpdate():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvRegistry.GovMathUpdate():" + ex2.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public void GovMathInsert(final Connection connection, final String s, final String s2) {
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" INSERT INTO\t사용_공공기관회계코드\t     \n");
            sb.append(" \t    (공공기관코드,공공기관회계코드)   \n");
            sb.append(" SELECT  ?,공공기관회계코드              \n");
            sb.append(" FROM\t사용_공공기관회계코드            \n");
            sb.append(" WHERE\t공공기관코드 = ?                \n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvRegistry.GovMathUpdate():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvRegistry.GovMathUpdate():" + ex2.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public void RcvCodeUpdate(final Connection connection, final String s, final String s2, final String s3, final String s4) {
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" UPDATE\t사용_행정표준정보수신\t\t\n");
            sb.append(" SET\t    상태 = ?\t\t\t\t\t\n");
            sb.append(" WHERE\t행정표준기관코드 = ?\t\t\n");
            sb.append(" AND\t\tDML구분 like ?\t\t\t\t\n");
            sb.append(" AND\t\t상태 like ?\t        \n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s4);
            prepareStatement.setString(2, s);
            prepareStatement.setString(3, "%" + s2 + "%");
            prepareStatement.setString(4, "%" + s3 + "%");
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvRegistry.RcvCodeUpdate():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvRegistry.RcvCodeUpdate():" + ex2.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public UM_RAM_RcvCode gccSelect(final Connection connection, final String s, final String s2, final String s3) {
        final UM_RAM_RcvCode um_RAM_RcvCode = new UM_RAM_RcvCode();
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        new StringBuffer().append(s2).append(" 00:00:01").toString();
        new StringBuffer().append(s3).append(" 23:59:59").toString();
        try {
            sb.append(" SELECT\t 행정표준기관코드,to_char(수신일자,'YYYYMMDD HH24:MI:SS'),DML구분,\n");
            sb.append(" \t\t to_char(생성일자,'YYYYMMDD HH24:MI:SS'),구분자,\n");
            sb.append(" \t\t 기관명,차수, 서열, 차상위기관코드,최상위기관코드,\n");
            sb.append(" \t\t 기관유형_대,기관유형_중,기관유형_소,기관장직급명,기관장직위코드,이전기관코드,\n");
            sb.append(" \t\t 상태 \n");
            sb.append(" FROM   사용_행정표준정보수신\t\t\t\t\t\n");
            sb.append(" WHERE  행정표준기관코드='" + s + "'\t\t\t\t\n");
            sb.append(" AND    상태='0'\t\t\t\t\t\t\t\t\n");
            sb.append(" AND    (DML구분='INS'or\tDML구분='UPD')\t\t\n");
            prepareStatement = connection.prepareStatement(sb.toString());
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_RAM_RcvCode.setgccCode(executeQuery.getString(1));
                um_RAM_RcvCode.setrcvDate(executeQuery.getString(2));
                um_RAM_RcvCode.setdmlFlag(executeQuery.getString(3));
                um_RAM_RcvCode.setcreateDate(executeQuery.getString(4));
                um_RAM_RcvCode.setgubun(executeQuery.getString(5));
                um_RAM_RcvCode.setgoNameFull(executeQuery.getString(6));
                um_RAM_RcvCode.setdegree(executeQuery.getString(7));
                um_RAM_RcvCode.setrank(executeQuery.getString(8));
                um_RAM_RcvCode.setfirstUpper(executeQuery.getString(9));
                um_RAM_RcvCode.setmostUpper(executeQuery.getString(10));
                um_RAM_RcvCode.settypeLarge(executeQuery.getString(11));
                um_RAM_RcvCode.settypeMiddle(executeQuery.getString(12));
                um_RAM_RcvCode.settypeSmall(executeQuery.getString(13));
                um_RAM_RcvCode.setmasterTask(executeQuery.getString(14));
                um_RAM_RcvCode.settaskCode(executeQuery.getString(15));
                um_RAM_RcvCode.setbeforeCode(executeQuery.getString(16));
                um_RAM_RcvCode.setstatusRcv(executeQuery.getString(17));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvCodeList.gccSelect():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvCodeList.gccSelect():" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
        }
        return um_RAM_RcvCode;
    }
    
    public UM_RAM_RcvCode gccSelect2(final Connection connection, final String s, final String s2, final String s3) {
        final UM_RAM_RcvCode um_RAM_RcvCode = new UM_RAM_RcvCode();
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        new StringBuffer().append(s2).append(" 00:00:01").toString();
        new StringBuffer().append(s3).append(" 23:59:59").toString();
        try {
            sb.append(" SELECT\t 행정표준기관코드,to_char(수신일자,'YYYYMMDD HH24:MI:SS'),DML구분,\n");
            sb.append(" \t\t to_char(생성일자,'YYYYMMDD HH24:MI:SS'),구분자,\n");
            sb.append(" \t\t 기관명,차수, 서열, 차상위기관코드,최상위기관코드,\n");
            sb.append(" \t\t 기관유형_대,기관유형_중,기관유형_소,기관장직급명,기관장직위코드,이전기관코드,\n");
            sb.append(" \t\t 상태 \n");
            sb.append(" FROM   사용_행정표준정보수신\t\t\t\t\t\n");
            sb.append(" WHERE  행정표준기관코드='" + s + "'\t\t\t\t\n");
            sb.append(" AND    상태='0'\t\t\t\t\t\t\t\t\n");
            sb.append(" AND    DML구분='DEL'\t\t\n");
            prepareStatement = connection.prepareStatement(sb.toString());
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_RAM_RcvCode.setgccCode(executeQuery.getString(1));
                um_RAM_RcvCode.setrcvDate(executeQuery.getString(2));
                um_RAM_RcvCode.setdmlFlag(executeQuery.getString(3));
                um_RAM_RcvCode.setcreateDate(executeQuery.getString(4));
                um_RAM_RcvCode.setgubun(executeQuery.getString(5));
                um_RAM_RcvCode.setgoNameFull(executeQuery.getString(6));
                um_RAM_RcvCode.setdegree(executeQuery.getString(7));
                um_RAM_RcvCode.setrank(executeQuery.getString(8));
                um_RAM_RcvCode.setfirstUpper(executeQuery.getString(9));
                um_RAM_RcvCode.setmostUpper(executeQuery.getString(10));
                um_RAM_RcvCode.settypeLarge(executeQuery.getString(11));
                um_RAM_RcvCode.settypeMiddle(executeQuery.getString(12));
                um_RAM_RcvCode.settypeSmall(executeQuery.getString(13));
                um_RAM_RcvCode.setmasterTask(executeQuery.getString(14));
                um_RAM_RcvCode.settaskCode(executeQuery.getString(15));
                um_RAM_RcvCode.setbeforeCode(executeQuery.getString(16));
                um_RAM_RcvCode.setstatusRcv(executeQuery.getString(17));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvCodeList.gccSelect():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvCodeList.gccSelect():" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
        }
        return um_RAM_RcvCode;
    }
    
    public void UserUpdate(final Connection connection, final String s, final String s2) {
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" update 사용_사용자 set \n");
            sb.append(" \t   마스터코드 = ? \n");
            sb.append(" WHERE  사용자ID = ? \n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s2);
            prepareStatement.setString(2, s);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvRegistry.UserUpdate():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvRegistry.UserUpdate():" + ex2.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public void UserUpdate2(final Connection connection, final String s, final String s2) {
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" update 사용_사용자 set \n");
            sb.append(" \t   마스터코드 = ? \n");
            sb.append(" WHERE  사용자ID = SOME( \n");
            sb.append("        select 사용자ID from 사용_사용자 \n");
            sb.append("        where 마스터코드 = ?) \n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s2);
            prepareStatement.setString(2, s);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvRegistry.UserUpdate():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvRegistry.UserUpdate():" + ex2.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public void UserVerfUpdate(final Connection connection, final String s) {
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" update 사용_사용자 set \n");
            sb.append(" \t   승인여부 = '9' \n");
            sb.append(" WHERE  마스터코드 = ? \n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvRegistry.UserUpdate():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvRegistry.UserUpdate():" + ex2.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public void firstUpperUpdate(final Connection connection, final String s, final String s2) {
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" update 사용_행정표준정보 set \n");
            sb.append(" \t   차상위기관코드 = ? \n");
            sb.append(" WHERE  행정표준기관코드 = ? \n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_RAM_RcvRegistry.firstUpperUpdate():" + ex.toString());
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("UM_RAM_RcvRegistry.firstUpperUpdate():" + ex2.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public void chglistUpdate(final Connection connection, final String s, final String s2) {
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" update 사용_행정표준정보 set \n");
            sb.append(" \t   변경일자 = sysdate, 변경유형구분 = ? \n");
            sb.append(" WHERE  행정표준기관코드 = ? \n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
            prepareStatement.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_RAM_RcvRegistry.chglistUpdate():" + ex.toString());
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("UM_RAM_RcvRegistry.chglistUpdate():" + ex2.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public String getZOrgCode(final Connection connection) {
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        try {
            String string = "";
            prepareStatement = connection.prepareStatement("select max(공공기관코드) from 사용_공공기관마스터 where 공공기관코드 like 'ZQ%' ");
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                string = executeQuery.getString(1);
                if (string == null) {
                    string = "";
                }
            }
            String string2;
            if (string.length() > 0) {
                string2 = "ZQ" + this.convertNum(String.valueOf(Integer.parseInt(string.substring(2)) + 1), 5);
            }
            else {
                string2 = "ZQ00001";
            }
            return string2;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_RAM_RcvRegistry.getZOrgCode():" + ex.toString());
            return "";
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("UM_RAM_RcvRegistry.getZOrgCode():" + ex2.toString());
            return "";
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
        }
    }
    
    public String convertNum(final String s, final int n) {
        String string = "";
        for (int i = 0; i < n - s.length(); ++i) {
            string += "0";
        }
        return string + s;
    }
}
