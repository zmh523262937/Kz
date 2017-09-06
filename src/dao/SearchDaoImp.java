package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Keyword;
import util.DBUtil;

public class SearchDaoImp implements SearchDao {

	@Override
	public List<Keyword> getList(String keyword) {
		// TODO Auto-generated method stub
		Connection conn =DBUtil.getConnection();
		List<Keyword>list =new ArrayList<>();
		Keyword k = null;
		String sql ="select t1.*,rownum from (select * from t_ajaxsearch where keyword like ?)t1 where rownum<6";
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				k = new Keyword(rs.getString(1), rs.getString(2));
				list.add(k);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
