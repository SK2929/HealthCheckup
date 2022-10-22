

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.RegisterBean;
import controller.SearchController;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/view/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see 検索画面から入力値を取得し、検索結果をJSPに渡す
     */
    public SearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>SearchServlet_doGet_OK</body>");
		out.println(out);
		out.println("</html>");
	}

	/**
	 * @see 検索画面から取得した入力値をsearchControllerへ渡す。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストの文字コード指定
		request.setCharacterEncoding("utf-8");
		
		// 入力情報の取得
		String date = request.getParameter("date");
		
		// Beanの作成
		RegisterBean registerBean = new RegisterBean();
		
		// searchControllerで処理
		registerBean = SearchController.setRegisterBeanData(registerBean, date);
		
		// Beanをリクエストに格納
		request.setAttribute("rb", registerBean);
		
		// registerBeanのerrorListの有無で処理を分岐
		if (registerBean.errorList == null) {
			// search.jspへフォワード
			RequestDispatcher rd = request.getRequestDispatcher("./search.jsp");
			rd.forward(request, response);
		}
		
		if (registerBean.errorList.size() != 0) {
			// registerError.jspへフォワード
			RequestDispatcher rd = request.getRequestDispatcher("./registerError.jsp");
			rd.forward(request, response);
		}
	}
}


