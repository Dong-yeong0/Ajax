package userInfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@WebServlet("/userInfoSerlvet")
public class UserInfoSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserInfoSerlvet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		UserDAO dao = new UserDAO();
		List<UserVO> list = dao.userList();
		
		JSONArray ary = new JSONArray();
		for(UserVO vo : list) {
			JSONObject obj = new JSONObject();
			obj.put("userId", vo.getUserId());
			obj.put("userName", vo.getUserName());
			obj.put("userPass", vo.getUserPass());
			obj.put("userPhone", vo.getPhone());
			obj.put("userGender", vo.getGender());
			ary.add(obj);
		}
		
		response.getWriter().print(ary);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		


	}

}
