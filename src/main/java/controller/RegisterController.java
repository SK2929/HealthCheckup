package controller;

import java.util.ArrayList;

import bean.RegisterBean;
import check.DataCheck;
import db.DbConnection;

public class RegisterController {

	/*
	 * 登録画面からの入力値をregisterBeanに格納
	 * エラーが存在する場合、registerBeanのerrorListに格納
	 */
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
		
		// DBデータ登録処理
		int insertResultNum = DbConnection.registerData(registerBean);
		
		if (insertResultNum == 0) {
			errorList.add("同じ日付が登録されています。データ登録に失敗しました");
			
			// Beanに格納
			registerBean.setErrorList(errorList);
			
			return registerBean;
		}
		return registerBean;
	}
}
