package DAO;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import VO.GoodsVO;

public class ShopDAO {
	Connection con;
	private static ShopDAO shopDAO;
	
	private ShopDAO() {}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public static ShopDAO getInstance() {
		
		if(shopDAO == null) {
			shopDAO = new ShopDAO();
		}
		
		return shopDAO;
	}
	
	public ArrayList<GoodsVO> getGoodsList(int commCode, int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<GoodsVO> goodsList = new ArrayList<GoodsVO>();
		int startrow = (page-1)*10;
		int endrow = startrow + limit;
		
		try {
			sql = "select * from goods where commCode = ? and rownum >= ? and rownum < ? order by regDate desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commCode);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					GoodsVO goods = new GoodsVO(rs.getInt("commCode"),
												rs.getString("pCode"),
												rs.getString("pName"),
												rs.getString("p_image"),
												rs.getString("description"),
												rs.getInt("price"),
												rs.getInt("stock"),
												rs.getDate("regDate"),
												rs.getDate("updateDate"),
												rs.getString("status")
												);
					
					goodsList.add(goods);
				}while(rs.next());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		return goodsList;
	}
	
	public int getGoodsListCnt(int commCode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int goodsCnt = 0;
		
		try {
			sql = "select count(*) from goods where commCode = ? and status = 'purchasable'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commCode);
			rs = pstmt.executeQuery();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return goodsCnt;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
