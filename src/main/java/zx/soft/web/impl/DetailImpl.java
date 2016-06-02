package zx.soft.web.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import zx.soft.utils.json.JsonUtils;
import zx.soft.web.model.Detail;

public class DetailImpl {

	public Detail getInfo() {
		Detail detail = new Detail();

		detail.setLatitude("36.06");
		detail.setLongitude("103.86");
		detail.setCountrycode("CN");
		detail.setCountry("CN");
		detail.setCity("Lanzhou");
		detail.setOrg("Gansu Lanzhou Area Net Club");
		detail.setLatitude2("47.80");
		detail.setLongitude2("-122.28");
		detail.setCountrycode2("US");
		detail.setCountry2("US");
		detail.setCity2("Lynnwood");
		detail.setType("ipviking.honey");
		detail.setMd5("61.178.13.133");
		detail.setDport("445");
		detail.setSvc("microsoft-ds");
		detail.setZerg("");

		return detail;
	}

	public List readFile() {
		List list = new ArrayList<>();
		URL xmlpath = this.getClass().getClassLoader().getResource("attack-data.log"); 
		String path = xmlpath.toString().substring(5);
		System.out.println(path);
		try {
			BufferedReader read = new BufferedReader(new FileReader(path));
			String json = null;
			while ((json = read.readLine()) != null) {
				if (!json.isEmpty()) {
					Detail detail = JsonUtils.getObject(json, Detail.class);
					System.out.println(JsonUtils.toJsonWithoutPretty(detail));
					list.add(detail);
				}
			}
			read.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static void main(String[] args) {
		DetailImpl detail = new DetailImpl();
		detail.readFile();

	}

}
