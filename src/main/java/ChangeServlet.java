

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.RegisterBean;
import controller.ChangeController;

/**
 * Servlet implementation class ChangeServlet
 */
@WebServlet("/view/ChangeServlet")
public class ChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		registerBean = ChangeController.setRegisterBeanData(registerBean, date, height, weight, temperature);
		
		// Beanをリクエストに格納
		request.setAttribute("rb", registerBean);
		
		// registerBeanのerrorListの有無で処理を分岐
		if (registerBean.errorList == null) {
			// register.jspへフォワード // URLでjspのファイルパスを指定
			RequestDispatcher rd = request.getRequestDispatcher("./change.jsp");
			rd.forward(request, response);
		}
		
		if (registerBean.errorList.size() != 0) {
			// registerError.jspへフォワード
			RequestDispatcher rd = request.getRequestDispatcher("./registerError.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストの文字コード指定
		request.setCharacterEncoding("utf-8");
		
		// 入力情報の取得
		String date = request.getParameter("date");
		String height = request.getParameter("height");
		String weight = request.getParameter("weight");
		String temperature = request.getParameter("temperature");
		String beforeDate = request.getParameter("beforeDate");
		
		// Beanの作成
		RegisterBean registerBean = new RegisterBean();
		
		// registerControllerで処理
		registerBean = ChangeController.setRegisterBeanData(registerBean, date, height, weight, temperature, beforeDate);
		
		// Beanをリクエストに格納
		request.setAttribute("rb", registerBean);
		
		// registerBeanのerrorListの有無で処理を分岐
		if (registerBean.errorList == null) {
			// search.jspへフォワード
			RequestDispatcher rd = request.getRequestDispatcher("./changeResult.jsp");
			rd.forward(request, response);
		}
		
		if (registerBean.errorList.size() != 0) {
			// registerError.jspへフォワード
			RequestDispatcher rd = request.getRequestDispatcher("./registerError.jsp");
			rd.forward(request, response);
		}
	}

}
