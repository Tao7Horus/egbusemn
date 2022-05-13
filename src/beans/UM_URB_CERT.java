package beans;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.Log;
import common.Trx;
import common.util.UM_COB_GTQ904;
import common.util.UM_COE_GTQ903;

public class UM_URB_CERT
{
  public void updateUseUser(String i01, String i02, String i03, String i04, String i05, String i06, String i072, String i09, String check1, String check2, String i101, String i102, String i103, String i104, String id, String upmuGubun, Connection conn)
    throws Exception
  {
    String sql = null;
    PreparedStatement pstmt = null;
    








    sql = " update UM_USER  set CHRGR_DEPART=?,     CHRGR_NM=?,     CHRGR_PHONE_NO=?, CHRGR_FAX=?, CHRGR_MOBILE=?,      CHRGR_EMAIL=?,   PASSWORD=?,     SWITCH_MNG_AUTHOR=?,     FA_YN=?,        PA_YN=?,   PAY_LINK_YN=?,   PAY_ID=?, PAY_SERVER_ID=?, MINISTRY_LINK_YN=?,      MINISTRY_ID=?,         MINISTRY_SERVER_ID=?, CITIS_LINK_YN=?,  CITIS_ID=?, CITIS_SERVER_ID=?,      INSTITU_WORK_CLS=?  where USER_ID='" + 
    




      id + "'";
    try
    {
      pstmt = conn.prepareStatement(sql);
      
      pstmt.setString(1, i01);
      pstmt.setString(2, i02);
      pstmt.setString(3, i03);
      pstmt.setString(4, i04);
      pstmt.setString(5, i05);
      pstmt.setString(6, i06);
      pstmt.setString(7, i072);
      pstmt.setString(8, i09);
      pstmt.setString(9, check1);
      pstmt.setString(10, check2);
      pstmt.setString(11, (i101.equals("")) && (i102.equals("")) ? "N" : "Y");
      pstmt.setString(12, i101);
      pstmt.setString(13, i102);
      pstmt.setString(14, i103.equals("") ? "N" : "Y");
      pstmt.setString(15, i103);
      pstmt.setString(16, "mofe");
      pstmt.setString(17, i104.equals("") ? "N" : "Y");
      pstmt.setString(18, i104);
      pstmt.setString(19, i104);
      pstmt.setString(20, upmuGubun);
      if (pstmt.executeUpdate() != 1) {
        throw new Exception(
          "Dữ liệu update trong bảng UM_User không phải là 1. Hãy kiểm tra lại dữ liệu.");
      }
    }
    finally
    {
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (Exception localException) {}
    }
    try
    {
      if (pstmt != null) {
        pstmt.close();
      }
    }
    catch (Exception localException1) {}
  }
  
  public void updateUseUser(String i01, String i02, String i03, String i04, String i05, String i06, String i072, String i09, String id, Connection conn)
    throws Exception
  {
    String sql = null;
    PreparedStatement pstmt = null;
    




    sql = " update UM_USER set CHRGR_DEPART=?, CHRGR_NM=?, CHRGR_PHONE_NO=?, CHRGR_FAX=?,  CHRGR_MOBILE=?, CHRGR_EMAIL=?, PASSWORD=?, SWITCH_MNG_AUTHOR=? where USER_ID='" + 
    

      id + "'";
    try
    {
      pstmt = conn.prepareStatement(sql);
      
      pstmt.setString(1, i01);
      pstmt.setString(2, i02);
      pstmt.setString(3, i03);
      pstmt.setString(4, i04);
      pstmt.setString(5, i05);
      pstmt.setString(6, i06);
      pstmt.setString(7, i072);
      pstmt.setString(8, i09);
      if (pstmt.executeUpdate() != 1) {
        throw new Exception(
          "Dữ liệu update trong bảng UM_User không phải là 1. Hãy kiểm tra lại dữ liệu.");
      }
    }
    finally
    {
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (Exception localException) {}
    }
    try
    {
      if (pstmt != null) {
        pstmt.close();
      }
    }
    catch (Exception localException1) {}
  }
  
  public void updateUseUserPasswd(String passwd, String id, Connection conn)
    throws Exception
  {
    String sql = null;
    PreparedStatement pstmt = null;
    

    sql = " update UM_USER set PASSWORD=? where USER_ID=?";
    try
    {
      pstmt = conn.prepareStatement(sql);
      
      pstmt.setString(1, passwd);
      pstmt.setString(2, id);
      if (pstmt.executeUpdate() != 1) {
        throw new Exception(
          "Dữ liệu thay đổi mật khẩu không phải là 1. ");
      }
    }
    finally
    {
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (Exception localException) {}
    }
    try
    {
      if (pstmt != null) {
        pstmt.close();
      }
    }
    catch (Exception localException1) {}
  }
  
