package model;

public class BangPhanCong {
private LaiXe laiXe;
private Tuyen tuyen;
private int soLuotLai;


public BangPhanCong() {
	super();
}
public BangPhanCong(LaiXe laiXe, Tuyen tuyen, int soLuotLai, int tong) {
	super();
	this.laiXe = laiXe;
	this.tuyen = tuyen;
	this.soLuotLai = soLuotLai;
}


public LaiXe getLaiXe() {
	return laiXe;
}
public void setLaiXe(LaiXe laiXe) {
	this.laiXe = laiXe;
}
public Tuyen getTuyen() {
	return tuyen;
}
public void setTuyen(Tuyen tuyen) {
	this.tuyen = tuyen;
}
public int getSoLuotLai() {
	return soLuotLai;
}
public void setSoLuotLai(int soLuotLai) {
	this.soLuotLai = soLuotLai;
}

@Override
public String toString() {
	   return "entity.BangPhanCong{" +
                "maLX=" + laiXe.getMaLX() +
                ", maTuyen='" + tuyen.getMaTuyen() + '\'' +
                ", tenLaiXe='" + laiXe.getHoTen() + '\'' +
                ", soLuotLai='" + soLuotLai + '\'' +
                '}';
    }




}
