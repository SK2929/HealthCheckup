package controller;

import java.util.ArrayList;

import bean.RegisterBean;
import check.DataCheck;
import db.DbConnection;

/*
 * 検索画面の入力値を基に、DB検索を実施
 * registerBeanへの格納は、DBデータ登録処理で行う
 */
public class SearchController {
	static public RegisterBean setRegisterBeanData(RegisterBean registerBean, String date) {
		// 入力値チェックエラー格納用
		ArrayList<String> errorList = new ArrayList<>();
		
		// 入力値チェック
		errorList = DataCheck.SearchData(date);
		
		if (errorList.size() != 0) {
			// Beanに格納
			registerBean.setErrorList(errorList);
			
			return registerBean;
		}
		
		// Beanに格納
		registerBean.setDate(date);
		
		// DBデータ登録処理
		int searchResultNum = DbConnection.searchData(registerBean);
		
		if (searchResultNum == 0) {
			errorList.add(date + "で探しましたが、該当日付の登録はありません");
			
			// Beanに格納
			registerBean.setErrorList(errorList);
			
			return registerBean;
		}
		return registerBean;
	}
}
