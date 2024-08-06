package fa.training.entities;

public class TouristFromForeign extends Tourist {

//	private int quocGia;
//	private boolean maChuyenBay;
	private String quocGia;
	private String maChuyenBay;



	public TouristFromForeign(int type, String id, String hoTen, String ngaySinh, String soDienThoai,
			String nhuCauMuaVe, String soMuiVacXin, String xacNhanAmTinh, String quocGia, String maChuyenBay) {
		super(type, id, hoTen, ngaySinh, soDienThoai, nhuCauMuaVe, soMuiVacXin, xacNhanAmTinh);
		this.quocGia = quocGia;
		this.maChuyenBay = maChuyenBay;
	}

	public String getquocGia() {
		return quocGia;
	}

	public void setquocGia(String quocGia) {
		this.quocGia = quocGia;
	}

	public String ismaChuyenBay() {
		return maChuyenBay;
	}

	public void setmaChuyenBay(String maChuyenBay) {
		this.maChuyenBay = maChuyenBay;
	}

	@Override
	public void showInfo() {
		super.showMe();
		System.out.println("Gestational Age: " + quocGia);
		System.out.println("Preterm Birth: " + maChuyenBay);
	}
}
