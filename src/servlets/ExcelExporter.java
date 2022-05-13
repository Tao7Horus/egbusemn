// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.util.List;
import beans.NationalRefDAO;
import jxl.write.WritableCell;
import jxl.format.CellFormat;
import jxl.write.Label;
import java.io.IOException;
import javax.servlet.ServletException;
import jxl.write.WritableWorkbook;
import entity.UM_GOE_ConiC010b;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.format.BorderLineStyle;
import jxl.format.Border;
import jxl.format.Colour;
import jxl.write.WritableFont;
import java.io.OutputStream;
import jxl.Workbook;
import entity.UM_GOE_ConiC110b;
import entity.UM_GOE_ConiC070b;
import entity.UM_GOE_ConiC050b;
import entity.UM_GOE_ConiC030b;
import entity.UM_GOE_ConiC020b;
import beans.UM_URB_UserU010p;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import javax.servlet.http.HttpServlet;

public class ExcelExporter extends HttpServlet
{
    private static final long serialVersionUID = 6098008158173980170L;
    private int startHRow;
    private int startVRow;
    private int numCellsMerger;
    private WritableSheet worksheet;
    WritableCellFormat labelCellFormat;
    WritableCellFormat valueCellFormat;
    WritableCellFormat headerCellFormat;
    
