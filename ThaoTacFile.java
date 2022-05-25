import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ThaoTacFile {
	private ArrayList<SinhVien> listSV ;
	private ArrayList<MonHoc> listMH;
	private ArrayList<Diem> listDiem;
	private FileReader frd = null;
	private BufferedReader bfrd = null;
	private FileWriter fwt = null;
	private BufferedWriter bfwt = null;
	private PrintWriter pwt = null;
	
	public static int count1 = 0;
	public static int count2 = 0;
	
	public ThaoTacFile() {
		listSV = new ArrayList<SinhVien>();
		listMH = new ArrayList<MonHoc>();
		listDiem = new ArrayList<Diem>();
	}
	
	public void ghiFileSV() {
		FileWriter fwt = null;
		PrintWriter pwt = null;
		try {
			fwt = new FileWriter(new File("C:\\data\\sinhvien.txt"));
			pwt = new PrintWriter(fwt);
			for(int i = 0; i < listSV.size(); i++) {
				pwt.printf("\r\n%s;%s;%s;%s;%s", listSV.get(i).getMaSv(), listSV.get(i).getHoDem(), listSV.get(i).getTen(), listSV.get(i).getNgaySinh(), listSV.get(i).getGioiTinh());
				pwt.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fwt.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<SinhVien> docFileSV() {
		try {
			frd = new FileReader(new File("C:\\data\\sinhvien.txt"));
			bfrd = new BufferedReader(frd);
			String line;
			while((line = bfrd.readLine()) != null) {
				if(line.startsWith("S") == false) {
					continue;
				}else {
				String[] tach = line.split(";");
				String maSV = tach[0];
				String hoDem = tach[1];
				String ten = tach[2];
				String ngaySinh = tach[3];
				String gioiTinh = tach[4];
				SinhVien sv = new SinhVien(maSV, hoDem, ten, ngaySinh, gioiTinh);
				listSV.add(sv);
				count1++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listSV;
	}

	public ArrayList<MonHoc> docFileMH() {
		try {
			
			frd = new FileReader(new File("C:\\data\\monhoc.txt"));
			bfrd = new BufferedReader(frd);
			String line;
			while((line = bfrd.readLine()) != null) {
				if(line.startsWith("0") == false) {
					continue;
				}
				String[] tach = line.split(";");
				MonHoc mh = new MonHoc(tach[0], tach[1], Float.parseFloat(tach[2]));
				listMH.add(mh);
				++count2;
				}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				frd.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return listMH;
	}
	
	public void ghiFileMH() {
		try {
			fwt = new FileWriter(new File("C:\\data\\monhoc.txt"));
			pwt = new PrintWriter(fwt);
			String line;
			for(int i = 0; i < listMH.size(); i++) {
				pwt.printf("\r\n%s;%s;%.1f", listMH.get(i).getMaMH(), listMH.get(i).getTenMH(), listMH.get(i).getHeSo());
				pwt.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fwt.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Diem> docFileDiem() {
		FileReader frd = null;
		BufferedReader bfrd = null;
		try {
			frd = new FileReader(new File("C:\\data\\diem.txt"));
			bfrd = new BufferedReader(frd);
			String line;
			while((line = bfrd.readLine()) != null) {
				if(line.startsWith("S") == false) {
					continue;
				}else {
				String[] tach = line.split(";");
				Diem diem = new Diem(tach[0], tach[1], Float.parseFloat(tach[2]));
				listDiem.add(diem);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				frd.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return listDiem;
	}
	public void ghiFileDiem() {
		try {
			fwt = new FileWriter(new File("C:\\data\\diem.txt"));
			pwt = new PrintWriter(fwt);
			String line;
			for(int i = 0; i < listMH.size(); i++) {
				pwt.printf("\r\n%s;%s;%.1f", listDiem.get(i).getMaSV(), listDiem.get(i).getMaMH(), listDiem.get(i).getDiem());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fwt.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
