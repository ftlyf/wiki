import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class TTT
{

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException
	{
		// 创建HttpClient实例   
	    HttpClient httpclient = new DefaultHttpClient();
	    // 创建Get方法实例   
        HttpGet httpgets = new HttpGet("http://zh.wikipedia.org/w/api.php?action=parse&prop=text&page=(I_Can%27t_Get_No)_Satisfaction&format=json");  
        HttpResponse response = httpclient.execute(httpgets);  
        HttpEntity entity = response.getEntity();  
        if (entity != null) {  
            InputStream instreams = entity.getContent();  
            String str = convertStreamToString(instreams);
            System.out.println(str);
            JSONObject jsonObject = JSONObject.fromObject(str);
            JSONObject parse = JSONObject.fromObject(jsonObject.get("parse"));
			System.out.println(parse.get("title"));
            JSONObject text = JSONObject.fromObject(parse.get("text"));
            System.err.println(text.get("*"));
//			System.out.println(convert(""+text.get("*")));
            // Do not need the rest  
            httpgets.abort();  
        }
	}
	
	private static String convert(String utfString){
		StringBuilder sb = new StringBuilder();
		int i = -1;
		int pos = 0;
		
		while((i=utfString.indexOf("\\u", pos)) != -1){
			sb.append(utfString.substring(pos, i));
			if(i+5 < utfString.length()){
				pos = i+6;
				sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));
			}
		}
		
		return sb.toString();
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