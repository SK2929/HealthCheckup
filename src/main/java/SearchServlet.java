

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.RegisterBean;
import check.DataCheck;
import db.DbConnection;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/view/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストの文字コード指定
		request.setCharacterEncoding("utf-8");
		
		// 入力情報の取得
		String date = request.getParameter("date");
		// エラー格納用
		ArrayList<String> errorList= new ArrayList<>();
		
		// 入力値チェック
		errorList = DataCheck.SearchData(date);

		// Beanの作成
		RegisterBean rb = new RegisterBean();
		
		if (errorList.size() == 0) {
			// Beanに格納
			rb.setDate(date);
			
			// Beanをリクエストに格納
			request.setAttribute("rb", rb);
			
			// DBデータ登録処理
			DbConnection.searchData(date);
			
			// register.jspへフォワード // URLでjspのファイルパスを指定
			RequestDispatcher rd = request.getRequestDispatcher("./register.jsp");
			rd.forward(request, response);
		}
		
		if (errorList.size() != 0) {
			// Beanに格納
			rb.setErrorList(errorList);
			
			// Beanをリクエストに格納
			request.setAttribute("rb", rb);
			
			// registerError.jspへフォワード
			RequestDispatcher rd = request.getRequestDispatcher("./registerError.jsp");
			rd.forward(request, response);
		}
	}
}
