// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

public class UM_GOV_ConiB021c
{
    public String[] DOC_NO;
    public String[] DOC_NM;
    public String[] CREATE_DT;
    public String[] INSTITU_NM;
    public String[] 담당자성명;
    public String[] 담당자전화번호;
    public String[] 공공기관코드;
    public String[] INSTITU_NM1;
    public String[] 담당자성명1;
    public String[] 사업자등록번호;
    public String[] 법인등록번호;
    public String[] 상호명;
    public String[] 우편번호;
    public String[] 주소1;
    public String[] 주소2;
    public String[] 영업종목;
    public String[] 면허번호;
    public String[] 제재근거_1;
    public String[] 제재근거_2;
    public String[] 제재근거코드명;
    public String[] 계약종류;
    public String[] 제재횟수;
    public String[] 제재년월일;
    public String[] 만료년월일;
    public String[] 제재기간;
    public String[] 해약년월일;
    public String[] 기관코드;
    public String[] 기관명;
    public String[] 비고;
    public String[] 입력자ID;
    public String[] 정지구분;
    public String[] 정지일자;
    public String[] 정지사유;
    public String[] 일련번호1;
    public String[] 대표자명;
    public String[] 대표자주민번호;
    public String[] 대표자주소;
    
    public UM_GOV_ConiB021c() {
        this.DOC_NO = new String[] { "", "/gb:Isssta/Header.Details/cc:Document.Number.Text/Text.Content" };
        this.DOC_NM = new String[] { "", "/gb:Isssta/Header.Details/cc:Message.Name/Text.Content" };
        this.CREATE_DT = new String[] { "", "/gb:Isssta/cc:Document.Issue.Date/DateTime.Content" };
        this.INSTITU_NM = new String[] { "", "/gb:Isssta/PublicOrganization.Details/Organization.Details/cc:Organization.Name/Text.Content" };
        this.담당자성명 = new String[] { "", "/gb:Isssta/PublicOrganization.Details/Employee.Details/cc:Employee.Name/Text.Content" };
        this.담당자전화번호 = new String[] { "", "/gb:Isssta/PublicOrganization.Details/Employee.Details/cc:Telephone.Number.Text/Text.Content" };
        this.공공기관코드 = new String[] { "", "/gb:Isssta/PublicOrganization.Details/Organization.Details/cc:Organization.Identifier/Identifier.Content" };
        this.INSTITU_NM1 = new String[] { "", "/gb:Isssta/G2BOrganization.Details/Organization.Details/cc:Organization.Name/Text.Content" };
        this.담당자성명1 = new String[] { "", "/gb:Isssta/G2BOrganization.Details/Employee.Details/cc:Employee.Name/Text.Content" };
        this.사업자등록번호 = new String[] { "", "/gb:Isssta/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content" };
        this.법인등록번호 = new String[] { "", "/gb:Isssta/Organization.Information/Registration.Number.Text/Text.Content" };
        this.상호명 = new String[] { "", "/gb:Isssta/Organization.Information/Organization.Details/cc:Organization.Name/Text.Content" };
        this.우편번호 = new String[] { "", "/gb:Isssta/Organization.Information/Address.Details/cc:PostCode.Identifier/Identifier.Content" };
        this.주소1 = new String[] { "", "/gb:Isssta/Organization.Information/Address.Details/cc:Address.Line1.Text/Text.Content" };
        this.주소2 = new String[] { "", "/gb:Isssta/Organization.Information/Address.Details/cc:Address.Line2.Text/Text.Content" };
        this.영업종목 = new String[] { "", "/gb:Isssta/Organization.Information/Business.Type.Text/Text.Content" };
        this.면허번호 = new String[] { "", "/gb:Isssta/Organization.Information/License.Number.Text/Text.Content" };
        this.제재근거_1 = new String[] { "", "/gb:Isssta/Sanction.Details/Sanction.Basis.Text/Text.Content" };
        this.제재근거_2 = new String[] { "", "/gb:Isssta/Sanction.Details/Sanction.Basis1.Text/Text.Content" };
        this.제재근거코드명 = new String[] { "", "/gb:Isssta/Sanction.Details/Sanction.Basis.Code/Code.Name" };
        this.계약종류 = new String[] { "", "/gb:Isssta/cc:Contract.Type.Code/Code.Name" };
        this.제재횟수 = new String[] { "", "/gb:Isssta/Sanction.Stop.Details/Sanction.Number.Text/Text.Content" };
        this.제재년월일 = new String[] { "", "/gb:Isssta/Sanction.Details/Sanction.Date/DateTime.Content" };
        this.만료년월일 = new String[] { "", "/gb:Isssta/Sanction.Details/Expiration.Date/DateTime.Content" };
        this.제재기간 = new String[] { "", "/gb:Isssta/Sanction.Details/Sanction.Periode.Text/Text.Content" };
        this.해약년월일 = new String[] { "", "/gb:Isssta/Sanction.Details/Contract.Cancel.Date/DateTime.Content" };
        this.기관코드 = new String[] { "", "/gb:Isssta/Sanction.Organization.Code/Code.Content" };
        this.기관명 = new String[] { "", "/gb:Isssta/Sanction.GovernmentOffice.Code/Code.Name" };
        this.비고 = new String[] { "", "/gb:Isssta/cc:General.Note.Text/Text.Content" };
        this.입력자ID = new String[] { "", "/gb:Isssta/Header.Details/cc:Message.Sender.Identifier/Identifier.Content" };
        this.정지구분 = new String[] { "", "/gb:Isssta/Sanction.Stop.Details/Sanction.Indicator/Indicator.Content" };
        this.정지일자 = new String[] { "", "/gb:Isssta/Sanction.Stop.Details/Sanction.Stop.Date/DateTime.Content" };
        this.정지사유 = new String[] { "", "/gb:Isssta/Sanction.Stop.Details/Sanction.Reason.Text/Text.Content" };
        this.일련번호1 = new String[] { "", "/gb:Gsacid/Organization.Information/CeoList/CeoItem/cc:Line.Number.Value/Numeric.Content" };
        this.대표자명 = new String[] { "", "/gb:Gsacid/Organization.Information/CeoList/CeoItem/Organization.CEO.Name/Text.Content" };
        this.대표자주민번호 = new String[] { "", "/gb:Gsacid/Organization.Information/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content" };
        this.대표자주소 = new String[] { "", "/gb:Gsacid/Organization.Information/CeoList/CeoItem/cc:Address.Line1.Text/Text.Content" };
    }
    
    public void UM_GOV_ConiA021c() {
    }
}
