// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.common.admin.datamodel.AdminInfo;
import java.util.Vector;
import common.Log;
import javax.naming.InitialContext;
import portal.common.admin.session.AdminHome;
import portal.common.admin.session.Admin;

public class AdminConn
{
    private Admin admin;
    
    public AdminConn() {
        this.admin = null;
        try {
            this.admin = ((AdminHome)new InitialContext().lookup("Admin")).create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public Vector adminDiv() {
        Vector itemDiv = null;
        try {
            itemDiv = this.admin.itemDiv();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDiv;
    }
    
    public Vector adminList(final String s) {
        Vector itemList = null;
        try {
            itemList = this.admin.itemList(s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemList;
    }
    
    public AdminInfo adminGet(final String s, final int n) {
        AdminInfo itemGet = null;
        try {
            itemGet = this.admin.itemGet(s, n);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemGet;
    }
    
    public int adminAdd(final String s, final String s2, final String s3) {
        int itemAdd = 0;
        try {
            itemAdd = this.admin.itemAdd(s, s2, s3);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemAdd;
    }
    
    public int adminDel(final String s, final int n) {
        int itemDel = 0;
        try {
            itemDel = this.admin.itemDel(s, n);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDel;
    }
    
    public boolean adminCheck(final String s, final String s2) {
        boolean itemCheck = false;
        try {
            itemCheck = this.admin.itemCheck(s, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemCheck;
    }
    
    public Vector adminCharge(final String s) {
        Vector itemCharge = null;
        try {
            itemCharge = this.admin.itemCharge(s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemCharge;
    }
}
