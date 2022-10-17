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
import db.DbConnection;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/view/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// エラー格納用
	ArrayList<String> errorList;
	// 条件分岐用
	int insertResultnum;
	
	/**
	 * @see HttpServlet#HttpServlet()
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
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>doGet_OK</body>");
		out.println(out);
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
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
		errorList = new ArrayList<>();
		
		// 入力値のチェック
		checkRegisterDate(date, height, weight, temperature);
		
		// Beanの作成
		RegisterBean rb = new RegisterBean();
		
		if (errorList.size() == 0) {
			// 入力情報の変換
			float fheight = Float.parseFloat(height);
			float fweight = Float.parseFloat(weight);
			float ftemperature = Float.parseFloat(temperature);
			
			// Beanに格納
			rb.setDate(date);
			rb.setHeight(fheight);
			rb.setWeight(fweight);
			rb.setTemperature(ftemperature);
			// response.setContentType("text/html;charset=utf-8"); // ここの設定値がない場合の挙動確認
			
			// Beanをリクエストに格納
			request.setAttribute("rb", rb);
			
			// DBデータ登録処理
			insertResultnum = DbConnection.register(date, fheight, fweight, ftemperature);
			
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
			rb.setErrorList(errorList);
			
			// Beanをリクエストに格納
			request.setAttribute("rb", rb);
			
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

	private void checkRegisterDate(String date, String height, String weight, String temperature) {
		// date
		if (!checkNull(date)) {
			errorList.add("DateがNullです");
		}
		if (!checkEmpty(date)) {
			errorList.add("Dateが入力されていません");
		}

		// height
		if (!checkNull(height)) {
			errorList.add("HeightがNullです");
		}
		if (!checkEmpty(height)) {
			errorList.add("Heightが入力されていません");
		}
		if (!checkNumber(height)) {
			errorList.add("Heightが数値ではありません");
		}

		// weight
		if (!checkNull(weight)) {
			errorList.add("Weightが値がNullです");
		}
		if (!checkEmpty(weight)) {
			errorList.add("Weightが入力されていません");
		}
		if (!checkNumber(weight)) {
			errorList.add("Weightが数値ではありません");
		}

		// temperature
		if (!checkNull(temperature)) {
			errorList.add("Temperatureが値がNullです");
		}
		if (!checkEmpty(temperature)) {
			errorList.add("Temperatureが入力されていません");
		}
		if (!checkNumber(temperature)) {
			errorList.add("Temperatureが数値ではありません");
		}
	}

	private boolean checkNull(String value) {
		if (value == null) {
			return false;
		}
		return true;
	}

	private boolean checkEmpty(String value) {
		if (value.isEmpty()) {
			return false;
		}
		return true;
	}

	private boolean checkNumber(String value) {
		try {
			Float.parseFloat(value);
			return true;
		} catch (NumberFormatException numberFormatException) {
			return false;
		}
	}

}
