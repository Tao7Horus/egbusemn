// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.customer.educat.datamodel.UserDivInfoList;
import portal.customer.educat.datamodel.UserDivInfo;
import java.util.Enumeration;
import portal.customer.educat.datamodel.ReceiptDivInfoList;
import portal.customer.educat.datamodel.ReceiptDivInfo;
import portal.customer.educat.datamodel.OrgUserInfo;
import portal.customer.educat.datamodel.EducatOrgInfoListDtl;
import portal.customer.educat.datamodel.EducatOrgInfo;
import portal.customer.educat.datamodel.EducatInfoListDtl;
import portal.customer.educat.datamodel.EducatInfo;
import portal.log.portalLog;
import javax.naming.InitialContext;
import portal.customer.educat.session.Educat;
import portal.customer.educat.session.EducatHome;

public class EducatConn
{
    private EducatHome pcHome;
    private Educat pcRemote;
    protected final boolean IS_LOG = false;
    protected final String LOGFILE = "Educat.log";
    protected final String ERRFILE = "EducatErr.log";
    protected String THIS_CLASS_NAME;
    
    public EducatConn() {
        this.pcHome = null;
        this.pcRemote = null;
        this.THIS_CLASS_NAME = "EducatConn";
        try {
            this.pcHome = (EducatHome)new InitialContext().lookup("Educat");
            this.pcRemote = this.pcHome.create();
        }
        catch (Exception ex) {
            portalLog.log("EducatErr.log", "[" + this.THIS_CLASS_NAME + ":EducatConn()] Exception" + "\n" + ex.toString());
        }
    }
    
    public String insertEducatList(final EducatInfo educatInfo) {
        String insertEducatList = "EduConnErr";
        try {
            insertEducatList = this.pcRemote.insertEducatList(educatInfo);
        }
        catch (Exception ex) {
            portalLog.log("EducatErr.log", "[" + this.THIS_CLASS_NAME + ":insertEducatList()] Exception" + "\n" + ex.toString());
        }
        return insertEducatList;
    }
    
    public int insertEducatListDtl(final String s, final EducatInfoListDtl educatInfoListDtl) {
        int insertEducatListDtl = -1;
        try {
            insertEducatListDtl = this.pcRemote.insertEducatListDtl(s, educatInfoListDtl);
        }
        catch (Exception ex) {
            portalLog.log("EducatErr.log", "[" + this.THIS_CLASS_NAME + ":insertEducatListDtl()] Exception" + "\n" + ex.toString());
        }
        return insertEducatListDtl;
    }
    
    public int updateEducatList(final EducatInfo educatInfo) {
        int updateEducatList = -1;
        try {
            updateEducatList = this.pcRemote.updateEducatList(educatInfo);
        }
        catch (Exception ex) {
            portalLog.log("EducatErr.log", "[" + this.THIS_CLASS_NAME + ":updateEducatList()] Exception" + "\n" + ex.toString());
        }
        return updateEducatList;
    }
    
    public int updateEducatListDtl(final String s, final EducatInfoListDtl educatInfoListDtl) {
        int updateEducatListDtl = -1;
        try {
            updateEducatListDtl = this.pcRemote.updateEducatListDtl(s, educatInfoListDtl);
        }
        catch (Exception ex) {
            portalLog.log("EducatErr.log", "[" + this.THIS_CLASS_NAME + ":updateEducatListDtl()] Exception" + "\n" + ex.toString());
        }
        return updateEducatListDtl;
    }
    
    public int deleteList(final String s, final EducatInfoListDtl educatInfoListDtl) {
        int deleteList = -1;
        try {
            deleteList = this.pcRemote.deleteList(s, educatInfoListDtl);
        }
        catch (Exception ex) {
            portalLog.log("EducatErr.log", "[" + this.THIS_CLASS_NAME + ":deleteList()] Exception" + "\n" + ex.toString());
        }
        return deleteList;
    }
    
    public EducatInfo selectRequestEducat(final String s, final String s2, final int n, final int n2) {
        EducatInfo selectRequestEducat = null;
        try {
            selectRequestEducat = this.pcRemote.selectRequestEducat(s, s2, n, n2);
        }
        catch (Exception ex) {
            portalLog.log("EducatErr.log", "[" + this.THIS_CLASS_NAME + ":selectRequestEducat()] Exception" + "\n" + ex.toString());
        }
        return selectRequestEducat;
    }
    
    public EducatInfo selectReceiptEducat(final String s, final String s2, final int n, final int n2) {
        EducatInfo selectReceiptEducat = null;
        try {
            selectReceiptEducat = this.pcRemote.selectReceiptEducat(s, s2, n, n2);
        }
        catch (Exception ex) {
            portalLog.log("EducatErr.log", "[" + this.THIS_CLASS_NAME + ":selectReceiptEducat()] Exception" + "\n" + ex.toString());
        }
        return selectReceiptEducat;
    }
    
    public EducatInfo selectEducatDtl(final String s) {
        EducatInfo selectEducatDtl = null;
        try {
            selectEducatDtl = this.pcRemote.selectEducatDtl(s);
        }
        catch (Exception ex) {
            portalLog.log("EducatErr.log", "[" + this.THIS_CLASS_NAME + ":selectRequestEducatDtl()] Exception" + "\n" + ex.toString());
        }
        return selectEducatDtl;
    }
    
