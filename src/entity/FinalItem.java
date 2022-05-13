// 
// Decompiled by Procyon v0.5.30
// 

package entity;

public class FinalItem
{
    private String year;
    private String ass_total;
    private String dept_total;
    private String ass_value;
    private String short_ass;
    private String short_dept;
    private String capital;
    
    public FinalItem(final String year, final String ass_total, final String dept_total, final String ass_value, final String short_ass, final String short_dept, final String capital) {
        this.year = year;
        this.ass_total = ass_total;
        this.dept_total = dept_total;
        this.ass_value = ass_value;
        this.short_ass = short_ass;
        this.short_dept = short_dept;
        this.capital = capital;
    }
    
    public String getYear() {
        return this.year;
    }
    
    public void setYear(final String year) {
        this.year = year;
    }
    
    public String getAss_total() {
        return this.ass_total;
    }
    
    public void setAss_total(final String ass_total) {
        this.ass_total = ass_total;
    }
    
    public String getDept_total() {
        return this.dept_total;
    }
    
    public void setDept_total(final String dept_total) {
        this.dept_total = dept_total;
    }
    
    public String getAss_value() {
        return this.ass_value;
    }
    
    public void setAss_value(final String ass_value) {
        this.ass_value = ass_value;
    }
    
    public String getShort_ass() {
        return this.short_ass;
    }
    
    public void setShort_ass(final String short_ass) {
        this.short_ass = short_ass;
    }
    
    public String getShort_dept() {
        return this.short_dept;
    }
    
    public void setShort_dept(final String short_dept) {
        this.short_dept = short_dept;
    }
    
    public String getCapital() {
        return this.capital;
    }
    
    public void setCapital(final String capital) {
        this.capital = capital;
    }
}
