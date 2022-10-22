import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.RegisterBean;
import controller.RegisterController;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/view/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see 登録画面からの入力データ処理
	 */
	public RegisterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>RegisterServlet_doGet_OK</body>");
		out.println(out);
		out.println("</html>");
	}

	/**
	 * @see 登録画面から取得した入力値をregisterControllerへ渡す。
	 *      入力データチェック処理やDB登録失敗時は、エラー画面に遷移する。
	 *      エラーがない場合、登録結果を画面に出力する。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストの文字コード指定
		request.setCharacterEncoding("utf-8");
		
		// 入力情報の取得
		String date = request.getParameter("date");
		String height = request.getParameter("height");
		String weight = request.getParameter("weight");
		String temperature = request.getParameter("temperature");
		
		// Beanの作成
		RegisterBean registerBean = new RegisterBean();
		
		// registerControllerで処理
		registerBean = RegisterController.setRegisterBeanData(registerBean, date, height, weight, temperature);
		
		// Beanをリクエストに格納
		request.setAttribute("rb", registerBean);
		
		// registerBeanのerrorListの有無で処理を分岐
		if (registerBean.errorList == null) {
			// register.jspへフォワード // URLでjspのファイルパスを指定
			RequestDispatcher rd = request.getRequestDispatcher("./register.jsp");
			rd.forward(request, response);
		}
		
		if (registerBean.errorList.size() != 0) {
			// registerError.jspへフォワード
			RequestDispatcher rd = request.getRequestDispatcher("./registerError.jsp");
			rd.forward(request, response);
		}
	}
}
