import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;



public class DanhSachSinhVien {
	private List<SinhVien> listSV ;
	private List<MonHoc> listMH;
	private List<Diem> listDiem;
	private ThaoTacFile thaoTac;
	
	Scanner sc = new Scanner(System.in);
	
	public DanhSachSinhVien() {
		thaoTac = new ThaoTacFile();
		listSV = thaoTac.docFileSV();
		listMH = thaoTac.docFileMH();
		listDiem = thaoTac.docFileDiem();
	}


	public void themSinhVien() {
			System.out.println("Nhập thông tin sinh viên muốn thêm :");
			String hoDem = SinhVien.inputHoDem();
			String ten = SinhVien.inputTen();
			String ngaySinh = SinhVien.inputNgaySinh();
			String gioiTinh = SinhVien.inputGioiTinh();
			SinhVien sv = new SinhVien(addMaSV(),hoDem, ten, ngaySinh, gioiTinh);
			
			listSV.add(sv);
			System.out.println("Cập nhật thành công!");
			titleDSSV();
			sv.showInfo();
			System.out.print("\n└───────────┴──────────────────────────┴────────────────┴────────────────┴───────────┘");
			ThaoTacFile.count1++;
			thaoTac.ghiFileSV();
		
	}
	
	public void menuSV() {
		int chon;
		do {
			Menu.titleMenuCapNhatDSSV();
			System.out.println("\n==>");
			chon = sc.nextInt();
			switch (chon) {
				case 1: themSinhVien(); break;
				case 2: suaSinhVien(); break;
				case 3: xoaSinhVien(); break;
				case 4: showMotTrangSV(1);
						showMenuDS(); break;
				case 0: break;
				default: System.err.println("Nhập không hợp lê!");
			}
		}while(chon != 0);
		
	}
	
