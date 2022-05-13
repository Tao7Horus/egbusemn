// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.util.StringTokenizer;

public class StandardPhonNumber
{
    private String firstNum;
    private String middleNum;
    private String lastNum;
    private String fullNum;
    
    public StandardPhonNumber() {
        this.firstNum = "";
        this.middleNum = "";
        this.lastNum = "";
        this.fullNum = "";
    }
    
    public StandardPhonNumber(final String pNum) {
        try {
            final StringTokenizer st = new StringTokenizer(pNum, "-");
            if (st.countTokens() == 3) {
                this.firstNum = pNum.substring(0, pNum.indexOf("-"));
                this.middleNum = pNum.substring(pNum.indexOf("-") + 1, pNum.lastIndexOf("-"));
                this.lastNum = pNum.substring(pNum.lastIndexOf("-") + 1);
                this.fullNum = pNum;
            }
            else if (st.countTokens() == 2) {
                this.firstNum = pNum.substring(0, pNum.indexOf("-"));
                this.middleNum = pNum.substring(pNum.indexOf("-") + 1);
                this.lastNum = "";
                this.fullNum = pNum;
            }
            else if (st.countTokens() == 1 || st.countTokens() == 0) {
                this.firstNum = pNum;
                this.middleNum = "";
                this.lastNum = "";
                this.fullNum = pNum;
            }
            else {
                this.firstNum = pNum.substring(0, pNum.indexOf("-"));
                this.middleNum = pNum.substring(pNum.indexOf("-") + 1, pNum.lastIndexOf("-"));
                this.lastNum = pNum.substring(pNum.lastIndexOf("-") + 1);
                this.fullNum = pNum;
            }
        }
        catch (Exception e) {
            this.firstNum = pNum;
            this.middleNum = "";
            this.lastNum = "";
            this.fullNum = pNum;
        }
    }
    
    public void setStandardPhonNumber(final String pNum) {
        try {
            final StringTokenizer st = new StringTokenizer(pNum, "-");
            if (st.countTokens() == 3) {
                this.firstNum = pNum.substring(0, pNum.indexOf("-"));
                this.middleNum = pNum.substring(pNum.indexOf("-") + 1, pNum.lastIndexOf("-"));
                this.lastNum = pNum.substring(pNum.lastIndexOf("-") + 1);
                this.fullNum = pNum;
            }
            else if (st.countTokens() == 2) {
                this.firstNum = pNum.substring(0, pNum.indexOf("-"));
                this.middleNum = pNum.substring(pNum.indexOf("-") + 1);
                this.lastNum = "";
                this.fullNum = pNum;
            }
            else if (st.countTokens() == 1 || st.countTokens() == 0) {
                this.firstNum = pNum;
                this.middleNum = "";
                this.lastNum = "";
                this.fullNum = pNum;
            }
            else {
                this.firstNum = pNum.substring(0, pNum.indexOf("-"));
                this.middleNum = pNum.substring(pNum.indexOf("-") + 1, pNum.lastIndexOf("-"));
                this.lastNum = pNum.substring(pNum.lastIndexOf("-") + 1);
                this.fullNum = pNum;
            }
        }
        catch (Exception e) {
            this.firstNum = pNum;
            this.middleNum = "";
            this.lastNum = "";
            this.fullNum = pNum;
        }
    }
    
    public String getFirstNum() {
        return this.firstNum;
    }
    
    public String getMiddleNum() {
        return this.middleNum;
    }
    
    public String getLastNum() {
        return this.lastNum;
    }
    
    public String getFullNum() {
        return this.fullNum;
    }
    
    public void setFirstNum(final String firstNum) {
        this.firstNum = firstNum;
    }
    
    public void setMiddleNum(final String middleNum) {
        this.middleNum = middleNum;
    }
    
    public void setLastNum(final String lastNum) {
        this.lastNum = lastNum;
    }
    
    public void setFullNum(final String fullNum) {
        this.fullNum = fullNum;
    }
}
