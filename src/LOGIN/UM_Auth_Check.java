// 
// Decompiled by Procyon v0.5.30
// 

package LOGIN;

import common.ComDbQuery;
import common.PublicUtil;
import java.sql.Connection;
import common.Log;
import common.util.HttpUtility;
import g2b.sso.SSO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

public class UM_Auth_Check
{
    private String ID;
    private String Gubun;
    private String MCode;
    private String Name;
    private String MName;
    private String LType;
    private String LogNum;
    private String TelNum;
    private String Email;
    private String GType;
    private String BOffi;
    private String SgCode;
    private String CertSeq;
    private String IP;
    
    public String getID() {
        return this.ID;
    }
    
    public String getGubun() {
        return this.Gubun;
    }
    
    public String getMCode() {
        return this.MCode;
    }
    
    public String getName() {
        return this.Name;
    }
    
    public String getMName() {
        return this.MName;
    }
    
    public String getLType() {
        return this.LType;
    }
    
    public String getLogNum() {
        return this.LogNum;
    }
    
    public String getTelNum() {
        return this.TelNum;
    }
    
    public String getEmail() {
        return this.Email;
    }
    
    public String getGType() {
        return this.GType;
    }
    
    public String getBOffi() {
        return this.BOffi;
    }
    
    public String getSgCode() {
        return this.SgCode;
    }
    
    public String getCertSeq() {
        return this.CertSeq;
    }
    
    public String getIP() {
        return this.IP;
    }
    
