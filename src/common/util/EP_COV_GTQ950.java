// 
// Decompiled by Procyon v0.5.30
// 

package common.util;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class EP_COV_GTQ950 extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcommon/util/EP_COB_GTQ805;
        //     3: dup            
        //     4: invokespecial   common/util/EP_COB_GTQ805.<init>:()V
        //     7: astore_3        /* fb */
        //     8: new             Lcommon/util/EP_COB_GTQ809;
        //    11: dup            
        //    12: invokespecial   common/util/EP_COB_GTQ809.<init>:()V
        //    15: astore          pageList
        //    17: aload_2         /* res */
        //    18: ldc             "text/html;charset=euc-kr"
        //    20: invokeinterface javax/servlet/http/HttpServletResponse.setContentType:(Ljava/lang/String;)V
        //    25: aload_2         /* res */
        //    26: invokeinterface javax/servlet/http/HttpServletResponse.getWriter:()Ljava/io/PrintWriter;
        //    31: astore          out
        //    33: aload_3         /* fb */
        //    34: aload_1         /* req */
        //    35: ldc             "regCode"
        //    37: invokeinterface javax/servlet/http/HttpServletRequest.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //    42: invokevirtual   common/util/EP_COB_GTQ805.retNull:(Ljava/lang/String;)Ljava/lang/String;
        //    45: astore          regCode
        //    47: aload_3         /* fb */
        //    48: aload_1         /* req */
        //    49: ldc             "regName"
        //    51: invokeinterface javax/servlet/http/HttpServletRequest.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //    56: invokevirtual   common/util/EP_COB_GTQ805.retNull:(Ljava/lang/String;)Ljava/lang/String;
        //    59: astore          regName
        //    61: aload           regCode
        //    63: ifnonnull       70
        //    66: ldc             ""
        //    68: astore          regCode
        //    70: aload           regName
        //    72: ifnonnull       79
        //    75: ldc             ""
        //    77: astore          regName
        //    79: iconst_0       
        //    80: istore          flag
        //    82: aload           regCode
        //    84: invokevirtual   java/lang/String.length:()I
        //    87: iconst_1       
        //    88: if_icmpgt       100
        //    91: aload           regName
        //    93: invokevirtual   java/lang/String.length:()I
        //    96: iconst_1       
        //    97: if_icmple       103
        //   100: iconst_1       
        //   101: istore          flag
        //   103: aload_3         /* fb */
        //   104: aload_1         /* req */
        //   105: ldc             "a"
        //   107: invokeinterface javax/servlet/http/HttpServletRequest.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //   112: invokevirtual   common/util/EP_COB_GTQ805.retNull:(Ljava/lang/String;)Ljava/lang/String;
        //   115: astore          a
        //   117: aload_3         /* fb */
        //   118: aload_1         /* req */
        //   119: ldc             "b"
        //   121: invokeinterface javax/servlet/http/HttpServletRequest.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //   126: invokevirtual   common/util/EP_COB_GTQ805.retNull:(Ljava/lang/String;)Ljava/lang/String;
        //   129: astore          b
        //   131: aload           b
        //   133: ifnonnull       140
        //   136: ldc             ""
        //   138: astore          b
        //   140: aload_3         /* fb */
        //   141: aload_1         /* req */
        //   142: ldc             "c"
        //   144: invokeinterface javax/servlet/http/HttpServletRequest.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //   149: invokevirtual   common/util/EP_COB_GTQ805.retNull:(Ljava/lang/String;)Ljava/lang/String;
        //   152: astore          c
        //   154: aload           c
        //   156: ifnonnull       163
        //   159: ldc             ""
        //   161: astore          c
        //   163: aload_3         /* fb */
        //   164: aload_1         /* req */
        //   165: ldc             "formName"
        //   167: invokeinterface javax/servlet/http/HttpServletRequest.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //   172: invokevirtual   common/util/EP_COB_GTQ805.retNull:(Ljava/lang/String;)Ljava/lang/String;
        //   175: astore          formName
        //   177: aload           a
        //   179: ifnull          187
        //   182: aload           formName
        //   184: ifnonnull       195
        //   187: aload           out
        //   189: ldc             "\ud504\ub85c\uadf8\ub7a8 \uc0ac\uc6a9\ubc95\uc774 \uc798\ubabb\t\ub418\uc5c8\uc2b5\ub2c8\ub2e4.\t\uc0ac\uc6a9\uc6a9\ubc95\uc744 \uc77d\uace0\t\uc815\ud655\ud788 \uc801\uc6a9\uc2dc\ucf1c\t\uc8fc\uc2ed\uc2dc\uc694."
        //   191: invokevirtual   java/io/PrintWriter.println:(Ljava/lang/String;)V
        //   194: return         
        //   195: aload_3         /* fb */
        //   196: aload_1         /* req */
        //   197: ldc             "page_no"
        //   199: invokeinterface javax/servlet/http/HttpServletRequest.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //   204: invokevirtual   common/util/EP_COB_GTQ805.retNull:(Ljava/lang/String;)Ljava/lang/String;
        //   207: astore          page_no
        //   209: aload           page_no
        //   211: ifnonnull       218
        //   214: ldc             "1"
        //   216: astore          page_no
        //   218: ldc             "10"
        //   220: astore          page_size
        //   222: ldc             ""
        //   224: astore          url
        //   226: new             Ljava/lang/StringBuffer;
        //   229: dup            
        //   230: ldc             "/servlet/common/util/EP_COV_GTQ950?regCode="
        //   232: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   235: aload           regCode
        //   237: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   240: ldc             "&regName="
        //   242: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   245: aload           regName
        //   247: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   250: ldc             "&a="
        //   252: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   255: aload           a
        //   257: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   260: ldc             "&b="
        //   262: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   265: aload           b
        //   267: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   270: ldc             "&c="
        //   272: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   275: aload           c
        //   277: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   280: ldc             "&formName="
        //   282: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   285: aload           formName
        //   287: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   290: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   293: astore          url
        //   295: iconst_0       
        //   296: istore          totalCount
        //   298: aconst_null    
        //   299: astore          conn
        //   301: aconst_null    
        //   302: astore          stmt
        //   304: aconst_null    
        //   305: astore          rest
        //   307: aconst_null    
        //   308: astore          resource
        //   310: ldc             ""
        //   312: astore          sql
        //   314: ldc             ""
        //   316: astore          sqlCount
        //   318: new             Ljava/lang/StringBuffer;
        //   321: dup            
        //   322: ldc             " select count(*) from\tsyn_\uc0ac\uc6a9_\uc870\ub2ec\uc5c5\uccb4\ub9c8\uc2a4\ud130 a, syn_\uc0ac\uc6a9_\uc5c5\uccb4\uc0c1\ud0dc b  where a.\uc0ac\uc5c5\uc790\ub4f1\ub85d\ubc88\ud638\tlike '"
        //   324: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   327: aload           regCode
        //   329: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   332: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   335: ldc             "%'"
        //   337: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   340: ldc             " and a.\uc0c1\ud638\uba85\tlike '"
        //   342: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   345: ldc             "%"
        //   347: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   350: aload           regName
        //   352: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   355: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   358: ldc             "%'"
        //   360: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   363: ldc             " and a.\uc0ac\uc5c5\uc790\ub4f1\ub85d\ubc88\ud638=b.\uc0ac\uc5c5\uc790\ub4f1\ub85d\ubc88\ud638(+)"
        //   365: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   368: ldc             " and (b.\uc0c1\ud0dc\uad6c\ubd84 not in ('02','03','04','05','06','07','10') or b.\uc0c1\ud0dc\uad6c\ubd84 is\tnull)"
        //   370: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   373: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   376: astore          sqlCount
        //   378: new             Ljava/lang/StringBuffer;
        //   381: dup            
        //   382: ldc             "select\tt, \uc0ac\uc5c5\uc790\ub4f1\ub85d\ubc88\ud638, \uc0c1\ud638\uba85, \ub300\ud45c\uc790\uba85 from( \tselect \uc0ac\uc5c5\uc790\ub4f1\ub85d\ubc88\ud638, \uc0c1\ud638\uba85, \ub300\ud45c\uc790\uba85, rownum t\tfrom (\t select\ta.\uc0ac\uc5c5\uc790\ub4f1\ub85d\ubc88\ud638,a.\uc0c1\ud638\uba85,c.\ub300\ud45c\uc790\uba85\t from syn_\uc0ac\uc6a9_\uc870\ub2ec\uc5c5\uccb4\ub9c8\uc2a4\ud130 a, syn_\uc0ac\uc6a9_\uc5c5\uccb4\uc0c1\ud0dc b, syn_\uc0ac\uc6a9_\ub300\ud45c\uc790 c\t where a.\uc0ac\uc5c5\uc790\ub4f1\ub85d\ubc88\ud638 like '"
        //   384: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   387: aload           regCode
        //   389: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   392: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   395: ldc             "%'"
        //   397: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   400: ldc             "\t and a.\uc0c1\ud638\uba85 like '"
        //   402: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   405: ldc             "%"
        //   407: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   410: aload           regName
        //   412: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   415: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   418: ldc             "%'"
        //   420: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   423: ldc             "\t and a.\uc0ac\uc5c5\uc790\ub4f1\ub85d\ubc88\ud638=b.\uc0ac\uc5c5\uc790\ub4f1\ub85d\ubc88\ud638(+)"
        //   425: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   428: ldc             "\t and a.\uc0ac\uc5c5\uc790\ub4f1\ub85d\ubc88\ud638=c.\uc0ac\uc5c5\uc790\ub4f1\ub85d\ubc88\ud638"
        //   430: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   433: ldc             "\t and c.\ub300\ud45c\ub300\ud45c\uc790\uc5ec\ubd80='Y'"
        //   435: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   438: ldc             "\t and (b.\uc0c1\ud0dc\uad6c\ubd84 not in\t('02','03','04','05','06','07','10') or\tb.\uc0c1\ud0dc\uad6c\ubd84 is null)"
        //   440: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   443: ldc             "\t order by 2\t"
        //   445: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   448: ldc             "\t)"
        //   450: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   453: ldc             " ) where t >\t"
        //   455: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   458: aload           page_size
        //   460: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   463: aload           page_no
        //   465: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   468: iconst_1       
        //   469: isub           
        //   470: imul           
        //   471: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //   474: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   477: ldc             "\tand\tt <\t "
        //   479: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   482: aload           page_size
        //   484: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   487: aload           page_no
        //   489: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   492: imul           
        //   493: iconst_1       
        //   494: iadd           
        //   495: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //   498: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   501: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   504: astore          sql
        //   506: aload_0         /* this */
        //   507: aload           out
        //   509: aload           regCode
        //   511: aload           regName
        //   513: aload           a
        //   515: aload           b
        //   517: aload           c
        //   519: aload           formName
        //   521: invokespecial   common/util/EP_COV_GTQ950.initHtml:(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   524: iload           flag
        //   526: ifeq            640
        //   529: new             Lcommon/Trx;
        //   532: dup            
        //   533: aload_0         /* this */
        //   534: invokespecial   common/Trx.<init>:(Ljava/lang/Object;)V
        //   537: astore          resource
        //   539: aload           resource
        //   541: invokevirtual   common/Trx.getConnection:()Ljava/sql/Connection;
        //   544: astore          conn
        //   546: aload           conn
        //   548: invokeinterface java/sql/Connection.createStatement:()Ljava/sql/Statement;
        //   553: astore          stmt
        //   555: aload           stmt
        //   557: aload           sqlCount
        //   559: invokeinterface java/sql/Statement.executeQuery:(Ljava/lang/String;)Ljava/sql/ResultSet;
        //   564: astore          rest
        //   566: aload           rest
        //   568: invokeinterface java/sql/ResultSet.next:()Z
        //   573: ifeq            586
        //   576: aload           rest
        //   578: iconst_1       
        //   579: invokeinterface java/sql/ResultSet.getInt:(I)I
        //   584: istore          totalCount
        //   586: iload           totalCount
        //   588: ifle            634
        //   591: aload           stmt
        //   593: aload           sql
        //   595: invokeinterface java/sql/Statement.executeQuery:(Ljava/lang/String;)Ljava/sql/ResultSet;
        //   600: astore          rest
        //   602: aload_0         /* this */
        //   603: aload           rest
        //   605: aload           out
        //   607: iload           totalCount
        //   609: invokespecial   common/util/EP_COV_GTQ950.dataHtml:(Ljava/sql/ResultSet;Ljava/io/PrintWriter;I)V
        //   612: aload           out
        //   614: aload           url
        //   616: iload           totalCount
        //   618: bipush          10
        //   620: aload           page_no
        //   622: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   625: invokestatic    common/util/EP_COB_GTQ809.getNextPageIndexes:(Ljava/lang/String;III)Ljava/lang/String;
        //   628: invokevirtual   java/io/PrintWriter.println:(Ljava/lang/String;)V
        //   631: goto            640
        //   634: aload_0         /* this */
        //   635: aload           out
        //   637: invokespecial   common/util/EP_COV_GTQ950.noDataHtml:(Ljava/io/PrintWriter;)V
        //   640: aload_0         /* this */
        //   641: aload           out
        //   643: invokespecial   common/util/EP_COV_GTQ950.endHtml:(Ljava/io/PrintWriter;)V
        //   646: goto            862
        //   649: astore          sqle
        //   651: aload           out
        //   653: new             Ljava/lang/StringBuffer;
        //   656: dup            
        //   657: ldc             "Error \ubc1c\uc0dd\t: "
        //   659: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   662: aload           sqle
        //   664: invokevirtual   java/sql/SQLException.toString:()Ljava/lang/String;
        //   667: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   670: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   673: invokevirtual   java/io/PrintWriter.println:(Ljava/lang/String;)V
        //   676: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   679: new             Ljava/lang/StringBuffer;
        //   682: dup            
        //   683: ldc             "\ub4f1\ub85d\uc5c5\uccb4 \ucc3e\uae30[EP_COV_GTQ500.java] \uc5d0\ub7ec(SQL)\t: "
        //   685: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   688: aload           sqle
        //   690: invokevirtual   java/sql/SQLException.toString:()Ljava/lang/String;
        //   693: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   696: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   699: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   702: aload           rest
        //   704: ifnull          719
        //   707: aload           rest
        //   709: invokeinterface java/sql/ResultSet.close:()V
        //   714: goto            719
        //   717: astore          25
        //   719: aload           stmt
        //   721: ifnull          736
        //   724: aload           stmt
        //   726: invokeinterface java/sql/Statement.close:()V
        //   731: goto            736
        //   734: astore          25
        //   736: aload           conn
        //   738: ifnull          751
        //   741: aload           resource
        //   743: invokevirtual   common/Trx.close:()V
        //   746: goto            751
        //   749: astore          25
        //   751: return         
        //   752: astore          ex
        //   754: aload           out
        //   756: new             Ljava/lang/StringBuffer;
        //   759: dup            
        //   760: ldc             "Error \ubc1c\uc0dd\t: "
        //   762: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   765: aload           ex
        //   767: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   770: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   773: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   776: invokevirtual   java/io/PrintWriter.println:(Ljava/lang/String;)V
        //   779: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   782: new             Ljava/lang/StringBuffer;
        //   785: dup            
        //   786: ldc             "\ub4f1\ub85d\uc5c5\uccb4 \ucc3e\uae30[EP_COV_GTQ500.java] \uc5d0\ub7ec : "
        //   788: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   791: aload           ex
        //   793: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   796: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   799: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   802: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   805: goto            702
        //   808: astore          24
        //   810: aload           rest
        //   812: ifnull          827
        //   815: aload           rest
        //   817: invokeinterface java/sql/ResultSet.close:()V
        //   822: goto            827
        //   825: astore          25
        //   827: aload           stmt
        //   829: ifnull          844
        //   832: aload           stmt
        //   834: invokeinterface java/sql/Statement.close:()V
        //   839: goto            844
        //   842: astore          25
        //   844: aload           conn
        //   846: ifnull          859
        //   849: aload           resource
        //   851: invokevirtual   common/Trx.close:()V
        //   854: goto            859
        //   857: astore          25
        //   859: aload           24
        //   861: athrow         
        //   862: aload           rest
        //   864: ifnull          879
        //   867: aload           rest
        //   869: invokeinterface java/sql/ResultSet.close:()V
        //   874: goto            879
        //   877: astore          25
        //   879: aload           stmt
        //   881: ifnull          896
        //   884: aload           stmt
        //   886: invokeinterface java/sql/Statement.close:()V
        //   891: goto            896
        //   894: astore          25
        //   896: aload           conn
        //   898: ifnull          911
        //   901: aload           resource
        //   903: invokevirtual   common/Trx.close:()V
        //   906: goto            911
        //   909: astore          25
        //   911: return         
        //    Exceptions:
        //  throws javax.servlet.ServletException
        //  throws java.io.IOException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name        Signature
        //  -----  ------  ----  ----------  ----------------------------------------
        //  0      912     0     this        Lcommon/util/EP_COV_GTQ950;
        //  0      912     1     req         Ljavax/servlet/http/HttpServletRequest;
        //  0      912     2     res         Ljavax/servlet/http/HttpServletResponse;
        //  8      904     3     fb          Lcommon/util/EP_COB_GTQ805;
        //  17     895     4     pageList    Lcommon/util/EP_COB_GTQ809;
        //  33     879     5     out         Ljava/io/PrintWriter;
        //  47     865     6     regCode     Ljava/lang/String;
        //  61     851     7     regName     Ljava/lang/String;
        //  82     830     8     flag        Z
        //  117    795     9     a           Ljava/lang/String;
        //  131    781     10    b           Ljava/lang/String;
        //  154    758     11    c           Ljava/lang/String;
        //  177    735     12    formName    Ljava/lang/String;
        //  209    703     13    page_no     Ljava/lang/String;
        //  222    690     14    page_size   Ljava/lang/String;
        //  226    686     15    url         Ljava/lang/String;
        //  298    614     16    totalCount  I
        //  301    611     17    conn        Ljava/sql/Connection;
        //  304    608     18    stmt        Ljava/sql/Statement;
        //  307    605     19    rest        Ljava/sql/ResultSet;
        //  310    602     20    resource    Lcommon/Trx;
        //  314    598     21    sql         Ljava/lang/String;
        //  318    594     22    sqlCount    Ljava/lang/String;
        //  651    51      23    sqle        Ljava/sql/SQLException;
        //  754    54      23    ex          Ljava/lang/Exception;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                   
        //  -----  -----  -----  -----  -----------------------
        //  506    646    649    702    Ljava/sql/SQLException;
        //  702    714    717    719    Ljava/lang/Exception;
        //  719    731    734    736    Ljava/lang/Exception;
        //  736    746    749    751    Ljava/lang/Exception;
        //  506    646    752    808    Ljava/lang/Exception;
        //  506    702    808    862    Any
        //  752    808    808    862    Any
        //  810    822    825    827    Ljava/lang/Exception;
        //  827    839    842    844    Ljava/lang/Exception;
        //  844    854    857    859    Ljava/lang/Exception;
        //  862    874    877    879    Ljava/lang/Exception;
        //  879    891    894    896    Ljava/lang/Exception;
        //  896    906    909    911    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 382, Size: 382
        //     at java.util.ArrayList.rangeCheck(Unknown Source)
        //     at java.util.ArrayList.get(Unknown Source)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3417)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:123)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void initHtml(final PrintWriter out, final String regCode, final String regName, final String a, final String b, final String c, final String formName) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title> 등록업체 찾기 </title>");
        out.println("<link rel=STYLESHEET type=text/css\thref=/gm/css/EP.css>");
        out.println("<script language='javascript' src='/gm/js/EP.js'></script>");
        out.println("<script language='javascript'>");
        out.println("\tfunction search() {");
        out.println("\t\tif((document.ebid.regCode.value.length < 2 &&  document.ebid.regName.value.length <\t2)){");
        out.println("\t\t\talert('사업자등록번호나 상호명을 두 자리 이상 입력하십시요.');");
        out.println("\t\t\treturn;");
        out.println("\t\t}");
        out.println("\t\tdocument.ebid.method='post';");
        out.println("\t\tdocument.ebid.action\t='/servlet/common/util/EP_COV_GTQ950';\t");
        out.println("\t\tdocument.ebid.submit();");
        out.println("\t\treturn;");
        out.println("\t}");
        out.println("function toOpener(arg1, arg2, arg3){");
        out.println("\topener.document." + formName + "." + a + ".value=arg1;");
        if (!b.equals("")) {
            out.println("\topener.document." + formName + "." + b + ".value=arg2;");
        }
        if (!c.equals("")) {
            out.println("\topener.document." + formName + "." + c + ".value=arg3;");
        }
        out.println("\tself.close();");
        out.println("\treturn;");
        out.println("}");
        out.println("</script>");
        out.println("</head>");
        out.println("<body bgcolor=#FFFFFF text=#000000\tleftmargin=0 topmargin=0 marginwidth=0 marginheight=0>");
        out.println("<center>");
        out.println("<br>");
        out.println("<table\twidth=90% border=0 cellpadding=0>");
        out.println("  <tr>");
        out.println("\t <td width=4% class=fontb><img src=/img/Main_ico.gif width=24 height=30\talign=absmiddle></td>");
        out.println("\t <td class=titleb width=96%>등록업체찾기</td>");
        out.println("  </tr>");
        out.println("</table>");
        out.println("<table\twidth=90% cellpadding=2\tcellspacing=2>");
        out.println("  <tr>\t");
        out.println("\t <td align=center background=/img/dotlines02.gif height=1></td>");
        out.println("  </tr>");
        out.println("  <tr>\t");
        out.println("\t <td align=center background=/img/dotlines02.gif height=1></td>");
        out.println("  </tr>");
        out.println("</table>");
        out.println("<br>");
        out.println("<center>");
        out.println("<form name=ebid onSubmit=javascript:gve_search()>");
        out.println("<table\tborder=0 cellpadding=2 cellspacing=1 width=90%>");
        out.println("<tr>");
        out.println("\t<td\tclass='tdar'>사업자등록번호</td>");
        out.println("\t<td\tclass='tdb'><input type=text name=regCode size=10 value='" + regCode.trim() + "' maxlength=10 onkeypress=\"javascript:if(event.keyCode == 13) search();\" class='read' onBlur=\"javascript:js_NumCheck(this);\"></td>");
        out.println("\t<td\tclass='tdb'><a href=\"javascript:search();\"><img src=/img/bu_refer.gif\tborder=0 align=absmiddle alt='검색'></a></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td class='tdar'>상호명</td>");
        out.println("<td class='tdb' colspan=2><input type=text\tname=regName value='" + regName.trim() + "'\tsize=30\tmaxlength=30 onkeypress=\"javascript:if(event.keyCode == 13) search();\" class='read'></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<input\ttype=hidden\tname=a value='" + a + "'>");
        out.println("<input\ttype=hidden\tname=b value='" + b + "'>");
        out.println("<input\ttype=hidden\tname=c value='" + c + "'>");
        out.println("<input\ttype=hidden\tname=formName value='" + formName + "'>");
        out.println("</form>");
    }
    
    private void endHtml(final PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
    }
    
    private void noDataHtml(final PrintWriter out) {
        out.println("<table\tclass=tr border=0 cellpadding=2\tcellspacing=1 align=center width=90%>");
        out.println("<tr>");
        out.println("\t<td\talign=right\tcolspan=10 class='page'>검색건수 : 0건</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td class=tdac>순번</td>");
        out.println("<td class=tdac>사업자등록번호</td>");
        out.println("<td class=tdac>상호명</td>");
        out.println("<td class=tdac>대표자명</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td class=tdbc\tcolspan=4><font\tcolor=red>검색된 Data가 없습니다.</font></td>");
        out.println("</tr>");
        out.println("</table>");
    }
    
    private void dataHtml(final ResultSet rest, final PrintWriter out, final int totalCount) throws SQLException {
        out.println("<table\tclass=tr border=0 cellpadding=2\tcellspacing=1 align=center width=90%>");
        out.println("<tr>");
        out.println("\t<td\talign=right\tcolspan=10 class='page'>검색건수 : " + Integer.toString(totalCount) + "건</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td class=tdac>순번</td>");
        out.println("<td class=tdac>사업자등록번호</td>");
        out.println("<td class=tdac>상호명</td>");
        out.println("<td class=tdac>대표자명</td>");
        out.println("</tr>");
        while (rest.next()) {
            out.println("<tr>");
            out.println("<td class=tdbc>" + rest.getString(1) + "</td>");
            out.println("<td class=tdbc><a href=\"javascript:toOpener('" + rest.getString(2) + "','" + rest.getString(3) + "','" + rest.getString(4) + "')\">" + rest.getString(2) + "</td>");
            out.println("<td class=tdbc>" + rest.getString(3) + "</td>");
            out.println("<td class=tdbc>" + rest.getString(4) + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
    }
}
