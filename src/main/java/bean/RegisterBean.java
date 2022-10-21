package bean;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * サーブレットとJSPへのデータ格納用
 * memo:日付(date)について、string型→date型へ変える
 * 使いまわしをするために、日付(date)を切り出し継承させる
 */
public class RegisterBean implements Serializable {
	private String date;
	private float height;
	private float weight;
	private float temperature;
	public ArrayList<String> errorList;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public void setErrorList(ArrayList<String> errorList) {
		this.errorList = errorList;
	}

	public String getErrorList() {
		String eList = "";
		
		for (String str : errorList) {
			eList = eList + str + "<br/>";
		}
		return eList;
	}
}
