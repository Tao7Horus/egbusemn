// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.util.Date;
import java.util.Vector;

public class ComboDate
{
    public Vector ComboYear;
    public Vector ComboMonth;
    public Vector ComboDay;
    public Date d;
    
    public ComboDate() {
        this.d = new Date();
        this.ComboYear = new Vector();
        this.ComboMonth = new Vector();
        this.ComboDay = new Vector();
    }
    
    public boolean GetCombo(final String name, final String ptrType, final int ptr) {
        int year = 0;
        int month = 0;
        int day = 0;
        int totalday = 0;
        this.ComboYear.clear();
        this.ComboMonth.clear();
        this.ComboDay.clear();
        try {
            year = this.d.getYear() + 1900;
            month = this.d.getMonth() + 1;
            day = this.d.getDate();
            if (ptrType.equals("YEAR")) {
                year -= ptr;
                totalday = this.GetTotalDay(month);
            }
            else if (ptrType.equals("MONTH")) {
                if (month + ptr < 0) {
                    --year;
                    month = month + 12 + ptr;
                }
                else {
                    month += ptr;
                }
                totalday = this.GetTotalDay(month);
            }
            else if (ptrType.equals("DAY")) {
                if (day + ptr < 0) {
                    if (month == 1) {
                        totalday = 31;
                    }
                    else {
                        totalday = this.GetTotalDay(month - 1);
                    }
                    if (totalday == -1) {
                        totalday = 30;
                    }
                    day = totalday + day + ptr;
                    --month;
                }
                else {
                    day += ptr;
                    totalday = this.GetTotalDay(month);
                }
            }
            String n = "<select name ='" + name + "Year' style=\"font-size:9pt;\">";
            this.ComboYear.addElement(n);
            for (int i = 2000; i < 2011; ++i) {
                n = "<option ";
                if (year == i) {
                    n = String.valueOf(n) + "selected ";
                }
                final String yearstr = String.valueOf(i);
                n = String.valueOf(n) + "value='" + yearstr + "'>" + yearstr + "</option>";
                this.ComboYear.addElement(n);
            }
            n = "</select>";
            this.ComboYear.addElement(n);
            n = "<select name='" + name + "Month' style=\"font-size:9pt;\">";
            this.ComboMonth.addElement(n);
            for (int i = 1; i < 13; ++i) {
                n = "<option ";
                if (month == i) {
                    n = String.valueOf(n) + "selected ";
                }
                String monthstr;
                if (i < 10) {
                    monthstr = String.valueOf("0" + i);
                }
                else {
                    monthstr = String.valueOf(i);
                }
                n = String.valueOf(n) + "value='" + monthstr + "'>" + monthstr + "</option>";
                this.ComboMonth.addElement(n);
            }
            n = "</select>";
            this.ComboMonth.addElement(n);
            n = "<select name='" + name + "Day' style=\"font-size:9pt;\">";
            this.ComboDay.addElement(n);
            for (int i = 1; i < 32; ++i) {
                n = "<option ";
                if (day == i) {
                    n = String.valueOf(n) + "selected ";
                }
                String daystr;
                if (i < 10) {
                    daystr = String.valueOf("0" + i);
                }
                else {
                    daystr = String.valueOf(i);
                }
                n = String.valueOf(n) + "value='" + daystr + "'>" + daystr + "</option>";
                this.ComboDay.addElement(n);
            }
            n = "</select>";
            this.ComboDay.addElement(n);
            return true;
        }
        catch (Exception y) {
            return false;
        }
    }
    
    public int GetTotalDay(final int month) {
        int year = 0;
        int day = 0;
        int totalday = 0;
        try {
            year = this.d.getYear() + 1900;
            day = this.d.getDate();
            if (month == 1) {
                totalday = 31;
            }
            else if (month == 3) {
                totalday = 31;
            }
            else if (month == 5) {
                totalday = 31;
            }
            else if (month == 7) {
                totalday = 31;
            }
            else if (month == 8) {
                totalday = 31;
            }
            else if (month == 10) {
                totalday = 31;
            }
            else if (month == 12) {
                totalday = 31;
            }
            else if (month == 4) {
                totalday = 30;
            }
            else if (month == 6) {
                totalday = 30;
            }
            else if (month == 9) {
                totalday = 30;
            }
            else if (month == 11) {
                totalday = 30;
            }
            else if (month == 2) {
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    totalday = 29;
                }
                else {
                    totalday = 28;
                }
            }
            return totalday;
        }
        catch (Exception y) {
            return -1;
        }
    }
}
