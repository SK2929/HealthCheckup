package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.RegisterBean;

public class DbConnection {
    // ドライバーのクラス名
    private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
    // JDMC接続先情報
    private static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:●●●/●●●";
    // ユーザー名
    private static final String USER = "●●●";
    // パスワード
    private static final String PASS = "●●●";
    
    // SQL作成
    private static String registerSql = "INSERT INTO health(date, height, weight, temperature) VALUES( ?, ?, ?, ?) ";
    private static String searchSql = "SELECT * FROM health WHERE date = ?";
    
    /*
     * DBデータ登録処理
     * 戻り値1：登録成功、戻り値0：登録失敗
     */
	public static int register(RegisterBean registerBean) {
		
        Connection connection = null;
        int num = 0;
        
        try {
            // データベースに接続する準備。
            // Class.forName()メソッドにJDBCドライバ名を与えJDBCドライバをロード
            Class.forName(POSTGRES_DRIVER);

            // 接続先の情報。引数:「JDMC接続先情報」,「ユーザー名」,「パスワード」
            connection = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS);
            
            // 上記のSQL文を受け取って接続
            PreparedStatement ps = connection.prepareStatement(registerSql);
            
            // データセット
            ps.setString(1, registerBean.getDate());
            ps.setFloat(2, registerBean.getHeight());
            ps.setFloat(3, registerBean.getWeight());
            ps.setFloat(4, registerBean.getTemperature());
            
            // 登録処理を実行
            num = ps.executeUpdate();
            
            System.out.println(num + "件データを登録しました。");
            
            // forName()で例外発生
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

            // getConnection()で例外発生
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (connection != null) {
                    // データベースを切断
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return num;
	}
	
    /*
     * DBデータ検索処理
     * 戻り値:検索結果
     */
	public static void searchData(String date, RegisterBean registerBean) {
		
        Connection connection = null;
        
        try {
            // データベースに接続する準備。
            // Class.forName()メソッドにJDBCドライバ名を与えJDBCドライバをロード
            Class.forName(POSTGRES_DRIVER);

            // 接続先の情報。引数:「JDMC接続先情報」,「ユーザー名」,「パスワード」
            connection = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS);
            
            // 上記のSQL文を受け取って接続
            PreparedStatement ps = connection.prepareStatement(searchSql);
            
            // データセット
            ps.setString(1, date);
            
            // 検索処理を実行
            ResultSet rs = ps.executeQuery();
            
			// 取得したデータを展開
			while(rs.next()){
				// Beanに格納
				registerBean.setDate(rs.getString("date"));
				registerBean.setHeight(rs.getFloat("height"));
				registerBean.setWeight(rs.getFloat("weight"));
				registerBean.setTemperature(rs.getFloat("Temperature"));
				
				// どちらでも可能
				//System.out.println(rs.getFloat("date"));
				//System.out.println(rs.getFloat(4));
			}

            // forName()で例外発生
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

            // getConnection()で例外発生
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (connection != null) {
                    // データベースを切断
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return ;
	}
}