    public EducatOrgInfo getEducatOrg(final String s) {
        EducatOrgInfo educatOrg = null;
        try {
            educatOrg = this.pcRemote.getEducatOrg(s);
        }
        catch (Exception ex) {
            portalLog.log("EducatErr.log", "[" + this.THIS_CLASS_NAME + ":selectRequestEducatDtl()] Exception" + "\n" + ex.toString());
        }
        return educatOrg;
    }
    
    public String getEducatOrgCode(final String s) {
        String educatOrgCode = null;
        try {
            educatOrgCode = this.pcRemote.getEducatOrgCode(s);
        }
        catch (Exception ex) {
            portalLog.log("EducatErr.log", "[" + this.THIS_CLASS_NAME + ":isEducatOrgUser()] Exception" + "\n" + ex.toString());
        }
        return educatOrgCode;
    }
    
    public int setEducatOrgList(final EducatOrgInfo educatOrgList) {
        int setEducatOrgList = 0;
        try {
            setEducatOrgList = this.pcRemote.setEducatOrgList(educatOrgList);
        }
        catch (Exception ex) {
            portalLog.log("EducatErr.log", "[" + this.THIS_CLASS_NAME + ":setEducatOrgList()] Exception" + "\n" + ex.toString());
        }
        return setEducatOrgList;
    }
    
    public int setEducatOrgListDtl(final String s, final EducatOrgInfoListDtl educatOrgInfoListDtl) {
        int setEducatOrgListDtl = 0;
        try {
            setEducatOrgListDtl = this.pcRemote.setEducatOrgListDtl(s, educatOrgInfoListDtl);
        }
        catch (Exception ex) {
            portalLog.log("EducatErr.log", "[" + this.THIS_CLASS_NAME + ":setEducatOrgList()] Exception" + "\n" + ex.toString());
        }
        return setEducatOrgListDtl;
    }
    
    public int delEducatOrgList(final String s) {
        int delEducatOrgList = 0;
        try {
            delEducatOrgList = this.pcRemote.delEducatOrgList(s);
        }
        catch (Exception ex) {
            portalLog.log("EducatErr.log", "[" + this.THIS_CLASS_NAME + ":delEducatOrgList()] Exception" + "\n" + ex.toString());
        }
        return delEducatOrgList;
    }
    
    public int delEducatOrgListDtl(final String s, final String s2) {
        int delEducatOrgListDtl = 0;
        try {
            delEducatOrgListDtl = this.pcRemote.delEducatOrgListDtl(s, s2);
        }
        catch (Exception ex) {
            portalLog.log("EducatErr.log", "[" + this.THIS_CLASS_NAME + ":delEducatOrgListDtl()] Exception" + "\n" + ex.toString());
        }
        return delEducatOrgListDtl;
    }
    
    public OrgUserInfo getOrgUser(final String s) {
        OrgUserInfo orgUser = null;
        try {
            orgUser = this.pcRemote.getOrgUser(s);
        }
        catch (Exception ex) {
            portalLog.log("EducatErr.log", "[" + this.THIS_CLASS_NAME + ":getOrgUser()] Exception" + "\n" + ex.toString());
        }
        return orgUser;
    }
    
    public String getRecpDivName(final String s) {
        if (s == null) {
            return "";
        }
        final ReceiptDivInfo receiptDivInfo = new ReceiptDivInfo();
        if (receiptDivInfo != null && receiptDivInfo.vList != null && receiptDivInfo.vList.size() > 0) {
            final Enumeration elements = receiptDivInfo.vList.elements();
            while (elements.hasMoreElements()) {
                final ReceiptDivInfoList list = (ReceiptDivInfoList)elements.nextElement();
                if (list.recpDivCode != null && list.recpDivCode.equals(s)) {
                    return list.recpDivName;
                }
            }
        }
        return "";
    }
    
    public String getUserDivName(final String s) {
        if (s == null) {
            return "";
        }
        final UserDivInfo userDivInfo = new UserDivInfo();
        if (userDivInfo != null && userDivInfo.vList != null && userDivInfo.vList.size() > 0) {
            final Enumeration elements = userDivInfo.vList.elements();
            while (elements.hasMoreElements()) {
                final UserDivInfoList list = (UserDivInfoList)elements.nextElement();
                if (list.userDivCode != null && list.userDivCode.equals(s)) {
                    return list.userDivName;
                }
            }
        }
        return "";
    }
    
    public EducatInfo getNotResult(final String s) {
        if (s == null) {
            return null;
        }
        EducatInfo notResult = null;
        try {
            notResult = this.pcRemote.getNotResult(s);
        }
        catch (Exception ex) {
            portalLog.log("EducatErr.log", "[" + this.THIS_CLASS_NAME + ":getNotResultCnt()] Exception" + "\n" + ex.toString());
        }
        return notResult;
    }
    
    public int getNotResultCnt(final String s) {
        if (s == null) {
            return 0;
        }
        int notResultCnt = 0;
        try {
            notResultCnt = this.pcRemote.getNotResultCnt(s);
        }
        catch (Exception ex) {
            portalLog.log("EducatErr.log", "[" + this.THIS_CLASS_NAME + ":getNotResultCnt()] Exception" + "\n" + ex.toString());
        }
        return notResultCnt;
    }
}
