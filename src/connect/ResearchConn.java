// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.customer.research.datamodel.ResearchStsInfo;
import portal.customer.research.datamodel.ResearchSaveInfo;
import portal.log.portalLog;
import javax.naming.InitialContext;
import portal.customer.research.session.Research;
import portal.customer.research.session.ResearchHome;

public class ResearchConn
{
    private ResearchHome pcHome;
    private Research pcRemote;
    protected final boolean IS_LOG = false;
    protected final String LOGFILE = "Research.log";
    protected final String ERRFILE = "ResearchErr.log";
    protected String THIS_CLASS_NAME;
    
    public ResearchConn() {
        this.pcHome = null;
        this.pcRemote = null;
        this.THIS_CLASS_NAME = "ResearchConn";
        try {
            this.pcHome = (ResearchHome)new InitialContext().lookup("Research");
            this.pcRemote = this.pcHome.create();
        }
        catch (Exception ex) {
            portalLog.log("ResearchErr.log", "[" + this.THIS_CLASS_NAME + ":ResearchConn()] Exception\n" + ex.toString());
        }
    }
    
    public int setResearch(final ResearchSaveInfo research) {
        int setResearch = -1;
        try {
            setResearch = this.pcRemote.setResearch(research);
        }
        catch (Exception ex) {
            portalLog.log("ResearchErr.log", "[" + this.THIS_CLASS_NAME + ":setResearch(ResearchSaveInfo)] Exception\n" + ex.toString());
        }
        return setResearch;
    }
    
    public void setReport(final String s, final ResearchSaveInfo researchSaveInfo) {
        try {
            this.pcRemote.setReport(s, researchSaveInfo);
        }
        catch (Exception ex) {
            portalLog.log("ResearchErr.log", "[" + this.THIS_CLASS_NAME + ":setReport(String, ResearchSaveInfo)] Exception\n" + ex.toString());
        }
    }
    
    public ResearchStsInfo getStsResearch(final String s, final String s2) {
        ResearchStsInfo stsResearch = null;
        try {
            stsResearch = this.pcRemote.getStsResearch(s, s2);
            return stsResearch;
        }
        catch (Exception ex) {
            portalLog.log("ResearchErr.log", "[" + this.THIS_CLASS_NAME + ":getStsResearch(String*2)] Exception\n" + ex.toString());
        }
        finally {
            return stsResearch;
        }
    }
}
