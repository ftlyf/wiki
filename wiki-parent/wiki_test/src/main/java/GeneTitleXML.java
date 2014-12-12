import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.ObjectInputStream.GetField;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class GeneTitleXML {
	public List<String> imageLinks = new ArrayList<String>();
	public static void main(String[] args) throws Exception{
		GeneTitleXML g = new GeneTitleXML();
		//①建立DocumentBuilderFactory，用于取得DocumentBuilder  
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
          
        //②通过DocumentBuilderFactory取得DocumentBuilder  
        DocumentBuilder builder = null;  
        builder = factory.newDocumentBuilder();
        Document doc = null;  
		//BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("C://zhwiki-latest-all-titles-in-ns0"),"UTF-8"));  
		//String title = br.readLine();//一次读入一行，直到读入null为文件结束  
		//int i = 0;
		//while(title!=null){ 
		      //title = br.readLine(); //接着读下一行  
		     // System.err.println(i+++":"+title);
		      //if(title!=null&&title.trim()!=""){
		    	  String webaddress = "http://zh.wikipedia.org/w/api.php?action=parse&prop=text&page=橋&format=xml&prop=text|langlinks|categories|links|templates|images|externallinks|sections|revid|displaytitle|iwlinks|properties";
		    	  g.imageLinks.clear();
		    	  URL url = new URL(webaddress);// 实例化URL类。
			        
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
					//templateContent = templateContent.replaceAll("\"", "\\\"");
					doc = builder.parse(new InputSource(new ByteArrayInputStream(templateContent.getBytes())));
					NodeList nl = doc.getElementsByTagName("img");
					for (int i = 0; i < nl.getLength(); i++) {
						Element e = (Element)nl.item(i);
						String imaName = e.getTextContent();
						g.addImageLink(java.net.URLEncoder.encode(imaName,"utf-8"), templateContent);
					}
					for (int i = 0; i < g.imageLinks.size(); i++) {
						System.out.println("第"+(i+1)+"个图片的链接为："+ g.imageLinks.get(i));
					}
					/*
					for (int i = 0; i < nl.getLength(); i++) {
						Element e = (Element)nl.item(i);
						System.out.println("第"+i+"个分类为："+e.getAttribute("line"));
					}
					*/
					
					//doc = builder.parse(templateContent);
					//NodeList nl = doc.getElementsByTagName("s");
					//System.err.println("段落的大小是："+nl.getLength());
					
					//String nameTitle = title;
					//nameTitle = nameTitle.replaceAll("/", "yfxg");
					//nameTitle = nameTitle.replaceAll("\\\\", "zfxg");
					//nameTitle = nameTitle.replaceAll(":", "mh");
					//nameTitle = nameTitle.replaceAll("\\*", "xh");
					//nameTitle = nameTitle.replaceAll("\\?", "wh");
					//nameTitle = nameTitle.replaceAll("\"", "syh");
					//以下特殊符号没有
					//nameTitle = nameTitle.replaceAll("<", "zkh");
					//nameTitle = nameTitle.replaceAll(">", "ykh");
					//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E://titleXML/"+nameTitle+".xml"),"UTF-8"));
					//bw.write(templateContent);
					//bw.close();
		      //}
		//}  
		
		
		/*
		//①建立DocumentBuilderFactory，用于取得DocumentBuilder  
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
          
        //②通过DocumentBuilderFactory取得DocumentBuilder  
        DocumentBuilder builder = null;  
        builder = factory.newDocumentBuilder();
        Document doc = null;  
        
		Long StartTime = System.currentTimeMillis();
        System.out.println("--     欢迎使用飞扬简易网页爬虫程序      --");
        System.out.println("");
        System.out.println("--请输入正确的网址如http：//www.baidu.com--");
        Scanner input = new Scanner(System.in);// 实例化键盘输入类
        
        String webaddress = input.next();// 创建输入对象
        File file = new File("D:" + File.separator + "test.txt");// 实例化文件类对象
        
        Writer outWriter = new FileWriter(file);// 实例化outWriter类
        
        URL url = new URL(webaddress);// 实例化URL类。
        
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
		System.err.println(templateContent);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D://1.xml"),"UTF-8"));
		bw.write(templateContent);
		bw.close();
		doc = builder.parse("E://titleXML/1.xml"); 
		//doc = builder.parse(new InputSource(new ByteArrayInputStream( new String(conn.getInputStream().toString().getBytes("iso8859-1"),"GBK").getBytes() ))); 
		NodeList nl = doc.getElementsByTagName("cl");
		System.err.println("段落的大小是："+nl.getLength());
		/*
		int startIndex = templateContent.indexOf("<sections>");
		int endIndex = templateContent.indexOf("</sections>");
		String sectionStr = templateContent.substring(startIndex+10, endIndex);
		System.err.println(sectionStr);
        List<Integer> secIndex = m.getPara(sectionStr);
        for (int i = 0; i < secIndex.size(); i++) {
			System.out.println(i);
		}
		*/
		/*
        String line = null;
        int i=0;
        String regex = "\\w+@\\w+(\\.\\w+)+";// 声明正则，提取网页前提
        
        Pattern p = Pattern.compile(regex);// 为patttern实例化
        
        outWriter.write("该网页中所包含的的邮箱如下所示:\r\n");
        while ((line = buff.readLine()) != null) {
        	System.err.println(line);
            Matcher m = p.matcher(line);// 进行匹配
            
            while (m.find()) {
                i++;
                outWriter.write(m.group() + ";\r\n");// 将匹配的字符输入到目标文件
            }
        }
        
        Long StopTime = System.currentTimeMillis();
        String UseTime=(StopTime-StartTime)+"";
        outWriter.write("--------------------------------------------------------\r\n");
        outWriter.write("本次爬取页面地址："+webaddress+"\r\n");
        outWriter.write("爬取用时："+UseTime+"毫秒\r\n");
        //outWriter.write("本次共得到邮箱："+i+"条\r\n");
        outWriter.write("****谢谢您的使用****\r\n");
        outWriter.write("--------------------------------------------------------");
        outWriter.close();// 关闭文件输出操作
        System.out.println(" —————————————————————\t");
        System.out.println("|页面爬取成功，请到D盘根目录下查看test文档|\t");
        System.out.println("|                                         |");
        System.out.println("|如需重新爬取，请再次执行程序,谢谢您的使用|\t");
        System.out.println(" —————————————————————\t");
        */
		
	}
	
	//获取有多少个段落
	public List<Integer> getPara(String paStr){
		int index = -1;
		List<Integer> indexList = new ArrayList<Integer>();
		while((index = paStr.indexOf("<sections>", index + 1)) >= 0){
			indexList.add(index);
		}
		return indexList;
	}
	
	public void addImageLink(String imageName,String textContent){
		int startTemp = textContent.indexOf("/wiki/File:"+imageName);
		int start = 0;
		int end = 0;
		if(startTemp > 0){
			start = textContent.indexOf("upload.wikimedia.org", startTemp);
			end = textContent.indexOf("&quot;", start);
			
		}else{
			startTemp = textContent.indexOf(imageName);
			start = textContent.lastIndexOf("upload.wikimedia.org", startTemp);
			end = textContent.indexOf("&quot;", start);
		}
		imageLinks.add("http://"+textContent.substring(start, end));
	}
}
