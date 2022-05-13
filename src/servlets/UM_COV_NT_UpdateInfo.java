// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.util.Set;
import java.sql.Connection;
import entity.StdClsItem;
import entity.UM_ADJ_GovuA020b;
import common.ComUtil;
import entity.ReprItem;
import common.Trx;

import org.apache.commons.fileupload.FileUploadException;
import java.util.Iterator;
import java.util.Map;
import entity.DPItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import common.util.HttpUtility;
import dao.UM_RAB_MastBidAgent;
import dao.UM_RAB_MastEnterStdCls;
import dao.UM_RAB_MastRepr;
import dao.UM_RAB_MastSupplier;
import common.Log;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.servlet.http.HttpServlet;

public class UM_COV_NT_UpdateInfo extends HttpServlet
{
    private Hashtable requestTable;
    private List DPList;
    
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            this.setParamsFromrequestMultiTablepartForm(req, res);
            Log.debug("Vao day");
            this.updateProcess();
            CommonMessage.printMessage("", "1", "Cập nhật thành công.", "/GO/UM_GOJ_ConiC031l.jsp", res, req);
        }
        catch (Exception exm) {
            Log.errors(this, exm, "");
            CommonMessage.printMessage("IP[" + HttpUtility.getIP(req) + "]", "", "Đã có lỗi xảy ra trong quá tình chỉnh sửa. Vui lòng thử lại.", null, res, req);
        }
    }
    
    public void setParamsFromrequestMultiTablepartForm(final HttpServletRequest req, final HttpServletResponse res) throws FileUploadException {
        this.requestTable = new Hashtable();
        if (req.getContentType() != null && req.getContentType().toLowerCase().indexOf("multipart/form-data") > -1) {
            final Map mapRequest = req.getParameterMap();
            Iterator iter = mapRequest.keySet().iterator();
            String name;
            for (;iter.hasNext();) {
            	name = (String) iter.next();
                final String[] item = (String[])mapRequest.get(name);
                this.requestTable.put(name, item[0]);
            }
            final List items = new ServletFileUpload((FileItemFactory)new DiskFileItemFactory()).parseRequest(req);
            final Iterator itr3 = items.iterator();
            boolean flagDP = false;
            String year = "";
            this.DPList = new ArrayList();
            DPItem dpItem = null;
            while (itr3.hasNext()) {
                try {
                    final DiskFileItem item2 = (DiskFileItem)itr3.next();
                    if ("stdFinanYear".equals(item2.getFieldName())) {
                        year = item2.getString();
                        if ("".equals(year)) {
                            continue;
                        }
                    }
                    else if ("reprIdentNo".equals(item2.getFieldName())) {
                        dpItem = new DPItem();
                        dpItem.setCeojuminNumber(item2.getString());
                        flagDP = true;
                    }
                    else if ("reprName".equals(item2.getFieldName()) && flagDP) {
                        dpItem.setCeoName(item2.getString());
                    }
                    else if ("reprEmail".equals(item2.getFieldName()) && flagDP) {
                        dpItem.setCeoMail(item2.getString());
                    }
                    else if ("reprMobile".equals(item2.getFieldName()) && flagDP) {
                        dpItem.setCeoPhone(item2.getString());
                        this.DPList.add(dpItem);
                        dpItem = null;
                        flagDP = false;
                    }
                    else if ("reprIsmain".equals(item2.getFieldName())) {
                    }
                    else {
                        this.requestTable.put(item2.getFieldName(), item2.getString("UTF-8"));
                    }
                }
                catch (Exception ex) {}
            }
        }
    }
    
    private String getRequestValue(final Object obj) {
        return (obj == null) ? "" : obj.toString();
    }
    
    private void updateProcess() throws Exception {
        Log.debug("Vao day");
        Trx tr = null;
        Connection conn = null;
        tr = new Trx(this, "usemn");
        conn = tr.getConnection();
        
        final UM_RAB_MastSupplier mastSupplierEnterMastDao = new UM_RAB_MastSupplier();
        final UM_RAB_MastRepr mastReprDao = new UM_RAB_MastRepr();
        final UM_RAB_MastEnterStdCls mastEnterStdClsDAO = new UM_RAB_MastEnterStdCls();
        final UM_RAB_MastBidAgent mastBidAgentDAO = new UM_RAB_MastBidAgent();
    
        try {
          
            conn.setAutoCommit(false);
            final Set keys = this.requestTable.keySet();
            final Iterator itr = keys.iterator();
            String temp = "";
            while (itr.hasNext()) {
                final String keyName = (String)itr.next();
                temp = String.valueOf(temp) + "_" + keyName + "=" + this.requestTable.get((Object)keyName);
            }
            final String bizRegNo = this.getRequestValue(this.requestTable.get((Object)"bizRegNo"));
            final String bizName = this.getRequestValue(this.requestTable.get((Object)"bizName"));
            final String bizEnName = this.getRequestValue(this.requestTable.get((Object)"bizEnName"));
            final String commDate = this.getRequestValue(this.requestTable.get((Object)"commDate"));
            final String addr = this.getRequestValue(this.requestTable.get((Object)"addr"));
            final String phoneNo = this.getRequestValue(this.requestTable.get((Object)"phoneNo"));
            final String homePage = this.getRequestValue(this.requestTable.get((Object)"homePage"));
            String capital = this.getRequestValue(this.requestTable.get((Object)"capital"));
            String emplCount = this.getRequestValue(this.requestTable.get((Object)"emplCount"));
            final String fax = this.getRequestValue(this.requestTable.get((Object)"fax"));
            final String bizCls = this.getRequestValue(this.requestTable.get((Object)"bizCls"));
            final String national = this.getRequestValue(this.requestTable.get((Object)"national"));
            final String province = this.getRequestValue(this.requestTable.get((Object)"area"));
            final String supplierCls = this.getRequestValue(this.requestTable.get((Object)"supplierCls"));
            Log.debug("supplierCls: " + supplierCls);
            final String reprCountStr = this.getRequestValue(this.requestTable.get((Object)"reprCount"));
            int reprCount = 0;
            if (reprCountStr != null && reprCountStr.length() != 0) {
                reprCount = Integer.parseInt(reprCountStr);
            }
            capital = capital.replaceAll("\\.", "");
            emplCount = emplCount.replaceAll("\\.", "");
            final List reprList = new ArrayList();
            ReprItem reprItem = null;
            String reprName = "";
            String reprIdentNo = "";
            String reprMobile = "";
            String reprIsmain = "";
            String reprEmail = "";
            for (int i = 0; i < reprCount; ++i) {
                reprName = this.getRequestValue(this.requestTable.get((Object)("reprName[" + i + "]")));
                reprIdentNo = this.getRequestValue(this.requestTable.get((Object)("reprIdentNo[" + i + "]")));
                reprMobile = this.getRequestValue(this.requestTable.get((Object)("reprMobile[" + i + "]")));
                reprIsmain = this.getRequestValue(this.requestTable.get((Object)("reprIsmain[" + i + "]")));
                reprEmail = this.getRequestValue(this.requestTable.get((Object)("reprEmail[" + i + "]")));
                reprItem = new ReprItem(reprName, reprIdentNo, reprMobile, reprEmail, reprIsmain);
                reprList.add(reprItem);
            }
            String employeeNo = ComUtil.replace(this.getRequestValue(this.requestTable.get((Object)"employeeNo")), ",", "");
            if (employeeNo.equals("")) {
                employeeNo = "0";
            }
            final String stdClsCountStr = this.getRequestValue(this.requestTable.get((Object)"stdClsCount"));
            int stdClsCount = 0;
            if (stdClsCountStr != null && stdClsCountStr.length() != 0) {
                stdClsCount = Integer.parseInt(stdClsCountStr);
            }
            final List stdClsList = new ArrayList();
            StdClsItem stdClsItem = null;
            String stdClsCode = "";
            String mastStdClsYn = "";
            String stdClsNo = "";
            String stdClsName = "";
            String stdClsIssuedDate = "";
            String constAbilEvalAmt = "";
            String evalStdYear = "";
            String stdClsExpiryDate = "";
            for (int j = 0; j < stdClsCount; ++j) {
                stdClsCode = this.getRequestValue(this.requestTable.get((Object)("stdClsCode[" + j + "]")));
                stdClsName = this.getRequestValue(this.requestTable.get((Object)("stdClsName[" + j + "]")));
                mastStdClsYn = this.getRequestValue(this.requestTable.get((Object)("mastStdClsYn[" + j + "]")));
                stdClsNo = this.getRequestValue(this.requestTable.get((Object)("stdClsNo[" + j + "]")));
                stdClsIssuedDate = this.getRequestValue(this.requestTable.get((Object)("stdClsIssuedDate[" + j + "]")));
                constAbilEvalAmt = this.getRequestValue(this.requestTable.get((Object)("constAbilEvalAmt[" + j + "]")));
                evalStdYear = this.getRequestValue(this.requestTable.get((Object)("evalStdYear[" + j + "]")));
                stdClsExpiryDate = this.getRequestValue(this.requestTable.get((Object)("stdClsExpiryDate[" + j + "]")));
                stdClsItem = new StdClsItem(stdClsCode, stdClsNo, stdClsIssuedDate, constAbilEvalAmt, evalStdYear, mastStdClsYn, stdClsExpiryDate, "", stdClsName);
                stdClsList.add(stdClsItem);
            }
            
            
           
          
            final String identCts = this.getRequestValue(this.requestTable.get((Object)"caReprName"));
            final String identNoCts = this.getRequestValue(this.requestTable.get((Object)"caReprIdentNo"));
            final String bizAgentName = this.getRequestValue(this.requestTable.get((Object)"bizAgentName"));
            final String bizDepart = this.getRequestValue(this.requestTable.get((Object)"bizDepart"));
            final String bizPhoneNo = this.getRequestValue(this.requestTable.get((Object)"bizPhoneNo"));
            final String bizFax = this.getRequestValue(this.requestTable.get((Object)"bizFax"));
            final String bizMobile = this.getRequestValue(this.requestTable.get((Object)"bizMobile"));
            final String bizAgentEmail = this.getRequestValue(this.requestTable.get((Object)"bizAgentEmail"));
            final String bizPosition = this.getRequestValue(this.requestTable.get((Object)"bizPosition"));
            final String bizIdentNo = this.getRequestValue(this.requestTable.get((Object)"bizIdentNo"));
            mastEnterStdClsDAO.deleteAll(bizRegNo, conn);
            mastEnterStdClsDAO.save(bizRegNo, stdClsList, conn);
            
            //ThachPN-20160426 - Thay doi nguoi dai dien chung thu so
            UM_ADJ_GovuA020b caInfo = new UM_ADJ_GovuA020b();
            caInfo.setIDENT(identCts);
            caInfo.setIDENTJUMIN(identNoCts);          
            final String UserID = "";
           // mastSupplierEnterMastDao.delete(bizRegNo, conn);
           // mastSupplierEnterMastDao.save(bizRegNo, bizName, bizEnName, commDate, addr, phoneNo, emplCount, fax, national, province, homePage, capital, supplierCls, bizCls, prevRecvNo, perBranch, docNo, conn);
            mastSupplierEnterMastDao.modifyWithManagerID2(bizRegNo, bizName, bizEnName, commDate, addr, phoneNo, emplCount, fax, national, homePage, capital, supplierCls, conn, UserID, bizCls, province);
            
            mastReprDao.deleteAll(bizRegNo, conn);
            mastReprDao.save(bizRegNo, reprList, conn);
            mastBidAgentDAO.deleteAll(bizRegNo, conn);
            mastBidAgentDAO.save(bizIdentNo, bizRegNo, bizAgentName, bizDepart, bizPosition, bizPhoneNo, bizAgentEmail, bizMobile, bizFax, conn);
            Log.debug("123 ");
           
	conn.commit();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            if (conn != null) {
                try {
                    conn.rollback();
                }
                catch (Exception ex) {}
            }
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                }
                catch (Exception ex2) {}
            }
            throw e;
        }
        finally {
            if (conn != null) {
                try {
                    tr.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (conn != null) {
            try {
                tr.close();
            }
            catch (Exception ex4) {}
        }
    }
}
