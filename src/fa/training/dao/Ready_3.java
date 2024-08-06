package fa.training.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import fa.training.entities.Tourist;
import fa.training.entities.TouristFromForeign;
import fa.training.entities.TouristFromProvince;
import fa.training.exception.DataNotMatchException;
import fa.training.exception.DateInvalidException;
import fa.training.exception.TouristDuplicateException;
import fa.training.utils.ConnectionUtil;

public class Ready_3 {

	public static int numberRecords = 0;

	/**
	 * THINHNV30 1993-03-03 THÊM THÔNG TIN HÀNH KHÁCH TỪ FILE
	 * 
	 * @throws Exception
	 */
	public static void insert() throws Exception {

		File f = new File("D:\\HOC_TAP\\FILE_FULLSTACK\\JPE\\JAVA CODE\\JPE_1\\Ready_3\\src\\data.csv");
		String line = "";

		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Ready (type,id, hoTen, ngaySinh, soDienThoai, nhuCauMuaVe, soMuiVacXin, xacNhanAmTinh, tinhThanh, phuongTien, quocGia, maChuyenBay) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			BufferedReader br = Files.newBufferedReader(f.toPath(), StandardCharsets.UTF_8);
//			int numberRecords = 0;
			while ((line = br.readLine()) != null) {

				String[] data = line.split(",");

				String type = data[0];
				String id = data[1];
				String hoTen = data[2];
				String ngaySinhStr = data[3];
				String soDienThoai = data[4];
				String nhuCauMuaVe = data[5];
				String soMuiVacXin = data[6];
				String xacNhanAmTinh = data[7];

				// Đây là biến boolean cho biết bản ghi có hợp lệ hay không
				boolean isValid = true;
				try {
					// Kiểm tra id
					if (!id.matches("^(P|F)\\d")) {
//						System.out.println("id must be in format of 'PSxxx' for Passenger " + id);
//						continue; // Bỏ qua dòng dữ liệu không hợp lệ
						throw new DataNotMatchException("ID khong dung dinh dang!!!");
					}

					// Kiểm tra soMuiVacXin
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date ngaySinh = dateFormat.parse(ngaySinhStr);
					Date currentDate = new Date();
					if (ngaySinh.after(currentDate)) {
						throw new DateInvalidException("Ngay sinh khong hop le !!!");

					}

					// Kiểm tra id trùng lặp
					if (isIdDuplicate(connection, id)) {
//						System.out.println("Passenger " + id + " has duplicate ID");
//						continue; // Bỏ qua dòng dữ liệu trùng lặp
						throw new TouristDuplicateException("ID " + id + " da bi trung lap truoc do !!!");

					}
				} catch (DataNotMatchException e) { // Đây là một lớp ngoại lệ cho biết lỗi xác thực

					// This is a statement that sets the isValid variable to false
					isValid = false;

					// Đây là câu lệnh in thông báo lỗi ra bàn điều khiển
					System.out.println(e.getMessage());
				} catch (DateInvalidException e) { // Đây là một lớp ngoại lệ cho biết lỗi xác thực

					// This is a statement that sets the isValid variable to false
					isValid = false;

					// Đây là câu lệnh in thông báo lỗi ra bàn điều khiển
					System.out.println(e.getMessage());
				} catch (TouristDuplicateException e) { // Đây là một lớp ngoại lệ cho biết lỗi xác thực

					// This is a statement that sets the isValid variable to false
					isValid = false;

					// Đây là câu lệnh in thông báo lỗi ra bàn điều khiển
					System.out.println(e.getMessage());
				}
				if (isValid) {

					// Nếu tất cả kiểm tra đều qua, thì thực hiện insert
					preparedStatement.setString(1, type);
					preparedStatement.setString(2, id);
					preparedStatement.setString(3, hoTen);
					preparedStatement.setString(4, ngaySinhStr);
					preparedStatement.setString(5, soDienThoai);
					preparedStatement.setString(6, nhuCauMuaVe);
					preparedStatement.setString(7, soMuiVacXin);
					preparedStatement.setString(8, xacNhanAmTinh);

					preparedStatement.setString(9, data[8]); // tinhThanh
					preparedStatement.setString(10, data[9]); // phuongTien
					preparedStatement.setString(11, data[10]); // quocGia

					preparedStatement.setString(12, data[11]); // maChuyenBay

//					preparedStatement.setBoolean(13, Boolean.parseBoolean(data[12])); // PretermBirth

					preparedStatement.executeUpdate();
					numberRecords++;

				}
			}

			br.close();
			preparedStatement.close();
			connection.close();

		} catch (SQLException e) {

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Program have an unexpected error occurred !!!");
			e.printStackTrace();
		}
		if (numberRecords == 0) {
			System.out.println("Insert that bai");
		}
		System.out.println("Số dòng insert thành công là: " + numberRecords);
	}

	/**
	 * 
	 * @author: ThinhNV30
	 * @birthday: 03/03/1993
	 * @param:HÀM KIỂM TRA TRÙNG LẶP id
	 * @return:
	 */
	public static boolean isIdDuplicate(Connection connection, String id) throws SQLException {

		String query = "SELECT id FROM Ready WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		return resultSet.next();
	}

	/**
	 * 
	 * @author: ThinhNV30
	 * @birthday: 03/03/1993
	 * @param:Lấy thông tin từ database
	 * @return:
	 */
	public static ArrayList<Tourist> getPassengersFromDatabase(Connection connection) {
		ArrayList<Tourist> passengers = new ArrayList<>();
		try {
			String query = "SELECT * FROM Ready";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				// Tạo đối tượng Passenger từ dữ liệu ResultSet
				int type = resultSet.getInt("type");
				String id = resultSet.getString("id");
				String hoTen = resultSet.getString("hoTen");
				String ngaySinh = resultSet.getString("ngaySinh");
				String soDienThoai = resultSet.getString("soDienThoai");
				String nhuCauMuaVe = resultSet.getString("nhuCauMuaVe");
				String soMuiVacXin = resultSet.getString("soMuiVacXin");
				String xacNhanAmTinh = resultSet.getString("xacNhanAmTinh");
				String tinhThanh = resultSet.getString("tinhThanh");
				String phuongTien = resultSet.getString("phuongTien");
				String quocGia = resultSet.getString("quocGia");
				String maChuyenBay = resultSet.getString("maChuyenBay");

				if (type == 1) {
					TouristFromProvince child = new TouristFromProvince(type, id, hoTen, ngaySinh, soDienThoai,
							nhuCauMuaVe, soMuiVacXin, xacNhanAmTinh, tinhThanh, phuongTien);
					passengers.add(child);
				} else if (type == 2) {
					TouristFromForeign pregnant = new TouristFromForeign(type, id, hoTen, ngaySinh, soDienThoai,
							nhuCauMuaVe, soMuiVacXin, xacNhanAmTinh, quocGia, maChuyenBay);
					passengers.add(pregnant);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return passengers;
	}

	/**
	 * 
	 * @author: ThinhNV30
	 * @birthday: 03/03/1993
	 * @param:GIỚI THIỆU THÔNG TIN CỦA CÁC HÀNH KHÁCH HIỆN CÓ
	 * @return:
	 */
	public static void showInformation(ArrayList<Tourist> passengers) {
		for (Tourist passenger : passengers) {
			passenger.showInfo();
			System.out.println(); // In một dòng trống để phân tách giữa các hành khách
		}
	}

	/**
	 * THINHNV30 1993-03-03 GIỚI THIỆU THÔNG TIN CỦA CÁC HÀNH KHÁCH HIỆN CÓ
	 */
	public static void getInformation() {

		Connection conn = ConnectionUtil.getConnection();
		// Tạo một danh sách hành khách
		ArrayList<Tourist> passengers = new ArrayList<Tourist>();
		passengers = getPassengersFromDatabase(conn);

		// Gọi phương thức showInformation để hiển thị thông tin của hành khách
		showInformation(passengers);
	}


	/**
	 * 
	 * @author: ThinhNV30
	 * @birthday: 03/03/1993
	 * @param:SẮP XẾP DANH SÁCH HÀNH KHÁCH
	 * @return:
	 */
	public static void sortHanhKhach() {
		try {
			Connection connection = ConnectionUtil.getConnection();

			// Truy vấn dữ liệu hành khách từ cơ sở dữ liệu
			ArrayList<Tourist> passengers = getPassengersFromDatabase(connection);

			// Sắp xếp danh sách hành khách theo yêu cầu
			Collections.sort(passengers, Comparator.comparing(Tourist::getid));
			;

			// Hiển thị danh sách đã sắp xếp
			showInformation(passengers);

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @author: ThinhNV30
	 * @birthday: 03/03/1993
	 * @param:số lượng hành khách mua vé
	 * @return:
	 */
	public static int selectSLHS() {

		int n = 0;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement prst = con.prepareStatement(
						"select count('x') as so_luong_khach_mua\r\n" + "from Ready WHERE nhuCauMuaVe = 'TRUE' ")) {

			ResultSet rs = prst.executeQuery();
			while (rs.next()) {

				n = rs.getInt("so_luong_khach_mua");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi truy vấn");
			e.printStackTrace();
		}
		return n;
	}

	/**
	 * 
	 * @author: ThinhNV30
	 * @birthday: 03/03/1993
	 * @param:xóa hành khách
	 * @return:
	 */
	public static void deleteHK() {
		int row;
		try (

				Connection connection = ConnectionUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"delete from Ready \r\n" + "where [soMuiVacXin]<2 \r\n" + "or xacNhanAmTinh = 'false'")) {
			row = preparedStatement.executeUpdate();

			System.out.println("Xoa hanh khach voi so mui vac xin nho hon 1 va chua co ket qua am tinh !!!");
			System.out.println("Số lượng khách bị xóa là : " + row);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * updateXacNhanAmTinh
	 * 
	 * @author: thinhnv30
	 * @birthday: 1989/11/24
	 */
	public static void updateXacNhanAmTinh(String id) {
		String sql = "UPDATE Ready SET xacNhanAmTinh='TRUE' WHERE id = ?";

		try (Connection conn = ConnectionUtil.getConnection(); 
				PreparedStatement prstmt = conn.prepareStatement(sql)) {
			prstmt.setString(1, id);
			int row = prstmt.executeUpdate();
			System.out.println("Update " + row + " rows successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update failed!");
		}

	}
	
	/**
	 *updateXacNhanAmTinh
	 * 
	 * @author: thinhnv30
	 * @birthday: 1989/11/24
	 */
	public static void updateSoMuiTiem(String id) {
		String sql = "UPDATE Ready SET soMuiVacXin=soMuiVacXin+1 WHERE id = ?";

		try (Connection conn = ConnectionUtil.getConnection(); 
				PreparedStatement prstmt = conn.prepareStatement(sql)) {
			prstmt.setString(1, id);
			int row = prstmt.executeUpdate();
			System.out.println("Update " + row + " rows successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Update failed!");
		}

	}
}
