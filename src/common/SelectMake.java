// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.util.Enumeration;
import java.util.Hashtable;

public class SelectMake
{
    public String getSelectBoxWithStringArray(final String[] arrayString, final String name, final String selected, final String javascript) throws Exception {
        String selectBox = "";
        if (javascript != null) {
            selectBox = "<select name='" + name + "' class='read' " + javascript + ">" + "\n";
        }
        else {
            selectBox = "<select name='" + name + "' class='read'>" + "\n";
        }
        for (int i = 0; i < arrayString.length; ++i) {
            if (arrayString[i].equals(selected)) {
                selectBox = String.valueOf(selectBox) + "\t<option value='" + arrayString[i] + "' selected>" + arrayString[i] + "</option>\n";
            }
            else {
                selectBox = String.valueOf(selectBox) + "\t<option value='" + arrayString[i] + "'>" + arrayString[i] + "</option>\n";
            }
        }
        selectBox = String.valueOf(selectBox) + "</select>";
        return selectBox;
    }
    
    public String getSelectBoxWithHash(final Hashtable arrayString, final String name, final String selected, final String javascript) throws Exception {
        String selectBox = "";
        if (javascript != null) {
            selectBox = "<select name='" + name + "' class='read' " + javascript + ">" + "\n";
        }
        else {
            selectBox = "<select name='" + name + "' class='read'>" + "\n";
        }
        final Enumeration list = arrayString.keys();
        String key = "";
        while (list.hasMoreElements()) {
            key = list.nextElement();
            if (selected != null) {
                if (key.equals(selected)) {
                    selectBox = String.valueOf(selectBox) + "\t<option value='" + key + "' selected>" + arrayString.get(key) + "</option>\n";
                }
                else {
                    selectBox = String.valueOf(selectBox) + "\t<option value='" + key + "'>" + arrayString.get(key) + "</option>\n";
                }
            }
            else {
                selectBox = String.valueOf(selectBox) + "\t<option value='" + key + "'>" + arrayString.get(key) + "</option>\n";
            }
        }
        selectBox = String.valueOf(selectBox) + "</select>";
        return selectBox;
    }
    
    public String getSelectBoxWithEntity(final CommEntity[] listEntity, final String name, final String selected, final String javascript) throws Exception {
        try {
            if (listEntity == null || listEntity.length == 0) {
                throw new Exception("데이타 확인 필요(Entity 이상]");
            }
            String selectBox = "";
            if (javascript != null) {
                selectBox = "<select name='" + name + "' class='read' " + javascript + ">" + "\n";
            }
            else {
                selectBox = "<select name='" + name + "' class='read'>" + "\n";
            }
            for (int i = 0, n = listEntity.length; i < n; ++i) {
                if (selected != null) {
                    if (CommUtil.retSpace(listEntity[i].data[0]).equals(selected)) {
                        selectBox = String.valueOf(selectBox) + "\t<option value='" + CommUtil.retSpace(listEntity[i].data[0]) + "' selected>" + CommUtil.retSpace(listEntity[i].data[1]) + "</option>\n";
                    }
                    else {
                        selectBox = String.valueOf(selectBox) + "\t<option value='" + CommUtil.retSpace(listEntity[i].data[0]) + "'>" + CommUtil.retSpace(listEntity[i].data[1]) + "</option>\n";
                    }
                }
                else {
                    selectBox = String.valueOf(selectBox) + "\t<option value='" + CommUtil.retSpace(listEntity[i].data[0]) + "'>" + CommUtil.retSpace(listEntity[i].data[1]) + "</option>\n";
                }
            }
            selectBox = String.valueOf(selectBox) + "</select>";
            return selectBox;
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getSelectBoxWithEntity() name[" + name + "],selected[" + selected + "],javascript[" + javascript + "] : " + exm.toString());
            throw exm;
        }
    }
}
