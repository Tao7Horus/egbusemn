// 
// Decompiled by Procyon v0.5.30
// 

package left;

import common.Log;
import common.CommEntity;

public class LeftMenuBc
{
    protected String CLF;
    
    public LeftMenuBc() {
        this.CLF = " \n";
    }
    
    public CommEntity[] getNonMenu(final String s, final String s2) throws Exception {
        final LeftMenuDao leftMenuDao = new LeftMenuDao();
        CommEntity[] adminMenuQuery = null;
        try {
            adminMenuQuery = leftMenuDao.getAdminMenuQuery(s2);
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + " : " + ex.toString());
        }
        return adminMenuQuery;
    }
    
    public CommEntity[] getMenu(final String s, final String s2) throws Exception {
        final LeftMenuDao leftMenuDao = new LeftMenuDao();
        CommEntity[] menuQuery = null;
        try {
            menuQuery = leftMenuDao.getMenuQuery(s, s2);
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + " : " + ex.toString());
        }
        return menuQuery;
    }
    
    public CommEntity[] getGroupMenuQuery(final String s, final String s2) throws Exception {
        final LeftMenuDao leftMenuDao = new LeftMenuDao();
        CommEntity[] groupMenuQuery = null;
        try {
            groupMenuQuery = leftMenuDao.getGroupMenuQuery(s, s2);
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + " : " + ex.toString());
        }
        return groupMenuQuery;
    }
}
