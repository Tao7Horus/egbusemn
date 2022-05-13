// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.lang.reflect.Method;
import java.util.StringTokenizer;
import java.lang.reflect.Field;
import java.io.Serializable;

public abstract class AbstractEntity implements Entity, Serializable
{
    public String showAttributes() {
        final Class c = this.getClass();
        final Field[] attrList = c.getDeclaredFields();
        String str = "> [name] : [value] \n";
        for (int i = 0; i < attrList.length; ++i) {
            str = String.valueOf(str) + "> " + attrList[i].toString() + " : " + this.getValue(attrList[i]) + "\n";
        }
        System.out.println(str);
        return str;
    }
    
    private String getValue(final Field field) {
        String attrName = field.getName();
        final StringTokenizer tok = new StringTokenizer(attrName, ".");
        while (tok.hasMoreElements()) {
            attrName = (String)tok.nextElement();
        }
        final String methodName = "get" + attrName.substring(0, 1).toUpperCase() + attrName.substring(1, attrName.length());
        Method method = null;
        try {
            method = this.getClass().getMethod(methodName, (Class<?>[])null);
        }
        catch (NoSuchMethodException ne) {
            return "(No get method!)";
        }
        Object result = null;
        try {
            result = method.invoke(this, (Object[])null);
        }
        catch (Exception ex) {}
        return String.valueOf(result);
    }
}
