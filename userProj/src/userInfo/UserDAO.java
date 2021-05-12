package userInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBcon;

public class UserDAO {
	Connection conn = DBcon.getConnect();
	ResultSet rs = null;
	PreparedStatement psmt = null;
	
	// 리스트 출력
	public List<UserVO> userList(){
		List<UserVO> list = new ArrayList<UserVO>();
		UserVO vo = new UserVO();
		String sql = "select * from user_temp";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo.setUserId(rs.getString("user_id"));
				vo.setUserName(rs.getString("user_name"));
				vo.setUserPass(rs.getString("user_pass"));
				vo.setPhone(rs.getString("user_phone"));
				vo.setGender(rs.getString("user_gender"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
		
	// Insert
	public UserVO insertUser(UserVO vo) {
		conn = DBcon.getConnect();
		String sql = "insert into user_temp values(?,?,?,?,?)";
		int n = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserId());
			psmt.setString(2, vo.getUserName());
			psmt.setString(3, vo.getUserPass());
			psmt.setString(4, vo.getPhone());
			psmt.setString(5, vo.getGender());
			n = psmt.executeUpdate();
			System.out.println(n + "건 정상 기입");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return vo;
	}
	
	public void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (psmt != null)
			try {
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
