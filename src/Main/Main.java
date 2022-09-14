package Main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

import Service.FileService;
import Service.Ultility;
import model.BangPhanCong;
import model.LaiXe;
import model.Tuyen;

public class Main {

	public static void main(String[] args) {
		int choice = 0;
		var driverFileName = "DRIVER.DAT";
		var RouteFileName = "ROUTE.DAT";
		var bdmFileName = "BDM.DAT";
		var ultility = new Ultility();
		var service = new FileService();
		var drivers = new ArrayList<LaiXe>();
		var routes = new ArrayList<Tuyen>();
		var bdms = new ArrayList<BangPhanCong>();
		var isRouteCheck = false;
		var isBookChecked = false;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("______________MENU______________");
			System.out.println("1. Thêm một lái xe mới");
			System.out.println("2. Hiển thị danh sách lái xe");
			System.out.println("3. Thêm một Tuyến mới");
			System.out.println("4. Hiển thị danh sách tuyến mới");
			System.out.println("5. Nhập danh sách phân công cho lái xe");
			System.out.println("6. Sắp xếp danh sách phân công theo họ tên");
			System.out.println("7. Sắp xếp ds lái xe theo số lượng tuyến đảm nhận trong ngày(giảm dần)");
			System.out.println("8. Lập bảng thống kê tổng khoảng cách chạy xe trong ngày của mỗi lái xe");
			System.out.println("0. Thoát khỏi danh sách ứng dụng");

			choice = sc.nextInt();
			sc.nextLine(); // đọc dòng chưa lựa chọn
			switch (choice) {
			case 0:
				System.out.println("___________________________________________");
				System.out.println("Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi");
				break;
			// Chức năng 1
			case 1:
				if (!isBookChecked) {
					checkDriverID(service, driverFileName);
					isBookChecked = true;
				}
				String[] luaChon = { "A", "B", "C", "D", "E", "F" };
				String hoTen, diaChi, sdt, trinhDo;
				int lc;
				System.out.println("Nhập tên lái xe:");
				hoTen = sc.nextLine();
				System.out.println("Nhập địa chỉ của lái xe:");
				diaChi = sc.nextLine();
				do {
					System.out.println("Nhập số điện thoại:");
					sdt = sc.nextLine();

				} while (!sdt.matches(
						"^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$"));
				do {
					System.out.println("Nhập trình độ lái xe:");
					System.out.println("1. A. \n2. B. \n3. C. \n4. D. \n5. E. \n6. F.");
					lc = sc.nextInt();
				} while (lc < 1 || lc > 6);
				trinhDo = luaChon[lc - 1];

				LaiXe laiXe = new LaiXe(0, hoTen, diaChi, sdt, trinhDo);
				service.writeDriverToFile(laiXe, driverFileName);
				break;

			// Chức năng 2
			case 2:
				drivers = service.readDriverFromFile(driverFileName);
				showDriverInfo(drivers);
				break;
			// Chức năng 3
			case 3:
				if (!isRouteCheck) {
					checkRouteID(service, RouteFileName);
					isRouteCheck = true;
				}
				System.out.println("Nhập khoảng cách:");
				double khoangCach = sc.nextDouble();
				System.out.println("Nhập số điểm dừng:");
				int soDiemDung = sc.nextInt();
				Tuyen tuyen = new Tuyen(0, khoangCach, soDiemDung);
				service.writeRouteToFile(tuyen, RouteFileName);
				break;

			// Chức năng 4
			case 4:
				routes = service.readRouteFromFile(RouteFileName);
				showRoutesInfo(routes);
				break;

			// Chức năng 5
			case 5:
				// khởi tạo danh sách
				drivers = service.readDriverFromFile(driverFileName);
				routes = service.readRouteFromFile(RouteFileName);
				bdms = service.readBDMFromFile(bdmFileName);

				int driverID, routeID;
				boolean coData = false;
				do {
					showDriverInfo(drivers);
					System.out.println("________________________________");
					System.out.println("Nhập mã lái xe, nhập 0 để bỏ qua:");
					driverID = sc.nextInt();
					if (driverID == 0) {
						break;
					}

					coData = checkLaiXe(drivers, driverID);
					if (coData) {
						if (getToTal(bdms, driverID) < 15) {
							break;
						} else {
							System.out.println("Lái xe đã được phân công số lượt lái tối đa");
						}

					} else {
						System.out.println("Mã lái xe không tồn tại");
					}

				} while (true);

				// B2:
				boolean coDl = false;
				do {
					showRoutesInfo(routes);
					System.out.println("___________________________________");
					System.out.println("Nhập mã tuyến, nhập 0 để bỏ qua");
					routeID = sc.nextInt();

					if (routeID == 0) {
						break;
					}
					coDl = checkDl(routes, routeID);
					if (coDl) {
						break;
					} else {
						System.out.println("mã tuyến không tồn tại");
					}

				} while (true);

				// b3
				int total = getToTal(bdms, driverID);
				int x;
				do {
					System.out.println("Nhập số lượt lái (đã phân công " + total + "): ");
					x = sc.nextInt();
					if ((x + total) >= 1 && (x + total) <= 15) {
						total += x;
						break;
					} else {
						System.out.println("Nhập quá số lượng qui định, vui lòng nhập lại");
					}
				} while (true);

				// b4
				// LaiXe laiXe, Tuyen tuyen, int soLuotLai, int tong
				LaiXe currentDriver = getDriver(drivers, driverID);
				Tuyen currentRoute = getRoute(routes, routeID);

				BangPhanCong b = new BangPhanCong(currentDriver, currentRoute, x, 0);

				bdms = ultility.updateBDM(bdms, b);// cập nhật danh sách quản lý phân công
				service.updateBDMFile(bdms, bdmFileName);// cập nhật file dl

				// b5
				showBDMInfo(bdms);
				break;

			// Chức năng6
			case 6:
				bdms = service.readBDMFromFile(bdmFileName);
				// cập nhật tổng số lượng mượn
				System.out.println("____________________________________________");
				System.out.println("_____________các lựa chọn sắp xếp___________");
				int y = 0;
				do {
					System.out.println("1.Sắp xếp theo tên lái xe");
					System.out.println("2.Sắp xếp theo số lượng tuyến đảm nhận trong ngày(giảm dần)");
					System.out.println("0.Sắp xếp theo số lượng tuyến đảm nhận trong ngày(giảm dần)");
					y = sc.nextInt();
					if (y == 0) {
						break;
					}
					switch (y) {
					case 1: // sx thep ten
						bdms = ultility.sortByDriverName(bdms);
						showBDMInfo(bdms);
						break;

					case 2:// sapws xeesp theo số lượng tuyến
						bdms = ultility.sortByNumBer(bdms);
						showBDMInfo(bdms);
						break;
					}
				} while (true);
				break;

			// Chức năng 7
			case 7:
				drivers = service.readDriverFromFile(driverFileName);
				bdms = service.readBDMFromFile(bdmFileName);
				System.out.println("Bảng thống kê khoảng cách chạy xe:");
				for (LaiXe temp : drivers) {
					System.out.println(temp.getMaLX() +" đã chạy "+ultility.tongKhoangCach(bdms, temp));
				}
				break;
			}
		} while (choice != 0);

	}

	private static boolean checkLaiXe(ArrayList<LaiXe> drivers, int driverID) {
		for (var r : drivers) {
			if (r.getMaLX() == driverID) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkDl(ArrayList<Tuyen> routes, int routeID) {
		for (var r : routes) {
			if (r.getMaTuyen() == routeID) {
				return true;
			}
		}
		return false;
	}

	private static void checkRouteID(FileService service, String routeFileName) {
		var routes = service.readRouteFromFile(routeFileName);
		if (routes.size() == 0) {
			// do nothing
		} else {
			Tuyen.setId(routes.get(routes.size() - 1).getMaTuyen() + 1);
		}

	}

	private static void checkDriverID(FileService services, String fileName) {
		var listBooks = services.readDriverFromFile(fileName);
		if (listBooks.size() == 0) {
			// do nothing
		} else {
			LaiXe.setId(listBooks.get(listBooks.size() - 1).getMaLX() + 1);
		}

	}

	private static void showDriverInfo(ArrayList<LaiXe> drivers) {
		System.out.println("_______________Thông tin lái xe trong file_______________");
		for (var d : drivers) {
			System.out.println(d);
		}

	}

	private static void showRoutesInfo(ArrayList<Tuyen> routes) {
		System.out.println("_____________Thông tin tuyến trong file_______________");
		for (var r : routes) {
			System.out.println(r);
		}

	}

	private static void showBDMInfo(ArrayList<BangPhanCong> bdms) {
		for (var b : bdms) {
			System.out.println(b);
		}

	}

	private static LaiXe getDriver(ArrayList<LaiXe> drivers, int driverID) {
		for (int i = 0; i < drivers.size(); i++) {
			if (drivers.get(i).getMaLX() == driverID) {
				return drivers.get(i);
			}
		}
		return null;
	}

	private static Tuyen getRoute(ArrayList<Tuyen> routes, int routeID) {
		for (int i = 0; i < routes.size(); i++) {
			if (routes.get(i).getMaTuyen() == routeID) {
				return routes.get(i);
			}
		}
		return null;
	}

	private static int getToTal(ArrayList<BangPhanCong> bdms, int driverID) {
		int count = 0;
		for (var r : bdms) {
			if (r.getLaiXe().getMaLX() == driverID) {
				count += r.getSoLuotLai();
			}
		}
		return count;
	}

}
