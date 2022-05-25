
public class Diem {
	private String maSV;
	private String maMH;
	private float diem;
	
	public String getMaSV() {
		return maSV;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public String getMaMH() {
		return maMH;
	}
	public void setMaMH(String maMH) {
		this.maMH = maMH;
	}
	public float getDiem() {
		return diem;
	}
	public void setDiem(float diem) {
		this.diem = diem;
	}
	public Diem() {
		
	}
	public Diem(String maSV, String maMH, float diem) {
		this.maSV = maSV;
		this.maMH = maMH;
		this.diem = diem;
	}
	public void showInfo() {
		System.out.printf("\n│ %-19s│ %-27s│ %-8.1f│", maSV, maMH, diem);
	}
	
}
