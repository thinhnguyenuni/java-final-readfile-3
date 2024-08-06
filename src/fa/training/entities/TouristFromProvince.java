package fa.training.entities;

public class TouristFromProvince extends Tourist {
	
	private String tinhThanh;
	private String phuongTien;

	

	public TouristFromProvince(int type, String id, String hoTen, String ngaySinh, String soDienThoai,
			String nhuCauMuaVe, String soMuiVacXin, String xacNhanAmTinh, String tinhThanh, String phuongTien) {
		super(type, id, hoTen, ngaySinh, soDienThoai, nhuCauMuaVe, soMuiVacXin, xacNhanAmTinh);
		this.tinhThanh = tinhThanh;
		this.phuongTien = phuongTien;
	}

	public String gettinhThanh() {
		return tinhThanh;
	}

	public void settinhThanh(String tinhThanh) {
		this.tinhThanh = tinhThanh;
	}

	public String getphuongTien() {
		return phuongTien;
	}

	public void setphuongTien(String phuongTien) {
		this.phuongTien = phuongTien;
	}

	@Override
	public void showInfo() {
		super.showMe();
		System.out.println("tinhThanh: " + tinhThanh);
		System.out.println("phuongTien: " + phuongTien);
	}
}
