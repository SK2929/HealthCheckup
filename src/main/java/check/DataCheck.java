package check;

import java.util.ArrayList;

/*
 * 入力値チェッククラス
 * 戻り値：エラーリスト
 */
public class DataCheck {
	// エラー格納用
	private static ArrayList<String> errorList = new ArrayList<>();
	
	/* 登録画面から入力された値をチェック */
	public static ArrayList<String> RegisterData(String date, String height, String weight, String temperature) {
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
		
		return errorList;
	}
	
	/* 検索画面から入力された値をチェック */
	public static ArrayList<String> SearchData(String date) {
		// date
		if (!checkNull(date)) {
			errorList.add("DateがNullです");
		}
		if (!checkEmpty(date)) {
			errorList.add("Dateが入力されていません");
		}
		return errorList;
	}
	
	private static boolean checkNull(String value) {
		if (value == null) {
			return false;
		}
		return true;
	}
	
	private static boolean checkEmpty(String value) {
		if (value.isEmpty()) {
			return false;
		}
		return true;
	}
	
	private static boolean checkNumber(String value) {
		try {
			Float.parseFloat(value);
			return true;
		} catch (NumberFormatException numberFormatException) {
			return false;
		}
	}
}
