package yo.hoo.wiki.datapost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Import {

	public static void main(String[] args) throws Exception {

		String title = "(I_Can%27t_Get_No)_Satisfaction";
		String url = "http://zh.wikipedia.org/w/api.php?action=parse&prop=text&format=json&page=";

		// 创建HttpClient实例   
		HttpClient httpclient = new DefaultHttpClient();
		// 创建Get方法实例   
		HttpGet httpgets = new HttpGet(url + title);
		HttpResponse response = httpclient.execute(httpgets);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instreams = entity.getContent();
			String str = convertStreamToString(instreams);
			JSONObject jsonObject = JSONObject.fromObject(str);
			JSONObject parse = JSONObject.fromObject(jsonObject.get("parse"));
			System.out.println(parse.get("title"));
			JSONObject text = JSONObject.fromObject(parse.get("text"));
			System.out.println(text.get("*"));
			httpgets.abort();
		}
	}

	private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return (sb.toString());
	}

}
