// 
// Decompiled by Procyon v0.5.30
// 

package com.globfx.swiffchart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Vector;

public class SwiffChart
{
    protected int width;
    protected int height;
    protected int frame_rate;
    protected boolean animate;
    protected boolean looping;
    protected boolean has_looping;
    protected boolean swf_compressed;
    protected boolean swf_protected;
    protected String separators;
    protected boolean ignore_multiple_sep;
    protected char decimal_sep;
    protected char thousands_sep;
    protected String install_dir;
    protected String style;
    protected String title;
    protected String subtitle;
    protected String[] categs;
    protected Vector series_captions;
    protected Vector series_x_values;
    protected Vector series_y_values;
    protected String[] axis_min_value;
    protected String[] axis_max_value;
    protected String[] axis_cross_value;
    protected String[] axis_title;
    protected String cache_name;
    protected String private_cache_dir;
    protected int max_cache_size;
    protected String fonts_dir_list;
    protected boolean use_cache;
    protected int output_format;
    protected int jpg_quality;
    protected int png_comp_level;
    protected String document_root;
    protected boolean no_cache;
    public String shell_exec_error;
    HttpServletRequest request;
    HttpServletResponse response;
    String so;
    String lo;
    protected static String hexchars;
    
    public SwiffChart() {
        this.install_dir = "C:/Program Files/GlobFX Technologies/Swiff Chart Generator";
        this.so = " /";
        this.lo = " /";
        this.cache_name = "";
        this.private_cache_dir = "";
        this.max_cache_size = -1;
        this.fonts_dir_list = "";
        this.use_cache = false;
        this.output_format = 0;
        this.jpg_quality = 75;
        this.png_comp_level = 3;
        this.document_root = "";
        this.no_cache = true;
        this.shell_exec_error = "";
        this.request = null;
        this.response = null;
        this.ClearAll();
    }
    
    public void Release() {
        this.request = null;
        this.response = null;
    }
    
    public void ClearAll() {
        this.width = 0;
        this.height = 0;
        this.frame_rate = 0;
        this.animate = true;
        this.looping = true;
        this.has_looping = false;
        this.swf_compressed = false;
        this.swf_protected = true;
        this.separators = ";";
        this.ignore_multiple_sep = false;
        this.decimal_sep = '\0';
        this.thousands_sep = '\0';
        this.style = "";
        this.title = null;
        this.subtitle = null;
        this.categs = new String[0];
        this.series_captions = new Vector();
        this.series_x_values = new Vector();
        this.series_y_values = new Vector();
        this.axis_min_value = new String[4];
        this.axis_max_value = new String[4];
        this.axis_cross_value = new String[4];
        this.axis_title = new String[4];
    }
    
    public void SetWidth(final int width) {
        this.width = width;
    }
    
    public void SetHeight(final int height) {
        this.height = height;
    }
    
    public int GetWidth() {
        return this.width;
    }
    
    public int GetHeight() {
        return this.height;
    }
    
    public void SetFrameRate(final int frame_rate) {
        this.frame_rate = frame_rate;
    }
    
    public int GetFrameRate() {
        return this.frame_rate;
    }
    
    public void AnimateChart(final boolean animate) {
        this.animate = animate;
    }
    
    public boolean IsAnimated() {
        return this.animate;
    }
    
    public void SetLooping(final boolean looping) {
        this.looping = looping;
        this.has_looping = true;
    }
    
    public boolean GetLooping() {
        return !this.has_looping || this.looping;
    }
    
    public void CompressSWF(final boolean swf_compressed) {
        this.swf_compressed = swf_compressed;
    }
    
    public boolean IsSWFCompressed() {
        return this.swf_compressed;
    }
    
    public void ProtectSWF(final boolean swf_protected) {
        this.swf_protected = swf_protected;
    }
    
    public boolean IsSWFProtected() {
        return this.swf_protected;
    }
    
    public void SetSeparators(String separators, final boolean ignore_multiple_sep) {
        if (separators == "") {
            separators = ";";
        }
        this.separators = separators;
        this.ignore_multiple_sep = ignore_multiple_sep;
    }
    
    public String GetSeparators() {
        return this.separators;
    }
    
    public void SetLocaleInfo(final int n, final char c) throws ServletException {
        switch (n) {
            case 1: {
                this.decimal_sep = c;
                break;
            }
            case 2: {
                this.thousands_sep = c;
                break;
            }
            default: {
                throw new ServletException("SwiffChart: SetLocaleInfo(): invalid type (" + n + ")");
            }
        }
    }
    
