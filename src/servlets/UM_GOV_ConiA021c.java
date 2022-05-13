// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

public class UM_GOV_ConiA021c
{
    public String[] DOC_NO;
    public String[] DOC_NM;
    public String[] CREATE_DT;
    public String[] DOC_FUNCTION;
    public String[] INSTITU_NM;
    public String[] INSTITU_DIRECTOR_NM;
    public String[] CHRG_NM;
    public String[] CHRG_PHONE_NO;
    public String[] INSTITU_CD;
    public String[] INSTITU_NM1;
    public String[] CHRG_NM1;
    public String[] BID_NO;
    public String[] BID_TURN_NO;
    public String[] BID_NM;
    public String[] PUBLIC_DT;
    public String[] CONTR_NO;
    public String[] CONTR_TURN;
    public String[] CONTR_TYPE;
    public String[] CONTR_DT;
    public String[] BIZ_REG_NO;
    public String[] CORP_REG_NO;
    public String[] BIZ_NM;
    public String[] ZIP_CD;
    public String[] ADDR1;
    public String[] ADDR2;
    public String[] BIZ_CAT;
    public String[] LICENSE_NO;
    public String[] SUSPEND_CLS;
    public String[] SUSPEND_DT;
    public String[] SUSPEND_RSON;
    public String[] PUNISH_BASIS_1;
    public String[] PUNISH_BASIS_2;
    public String[] PUNISH_BASIS_CD;
    public String[] PUNISH_BY_LAW;
    public String[] PUNISH_DT;
    public String[] EXPIRE_DT;
    public String[] PUNISH_PERIOD;
    public String[] CANCEL_DT;
    public String[] INSTITU_NM2;
    public String[] INSTITU_CD2;
    public String[] REMARK;
    public String[] INPUT_UM_ID;
    