	public void suaSinhVien() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập mã của sinh viên muốn sửa :");
		String tam = sc.nextLine();
		int dem = 0;
		for(int i = 0; i < listSV.size(); i++) {
			if(tam.equalsIgnoreCase(listSV.get(i).getMaSv()) == true) {
				int chon;
				do {
					System.out.println("\n1.Sửa họ đệm");
					System.out.println("2.Sửa tên");
					System.out.println("3.Sửa ngày sinh");
					System.out.println("4.Sửa giới tính");
					System.out.println("0.Trở về sang trước");
					System.out.println("==>");
					chon = sc.nextInt();
					switch (chon) {
						case 1: listSV.get(i).setHoDem(SinhVien.inputHoDem()); break;
						case 2: listSV.get(i).setTen(SinhVien.inputTen()); break;
						case 3: listSV.get(i).setNgaySinh(SinhVien.inputNgaySinh()); break;
						case 4: listSV.get(i).setGioiTinh(SinhVien.inputGioiTinh()); break;
						case 0: menuSV(); break;
						default : System.out.println("Nhập không hợp lệ!"); break;
					}
				}while(chon != 0);
			}
			else {
				dem++;
			}
		}
		if(dem == listSV.size()) {
			System.err.println("Sinh viên không hợp lê!");
		}
		thaoTac.ghiFileSV();
	}
	
	public void xoaSinhVien() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập mã sinh viên muốn xóa:");
		String tam = sc.nextLine();
		int dem = 0;
		int dem2 = 0;
		int index = 0;
		for(int i = 0; i < listSV.size(); i++) {
			if(tam.equalsIgnoreCase(listSV.get(i).getMaSv()) == true) {
				index = i;
			}
			else {
				dem++;
			}
		}
		for(int i = 0; i < listDiem.size(); i++) {
			if(tam.equalsIgnoreCase(listDiem.get(i).getMaSV()) == true) {
				continue;
			}
			else {
				dem2++;
			}
		}
		if(dem < listSV.size()) {
			if(dem2 < listDiem.size()) {
				System.out.println("Sinh viên đã tham gia dự thi! không thể xóa");
			}else {
				listSV.remove(index);
				System.out.println("Đã xóa!");
			}
		}
		thaoTac.ghiFileSV();
	}
	
	public int showMotTrangSV(int sotrang) {
		Collator col = Collator.getInstance(new Locale("vi", "vn"));
		Collections.sort(listSV, (s1, s2) -> col.compare(s1.getTen(), s2.getTen()));
		
		int n = listSV.size() / 50 + 1;
		System.out.println("\n----------------------------------DANH SÁCH SINH VIÊN---------------------------------");	
		titleDSSV();
		int begin, end;
		begin = (sotrang - 1) * 50;
		end = sotrang * 50;
		for (int i = begin; i < end; i++) {
			SinhVien s = listSV.get(i);
			s.showInfo();
			if (i >= listSV.size() - 1)
				break;
		}
		System.out.print("\n└───────────┴──────────────────────────┴────────────────┴────────────────┴───────────┘");
		System.out.println(
				"\n------------------------------trang " + sotrang + "/" + n + "------------------------------");
		System.out.printf("\n\n%-30s %-30s %-30s", "1.Xem trang tiếp theo", "3.Đến trang cuối", "5.Xem trang cụ thể");
		System.out.printf("\n%-30s %-30s %-30s", "2.Trở lại trang trước", "4.Đến trang đầu tiên",
				"0.Trở về menu trước");
		System.out.printf("\n%-30s", "6.Xem chi tiết bảng điểm");
		return sotrang;
	}
	
	public void showMenuDS() {
		int chon;
		int n = listSV.size() / 50;
		int trangnow = showMotTrangSV(1);
		do {
			System.out.println("\n==>");
			chon = sc.nextInt();
			switch (chon) {
			case 1:
				if(trangnow <= n) {
					++trangnow;
					showMotTrangSV(trangnow);
				}else {
					showMotTrangSV(trangnow);
					System.out.println("Đây đã là trang cuối cùng");
				}
				break;
			case 2:
				if (trangnow >= 1) {
					--trangnow;
					showMotTrangSV(trangnow);
				} else {
					showMotTrangSV(trangnow);
					System.out.println("Đây đã là trang đầu tiên");
				}
				break;
			case 3:
				trangnow = n;
				showMotTrangSV(trangnow);
				break;
			case 4:
				showMotTrangSV(1);
				break;
			case 5:
				System.out.println("Trang bạn muốn xem :");
				trangnow = sc.nextInt();
				if (trangnow <= n && trangnow >= 0) {
					showMotTrangSV(trangnow);
				} else {
					System.out.println("Trang không hợp lệ!");
				}
				break;
			case 6:
				showBangDiem();
				break;
			case 0: break;
			default:
				System.err.println("Lựa chọn không hợp lệ!");
			}
		} while (chon != 0);
	}
	
	public void showBangDiem() {
		HashMap<String, Float> map1 = new HashMap<String, Float>();
		HashMap<String, Float> map2 = new HashMap<String, Float>();
		SinhVien sv = null;
		
		float diemTongKet = 0;
		float diemTong = 0;
		
		int dem = 0;
		int dem1 = 0;
		float tongHeSo = 0;
		
		System.out.println("\nNhập mã sinh viên muốn xem:");
		String m = sc.nextLine();
		
		List<String> listMHDaThi = new ArrayList<String>();

		
		for (int i = 0; i < listSV.size(); i++) {
			if (m.equalsIgnoreCase(listSV.get(i).getMaSv())) {
				sv = listSV.get(i);
				dem++;
			}
		}
		if (dem != 0) {
			for (int i = 0; i < listDiem.size(); i++) {
				if (m.equalsIgnoreCase(listDiem.get(i).getMaSV())) {
					map1.put(listDiem.get(i).getMaMH(), listDiem.get(i).getDiem());
					listMHDaThi.add(listDiem.get(i).getMaMH());
					dem1++;
					
				}
			}
		} else {
			System.out.println("Sinh viên không tồn tại!");
		}
		
		
		for (int i = 0; i < listMH.size(); i++) {
			map2.put(listMH.get(i).getMaMH(), listMH.get(i).getHeSo());
		}
		
		for (String str1 : map1.keySet()) {
			for (String str2 : map2.keySet()) {
				if (str1.equals(str2)) {
					diemTong = diemTong + map1.get(str1) * map2.get(str2);
					tongHeSo = tongHeSo + map2.get(str1);
				}
			}
		}
		
		if (dem != 0) {
			if (dem1 != 0) {
				diemTongKet = diemTong / tongHeSo;
				String diemTKS = String.format("%.2f", diemTongKet);
				System.out.print("\n┌───────────┬────────────────────────────────────────────────────────────────────────┐");
				System.out.printf("\n│ %42s%40s │", sv.getMaSv(), sv.getHoDem() + " " + sv.getTen());
				System.out.printf("\n│ %42s%40s │", "Ngày sinh:" + " " + sv.getNgaySinh(), "Giới tinh:" + " " +sv.getGioiTinh());
				System.out.printf("\n│ %42s%40s │", " " ,"DTK : " + diemTKS);
				System.out.print("\n├───────────┼────────────────────────────────────────────────────────────────────────┤");
				for (int i = 0; i < listMHDaThi.size(); i++) {
					String tam = listMHDaThi.get(i);
					for (int j = 0; j < listMH.size(); j++) {
						if (tam.equalsIgnoreCase(listMH.get(j).getMaMH())) {
							System.out.printf("\n│ %18s│%-40s │ %-20s │", listMH.get(j).getMaMH(), listMH.get(j).getTenMH(), listMH.get(j).getHeSo());
						}
					}
				}
				System.out.print("\n└───────────┴────────────────────────────────────────────────────────────────────────┘");
			} else {
				diemTongKet = 0;
				System.out.print("\n┌───────────┬────────────────────────────────────────────────────────────────────────┐");
				System.out.printf("\n│ %42s%40s │", sv.getMaSv(), sv.getHoDem() + " " + sv.getTen());
				System.out.printf("\n│ %42s%40s │", "Ngày sinh:" + " " + sv.getNgaySinh(), "Giới tinh:" + " " +sv.getGioiTinh());
				System.out.printf("\n│ %42s%40s │", " " ,"DTK : " + diemTongKet);
				System.out.print("\n├───────────┼────────────────────────────────────────────────────────────────────────┤");
				System.out.print("\n└───────────┴────────────────────────────────────────────────────────────────────────┘");
			}
		}
	}
	
	public static void titleDSSV() {
		System.out.print("\n┌───────────┬──────────────────────────┬────────────────┬────────────────┬───────────┐");
		System.out.printf("\n│ %-10s│ %-25s│ %-15s│ %-15s│ %-10s│", "Mã SV", "Họ đệm", "Tên", "Ngày sinh", "Giới tính");
		System.out.print("\n├───────────┼──────────────────────────┼────────────────┼────────────────┼───────────┤");
	}
	
	public void showDanhSachTimKiemTheoTen(int sotrang, ArrayList<SinhVien> list) {
		if (!list.isEmpty()) {
			int n = list.size() / 30 + 1;
			System.out.println("\n----------------------------------DANH SÁCH SINH VIÊN---------------------------------");	
			titleDSSV();
			int begin, end;
			begin = (sotrang - 1) * 30;
			end = sotrang * 30;
			for (int i = begin; i < end; i++) {
				SinhVien s = list.get(i);
				s.showInfo();
				if (i >= listSV.size() - 1)
					break;
			}
			System.out.print("\n└───────────┴──────────────────────────┴────────────────┴────────────────┴───────────┘");
			System.out.println(
					"\n------------------------------trang " + sotrang + "/" + n + "------------------------------");
		}else {
			System.out.println("Lỗi!");
		}
	}
	
	public void timTheoTenSinhVien() {
		System.out.println("Nhập tên muốn tìm : ");
		String ten = sc.nextLine();
		ArrayList<SinhVien> dsSV = new ArrayList<SinhVien>();
		
		for(int i =0; i < listSV.size(); i++) {
			if(listSV.get(i).getTen().contains(ten)) {
				dsSV.add(listSV.get(i));
			}
		}
		
		int chon;
		int n = dsSV.size() / 30 + 1;
		int tranghientai = 1;
		showDanhSachTimKiemTheoTen(tranghientai, dsSV);
		do {
			System.out.printf("\n\n%-30s %-30s %-30s", "1.Xem trang tiếp theo", "3.Đến trang cuối", "5.Xem trang cụ thể");
			System.out.printf("\n%-30s %-30s %-30s", "2.Trở lại trang trước", "4.Đến trang đầu tiên",
					"0.Trở về menu trước");
			System.out.printf("\n%-30s", "6.Xem chi tiết bảng điểm");
			System.out.println("\n==>");
			chon = sc.nextInt();
			switch (chon) {
			case 1:
				if (tranghientai <= n) {
					tranghientai++;
					showDanhSachTimKiemTheoTen(tranghientai, dsSV);
				} else {
					System.out.println("Đây đã là trang cuối cùng!");
				}
				break;
			case 2:
				if (tranghientai >= 1) {
					tranghientai--;
					showDanhSachTimKiemTheoTen(tranghientai, dsSV);
				} else {
					System.out.println("Đây là trang đầu tiên!");
				}
				break;
			case 3:
				tranghientai = n;
				showDanhSachTimKiemTheoTen(tranghientai, dsSV);
				break;
			case 4:
				tranghientai = 1;
				showDanhSachTimKiemTheoTen(tranghientai, dsSV);
				break;
			case 5:
				System.out.println("Trang muon xem:");
				tranghientai = sc.nextInt();
				if (tranghientai <= n) {
					showDanhSachTimKiemTheoTen(tranghientai, dsSV);
				} else {
					System.out.println("Hiển thị danh sách sinh viên!");
					showDanhSachTimKiemTheoTen(tranghientai, dsSV);
				}
				break;
			case 6:
				showBangDiem();
				break;
			case 0:
				System.out.println("Quay lại");
				break;
			default:
				showDanhSachTimKiemTheoTen(tranghientai, dsSV);
				System.out.println("Chọn không hợp lệ!");
			}
		} while (chon != 0);
	}
	public String addMaSV() {
		int id = ThaoTacFile.count1 + 1;
		String id2 = "SV" + String.format("%05d", id);
		return id2;
	}
}
