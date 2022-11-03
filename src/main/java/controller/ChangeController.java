package controller;

import java.util.ArrayList;

import bean.RegisterBean;
import check.DataCheck;
import db.DbConnection;

public class ChangeController {
	static public RegisterBean setRegisterBeanData(RegisterBean registerBean, String date, String height, String weight,
			String temperature) {
		// 入力値チェックエラー格納用
		ArrayList<String> errorList = new ArrayList<>();
		
		// 入力値チェック
		errorList = DataCheck.RegisterData(date, height, weight, temperature);
		
		if (errorList.size() != 0) {
			// Beanに格納
			registerBean.setErrorList(errorList);
			
			return registerBean;
		}
				
		// 入力情報の変換
		float fHeight = Float.parseFloat(height);
		float fWeight = Float.parseFloat(weight);
		float fTemperature = Float.parseFloat(temperature);
		
		// Beanに格納
		registerBean.setDate(date);
		registerBean.setHeight(fHeight);
		registerBean.setWeight(fWeight);
		registerBean.setTemperature(fTemperature);
		
		return registerBean;
	}
	
	static public RegisterBean setRegisterBeanData(RegisterBean registerBean, String date, String height, String weight,
			String temperature, String beforeDate) {
		// 入力値チェックエラー格納用
		ArrayList<String> errorList = new ArrayList<>();
		
		// 入力値チェック
		errorList = DataCheck.RegisterData(date, height, weight, temperature);
		
		if (errorList.size() != 0) {
			// Beanに格納
			registerBean.setErrorList(errorList);
			
			return registerBean;
		}
		
		// 入力情報の変換
		float fHeight = Float.parseFloat(height);
		float fWeight = Float.parseFloat(weight);
		float fTemperature = Float.parseFloat(temperature);
		
		// Beanに格納
		registerBean.setDate(date);
		registerBean.setHeight(fHeight);
		registerBean.setWeight(fWeight);
		registerBean.setTemperature(fTemperature);
		
		// DB検索処理
		int searchResultNum = DbConnection.searchData(beforeDate);
		
		// DB検索失敗時
		if (searchResultNum == 0) {
			errorList.add("該当日付の登録はありません");
			
			// Beanに格納
			registerBean.setErrorList(errorList);
			
			return registerBean;
		}
		
		// DBデータ更新処理
		int changeResultNum = DbConnection.changeData(registerBean, beforeDate);
		
		if (changeResultNum == 0) {
			errorList.add("健診結果の更新に失敗しました");
			
			// Beanに格納
			registerBean.setErrorList(errorList);
			
			return registerBean;
		}
		
		return registerBean;
	}
}