    public UM_GOV_ConiA021c() {
        this.DOC_NO = new String[] { "", "/gb:Dfisan/Header.Details/cc:Document.Number.Text/Text.Content" };
        this.DOC_NM = new String[] { "", "/gb:Dfisan/Header.Details/cc:Message.Name/Text.Content" };
        this.CREATE_DT = new String[] { "", "/gb:Dfisan/cc:Document.Issue.Date/DateTime.Content" };
        this.DOC_FUNCTION = new String[] { "", "/gb:Dfisan/Header.Details/cc:Message.Function.Code/Code.Content" };
        this.INSTITU_NM = new String[] { "", "/gb:Dfisan/PublicOrganization.Details/Organization.Details/cc:Organization.Name/Text.Content" };
        this.INSTITU_DIRECTOR_NM = new String[] { "", "/gb:Dfisan/PublicOrganization.Details/Organization.Details/cc:Organization.CEO.Name/Text.Content" };
        this.CHRG_NM = new String[] { "", "/gb:Dfisan/PublicOrganization.Details/Employee.Details/cc:Employee.Name/Text.Content" };
        this.CHRG_PHONE_NO = new String[] { "", "/gb:Dfisan/PublicOrganization.Details/Employee.Details/cc:Telephone.Number.Text/Text.Content" };
        this.INSTITU_CD = new String[] { "", "/gb:Dfisan/PublicOrganization.Details/Organization.Details/cc:Organization.Identifier/Identifier.Content" };
        this.INSTITU_NM1 = new String[] { "", "/gb:Dfisan/G2BOrganization.Details/Organization.Details/cc:Organization.Name/Text.Content" };
        this.CHRG_NM1 = new String[] { "", "/gb:Dfisan/G2BOrganization.Details/Employee.Details/cc:Employee.Name/Text.Content" };
        this.BID_NO = new String[] { "", "/gb:Dfisan/Bidding.Details/cc:Bidding.NotifyNumber.Text/Text.Content" };
        this.BID_TURN_NO = new String[] { "", "/gb:Dfisan/Bidding.Details/cc:Bidding.NotifySequenceNumber.Text/Text.Content" };
        this.BID_NM = new String[] { "", "/gb:Dfisan/Bidding.Details/cc:Bidding.Name/Text.Content" };
        this.PUBLIC_DT = new String[] { "", "/gb:Dfisan/Bidding.Notify.Date/DateTime.Content" };
        this.CONTR_NO = new String[] { "", "/gb:Dfisan/cc:Contract.Number.Text/Text.Content" };
        this.CONTR_TURN = new String[] { "", "/gb:Dfisan/cc:Contract.SequenceNumber.Value/Numeric.Content" };
        this.CONTR_TYPE = new String[] { "", "/gb:Dfisan/cc:Contract.Type.Code/Code.Content" };
        this.CONTR_DT = new String[] { "", "/gb:Dfisan/cc:Contract.Date/DateTime.Content" };
        this.BIZ_REG_NO = new String[] { "", "/gb:Dfisan/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content" };
        this.CORP_REG_NO = new String[] { "", "/gb:Dfisan/Organization.Information/Registration.Number.Text/Text.Content" };
        this.BIZ_NM = new String[] { "", "/gb:Dfisan/Organization.Information/Organization.Details/cc:Organization.Name/Text.Content" };
        this.ZIP_CD = new String[] { "", "/gb:Dfisan/Organization.Information/Address.Details/cc:PostCode.Identifier/Identifier.Content" };
        this.ADDR1 = new String[] { "", "/gb:Dfisan/Organization.Information/Address.Details/cc:Address.Line1.Text" };
        this.ADDR2 = new String[] { "", "/gb:Dfisan/Organization.Information/Address.Details/cc:Address.Line2.Text/Text.Content" };
        this.BIZ_CAT = new String[] { "", "/gb:Dfisan/Organization.Information/Business.Type.Text/Text.Content" };
        this.LICENSE_NO = new String[] { "", "/gb:Dfisan/Organization.Information/License.Number.Text/Text.Content" };
        this.SUSPEND_CLS = new String[] { "", "/gb:Dfisan/Sanction.Details/Suspend.Indicator/Indicator.Content" };
        this.SUSPEND_DT = new String[] { "", "/gb:Dfisan/Sanction.Details/Suspend.Date/DateTime.Content" };
        this.SUSPEND_RSON = new String[] { "", "/gb:Dfisan/Sanction.Details/SuspendReason.Text/Text.Content" };
        this.PUNISH_BASIS_1 = new String[] { "", "/gb:Dfisan/Sanction.Details/Sanction.Basis.Text/Text.Content" };
        this.PUNISH_BASIS_2 = new String[] { "", "/gb:Dfisan/Sanction.Details/Sanction.Basis.Code/Code.Content" };
        this.PUNISH_BASIS_CD = new String[] { "", "/gb:Dfisan/Sanction.Details/Sanction.Basis.Code/Code.Name" };
        this.PUNISH_BY_LAW = new String[] { "", "/gb:Dfisan/Sanction.Details/OtherSanction.Basis.Text/Text.Content" };
        this.PUNISH_DT = new String[] { "", "/gb:Dfisan/Sanction.Details/Sanction.Date/DateTime.Content" };
        this.EXPIRE_DT = new String[] { "", "/gb:Dfisan/Sanction.Details/Expiration.Date/DateTime.Content" };
        this.PUNISH_PERIOD = new String[] { "", "/gb:Dfisan/Sanction.Details/Sanction.Periode.Text/Text.Content" };
        this.CANCEL_DT = new String[] { "", "/gb:Dfisan/Sanction.Details/Contract.Cancel.Date/DateTime.Content" };
        this.INSTITU_NM2 = new String[] { "", "/gb:Dfisan/Sanction.Organization.Code/Code.Content" };
        this.INSTITU_CD2 = new String[] { "", "/gb:Dfisan/Sanction.GovernmentOffice.Code/Code.Name" };
        this.REMARK = new String[] { "", "/gb:Dfisan/cc:General.Note.Text/Text.Content" };
        this.INPUT_UM_ID = new String[] { "", "/gb:Dfisan/Header.Details/cc:Message.Sender.Identifier/Identifier.Content" };
    }
    
    public void UM_GOV_ConiA021c() {
    }
}
