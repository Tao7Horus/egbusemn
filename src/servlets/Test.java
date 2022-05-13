// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

public class Test
{
    public static void main(final String[] args) {
        final String userID = "Ca000004G0099";
        System.out.println(addNumberToString(userID));
    }
    
    protected static String addNumberToString(final String userID) {
        final int isG = userID.lastIndexOf("G");
        String res = "";
        if (isG > -1) {
            final String digit = userID.substring(isG + 1);
            final int num = Integer.valueOf(userID.substring(isG + 1)) + 1;
            for (int len = 4 - String.valueOf(num).length(), i = 0; i < len; ++i) {
                res = String.valueOf(res) + "0";
            }
            return String.valueOf(userID.substring(0, isG + 1)) + res + num;
        }
        return String.valueOf(userID) + "0001";
    }
}
