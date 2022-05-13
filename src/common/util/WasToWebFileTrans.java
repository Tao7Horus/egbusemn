// 
// Decompiled by Procyon v0.5.30
// 

package common.util;

import common.Log;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import java.util.Hashtable;

public class WasToWebFileTrans
{
    private static final String transSh130 = "/usr/bin/csh /data/USEMN/moveImage130.sh ";
    private static final String transSh133 = "/usr/bin/csh /data/USEMN/moveImage133.sh ";
    private static final String transSh160 = "/usr/bin/csh /data/USEMN/moveImage160.sh ";
    private static final String transSh163 = "/usr/bin/csh /data/USEMN/moveImage163.sh ";
    private static final String removeSh130 = "/usr/bin/csh /data/USEMN/removeImage130.sh ";
    private static final String removeSh133 = "/usr/bin/csh /data/USEMN/removeImage133.sh ";
    private static final String removeSh160 = "/usr/bin/csh /data/USEMN/removeImage160.sh ";
    private static final String removeSh163 = "/usr/bin/csh /data/USEMN/removeImage163.sh ";
    private String caller;
    private String clientIP;
    private String saupjaNumber;
    private Hashtable targetImage;
    
    public WasToWebFileTrans(final Object caller_obj, final HttpServletRequest req) throws Exception {
        this.caller = "unknown";
        this.clientIP = "unknown";
        this.saupjaNumber = "unknown";
        this.targetImage = null;
        if (caller_obj != null) {
            this.caller = caller_obj.getClass().getName();
        }
        this.clientIP = HttpUtility.getIP(req);
    }
    
    public boolean startIngamImageMove(final Hashtable hashImage, final String targetSaupjaNumber) throws Exception {
        this.targetImage = hashImage;
        this.saupjaNumber = targetSaupjaNumber;
        final Enumeration list = hashImage.keys();
        String key = "";
        Process proc130 = null;
        Process proc131 = null;
        Process proc132 = null;
        Process proc133 = null;
        while (list.hasMoreElements()) {
            key = list.nextElement();
            proc130 = Runtime.getRuntime().exec("/usr/bin/csh /data/USEMN/moveImage130.sh " + key + " " + hashImage.get(key));
            proc131 = Runtime.getRuntime().exec("/usr/bin/csh /data/USEMN/moveImage133.sh " + key + " " + hashImage.get(key));
            proc132 = Runtime.getRuntime().exec("/usr/bin/csh /data/USEMN/moveImage160.sh " + key + " " + hashImage.get(key));
            proc133 = Runtime.getRuntime().exec("/usr/bin/csh /data/USEMN/moveImage163.sh " + key + " " + hashImage.get(key));
            proc130.waitFor();
            proc131.waitFor();
            proc132.waitFor();
            proc133.waitFor();
        }
        return true;
    }
    
    public void removeImage() throws Exception {
        final Enumeration list = this.targetImage.keys();
        String key = "";
        while (list.hasMoreElements()) {
            key = list.nextElement();
            Runtime.getRuntime().exec("/usr/bin/csh /data/USEMN/removeImage130.sh " + this.targetImage.get(key));
            Runtime.getRuntime().exec("/usr/bin/csh /data/USEMN/removeImage133.sh " + this.targetImage.get(key));
            Runtime.getRuntime().exec("/usr/bin/csh /data/USEMN/removeImage160.sh " + this.targetImage.get(key));
            Runtime.getRuntime().exec("/usr/bin/csh /data/USEMN/removeImage163.sh " + this.targetImage.get(key));
        }
    }
    
    public void finalize() {
        try {
            this.removeImage();
        }
        catch (Exception ex) {}
        Log.debug("인감 이미지 was->web 이후 삭제처리 : caller[" + this.caller + "], CLIENT_IP[" + this.clientIP + "],사업자등록번호[" + this.saupjaNumber + "]");
    }
}
