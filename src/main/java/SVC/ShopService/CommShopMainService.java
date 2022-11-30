package SVC.ShopService;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.ShopDAO;
import VO.GoodsVO;

public class CommShopMainService {
	public ArrayList<GoodsVO> getGoodsList(int commCode, int page, int limit) {
		ShopDAO shopDAO = ShopDAO.getInstance();
		Connection con = getConnection();
		shopDAO.setConnection(con);
		
		ArrayList<GoodsVO> goodsList = null;
		
		goodsList = shopDAO.getGoodsList(commCode, page, limit);
		
		return goodsList;
	}
	
	public int getListCount(int commCode) {
		ShopDAO shopDAO = ShopDAO.getInstance();
		Connection con = getConnection();
		shopDAO.setConnection(con);

		int listCnt = shopDAO.getGoodsListCnt(commCode);
		
		return listCnt;
	}
}
