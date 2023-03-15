package servlet.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Login
 */

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userNameString = request.getParameter("userName");
		String password = request.getParameter("password");

		User user = User.getUserByUserName(userNameString);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if (user == null) {
			String responce = """
					<html>
						<body>
							<h1>404 no such user</h1>
						</body>
					</html>
						""";
			out.print(responce);
			out.close();
			return;
		}
		if (!user.getPassword().equals(password)) {
			String responce = """
					<html>
						<body>
							<h1>401 invalid credentials</h1>
						</body>
					</html>
						""";
			out.print(responce);
			out.close();
			return;
		}
		Cookie userName = new Cookie("userName", userNameString);
		userName.setMaxAge(30 * 60);
		response.addCookie(userName);
		response.sendRedirect("index.jsp");

	}
}
