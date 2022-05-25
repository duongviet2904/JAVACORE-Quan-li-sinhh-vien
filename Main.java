import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		
		DanhSachSinhVien dssv = new DanhSachSinhVien();
		DanhSachMonHoc dsmh = new DanhSachMonHoc();
		DanhSachDiem dsd = new DanhSachDiem();
		menuChinh(dssv, dsmh, dsd);
	}
	
	public static void menuChinh(DanhSachSinhVien dssv, DanhSachMonHoc dsmh, DanhSachDiem dsd) {
		int chon;
		do {
			Menu.titleMenuChinh();
			System.out.println("\nChọn :");
			chon = sc.nextInt();
			
			switch(chon) {
			case 1:
				capNhatDanhSach(dssv, dsmh, dsd);
				break;
			case 2:
				showBangDiem(dssv, dsmh, dsd);
				break;
			case 3:
				timKiem(dssv, dsmh, dsd);
				break;
			case 0:
				System.out.println("Đóng chương trình!");
				break;
			default:
				System.out.println("Chọn thao tác không hợp lệ.");
			}
		}while(chon!=0);
	}
	public static void capNhatDanhSach(DanhSachSinhVien dssv, DanhSachMonHoc dsmh, DanhSachDiem dsd){
		int chon;
		do {
			Menu.titleMenuCapNhatDS();
			System.out.println("\nChon:");
			chon = sc.nextInt();
			
			switch(chon) {
			case 1:
				dssv.menuSV(); break;
			case 2:
				dsmh.menuMH(); break;
			case 3:
				dsd.menuDiem(); break;
			case 0:
				System.out.println("trở về menu trước");
				menuChinh(dssv, dsmh, dsd);
				break;
			default:
				System.out.println("Chọn thao tác không hợp lệ.");
			}
		}while(chon!=0);
	}
	
	public static void showBangDiem(DanhSachSinhVien dssv, DanhSachMonHoc dsmh, DanhSachDiem dsd) {
		int chon;
		do {
			Menu.titleHienThiBangDiem();
			System.out.println("\nChọn:");
			chon = sc.nextInt();
			switch(chon) {
			case 1:
				dsd.showBangDiemTheoSinhVien();
				break;
			case 2:
				dsd.showBangDiemTheoMonHoc();
				break;
			case 3 :
				dsd.menuDiem();
				break;
			case 0:
				System.out.println("Quay lại");
				menuChinh(dssv, dsmh, dsd);
				break;
			default:
				System.out.println("Chọn hiển thị sai.");
			}
		}while(chon!=0);
	}
	
	public static void timKiem(DanhSachSinhVien dssv, DanhSachMonHoc dsmh, DanhSachDiem dsd) {
		int chon;
		do {
			Menu.titleChucNangTimKiem();
			System.out.println("\n chọn:");
			chon = sc.nextInt();
			switch(chon) {
			case 1:
				dssv.showBangDiem();
				break;
			case 2:
				dssv.timTheoTenSinhVien();
				break;
			case 0:
				System.out.println("Quay lại");
				menuChinh(dssv, dsmh, dsd);
				break;
			default:
				System.out.println("Chọn sai!");
			}
		}while(chon!=0);
	}
}
