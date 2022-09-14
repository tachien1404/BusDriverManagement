package model;

import java.util.Scanner;

public class Person {
 protected String hoTen;
 protected String diaChi;
 protected String sdt;
public String getHoTen() {
	return hoTen;
}
public void setHoTen(String hoTen) {
	this.hoTen = hoTen;
}
public String getDiaChi() {
	return diaChi;
}
public void setDiaChi(String diaChi) {
	this.diaChi = diaChi;
}
public String getSdt() {
	return sdt;
}
public void setSdt(String sdt) {
	this.sdt = sdt;
}

public Person() {
	super();
}
public Person(String hoTen, String diaChi, String sdt) {
	super();
	this.hoTen = hoTen;
	this.diaChi = diaChi;
	this.sdt = sdt;
}


 }
