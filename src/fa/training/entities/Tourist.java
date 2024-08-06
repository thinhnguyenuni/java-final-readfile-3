package fa.training.entities;

public abstract class Tourist {

	private int type;
	private String id;
	private String hoTen;
	private String ngaySinh;
	private String soDienThoai;
	private String nhuCauMuaVe;
	private String soMuiVacXin;
	private String xacNhanAmTinh;

	public Tourist(int type, String id, String hoTen, String ngaySinh, String soDienThoai, String nhuCauMuaVe,
			String soMuiVacXin, String xacNhanAmTinh) {
		this.type = type;
		this.id = id;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.soDienThoai = soDienThoai;
		this.nhuCauMuaVe = nhuCauMuaVe;
		this.soMuiVacXin = soMuiVacXin;
		this.xacNhanAmTinh = xacNhanAmTinh;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	public String gethoTen() {
		return hoTen;
	}

	public void sethoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getngaySinh() {
		return ngaySinh;
	}

	public void setngaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getsoDienThoai() {
		return soDienThoai;
	}

	public void setsoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getnhuCauMuaVe() {
		return nhuCauMuaVe;
	}

	public void setnhuCauMuaVe(String nhuCauMuaVe) {
		this.nhuCauMuaVe = nhuCauMuaVe;
	}

	public String getsoMuiVacXin() {
		return soMuiVacXin;
	}

	public void setsoMuiVacXin(String soMuiVacXin) {
		this.soMuiVacXin = soMuiVacXin;
	}

	public String getxacNhanAmTinh() {
		return xacNhanAmTinh;
	}

	public void setxacNhanAmTinh(String xacNhanAmTinh) {
		this.xacNhanAmTinh = xacNhanAmTinh;
	}

	/**
	 * hiển thị thông tin
	 * 
	 * @author: ThinhNV30
	 * @birthday: 03/03/1993
	 * @param:
	 * @return:
	 */
	public abstract void showInfo();

	/**
	 * hiển thị thông tin
	 * 
	 * @author: ThinhNV30
	 * @birthday: 03/03/1993
	 * @param:
	 * @return:
	 */
	public void showMe() {
		System.out.println("Type: " + type);
		System.out.println("id: " + id);
		System.out.println("hoTen: " + hoTen);
		System.out.println("ngaySinh: " + ngaySinh);
		System.out.println("soDienThoai: " + soDienThoai);
		System.out.println("nhuCauMuaVe: " + nhuCauMuaVe);
		System.out.println("soMuiVacXin: " + soMuiVacXin);
		System.out.println("xacNhanAmTinh: " + xacNhanAmTinh);
	}

}
