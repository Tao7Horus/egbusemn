// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import common.util.UM_COB_GTQ904;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.Log;
import common.Trx;

public class Common_ComBo
{
    public String getCode(final String opt, final String name, final String code, final String code_nm) throws Exception {
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sqlQ = new StringBuffer();
        Connection con = null;
        Trx trx = null;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sqlQ.append("select CD, CD_NM from SYN_PUB_CODE \n");
            sqlQ.append(" where CD_CLS = '" + code + "' and USE_YN not in ('N') \n");
            sqlQ.append(" ORDER BY CD ASC \n");
            psmt = con.prepareStatement(sqlQ.toString());
            rs = psmt.executeQuery();
            return this.format(rs, opt, name, code, code_nm);
        }
        catch (SQLException se) {
            Log.debug("SQLException : Common_ComBo.java's getCode(opt=" + opt + "/name=" + name + "/code=" + code + "/code_nm" + code_nm + "/): message = " + se.getMessage());
            throw new Exception(se.getMessage());
        }
        catch (Exception e) {
            Log.debug("Exception : Common_ComBo.java's getCode(opt=" + opt + "/name=" + name + "/code=" + code + "/code_nm" + code_nm + "/ ): message = " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        finally {
            sqlQ.setLength(0);
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
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public String getCode_5(final String opt, final String name, final String code, final String code_nm) throws Exception {
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sqlQ = new StringBuffer();
        Connection con = null;
        Trx trx = null;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sqlQ.append("\tSELECT CD, CD_NM FROM SYN_PUB_CODE\t\t\t\t\t\t\t\t\n");
            sqlQ.append("\tWHERE CD_CLS = '" + code + "' and USE_YN not in ('N')\t\t\t\n");
            sqlQ.append("\tAND CD NOT IN ('05','07')\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sqlQ.append("\tORDER BY CD ASC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            psmt = con.prepareStatement(sqlQ.toString());
            rs = psmt.executeQuery();
            return this.format(rs, opt, name, code, code_nm);
        }
        catch (SQLException se) {
            Log.debug("SQLException : Common_ComBo.java's getCode_5(opt=" + opt + "/name=" + name + "/code=" + code + "/code_nm" + code_nm + "/): message = " + se.getMessage());
            throw new Exception(se.getMessage());
        }
        catch (Exception e) {
            Log.debug("Exception : Common_ComBo.java's getCode_5(opt=" + opt + "/name=" + name + "/code=" + code + "/code_nm" + code_nm + "/ ): message = " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        finally {
            sqlQ.setLength(0);
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
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public String getCode_1(final String opt, final String name, final String code, final String code_nm) throws Exception {
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sqlQ = new StringBuffer();
        Connection con = null;
        Trx trx = null;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sqlQ.append(" SELECT CD, CD_NM FROM SYN_PUB_CODE\t\t\t\t\t\t\t\n");
            sqlQ.append(" WHERE CD_CLS = '" + code + "' and USE_YN not in ('N')\t\t\n");
            sqlQ.append(" AND CD NOT IN('15')\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sqlQ.append(" ORDER BY CD ASC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            psmt = con.prepareStatement(sqlQ.toString());
            rs = psmt.executeQuery();
            return this.format(rs, opt, name, code, code_nm);
        }
        catch (SQLException se) {
            Log.debug("SQLException : Common_ComBo.java's getCode_1(opt=" + opt + "/name=" + name + "/code=" + code + "/code_nm" + code_nm + "/): message = " + se.getMessage());
            throw new Exception(se.getMessage());
        }
        catch (Exception e) {
            Log.debug("Exception : Common_ComBo.java's getCode_1(opt=" + opt + "/name=" + name + "/code=" + code + "/code_nm" + code_nm + "/ ): message = " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        finally {
            sqlQ.setLength(0);
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
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public String getCode_4(final String opt, final String name, final String code, final String code_nm) throws Exception {
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sqlQ = new StringBuffer();
        Connection con = null;
        Trx trx = null;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sqlQ.append("SELECT CD, CD_NM FROM SYN_PUB_CODE\n");
            sqlQ.append("WHERE CD_CLS = '" + code + "' \n");
            sqlQ.append("and USE_YN not in ('N') ");
            sqlQ.append("and CD in('21','22','23','24','25','35') \n");
            sqlQ.append("ORDER BY CD ASC \n");
            psmt = con.prepareStatement(sqlQ.toString());
            rs = psmt.executeQuery();
            return this.format(rs, opt, name, code, code_nm);
        }
        catch (SQLException se) {
            Log.debug("SQLException : Common_ComBo.java's getCode_4(opt=" + opt + "/name=" + name + "/code=" + code + "/code_nm" + code_nm + "/): message = " + se.getMessage());
            throw new Exception(se.getMessage());
        }
        catch (Exception e) {
            Log.debug("Exception : Common_ComBo.java's getCode_4(opt=" + opt + "/name=" + name + "/code=" + code + "/code_nm" + code_nm + "/ ): message = " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        finally {
            sqlQ.setLength(0);
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
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public String getCode_2(final String opt, final String name, final String code, final String code_nm) throws Exception {
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sqlQ = new StringBuffer();
        Connection con = null;
        Trx trx = null;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sqlQ.append("SELECT CD, CD_NM FROM SYN_PUB_CODE\n");
            sqlQ.append("WHERE CD_CLS = '" + code + "' \n");
            sqlQ.append("and USE_YN not in ('N') ");
            sqlQ.append("and CD in('12','21','22','23','24','25','32','33','35','38','39') \n");
            sqlQ.append("ORDER BY CD ASC \n");
            psmt = con.prepareStatement(sqlQ.toString());
            rs = psmt.executeQuery();
            return this.format(rs, opt, name, code, code_nm);
        }
        catch (SQLException se) {
            Log.debug("SQLException : Common_ComBo.java's getCode(opt=" + opt + "/name=" + name + "/code=" + code + "/code_nm" + code_nm + "/): message = " + se.getMessage());
            throw new Exception(se.getMessage());
        }
        catch (Exception e) {
            Log.debug("Exception : Common_ComBo.java's getCode(opt=" + opt + "/name=" + name + "/code=" + code + "/code_nm" + code_nm + "/ ): message = " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        finally {
            sqlQ.setLength(0);
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
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public String getCode_3(final String opt, final String name, final String code, final String code_nm) throws Exception {
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sqlQ = new StringBuffer();
        Connection con = null;
        Trx trx = null;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sqlQ.append("SELECT CD, CD_NM FROM SYN_PUB_CODE\n");
            sqlQ.append("WHERE CD_CLS = '" + code + "' \n");
            sqlQ.append("and USE_YN not in ('N') ");
            sqlQ.append("and CD in('12','21','22','23','24','25','32','33','35','38','39','XX') \n");
            sqlQ.append("ORDER BY CD ASC \n");
            psmt = con.prepareStatement(sqlQ.toString());
            rs = psmt.executeQuery();
            return this.format(rs, opt, name, code, code_nm);
        }
        catch (SQLException se) {
            Log.debug("SQLException : Common_ComBo.java's getCode(opt=" + opt + "/name=" + name + "/code=" + code + "/code_nm" + code_nm + "/): message = " + se.getMessage());
            throw new Exception(se.getMessage());
        }
        catch (Exception e) {
            Log.debug("Exception : Common_ComBo.java's getCode(opt=" + opt + "/name=" + name + "/code=" + code + "/code_nm" + code_nm + "/ ): message = " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        finally {
            sqlQ.setLength(0);
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
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    private String format(final ResultSet rs, final String opt, final String name, final String code, final String code_nm) throws SQLException {
        final StringBuffer sb = new StringBuffer();
        String Svalue = "";
        String Scode = "";
        boolean flg = true;
        if (opt.equals("M")) {
            sb.append("<select type=select name='" + name + "'>");
            if (code.equals("GZA") && flg) {
                sb.append("<option value='' selected>없음</option>");
            }
            while (rs.next()) {
                Scode = rs.getString("CD");
                Svalue = rs.getString("CD_NM");
                if (code_nm.equals(Scode)) {
                    flg = false;
                    sb.append("<option value='" + Scode + "' selected>").append(Svalue).append("</option>");
                }
                else {
                    sb.append("<option value='" + Scode + "'>").append(Svalue).append("</option>");
                }
            }
            sb.append("</select>");
        }
        else if (opt.equals("S")) {
            sb.append("<select type=select name='" + name + "'>");
            while (rs.next()) {
                Scode = rs.getString("CD");
                Svalue = rs.getString("CD_NM");
                sb.append("<option value='" + Scode + "'>").append(Svalue).append("</option>");
            }
            sb.append("</select>");
        }
        else if (opt.equals("T")) {
            while (rs.next()) {
                Scode = rs.getString("CD");
                Svalue = rs.getString("CD_NM");
                if (code_nm.equals(Scode)) {
                    sb.append(Svalue);
                }
            }
        }
        else if (opt.equals("B")) {
            sb.append("<select name=\"" + name + "\">");
            sb.append("<option value='' selected>없음</option>");
            while (rs.next()) {
                Scode = rs.getString("물품분류번호");
                Svalue = rs.getString("분류명");
                sb.append("<option value='" + Scode + " " + Svalue + "'>").append(String.valueOf(Scode) + " " + Svalue).append("</option>");
            }
            sb.append("</select>");
        }
        else if (opt.equals("TFH")) {
            sb.append("<select name='" + name + "'>");
            sb.append("<option value=''>선택</option>");
            while (rs.next()) {
                Scode = rs.getString("CD");
                Svalue = rs.getString("CD_NM");
                sb.append("<option value='" + Scode + "'>").append(Scode).append("</option>");
            }
            sb.append("</select>");
        }
        else if (opt.equals("G")) {
            sb.append("<select name='" + name + "'>");
            while (rs.next()) {
                Scode = rs.getString("CD");
                Svalue = rs.getString("CD_NM");
                sb.append("<option value='" + Scode + "'>").append(Scode).append("</option>");
            }
            sb.append("</select>");
        }
        else if (opt.equals("TFHM")) {
            sb.append("<select type=select name='" + name + "'>");
            sb.append("<option value=''>선택</option>");
            while (rs.next()) {
                Scode = rs.getString("CD");
                Svalue = rs.getString("CD_NM");
                if (code_nm.equals(Scode)) {
                    flg = false;
                    sb.append("<option value='" + Scode + "' selected>").append(Scode).append("</option>");
                }
                else {
                    sb.append("<option value='" + Scode + "'>").append(Scode).append("</option>");
                }
            }
            sb.append("</select>");
        }
        final String result = sb.toString();
        sb.setLength(0);
        return result;
    }
    
    public String selectOpt(String selectedCode, final String codeGubun, final String objname, String javascript, String detailyn, String allyn, String nullyn) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String result = null;
        Trx resource = null;
        if (selectedCode == null) {
            selectedCode = " ";
        }
        if (detailyn == null) {
            detailyn = "N";
        }
        if (allyn == null) {
            allyn = "N";
        }
        if (nullyn == null) {
            nullyn = "N";
        }
        while (true) {
            if (javascript == null) {
                javascript = " ";
                try {
                    resource = new Trx(this, "usemn");
                    conn = resource.getConnection();
                    stmt = conn.createStatement();
                    result = "<select name=\"" + objname + "\"" + javascript + ">" + "\n";
                    if (detailyn.equalsIgnoreCase("Y")) {
                        rs = stmt.executeQuery("select CD, substrb(nvl(CD_NM,' '),1), substrb(nvl(CD_NM2,' '),1) from SYN_PUB_CODE where CD_CLS = '" + codeGubun + "' and USE_YN = 'Y' order by CD");
                        if (nullyn.equalsIgnoreCase("Y")) {
                            result = String.valueOf(result) + "<option value=\"\">\n";
                        }
                        if (allyn.equalsIgnoreCase("Y")) {
                            result = String.valueOf(result) + "<option value=\"\">전체\n";
                        }
                        while (rs.next()) {
                            if (selectedCode.equals(rs.getString(1))) {
                                result = String.valueOf(result) + "<option value=\"" + rs.getString(1) + "\" selected>" + rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "\n";
                            }
                            else {
                                result = String.valueOf(result) + "<option value=\"" + rs.getString(1) + "\">" + rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "\n";
                            }
                        }
                    }
                    else {
                        rs = stmt.executeQuery("select CD, substrb(nvl(CD_NM,' '),1) from SYN_PUB_CODE where CD_CLS = '" + codeGubun + "' and USE_YN = 'Y' order by CD");
                        if (nullyn.equalsIgnoreCase("Y")) {
                            result = String.valueOf(result) + "<option value=\"\">\n";
                        }
                        if (allyn.equalsIgnoreCase("Y")) {
                            result = String.valueOf(result) + "<option value=\"\">전체\n";
                        }
                        while (rs.next()) {
                            if (selectedCode.equals(rs.getString(1))) {
                                result = String.valueOf(result) + "<option value=\"" + rs.getString(1) + "\" selected>" + rs.getString(1) + "  " + rs.getString(2) + "\n";
                            }
                            else {
                                result = String.valueOf(result) + "<option value=\"" + rs.getString(1) + "\">" + rs.getString(1) + "  " + rs.getString(2) + "\n";
                            }
                        }
                    }
                    result = String.valueOf(result) + "</select>";
                }
                catch (SQLException exc) {
                    Log.debug("Common_ComBo.selectOpt() CD_CLS[" + codeGubun + "] :" + exc.toString());
                    throw new Exception(exc.getMessage());
                }
                catch (Exception exc2) {
                    Log.debug("Common_ComBo.selectOpt() CD_CLS[" + codeGubun + "] :" + exc2.toString());
                    throw new Exception(exc2.getMessage());
                }
                finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        }
                        catch (Exception ex) {}
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (Exception ex2) {}
                    }
                    if (conn != null) {
                        try {
                            resource.close();
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
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex5) {}
                }
                if (conn != null) {
                    try {
                        resource.close();
                    }
                    catch (Exception ex6) {}
                }
                return result;
            }
            continue;
        }
    }
    
    public String selectOpt_1(String selectedCode, final String codeGubun, final String objname, String javascript, String detailyn, String allyn, String nullyn) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String result = null;
        Trx resource = null;
        if (selectedCode == null) {
            selectedCode = " ";
        }
        if (detailyn == null) {
            detailyn = "N";
        }
        if (allyn == null) {
            allyn = "N";
        }
        if (nullyn == null) {
            nullyn = "N";
        }
        while (true) {
            if (javascript == null) {
                javascript = " ";
                try {
                    resource = new Trx(this, "usemn");
                    conn = resource.getConnection();
                    stmt = conn.createStatement();
                    result = "<select name=\"" + objname + "\"" + javascript + ">" + "\n";
                    if (detailyn.equalsIgnoreCase("Y")) {
                        rs = stmt.executeQuery("select CD, substrb(nvl(CD_NM,' '),1), substrb(nvl(CD_NM2,' '),1) from SYN_PUB_CODE where CD_CLS = '" + codeGubun + "' AND CD IN('32','38','24','23','21','12','22','00','35','39','33','25','91','XX') order by CD");
                        if (nullyn.equalsIgnoreCase("Y")) {
                            result = String.valueOf(result) + "<option value=\"\">\n";
                        }
                        if (allyn.equalsIgnoreCase("Y")) {
                            result = String.valueOf(result) + "<option value=\"\">전체\n";
                        }
                        while (rs.next()) {
                            if (selectedCode.equals(rs.getString(1))) {
                                result = String.valueOf(result) + "<option value=\"" + rs.getString(1) + "\" selected>" + rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "\n";
                            }
                            else {
                                result = String.valueOf(result) + "<option value=\"" + rs.getString(1) + "\">" + rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "\n";
                            }
                        }
                    }
                    else {
                        rs = stmt.executeQuery("select CD, substrb(nvl(CD_NM,' '),1) from SYN_PUB_CODE where CD_CLS = '" + codeGubun + "' AND CD IN('32','38','24','23','21','12','22','00','35','39','33','25','91','XX') order by CD");
                        if (nullyn.equalsIgnoreCase("Y")) {
                            result = String.valueOf(result) + "<option value=\"\">\n";
                        }
                        if (allyn.equalsIgnoreCase("Y")) {
                            result = String.valueOf(result) + "<option value=\"\">전체\n";
                        }
                        while (rs.next()) {
                            if (selectedCode.equals(rs.getString(1))) {
                                result = String.valueOf(result) + "<option value=\"" + rs.getString(1) + "\" selected>" + rs.getString(1) + "  " + rs.getString(2) + "\n";
                            }
                            else {
                                result = String.valueOf(result) + "<option value=\"" + rs.getString(1) + "\">" + rs.getString(1) + "  " + rs.getString(2) + "\n";
                            }
                        }
                    }
                    result = String.valueOf(result) + "</select>";
                }
                catch (SQLException exc) {
                    Log.debug("Common_ComBo.selectOpt() CD_CLS[" + codeGubun + "] :" + exc.toString());
                    throw new Exception(exc.getMessage());
                }
                catch (Exception exc2) {
                    Log.debug("Common_ComBo.selectOpt() CD_CLS[" + codeGubun + "] :" + exc2.toString());
                    throw new Exception(exc2.getMessage());
                }
                finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        }
                        catch (Exception ex) {}
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (Exception ex2) {}
                    }
                    if (conn != null) {
                        try {
                            resource.close();
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
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex5) {}
                }
                if (conn != null) {
                    try {
                        resource.close();
                    }
                    catch (Exception ex6) {}
                }
                return result;
            }
            continue;
        }
    }
    
    public String getCodeName(final String codeGubun, final String code) throws Exception {
        final String sql = "select CD_NM from SYN_PUB_CODE where CD_CLS='" + codeGubun + "' and CD='" + code + "'";
        return new UM_COB_GTQ904().getSqlDataOne(this, sql, null);
    }
    
    public String getBichukMulpum(final String opt, final String bichukMulpum, final String code, final String code_nm) throws Exception {
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sqlQ = new StringBuffer();
        Connection con = null;
        Trx trx = null;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sqlQ.append(" SELECT 물품분류번호, 분류명 FROM 사용_비축품목 \n");
            sqlQ.append(" WHERE 사용여부 = 'Y' \n");
            sqlQ.append(" ORDER BY 분류명 ASC \n");
            psmt = con.prepareStatement(sqlQ.toString());
            rs = psmt.executeQuery();
            return this.format(rs, opt, bichukMulpum, code, code_nm);
        }
        catch (SQLException se) {
            Log.debug("SQLException : Common_ComBo.java's getBichukMulpum(bichukMulpum=" + bichukMulpum + "/): message = " + se.getMessage());
            throw new Exception(se.getMessage());
        }
        catch (Exception e) {
            Log.debug("Exception : Common_ComBo.java's getBichukMulpum(bichukMulpum=" + bichukMulpum + "/): message = " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        finally {
            sqlQ.setLength(0);
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
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
}
