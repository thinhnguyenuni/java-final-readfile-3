package fa.training.main;

import java.sql.Connection;
import java.util.Scanner;

import fa.training.dao.Ready_3;
import fa.training.utils.ConnectionUtil;


public class Menu {

	/**
	 * 
	 * @author: ThinhNV30
	 * @birthday: 03/03/1993
	 * @param:
	 * @return:menu
	 */
	public static void menu() throws Exception {
		
		Scanner sc = new Scanner(System.in);

		String st = null;
		int choice;
		do {
			System.out.println("----- Chào mừng bạn đến với chương trình quản lí bài tập của THINHNV30 -----");
			System.out.println("-----CHÚC BẠN NGÀY MỚI TỐT LÀNH-------");
			System.out.println(" 1.Chức năng insert Data----- ");
			System.out.println(" 2.Giới thiệu thông tin của các hành khách hiện có ");
			System.out.println(" 3.Số lượng hành khách có nhu cầu mua vé");
			System.out.println(" 4.Sắp xếp danh sách theo id tăng dần ");
			System.out.println(" 5.Xóa những hành khách chưa tiến hành test covid hoặc ít hơn 2 mũi tiêm !!!");
			System.out.println(" 6.Cập nhật xác nhận âm tính !!!");
			System.out.println(" 7.Cập nhật số mũi tiêm !!!");
			System.out.println("======Nhập vào số nguyên tương ứng với bài tập bạn muốn chạy :");
			System.out.println();
			
			
			while (true) {
				try {
					st = sc.nextLine();
					choice = Integer.parseInt(st);
					System.out.println("Số bạn nhập là : " + choice);
					break;
				} catch (Exception ex) {
					System.out.println(st + "Không phải số nguyên");
					System.out.println("Vui lòng nhập lại ");
				}
			}
			switch (choice) {
			case 1:// Cau 1
					Ready_3.insert();
				
				break;

			case 2:// Cau 2
				Ready_3.getInformation();
				break;

			case 3:// Cau 3
				System.out.println("Số lượng khách có nhu cầu mua vé :"+Ready_3.selectSLHS());
				break;
				
			case 4:// Cau 4
				Ready_3.sortHanhKhach();
				break;
				
			case 5:// Cau 5
				Ready_3.deleteHK();
				break;
				
			case 6:// Cau 5
				Connection connection1 = ConnectionUtil.getConnection();
				System.out.println("Nhập id cần cập nhật xác nhận âm tính !!!");
				String id1 = sc.nextLine();
				
				if(!Ready_3.isIdDuplicate(connection1,id1)) {
					System.out.println("ID"+id1+"không tồn tại trong hệ thống!!!");
				}
				Ready_3.updateXacNhanAmTinh(id1);
				connection1.close();
				break;
				
			case 7:// Cau 5
				Connection connection2 = ConnectionUtil.getConnection();
				System.out.println("Nhập id cần cập nhật số mũi tiêm !!!");
				String id2 = sc.nextLine();
				if(!Ready_3.isIdDuplicate(connection2,id2)) {
					System.out.println("ID"+id2+"không tồn tại trong hệ thống!!!");
				}
				connection2.close();
				Ready_3.updateSoMuiTiem(id2);
				break;
				
			case 0:
				// 0. Thoát
				System.out.println("====Kết thúc chương trình======");
				break;

			default:
				System.out.println("Lựa chọn không hợp lệ");
				break;
			}
		} while (choice != 0);
		
	}
	
	/**
	 * 
	 * @author: ThinhNV30
	 * @birthday: 03/03/1993
	 * @param:
	 * @return:menu
	 */
	public static void main(String[] args) {
		
		try {
			menu();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Program have an unexpected error occurred !!!");
					
			System.out.println("Program is Exit");

		}
	}
	
	
}
