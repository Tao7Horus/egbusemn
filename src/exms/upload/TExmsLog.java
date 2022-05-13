// 
// Decompiled by Procyon v0.5.30
// 

package exms.upload;

import java.util.Date;

public class TExmsLog
{
    public String unikey;
    public String msg_type;
    public String doc_type;
    public String status;
    public String err_code;
    public Date import_date;
    public Date analyz_date;
    public Date transm_date;
    public Date inform_date;
    public Date export_date;
    public Date error_date;
    public String from_id;
    public String from_type;
    public String from_grp;
    public String from_contype;
    public String to_id;
    public String to_type;
    public String to_grp;
    public String to_contype;
    public String cpa_id;
    public String conversation_id;
    public String service;
    public String action;
    public String message_id;
    public String ref_message_id;
    public String timestamp;
    public int seq_num;
    public String description;
    public String ack_req_type;
    public String ack_sign_yn;
    public String highestserverity;
    public String org_file_name;
    public long file_size;
    public int attach_count;
    public int resnd_count;
    public String constructname;
    
    public TExmsLog() {
        this.unikey = "";
        this.msg_type = "N";
        this.doc_type = "O";
        this.status = "F";
        this.err_code = "N";
        this.import_date = null;
        this.analyz_date = null;
        this.transm_date = null;
        this.inform_date = null;
        this.export_date = null;
        this.error_date = null;
        this.from_id = "";
        this.from_type = "";
        this.from_grp = "";
        this.from_contype = "";
        this.to_id = "";
        this.to_type = "";
        this.to_grp = "";
        this.to_contype = "";
        this.cpa_id = "";
        this.conversation_id = "";
        this.service = "";
        this.action = "";
        this.message_id = "";
        this.ref_message_id = "";
        this.timestamp = "";
        this.seq_num = 0;
        this.description = "";
        this.ack_req_type = "N";
        this.ack_sign_yn = "N";
        this.highestserverity = "N";
        this.org_file_name = "";
        this.file_size = 0L;
        this.attach_count = 0;
        this.resnd_count = 0;
        this.constructname = "";
    }
}
