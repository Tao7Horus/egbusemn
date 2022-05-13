// 
// Decompiled by Procyon v0.5.30
// 

package exms.xml;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import com.exln.dxe.Session;
import javax.servlet.ServletException;
import java.io.InputStream;
import com.exln.dxe.XlnProperties;
import java.text.SimpleDateFormat;
import java.util.Date;
import datec.xis.session.ClientSession;

public class CallRepServlet
{
    public String main_process(final String filename, final String filepath) {
        final Session session = ClientSession.getInstance("XLNDB", 1050).getSession("ppsxml", "update");
        final Date yyyymmdd = new Date();
        final SimpleDateFormat myformat = new SimpleDateFormat("yyyyMMdd");
        final String year = filename.substring(1, 5);
        final String month = filename.substring(5, 7);
        final String day = filename.substring(7, 9);
        final String xmldocu = readFromFile(filepath);
        final XlnProperties theProperties = new XlnProperties();
        theProperties.setProperty("xmlstring", xmldocu);
        theProperties.setProperty("xmlstorename", year);
        theProperties.setProperty("directoryname", "/" + month);
        theProperties.setProperty("subdirname", day);
        theProperties.setProperty("xmlfilename", String.valueOf(filename) + ".0");
        InputStream responseStream = null;
        try {
            responseStream = (InputStream)session.executeServlet("xmltorep", theProperties, (InputStream)null);
        }
        catch (ServletException e) {
            System.out.println("1 :");
            e.printStackTrace();
            return "-8[CallRepServlet] Repository 저장 실패: 예외발생" + e.getMessage();
        }
        final String sResult = getString(responseStream).trim();
        if (!sResult.equals("true")) {
            return "-8[CallRepServlet] Repository 저장 실패";
        }
        return sResult;
    }
    
    public static String getString(final InputStream theStream) {
        String output = "";
        try {
            for (int n = theStream.available(); n > 0; n = theStream.available()) {
                final byte[] bytes = new byte[n];
                theStream.read(bytes);
                output = String.valueOf(output) + new String(bytes);
            }
        }
        catch (IOException e) {
            System.out.println("2 :");
            e.printStackTrace();
            return "-8[CallRepServlet] Repository 저장 실패: 예외발생" + e.getMessage();
        }
        return output;
    }
    
    private static String readFromFile(final String _FilePath) {
        FileInputStream in = null;
        ByteArrayOutputStream baos = null;
        try {
            in = new FileInputStream(_FilePath);
            baos = new ByteArrayOutputStream();
            String rlt = "";
            final byte[] buff = new byte[8192];
            int ret = 0;
            while ((ret = in.read(buff)) != -1) {
                baos.write(buff, 0, ret);
            }
            baos.flush();
            rlt = baos.toString("KSC5601");
            return rlt;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            }
            catch (IOException ex) {}
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ex2) {}
        }
    }
}
