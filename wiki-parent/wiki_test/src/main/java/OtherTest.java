import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;


public class OtherTest {
	public static void main(String[] args){
		OtherTest o = new OtherTest();
		Document doc = null; 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
		DocumentBuilder builder = null;  
        for(int i = 1;i<10;i++){
        	try{
        		builder = factory.newDocumentBuilder();
        		String webaddress = "http://202.38.93.29/showpage.do?status=show&METAID="+i;
        	    URL url = new URL(webaddress);// 实例化URL类。
        	        System.err.println(i);
        	    URLConnection conn = url.openConnection();// 取得链接
        	        
        	        //BufferedReader buff = new BufferedReader(new InputStreamReader(
        	        //                                        conn.getInputStream()));// 取得网页数据
        	    BufferedReader buff=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));  
        	    String templateContent =""; 
        	    String temp = "";
        	    while((temp = buff.readLine()) != null)
        	    {
        			templateContent = templateContent + temp;
        		}
        	    //System.out.println(templateContent);
        	    String cdName = o.getCdName(templateContent);
        	    String cdSize = o.getCdSize(templateContent);
        	    String cdISBN = o.getCdISBN(templateContent);
        	    System.out.println("序号为："+i+"的书信息："+"书名："+cdName +",大小："+cdSize + ",ISBN号："+cdISBN);
        	}catch (Exception e) {
        		continue;
        	}
        	
        }
	}
	
	//获取光盘名：
	public String getCdName(String str){
		int start = str.indexOf("书名:</span>");
		int end = str.indexOf("<br>", start);
		String cdName = str.substring(start+11, end);
		return cdName;
	}
	
	//获取光盘大小：
	public String getCdSize(String str){
		int start = str.indexOf("大小：</span>");
		if(start == -1){
			return "不存在此书";
		}else{
			int end = str.indexOf("<br>", start);
			String cdSize = str.substring(start+10, end);
			return cdSize;
		}
	}
	
	//获取光盘ISBN号：
	public String getCdISBN(String str){
		int start = str.indexOf("ISBN:</span>");
		int end = str.indexOf("<br>", start);
		String cdISBN = str.substring(start+13, end);
		return cdISBN;
	}
}
