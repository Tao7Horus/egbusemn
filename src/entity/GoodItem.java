// 
// Decompiled by Procyon v0.5.30
// 

package entity;

public class GoodItem
{
    private String goodCode;
    private String mastGoodYn;
    
    public String getGoodCode() {
        return this.goodCode;
    }
    
    public void setGoodCode(final String goodCode) {
        this.goodCode = goodCode;
    }
    
    public String getMastGoodYn() {
        return this.mastGoodYn;
    }
    
    public void setMastGoodYn(final String mastGoodYn) {
        this.mastGoodYn = mastGoodYn;
    }
    
    public GoodItem(final String goodCode, final String mastGoodYn) {
        this.goodCode = goodCode;
        this.mastGoodYn = mastGoodYn;
    }
}
