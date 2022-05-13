// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import common.AbstractEntity;

public class UM_KIJ_101e extends AbstractEntity
{
    private String codeGbn;
    private String codeGbnNm;
    private String type;
    private String leng;
    private String useYn;
    private String regId;
    private String regDate;
    
    public UM_KIJ_101e() {
    }
    
    public UM_KIJ_101e(final String codeGbn, final String codeGbnNm, final String type, final String leng, final String useYn, final String regId, final String regDate) {
        this.codeGbn = codeGbn;
        this.codeGbnNm = codeGbnNm;
        this.type = type;
        this.leng = leng;
        this.useYn = useYn;
        this.regId = regId;
        this.regDate = regDate;
    }
    
    public String getCodeGbn() {
        return this.codeGbn;
    }
    
    public String getCodeGbnNm() {
        return this.codeGbnNm;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getLeng() {
        return this.leng;
    }
    
    public String getUseYn() {
        return this.useYn;
    }
    
    public String getRegId() {
        return this.regId;
    }
    
    public String getRegDate() {
        return this.regDate;
    }
}
