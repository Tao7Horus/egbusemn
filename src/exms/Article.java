// 
// Decompiled by Procyon v0.5.30
// 

package exms;

import java.sql.Timestamp;

public class Article
{
    private int seq;
    private int ref;
    private int step;
    private int lev;
    private String writer;
    private String subject;
    private String password;
    private String email;
    private int read;
    private String fileName;
    private String maskName;
    private double fileSize;
    private int download;
    private Timestamp when;
    private String ip;
    
    public Article(final int seq, final int ref, final int step, final int lev, final String writer, final String subject, final String password, final String email, int read, final String fileName, final String maskName, final double fileSize, final int download, final Timestamp when, final String ip) {
        read = 0;
        this.setContents(seq, ref, step, lev, writer, subject, password, email, read, fileName, maskName, fileSize, download, when, ip);
    }
    
    public void setContents(final int seq, final int ref, final int step, final int lev, final String writer, final String subject, final String password, final String email, final int read, final String fileName, final String maskName, final double fileSize, final int download, final Timestamp when, final String ip) {
        this.seq = seq;
        this.ref = ref;
        this.step = step;
        this.lev = lev;
        this.writer = writer;
        this.subject = subject;
        this.password = password;
        this.email = email;
        this.read = read;
        this.fileName = fileName;
        this.maskName = maskName;
        this.fileSize = fileSize;
        this.download = download;
        this.when = when;
        this.ip = ip;
    }
    
    public void setSeq(final int seq) {
        this.seq = seq;
    }
    
    public void setRef(final int ref) {
        this.ref = ref;
    }
    
    public void setStep(final int step) {
        this.step = step;
    }
    
    public void setLev(final int lev) {
        this.lev = lev;
    }
    
    public void setWriter(final String writer) {
        this.writer = writer;
    }
    
    public void setSubject(final String subject) {
        this.subject = subject;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public void setRead(final int read) {
        this.read = read;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    public void setMaskName(final String maskName) {
        this.maskName = maskName;
    }
    
    public void setFileSize(final double fileSize) {
        this.fileSize = fileSize;
    }
    
    public void setDownload(final int download) {
        this.download = download;
    }
    
    public void setWhen(final Timestamp when) {
        this.when = when;
    }
    
    public void setIp(final String ip) {
        this.ip = ip;
    }
    
    public int getSeq() {
        return this.seq;
    }
    
    public int getRef() {
        return this.ref;
    }
    
    public int getStep() {
        return this.step;
    }
    
    public int getLev() {
        return this.lev;
    }
    
    public String getWriter() {
        return this.writer;
    }
    
    public String getSubject() {
        return this.subject;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public int getRead() {
        return this.read;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public String getMaskName() {
        return this.maskName;
    }
    
    public double getFileSize() {
        return this.fileSize;
    }
    
    public int getDownload() {
        return this.download;
    }
    
    public Timestamp getWhen() {
        return this.when;
    }
    
    public String getIp() {
        return this.ip;
    }
}
