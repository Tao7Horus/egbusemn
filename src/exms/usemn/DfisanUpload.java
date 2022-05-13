// 
// Decompiled by Procyon v0.5.30
// 

package exms.usemn;

import java.io.IOException;
import javax.servlet.ServletException;
import exms.upload.RcvDfisan1;
import common.Log;
import entity.UM_GOE_ConiA030b;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class DfisanUpload extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final UM_GOE_ConiA030b ett = new UM_GOE_ConiA030b();
        final String action = req.getParameter("action");
        final String inputUmId = req.getParameter("inputUmId");
        final String docNo = req.getParameter("docNo");
        final String instituNm = req.getParameter("instituNm");
        final String toInstituNm = req.getParameter("toInstituNm");
        final String instituDirectorNm = req.getParameter("instituDirectorNm");
        final String chrgNm = req.getParameter("chrgNm");
        final String chrgPhoneNo = req.getParameter("chrgPhoneNo");
        final String bizRegNo = req.getParameter("bizRegNo");
        final String bizNm = req.getParameter("bizNm");
        final String addr = req.getParameter("addr");
        final String bizCat = req.getParameter("bizCat");
        final String reprNm = req.getParameter("reprNm");
        final String identNo = req.getParameter("identNo");
        final String reprAddr = req.getParameter("reprAddr");
        final String punishBasis1 = req.getParameter("punishBasis1");
        final String punishDt = req.getParameter("punishDt");
        final String expireDt = req.getParameter("expireDt");
        final String punishPeriod = req.getParameter("punishPeriod");
        final String contrType = req.getParameter("contrType");
        final String cancelDt = req.getParameter("cancelDt");
        final String note = req.getParameter("note");
        Log.debug("111 manhbt");
        try {
            if ("insert".equals(action)) {
                final RcvDfisan1 rcvDfisan1 = new RcvDfisan1();
                rcvDfisan1.main_process(inputUmId, docNo, instituNm, toInstituNm, instituDirectorNm, chrgNm, chrgPhoneNo, bizRegNo, bizNm, addr, bizCat, reprNm, identNo, reprAddr, punishBasis1, punishDt, expireDt, punishPeriod, cancelDt, contrType, note);
            }
        }
        catch (Exception e) {
            Log.errors(this, e, "Exception block EP_COV_PQA505.doPost()");
        }
    }
}
