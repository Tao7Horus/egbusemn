// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import javax.servlet.ServletOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServlet;

public class Upload extends HttpServlet
{
    private final String PRGNAME = "UPLOAD";
    private final String PRGCODE = "UP";
    private Logger log;
    
    public Upload() {
        throw new Error("Unresolved compilation problems: \n\tThe import common.Config cannot be resolved\n\tThe import common.ComComm cannot be resolved\n\tThe import common.ErrMsg cannot be resolved\n\tThe import common.Jodal cannot be resolved\n\tThe import common.eMartRoutine cannot be resolved\n\tThe import common.vo cannot be resolved\n\tThe import common.vo cannot be resolved\n\tThe import daemon cannot be resolved\n\tThe import daemon cannot be resolved\n\tThe import daemon cannot be resolved\n\tErrMsg cannot be resolved to a type\n\tErrMsg cannot be resolved to a type\n\tComComm cannot be resolved to a type\n\tComComm cannot be resolved to a type\n\tMyUtil cannot be resolved to a type\n\tMyUtil cannot be resolved to a type\n\tUser cannot be resolved to a type\n\tUser cannot be resolved to a type\n\tUserCheck cannot be resolved to a type\n\tUserCheck cannot be resolved to a type\n\tAnalyze cannot be resolved to a type\n\tAnalyze cannot be resolved to a type\n\tTrace cannot be resolved to a type\n\tTrace cannot be resolved to a type\n\tJodal cannot be resolved\n\tThe method format(String, Object[]) in the type String is not applicable for the arguments (String, String)\n\tConfig cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tComComm cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tConfig cannot be resolved\n\tConfig cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tThe method format(Locale, String, Object[]) in the type String is not applicable for the arguments (String, String, String)\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tThe method format(Locale, String, Object[]) in the type String is not applicable for the arguments (String, String, String)\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tErrMsg cannot be resolved to a type\n\tThe method format(String, Object[]) in the type String is not applicable for the arguments (String, String, int)\n\tThe method format(Locale, String, Object[]) in the type String is not applicable for the arguments (String, String, String)\n\tThe method format(String, Object[]) in the type String is not applicable for the arguments (String, String)\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tMyUtil cannot be resolved to a type\n\tJodal cannot be resolved\n");
    }
    
    public void init(final ServletConfig config) throws ServletException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public void destroy() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        throw new Error("Unresolved compilation problems: \n\tErrMsg cannot be resolved to a type\n\tErrMsg cannot be resolved to a type\n\tComComm cannot be resolved to a type\n\tComComm cannot be resolved to a type\n\tMyUtil cannot be resolved to a type\n\tMyUtil cannot be resolved to a type\n\tUser cannot be resolved to a type\n\tUser cannot be resolved to a type\n\tUserCheck cannot be resolved to a type\n\tUserCheck cannot be resolved to a type\n\tAnalyze cannot be resolved to a type\n\tAnalyze cannot be resolved to a type\n\tTrace cannot be resolved to a type\n\tTrace cannot be resolved to a type\n\tJodal cannot be resolved\n\tThe method format(String, Object[]) in the type String is not applicable for the arguments (String, String)\n\tConfig cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tComComm cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tConfig cannot be resolved\n\tConfig cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tThe method format(Locale, String, Object[]) in the type String is not applicable for the arguments (String, String, String)\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tThe method format(Locale, String, Object[]) in the type String is not applicable for the arguments (String, String, String)\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n");
    }
    
    public String mySaveBodyCnt(final String cpUnikey, final String cpBackupDir, final String cpFullPath, final String cntType, final StringBuffer bodyCnt, final BufferedReader br, final ErrMsg err) {
        throw new Error("Unresolved compilation problems: \n\tErrMsg cannot be resolved to a type\n\tThe method format(String, Object[]) in the type String is not applicable for the arguments (String, String, int)\n\tThe method format(Locale, String, Object[]) in the type String is not applicable for the arguments (String, String, String)\n\tThe method format(String, Object[]) in the type String is not applicable for the arguments (String, String)\n\tJodal cannot be resolved\n\tJodal cannot be resolved\n");
    }
    
    public void myEndProcess(final ServletOutputStream out, final int retval, final String caXmlID, final MyUtil util) throws IOException {
        throw new Error("Unresolved compilation problems: \n\tMyUtil cannot be resolved to a type\n\tJodal cannot be resolved\n");
    }
    
    public String myGetXmlId(final StringBuffer bodycnt) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public String myGetDescription(final StringBuffer bodycnt) {
        throw new Error("Unresolved compilation problem: \n");
    }
}