  public int isDataExistOnGigwanMaster(String mCode, Connection conn)
    throws Exception
  {
    String sql = " SELECT COUNT(*) FROM UM_PUB_INSTITU_MAST WHERE INSTITU_CD='" + 
      mCode + "'";
    
    return new UM_COB_GTQ904().getCount(this, sql, conn);
  }
  
  public int isDataExistOnUserApproveTwo(String mCode, Connection conn)
    throws Exception
  {
    String sql = " SELECT COUNT(*) FROM UM_USER WHERE MAST_CD='" + mCode + 
      "' and PERMIT_YN='2'";
    return new UM_COB_GTQ904().getCount(this, sql, conn);
  }
  
  public int isDataExistOnCert(String DN, Connection conn)
    throws Exception
  {
    String sql = " SELECT COUNT(*) FROM UM_CERT_INFO WHERE CERT_NM='" + DN + 
      "'";
    
    return new UM_COB_GTQ904().getCount(this, sql, conn);
  }
  
  public int isDataExistOnSamePerson(String mCode, String person, Connection conn)
    throws Exception
  {
    String sql = "SELECT COUNT(*) FROM UM_USER WHERE MAST_CD='" + mCode + 
      "' and CHRGR_NM='" + person + "'  and PERMIT_YN='2'";
    return new UM_COB_GTQ904().getCount(this, sql, conn);
  }
  
  public int isDataExistOnJodalUpcheMaster(String mCode, Connection conn)
    throws Exception
  {
    String sql = "SELECT COUNT(*) FROM UM_SUPPLIER_ENTER_MAST where BIZ_REG_NO='" + 
      mCode + "' ";
    
    return new UM_COB_GTQ904().getCount(this, sql, conn);
  }
  
  public int isDataExistOnBichukUpcheMaster(String mCode, Connection conn)
    throws Exception
  {
    String sql = " SELECT COUNT(*) FROM UM_SUPPLIER_ENTER_MAST a, UM_ENTER_CLS b  WHERE a.BIZ_REG_NO = '" + 
    
      mCode + 
      "' " + 
      "   AND a.BIZ_REG_NO = b.BIZ_REG_NO " + 
      "   AND b.ENTER_CLS = '2' " + "   AND b.USE_YN = 'Y' ";
    return new UM_COB_GTQ904().getCount(this, sql, conn);
  }
  
