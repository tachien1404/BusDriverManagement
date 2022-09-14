package model;

public class LaiXe extends Person {
	public static int AUTO_ID = 10000;
	
	private int maLX ;
	private String trinhDo;
	
	
	public LaiXe() {
		super();
	}
	public LaiXe(int maLX) {
		this.maLX = maLX;
	}

	public LaiXe(int maLX,String hoTen,String diaChi, String sdt, String trinhDo) {
	if(maLX ==0) {
		this.maLX = AUTO_ID++;
		
	}else {
		this.maLX = maLX;
	}
		super.hoTen = hoTen;
		super.diaChi = diaChi;
		super.sdt = sdt;
		this.trinhDo = trinhDo;
	}
	

	public int getMaLX() {
		return maLX;
	}

	public void setMaLX(int maLX) {
		this.maLX = maLX;
	}

	public String getTrinhDo() {
		return trinhDo;
	}

	public void setTrinhDo(String trinhDo) {
		this.trinhDo = trinhDo;
	}
	@Override
	public String toString() {
		   return "entity.LaiXe{" +
	                "maLaiXe=" + maLX +
	                ", họ tên='" + hoTen + '\'' +
	                ", địa chỉ='" + diaChi + '\'' +
	                ", số điện thoại='" + sdt + '\'' +
	                ", trình độ='" + trinhDo  + '\'' +
	                '}';
	    }
	 public static int getId() {
	        return AUTO_ID;
	    }

	    public static void setId(int AUTO_ID) {
	        LaiXe.AUTO_ID = AUTO_ID;
	    }
	
	

	
}