    public UM_Auth_Check(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws Exception {
        final SSO sso = new SSO(httpServletRequest, httpServletResponse);
        if (sso.isLogin()) {
            this.ID = sso.getID();
            this.Gubun = sso.getGubun();
            this.MCode = sso.getMCode();
            this.Name = sso.getName();
            this.MName = sso.getMName();
            this.LType = sso.getLType();
            this.LogNum = sso.getLogNum();
            this.TelNum = sso.getTelNum();
            this.Email = sso.getEmail();
            this.GType = sso.getGType();
            this.BOffi = sso.getBOffi();
            this.SgCode = sso.getSgCode();
            this.IP = HttpUtility.getIP(httpServletRequest);
            return;
        }
        Log.debug(2, this.getClass().getName() + ".UM_Auth_Check() IP[" + HttpUtility.getIP(httpServletRequest) + "]:sso.isLogin is false");
        throw new Exception("Kh??ng th??? ?????c c??c th??ng tin ????ng nh???p.<br>H??y ????ng xu???t r???i ????ng nh???p l???i.");
    }
    
    public UM_Auth_Check(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final SSO sso) throws Exception {
        if (sso.isLogin()) {
            this.ID = sso.getID();
            this.Gubun = sso.getGubun();
            this.MCode = sso.getMCode();
            this.Name = sso.getName();
            this.MName = sso.getMName();
            this.LType = sso.getLType();
            this.LogNum = sso.getLogNum();
            this.TelNum = sso.getTelNum();
            this.Email = sso.getEmail();
            this.GType = sso.getGType();
            this.BOffi = sso.getBOffi();
            this.SgCode = sso.getSgCode();
            this.IP = HttpUtility.getIP(httpServletRequest);
            return;
        }
        Log.debug(2, this.getClass().getName() + ".UM_Auth_Check() IP[" + HttpUtility.getIP(httpServletRequest) + "]:sso.isLogin is false");
        throw new Exception("Kh??ng th??? ?????c c??c th??ng tin ????ng nh???p.<br>H??y ????ng xu???t r???i ????ng nh???p l???i.");
    }
    
    public void checkCookie(final String s, final String s2, final String s3) throws Exception {
        if (this.getMCode() == null || this.getGubun() == null) {
            Log.debug(2, this.getClass().getName() + ".checkCookie() type[" + s + "],saupjaNumber[" + s2 + "],gigwanNumber[" + s3 + "], IP[" + this.getIP() + "] getMCode() is null ||  getGubun() is null");
            throw new Exception("Kh??ng th??? ?????c c??c th??ng tin ????ng nh???p.<br>H??y ????ng xu???t r???i ????ng nh???p l???i.");
        }
        if (s.equals("c")) {
            this.checkUpche(s2);
        }
        else if (s.equals("g")) {
            this.checkGigwan(s3);
        }
        else if (s.equals("k")) {
            this.checkGigwanApprove();
        }
        else if (s.equals("a")) {
            this.checkUpcheApprove();
        }
        else if (s.equals("ca")) {
            this.checkCookieCA(s2);
        }
        else if (s.equals("cga")) {
            this.checkCookieCGA(s2, s3);
        }
        else if (s.equals("ka")) {
            this.checkCookieKA();
        }
        else if (s.equals("gah")) {
            this.checkCookieGAH();
        }
        else if (s.equals("ga")) {
            this.checkCookieGA();
        }
        else if (s.equals("gaz")) {
            this.checkCookieGAZ();
        }
        else if (s.equals("gh")) {
            this.checkCookieGH();
        }
        else if (s.equals("gazm")) {
            this.checkCookieGAZM();
        }
        else if (s.equals("gazmj")) {
            this.checkCookieGAZMJ();
        }
        else if (s.equals("gam")) {
            this.checkCookieGAM();
        }
        else if (s.equals("x")) {
            this.checkCookieX();
        }
        else if (s.equals("d")) {
            this.checkCookieD();
        }
        else if (s.equals("ghi")) {
            this.checkCookieGHI();
        }
    }
    
    public void checkCookie(final String s, final String s2) throws Exception {
        if (this.getMCode() == null || this.getGubun() == null) {
            throw new Exception("Kh??ng th??? ?????c c??c th??ng tin ????ng nh???p.<br>H??y ????ng xu???t r???i ????ng nh???p l???i.");
        }
        if (s.equals("c")) {
            this.checkUpche(s2);
        }
        else if (s.equals("g")) {
            this.checkGigwan(this.ID);
        }
        else if (s.equals("k")) {
            this.checkGigwanApprove();
        }
        else if (s.equals("a")) {
            this.checkUpcheApprove();
        }
        else if (s.equals("ca")) {
            this.checkCookieCA(s2);
        }
        else if (s.equals("cga")) {
            this.checkCookieCGA(s2, this.ID);
        }
        else if (s.equals("ka")) {
            this.checkCookieKA();
        }
        else if (s.equals("gah")) {
            this.checkCookieGAH();
        }
        else if (s.equals("ga")) {
            this.checkCookieGA();
        }
        else if (s.equals("gaz")) {
            this.checkCookieGAZ();
        }
        else if (s.equals("gh")) {
            this.checkCookieGH();
        }
        else if (s.equals("gazm")) {
            this.checkCookieGAZM();
        }
        else if (s.equals("gazmj")) {
            this.checkCookieGAZMJ();
        }
        else if (s.equals("gam")) {
            this.checkCookieGAM();
        }
        else if (s.equals("x")) {
            this.checkCookieX();
        }
        else if (s.equals("d")) {
            this.checkCookieD();
        }
        else if (s.equals("ghi")) {
            this.checkCookieGHI();
        }
    }
    
    public void checkModifyPasswd() throws Exception {
        if (this.getGubun() == null) {
            Log.debug(2, this.getClass().getName() + ".checkModifyPasswd() IP[" + this.getIP() + "] getGubun() is null");
            throw new Exception("Kh??ng th??? ?????c c??c th??ng tin ????ng nh???p.<br>H??y ????ng xu???t r???i ????ng nh???p l???i.");
        }
        if (!this.getGubun().equals("k")) {}
    }
    
    private void checkUpche(final String s) throws Exception {
        if (!this.getGubun().equals("c")) {
            throw new Exception("Kh??ng ph???i l?? ng?????i s??? d???ng doanh nghi???p.");
        }
        if (s != null && !this.getMCode().equals(s)) {
            throw new Exception("Kh??ng ???????c ph??p s??? d???ng c??c ch????ng tr??nh.");
        }
    }
    
    private void checkGigwan(final String s) throws Exception {
        if (!this.getGubun().equals("g")) {
            throw new Exception("C??c t??? ch???c ch??nh ph???, kh??ng ph???i ng?????i d??ng.");
        }
        if (s != null && !this.getMCode().equals(s)) {
            throw new Exception("Kh??ng ???????c ph??p s??? d???ng c??c ch????ng tr??nh.");
        }
    }
    
    private void checkGigwanApprove() throws Exception {
        if (!this.getGubun().equals("k")) {
            throw new Exception("C?? quan kh??ng ???????c ch???p thu???n.");
        }
    }
    
    private void checkUpcheApprove() throws Exception {
        if (!this.getGubun().equals("a")) {
            throw new Exception("Doanh nghi???p kh??ng ???????c ch???p thu???n.");
        }
    }
    
    private void checkCookieCA(final String s) throws Exception {
        if (this.getGubun().equals("c")) {
            this.checkUpche(s);
        }
        else {
            if (!this.getGubun().equals("a")) {
                throw new Exception("Kh??ng ???????c ph??p s??? d???ng c??c ch????ng tr??nh.");
            }
            this.checkUpcheApprove();
        }
    }
    
    private void checkCookieGAZM() throws Exception {
        if (this.getGubun().equals("g")) {
            this.checkGigwan(null);
        }
        else if (this.getGubun().equals("a")) {
            this.checkUpcheApprove();
        }
        else if (this.getGubun().equals("z")) {
            this.checkCookieZ();
        }
        else {
            if (!this.getGubun().equals("m")) {
                throw new Exception("Kh??ng ???????c ph??p s??? d???ng c??c ch????ng tr??nh.");
            }
            this.checkCookieM();
        }
    }
    
    private void checkCookieGAZMJ() throws Exception {
        if (this.getGubun().equals("g")) {
            this.checkGigwan(null);
        }
        else if (this.getGubun().equals("a")) {
            this.checkUpcheApprove();
        }
        else if (this.getGubun().equals("z")) {
            this.checkCookieZ();
        }
        else if (this.getGubun().equals("m")) {
            this.checkCookieM();
        }
        else {
            if (!this.getGubun().equals("j")) {
                throw new Exception("Kh??ng ???????c ph??p s??? d???ng c??c ch????ng tr??nh.");
            }
            this.checkCookieJ();
        }
    }
    
    private void checkCookieGAM() throws Exception {
        if (this.getGubun().equals("g")) {
            this.checkGigwan(null);
        }
        else if (this.getGubun().equals("a")) {
            this.checkUpcheApprove();
        }
        else {
            if (!this.getGubun().equals("m")) {
                throw new Exception("Kh??ng ???????c ph??p s??? d???ng c??c ch????ng tr??nh.");
            }
            this.checkCookieM();
        }
    }
    
    private void checkCookieCGA(final String s, final String s2) throws Exception {
        if (this.getGubun().equals("c")) {
            this.checkUpche(s);
        }
        else if (this.getGubun().equals("a")) {
            this.checkUpcheApprove();
        }
        else if (this.getGubun().equals("g")) {
            this.checkGigwan(s2);
        }
        else {
            if (!this.getGubun().equals("k")) {
                throw new Exception("Kh??ng ???????c ph??p s??? d???ng c??c ch????ng tr??nh.");
            }
            this.checkGigwanApprove();
        }
    }
    
    private void checkCookieKA() throws Exception {
        if (this.getGubun().equals("k")) {
            this.checkGigwanApprove();
        }
        else {
            if (!this.getGubun().equals("a")) {
                throw new Exception("Kh??ng ???????c ph??p s??? d???ng c??c ch????ng tr??nh.");
            }
            this.checkUpcheApprove();
        }
    }
    
    private void checkCookieGAH() throws Exception {
        if (this.getGubun().equals("g")) {
            this.checkGigwan(null);
        }
        else if (this.getGubun().equals("a")) {
            this.checkUpcheApprove();
        }
        else {
            if (!this.getGubun().equals("h")) {
                throw new Exception("Kh??ng ???????c ph??p s??? d???ng c??c ch????ng tr??nh.");
            }
            this.checkCookieH();
        }
    }
    
    private void checkCookieGAZ() throws Exception {
        if (this.getGubun().equals("g")) {
            this.checkGigwan(null);
        }
        else if (this.getGubun().equals("a")) {
            this.checkUpcheApprove();
        }
        else {
            if (!this.getGubun().equals("z")) {
                throw new Exception("Kh??ng ???????c ph??p s??? d???ng c??c ch????ng tr??nh.");
            }
            this.checkCookieZ();
        }
    }
    
    private void checkCookieH() throws Exception {
        if (!this.getGubun().equals("h")) {
            throw new Exception("Kh??ng ???????c b???o ?????m.");
        }
    }
    
    private void checkCookieZ() throws Exception {
        if (!this.getGubun().equals("z")) {
            throw new Exception("Kh??ng ph???i l?? ng?????i ?????i di???n.");
        }
    }
    
    private void checkCookieM() throws Exception {
        if (!this.getGubun().equals("m")) {
            throw new Exception("Kh??ng ph???i l?? qu???n l??.");
        }
    }
    
    private void checkCookieJ() throws Exception {
        if (!this.getGubun().equals("j")) {
            throw new Exception("Kh??ng ph???i l?? ng?????i ?????i di???n.");
        }
    }
    
    private void checkCookieGA() throws Exception {
        if (this.getGubun().equals("g")) {
            this.checkGigwan(null);
        }
        else {
            if (!this.getGubun().equals("a")) {
                throw new Exception("Kh??ng ???????c ph??p s??? d???ng c??c ch????ng tr??nh.");
            }
            this.checkUpcheApprove();
        }
    }
    
    private void checkCookieGH() throws Exception {
        if (this.getGubun().equals("g")) {
            this.checkGigwan(null);
        }
        else {
            if (!this.getGubun().equals("h")) {
                throw new Exception("Kh??ng ???????c ph??p s??? d???ng c??c ch????ng tr??nh.");
            }
            this.checkCookieH();
        }
    }
    
    private void checkCookieGHI() throws Exception {
        if (this.getGubun().equals("g")) {
            this.checkGigwan(null);
        }
        else if (this.getGubun().equals("h")) {
            this.checkCookieH();
        }
        else if (!this.getID().equals("CZ00608600001")) {
            throw new Exception("Kh??ng ???????c ph??p s??? d???ng c??c ch????ng tr??nh.");
        }
    }
    
    private void checkCookieX() throws Exception {
        if (!this.getGubun().equals("x")) {
            throw new Exception("Kh??ng ph???i h??ng h??a c???a doanh nghi???p.");
        }
    }
    
    private void checkCookieD() throws Exception {
        if (!this.getGubun().equals("d")) {
            throw new Exception("Ng?????i s??? d???ng kh??ng ph???i qu???n l??.");
        }
    }
    
    public boolean isSosokEquals(final String s, final String s2, final Connection conn) throws Exception {
        try {
            if (PublicUtil.retSpace(s).equals("")) {
                throw new Exception(" C???n ph???i ki???m tra tham s???");
            }
            if (PublicUtil.retSpace(s2).equals("")) {
                throw new Exception("C???n ph???i ki???m tra tham s???");
            }
            return new ComDbQuery().getCount(this, "usemn", " select count(*)  from (select a.MAST_CD from UM_USER a where a.USER_ID=?) A, (select b.MAST_CD from UM_USER b where b.USER_ID=?) B where A.MAST_CD=B.MAST_CD", new String[] { s, s2 }, conn) > 0;
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".isSosokEquals() userID1[" + s + "], userID2[" + s2 + "]:" + ex.toString());
            throw ex;
        }
    }
}
