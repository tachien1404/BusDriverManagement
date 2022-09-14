package Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import model.BangPhanCong;
import model.LaiXe;
import model.Tuyen;

public class FileService {
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	private PrintWriter printWriter;
	private Scanner scanner;

	public void openFileToWrire(String fileName) {
		try {
			fileWriter = new FileWriter(fileName, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeDriverToFile(LaiXe laiXe, String fileName) {
		openFileToWrire(fileName);
		printWriter.println(laiXe.getMaLX() + "|" + laiXe.getHoTen() + "|" + laiXe.getDiaChi() + "|" + laiXe.getSdt()
				+ "|" + laiXe.getTrinhDo());
		closeFileAfterWrite(fileName);
	}

	public void writeRouteToFile(Tuyen tuyen, String fileName) {
		openFileToWrire(fileName);
		printWriter.println(tuyen.getMaTuyen() + "|" + tuyen.getKhoangCach() + "|" + tuyen.getSoDiemDung());
		closeFileAfterWrite(fileName);
	}

	public void writeBDMToFile(BangPhanCong bangPhanCong, String fileName) {
		openFileToWrire(fileName);
		printWriter.println(bangPhanCong.getLaiXe().getMaLX() + "|" + bangPhanCong.getTuyen().getMaTuyen() + "|"
				+ bangPhanCong.getSoLuotLai());
		closeFileAfterWrite(fileName);
	}

	public void closeFileAfterWrite(String fileName) {
		try {
			printWriter.close();
			bufferedWriter.close();
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openFileToRead(String fileName) {
		try {
			File file = new File(fileName);
			if(!file.exists()) { // nếu file chưa tồn tại
				file.createNewFile();// tạo file mới
			}
			scanner = new Scanner(Paths.get(fileName), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeFileAfterRead(String fileNAme) {
		try {
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<LaiXe> readDriverFromFile(String fileName) {
		openFileToRead(fileName);
		ArrayList<LaiXe> arrLaiXe = new ArrayList<>();
		while (scanner.hasNextLine()) {
			String data = scanner.nextLine();
			LaiXe laixe = createDriverFromData(data);
			arrLaiXe.add(laixe);

		}
		closeFileAfterRead(fileName);
		return arrLaiXe;
	}

	public LaiXe createDriverFromData(String data) {
		String[] datas = data.split("\\|");
		LaiXe laiXe = new LaiXe(Integer.parseInt(datas[0]), datas[1], datas[2], datas[3], datas[4]);
		return laiXe;
	}

	public ArrayList<Tuyen> readRouteFromFile(String fileName){
		openFileToRead(fileName);
		ArrayList<Tuyen> arrTuyen = new ArrayList<Tuyen>();
		while (scanner.hasNextLine()) {
			String data = scanner.nextLine();
		Tuyen tuyen = createRouteFromData(data);
		arrTuyen.add(tuyen);
		}
		closeFileAfterRead(fileName);
		return arrTuyen;
	}

	public Tuyen createRouteFromData(String data) {
		String[] datas = data.split("\\|");
		Tuyen tuyen = new Tuyen(Integer.parseInt(datas[0]),Double.parseDouble(datas[1]),
				Integer.parseInt(datas[2]));
		
		return tuyen;
	}
	
	public ArrayList<BangPhanCong> readBDMFromFile(String fileName){
		var laiXes = readDriverFromFile("DRIVER.DAT");
		var tuyens = readRouteFromFile("ROUTE.DAT");
		openFileToRead(fileName);
		ArrayList<BangPhanCong> arrBDM = new ArrayList<BangPhanCong>();
		while (scanner.hasNextLine()) {
			String data = scanner.nextLine();
		BangPhanCong bangphanCong = createBDMFromData(data,laiXes,tuyens);
		arrBDM.add(bangphanCong);
		}
		closeFileAfterRead(fileName);
		return arrBDM;
	}

	

	public BangPhanCong createBDMFromData(String data,ArrayList<LaiXe> laiXes,
			ArrayList<Tuyen> tuyens) {
		String[] datas = data.split("\\|");
		BangPhanCong bpc = new BangPhanCong(getDriver(laiXes,Integer.parseInt(datas[0])),
				getRoute(tuyens,Integer.parseInt(datas[1])),
				Integer.parseInt(datas[2]),0);	
		return bpc;
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
	
	public void updateBDMFile(ArrayList<BangPhanCong> list, String fileName) {
		// xóa bỏ file cũ
		File file = new File(fileName);
		if(file.exists()) {
			file.delete(); // xóa file
		}
		
		// ghi file mới
		openFileToWrire(fileName);
		for(var bangPhanCong:list) {
			printWriter.println(bangPhanCong.getLaiXe().getMaLX() + "|" + bangPhanCong.getTuyen().getMaTuyen() + "|"
					+ bangPhanCong.getSoLuotLai());
		}
		closeFileAfterWrite(fileName);
	}
	
}
 