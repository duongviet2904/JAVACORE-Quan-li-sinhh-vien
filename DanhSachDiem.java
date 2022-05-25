import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DanhSachDiem {
	private List<SinhVien> listSV ;
	private List<MonHoc> listMH;
	private List<Diem> listDiem;
	private ThaoTacFile thaoTac;
	
	Scanner sc = new Scanner(System.in);
	
	public DanhSachDiem() {
		thaoTac = new ThaoTacFile();
		listSV = thaoTac.docFileSV();
		listMH = thaoTac.docFileMH();
		listDiem = thaoTac.docFileDiem();
	}
	
	public void themDiem() {
		System.out.println("Nhập mã sinh viên muốn thêm điểm");
		sc.nextLine();
		String ma = sc.nextLine();
		int dem = 0;
		for(int i = 0; i < listSV.size(); i++) {
			if(ma.equalsIgnoreCase(listSV.get(i).getMaSv()) == true) {
				dem++;
			}
		}
		if(dem == 0) {
			System.err.println("Mã sinh viên không tồn tại");
		}else {
			System.out.println("Nhập mã môn học :");
			String x = sc.nextLine();
			int dem1 = 0;
			for(int j = 0; j < listMH.size(); j++) {
				if(x.equalsIgnoreCase(listMH.get(j).getMaMH()) == true) {
					dem1++;
				}
			}
			if(dem1 == 0) {
				System.out.println("Mã môn học không tồn tại!");
			}else {
				System.out.println("Nhập điểm :");
				Float d = sc.nextFloat();
				Diem diem = new Diem(ma, x, d);
				listDiem.add(diem);
			}
		}
		thaoTac.ghiFileDiem();
		
	}
	public void suaDiem() {
		System.out.println("Nhập mã sinh viên muốn sửa điểm");
		String ma = sc.nextLine();
		int dem = 0;
		int index = 0;
		for(int i = 0; i < listDiem.size(); i++) {
			if(ma.equalsIgnoreCase(listDiem.get(i).getMaSV()) == true) {
				index = i;
				dem++;
			}
		}
		if(dem == 0) {
			System.err.println("Mã sinh viên không hợp lệ!");
		}else {
			System.out.println("Nhập mã môn học :");
			String x = sc.nextLine();
			int dem1 = 0;
			for(int i = 0; i < listDiem.size(); i++) {
				if(ma.equalsIgnoreCase(listDiem.get(i).getMaMH()) == true) {
					dem1++;
				}
			}
			if(dem1 == 0) {
				System.out.println("Mã môn học không hợp lệ!");
			}else {
				System.out.println("Nhập điểm :");
				Float d = sc.nextFloat();
				listDiem.get(index).setDiem(d);
			}
		}
		thaoTac.ghiFileDiem();
	}
	
	public void xoaDiem() {
		System.out.println("Nhập mã sinh viên muốn xóa điểm");
		String tam = sc.nextLine();
		int index = 0;
		int dem = 0;
		for(int i = 0; i < listSV.size(); i++) {
			if(tam.equalsIgnoreCase(listSV.get(i).getMaSv())) {
				dem++;
			}
		}
		if(dem != 0) {
			int dem2 = 0;
			for(int i = 0; i < listDiem.size(); i++) {
				if(tam.equalsIgnoreCase(listDiem.get(i).getMaSV())) {
					dem2++;
				}
			}
			if(dem2 != 0) {
				System.out.println("Nhập mã môn học muốn xóa điểm:");
				String ma = sc.nextLine();
				int dem3 = 0;
				for(int j = 0; j < listMH.size(); j++) {
					if(ma.equalsIgnoreCase(listMH.get(j).getMaMH())) {
						dem3++;
					}
				}	
				if(dem3 != 0) {
					int dem4 = 0;
					for(int z = 0; z < listDiem.size(); z++) {
						if(ma.equalsIgnoreCase(listDiem.get(z).getMaMH())) {
							index = z;
							dem++;
						}
					}
					if(dem4 != 0) {
						listDiem.remove(index);
					}else {
						System.err.println("Mã môn học không hợp lệ!");
					}
				}else {
					System.err.println("Mã môn học không tồn tại");
				}
			}else {
				System.err.println("Mã sinh viên không hợp lệ!");
			}
			}else {
				System.err.println("Mã sinh viên không tồn tại!");
			}	
		thaoTac.ghiFileDiem();
	}
	private void titleDiem() {
		System.out.print("\n┌────────────────────┬────────────────────────────┬───────────┐");
		System.out.printf("\n│ %-19s│ %-27s│ %-10s│", "Mã SV", "Mã MH", "điểm");
		System.out.print("\n├────────────────────┼────────────────────────────┼───────────┤");
	}
	public void menuDiem() {
		int chon;
		do {
			Menu.titleMenuCapNhatBD();
			System.out.println("\n\nChon:");
			chon = sc.nextInt();
			switch (chon) {
			case 1:
				themDiem(); break;
			case 2:
				suaDiem(); break;
			case 3:
				xoaDiem(); break;
			case 0:
				
				break;
			default:
				System.out.println("Chọn không hợp lệ!");
				break;
			}
		} while (chon != 0);
	}
	public void showBangDiemTheoMonHoc() {
		int chon;
		int n = listMH.size() / 20 + 1;
		int tranghientai = showTrangBangDiemMonHoc(1);
		do {
			System.out.println("\n==>:");

			chon = sc.nextInt();
			switch (chon) {
			case 1:
				if (tranghientai <= n) {
					tranghientai++;
					showTrangBangDiemMonHoc(tranghientai);
				} else {

					showTrangBangDiemMonHoc(tranghientai);
					System.out.println("\nĐây đã là trang cuối cùng");
				}
				break;
			case 2:
				if (tranghientai >= 1) {
					tranghientai--;
					showTrangBangDiemMonHoc(tranghientai);
				} else {
					showTrangBangDiemMonHoc(tranghientai);
					System.out.println("\nĐây là trang đầu tiên");
				}
				break;
			case 3:
				tranghientai = n;
				showTrangBangDiemMonHoc(tranghientai);
				break;
			case 4:
				tranghientai = 1;
				showTrangBangDiemMonHoc(tranghientai);
				break;
			case 5:
				System.out.println("Trang muon xem:");
				tranghientai = sc.nextInt();
				if (tranghientai <= n && tranghientai >= 1) {
					showTrangBangDiemMonHoc(tranghientai);
				} else {
					System.out.println("Hiển thị trang bảng điểm môn học!");
					showTrangBangDiemMonHoc(tranghientai);
				}
				break;
			case 0:
				break;
			default:
				System.out.println("Chọn không hợp lệ!");
			}
		} while (chon != 0);
	}
	public void showBangDiemTheoSinhVien() {
		int chon;
		int n = listSV.size() / 15 + 1;
		int tranghientai = showMotTrangBangDiem(1);
		do {
			System.out.println("\nChon:");

			chon = sc.nextInt();
			switch (chon) {
			case 1:
				if (tranghientai <= n) {
					tranghientai++;
					showMotTrangBangDiem(tranghientai);
				} else {

					showMotTrangBangDiem(n);
					System.out.println("\nĐây đã là trang cuối!");
				}
				break;
			case 2:
				if (tranghientai >= 1) {
					tranghientai--;
					showMotTrangBangDiem(tranghientai);
				} else {
					showMotTrangBangDiem(tranghientai);
					System.out.println("\nĐây là trang đầu tiên");
				}
				break;
			case 3:
				tranghientai = n;
				showMotTrangBangDiem(tranghientai);
				break;
			case 4:
				tranghientai = 1;
				showMotTrangBangDiem(tranghientai);
				break;
			case 5:
				System.out.println("Trang muốn xem :");
				tranghientai = sc.nextInt();
				if (tranghientai <= n) {
					showMotTrangBangDiem(tranghientai);
				} else {
					System.out.println("Hiển thị trang bảng điểm!");
					showMotTrangBangDiem(tranghientai);
				}
				break;
			case 0:
				break;
			default:
				System.out.println("Chọn không hợp lệ!");
			}
		} while (chon != 0);
	}

	public int showMotTrangBangDiem(int sotrang) {
		int n = listSV.size() / 15 + 1;
		System.out.println("\n----------------DANH SACH SINH VIEN-----------------");
		System.out.println("-------------------trang " + sotrang + "/" + n + "------------------------");
		int dau, cuoi;
		dau = (sotrang - 1) * 15;
		cuoi = sotrang * 15;
		for (int j = dau; j < cuoi; j++) {
			showBangDiemMotSinhVien(listSV.get(j));
			if (j >= listSV.size() - 1)
				break;
		}

		System.out.println(
				"\n------------------------------trang " + sotrang + "/" + n + "------------------------------");
		System.out.printf("\n\n%-30s %-30s %-30s", "1.Xem trang tiếp theo", "3.Đến trang cuối", "5.Xem trang cụ thể");
		System.out.printf("\n%-30s %-30s %-30s", "2.Trở lại trang trước", "4.Đến trang đầu tiên",
				"0.Trở về menu trước");
		return sotrang;
	}

	public void showBangDiemMotSinhVien(SinhVien sv) {
		
		ArrayList<String> dsMaMH = new ArrayList<String>();
		HashMap<String, Float> map = new HashMap<String, Float>();
		HashMap<String, Float> mapDiem = new HashMap<String, Float>();
		HashMap<String, String> mapMonHoc = new HashMap<String, String>();
		
		float diemTongKet = 0, diemTong = 0;
		
		int d1 = 0;
		float tongHeSo = 0;
		String m = sv.getMaSv();
		for (int i = 0; i < listDiem.size(); i++) {
			if (m.equalsIgnoreCase(listDiem.get(i).getMaSV())) {
				mapDiem.put(listDiem.get(i).getMaMH(), listDiem.get(i).getDiem());
				dsMaMH.add(listDiem.get(i).getMaMH());
				d1++;
			}
		}
		for (int i = 0; i < listMH.size(); i++) {
			map.put(listMH.get(i).getMaMH(), listMH.get(i).getHeSo());
			mapMonHoc.put(listMH.get(i).getMaMH(), listMH.get(i).getTenMH());
		}
		for (String str : map.keySet()) {
			for (String str1 : mapDiem.keySet()) {
				if (str.equals(str1)) {
					diemTong += map.get(str) * mapDiem.get(str1);
					tongHeSo += map.get(str);
				}
			}
		}
		if (d1 != 0) {
			diemTongKet = diemTong / tongHeSo;
			String dtk = String.format("%.2f", diemTongKet);
			System.out.print("\n┌────────────────────────────────────────────────────────────────────────────────────┐");
			System.out.printf("\n│ %-42s%40s │", sv.getMaSv(), sv.getHoDem() + " " + sv.getTen());
			System.out.printf("\n│ %-42s%40s │", "Ngày sinh:" + " " + sv.getNgaySinh(), "Giới tinh:" + " " +sv.getGioiTinh());
			System.out.printf("\n│ %-35s%47s │", "Điểm tổng kết : " , dtk);
			System.out.print("\n├────────────────────────────────────────────────────────────────────────────────────┤");
			for (String str : mapDiem.keySet()) {
				for (String str1 : mapMonHoc.keySet()) {
					if (str.equals(str1)) {
						System.out.printf("\n│ %-18s│%-40s │ %-20s │", str, mapMonHoc.get(str1), mapDiem.get(str));
					}
				}
			}
			System.out.print("\n└────────────────────────────────────────────────────────────────────────────────────┘");
		} else {
			diemTongKet = 0;
			System.out.print("\n┌────────────────────────────────────────────────────────────────────────────────────┐");
			System.out.printf("\n│ %-42s%40s │", sv.getMaSv(), sv.getHoDem() + " " + sv.getTen());
			System.out.printf("\n│ %-42s%40s │", "Ngày sinh:" + " " + sv.getNgaySinh(), "Giới tinh:" + " " +sv.getGioiTinh());
			System.out.printf("\n│ %-35s%47s │", " " ,"Điểm tổng kết : " ,"0.0");
			System.out.print("\n├────────────────────────────────────────────────────────────────────────────────────┤");
			System.out.print("\n└────────────────────────────────────────────────────────────────────────────────────┘");
		}
	}

	

	public int showTrangBangDiemMonHoc(int sotrang) {
			int n = listMH.size() / 20 +1;
			System.out.println("\n----------------DANH SACH SINH VIEN-----------------");
			System.out.println("-------------------trang " + sotrang + "/" + n + "------------------------");
			int begin, end;
			begin = (sotrang - 1) * 20;
			end = sotrang * 20;
			for (int j = begin; j < end; j++) {
				showDiemTheoMotMonHoc(listMH.get(j));
				if (j >= listMH.size() - 1)
					break;
			}

			System.out.println(
					"\n------------------------------trang " + sotrang + "/" + n + "------------------------------");
			System.out.printf("\n\n%-30s %-30s %-30s", "1.Xem trang tiếp theo", "3.Đến trang cuối", "5.Xem trang cụ thể");
			System.out.printf("\n%-30s %-30s %-30s", "2.Trở lại trang trước", "4.Đến trang đầu tiên",
					"0.Trở về menu trước");

			return sotrang;
	}
	
	public void showDiemTheoMotMonHoc(MonHoc mh) {
		ArrayList<String> dsMaMH = new ArrayList<String>();
		HashMap<String, String> hmSinhVien = new HashMap<String, String>();
		HashMap<String, String> hmDiem = new HashMap<String, String>();
		HashMap<String, Float> hmMonHoc = new HashMap<String, Float>();
		ArrayList<DiemTheoMonHoc> dsMHD = new ArrayList<DiemTheoMonHoc>();
		
		float diemTongKet = 0, diemTong = 0;
		int d1 = 0;
		float d2 = 0;
		String m = mh.getMaMH();
		for (int i = 0; i < listDiem.size(); i++) {
			if (m.equalsIgnoreCase(listDiem.get(i).getMaMH())) {
				hmDiem.put(listDiem.get(i).getMaSV(), listDiem.get(i).getMaMH());
				hmMonHoc.put(listDiem.get(i).getMaSV(), listDiem.get(i).getDiem());
				d1++;
			}
		}
		for (int i = 0; i < listSV.size(); i++) {
			String ten = listSV.get(i).getHoDem() + " " + listSV.get(i).getTen();
			hmSinhVien.put(listSV.get(i).getMaSv(), ten);
		}
		for (String str : hmMonHoc.keySet()) {
			for (String str1 : hmDiem.keySet()) {
				if (str.equals(str1)) {
					diemTong += hmMonHoc.get(str);
					d2++;
				}
			}
		}
		if (d1 != 0) {
			diemTongKet = diemTong / d2;
			String diemTKS = String.format("%.1f", diemTongKet);
			System.out.print("\n┌────────────────────────────────────────────────────────────────────────────────────┐");
			System.out.printf("\n│ %-42s%40s │", mh.getMaMH(), mh.getTenMH());
			System.out.printf("\n│ %-35s%47s │", " " ,"Điểm trung bình : " + diemTKS);
			System.out.print("\n├────────────────────────────────────────────────────────────────────────────────────┤");
			for (String str1 : hmSinhVien.keySet()) {
				for (String str : hmMonHoc.keySet()) {
					if (str.equals(str1)) {
						DiemTheoMonHoc mhd = new DiemTheoMonHoc(str, hmSinhVien.get(str), hmMonHoc.get(str1));
						dsMHD.add(mhd);
					}
				}
			}
			Collections.sort(dsMHD, (s1,s2)->s1.getMaSV().compareTo(s2.getMaSV()));
			
			for (int i = 0; i < 10; i++) {
				System.out.printf("\n│ %-18s│%-40s │ %-20s │", dsMHD.get(i).getMaSV(), dsMHD.get(i).getTenSV(),dsMHD.get(i).getDiem());

			}
			System.out.print("\n│                                  còn " + (dsMHD.size() - 10) + " sinh viên nữa                             │");
			System.out.print("\n└────────────────────────────────────────────────────────────────────────────────────┘");
		} else {
			diemTongKet = 0;
			System.out.print("\n┌────────────────────────────────────────────────────────────────────────────────────┐");
			System.out.printf("\n│ %-42s%40s │", mh.getMaMH(), mh.getTenMH());
			System.out.printf("\n│ %-35s%47s │", " " ,"Điểm trung bình : " + diemTongKet);
			System.out.print("\n├────────────────────────────────────────────────────────────────────────────────────┤");
			System.out.print("\n└────────────────────────────────────────────────────────────────────────────────────┘");
		}
	}
}
	
