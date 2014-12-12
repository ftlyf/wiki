import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class RedirectTest {
	public static void main(String[] args) throws Exception{
		RedirectTest r = new RedirectTest();
		String title = "中國";
		boolean flag = r.isDirect(title);
		System.out.println("标题："+title+"是否重定向？" + flag);
	}
	
	public boolean isDirect(String title) throws Exception{
		boolean f = false;
		GeneTitleXML g = new GeneTitleXML();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
          
        DocumentBuilder builder = null;  
        builder = factory.newDocumentBuilder();
        Document doc = null;  
        String webaddress = "http://zh.wikipedia.org/w/api.php?action=parse&prop=text&page="+title+"&format=xml&prop=text|langlinks|categories|links|templates|images|externallinks|sections|revid|displaytitle|iwlinks|properties";
        URL url = new URL(webaddress);// 实例化URL类。
        URLConnection conn = url.openConnection();// 取得链接
        BufferedReader buff=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));  
        String templateContent =""; 
        String temp = "";
		while((temp = buff.readLine()) != null)
		{
			templateContent = templateContent + temp;
		}
		doc = builder.parse(new InputSource(new ByteArrayInputStream(templateContent.getBytes())));
		NodeList nl = doc.getElementsByTagName("text");
		Element e = (Element)nl.item(0);
		String directStr = e.getTextContent();
		System.out.println(directStr);
		//if(directStr.contains("<div class=\"redirectMsg\">"))
			f = true;
		return f;
	}
}
