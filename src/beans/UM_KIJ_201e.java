// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import common.AbstractEntity;

public class UM_KIJ_201e extends AbstractEntity
{
    private String codeGbn;
    private String code;
    private String codeNm1;
    private String codeNm2;
    private String useYn;
    private String bigo;
    private String regId;
    private String regDate;
    private String attrCode;
    private String attrValue;
    private String copYn;
    
    public UM_KIJ_201e() {
    }
    
    public UM_KIJ_201e(final String codeGbn, final String code, final String codeNm1, final String codeNm2, final String useYn, final String bigo, final String regId, final String regDate, final String attrCode, final String attrValue, final String copYn) {
        this.codeGbn = codeGbn;
        this.code = code;
        this.codeNm1 = codeNm1;
        this.codeNm2 = codeNm2;
        this.useYn = useYn;
        this.bigo = bigo;
        this.regId = regId;
        this.regDate = regDate;
        this.attrCode = attrCode;
        this.attrValue = attrValue;
        this.copYn = copYn;
    }
    
    public String getCodeGbn() {
        return this.codeGbn;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public String getCodeNm1() {
        return this.codeNm1;
    }
    
    public String getCodeNm2() {
        return this.codeNm2;
    }
    
    public String getUseYn() {
        return this.useYn;
    }
    
    public String getBigo() {
        return this.bigo;
    }
    
    public String getRegId() {
        return this.regId;
    }
    
    public String getRegDate() {
        return this.regDate;
    }
    
    public String getAttrCode() {
        return this.attrCode;
    }
    
    public String getAttrValue() {
        return this.attrValue;
    }
    
    public String getCopYn() {
        return this.copYn;
    }
}
