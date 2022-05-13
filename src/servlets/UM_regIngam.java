// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.Connection;
import common.Log;
import java.sql.SQLException;
import java.io.File;
import common.Trx;
import common.util.CommonMessage;
import beans.FileUpManager_DEXT;
import LOGIN.UM_Auth_Check;
import beans.UM_IngamControl;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_regIngam extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        String saupNo = "";
        FileUpManager_DEXT fupManager = null;
        final UM_IngamControl umic = new UM_IngamControl();
        UM_Auth_Check cookieCheck = null;
        Trx resource = null;
        Connection conn = null;
        String ErrorCode = "";
        String maxNumber = "";
        String ingamName = "";
        String errorMessage = "";
        Label_0456: {
            while (true) {
                try {
                    cookieCheck = new UM_Auth_Check(req, res);
                    try {
                        fupManager = new FileUpManager_DEXT(req, "/data/USEMN/", 30, res);
                    }
                    catch (Exception e) {
                        ErrorCode = "b";
                        errorMessage = e.getMessage();
                    }
                    if (ErrorCode.equals("b")) {
                        CommonMessage.printMsg(null, "", "Kích thước tập tin không được vượt quá 30KByte.<br>" + errorMessage, null, res);
                    }
                    else {
                        saupNo = fupManager.getParameter("saupNo");
                        cookieCheck.checkCookie("cga", saupNo, null);
                        resource = new Trx(this, "usemn");
                        conn = resource.getConnection();
                        conn.setAutoCommit(false);
                        maxNumber = umic.getMaxNumber(saupNo, conn);
                        ingamName = String.valueOf(saupNo) + "-B-" + maxNumber;
                        if (fupManager.renameTo("inGam", ingamName)) {
                            try {
                                umic.insertIngam(saupNo, maxNumber, conn);
                            }
                            catch (Exception exm) {
                                try {
                                    final File file = new File("/data/USEMN/" + ingamName);
                                    file.delete();
                                }
                                catch (Exception ex2) {}
                                throw exm;
                            }
                            conn.commit();
                            conn.setAutoCommit(true);
                            CommonMessage.printMsg("", "5", "Đăng ký đã được xử lý.", "", res);
                            break Label_0456;
                        }
                        conn.commit();
                        conn.setAutoCommit(true);
                        CommonMessage.printMsg(null, "", "Đăng ký con dấu không thành công.<br>Xin vui lòng thử lại.", null, res);
                    }
                    try {}
                    catch (Exception ex3) {}
                    return;
                }
                catch (Exception ex) {
                    try {
                        if (conn != null) {
                            conn.rollback();
                        }
                    }
                    catch (SQLException ex4) {}
                    try {
                        if (conn != null) {
                            conn.setAutoCommit(true);
                        }
                    }
                    catch (SQLException ex5) {}
                    Log.debug("[UM_regIngam.java] Số đăng ký kinh doanh[" + saupNo + "],ID đăng nhập:[" + cookieCheck.getID() + "] :" + ex.toString());
                    CommonMessage.printMsg(null, "", ex.getMessage(), null, res);
                    continue;
                }
                finally {
                    try {
                        if (conn != null) {
                            resource.close();
                        }
                    }
                    catch (Exception ex6) {}
                }
                break;
            }
            try {
                if (conn != null) {
                    resource.close();
                }
            }
            catch (Exception ex7) {}
        }
    }
}
