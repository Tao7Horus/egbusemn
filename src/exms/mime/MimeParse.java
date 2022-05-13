// 
// Decompiled by Procyon v0.5.30
// 

package exms.mime;

import java.util.Properties;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import javax.mail.internet.MimeBodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.activation.FileDataSource;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.Vector;

public class MimeParse
{
    private String destSource;
    private String SavePath;
    private String SendCode;
    private Vector mimes;
    private StringBuffer LogMsg;
    
    public MimeParse(final String aSource, final String sendcode, final String savePath) {
        this.destSource = null;
        this.SavePath = null;
        this.SendCode = null;
        this.mimes = null;
        this.LogMsg = null;
        this.destSource = aSource;
        this.SavePath = savePath;
        this.SendCode = sendcode;
        this.mimes = new Vector();
        this.LogMsg = new StringBuffer(1024);
    }
    
    public Vector parseString() throws Exception {
        final ByteArrayInputStream in = new ByteArrayInputStream(this.destSource.getBytes());
        return this.doParse(in);
    }
    
    public Vector parseFile() throws Exception {
        final FileDataSource fds = new FileDataSource(this.destSource);
        return this.doParse(fds.getInputStream());
    }
    
    public Vector doParse(final InputStream in) throws Exception {
        InputStream is = null;
        FileOutputStream fout = null;
        BufferedOutputStream bout = null;
        System.out.println("Luu tap tin dinh kem : " + this.SavePath);
        try {
            final Properties sysprop = System.getProperties();
            final Session session = Session.getDefaultInstance(sysprop, (Authenticator)null);
            session.setDebug(true);
            System.out.println("Hoan thanh viec tao session");
            final MimeMessage msg = new MimeMessage(session, in);
            System.out.println("Hoan thanh tao ra cac doi tuong bai viet");
            this.SaveLog("MIME Content-Type =:  " + msg.getContentType());
            if (!msg.getContentType().trim().toLowerCase().startsWith("multipart")) {
                throw new Exception("Khong phai la thich hop du lieu 1 적절한 MIME데이타가 아닙니다.(Not multipart");
            }
            if (msg.isExpunged()) {
                throw new Exception("적절한 MIME데이타가 아닙니다.(Message is Expunged)");
            }
            final Multipart mp = (Multipart)msg.getContent();
            if (mp == null) {
                throw new Exception("Khong phai la thich hop du lieu 2적절한 MIME데이타가 아닙니다.(Not multipart");
            }
            System.out.println("Tren toan bo MIME tieu de phan tich");
            if (mp.getCount() < 1) {
                throw new Exception("Body can not find the information.");
            }
            this.SaveLog("Separate attachment to start.");
            for (int i = 0; i < mp.getCount(); ++i) {
                this.SaveLog("( " + i + " ) To analyze the attachment.");
                final byte[] buf = new byte[4096];
                final MimeBodyPart body1 = (MimeBodyPart)mp.getBodyPart(i);
                final int count = 0;
                int ret = 0;
                String fname = "";
                final File _fdir = new File(this.SavePath);
                if (!_fdir.exists()) {
                    _fdir.mkdirs();
                }
                fname = String.valueOf(this.SavePath) + this.SendCode + "." + ((i == 0) ? "s" : Integer.toString(i - 1));
                fout = new FileOutputStream(fname);
                bout = new BufferedOutputStream(fout, 16384);
                this.SaveLog("( " + i + " ) Stream of the file was created.");
                long totalbytes = 0L;
                is = body1.getDataHandler().getInputStream();
                String tmpString = null;
                while ((ret = is.read(buf)) != -1) {
                    totalbytes += ret;
                    tmpString = new String(buf, 0, ret);
                    Thread.sleep(3L);
                    bout.write(buf, 0, ret);
                    bout.flush();
                }
                try {
                    bout.close();
                }
                catch (Exception ex) {}
                try {
                    if (fout != null) {
                        fout.close();
                    }
                }
                catch (Exception ex2) {}
                try {
                    if (is != null) {
                        is.close();
                    }
                }
                catch (Exception ex3) {}
                if (totalbytes == 0L) {
                    final File fd = new File(fname);
                    fd.delete();
                }
                else {
                    final DAttachInfo attach = new DAttachInfo();
                    attach.SEQ = Integer.toString(i);
                    try {
                        attach.FileName = new String(body1.getFileName().getBytes("8859_1"), "KSC5601");
                    }
                    catch (Exception exc) {
                        attach.FileName = "";
                    }
                    attach.encoding = body1.getEncoding();
                    attach.fileSize = new File(fname).length();
                    attach.SavedPath = fname;
                    attach.Contents = ((!"8bit".equals(body1.getEncoding()) || i >= 2) ? "" : ((String)body1.getContent()));
                    this.mimes.addElement(attach);
                }
            }
        }
        catch (Exception e) {
            this.SaveLog("MIME File processing error has occurred. (Content : " + e.getMessage() + ")");
            throw new Exception("MIME File processing error has occurred. (Content: " + e.getMessage() + ")");
        }
        finally {
            try {
                in.close();
            }
            catch (Exception ex4) {}
            try {
                bout.close();
            }
            catch (Exception ex5) {}
            try {
                fout.close();
            }
            catch (Exception ex6) {}
            try {
                is.close();
            }
            catch (Exception ex7) {}
        }
        try {
            in.close();
        }
        catch (Exception ex8) {}
        try {
            bout.close();
        }
        catch (Exception ex9) {}
        try {
            fout.close();
        }
        catch (Exception ex10) {}
        try {
            is.close();
        }
        catch (Exception ex11) {}
        return this.mimes;
    }
    
    private void SaveLog(final String log) {
        System.out.println(log);
        this.LogMsg.append(String.valueOf(log) + "\n");
    }
    
    public String getLog() {
        return this.LogMsg.toString();
    }
}
