package userInfo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/userInsertServlet")
public class UserInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserInsertServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		MultipartRequest multi = new MultipartRequest(request, // 요청정보
				"c:/tmp", // 저장위치
				5 * 1024 * 1024, // 용량
				"UTF-8", // 인코딩
				new DefaultFileRenamePolicy());
		
		String id = multi.getParameter("id");
		String name = multi.getParameter("name");
		String pass = multi.getParameter("pass");
		String phone = multi.getParameter("phone");
		String gender = multi.getParameter("gender");
		
		System.out.println(id + name + pass + phone + gender);
		
		UserDAO dao = new UserDAO();
		UserVO vo = new UserVO();
		
		vo.setUserId(id);
		vo.setUserName(name);
		vo.setUserPass(pass);
		vo.setPhone(phone);
		vo.setGender(gender);
		
		UserVO uvo = dao.insertUser(vo);
		
		JSONObject obj = new JSONObject();
		obj.put("id", uvo.getUserId());
		obj.put("name", uvo.getUserName());
		obj.put("pass", uvo.getUserPass());
		obj.put("phone", uvo.getPhone());
		obj.put("gender", uvo.getGender());
		
		response.getWriter().print(obj);
	}

}
