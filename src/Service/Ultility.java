package Service;

import java.util.ArrayList;

import model.BangPhanCong;
import model.LaiXe;

public class Ultility {
	public ArrayList<BangPhanCong> updateBDM(ArrayList<BangPhanCong> list, BangPhanCong bdm) {
		boolean isUpdated = false;
		for (int i = 0; i < list.size(); i++) {
			BangPhanCong b = list.get(i);
			if (b.getLaiXe().getMaLX() == bdm.getLaiXe().getMaLX()
					&& b.getTuyen().getMaTuyen() == bdm.getTuyen().getMaTuyen()) {
				int temp = b.getSoLuotLai() + bdm.getSoLuotLai();
				bdm.setSoLuotLai(temp);
				list.set(i, bdm);// cập nhật lại đối tượng quản lý lái xe
				isUpdated = true;
				break;
			}
		}
		if (!isUpdated) {
			list.add(bdm);
		}
		return list;
	}

	public ArrayList<BangPhanCong> sortByDriverName(ArrayList<BangPhanCong> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				BangPhanCong bpc = list.get(i);
				BangPhanCong bbpc = list.get(j);
				if (bpc.getLaiXe().getHoTen().compareTo(bbpc.getLaiXe().getHoTen()) > 0) {
					list.set(i, bbpc);
					list.set(j, bpc);
				}
			}
		}
		return list;
	}

	public ArrayList<BangPhanCong> sortByNumBer(ArrayList<BangPhanCong> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				BangPhanCong bpc = list.get(i);
				BangPhanCong bbpc = list.get(j);
				if (dem(list, bpc) < dem(list, bbpc)) {
					list.set(i, bbpc);
					list.set(j, bpc);
				}
			}
		}

		return list;
	}

	public int dem(ArrayList<BangPhanCong> list, BangPhanCong bpc) {
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			BangPhanCong b = list.get(i);

			if (b.getLaiXe().getMaLX() == bpc.getLaiXe().getMaLX() && b.getTuyen() != bpc.getTuyen()) {
				count++;
			}
		}
		return count;
	}

	public int tongKhoangCach(ArrayList<BangPhanCong> list, LaiXe x) {
		int dem = 0;
		for(int i=0;i<list.size();i++) {
			BangPhanCong b = list.get(i);
			if(b.getLaiXe().getMaLX() ==x.getMaLX()) {
				dem += b.getTuyen().getKhoangCach();
			}
		}
		return dem;
	}
}