  public int isDataExistOnNoBichukUpcheMaster(String mCode)
    throws Exception
  {
    Trx trx = null;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstm = null;
    int CNT = 0;
    try
    {
      String sql = " SELECT COUNT(*) FROM UM_SUPPLIER_ENTER_MAST a, UM_ENTER_CLS b  WHERE a.BIZ_REG_NO = '" + 
      
        mCode + 
        "' " + 
        "   AND a.BIZ_REG_NO = b.BIZ_REG_NO " + 
        "   AND b.ENTER_CLS = '2' " + 
        "   AND b.USE_YN = 'Y' " + 
        "   AND a.BID_ATTEND_QUALIFY_YN ='N' ";
      trx = new Trx(this, "USEMN");
      conn = trx.getConnection();
      pstm = conn.prepareStatement(sql);
      rs = pstm.executeQuery();
      pstm.clearParameters();
      while (rs.next()) {
        CNT = rs.getInt(1);
      }
    }
    catch (SQLException sqle)
    {
      Log.debug(
        "UM_URB_CERT.isDataExistOnNoBichukUpcheMaster('" + mCode + "'):" + sqle.toString());
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException1) {}
      }
      if (pstm != null) {
        try
        {
          pstm.close();
        }
        catch (Exception localException2) {}
      }
      if (conn != null) {
        try
        {
          trx.close();
        }
        catch (Exception localException3) {}
      }
    }
    catch (Exception exc)
    {
      Log.debug(
        "UM_URB_CERT.isDataExistOnNoBichukUpcheMaster('" + mCode + "'):" + exc.toString());
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException4) {}
      }
      if (pstm != null) {
        try
        {
          pstm.close();
        }
        catch (Exception localException5) {}
      }
      if (conn != null) {
        try
        {
          trx.close();
        }
        catch (Exception localException6) {}
      }
    }
    finally
    {
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException7) {}
      }
      if (pstm != null) {
        try
        {
          pstm.close();
        }
        catch (Exception localException8) {}
      }
      if (conn != null) {
        try
        {
          trx.close();
        }
        catch (Exception localException9) {}
      }
    }
    return CNT;
  }
  
  public int isDataExistOnUserCertD(String mCode, Connection conn)
    throws Exception
  {
    String sql = "SELECT COUNT(*) FROM UM_USER WHERE MAST_CD ='" + mCode + 
      "'";
    
    return new UM_COB_GTQ904().getCount(this, sql, conn);
  }
  
  public String getSaupjaNumber(String mCode, Connection conn)
    throws Exception
  {
    String sql = null;
    PreparedStatement pstmt = null;
    ResultSet rest = null;
    
    String result = null;
    

    sql = "select BIZ_REG_NO from UM_PUB_INSTITU_MAST where INSTITU_CD=?";
    try
    {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, mCode);
      rest = pstmt.executeQuery();
      if (rest.next())
      {
        result = rest.getString(1);
        if (rest.wasNull()) {
          result = null;
        }
      }
    }
    finally
    {
      try
      {
        if (rest != null) {
          rest.close();
        }
      }
      catch (Exception localException) {}
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (Exception localException1) {}
    }
    return result;
  }
  
  public String getTestOP(String bizregno, Connection conn)
    throws Exception
  {
    String sql = null;
    PreparedStatement pstmt = null;
    ResultSet rest = null;
    
    String result = "N";
    

    sql = "select TEST_OPTION_YN from UM_SUPPLIER_ENTER_MAST where BIZ_REG_NO =?";
    try
    {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, bizregno);
      rest = pstmt.executeQuery();
      if (rest.next())
      {
        result = rest.getString(1);
        if (rest.wasNull()) {
          result = "N";
        }
      }
    }
    finally
    {
      try
      {
        if (rest != null) {
          rest.close();
        }
      }
      catch (Exception localException) {}
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (Exception localException1) {}
    }
    return result;
  }
  
  public String getUserID(String mCode, Connection conn)
    throws Exception
  {
    String sql = null;
    PreparedStatement pstmt = null;
    ResultSet rest = null;
    
    String result = null;
    































    sql = " select max( USER_ID ) USER_ID   from UM_USER where MAST_CD='" + 
      mCode + 
      "' and   USER_ID like '%" + mCode + "%'";
    try
    {
      pstmt = conn.prepareStatement(sql);
      rest = pstmt.executeQuery();
      if ((!"".equals(rest)) && (rest.next()))
      {
        result = rest.getString(1);
        if ((result == null) || ("".equals(result)))
        {
          result = "C" + mCode + "G" + "0001";
        }
        else
        {
          int isG = result.lastIndexOf("G");
          if (isG > -1)
          {
            String res = "";
            int num = Integer.valueOf(result.substring(isG + 1))
              .intValue() + 1;
            
            int len = 4 - String.valueOf(num).length();
            for (int i = 0; i < len; i++) {
              res = res + "0";
            }
            result = result.substring(0, isG + 1) + res + num;
          }
          else
          {
            result = result + "0001";
          }
        }
      }
    }
    catch (Exception localException)
    {
      try
      {
        if (rest != null) {
          rest.close();
        }
      }
      catch (Exception localException1) {}
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (Exception localException2) {}
    }
    finally
    {
      try
      {
        if (rest != null) {
          rest.close();
        }
      }
      catch (Exception localException3) {}
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (Exception localException4) {}
    }
    if ((result == null) || ("".equals(result))) {
      result = "C" + mCode + "G" + "0001";
    }
    return result;
  }
  
  public String getUserUpcheID(String mCode, Connection conn)
    throws Exception
  {
    String sql = null;
    PreparedStatement pstmt = null;
    ResultSet rest = null;
    
    String result = null;
    








    sql = " select max( USER_ID ) USER_ID   from UM_USER where MAST_CD='" + 
      mCode + 
      "' and   USER_ID like '%" + mCode + "%'";
    try
    {
      pstmt = conn.prepareStatement(sql);
      

      rest = pstmt.executeQuery();
      if ((!"".equals(rest)) && (rest.next()))
      {
        result = rest.getString(1);
        if ((result == null) || ("".equals(result)))
        {
          result = "C" + mCode + "01";
        }
        else
        {
          String Chu2cuoi = result.substring(result.length() - 3, 
            result.length() - 1);
          if (Chu2cuoi != null)
          {
            if (Integer.valueOf(Chu2cuoi).intValue() + 1 < 10) {
              result = 
              

                "C" + mCode + "0" + (Integer.valueOf(Chu2cuoi).intValue() + 1);
            } else {
              result = 
              
                "C" + mCode + (Integer.valueOf(Chu2cuoi).intValue() + 1);
            }
          }
          else {
            result = "C" + mCode + "01";
          }
        }
      }
    }
    finally
    {
      try
      {
        if (rest != null) {
          rest.close();
        }
      }
      catch (Exception localException) {}
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (Exception localException1) {}
    }
    if ((result == null) || ("".equals(result))) {
      result = "C" + mCode + "01";
    }
    return result;
  }
  
  public void updateUserZzangReceive(String mCode, Connection conn)
    throws Exception
  {
    String sql = null;
    PreparedStatement pstmt = null;
    

    sql = "update UM_USER set MAST_RECV_YN='N' where MAST_CD=?";
    try
    {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, mCode);
      
      pstmt.executeUpdate();
    }
    finally
    {
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (Exception localException) {}
    }
  }
  
  public void insertUseUser(String id, String d01, String i072, String i02, String i01, String i03, String i05, String i04, String i06, String i103, String i101, String i102, String i08, String i09, String i104, String check1, String check2, String grp, String upmuGubun, Connection conn)
    throws Exception
  {
    String sql = null;
    PreparedStatement pstmt = null;
    if ("9999999".equals(d01)) {
      sql = 
      












        "insert into UM_USER (USER_ID, USER_CLS, MAST_CD, PASSWORD,CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_MOBILE,CHRGR_FAX, CHRGR_EMAIL, USER_GRP,CON_TYPE, PERMIT_YN, FIRST_REG_DT, MINISTRY_ID,PAY_ID, PAY_SERVER_ID, INSTITU_FUNC_APPLY_YN, MAST_RECV_YN,SWITCH_MNG_AUTHOR, IS_OLD_BID_SERVICE, PERMIT_BRANCH, PASSWORD_SEEDKEY, MINISTRY_LINK_YN,CITIS_LINK_YN, CITIS_ID, CITIS_SERVER_ID, PAY_LINK_YN, MINISTRY_SERVER_ID, FA_YN,  PA_YN, INSTITU_WORK_CLS)values(?, 'b', ?, ?,?, ?, ?, ?,?, ?,'" + grp + "'," + "'HH', '2', SYSDATE, ?," + "?, ?, '', ?," + "?, '', '', 'PASSWORDSEEDKEY', ?," + "?, ?, ?, ?, ?, ?, ?, ?)";
    } else {
      sql = 
      












        "insert into UM_USER (USER_ID, USER_CLS, MAST_CD, PASSWORD,CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_MOBILE,CHRGR_FAX, CHRGR_EMAIL, USER_GRP,CON_TYPE, PERMIT_YN, FIRST_REG_DT, MINISTRY_ID,PAY_ID, PAY_SERVER_ID, INSTITU_FUNC_APPLY_YN, MAST_RECV_YN,SWITCH_MNG_AUTHOR, IS_OLD_BID_SERVICE, PERMIT_BRANCH, PASSWORD_SEEDKEY, MINISTRY_LINK_YN,CITIS_LINK_YN, CITIS_ID, CITIS_SERVER_ID, PAY_LINK_YN, MINISTRY_SERVER_ID, FA_YN,  PA_YN, INSTITU_WORK_CLS)values(?, 'g', ?, ?,?, ?, ?, ?,?, ?,'" + grp + "'," + "'HH', '2', SYSDATE, ?," + "?, ?, '', ?," + "?, '', '', 'PASSWORDSEEDKEY', ?," + "?, ?, ?, ?, ?, ?, ?, ?)";
    }
    try
    {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, id);
      pstmt.setString(2, d01);
      pstmt.setString(3, i072);
      pstmt.setString(4, i02);
      pstmt.setString(5, i01);
      pstmt.setString(6, i03);
      pstmt.setString(7, i05);
      pstmt.setString(8, i04);
      pstmt.setString(9, i06);
      pstmt.setString(10, i103);
      pstmt.setString(11, i101);
      pstmt.setString(12, i102);
      pstmt.setString(13, i08);
      pstmt.setString(14, i09);
      pstmt.setString(15, i103.equals("") ? "N" : "Y");
      pstmt.setString(16, i104.equals("") ? "N" : "Y");
      pstmt.setString(17, i104);
      pstmt.setString(18, i104);
      pstmt.setString(19, (i101.equals("")) && (i102.equals("")) ? "N" : "Y");
      pstmt.setString(20, "mofe");
      pstmt.setString(21, check1);
      pstmt.setString(22, check2);
      pstmt.setString(23, upmuGubun);
      if (pstmt.executeUpdate() != 1) {
        throw new Exception("Đăng ký Chứng nhận số phát sinh");
      }
    }
    finally
    {
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (Exception localException) {}
    }
    try
    {
      if (pstmt != null) {
        pstmt.close();
      }
    }
    catch (Exception localException1) {}
  }
  
  public void insertUseUser_NEW(String test_op_yn, String id, String d01, String i072, String i02, String i01, String i03, String i05, String i04, String i06, String i103, String i101, String i102, String i08, String i09, String i104, String check1, String check2, String grp, String upmuGubun, Connection conn)
    throws Exception
  {
    String sql = null;
    PreparedStatement pstmt = null;
    if ("9999999".equals(d01)) {
      sql = 
      












        "insert into UM_USER (USER_ID, USER_CLS, MAST_CD, PASSWORD,CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_MOBILE,CHRGR_FAX, CHRGR_EMAIL, USER_GRP,CON_TYPE, PERMIT_YN, FIRST_REG_DT, MINISTRY_ID,PAY_ID, PAY_SERVER_ID, INSTITU_FUNC_APPLY_YN, MAST_RECV_YN,SWITCH_MNG_AUTHOR, IS_OLD_BID_SERVICE, PERMIT_BRANCH, PASSWORD_SEEDKEY, MINISTRY_LINK_YN,CITIS_LINK_YN, CITIS_ID, CITIS_SERVER_ID, PAY_LINK_YN, MINISTRY_SERVER_ID, FA_YN,  PA_YN, TEST_OPTION_YN, INSTITU_WORK_CLS)values(?, 'b', ?, ?,?, ?, ?, ?,?, ?,'" + grp + "'," + "'HH', '2', SYSDATE, ?," + "?, ?, '', ?," + "?, '', '', 'PASSWORDSEEDKEY', ?," + "?, ?, ?, ?, ?, ?, ?, ?, ?)";
    } else {
      sql = 
      












        "insert into UM_USER (USER_ID, USER_CLS, MAST_CD, PASSWORD,CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_MOBILE,CHRGR_FAX, CHRGR_EMAIL, USER_GRP,CON_TYPE, PERMIT_YN, FIRST_REG_DT, MINISTRY_ID,PAY_ID, PAY_SERVER_ID, INSTITU_FUNC_APPLY_YN, MAST_RECV_YN,SWITCH_MNG_AUTHOR, IS_OLD_BID_SERVICE, PERMIT_BRANCH, PASSWORD_SEEDKEY, MINISTRY_LINK_YN,CITIS_LINK_YN, CITIS_ID, CITIS_SERVER_ID, PAY_LINK_YN, MINISTRY_SERVER_ID, FA_YN,  PA_YN, TEST_OPTION_YN, INSTITU_WORK_CLS)values(?, 'g', ?, ?,?, ?, ?, ?,?, ?,'" + grp + "'," + "'HH', '2', SYSDATE, ?," + "?, ?, '', ?," + "?, '', '', 'PASSWORDSEEDKEY', ?," + "?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }
    try
    {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, id);
      pstmt.setString(2, d01);
      pstmt.setString(3, i072);
      pstmt.setString(4, i02);
      pstmt.setString(5, i01);
      pstmt.setString(6, i03);
      pstmt.setString(7, i05);
      pstmt.setString(8, i04);
      pstmt.setString(9, i06);
      pstmt.setString(10, i103);
      pstmt.setString(11, i101);
      pstmt.setString(12, i102);
      pstmt.setString(13, i08);
      pstmt.setString(14, i09);
      pstmt.setString(15, i103.equals("") ? "N" : "Y");
      pstmt.setString(16, i104.equals("") ? "N" : "Y");
      pstmt.setString(17, i104);
      pstmt.setString(18, i104);
      pstmt.setString(19, (i101.equals("")) && (i102.equals("")) ? "N" : "Y");
      pstmt.setString(20, "mofe");
      pstmt.setString(21, check1);
      pstmt.setString(22, check2);
      pstmt.setString(23, test_op_yn);
      pstmt.setString(24, upmuGubun);
      if (pstmt.executeUpdate() != 1) {
        throw new Exception("Đăng ký Chứng nhận số phát sinh");
      }
    }
    finally
    {
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (Exception localException) {}
    }
    try
    {
      if (pstmt != null) {
        pstmt.close();
      }
    }
    catch (Exception localException1) {}
  }
  
  public void insertUseUser(String id, String oper, String d04, String i072, String i02, String i01, String i03, String i05, String i04, String i06, String i08, String i09, String grp, Connection conn)
    throws Exception
  {
    PreparedStatement pstmt = null;
    StringBuffer sb = new StringBuffer();
    












    sb.append("insert into UM_USER (")
      .append("USER_ID, USER_CLS, MAST_CD, PASSWORD,")
      
      .append("CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_MOBILE,")
      
      .append("CHRGR_FAX, CHRGR_EMAIL, USER_GRP,")
      
      .append("CON_TYPE, PERMIT_YN, FIRST_REG_DT, MINISTRY_ID,")
      .append("PAY_ID, PAY_SERVER_ID, INSTITU_FUNC_APPLY_YN, MAST_RECV_YN,")
      .append("SWITCH_MNG_AUTHOR, IS_OLD_BID_SERVICE, PERMIT_BRANCH, PASSWORD_SEEDKEY, MINISTRY_LINK_YN,")
      .append("CITIS_LINK_YN, CITIS_ID, CITIS_SERVER_ID, PAY_LINK_YN, MINISTRY_SERVER_ID)")
      .append("values(?, ?, ?, ?,")
      .append("?, ?, ?, ?,")
      .append("?, ?, '" + grp + "',")
      .append("'HH', '2', SYSDATE, '',")
      .append("'', '', '', ?,?, ")
      .append("'', '', 'PASSWORD_SEEDKEY', '',")
      .append("'', '', '', '', 'mofe')");
    try
    {
      pstmt = conn.prepareStatement(sb.toString());
      pstmt.setString(1, id);
      pstmt.setString(2, oper);
      pstmt.setString(3, d04);
      pstmt.setString(4, i072);
      pstmt.setString(5, i02);
      pstmt.setString(6, i01);
      pstmt.setString(7, i03);
      pstmt.setString(8, i05);
      pstmt.setString(9, i04);
      pstmt.setString(10, i06);
      pstmt.setString(11, i08);
      pstmt.setString(12, i09);
      if (pstmt.executeUpdate() != 1) {
        throw new Exception("Đăng ký chứng nhận số phát sinh lỗi");
      }
    }
    finally
    {
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (Exception localException) {}
    }
    try
    {
      if (pstmt != null) {
        pstmt.close();
      }
    }
    catch (Exception localException1) {}
  }
  
  public void insertUseCert(String id, String DN1, String DN2, String CERT1, String CERT2, String CertFromDate, String CertToDate, Connection conn)
    throws Exception
  {
    String sql = null;
    String sql1 = null;String sql2 = null;
    
    PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;
    PreparedStatement pstmt2 = null;
    























    sql = " insert into UM_CERT_INFO  (USER_ID, TURN, CERT_NM, ENC_CERT_NM,   REG_DT, UPDATE_DT, LAST_LOGIN_DT,   AVAIL_PERIOD_START_DT, AVAIL_PERIOD_END_DT)  values (?, (select decode(max(TURN),NULL,1,max(TURN)+1) from UM_CERT_INFO where USER_ID =?), ?, ?, SYSDATE, '', '', to_date(?, 'yyyymmddhh24miss'), to_date(?, 'yyyymmddhh24miss'))";
    



    sql1 = " update  UM_CERT_INFO     set  CERT = ?   where  USER_ID = ? ";
    
    sql2 = " update  UM_CERT_INFO     set  ENC_CERT = ?   where  USER_ID = ? ";
    

    System.out.println("778:" + sql);
    try
    {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, id);
      pstmt.setString(2, id);
      pstmt.setString(3, DN1);
      pstmt.setString(4, DN2);
      












      pstmt.setString(5, CertFromDate);
      pstmt.setString(6, CertToDate);
      if (pstmt.executeUpdate() != 1) {
        throw new Exception(
          "Khi đăng ký chứng nhận số vào UM_CERT_INFO phát sinh lỗi");
      }
      pstmt1 = conn.prepareStatement(sql1);
      pstmt1.setCharacterStream(1, new StringReader(CERT1), 
        CERT1.length());
      pstmt1.setString(2, id);
      

      pstmt2 = conn.prepareStatement(sql2);
      pstmt2.setCharacterStream(1, new StringReader(CERT2), 
        CERT2.length());
      pstmt2.setString(2, id);
    }
    finally
    {
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (Exception localException) {}
      try
      {
        if (pstmt1 != null) {
          pstmt1.close();
        }
      }
      catch (Exception localException1) {}
      try
      {
        if (pstmt2 != null) {
          pstmt2.close();
        }
      }
      catch (Exception localException2) {}
    }
  }
  
  public int isPasswordOk(String id, String passwd, String sosok, Connection conn)
    throws Exception
  {
    String sql = "SELECT COUNT(*) FROM UM_USER WHERE USER_ID='" + id + 
      "' and PASSWORD='" + passwd + "' and USER_CLS='" + sosok + 
      "'";
    

    return new UM_COB_GTQ904().getCount(this, sql, conn);
  }
  
  public void insertCertLog(String DN, Connection conn)
    throws Exception
  {
    String sql = null;
    PreparedStatement pstmt = null;
    





    sql = "insert into UM_CERT_INFO_LOG (USER_ID, DELETE_DT, CERT_NM, ENC_CERT_NM, CERT, ENC_CERT, REG_DT, UPDATE_DT) select USER_ID, SYSDATE, CERT_NM, ENC_CERT_NM, CERT, ENC_CERT, REG_DT, UPDATE_DT from UM_CERT_INFO where CERT_NM=?";
    try
    {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, DN);
      if (pstmt.executeUpdate() != 1) {
        throw new Exception(
          "Thêm thông tin vào UM_CERT_INFO_LOG không phải là 1");
      }
    }
    finally
    {
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (Exception localException) {}
    }
    try
    {
      if (pstmt != null) {
        pstmt.close();
      }
    }
    catch (Exception localException1) {}
  }
  
  public void insertCertLog(String id, String DN, Connection conn)
    throws Exception
  {
    String sql = null;
    PreparedStatement pstmt = null;
    





    sql = "insert into UM_CERT_INFO_LOG (USER_ID, DELETE_DT, CERT_NM, ENC_CERT_NM, CERT, ENC_CERT, REG_DT, UPDATE_DT) values (?, SYSDATE, ?, '', '', '', SYSDATE, SYSDATE)";
    try
    {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, id);
      pstmt.setString(2, DN);
      if (pstmt.executeUpdate() != 1) {
        throw new Exception(
          "Thên thông tin vào UM_CERT_INFO_LOG không phải là 1");
      }
    }
    finally
    {
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (Exception localException) {}
    }
    try
    {
      if (pstmt != null) {
        pstmt.close();
      }
    }
    catch (Exception localException1) {}
  }
  
  public void deleteCert(String DN, Connection conn)
    throws Exception
  {
    String sql = null;
    PreparedStatement pstmt = null;
    

    sql = "delete from UM_CERT_INFO where CERT_NM=?";
    try
    {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, DN);
      if (pstmt.executeUpdate() != 1) {
        throw new Exception(
          "Xóa thông tin trong bảng UM_CERT_INFO không phải là 1");
      }
    }
    finally
    {
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (Exception localException) {}
    }
    try
    {
      if (pstmt != null) {
        pstmt.close();
      }
    }
    catch (Exception localException1) {}
  }
  
  public UM_COE_GTQ903[] getUpcheInfo(String DN1, String id, String d04, String mcode, Connection conn)
    throws Exception
  {
    String sql = "select a.USER_ID, 'PPS', b.BIZ_NM, '', concat(decode(b.ADDR,'*','',b.ADDR),decode(b.DETAIL_ADDR,'*','',b.DETAIL_ADDR)),  substrb(b.PHONE_NO,1,20), substrb(b.FAX,1,20), a.CHRGR_NM, a.CHRGR_DEPART, substrb(a.CHRGR_PHONE_NO,1,20),  substrb(a.CHRGR_FAX,1,20), a.CHRGR_EMAIL, to_char(a.FIRST_REG_DT,'yyyymmddhh24mi'), 'c', '" + 
    

      DN1 + 
      "',  " + 
      "'2', to_char(sysdate,'yyyymmddhh24mi'), '" + 
      mcode + 
      "', a.PASSWORD, a.MAST_CD, " + 
      "substrb(c.REPR_NM,1,20), c.REPR_IDENT_NO, '', '', '',  " + 
      "'', a.SWITCH_MNG_AUTHOR, a.USER_GRP, a.CON_TYPE, 'NONE',  " + 
      "'NONE'   " + 
      "from  " + 
      "(select USER_ID, USER_CLS, MAST_CD, PASSWORD, CHRGR_NM,  " + 
      "CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_FAX, CHRGR_EMAIL,  " + 
      "USER_GRP, CON_TYPE, PERMIT_YN, FIRST_REG_DT, SWITCH_MNG_AUTHOR  " + 
      "from UM_USER  " + 
      "where USER_ID='" + 
      id + 
      "') a,   " + 
      "(select BIZ_NM, ADDR, DETAIL_ADDR, PHONE_NO,FAX   " + 
      "from UM_SUPPLIER_ENTER_MAST  " + 
      "where BIZ_REG_NO='" + 
      d04 + 
      "') b, " + 
      "(select BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, MAST_REPR_YN " + 
      "from UM_REPR where BIZ_REG_NO='" + 
      d04 + 
      "' and MAST_REPR_YN='Y') c";
    
    return new UM_COB_GTQ904().getList(this, sql, conn);
  }
  
  public void updateModifyDate(String id, String DN, Connection conn)
    throws Exception
  {
    String sql = null;
    PreparedStatement pstmt = null;
    




    sql = " update UM_CERT_INFO set    UPDATE_DT = sysdate,         MOD_REQ_YN = 'Y'  where  USER_ID = ? and CERT_NM = ? ";
    try
    {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, id);
      pstmt.setString(2, DN);
      if (pstmt.executeUpdate() != 1) {
        throw new Exception(
          "Cập nhật thông tin Chứng nhận số không phải là 1.");
      }
      Log.debug("Update 0 id : " + id + ", DN : " + DN);
    }
    finally
    {
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (Exception localException) {}
    }
  }
  
  public void updateModifyDate1(String id, String DN, Connection conn)
    throws Exception
  {
    String sql = null;
    PreparedStatement pstmt = null;
    



    sql = " update UM_CERT_INFO set    UPDATE_DT = sysdate  where  USER_ID = ? and CERT_NM = ? ";
    try
    {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, id);
      pstmt.setString(2, DN);
      if (pstmt.executeUpdate() != 1) {
        throw new Exception(
          "Cập nhật thông tin Chứng nhận số không phải là 1.");
      }
    }
    finally
    {
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
      }
      catch (Exception localException) {}
    }
    try
    {
      if (pstmt != null) {
        pstmt.close();
      }
    }
    catch (Exception localException1) {}
  }
  
  public int isDataExistOnRecPubCert(String institucd, String certName, Connection conn)
		    throws Exception
  {
    String sql = " SELECT COUNT(*) FROM UM_REC_PUB_INSTITU_CERT a WHERE a.INSTITU_CD = ? AND a.CERT_NM = ? ";
    PreparedStatement prepareStatement = null;
    ResultSet executeQuery = null;
    int int1 = 0;
    try {
        
        prepareStatement = conn.prepareStatement(sql);
        prepareStatement.setString(1, institucd);
        prepareStatement.setString(2, certName);
        executeQuery = prepareStatement.executeQuery();

        if (executeQuery.next()) {
            int1 = executeQuery.getInt(1);
        }
        return int1;
    }
    catch (Exception ex) {        
        throw new Exception(ex.getMessage());
    }
    finally {
        try {
            if (executeQuery != null) {
                executeQuery.close();
            }
        }
        catch (Exception ex2) {}
        try {
            if (prepareStatement != null) {
                prepareStatement.close();
            }
        }
        catch (Exception ex3) {}        
    }
  }
  
  public String getUserClsFromUserId(String userId, Connection connection) throws Exception  {
	  
	  String result = "";
	  boolean isFreshConnection = false;
	  Trx trx = null;
	  PreparedStatement ps = null;
	  String sql = "";
	  ResultSet rs = null;
	  
	  if(userId == null || userId.trim().length() == 0) {
		  return result;
	  }
	  
	  if(connection == null) {
		  try {
			  trx = new Trx(this, "USEMN");
			  connection = trx.getConnection();
			  isFreshConnection = true;
		  } catch(Exception ex) {
			  Log.debug(this.getClass().getName() + ".getUserClsFromUserId(" + userId + ") => Exception: " + ex.toString());
			  throw new Exception("Không tạo được kết nối đến Database");
		  }
	  }
	  
	  try {
		  sql =  "select user_cls FROM USEMN.UM_USER U3 where U3.USER_ID = ?";
		  ps = connection.prepareStatement(sql);
		  ps.setString(1, userId);
		  rs = ps.executeQuery();
		  if(rs.next()) {
			  result = rs.getString(1);
		  }
	  } catch(Exception ex) {
		  Log.debug(this.getClass().getName() + ".getUserClsFromUserId(" + userId + ") => Exception: " + ex.toString());
		  throw new Exception("Lỗi xử lý dữ liệu");
	  } finally {
		  if(rs != null) {
			  try {
				rs.close();
			} catch (SQLException e) {
				Log.debug(this.getClass().getName() + ".getUserClsFromUserId(" + userId + ") => Exception: " + e.toString());
				e.printStackTrace();
			}
		  }
		  if (ps != null) {
			  try {
				ps.close();
			} catch (SQLException e) {
				Log.debug(this.getClass().getName() + ".getUserClsFromUserId(" + userId + ") => Exception: " + e.toString());
				e.printStackTrace();
			}
		  }
		  if(isFreshConnection) {
			  if(connection != null) {
				  try {
					connection.close();
				} catch (SQLException e) {
					Log.debug(this.getClass().getName() + ".getUserClsFromUserId(" + userId + ") => Exception: " + e.toString());
					e.printStackTrace();
				}
			  }
		  }
	  }
	  
	  return result;
  }
  
}
