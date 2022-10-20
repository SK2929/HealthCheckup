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
	 * @see 登録画面から取得した入力値をチェックする。
	 * 		チェック後、入力データをDB登録処理へ渡す。
	 *      入力データチェック処理やDB登録失敗時は、エラー画面に遷移する。
	 *      エラーがない場合、登録結果を画面に出力する。
	 *      memo:servletの役割を単調化するため、ロジック（61行目～を切り出して、controlle側で実装する）
	 *      メモ：エラーリストを初期化する処理を追加する。
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
		// エラー格納用
		ArrayList<String> errorList = new ArrayList<>();
		
		// 入力値チェック
		errorList = DataCheck.RegisterData(date, height, weight, temperature);
		
		// Beanの作成
		RegisterBean registerBean = new RegisterBean();
		
		if (errorList.size() == 0) {
			// 入力情報の変換
			float fHeight = Float.parseFloat(height);
			float fWeight = Float.parseFloat(weight);
			float fTemperature = Float.parseFloat(temperature);
			
			// Beanに格納
			registerBean.setDate(date);
			registerBean.setHeight(fHeight);
			registerBean.setWeight(fWeight);
			registerBean.setTemperature(fTemperature);
			// response.setContentType("text/html;charset=utf-8"); // ここの設定値がない場合の挙動確認
			
			// Beanをリクエストに格納
			request.setAttribute("rb", registerBean);
			
			// DBデータ登録処理
			int insertResultnum = DbConnection.register(date, fHeight, fWeight, fTemperature);
			
			if (insertResultnum == 1) {
				// register.jspへフォワード // URLでjspのファイルパスを指定
				RequestDispatcher rd = request.getRequestDispatcher("./register.jsp");
				rd.forward(request, response);
			} else {
				errorList.add("データ登録に失敗しました");
			}
		}
		
		if (errorList.size() != 0) {
			// Beanに格納
			registerBean.setErrorList(errorList);
			
			// Beanをリクエストに格納
			request.setAttribute("rb", registerBean);
			
			// registerError.jspへフォワード
			RequestDispatcher rd = request.getRequestDispatcher("./registerError.jsp");
			rd.forward(request, response);
		}
		
		// コンソール確認用
		System.out.println("エラー数：" + errorList.size());
		for (String str: errorList) {
			System.out.println(str);
		}
	}
}
