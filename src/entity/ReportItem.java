// 
// Decompiled by Procyon v0.5.30
// 

package entity;

public class ReportItem
{
    private String year;
    private String REVENUE_TOTAL;
    private String AVERAGE_REVENUE;
    private String PROFIT_BEFORE;
    private String PROFIT_AFTER;
    
    public ReportItem(final String year, final String REVENUE_TOTAL, final String AVERAGE_REVENUE, final String PROFIT_BEFORE, final String PROFIT_AFTER) {
        this.year = year;
        this.REVENUE_TOTAL = REVENUE_TOTAL;
        this.AVERAGE_REVENUE = AVERAGE_REVENUE;
        this.PROFIT_BEFORE = PROFIT_BEFORE;
        this.PROFIT_AFTER = PROFIT_AFTER;
    }
    
    public String getYear() {
        return this.year;
    }
    
    public void setYear(final String year) {
        this.year = year;
    }
    
    public String getREVENUE_TOTAL() {
        return this.REVENUE_TOTAL;
    }
    
    public void setREVENUE_TOTAL(final String rEVENUE_TOTAL) {
        this.REVENUE_TOTAL = rEVENUE_TOTAL;
    }
    
    public String getAVERAGE_REVENUE() {
        return this.AVERAGE_REVENUE;
    }
    
    public void setAVERAGE_REVENUE(final String aVERAGE_REVENUE) {
        this.AVERAGE_REVENUE = aVERAGE_REVENUE;
    }
    
    public String getPROFIT_BEFORE() {
        return this.PROFIT_BEFORE;
    }
    
    public void setPROFIT_BEFORE(final String pROFIT_BEFORE) {
        this.PROFIT_BEFORE = pROFIT_BEFORE;
    }
    
    public String getPROFIT_AFTER() {
        return this.PROFIT_AFTER;
    }
    
    public void setPROFIT_AFTER(final String pROFIT_AFTER) {
        this.PROFIT_AFTER = pROFIT_AFTER;
    }
}
