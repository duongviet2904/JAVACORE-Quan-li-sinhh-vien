import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SinhVien {
	private String maSv;
	private String hoDem;
	private String ten;
	private String ngaySinh;
	private String gioiTinh;

	
	public String getMaSv() {
		return maSv;
	}

	public void setMaSv(String maSv) {
		this.maSv = maSv;
	}

	public String getHoDem() {
		return hoDem;
	}

	public void setHoDem(String hoDem) {
		this.hoDem = hoDem;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	
	public SinhVien() {
		
	}
	
	public SinhVien(String maSv, String hoDem, String ten, String ngaySinh, String gioiTinh) {
		this.maSv = maSv;
		this.hoDem = hoDem;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;

	}

	public SinhVien(String hoDem, String ten, String ngaySinh, String gioiTinh) {
		this.hoDem = hoDem;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
	}
	
	public void showInfo() {
		System.out.printf("\n│ %-10s│ %-25s│ %-15s│ %-15s│ %-10s│", maSv, hoDem, ten, ngaySinh, gioiTinh);
	}
	
	
	public static String inputHoDem() {
		Scanner sc = new Scanner (System.in);
		String hd;
		do {
			System.out.println("Nhập họ đệm :");
			hd = sc.nextLine();
		}while(testHoDem(hd) == false);
		return hd;
	}
	
	public static String inputTen() {
		Scanner sc = new Scanner (System.in);
		String t;
		do {
			System.out.println("Nhập tên :");
			t = sc.nextLine();
		}while(testTen(t) == false);
		return t;
	}
	
	public static String inputNgaySinh() {
		Scanner sc = new Scanner (System.in);
		String ns;
		do {
			System.out.println("Nhập ngày sinh(dd/MM/yyyy) :");
			ns = sc.nextLine();
			
		}while(testNgaySinh(ns) == false);
		return ns;
	}
	
	public static String inputGioiTinh() {
		Scanner sc = new Scanner (System.in);
		String gt;
		do {
			System.out.println("Nhập giới tính(nam/nu) :");
			gt = sc.nextLine();
			
		}while(testGioiTinh(gt) == false);
		return gt;
	}
	
	public static final String STRING =
            "^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶ" +
            "ẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềếểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợ" +
            "ụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹư\\s]+$";
	
	public static boolean testHoDem(String hoDem) {
		if (hoDem != null && hoDem.matches(STRING) == true) {
			return true;
		}else {
			System.err.println("Lỗi : Họ đệm không hợp lệ!");
			return false;
		}
	}
	
	public static boolean testTen(String ten) {
		if (ten != null && ten.matches(STRING) == true) {
			return true;
		}else {
			System.err.println("Lỗi : Tên không hợp lệ!");
			return false;
		}
	}
	
	public static boolean testNgaySinh(String ngaySinh) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date ns = sdf.parse(ngaySinh);
			return true;
		} catch (ParseException e) {
			System.err.println("Lỗi : Định dạng ngày không hợp lệ(dd/MM/yyy)");
			return false;
		}
	}
	
	public static boolean testGioiTinh(String gioiTinh) {
		if(gioiTinh.equalsIgnoreCase("nam") == true || gioiTinh.equalsIgnoreCase("nu") == true) {
			return true;
		}else {
			System.err.println("Lỗi : Giới tính không hợp lệ(nam/nu)!");
			return false;
		}
	}
	
}
