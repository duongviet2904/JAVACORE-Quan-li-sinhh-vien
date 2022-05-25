import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class DanhSachMonHoc {
	private List<SinhVien> listSV ;
	private List<MonHoc> listMH;
	private List<Diem> listDiem;
	private ThaoTacFile thaoTac;
	
	Scanner sc = new Scanner(System.in);
	
	public DanhSachMonHoc() {
		thaoTac = new ThaoTacFile();
		listSV = thaoTac.docFileSV();
		listMH = thaoTac.docFileMH();
		listDiem = thaoTac.docFileDiem();
	}
	public void menuMH() {
		int chon;
		do {
			Menu.titleMenuCapNhatDSMH();
			System.out.println("\nChọn:");
			chon = sc.nextInt();
			switch (chon) {
			case 1:
				themMonHoc(); break;
			case 2:
				suaMonHoc(); break;
			case 3:
				xoaMonHoc(); break;
			case 4:
				showMenuDanhSach(); break;
			case 0:
				break;
			default:
				System.out.println("Chọn không hợp lệ!");
				break;
			}
		} while (chon!=0);
	}
	
	public void themMonHoc() {
		Scanner sc = new Scanner (System.in);
		String tenMH = inputTenMH();
		float heSo = inputHeSo();
		MonHoc mh = new MonHoc(addMa(), tenMH, heSo);
		listMH.add(mh);
		ThaoTacFile.count2++;
		thaoTac.ghiFileMH();
	}
	
	public void suaMonHoc() {
		Scanner sc = new Scanner(System.in);
		String tam = sc.nextLine();
		int dem = 0;
		for(int i = 0; i < listMH.size(); i++) {
			if(tam.equalsIgnoreCase(listMH.get(i).getMaMH()) == true) {
				int chon;
				do {
					System.out.println("\n1. Sửa tên môn học");
					System.out.println("2. Xóa môn học");
					System.out.println("0. Trở về trang trước");
					System.out.println("==>");
					chon = sc.nextInt();
					switch(chon) {
						case 1 : listMH.get(i).setTenMH(inputTenMH()); break;
						case 2 : listMH.get(i).setHeSo(inputHeSo()); break;
						case 0 : menuMH(); break;
						default : System.out.println("Lựa chọn không hợp lệ!");
					}
				}while(chon != 0);
			}else {
				dem++;
			}
		}
		if(dem == listMH.size()) {
			System.err.println("Mã môn học không tồn tại!");
		}
		thaoTac.ghiFileMH();
	}
	
	public void xoaMonHoc() {
		int dem = 0;
		int dem1 = 0;
		System.out.println("Nhập mã môn học muốn xóa");
		String tam = sc.nextLine();
		for (int i = 0; i < listMH.size(); i++) {
			if (tam.equalsIgnoreCase(listMH.get(i).getMaMH())) {
				dem++;
			}
		}
		if (dem > 0) {
			for (int i = 0; i < listDiem.size(); i++) {
				if (tam.equalsIgnoreCase(listDiem.get(i).getMaMH())) {
					dem1++;
				}
			}
		}
		if(dem != 0) {
			if (dem1 == 0) {
				for (int i = 0; i < listMH.size(); i++) {
					if (listMH.get(i).getMaMH().equalsIgnoreCase(tam)) {
							titleMH();
							listMH.get(i).showInfo();
							listMH.remove(i);
							System.out.println("Xóa thành công!");
						}
					}
				}else {
				System.out.println("Môn học này đã có người tham gia thi!");
			}
		}else {
			System.out.println("Mã môn học không hợp lệ!");
		}
		thaoTac.ghiFileMH();
	}
	
	public void titleMH() {
		System.out.print("\n┌────────────────────┬────────────────────────────┬───────────┐");
		System.out.printf("\n│ %-19s│ %-27s│ %-10s│", "Mã SM", "Tên MH", "Hệ số");
		System.out.print("\n├────────────────────┼────────────────────────────┼───────────┤");
	}
	
	public int showMotTrangMonHoc(int soTrang) {
		Collator col = Collator.getInstance(new Locale("vi", "vn"));
		Collections.sort(listMH, (s1, s2) -> col.compare(s1.getTenMH(), s2.getTenMH()));
		
		int n = listMH.size() /30 + 1;
		
		
		System.out.println("\n-------------------DANH SÁCH MÔN HỌC---------------------");
		titleMH();
		int begin, end;
		begin = (soTrang - 1) * 30;
		end = soTrang * 30;
		
		for (int j = begin; j < end; j++) {
			MonHoc m = listMH.get(j);
			m.showInfo();
			if (j >= listMH.size()-1) 
				break;
				
		}
		System.out.print("\n└────────────────────┴────────────────────────────┴───────────┘");
		System.out.println(
				"\n---------------------------trang " + soTrang + "/" + n + "---------------------------");
		System.out.printf("\n\n%-30s %-30s %-30s", "1.Xem trang tiếp theo", "3.Đến trang cuối", "5.Xem trang cụ thể");
		System.out.printf("\n%-30s %-30s %-30s", "2.Trở lại trang trước", "4.Đến trang đầu tiên",
				"0.Trở về menu trước");
		return soTrang;
	}
	
	public void showMenuDanhSach() {
		int chon;
		int n = listMH.size() / 30+1;
		int trangnow = showMotTrangMonHoc(1);
		
		do {
			System.out.println("\nChon:");
			chon = sc.nextInt();
			switch (chon) {
			case 1:
				trangnow++;
				if(trangnow <= n) {
					showMotTrangMonHoc(trangnow);
				}else {
					System.out.println("Đây đã là trang cuối cùng");
					showMenuDanhSach();
				}
				break;
			case 2:
				if (trangnow >= 1) {
					trangnow--;
					showMotTrangMonHoc(trangnow);
				}else {
					System.out.println("Đây là trang đầu tiên");
					showMenuDanhSach();
				}
				break;
			case 3:
				trangnow = n;
				showMotTrangMonHoc(trangnow);
				break;
			case 4:
				showMotTrangMonHoc(1);
				break;
			case 5:
				System.out.println("Trang muốn xem :");
				trangnow = sc.nextInt();
				if(trangnow <= n && trangnow >= 1) {
					showMotTrangMonHoc(trangnow);
				}else {
					System.out.println("Hiển thị danh sách môn học: ");
					showMenuDanhSach();
				}
				break;
			case 0:
				menuMH();
				break;
			default:
				System.out.println("Chọn không hợp lệ!");
			}
		} while (chon != 0);
	}
	
	
	public boolean testTenMH(String tenMH) {
		int dem = 0;
		for(int i = 0; i < listMH.size(); i++) {
			if(tenMH.equalsIgnoreCase(listMH.get(i).getTenMH()) == true) {
				continue;
			}else {
				dem++;
			}
		}
		if(dem == listMH.size() && tenMH.matches(SinhVien.STRING) == true && tenMH != null) {
			return true;
		}else {
			System.err.println("Lỗi : Không đúng định dạng hoặc tên môn học đã tồn tại! ");
			return false;
		}
	}
	
	public String inputTenMH() {
		Scanner sc = new Scanner(System.in);
		String tenMH;
		do {
			System.out.println("Tên môn học :");
			tenMH = sc.nextLine();
			
		}while(!testTenMH(tenMH));
		return tenMH;
	}
	
	public Float inputHeSo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Hệ số môn học :");
		float heSo = sc.nextFloat();
		return heSo;
	}
	public String addMa() {
		int id = ThaoTacFile.count2 + 1;
		String set = String.format("%03d", id);
		return set;
	}
	
	
}
