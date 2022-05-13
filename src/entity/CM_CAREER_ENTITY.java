// 
// Decompiled by Procyon v0.5.30
// 

package entity;

import java.util.Date;

public class CM_CAREER_ENTITY
{
    private String cd;
    private String cdNm;
    private String summary;
    private Date inputDt;
    private Date updateDt;
    
    public String getCd() {
        return this.cd;
    }
    
    public void setCd(final String cd) {
        this.cd = cd;
    }
    
    public String getCdNm() {
        return this.cdNm;
    }
    
    public void setCdNm(final String cdNm) {
        this.cdNm = cdNm;
    }
    
    public String getSummary() {
        return this.summary;
    }
    
    public void setSummary(final String summary) {
        this.summary = summary;
    }
    
    public Date getInputDt() {
        return this.inputDt;
    }
    
    public void setInputDt(final Date inputDt) {
        this.inputDt = inputDt;
    }
    
    public Date getUpdateDt() {
        return this.updateDt;
    }
    
    public void setUpdateDt(final Date updateDt) {
        this.updateDt = updateDt;
    }
}
