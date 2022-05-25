import java.util.Scanner;

public class Menu {
	public static void titleMenuChinh() {
		System.out.println("┌────────────────────────────────┐");
		System.out.printf("│ %-30s │" , "MENU CHUC NANG CHINH");
		System.out.print("\n├────────────────────────────────┤");
		System.out.printf("\n│ %-30s │", "1. Cập nhật danh sách");
		System.out.printf("\n│ %-30s │", "2. Hiển thị bảng điểm");
		System.out.printf("\n│ %-30s │", "3. Tìm kiếm");
		System.out.printf("\n│ %-30s │", "0. Thoát");
		System.out.print("\n└────────────────────────────────┘");
		
	}
	
	public static void titleMenuCapNhatDS() {
		System.out.println("\n┌───────────────────────────────────┐");
		System.out.printf("│ %-33s │" , "CẬP NHẬT DANH SÁCH");
		System.out.print("\n├───────────────────────────────────┤");
		System.out.printf("\n│ %-33s │", "1. Cập nhật danh sách sinh viên");
		System.out.printf("\n│ %-33s │", "2. Cập nhật danh sách môn học");
		System.out.printf("\n│ %-33s │", "3. Cập nhật bảng điểm");
		System.out.printf("\n│ %-33s │", "0. Trở về menu trước");
		System.out.print("\n└───────────────────────────────────┘");
		
	}
	
	public static void titleHienThiBangDiem() {
		System.out.println("\n┌──────────────────────────────────────────┐");
		System.out.printf("│ %-40s │" , "HIỂN THỊ BẢNG ĐIỂM");
		System.out.print("\n├──────────────────────────────────────────┤");
		System.out.printf("\n│ %-40s │", "1. Hiển thị theo sinh viên");
		System.out.printf("\n│ %-40s │", "2. Hiển thị bảng điểm theo môn học");
		System.out.printf("\n│ %-40s │", "3. Cập nhật bảng điểm");
		System.out.printf("\n│ %-40s │", "0. Trở về menu trước");
		System.out.print("\n└──────────────────────────────────────────┘");
		
	}
	public static void titleMenuCapNhatDSSV() {
		System.out.println("\n┌───────────────────────────────────┐");
		System.out.printf("│ %-33s │" , "DANH SÁCH SINH VIÊN");
		System.out.print("\n├───────────────────────────────────┤");
		System.out.printf("\n│ %-33s │", "1. Thêm sinh viên");
		System.out.printf("\n│ %-33s │", "2. Sửa thông tin sv");
		System.out.printf("\n│ %-33s │", "3. Xóa sinh viên");
		System.out.printf("\n│ %-33s │", "4. Hiển thị danh sách");
		System.out.printf("\n│ %-33s │", "0. Trở về menu trước");
		System.out.print("\n└───────────────────────────────────┘");
		
	}
	
	public static void titleChucNangTimKiem() {
		System.out.println("\n┌───────────────────────────────────┐");
		System.out.printf("│ %-33s │" , "CHÚC NĂNG TÌM KIẾM");
		System.out.print("\n├───────────────────────────────────┤");
		System.out.printf("\n│ %-33s │", "1. Tìm kiếm theo mã sv");
		System.out.printf("\n│ %-33s │", "2. Tìm kiếm theo tên sv");
		System.out.printf("\n│ %-33s │", "0. Trở về menu trước");
		System.out.print("\n└───────────────────────────────────┘");
	}
	public static void titleMenuCapNhatDSMH() {
		System.out.println("\n┌───────────────────────────────────┐");
		System.out.printf("│ %-33s │" , "DANH SÁCH MÔN HỌC");
		System.out.print("\n├───────────────────────────────────┤");
		System.out.printf("\n│ %-33s │", "1. Thêm môn học");
		System.out.printf("\n│ %-33s │", "2. Sửa môn học");
		System.out.printf("\n│ %-33s │", "3. Xóa môn học");
		System.out.printf("\n│ %-33s │", "4. Hiển thị danh sách");
		System.out.printf("\n│ %-33s │", "0. Trở về menu trước");
		System.out.print("\n└───────────────────────────────────┘");
		
	}
	public static void titleMenuCapNhatBD() {
		System.out.println("\n┌───────────────────────────────────┐");
		System.out.printf("│ %-33s │" , "BẢNG ĐIỂM");
		System.out.print("\n├───────────────────────────────────┤");
		System.out.printf("\n│ %-33s │", "1. Thêm điểm vào ds");
		System.out.printf("\n│ %-33s │", "2. Sửa điểm trong ds");
		System.out.printf("\n│ %-33s │", "3. Xóa điểm trong ds");
		System.out.printf("\n│ %-33s │", "0. Trở về menu trước");
		System.out.print("\n└───────────────────────────────────┘");
		
	}
	
	
}
