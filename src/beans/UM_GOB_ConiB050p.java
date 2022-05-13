// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import entity.UM_GOE_ConiA031b;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import common.ComStr;
import java.util.Vector;
import entity.UM_GOE_ConiA030b;

public class UM_GOB_ConiB050p
{
    public final int MODE_DP = 100;
    
    public UM_GOE_ConiA030b[] select_list(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8) throws Exception {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final Vector vector = new Vector<UM_GOE_ConiA030b>();
        final StringBuffer sb = new StringBuffer();
        String string = "";
        final String string2 = s4 + " 00:00:00";
        final String string3 = s5 + " 23:59:59";
        try {
            sb.append(" SELECT BIZ_REG_NO,  INPUT_DT, BIZ_NM, ADDR,                                     \t\t\t       \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t       DETAIL_ADDR,      REPR_NM,     IDENT_NO,                                                     \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t       PUNISH_COUNT,\t\tINSTITU_NM,                                                                       \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t       PUNISH_DT,\t\tEXPIRE_DT,\tSUSPEND_CLS,\t  INSTITU_CD,\t\t    RESUMPTION_CLS,\tRELEASE_CLS, n           \t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" FROM(\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\tSELECT\tBIZ_REG_NO,\tINPUT_DT,BIZ_NM,ADDR,        \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t  \t\tDETAIL_ADDR,\t\tPUNISH_COUNT,\t\tREPR_NM,IDENT_NO,\t\t                             \t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t  \t\tINSTITU_NM,\t\trownum n,\t\tSUSPEND_CLS,                                                 \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t  \t\tPUNISH_DT,\tEXPIRE_DT,     \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t  \t\tINSTITU_CD, \tRESUMPTION_CLS ,RELEASE_CLS\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\tFROM (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\tSELECT\tA.BIZ_REG_NO,\tTO_CHAR(A.INPUT_DT,'DD/MM/YYYY') INPUT_DT,\t    A.BIZ_NM,\t\tA.ADDR,\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t  \t\tA.DETAIL_ADDR,\t\tA.PUNISH_COUNT,\t\t\t\t\t    \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t  \t\tA.INSTITU_NM,\t\tA.SUSPEND_CLS,\t\t                    \t\t\t\t\t\t\t\t\t    \t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t  \t\tTO_CHAR(A.PUNISH_DT, 'DD/MM/YYYY') PUNISH_DT,\tTO_CHAR(A.EXPIRE_DT, 'DD/MM/YYYY') EXPIRE_DT,\t\t\t\t\n");
            sb.append("\t\t  \t\tA.INSTITU_CD, \tA.RESUMPTION_CLS, D.REPR_NM,D.IDENT_NO , A.RELEASE_CLS\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t   FROM\tUM_VIOLATE_COM \tA,UM_VIOLATE_REPR D\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("       WHERE\tA.BIZ_REG_NO = D.BIZ_REG_NO\tAND A.PUNISH_COUNT = D.PUNISH_COUNT\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            if (s != null && s.length() >= 1) {
                sb.append(" AND\t\tA.BIZ_REG_NO like '" + ComStr.replace(s, "-", "") + "%'\t                \t\t\t\t\t\n");
            }
            if (s2 != null && s2.length() >= 1) {
                sb.append(" AND\t\tA.INSTITU_CD = '" + s2 + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            if (s.equals("") && s2.equals("")) {
                sb.append(" AND\tA.INSTITU_CD != 'ZY00480'\t\t\t\n");
                sb.append(" AND\tA.BIZ_REG_NO NOT LIKE '999%'\t\n");
            }
            if (!s3.equals("")) {
                if (s7 != null && s7.equals("1")) {
                    sb.append(" AND\t\tD.REPR_NM like '%'||?||'%'                                                                 \n");
                }
                else if (s7 != null && s7.equals("2")) {
                    sb.append(" AND\t\tD.IDENT_NO like ?||'%'                                                             \n");
                }
            }
            if (!s4.equals("") && !s5.equals("")) {
                if (s8 != null && s8.equals("1")) {
                    sb.append(" AND\t\tA.INPUT_DT BETWEEN TO_DATE('" + string2 + "', 'DD/MM/YYYY HH24:MI:SS') AND TO_DATE('" + string3 + "', 'DD/MM/YYYY HH24:MI:SS')\t\n");
                }
                else if (s8 != null && s8.equals("2")) {
                    sb.append("\t\t   AND  A.PUNISH_DT >= TO_DATE('" + string2 + "', 'DD/MM/YYYY HH24:MI:SS') AND A.EXPIRE_DT <= TO_DATE('" + string3 + "',   'DD/MM/YYYY HH24:MI:SS')    \n");
                }
            }
            sb.append("  order by A.INPUT_DT desc                                                                                   \t\n");
            sb.append("\t ) \t\t                                                                                                        \n");
            sb.append(" ) where\tn between (((" + n + " - 1) * " + n2 + ")+1) and (" + n + " * " + n2 + ") \t                    \n");
            string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            if (!s3.equals("")) {
                prepareStatement.setString(1, s3);
            }
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiA030b um_GOE_ConiA030b = new UM_GOE_ConiA030b();
                um_GOE_ConiA030b.setunpairNo(executeQuery.getString(1));
                um_GOE_ConiA030b.settoday(executeQuery.getString(2));
                um_GOE_ConiA030b.setupcheNm(executeQuery.getString(3));
                um_GOE_ConiA030b.setbonsaAddr(executeQuery.getString(4));
                um_GOE_ConiA030b.setbonsaAddrNa(executeQuery.getString(5));
                um_GOE_ConiA030b.setdaepyoNm(executeQuery.getString(6));
                um_GOE_ConiA030b.setdaepyoJumin(executeQuery.getString(7));
                um_GOE_ConiA030b.setpunishmentCount(executeQuery.getString(8));
                um_GOE_ConiA030b.setkigwanNm(executeQuery.getString(9));
                um_GOE_ConiA030b.setpunishmentStart(executeQuery.getString(10));
                um_GOE_ConiA030b.setpunishmentEnd(executeQuery.getString(11));
                um_GOE_ConiA030b.setstopGubun(executeQuery.getString(12));
                um_GOE_ConiA030b.setkigwanCode(executeQuery.getString(13));
                um_GOE_ConiA030b.setrestartGubun(executeQuery.getString(14));
                um_GOE_ConiA030b.setpunishmentCancelGubun(executeQuery.getString(15));
                vector.addElement(um_GOE_ConiA030b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiB050p.select_list() : sql : " + string + "::: " + ex.toString());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiB050p.select_list() : sql : " + string + "::: " + ex2.toString());
            throw new Exception(ex2.getMessage());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_GOE_ConiA030b[] array = new UM_GOE_ConiA030b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public UM_GOE_ConiA030b[] Modify_list(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final Vector vector = new Vector<UM_GOE_ConiA030b>();
        final StringBuffer sb = new StringBuffer();
        String string = "";
        try {
            sb.append(" SELECT A.BIZ_REG_NO,    A.INPUT_DT,      A.BIZ_NM,        A.ADDR,\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t       A.DETAIL_ADDR,        A.REPR_NM,      A.IDENT_NO,  A.CONTR_TYPE,\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t       A.PUNISH_COUNT,\t\t\tA.INSTITU_NM,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t       A.PUNISH_DT,\t\tA.EXPIRE_DT,\t A.SUSPEND_CLS,\t  A.INSTITU_CD,\t\tA.RESUMPTION_CLS,\tA.INPUT_UM_ID,  n\t\t\t\n");
            sb.append(" FROM\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t(SELECT\tA.BIZ_REG_NO,\tTO_CHAR(A.INPUT_DT,'DD/MM/YYYY') INPUT_DT,\tA.BIZ_NM,\t\tA.ADDR,\t\t\t\t\t\t\n");
            sb.append("\t\t  \t\tA.DETAIL_ADDR,\t\tD.REPR_NM,\t\t\tD.IDENT_NO,\t\t\tA.CONTR_TYPE,\tA.PUNISH_COUNT,\t\t\t\t\t\n");
            sb.append("\t\t  \t\tA.INSTITU_NM,\t\trownum n,\t\t\tA.SUSPEND_CLS,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t  \t\tTO_CHAR(A.PUNISH_DT, 'DD/MM/YYYY') PUNISH_DT,\tTO_CHAR(A.EXPIRE_DT, 'DD/MM/YYYY') EXPIRE_DT,\t\t\t\t\n");
            sb.append("\t\t  \t\tA.INSTITU_CD, \tA.RESUMPTION_CLS,  A.INPUT_UM_ID  \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t   FROM\tUM_VIOLATE_COM \tA,  UM_VIOLATE_REPR D,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t(SELECT\tA.BIZ_REG_NO,\tTO_CHAR(A.INPUT_DT,'DD/MM/YYYY') INPUT_DT,\t    A.BIZ_NM,\t\tA.ADDR,\t\t\n");
            sb.append("\t\t  \t\tA.DETAIL_ADDR,\t\tD.REPR_NM,\t\t\tD.IDENT_NO,\t\t    A.PUNISH_COUNT,\t\t\t\t\t    \n");
            sb.append("\t\t  \t\tA.INSTITU_NM,\t\tA.SUSPEND_CLS,\t\t                    \t\t\t\t\t\t\t\t\t    \n");
            sb.append("\t\t  \t\tTO_CHAR(A.PUNISH_DT, 'DD/MM/YYYY') PUNISH_DT,\tTO_CHAR(A.EXPIRE_DT, 'DD/MM/YYYY') EXPIRE_DT,\t\n");
            sb.append("\t\t  \t\tA.INSTITU_CD, \tA.RESUMPTION_CLS\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t   FROM\tUM_VIOLATE_COM \tA,  UM_VIOLATE_REPR D\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("       WHERE\tA.BIZ_REG_NO = D.BIZ_REG_NO\tAND A.PUNISH_COUNT = D.PUNISH_COUNT\t\t\t\t\t\t            \n");
            sb.append("         AND A.RELEASE_CLS = 'N'\t\t                                            \t\t\t\t            \n");
            if (s != null && s.length() >= 1) {
                sb.append(" AND\t\tA.BIZ_REG_NO like '" + ComStr.replace(s, "-", "") + "%'\t                \t\t\t\n");
            }
            sb.append("  order by A.INPUT_DT desc                                                                                   \n");
            sb.append("     ) B                                                                                                     \n");
            sb.append(" WHERE\tA.BIZ_REG_NO = D.BIZ_REG_NO                                                                     \n");
            sb.append("   AND   A.BIZ_REG_NO = B.BIZ_REG_NO                                                                     \n");
            sb.append("   AND   A.PUNISH_COUNT = D.PUNISH_COUNT                                                                                 \n");
            sb.append("   AND   A.PUNISH_COUNT = B.PUNISH_COUNT                                                                                 \n");
            sb.append("   AND   A.RELEASE_CLS = 'N'                                                                                        \n");
            if (s2.equals("Name")) {
                sb.append(" AND\t\tD.REPR_NM like '%'||?||'%'\t\t\t\t\t\t\t\t                                            \n");
            }
            else if (s2.equals("JuminNo")) {
                sb.append(" AND\t\tD.IDENT_NO like ?||'%'\t\t\t\t\t\t\t                                            \n");
            }
            if (s != null && s.length() >= 1) {
                sb.append(" AND\t\tA.BIZ_REG_NO like '" + s + "%'\t\t\t\t\t\t                                            \n");
            }
            sb.append("     AND\t\tA.INSTITU_CD = '" + s5 + "'                                                                                \n");
            sb.append("\t\tAND\t\trownum < = (" + n + " * " + n2 + ")\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t) A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\t\tn BETWEEN (((" + n + " - 1) * " + n2 + ")+1) AND (" + n + " * " + n2 + ")\t\t        \t\t\t\t\n");
            string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            if (!s2.equals("")) {
                prepareStatement.setString(1, s3);
            }
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiA030b um_GOE_ConiA030b = new UM_GOE_ConiA030b();
                um_GOE_ConiA030b.setunpairNo(executeQuery.getString(1));
                um_GOE_ConiA030b.settoday(executeQuery.getString(2));
                um_GOE_ConiA030b.setupcheNm(executeQuery.getString(3));
                um_GOE_ConiA030b.setbonsaAddr(executeQuery.getString(4));
                um_GOE_ConiA030b.setbonsaAddrNa(executeQuery.getString(5));
                um_GOE_ConiA030b.setdaepyoNm(executeQuery.getString(6));
                um_GOE_ConiA030b.setdaepyoJumin(executeQuery.getString(7));
                um_GOE_ConiA030b.setnotifyKind(executeQuery.getString(8));
                um_GOE_ConiA030b.setpunishmentCount(executeQuery.getString(9));
                um_GOE_ConiA030b.setkigwanNm(executeQuery.getString(10));
                um_GOE_ConiA030b.setpunishmentStart(executeQuery.getString(11));
                um_GOE_ConiA030b.setpunishmentEnd(executeQuery.getString(12));
                um_GOE_ConiA030b.setstopGubun(executeQuery.getString(13));
                um_GOE_ConiA030b.setkigwanCode(executeQuery.getString(14));
                um_GOE_ConiA030b.setrestartGubun(executeQuery.getString(15));
                um_GOE_ConiA030b.setfirstId(executeQuery.getString(16));
                vector.addElement(um_GOE_ConiA030b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiB050p.Modify_list() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiB050p.Modify_list() : sql : " + string + "::: " + ex2.toString());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_GOE_ConiA030b[] array = new UM_GOE_ConiA030b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public UM_GOE_ConiA030b[] Stop_list(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final Vector vector = new Vector<UM_GOE_ConiA030b>();
        final StringBuffer sb = new StringBuffer();
        String string = "";
        final String string2 = s4 + " 00:00:00";
        final String string3 = s5 + " 23:59:59";
        try {
            sb.append(" SELECT A.BIZ_REG_NO,    A.INPUT_DT,      A.BIZ_NM,        A.ADDR,\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t       A.DETAIL_ADDR,        A.REPR_NM,      A.IDENT_NO,  A.CONTR_TYPE,\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t       A.PUNISH_COUNT,\t\t\tA.INSTITU_NM,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t       A.PUNISH_DT,\t\tA.EXPIRE_DT,\t A.SUSPEND_CLS,\t  A.INSTITU_CD,\t\tA.RESUMPTION_CLS,\tA.INPUT_UM_ID,  n\t\t\t\n");
            sb.append(" FROM\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t(SELECT\tA.BIZ_REG_NO,\tTO_CHAR(A.INPUT_DT,'DD/MM/YYYY') INPUT_DT,\tA.BIZ_NM,\t\tA.ADDR,\t\t\t\t\t\t\n");
            sb.append("\t\t  \t\tA.DETAIL_ADDR,\t\tD.REPR_NM,\t\t\tD.IDENT_NO,\t\t\tA.CONTR_TYPE,\tA.PUNISH_COUNT,\t\t\t\t\t\n");
            sb.append("\t\t  \t\tA.INSTITU_NM,\t\trownum n,\t\t\tA.SUSPEND_CLS,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t  \t\tTO_CHAR(A.PUNISH_DT, 'DD/MM/YYYY') PUNISH_DT,\tTO_CHAR(A.EXPIRE_DT, 'DD/MM/YYYY') EXPIRE_DT,\t\t\t\t\n");
            sb.append("\t\t  \t\tA.INSTITU_CD, \tA.RESUMPTION_CLS,  A.INPUT_UM_ID  \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t   FROM\tUM_VIOLATE_COM \tA,  UM_VIOLATE_REPR D\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\tA.BIZ_REG_NO = D.BIZ_REG_NO\tAND A.PUNISH_COUNT = D.PUNISH_COUNT\t\t\t\t\t\t                            \n");
            if (s != null && s.length() >= 1) {
                sb.append(" AND\t\tA.BIZ_REG_NO like '" + ComStr.replace(s, "-", "") + "%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            if (s3 != null && s3.length() >= 1) {
                sb.append(" AND\t\tD.REPR_NM like '%'||?||'%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            if (s4 != null && s4.length() >= 1) {
                sb.append(" AND\t\tA.INPUT_DT BETWEEN TO_DATE('" + string2 + "', 'DD/MM/YYYY HH24:MI:SS') AND TO_DATE('" + string3 + "', 'DD/MM/YYYY HH24:MI:SS')\t\n");
            }
            if (s2 != null && s2.length() > 3) {
                sb.append(" AND\t\tA.CONTR_TYPE = '" + s2 + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            sb.append("\t\tAND\t\trownum < = (" + n + " * " + n2 + ")\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("     AND\t\tA.INSTITU_CD = '" + s7 + "'                                                                                \n");
            sb.append("\t\t) A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\t\tn BETWEEN (((" + n + " - 1) * " + n2 + ")+1) AND (" + n + " * " + n2 + ")\t\t        \t\t\t\t\n");
            sb.append("  ORDER BY a.INPUT_DT desc                                                                                 \t\t\t\t\n");
            string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            if (s3 != null && s3.length() >= 1) {
                prepareStatement.setString(1, s3);
            }
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiA030b um_GOE_ConiA030b = new UM_GOE_ConiA030b();
                um_GOE_ConiA030b.setunpairNo(executeQuery.getString(1));
                um_GOE_ConiA030b.settoday(executeQuery.getString(2));
                um_GOE_ConiA030b.setupcheNm(executeQuery.getString(3));
                um_GOE_ConiA030b.setbonsaAddr(executeQuery.getString(4));
                um_GOE_ConiA030b.setbonsaAddrNa(executeQuery.getString(5));
                um_GOE_ConiA030b.setdaepyoNm(executeQuery.getString(6));
                um_GOE_ConiA030b.setdaepyoJumin(executeQuery.getString(7));
                um_GOE_ConiA030b.setnotifyKind(executeQuery.getString(8));
                um_GOE_ConiA030b.setpunishmentCount(executeQuery.getString(9));
                um_GOE_ConiA030b.setkigwanNm(executeQuery.getString(10));
                um_GOE_ConiA030b.setpunishmentStart(executeQuery.getString(11));
                um_GOE_ConiA030b.setpunishmentEnd(executeQuery.getString(12));
                um_GOE_ConiA030b.setstopGubun(executeQuery.getString(13));
                um_GOE_ConiA030b.setkigwanCode(executeQuery.getString(14));
                um_GOE_ConiA030b.setrestartGubun(executeQuery.getString(15));
                um_GOE_ConiA030b.setfirstId(executeQuery.getString(16));
                vector.addElement(um_GOE_ConiA030b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiB050p.Stop_list() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiB050p.Stop_list() : sql : " + string + "::: " + ex2.toString());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_GOE_ConiA030b[] array = new UM_GOE_ConiA030b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Historycount(final String s, final String s2, final String s3, final String s4, final String s5) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        int int1 = 0;
        try {
            sb.append("SELECT\tCOUNT(*)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("   FROM\tUM_VIOLATE_COM_HIST\tA,  UM_VIOLATE_REPR_HIST D\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\tA.BIZ_REG_NO = D.BIZ_REG_NO\tAND A.PUNISH_COUNT = D.PUNISH_COUNT AND A.UPDATE_DT = D.UPDATE_DT\tAND A.RELEASE_CLS = 'N' \n");
            if (s2.equals("Name")) {
                sb.append(" AND\t\tD.REPR_NM like '%'||?||'%'\t\t\t\t\t\t\t\t\n");
            }
            else if (s2.equals("JuminNo")) {
                sb.append(" AND\t\tD.IDENT_NO like ?||'%'\t\t\t\t\t\t\t\n");
            }
            if (s.length() >= 1) {
                sb.append(" AND\t\tA.BIZ_REG_NO like '" + s + "%'\t\t\t\t\t\t\n");
            }
            sb.append("   AND\t\tA.INSTITU_CD = '" + s5 + "'                                \n");
            string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            if (!s2.equals("")) {
                prepareStatement.setString(1, s3);
            }
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiB050p.Historycount() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiB050p.Historycount() : sql : " + string + "::: " + ex2.toString());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    public UM_GOE_ConiA030b[] History_list(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5) {
        Trx trx = null;
        Connection connection = null;
        Object[] array = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final Vector vector = new Vector<UM_GOE_ConiA030b>();
        String s6 = null;
        try {
            s6 = "\n select BIZ_REG_NO, UPDATE_DT, BIZ_NM, ADDR,\n \t\tDETAIL_ADDR, REPR_NM, IDENT_NO, CONTR_TYPE,PUNISH_COUNT,\n \t\tINSTITU_NM, PUNISH_DT, EXPIRE_DT,\n \t\tINSTITU_CD, UPDATE_UM_ID\n from (\n \tselect  BIZ_REG_NO, UPDATE_DT, BIZ_NM, ADDR,\n \t\t\tDETAIL_ADDR, REPR_NM, IDENT_NO, PUNISH_COUNT, \n \t\t\tINSTITU_NM, SUSPEND_CLS, PUNISH_DT, EXPIRE_DT,\n \t\t\tINSTITU_CD, RESUMPTION_CLS, CONTR_TYPE,UPDATE_UM_ID,rownum t\n \tfrom (\n \t\tSELECT\tA.BIZ_REG_NO,\tTO_CHAR(A.UPDATE_DT,'DD/MM/YYYY HH24:MI:SS') UPDATE_DT,\t    A.BIZ_NM,\t\tA.ADDR,\n \t\t  \t\tA.DETAIL_ADDR,\t\tD.REPR_NM,\t\t\tD.IDENT_NO,\t\t    A.PUNISH_COUNT,\n \t\t  \t\tA.INSTITU_NM,\t\tA.SUSPEND_CLS,\n \t\t  \t\tTO_CHAR(A.PUNISH_DT, 'DD/MM/YYYY') PUNISH_DT,\tTO_CHAR(A.EXPIRE_DT, 'DD/MM/YYYY') EXPIRE_DT,\n \t\t  \t\tA.INSTITU_CD, \tA.RESUMPTION_CLS, A.CONTR_TYPE, A.UPDATE_UM_ID\n \t\tFROM\tUM_VIOLATE_COM_HIST A,  UM_VIOLATE_REPR_HIST D\n \t\tWHERE\tA.BIZ_REG_NO = D.BIZ_REG_NO\n \t\tAND A.PUNISH_COUNT = D.PUNISH_COUNT\n \t\tAND A.UPDATE_DT = D.UPDATE_DT\n \t\tAND A.RELEASE_CLS = 'N'\n \t\tAND\tA.INSTITU_CD = '" + s5 + "'";
            if (s2.equals("Name")) {
                s6 += "\n AND D.REPR_NM like '%'||?||'%'";
            }
            if (s2.equals("JuminNo")) {
                s6 += "\n AND D.IDENT_NO like ?||'%'";
            }
            if (s != null && s.length() >= 1) {
                s6 = s6 + "\n AND A.BIZ_REG_NO like '" + s + "%'";
            }
            s6 = s6 + "\n order by 2 desc\n \t)\n ) where t >" + Integer.toString(n2 * (n - 1)) + "\tand\tt < " + Integer.toString(n2 * n + 1);
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s6);
            if (!s2.equals("")) {
                prepareStatement.setString(1, s3);
            }
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiA030b um_GOE_ConiA030b = new UM_GOE_ConiA030b();
                um_GOE_ConiA030b.setunpairNo(executeQuery.getString(1));
                um_GOE_ConiA030b.settoday(executeQuery.getString(2));
                um_GOE_ConiA030b.setupcheNm(executeQuery.getString(3));
                um_GOE_ConiA030b.setbonsaAddr(executeQuery.getString(4));
                um_GOE_ConiA030b.setbonsaAddrNa(executeQuery.getString(5));
                um_GOE_ConiA030b.setdaepyoNm(executeQuery.getString(6));
                um_GOE_ConiA030b.setdaepyoJumin(executeQuery.getString(7));
                um_GOE_ConiA030b.setnotifyKind(executeQuery.getString(8));
                um_GOE_ConiA030b.setpunishmentCount(executeQuery.getString(9));
                um_GOE_ConiA030b.setkigwanNm(executeQuery.getString(10));
                um_GOE_ConiA030b.setpunishmentStart(executeQuery.getString(11));
                um_GOE_ConiA030b.setpunishmentEnd(executeQuery.getString(12));
                um_GOE_ConiA030b.setkigwanCode(executeQuery.getString(13));
                um_GOE_ConiA030b.setfirstId(executeQuery.getString(14));
                vector.addElement(um_GOE_ConiA030b);
            }
            array = new UM_GOE_ConiA030b[vector.size()];
            vector.copyInto(array);
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiB050p.History_list() : sql : " + s6 + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiB050p.History_list() : sql : " + s6 + "::: " + ex2.toString());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return (UM_GOE_ConiA030b[])array;
    }
    
    public UM_GOE_ConiA030b select(final String s, final String s2, final String s3, final String s4) throws Exception {
        Trx trx = null;
        Connection connection = null;
        UM_GOE_ConiA030b um_GOE_ConiA030b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        try {
            sb.append(" SELECT\tA.BIZ_REG_NO, TO_CHAR(A.INPUT_DT, 'DD/MM/YYYY') INPUT_DT, A.DOC_NO, A.CONTR_NO, A.CONTR_NO_ORDER, A.BID_NM,\t\t\t\t\t\t\t\t\t\t\t\t\t   \n");
            sb.append(" \t\t        A.BID_NO, A.CORP_REG_NO, A.BIZ_NM, A.ZIP_CD, A.ADDR,\tA.DETAIL_ADDR,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \n");
            sb.append("\t  \t\t    A.CONTR_TYPE, A.BIZ_CAT, A.LICENSE_REG_NO, A.SUSPEND_CLS, TO_CHAR(A.SUSPEND_DT, 'DD/MM/YYYY') SUSPEND_DT,\t A.SUSPEND_RSON,\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \n");
            sb.append("\t  \t\t    A.PUNISH_BASIS1, A.PUNISH_BASIS2, A.PUNISH_BY_LAW,\tTO_CHAR(A.PUNISH_DT, 'DD/MM/YYYY') PUNISH_DT,\tTO_CHAR(A.EXPIRE_DT, 'DD/MM/YYYY') EXPIRE_DT,\t\t\t   \n");
            sb.append("\t  \t\t    TO_CHAR(A.CANCEL_DT, 'DD/MM/YYYY') CANCEL_DT, A.PUNISH_RSON, A.PUNISH_COUNT, \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \n");
            sb.append("\t\t\t    A.INSTITU_NM,\t\tA.PUNISH_INSTITU_CD_CLS, A.PUNISH_INSTITU_CD, A.PUNISH_PERIOD, A.INSTITU_DIRECTOR_NM, A.CHRG_NM, A.CHRG_PHONE_NO, A.INSTITU_CD,\t\t\t\t\t\t\t\t\t\t\t   \n");
            sb.append("\t\t\t    TO_CHAR(A.RESUMPTION_DT, 'DD/MM/YYYY') RESUMPTION_DT,\t A.RESUMPTION_RSON, TO_CHAR(A.RELEASE_DT, 'DD/MM/YYYY') RELEASE_DT, A.RELEASE_RSON, A.NOTE, A.BID_TURN_NO,\t\t\t\t   \n");
            sb.append("\t\t\t    A.RESUMPTION_CLS, A.RELEASE_CLS,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \n");
            sb.append("\t\t\t    TO_CHAR(A.BID_DOC_DT, 'DD/MM/YYYY') BID_DOC_DT, TO_CHAR(A.CONTR_DT, 'DD/MM/YYYY') CONTR_DT,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \n");
            sb.append("\t\t\t    ( SELECT TO_CHAR(MAX(UPDATE_DT),'DD/MM/YYYY hh24:mi:ss') FROM UM_VIOLATE_COM_HIST B\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \n");
            sb.append("\t\t\t      WHERE B.BIZ_REG_NO = '" + ComStr.replace(s, "-", "") + "' AND B.PUNISH_COUNT = " + s4 + " AND B.HIST_CLS = '0') STOP_DT,\t\t   \n");
            sb.append("\t\t\t    ( SELECT TO_CHAR(MAX(UPDATE_DT),'DD/MM/YYYY hh24:mi:ss') FROM UM_VIOLATE_COM_HIST B\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \n");
            sb.append("\t\t\t      WHERE B.BIZ_REG_NO = '" + ComStr.replace(s, "-", "") + "' AND B.PUNISH_COUNT = " + s4 + " AND B.HIST_CLS = '1') RE_PUNISH_DT,\t\t   \n");
            sb.append("\t\t\t    ( SELECT TO_CHAR(MAX(UPDATE_DT),'DD/MM/YYYY hh24:mi:ss') FROM UM_VIOLATE_COM_HIST B\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \n");
            sb.append("\t\t\t      WHERE B.BIZ_REG_NO = '" + ComStr.replace(s, "-", "") + "' AND B.PUNISH_COUNT = " + s4 + " AND B.HIST_CLS = '2') CANCEL_PUNISH_DT\t\t   \n");
            sb.append(" FROM\tUM_VIOLATE_COM \tA\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \n");
            sb.append(" WHERE\tA.BIZ_REG_NO = '" + ComStr.replace(s, "-", "") + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \n");
            sb.append(" AND\t\tA.PUNISH_COUNT = " + s4 + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \n");
            string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            if (executeQuery.next()) {
                um_GOE_ConiA030b = new UM_GOE_ConiA030b();
                um_GOE_ConiA030b.setunpairNo(executeQuery.getString(1));
                um_GOE_ConiA030b.settoday(executeQuery.getString(2));
                um_GOE_ConiA030b.setdocNo(executeQuery.getString(3));
                um_GOE_ConiA030b.setcompactNo(executeQuery.getString(4));
                um_GOE_ConiA030b.setcompactNoDiffer(executeQuery.getString(5));
                um_GOE_ConiA030b.setnotifyNm(executeQuery.getString(6));
                um_GOE_ConiA030b.setnotifyNo(executeQuery.getString(7));
                um_GOE_ConiA030b.setlawNo(executeQuery.getString(8));
                um_GOE_ConiA030b.setupcheNm(executeQuery.getString(9));
                um_GOE_ConiA030b.setbonsapostNo(executeQuery.getString(10));
                um_GOE_ConiA030b.setbonsaAddr(executeQuery.getString(11));
                um_GOE_ConiA030b.setbonsaAddrNa(executeQuery.getString(12));
                um_GOE_ConiA030b.setnotifyKind(executeQuery.getString(13));
                um_GOE_ConiA030b.setbusinItem(executeQuery.getString(14));
                um_GOE_ConiA030b.setlicenseNo(executeQuery.getString(15));
                um_GOE_ConiA030b.setstopGubun(executeQuery.getString(16));
                um_GOE_ConiA030b.setstopDay(executeQuery.getString(17));
                um_GOE_ConiA030b.setstopSayu(executeQuery.getString(18));
                um_GOE_ConiA030b.setpunishmentBasic(executeQuery.getString(19));
                um_GOE_ConiA030b.setpunishmentBasicCode(executeQuery.getString(20));
                um_GOE_ConiA030b.setdipunishmentBasic(executeQuery.getString(21));
                um_GOE_ConiA030b.setpunishmentStart(executeQuery.getString(22));
                um_GOE_ConiA030b.setpunishmentEnd(executeQuery.getString(23));
                um_GOE_ConiA030b.setcancelDay(executeQuery.getString(24));
                um_GOE_ConiA030b.setcode(executeQuery.getString(25));
                um_GOE_ConiA030b.setpunishmentCount(executeQuery.getString(26));
                um_GOE_ConiA030b.setkigwanNm(executeQuery.getString(27));
                um_GOE_ConiA030b.setpunishmentGubun(executeQuery.getString(28));
                um_GOE_ConiA030b.setpunishmentCode(executeQuery.getString(29));
                um_GOE_ConiA030b.setpunishmentPeriod(executeQuery.getString(30));
                um_GOE_ConiA030b.setkigwanDaepyoNm(executeQuery.getString(31));
                um_GOE_ConiA030b.setchargeNm(executeQuery.getString(32));
                um_GOE_ConiA030b.setchargeTel((executeQuery.getString(33) == null) ? "" : executeQuery.getString(33));
                um_GOE_ConiA030b.setkigwanCode(executeQuery.getString(34));
                um_GOE_ConiA030b.setrestartDay(executeQuery.getString(35));
                um_GOE_ConiA030b.setrestartSayu(executeQuery.getString(36));
                um_GOE_ConiA030b.setpunishmentCancelDay(executeQuery.getString(37));
                um_GOE_ConiA030b.setpunishmentCancelSayu(executeQuery.getString(38));
                um_GOE_ConiA030b.setgita(executeQuery.getString(39));
                um_GOE_ConiA030b.setnotifyDiffer(executeQuery.getString(40));
                um_GOE_ConiA030b.setrestartGubun(executeQuery.getString(41));
                um_GOE_ConiA030b.setpunishmentCancelGubun(executeQuery.getString(42));
                um_GOE_ConiA030b.setnotifyDate(executeQuery.getString(43));
                um_GOE_ConiA030b.setcompactNoDate(executeQuery.getString(44));
                um_GOE_ConiA030b.setinputstopDay(executeQuery.getString(45));
                um_GOE_ConiA030b.setinputrestartDay(executeQuery.getString(46));
                um_GOE_ConiA030b.setinputpunishmentCancelDay(executeQuery.getString(47));
                return um_GOE_ConiA030b;
            }
            throw new Exception("Không có dữ liệu");
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiB050p.select() : sql : " + string + "::: " + ex.toString());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiB050p.select() : sql : " + string + "::: " + ex2.toString());
            throw new Exception(ex2.getMessage());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return um_GOE_ConiA030b;
    }
    
    public UM_GOE_ConiA030b selectHistory(final String s, final String s2, final String s3, final String s4) throws Exception {
        Trx trx = null;
        Connection connection = null;
        UM_GOE_ConiA030b um_GOE_ConiA030b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        try {
            sb.append(" SELECT\tA.BIZ_REG_NO,\tA.UPDATE_DT,\t\t\tA.DOC_NO,\t\tA.CORP_REG_NO,\t\tA.BIZ_NM,\t\tA.ZIP_CD,\t\t    \t\n");
            sb.append("\t  \t\tA.ADDR,\t            A.DETAIL_ADDR,\t\tA.CONTR_TYPE,\tA.BIZ_CAT,\t\t\tA.LICENSE_REG_NO,\tA.PUNISH_BASIS1,\t\t\t\n");
            sb.append("\t  \t\tA.PUNISH_BASIS2,        TO_CHAR(A.PUNISH_DT, 'DD/MM/YYYY') PUNISH_DT,\tTO_CHAR(A.EXPIRE_DT, 'DD/MM/YYYY') EXPIRE_DT,\t\n");
            sb.append("\t  \t\tTO_CHAR(A.CANCEL_DT, 'DD/MM/YYYY') CANCEL_DT,\t\t\tA.PUNISH_COUNT, \t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\tA.INSTITU_NM,\t\tA.PUNISH_PERIOD,\t\tA.INSTITU_DIRECTOR_NM,\t\tA.CHRG_NM,\t        A.CHRG_PHONE_NO, A.INSTITU_CD,\t\t\n");
            sb.append("\t\t\tA.NOTE,                                                                                                     \t\t\n");
            sb.append("\t  \t\tA.SUSPEND_CLS,\t\tTO_CHAR(A.SUSPEND_DT, 'DD/MM/YYYY') SUSPEND_DT,\t    A.SUSPEND_RSON,                                         \n");
            sb.append("\t\t\tA.RESUMPTION_CLS,     TO_CHAR(A.RESUMPTION_DT, 'DD/MM/YYYY') RESUMPTION_DT,\t\tA.RESUMPTION_RSON,                                         \n");
            sb.append("\t\t\tA.RELEASE_CLS,\t\tTO_CHAR(A.RELEASE_DT, 'DD/MM/YYYY') RELEASE_DT,  \tA.RELEASE_RSON                                          \n");
            sb.append(" FROM\tUM_VIOLATE_COM_HIST\tA\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\tA.BIZ_REG_NO = '" + ComStr.replace(s, "-", "") + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" AND\t\tA.PUNISH_COUNT = " + s4 + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    \t\t\t\t\t\n");
            sb.append(" AND\t\tA.UPDATE_DT = TO_DATE('" + s3 + "', 'DD/MM/YYYY HH24:MI:SS')\t\t\t\t\t\t\t\t\t\t\t\t        \t\t\t\t\n");
            string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            if (executeQuery.next()) {
                um_GOE_ConiA030b = new UM_GOE_ConiA030b();
                um_GOE_ConiA030b.setunpairNo(executeQuery.getString(1));
                um_GOE_ConiA030b.settoday(executeQuery.getString(2));
                um_GOE_ConiA030b.setdocNo(executeQuery.getString(3));
                um_GOE_ConiA030b.setlawNo(executeQuery.getString(4));
                um_GOE_ConiA030b.setupcheNm(executeQuery.getString(5));
                um_GOE_ConiA030b.setbonsapostNo(executeQuery.getString(6));
                um_GOE_ConiA030b.setbonsaAddr(executeQuery.getString(7));
                um_GOE_ConiA030b.setbonsaAddrNa(executeQuery.getString(8));
                um_GOE_ConiA030b.setnotifyKind(executeQuery.getString(9));
                um_GOE_ConiA030b.setbusinItem(executeQuery.getString(10));
                um_GOE_ConiA030b.setlicenseNo(executeQuery.getString(11));
                um_GOE_ConiA030b.setpunishmentBasic(executeQuery.getString(12));
                um_GOE_ConiA030b.setpunishmentBasicCode(executeQuery.getString(13));
                um_GOE_ConiA030b.setpunishmentStart(executeQuery.getString(14));
                um_GOE_ConiA030b.setpunishmentEnd(executeQuery.getString(15));
                um_GOE_ConiA030b.setcancelDay(executeQuery.getString(16));
                um_GOE_ConiA030b.setpunishmentCount(executeQuery.getString(17));
                um_GOE_ConiA030b.setkigwanNm(executeQuery.getString(18));
                um_GOE_ConiA030b.setpunishmentPeriod(executeQuery.getString(19));
                um_GOE_ConiA030b.setkigwanDaepyoNm(executeQuery.getString(20));
                um_GOE_ConiA030b.setchargeNm(executeQuery.getString(21));
                um_GOE_ConiA030b.setchargeTel(executeQuery.getString(22));
                um_GOE_ConiA030b.setkigwanCode(executeQuery.getString(23));
                um_GOE_ConiA030b.setgita(executeQuery.getString(24));
                um_GOE_ConiA030b.setstopGubun(executeQuery.getString(25));
                um_GOE_ConiA030b.setstopDay(executeQuery.getString(26));
                um_GOE_ConiA030b.setstopSayu(executeQuery.getString(27));
                um_GOE_ConiA030b.setrestartGubun(executeQuery.getString(28));
                um_GOE_ConiA030b.setrestartDay(executeQuery.getString(29));
                um_GOE_ConiA030b.setrestartSayu(executeQuery.getString(30));
                um_GOE_ConiA030b.setpunishmentCancelGubun(executeQuery.getString(31));
                um_GOE_ConiA030b.setpunishmentCancelDay(executeQuery.getString(32));
                um_GOE_ConiA030b.setpunishmentCancelSayu(executeQuery.getString(33));
                return um_GOE_ConiA030b;
            }
            throw new Exception("Không có dữ liệu");
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiB050p.selectHistory() : sql : " + string + "::: " + ex.toString());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiB050p.selectHistory() : sql : " + string + "::: " + ex2.toString());
            throw new Exception(ex2.getMessage());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return um_GOE_ConiA030b;
    }
    
    public UM_GOE_ConiA030b[] selectHistory_1(final String s, final String s2) throws Exception {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final Vector vector = new Vector<UM_GOE_ConiA030b>();
        final StringBuffer sb = new StringBuffer();
        String string = "";
        try {
            sb.append(" SELECT\tA.BIZ_REG_NO, A.UPDATE_DT, A.DOC_NO, A.CORP_REG_NO, A.BIZ_NM, A.ZIP_CD,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t  \t\t    A.ADDR, A.DETAIL_ADDR, A.CONTR_TYPE, A.BIZ_CAT, A.LICENSE_REG_NO, A.PUNISH_BASIS1,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    \n");
            sb.append("\t  \t\t    A.PUNISH_BASIS2, TO_CHAR(A.PUNISH_DT, 'DD/MM/YYYY') PUNISH_DT, TO_CHAR(A.EXPIRE_DT, 'DD/MM/YYYY') EXPIRE_DT,\t\t\n");
            sb.append("\t  \t\t    TO_CHAR(A.CANCEL_DT, 'DD/MM/YYYY') CANCEL_DT, A.PUNISH_COUNT, \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t    A.INSTITU_NM, A.PUNISH_PERIOD, A.INSTITU_DIRECTOR_NM, A.CHRG_NM, A.CHRG_PHONE_NO, A.INSTITU_CD,\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t    A.NOTE,                                                                                                     \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t  \t\t    A.SUSPEND_CLS, TO_CHAR(A.SUSPEND_DT, 'DD/MM/YYYY') SUSPEND_DT, A.SUSPEND_RSON,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t    A.RESUMPTION_CLS, TO_CHAR(A.RESUMPTION_DT, 'DD/MM/YYYY') RESUMPTION_DT, A.RESUMPTION_RSON,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t    A.RELEASE_CLS, TO_CHAR(A.RELEASE_DT, 'DD/MM/YYYY') RELEASE_DT, A.RELEASE_RSON\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" FROM\tUM_VIOLATE_COM_HIST\tA\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\tA.BIZ_REG_NO = '" + ComStr.replace(s, "-", "") + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" AND\t\tA.PUNISH_COUNT = " + s2 + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" ORDER BY A.UPDATE_DT  DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t        \t\t\t\t\t\t\t\t\n");
            string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiA030b um_GOE_ConiA030b = new UM_GOE_ConiA030b();
                um_GOE_ConiA030b.setunpairNo(executeQuery.getString(1));
                um_GOE_ConiA030b.settoday(executeQuery.getString(2));
                um_GOE_ConiA030b.setdocNo(executeQuery.getString(3));
                um_GOE_ConiA030b.setlawNo(executeQuery.getString(4));
                um_GOE_ConiA030b.setupcheNm(executeQuery.getString(5));
                um_GOE_ConiA030b.setbonsapostNo(executeQuery.getString(6));
                um_GOE_ConiA030b.setbonsaAddr(executeQuery.getString(7));
                um_GOE_ConiA030b.setbonsaAddrNa(executeQuery.getString(8));
                um_GOE_ConiA030b.setnotifyKind(executeQuery.getString(9));
                um_GOE_ConiA030b.setbusinItem(executeQuery.getString(10));
                um_GOE_ConiA030b.setlicenseNo(executeQuery.getString(11));
                um_GOE_ConiA030b.setpunishmentBasic(executeQuery.getString(12));
                um_GOE_ConiA030b.setpunishmentBasicCode(executeQuery.getString(13));
                um_GOE_ConiA030b.setpunishmentStart(executeQuery.getString(14));
                um_GOE_ConiA030b.setpunishmentEnd(executeQuery.getString(15));
                um_GOE_ConiA030b.setcancelDay(executeQuery.getString(16));
                um_GOE_ConiA030b.setpunishmentCount(executeQuery.getString(17));
                um_GOE_ConiA030b.setkigwanNm(executeQuery.getString(18));
                um_GOE_ConiA030b.setpunishmentPeriod(executeQuery.getString(19));
                um_GOE_ConiA030b.setkigwanDaepyoNm(executeQuery.getString(20));
                um_GOE_ConiA030b.setchargeNm(executeQuery.getString(21));
                um_GOE_ConiA030b.setchargeTel(executeQuery.getString(22));
                um_GOE_ConiA030b.setkigwanCode(executeQuery.getString(23));
                um_GOE_ConiA030b.setgita(executeQuery.getString(24));
                um_GOE_ConiA030b.setstopGubun(executeQuery.getString(25));
                um_GOE_ConiA030b.setstopDay(executeQuery.getString(26));
                um_GOE_ConiA030b.setstopSayu(executeQuery.getString(27));
                um_GOE_ConiA030b.setrestartGubun(executeQuery.getString(28));
                um_GOE_ConiA030b.setrestartDay(executeQuery.getString(29));
                um_GOE_ConiA030b.setrestartSayu(executeQuery.getString(30));
                um_GOE_ConiA030b.setpunishmentCancelGubun(executeQuery.getString(31));
                um_GOE_ConiA030b.setpunishmentCancelDay(executeQuery.getString(32));
                um_GOE_ConiA030b.setpunishmentCancelSayu(executeQuery.getString(33));
                vector.addElement(um_GOE_ConiA030b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiB050p.selectHistory_1() : sql : " + string + "::: " + ex.toString());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiB050p.selectHistory_1() : sql : " + string + "::: " + ex2.toString());
            throw new Exception(ex2.getMessage());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_GOE_ConiA030b[] array = new UM_GOE_ConiA030b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public UM_GOE_ConiA030b masterInformation(final String s) {
        Trx trx = null;
        Connection connection = null;
        UM_GOE_ConiA030b um_GOE_ConiA030b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        try {
            sb.append(" SELECT\tINSTITU_CD,\tBIZ_REG_NO,\t\tCHRG_NM,\tCHRGR_DEPART,\tCHRG_PHONE_NO\t\t\n");
            sb.append(" FROM\tUM_PUB_INSTITU_MAST\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\tINSTITU_CD = '" + s + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_GOE_ConiA030b = new UM_GOE_ConiA030b();
                um_GOE_ConiA030b.setkigwanCode(executeQuery.getString(1));
                um_GOE_ConiA030b.setunpairNo(executeQuery.getString(2));
                um_GOE_ConiA030b.setchargeNm(executeQuery.getString(3));
                um_GOE_ConiA030b.setchargeDepart(executeQuery.getString(4));
                um_GOE_ConiA030b.setchargeTel(executeQuery.getString(5));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiB050p.masterInformation() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiB050p.masterInformation() : sql : " + string + "::: " + ex2.toString());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return um_GOE_ConiA030b;
    }
    
    public String[][] getList(final String s, final String s2) {
        Trx trx = null;
        Connection connection = null;
        Statement prepareStatement = null;
        ResultSet executeQuery = null;
        String[][] array = (String[][])null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            sb.append(" SELECT\tA.REPR_NM, A.IDENT_NO, A.REPR_ADDR, A.MAST_REPR_YN, A.SERIAL_NO\t\t\n");
            sb.append(" FROM\tUM_VIOLATE_REPR A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\tA.BIZ_REG_NO = " + ComStr.replace(s, "-", "") + "\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" AND\t\tA.PUNISH_COUNT = " + s2 + "\t\t\t\t\t\t\t\t\t\t\n");
            string = sb.toString();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery(string);
            executeQuery.getMetaData();
            array = new String[this.getRowCount(executeQuery)][this.getColumnCount(executeQuery)];
            int n = 0;
            while (executeQuery.next()) {
                for (int i = 0; i < this.getColumnCount(executeQuery); ++i) {
                    array[n][i] = ((executeQuery.getString(i + 1) == null) ? "" : executeQuery.getString(i + 1));
                }
                ++n;
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiB050p.getList() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiB050p.getList() : sql : " + string + "::: " + ex2.toString());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return array;
    }
    
    public int getRowCount(final ResultSet set) throws SQLException {
        int row = 0;
        if (set == null) {
            return 0;
        }
        if (set.last()) {
            row = set.getRow();
        }
        set.beforeFirst();
        return row;
    }
    
    public int getColumnCount(final ResultSet set) throws SQLException {
        if (set == null) {
            return 0;
        }
        return set.getMetaData().getColumnCount();
    }
    
    public UM_GOE_ConiA031b[] daepyo_list(final String s, final String s2) throws Exception {
        Trx trx = null;
        Connection connection = null;
        Object[] array = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final Vector vector = new Vector<UM_GOE_ConiA031b>();
        final StringBuffer sb = new StringBuffer();
        String string = "";
        try {
            sb.append(" SELECT\tA.REPR_NM, A.IDENT_NO, A.REPR_ADDR, A.MAST_REPR_YN, A.SERIAL_NO\t\t\t\n");
            sb.append(" FROM\tUM_VIOLATE_REPR A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\tA.BIZ_REG_NO = '" + ComStr.replace(s, "-", "") + "'\t\t\t\t\t\n");
            sb.append(" AND\t\tA.PUNISH_COUNT = " + s2 + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiA031b um_GOE_ConiA031b = new UM_GOE_ConiA031b();
                um_GOE_ConiA031b.setdaepyoNm(executeQuery.getString(1));
                um_GOE_ConiA031b.setdaepyoJumin(executeQuery.getString(2));
                um_GOE_ConiA031b.setdaepyoAddr(executeQuery.getString(3));
                um_GOE_ConiA031b.setdaepyoYN(executeQuery.getString(4));
                um_GOE_ConiA031b.setorder(executeQuery.getString(5));
                vector.addElement(um_GOE_ConiA031b);
            }
            array = new UM_GOE_ConiA031b[vector.size()];
            vector.copyInto(array);
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiB050p.daepyo_list() : sql : " + string + "::: " + ex.toString());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiB050p.daepyo_list() : sql : " + string + "::: " + ex2.toString());
            throw new Exception(ex2.getMessage());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return (UM_GOE_ConiA031b[])array;
    }
    
    public UM_GOE_ConiA031b[] daepyo_History(final String s, final String s2) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final Vector vector = new Vector<UM_GOE_ConiA031b>();
        final StringBuffer sb = new StringBuffer();
        String string = "";
        try {
            sb.append(" SELECT\tA.REPR_NM, A.IDENT_NO, A.REPR_ADDR, A.MAST_REPR_YN, A.SERIAL_NO\t\t\n");
            sb.append(" FROM\tUM_VIOLATE_REPR_HIST A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\tA.BIZ_REG_NO = '" + ComStr.replace(s, "-", "") + "'\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" AND\t\tA.PUNISH_COUNT = " + s2 + "\t\t\t\t\t\t\t\t\t\t\n");
            string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiA031b um_GOE_ConiA031b = new UM_GOE_ConiA031b();
                um_GOE_ConiA031b.setdaepyoNm(executeQuery.getString(1));
                um_GOE_ConiA031b.setdaepyoJumin(executeQuery.getString(2));
                um_GOE_ConiA031b.setdaepyoAddr(executeQuery.getString(3));
                um_GOE_ConiA031b.setdaepyoYN(executeQuery.getString(4));
                um_GOE_ConiA031b.setorder(executeQuery.getString(5));
                vector.addElement(um_GOE_ConiA031b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiB050p.daepyo_History() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiB050p.daepyo_History() : sql : " + string + "::: " + ex2.toString());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_GOE_ConiA031b[] array = new UM_GOE_ConiA031b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Max_count(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8) throws Exception {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        int int1 = 0;
        final String string2 = s4 + " 00:00:00";
        final String string3 = s5 + " 23:59:59";
        try {
            sb.append("SELECT\tCOUNT(*)\t\t\t\t\t\t\t\t\t\t\t    \t\t\t\t\t\t\t\t\t\n");
            sb.append("   FROM\tUM_VIOLATE_COM \tA,  UM_VIOLATE_REPR D\t\t\t\t\t\t\t\t\t\t\t\t\t    \n");
            sb.append(" WHERE\tA.BIZ_REG_NO = D.BIZ_REG_NO\tAND A.PUNISH_COUNT = D.PUNISH_COUNT\t\t\t\t                \n");
            if (s != null && s.length() >= 1) {
                sb.append(" AND\t\tA.BIZ_REG_NO Like'" + ComStr.replace(s, "-", "") + "%'\t\t\t\t\t\t\t\n");
            }
            if (s2 != null && s2.length() >= 1) {
                sb.append(" AND\t\tA.INSTITU_CD = '" + s2 + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            if (s.equals("") && s2.equals("")) {
                sb.append(" AND\tA.INSTITU_CD != 'ZY00480'\t\t\t\n");
                sb.append(" AND\tA.BIZ_REG_NO NOT LIKE '999%'\t\n");
            }
            if (!s3.equals("")) {
                if (s7 != null && s7.equals("1")) {
                    sb.append(" AND\t\tD.REPR_NM like '%'||?||'%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                }
                else if (s7 != null && s7.equals("2")) {
                    sb.append(" AND\t\tD.IDENT_NO like ?||'%' \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                }
            }
            if (!s4.equals("") && !s5.equals("")) {
                if (s8 != null && s8.equals("1")) {
                    sb.append(" AND\t\tA.INPUT_DT BETWEEN TO_DATE('" + string2 + "', 'DD/MM/YYYY HH24:MI:SS') AND TO_DATE('" + string3 + "', 'DD/MM/YYYY HH24:MI:SS')\t\n");
                }
                else if (s8 != null && s8.equals("2")) {
                    sb.append("\t\t   AND  A.PUNISH_DT >= TO_DATE('" + string2 + "', 'DD/MM/YYYY HH24:MI:SS') AND A.EXPIRE_DT <= TO_DATE('" + string3 + "',   'DD/MM/YYYY HH24:MI:SS')    \n");
                }
            }
            string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            if (!s3.equals("")) {
                prepareStatement.setString(1, s3);
            }
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiB050p.Max_count() : sql : " + string + "::: " + ex.toString());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiB050p.Max_count() : sql : " + string + "::: " + ex2.toString());
            throw new Exception(ex2.getMessage());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    public int Stopcount(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        int int1 = 0;
        final String string2 = s4 + " 00:00:00";
        final String string3 = s5 + " 23:59:59";
        try {
            sb.append("SELECT\tCOUNT(*)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("   FROM\tUM_VIOLATE_COM \tA,  UM_VIOLATE_REPR D\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    \n");
            sb.append(" WHERE\tA.BIZ_REG_NO = D.BIZ_REG_NO\tAND A.PUNISH_COUNT = D.PUNISH_COUNT\t\t\t\t                            \n");
            if (s2.length() > 3) {
                sb.append(" AND\t\tA.CONTR_TYPE = '" + s2 + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            if (s.length() >= 1) {
                sb.append(" AND\t\tA.BIZ_REG_NO like '" + s + "%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            if (s3.length() >= 1) {
                sb.append(" AND\t\tD.REPR_NM like '%'||?||'%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            if (s4.length() >= 1) {
                sb.append(" AND\t\tA.INPUT_DT BETWEEN TO_DATE('" + string2 + "', 'DD/MM/YYYY  HH24:MI:SS') AND TO_DATE('" + string3 + "', 'DD/MM/YYYY HH24:MI:SS')\t\n");
            }
            sb.append("   AND\t\tA.INSTITU_CD = '" + s7 + "'                                                                                  \n");
            string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            if (s3.length() >= 1) {
                prepareStatement.setString(1, s3);
            }
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiB050p.Stopcount() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiB050p.Stopcount() : sql : " + string + "::: " + ex2.toString());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    public int Modifycount(final String s, final String s2, final String s3, final String s4, final String s5) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        int int1 = 0;
        try {
            sb.append("SELECT\tCOUNT(*)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("   FROM\tUM_VIOLATE_COM \tA,  UM_VIOLATE_REPR D\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\tA.BIZ_REG_NO = D.BIZ_REG_NO\tAND A.PUNISH_COUNT = D.PUNISH_COUNT\t\n");
            sb.append("   AND   A.RELEASE_CLS = 'N'\t\t                                        \n");
            if (s2.equals("Name")) {
                sb.append(" AND\t\tD.REPR_NM like '%'||?||'%'\t\t\t\t\t\t\t\t\n");
            }
            else if (s2.equals("JuminNo")) {
                sb.append(" AND\t\tD.IDENT_NO like ?||'%'\t\t\t\t\t\t\t\n");
            }
            if (s.length() >= 1) {
                sb.append(" AND\t\tA.BIZ_REG_NO like '" + s + "%'\t\t\t\t\t\t\n");
            }
            sb.append("   AND\t\tA.INSTITU_CD = '" + s5 + "'                                \n");
            string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            if (!s2.equals("")) {
                prepareStatement.setString(1, s3);
            }
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiB050p.Modifycount() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiB050p.Modifycount() : sql : " + string + "::: " + ex2.toString());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    public int UpdateCount(final String s, final String s2) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        int int1 = 0;
        try {
            sb.append(" SELECT\tCOUNT(*)                            \n");
            sb.append("   FROM\tUM_VIOLATE_COM_HIST                \n");
            sb.append("  WHERE  BIZ_REG_NO = '" + s + "'     \n");
            sb.append("    AND  PUNISH_COUNT = " + s2 + "      \n");
            sb.append("    AND  SUSPEND_CLS = 'N'                      \n");
            sb.append("    AND  RESUMPTION_CLS = 'N'                      \n");
            sb.append("    AND  RELEASE_CLS = 'N'                      \n");
            string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiB050p.UpdateCount() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiB050p.UpdateCount() : sql : " + string + "::: " + ex2.toString());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
}
