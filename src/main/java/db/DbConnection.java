package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbConnection {
    // ドライバーのクラス名
    private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
    // JDMC接続先情報
    private static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:●●●/●●●";
    // ユーザー名
    private static final String USER = "●●●";
    // パスワード
    private static final String PASS = "●●●";
    
    /*
     * DBデータ登録処理
     * 戻り値1：登録成功、戻り値0：登録失敗
     */
	public static int register(String date, float height, float weight, float temperature) {
		
        Connection connection = null;
        int num = 0;
        
        try {
            // データベースに接続する準備。
            // Class.forName()メソッドにJDBCドライバ名を与えJDBCドライバをロード
            Class.forName(POSTGRES_DRIVER);

            // 接続先の情報。引数:「JDMC接続先情報」,「ユーザー名」,「パスワード」
            connection = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS);
            
            // SQL作成
            String SQL = "INSERT INTO health(date, height, weight, temperature) VALUES( ?, ?, ?, ?) ";
            
            // 上記のSQL文を受け取って接続
            PreparedStatement ps = connection.prepareStatement(SQL);
            
            ps.setString(1, date);
            ps.setFloat(2, height);
            ps.setFloat(3, weight);
            ps.setFloat(4, temperature);
            
            // アップデートを実行
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
}

