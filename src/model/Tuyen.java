package model;

public class Tuyen {
	private static int AUTO_ID = 100;
	private int maTuyen;
	private double khoangCach;
	private int soDiemDung;

	public Tuyen() {
		super();
	}
	public Tuyen(int maTuyen) {
		this.maTuyen = maTuyen;
	}



	public Tuyen(int maTuyen, double khoangCach, int soDiemDung) {
		if (maTuyen == 0) {
			this.maTuyen = AUTO_ID++;
		} else {
			this.maTuyen = maTuyen;
		}
		this.khoangCach = khoangCach;
		this.soDiemDung = soDiemDung;
	}

	

	public int getMaTuyen() {
		return maTuyen;
	}

	public void setMaTuyen(int maTuyen) {
		this.maTuyen = maTuyen;
	}

	public double getKhoangCach() {
		return khoangCach;
	}

	public void setKhoangCach(double khoangCach) {
		this.khoangCach = khoangCach;
	}

	public int getSoDiemDung() {
		return soDiemDung;
	}

	public void setSoDiemDung(int soDiemDung) {
		this.soDiemDung = soDiemDung;
	}

	@Override
	public String toString() {
		return "entity.Tuyen{" + "maTuyen=" + maTuyen + ", khoanhCach='" + khoangCach + '\'' + ", soDiemDung='"
				+ soDiemDung + '\'' + '}';
	}
	public static int getId() {
		return AUTO_ID;
	}

	public static void setId(int AUTO_ID) {
		Tuyen.AUTO_ID = AUTO_ID;
	}

}