    public ExcelExporter() {
        this.startHRow = 5;
        this.startVRow = 5;
        this.numCellsMerger = 5;
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String saupNo = request.getParameter("saupNo");
        final UM_URB_UserU010p ctl = new UM_URB_UserU010p();
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=Thông tin nhà thầu.xls");
            if (saupNo.equals("") || saupNo == null) {
                throw new Exception("[Chỉnh sửa thông tin nhà thầu/Đăng ký sản xuất] Danh sách các lỗi đã xảy ra.<br>Sau khi đăng nhập xin vui lòng thử lại.");
            }
            UM_GOE_ConiC010b ett = null;
            UM_GOE_ConiC020b[] ett2 = (UM_GOE_ConiC020b[])null;
            UM_GOE_ConiC030b[] ett3 = (UM_GOE_ConiC030b[])null;
            UM_GOE_ConiC050b[] ett4 = (UM_GOE_ConiC050b[])null;
            UM_GOE_ConiC070b[] ett5 = (UM_GOE_ConiC070b[])null;
            final UM_GOE_ConiC110b[] ett6 = (UM_GOE_ConiC110b[])null;
            ett = ctl.select_main(saupNo);
            ett2 = ctl.select_MS(saupNo);
            ett3 = ctl.select_DP(saupNo);
            ett4 = ctl.select_ID(saupNo);
            ett5 = ctl.select_stat(saupNo);
            final int branchgubun = ctl.getBranchGubun(saupNo);
            final WritableWorkbook workbook = Workbook.createWorkbook((OutputStream)response.getOutputStream());
            this.worksheet = workbook.createSheet("Thông tin nhà thầu", 0);
            final WritableFont arial11font = new WritableFont(WritableFont.ARIAL, 11, WritableFont.NO_BOLD, true);
            final WritableFont arial13font = new WritableFont(WritableFont.ARIAL, 13, WritableFont.BOLD, true);
            this.labelCellFormat = new WritableCellFormat(arial11font);
            this.valueCellFormat = new WritableCellFormat(arial11font);
            this.headerCellFormat = new WritableCellFormat(arial13font);
            this.labelCellFormat.setBackground(Colour.GRAY_25);
            this.labelCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            this.valueCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            this.headerCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            this.labelCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            this.labelCellFormat.setAlignment(Alignment.LEFT);
            this.valueCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            this.valueCellFormat.setAlignment(Alignment.LEFT);
            this.headerCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            this.headerCellFormat.setAlignment(Alignment.CENTRE);
            this.addComInfo(ett);
            ++this.startVRow;
            workbook.write();
            workbook.close();
        }
        catch (Exception ex) {}
    }
    
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
    }
    
    private void addCaInfo() throws Exception {
        this.addItem(this.startHRow, this.startVRow, "Thông tin chứng nhận số", "", 3);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Cơ quan chứng nhận số", "Cục Quản lý Đấu thầu - Bộ Kế hoạch và Đầu tư", 1);
        this.addItem(this.startHRow, this.startVRow, "Chi nhánh phê duyệt", "Cục quản lý đấu thầu ", 2);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Tên người đại diệni", "Tran thanh Minh an", 1);
        this.addItem(this.startHRow, this.startVRow, "Số CMND", "345655467 ", 2);
    }
    
    private void addBidAgent() throws Exception {
        this.addItem(this.startHRow, this.startVRow, "Thông tin người đại diện dự thầu", "", 3);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Tên người đại diện", "Lu Minh Bui", 0);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Số CMND", "876978906", 0);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Số di động", "43657865", 1);
        this.addItem(this.startHRow, this.startVRow, "Địa chỉ email", "mail@yahoo.com", 2);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Chức vụ", "giam doc", 1);
        this.addItem(this.startHRow, this.startVRow, "Phòng/Ban", "hanh chinh ", 2);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Số điện thoại", "9879876", 1);
        this.addItem(this.startHRow, this.startVRow, "Số Fax", "45887987000 ", 2);
        ++this.startVRow;
    }
    
    private void addStdCls() throws Exception {
        this.addItem(this.startHRow, this.startVRow, "Tên ngành nghề", "Sản xuất thiết bị điện chiếu sáng ", 0);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Mã ngành nghề", "27400", 1);
        this.addItem(this.startHRow, this.startVRow, "Là nghành nghề chính", "Có", 2);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Số đăng ký", "32454657897", 0);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Ngày đăng ký", "10/07/2007", 1);
        this.addItem(this.startHRow, this.startVRow, "Ngày hết hiệu lực", "23/07/2011", 2);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Số tiền bảo lãnh", "567.800.000.000 (VND)", 1);
        this.addItem(this.startHRow, this.startVRow, "Năm đăng ký tiền bảo lãnh", "1999", 2);
    }
    
    private void addCeoInfo() throws Exception {
        this.addItem(this.startHRow, this.startVRow, "Tên người lãnh đạo", "Nguyen Hai Vu", 0);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Số CMND", "465465748", 0);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Số điện thoại", "235463786", 1);
        this.addItem(this.startHRow, this.startVRow, "Địa chỉ email", "hin@yahoo.com", 2);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Là giám đốc", "Có ", 0);
    }
    
    private void addComInfo(final UM_GOE_ConiC010b ett) throws Exception {
        this.worksheet.addCell((WritableCell)new Label(this.startHRow, this.startVRow, "Số văn bản: 234658676969679679 | Ngày soạn thảo: 14/07/2009", (CellFormat)this.valueCellFormat));
        this.worksheet.mergeCells(this.startHRow, this.startVRow, this.startHRow + this.numCellsMerger, this.startVRow);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Thông tin doanh nghiệp", "", 3);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Số tiếp nhận", "2009071406071871", 0);
        ++this.startVRow;
        this.worksheet.addCell((WritableCell)new Label(this.startHRow, this.startVRow, "Tên doanh nghiệp", (CellFormat)this.labelCellFormat));
        this.worksheet.mergeCells(this.startHRow, this.startVRow, this.startHRow, this.startVRow + 1);
        this.worksheet.addCell((WritableCell)new Label(this.startHRow + 1, this.startVRow, "đầy đủ", (CellFormat)this.labelCellFormat));
        this.worksheet.addCell((WritableCell)new Label(this.startHRow + 2, this.startVRow, ett.getSangho(), (CellFormat)this.valueCellFormat));
        this.worksheet.mergeCells(this.startHRow + 2, this.startVRow, this.startHRow + this.numCellsMerger, this.startVRow);
        ++this.startVRow;
        final String enName = (ett.getESangho() == null) ? "" : ett.getESangho();
        this.worksheet.addCell((WritableCell)new Label(this.startHRow + 1, this.startVRow, "tiếng Anh", (CellFormat)this.labelCellFormat));
        this.worksheet.addCell((WritableCell)new Label(this.startHRow + 2, this.startVRow, enName, (CellFormat)this.valueCellFormat));
        this.worksheet.mergeCells(this.startHRow + 2, this.startVRow, this.startHRow + this.numCellsMerger, this.startVRow);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Số ĐKKD", ett.getSaupNo(), 1);
        this.addItem(this.startHRow, this.startVRow, "Ngày ĐKKD", ett.getOpenDate(), 2);
        ++this.startVRow;
        final String typeSupplier = (ett.getComGubun1() == null) ? "" : ett.getComGubun1();
        String typeName = "";
        if ("01".equals(typeSupplier)) {
            typeName = "Doanh nghiệp tư nhân";
        }
        else if ("02".equals(typeSupplier)) {
            typeName = "Công ty trách nhiệm hữu hạn";
        }
        else if ("03".equals(typeSupplier)) {
            typeName = "Công ty cổ phần";
        }
        else if ("04".equals(typeSupplier)) {
            typeName = "Công ty hợp danh";
        }
        else if ("05".equals(typeSupplier)) {
            typeName = "Hợp tác xã";
        }
        else if ("06".equals(typeSupplier)) {
            typeName = "Công ty liên doanh";
        }
        else if ("07".equals(typeSupplier)) {
            typeName = "Công ty 100% vốn nước ngoài";
        }
        this.addItem(this.startHRow, this.startVRow, "Phân loại doanh nghiệp", typeName, 1);
        final String businessType = ett.getJobGubun();
        String businessName = "";
        if (businessType.indexOf("1") >= 0) {
            businessName = String.valueOf(businessName) + "Hàng hóa, ";
        }
        else if (businessType.indexOf("3") >= 0) {
            businessName = String.valueOf(businessName) + "Xây lắp, ";
        }
        else if (businessType.indexOf("5") >= 0) {
            businessName = String.valueOf(businessName) + "Tư vấn, ";
        }
        businessName = businessName.substring(0, businessName.lastIndexOf(","));
        this.addItem(this.startHRow, this.startVRow, "Lĩnh vực kinh doanh", businessName, 2);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Số nhân viên", String.valueOf(ett.getEmployeeNo()) + " (người)", 1);
        this.addItem(this.startHRow, this.startVRow, "Vốn điều lệ", String.valueOf(ett.getJabon()) + " (VND)", 2);
        ++this.startVRow;
        final NationalRefDAO nationalCtrl = new NationalRefDAO();
        final List names = nationalCtrl.getNameByCD("I99", ett.getNationality());
        this.addItem(this.startHRow, this.startVRow, "Quốc gia", names.get(0).toString(), 1);
        final List areas = nationalCtrl.getNameByCD("J05", ett.getLocalCode());
        this.addItem(this.startHRow, this.startVRow, "Tỉnh/Thành phố", areas.get(0).toString(), 2);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Địa chỉ", ett.getAddr(), 1);
        final String web = (ett.getHomepage() == null) ? "" : ett.getHomepage();
        this.addItem(this.startHRow, this.startVRow, "Trang web", web, 2);
        ++this.startVRow;
        this.addItem(this.startHRow, this.startVRow, "Số điện thoại", ett.getTel(), 1);
        final String fax = (ett.getFax() == null) ? "" : ett.getFax();
        this.addItem(this.startHRow, this.startVRow, "Số Fax", fax, 2);
    }
    
    private void addItem(final int hoz, final int ver, final String title, final String value, final int type) throws Exception {
        if (type == 0) {
            this.worksheet.addCell((WritableCell)new Label(hoz, ver, title, (CellFormat)this.labelCellFormat));
            this.worksheet.mergeCells(hoz, ver, hoz + 1, ver);
            this.worksheet.addCell((WritableCell)new Label(hoz + 2, ver, value, (CellFormat)this.valueCellFormat));
            this.worksheet.mergeCells(hoz + 2, ver, hoz + this.numCellsMerger, ver);
        }
        else if (type == 1) {
            this.worksheet.addCell((WritableCell)new Label(hoz, ver, title, (CellFormat)this.labelCellFormat));
            this.worksheet.mergeCells(hoz, ver, hoz + 1, ver);
            this.worksheet.addCell((WritableCell)new Label(hoz + 2, ver, value, (CellFormat)this.valueCellFormat));
        }
        else if (type == 2) {
            this.worksheet.addCell((WritableCell)new Label(hoz + 3, ver, title, (CellFormat)this.labelCellFormat));
            this.worksheet.mergeCells(hoz + 3, ver, hoz + 4, ver);
            this.worksheet.addCell((WritableCell)new Label(hoz + 5, ver, value, (CellFormat)this.valueCellFormat));
        }
        else if (type == 3) {
            this.worksheet.addCell((WritableCell)new Label(hoz, ver, title, (CellFormat)this.headerCellFormat));
            this.worksheet.mergeCells(hoz, ver, hoz + this.numCellsMerger, ver);
        }
    }
}