    public void SetTitle(final String title) {
        this.title = title;
    }
    
    public String GetTitle() {
        return this.title;
    }
    
    public void SetSubtitle(final String subtitle) {
        this.subtitle = subtitle;
    }
    
    public String GetSubtitle() {
        return this.subtitle;
    }
    
    public void SetCategoriesFromArray(final String[] array) {
        this.categs = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            this.categs[i] = array[i];
        }
    }
    
    protected int strpbrk(final String s, final int n, final String s2) {
        int n2 = -1;
        for (int i = 0; i < s2.length(); ++i) {
            final int index = s.indexOf(s2.charAt(i), n);
            if (index != -1) {
                if (n2 == -1) {
                    n2 = index;
                }
                else if (index < n2) {
                    n2 = index;
                }
            }
        }
        return n2;
    }
    
    protected String[] split_string(final String s, final String s2, final boolean b) {
        String[] array = new String[0];
        int n = 0;
        while (true) {
            final int strpbrk = this.strpbrk(s, n, s2);
            final int n2 = (strpbrk == -1) ? s.length() : strpbrk;
            if (n > 0 && strpbrk != -1 && n2 == n && b) {
                if (strpbrk == -1) {
                    break;
                }
                ++n;
            }
            else {
                final String substring = s.substring(n, n2);
                final String[] array2 = new String[array.length + 1];
                System.arraycopy(array, 0, array2, 0, array.length);
                array = array2;
                array[array.length - 1] = substring;
                if (strpbrk == -1) {
                    break;
                }
                n = n2 + 1;
            }
        }
        return array;
    }
    
    public void SetCategoriesFromString(final String s) {
        this.SetCategoriesFromArray(this.split_string(s, this.separators, this.ignore_multiple_sep));
    }
    
    public String GetCategory(final int n) throws ServletException {
        if (n < 0 || n >= this.categs.length) {
            throw new ServletException("SwiffChart: GetCategory(): index " + n + " is out of bounds [0," + this.categs.length + "]");
        }
        return this.categs[n];
    }
    
    public void SetSeriesCaptionsFromArray(final String[] array) {
        (this.series_captions = new Vector(array.length)).setSize(array.length);
        for (int i = 0; i < array.length; ++i) {
            this.series_captions.setElementAt(array[i], i);
        }
        while (this.GetSeriesCount() < this.series_captions.size()) {
            this.AddSeries();
        }
    }
    
    public void SetSeriesCaptionsFromString(final String s) {
        this.SetSeriesCaptionsFromArray(this.split_string(s, this.separators, this.ignore_multiple_sep));
    }
    
    public void SetSeriesCaption(final int n, final String s) throws ServletException {
        if (n < 0 || n >= this.GetSeriesCount()) {
            throw new ServletException("SwiffChart: SetSeriesCaption(): index " + n + " is out of bounds [0," + (this.GetSeriesCount() - 1) + "]");
        }
        this.series_captions.setElementAt(s, n);
    }
    
    public String GetSeriesCaption(final int n) throws ServletException {
        if (n < 0 || n >= this.GetSeriesCount()) {
            throw new ServletException("SwiffChart: GetSeriesCaption(): index " + n + " is out of bounds [0," + (this.GetSeriesCount() - 1) + "]");
        }
        return this.series_captions.elementAt(n);
    }
    
    public void AddSeries() {
        this.series_y_values.addElement(new String[0]);
        this.series_x_values.addElement(new String[0]);
        this.series_captions.addElement(new String());
    }
    
    public int GetValuesCount() {
        int n = this.categs.length;
        for (int i = 0; i < this.series_y_values.size(); ++i) {
            final String[] array = (String[])this.series_y_values.elementAt(i);
            if (array.length > n) {
                n = array.length;
            }
            final String[] array2 = (String[])this.series_x_values.elementAt(i);
            if (array2.length > n) {
                n = array2.length;
            }
        }
        return n;
    }
    
    public int GetSeriesCount() {
        return this.series_y_values.size();
    }
    
    public String GetSeriesValue(final int n, final int n2) throws ServletException {
        return this.GetSeriesYValue(n, n2);
    }
    
    public String GetSeriesXValue(final int n, final int n2) throws ServletException {
        if (n < 0 || n >= this.GetSeriesCount()) {
            throw new ServletException("SwiffChart: GetSeriesXValue(): index " + n + " is out of bounds [0," + (this.GetSeriesCount() - 1) + "]");
        }
        final String[] array = (String[])this.series_x_values.elementAt(n);
        if (n2 < 0 || n2 >= array.length) {
            throw new ServletException("SwiffChart: GetSeriesXValue(): value index " + n2 + " is out of bounds [0," + (array.length - 1) + "]");
        }
        return array[n2];
    }
    
    public String GetSeriesYValue(final int n, final int n2) throws ServletException {
        if (n < 0 || n >= this.GetSeriesCount()) {
            throw new ServletException("SwiffChart: GetSeriesYValue(): index " + n + " is out of bounds [0," + (this.GetSeriesCount() - 1) + "]");
        }
        final String[] array = (String[])this.series_y_values.elementAt(n);
        if (n2 < 0 || n2 >= array.length) {
            throw new ServletException("SwiffChart: GetSeriesYValue(): value index " + n2 + " is out of bounds [0," + (array.length - 1) + "]");
        }
        return array[n2];
    }
    
    public void SetSeriesValuesFromArray(final int n, final String[] array) throws ServletException {
        this.SetSeriesYValuesFromArray(n, array);
    }
    
    public void SetSeriesValuesFromString(final int n, final String s) throws ServletException {
        this.SetSeriesYValuesFromString(n, s);
    }
    
    public void SetSeriesXValuesFromArray(final int n, final String[] array) throws ServletException {
        if (n < 0 || n >= this.GetSeriesCount()) {
            throw new ServletException("SwiffChart: SetSeriesXValuesFromArray(): index " + n + " is out of bounds [0," + (this.GetSeriesCount() - 1) + "]");
        }
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[i];
        }
        this.series_x_values.setElementAt(array2, n);
    }
    
    public void SetSeriesXValuesFromString(final int n, final String s) throws ServletException {
        this.SetSeriesXValuesFromArray(n, this.split_string(s, this.separators, this.ignore_multiple_sep));
    }
    
    public void SetSeriesYValuesFromArray(final int n, final String[] array) throws ServletException {
        if (n < 0 || n >= this.GetSeriesCount()) {
            throw new ServletException("SwiffChart: SetSeriesYValuesFromArray(): index " + n + " is out of bounds [0," + (this.GetSeriesCount() - 1) + "]");
        }
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[i];
        }
        this.series_y_values.setElementAt(array2, n);
    }
    
    public void SetSeriesYValuesFromString(final int n, final String s) throws ServletException {
        this.SetSeriesYValuesFromArray(n, this.split_string(s, this.separators, this.ignore_multiple_sep));
    }
    
    public void SetSeriesXYValuesFromString(final int n, final String s) throws ServletException {
        if (n < 0 || n >= this.GetSeriesCount()) {
            throw new ServletException("SwiffChart: SetSeriesXYValuesFromString(): index " + n + " is out of bounds [0," + (this.GetSeriesCount() - 1) + "]");
        }
        final String[] split_string = this.split_string(s, this.separators, this.ignore_multiple_sep);
        final int n2 = split_string.length / 2;
        final String[] array = new String[split_string.length];
        final String[] array2 = new String[split_string.length];
        this.series_x_values.setElementAt(array, n);
        this.series_y_values.setElementAt(array2, n);
        for (int i = 0; i < n2; ++i) {
            array[i] = split_string[2 * i];
            array2[i] = split_string[2 * i + 1];
        }
    }
    
    public void SetAxisMinValue(final int n, final String s) throws ServletException {
        if (n < 0 || n > 3) {
            throw new ServletException("SwiffChart: SetAxisMinValue(): axis number " + n + " is out of bounds [0,1]");
        }
        this.axis_min_value[n] = s;
    }
    
    public void SetAxisMaxValue(final int n, final String s) throws ServletException {
        if (n < 0 || n > 3) {
            throw new ServletException("SwiffChart: SetAxisMaxValue(): axis number " + n + " is out of bounds [0,1]");
        }
        this.axis_max_value[n] = s;
    }
    
    public void SetAxisCrossValue(final int n, final String s) throws ServletException {
        if (n < 0 || n > 3) {
            throw new ServletException("SwiffChart: SetAxisCrossValue(): axis number " + n + " is out of bounds [0,1]");
        }
        this.axis_cross_value[n] = s;
    }
    
    public void ResetAxisBounds(final int n) throws ServletException {
        if (n < 0 || n > 3) {
            throw new ServletException("SwiffChart: ResetAxisBounds(): axis number " + n + " is out of bounds [0,1]");
        }
        this.axis_min_value[n] = null;
        this.axis_max_value[n] = null;
        this.axis_cross_value[n] = null;
    }
    
    public void SetAxisTitle(final int n, final String s) throws ServletException {
        if (n < 0 || n > 3) {
            throw new ServletException("SwiffChart: SetAxisTitle(): axis number " + n + " is out of bounds [0,1]");
        }
        this.axis_title[n] = s;
    }
    
    public String GetAxisTitle(final int n) throws ServletException {
        if (n < 0 || n > 3) {
            throw new ServletException("SwiffChart: GetAxisTitle(): axis number " + n + " is out of bounds [0,1]");
        }
        return this.axis_title[n];
    }
    
    public void SetDataFromQuery() throws ServletException {
        throw new ServletException("SwiffChart: SetDataFromQuery(): Not implemented");
    }
    
    public void SetDataFromTxtFile(final String s, final boolean b, final boolean b2, final boolean b3) throws ServletException {
        throw new ServletException("SwiffChart: SetDataFromTxtFile(): Not implemented");
    }
    
    public void SetXYDataFromTxtFile(final String s, final boolean b, final boolean b2, final boolean b3) throws ServletException {
        throw new ServletException("SwiffChart: SetXYDataFromTxtFile(): Not implemented");
    }
    
    protected String rawurlencode(final String s) {
        if (s == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer(3 * s.length());
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if ((char1 < '0' && char1 != '.') || (char1 < 'A' && char1 > '9') || (char1 > 'Z' && char1 < 'a' && char1 != '_') || char1 > 'z') {
                if (char1 < 'Ā') {
                    final char c = (char)(char1 & 'ÿ');
                    sb.append('%');
                    sb.append(SwiffChart.hexchars.charAt(c >> 4));
                    sb.append(SwiffChart.hexchars.charAt(c & '\u000f'));
                }
                else {
                    final char c2 = (char)(char1 & '\uffff');
                    sb.append('@');
                    sb.append(SwiffChart.hexchars.charAt(c2 >> 12 & '\u000f'));
                    sb.append(SwiffChart.hexchars.charAt(c2 >> 8 & '\u000f'));
                    sb.append(SwiffChart.hexchars.charAt(c2 >> 4 & '\u000f'));
                    sb.append(SwiffChart.hexchars.charAt(c2 & '\u000f'));
                }
            }
            else {
                sb.append(char1);
            }
        }
        return new String(sb);
    }
    
    protected String rawurldecode(final String s) {
        if (s == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer(s.length());
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 != '%' || i + 3 >= s.length()) {
                sb.append(char1);
            }
            else {
                int char2 = s.charAt(i + 1);
                int char3 = s.charAt(i + 2);
                if (char2 >= 97 && char2 <= 122) {
                    char2 -= 32;
                }
                if (char3 >= 97 && char3 <= 122) {
                    char3 -= 32;
                }
                int n;
                if (char2 >= 65) {
                    n = char2 - 65 + 10 << 4;
                }
                else {
                    n = char2 - 48 << 4;
                }
                int n2;
                if (char3 >= 65) {
                    n2 = n + (char3 - 65 + 10);
                }
                else {
                    n2 = n + (char3 - 48);
                }
                sb.append((char)n2);
                i += 2;
            }
        }
        return new String(sb);
    }
    
    protected String mangle(final String s) {
        return this.rawurlencode(s);
    }
    
    protected String mangle_int(final int n) {
        return this.mangle(Integer.toString(n));
    }
    
    protected String mangle_char(final char c) {
        return this.mangle(new Character(c).toString());
    }
    
    protected String prepare_cmd_line(final boolean b, final boolean b2) {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.install_dir + "/swfchart");
        if (this.style != null && this.style != "") {
            sb.append(this.so + "s " + this.mangle(this.style));
        }
        if (this.width >= 1) {
            sb.append(this.lo + "width " + this.mangle_int(this.width));
        }
        if (this.height >= 1) {
            sb.append(this.lo + "height " + this.mangle_int(this.height));
        }
        if (this.frame_rate >= 1) {
            sb.append(this.lo + "fps " + this.mangle_int(this.frame_rate));
        }
        if (!this.animate) {
            sb.append(this.so + "a false");
        }
        if (!this.swf_protected) {
            sb.append(this.lo + "protect false");
        }
        if (this.swf_compressed) {
            sb.append(this.lo + "compress true");
        }
        if (this.has_looping) {
            sb.append(this.lo + "looping " + (this.looping ? "true" : "false"));
        }
        sb.append(this.so + "tr false" + this.so + "tc false" + this.so + "sr");
        if (this.title != null && this.title != "") {
            sb.append(this.lo + "title " + this.mangle(this.title));
        }
        if (this.subtitle != null && this.subtitle != "") {
            sb.append(this.lo + "subtitle " + this.mangle(this.subtitle));
        }
        if (this.decimal_sep != '\0') {
            sb.append(this.so + "ds " + this.mangle_char(this.decimal_sep));
        }
        if (this.thousands_sep != '\0') {
            sb.append(this.so + "ts " + this.mangle_char(this.thousands_sep));
        }
        final int length = this.categs.length;
        if (length > 0) {
            final StringBuffer sb2 = new StringBuffer();
            for (int i = 0; i < length; ++i) {
                if (i > 0) {
                    sb2.append(this.separators.charAt(0));
                }
                sb2.append(this.categs[i]);
            }
            if (sb2.length() > 0) {
                sb.append(this.so + "c " + this.mangle(sb2.toString()));
            }
        }
        final int size = this.series_captions.size();
        if (size > 0) {
            final StringBuffer sb3 = new StringBuffer();
            for (int j = 0; j < size; ++j) {
                if (j > 0) {
                    sb3.append(this.separators.charAt(0));
                }
                final Object element = this.series_captions.elementAt(j);
                if (element != null) {
                    sb3.append((String)element);
                }
            }
            if (sb3.length() > 0) {
                sb.append(this.so + "i " + this.mangle(sb3.toString()));
            }
        }
        final int size2 = this.series_y_values.size();
        if (size2 > 0) {
            final StringBuffer sb4 = new StringBuffer();
            boolean b3 = false;
            for (int k = 0; k < size2; ++k) {
                if (((String[])this.series_x_values.elementAt(k)).length > 0) {
                    b3 = true;
                    break;
                }
            }
            for (int l = 0; l < size2; ++l) {
                if (b3) {
                    final String[] array = (String[])this.series_x_values.elementAt(l);
                    for (int n = 0; n < array.length; ++n) {
                        if (n > 0) {
                            sb4.append(this.separators.charAt(0));
                        }
                        sb4.append(array[n]);
                    }
                    sb4.append("\\n");
                }
                final String[] array2 = (String[])this.series_y_values.elementAt(l);
                for (int n2 = 0; n2 < array2.length; ++n2) {
                    if (n2 > 0) {
                        sb4.append(this.separators.charAt(0));
                    }
                    sb4.append(array2[n2]);
                }
                if (l + 1 < size2) {
                    sb4.append("\\n");
                }
            }
            if (sb4.length() > 0) {
                sb.append(this.so + "n " + this.mangle(sb4.toString()));
            }
        }
        if (this.axis_title[0] != null && this.axis_title[0] != "") {
            sb.append(this.lo + "haxis-title " + this.mangle(this.axis_title[0]));
        }
        if (this.axis_title[1] != null && this.axis_title[1] != "") {
            sb.append(this.lo + "vaxis-title " + this.mangle(this.axis_title[1]));
        }
        if (this.axis_min_value[0] != null && this.axis_min_value[0] != "") {
            sb.append(this.lo + "axis-min 0 " + this.mangle(this.axis_min_value[0]));
        }
        if (this.axis_min_value[1] != null && this.axis_min_value[1] != "") {
            sb.append(this.lo + "axis-min 1 " + this.mangle(this.axis_min_value[1]));
        }
        if (this.axis_max_value[0] != null && this.axis_max_value[0] != "") {
            sb.append(this.lo + "axis-max 0 " + this.mangle(this.axis_max_value[0]));
        }
        if (this.axis_max_value[1] != null && this.axis_max_value[1] != "") {
            sb.append(this.lo + "axis-max 1 " + this.mangle(this.axis_max_value[1]));
        }
        if (this.axis_cross_value[0] != null && this.axis_cross_value[0] != "") {
            sb.append(this.lo + "axis-cross 0 " + this.mangle(this.axis_cross_value[0]));
        }
        if (this.axis_cross_value[1] != null && this.axis_cross_value[1] != "") {
            sb.append(this.lo + "axis-cross 1 " + this.mangle(this.axis_cross_value[1]));
        }
        if (this.separators.charAt(0) != ';') {
            sb.append(this.so + "d '" + this.mangle_char(this.separators.charAt(0)) + "'");
        }
        if (b || b2 || this.use_cache) {
            if (b) {
                sb.append(this.lo + "html-tag");
            }
            else if (b2) {
                sb.append(this.lo + "output-loc");
            }
            else {
                sb.append(this.lo + "use-cache");
            }
            if (this.document_root != null && this.document_root != "") {
                sb.append(this.lo + "doc-root " + this.mangle(this.document_root));
            }
            if (this.cache_name != null && this.cache_name != "") {
                sb.append(this.lo + "cache-name " + this.mangle(this.cache_name));
            }
            if (this.private_cache_dir != null && this.private_cache_dir != "") {
                sb.append(this.lo + "private-cache-dir " + this.mangle(this.private_cache_dir));
            }
            if (this.max_cache_size >= 0) {
                sb.append(this.lo + "max-cache-size " + this.max_cache_size);
            }
        }
        if (this.fonts_dir_list != null && this.fonts_dir_list != "") {
            sb.append(this.lo + "fonts " + this.mangle(this.fonts_dir_list));
        }
        if (this.output_format == 1) {
            sb.append(this.lo + "format jpg");
            if (this.jpg_quality != 75) {
                sb.append(this.lo + "jpg-quality " + this.jpg_quality);
            }
        }
        else if (this.output_format == 2) {
            sb.append(this.lo + "format png");
            if (this.png_comp_level != 3) {
                sb.append(this.lo + "png-comp-level " + this.png_comp_level);
            }
        }
        return sb.toString();
    }
    
    protected byte[] read_stream(final InputStream inputStream) throws IOException {
        byte[] array = new byte[8192];
        int n = 0;
        while (true) {
            final int read = inputStream.read(array, n, array.length - n);
            if (read <= 0) {
                break;
            }
            n += read;
            if (n < array.length) {
                continue;
            }
            final byte[] array2 = new byte[array.length + array.length / 2];
            System.arraycopy(array, 0, array2, 0, array.length);
            array = array2;
        }
        final byte[] array3 = new byte[n];
        System.arraycopy(array, 0, array3, 0, n);
        return array3;
    }
    
    public byte[] shell_exec(final String s) throws IOException {
        this.shell_exec_error = "";
        final Process exec = Runtime.getRuntime().exec(s);
        final byte[] read_stream = this.read_stream(exec.getInputStream());
        if (read_stream.length > 0) {
            return read_stream;
        }
        this.shell_exec_error = new String(this.read_stream(exec.getErrorStream()));
        return null;
    }
    
    public void SetCacheName(final String cache_name) {
        this.cache_name = cache_name;
    }
    
    public void SetPrivateCacheDir(String s) {
        s = s;
    }
    
    public void SetMaxCacheSize(final int max_cache_size) {
        this.max_cache_size = max_cache_size;
    }
    
    public void ClearCache() throws ServletException, IOException {
        this.shell_exec(this.prepare_cmd_line(true, false) + this.lo + "clear-cache");
        if (this.shell_exec_error.length() > 0) {
            throw new ServletException("SwiffChart: ClearCache(): An error occurred: " + this.shell_exec_error);
        }
    }
    
    public void UseCache(final boolean use_cache) {
        this.use_cache = use_cache;
    }
    
    public void SetTTFontsDir(final String fonts_dir_list) {
        this.fonts_dir_list = fonts_dir_list;
    }
    
    public void SetOutputFormat(final String s) throws ServletException {
        final String lowerCase = s.toLowerCase();
        if (lowerCase.compareTo("swf") == 0) {
            this.output_format = 0;
            return;
        }
        if (lowerCase.compareTo("jpg") == 0) {
            this.output_format = 1;
            return;
        }
        if (lowerCase.compareTo("png") == 0) {
            this.output_format = 2;
            return;
        }
        throw new ServletException("SwiffChart: SetOutputFormat(): Invalid output format '" + s + "'");
    }
    
    public String GetHTTPContentType() {
        switch (this.output_format) {
            case 0: {
                return "application/x-shockwave-flash";
            }
            case 1: {
                return "image/jpeg";
            }
            case 2: {
                return "image/png";
            }
            default: {
                return "";
            }
        }
    }
    
    public void ExportAsResponse() throws ServletException, IOException {
        if (this.response == null) {
            throw new ServletException("SwiffChart: ExportAsResponse(): SetServletInfo() has not been called");
        }
        final byte[] shell_exec = this.shell_exec(this.prepare_cmd_line(false, false));
        if (this.shell_exec_error.length() > 0) {
            throw new ServletException("SwiffChart: ExportAsResponse(): An error occurred: " + this.shell_exec_error);
        }
        this.response.reset();
        this.response.setContentType(this.GetHTTPContentType());
        if (this.no_cache) {
            this.response.addHeader("Expires", "Mon, 01 Jan 1990 00:00:00 GMT");
            this.response.addHeader("Pragma", "no-cache");
        }
        this.response.getOutputStream().write(shell_exec, 0, shell_exec.length);
    }
    
    public byte[] ExportAsBinary() throws ServletException, IOException {
        final byte[] shell_exec = this.shell_exec(this.prepare_cmd_line(false, false));
        if (this.shell_exec_error.length() > 0) {
            throw new ServletException("SwiffChart: ExportAsBinary(): An error occurred: " + this.shell_exec_error);
        }
        return shell_exec;
    }
    
    public void ExportAsFile(final String s) throws ServletException, IOException {
        this.shell_exec(this.prepare_cmd_line(false, false) + this.so + "o " + this.mangle(s));
        if (this.shell_exec_error.length() > 0) {
            throw new ServletException("SwiffChart: ExportAsFile(): An error occurred: " + this.shell_exec_error);
        }
    }
    
    public void SetJPGQuality(final int jpg_quality) {
        this.jpg_quality = jpg_quality;
    }
    
    public void SetPNGCompLevel(final int png_comp_level) {
        this.png_comp_level = png_comp_level;
    }
    
    public String GetHTMLTag() throws ServletException, IOException {
        final byte[] shell_exec = this.shell_exec(this.prepare_cmd_line(true, false));
        if (this.shell_exec_error.length() > 0) {
            throw new ServletException("SwiffChart: GetHTMLTag(): An error occurred: " + this.shell_exec_error);
        }
        return new String(shell_exec);
    }
    
    public String GetOutputLocation() throws ServletException, IOException {
        final byte[] shell_exec = this.shell_exec(this.prepare_cmd_line(false, true));
        if (this.shell_exec_error.length() > 0) {
            throw new ServletException("SwiffChart: GetOutputLocation(): An error occurred: " + this.shell_exec_error);
        }
        return new String(shell_exec);
    }
    
    protected int substr_count(final String s, final char c) {
        int n = 0;
        int n2 = 0;
        while (true) {
            final int index = s.indexOf(c, n2);
            if (index == -1) {
                break;
            }
            ++n;
            n2 = index + 1;
        }
        return n;
    }
    
    public void LoadStyle(String style) throws ServletException {
        if (this.substr_count(style, '/') == 0 || style.charAt(0) != '/') {
            style = this.install_dir + "/styles/" + style;
        }
        if (new File(style).getName().indexOf(".scs") == -1) {
            style += ".scs";
        }
        if (!new File(style).canRead()) {
            throw new ServletException("SwiffChart: Unable to open file " + style);
        }
        this.style = style;
    }
    
    public String GetVersion() throws ServletException, IOException {
        final byte[] shell_exec = this.shell_exec(this.install_dir + "/swfchart" + this.so + "rawversion");
        if (this.shell_exec_error.length() > 0) {
            throw new ServletException("SwiffChart: GetVersion(): An error occurred: " + this.shell_exec_error);
        }
        return new String(shell_exec);
    }
    
    public void SetInstallDir(final String install_dir) {
        this.install_dir = install_dir;
    }
    
    public void SetDocumentRoot(final String document_root) {
        this.document_root = document_root;
    }
    
    public void SetServletInfo(final HttpServletRequest request, final HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    
    static {
        SwiffChart.hexchars = "0123456789ABCDEF";
    }
}
