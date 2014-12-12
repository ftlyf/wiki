package yo.hoo.wiki.dao;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import yo.hoo.wiki.core.Doc;
import yo.hoo.wiki.core.Section;
import yo.hoo.wiki.core.Title;
import yo.hoo.wiki.dao.tool.ContectAware;


public class Test {
	
	//用来存放一个Title的段落内容
	private List<String> sectionContent = new ArrayList<String>();
	private List<String> sectionDna = new ArrayList<String>();
	public static HibernateTemplate hibernate = ContectAware.getHibernate();
	public static void main(String[] args) throws Exception{
		/*
		String s = "";
		String dna = "";
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("C://test"),"UTF-8"));  
		String title = br.readLine();//一次读入一行，直到读入null为文件结束  
		Title t = new Title();
		int i = 804312;
		while(title!=null){ 
		      title = br.readLine(); //接着读下一行  
		      if(title!=null){
		    	  s = java.net.URLEncoder.encode(title,"utf-8");
		    	  t.setAuthorid(i);
		    	  t.setAuthorized_ids(null);
		    	  t.setCid(i);
		    	  dna = getTitleDnaStr(i);
		    	  t.setDna(dna);
		    	  t.setMappingid(i);
		    	  t.setSummary("概要简介");
		    	  t.setTime(new Date());
		    	  t.setTitle(s);
		    	  hibernate.save(t);
		      }
		      i++;
		}
		*/
		List<Title> titleList = hibernate.find("from Title");
		for(int i=0;i<titleList.size();i++){
			setTitleSectionContent(titleList.get(i));
		}
	}
	
	//生成Title的dna字符串，目前暂且把did的值也作为authorid的值，可以写到main中去
	public static String getTitleDnaStr(int i){
		String temp = "m_p"+i + "_d"+i+"::u"+i+"_d"+i;
		return temp;
	}
	
	//获取每个Title的段落内容并添加到数据库
	public static void setTitleSectionContent(Title title) throws Exception{
		String titleName = java.net.URLDecoder.decode(title.getTitle());
		Test t = new Test();
		t.sectionContent.clear();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
		Document doc = null; 
		DocumentBuilder builder = null;  
        builder = factory.newDocumentBuilder();
        String webaddress = "http://zh.wikipedia.org/w/api.php?action=parse&prop=text&page="+titleName+"&format=xml&prop=text|langlinks|categories|links|templates|images|externallinks|sections|revid|displaytitle|iwlinks|properties";
		URL url = new URL(webaddress);// 实例化URL类。
        URLConnection conn = url.openConnection();// 取得链接
        BufferedReader buff=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));  
        String templateContentAll =""; 
        String temp = "";
		while((temp = buff.readLine()) != null)
		{
			templateContentAll = templateContentAll + temp;
		}
		doc = builder.parse(new InputSource(new ByteArrayInputStream(templateContentAll.getBytes())));
		NodeList nl = doc.getElementsByTagName("s");
		//System.out.println(title+"共有"+nl.getLength()+"段内容");
		for(int j=0;j<nl.getLength();j++){
			webaddress = "http://zh.wikipedia.org/w/api.php?action=parse&format=xml&prop=text|sections|images&page="+titleName+"&disabletoc=&disableeditsection=&disablepp=&noimages=&preview=&sectionpreview=&section="+(j+1);
			url = new URL(webaddress);
			conn = url.openConnection();// 取得链接
			buff=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));  
			String templateContent =""; 
			temp = "";
			while((temp = buff.readLine()) != null)
			{
				templateContent = templateContent + temp;
			}
			doc = builder.parse(new InputSource(new ByteArrayInputStream(templateContent.getBytes())));
			NodeList titleNl = doc.getElementsByTagName("text");
			Element e = (Element)titleNl.item(0);
			Section s = new Section();
			String secDna = "m_p"+title.getAuthorid()+"_d"+title.getDid()+"_s"+(j+1)+"::u" +
					""+title.getAuthorid()+"_d"+title.getDid()+"_s"+(j+1);
			Element ee = (Element)nl.item(j);
			String secTitle = ee.getAttribute("line");
			String secContent = e.getTextContent();
			t.sectionContent.add(secContent);
			t.sectionDna.add(secDna);
			s.setContent(secContent);
			s.setDna(secDna);
			s.setTitle(secTitle);
			s.setTime(new Date());
			hibernate.save(s);
			System.err.println(title+"的第"+(j+1)+"段的标题是："+secTitle);
		}
		//调用添加doc
		addDoc(templateContentAll, title, t.sectionDna, t.sectionContent);
	}
	
	//添加doc到数据库
	public static void addDoc(String tt,Title title,List<String> sectionDna,List<String> sectionContent) throws Exception{
		System.out.println("templateContent:"+tt);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
		Document doc = null; 
		DocumentBuilder builder = null;  
        builder = factory.newDocumentBuilder();
		Map<String, SectionTemp> map = new HashMap<String, SectionTemp>();
		List<SectionTemp> ss = new ArrayList<SectionTemp>();
		doc = builder.parse(new InputSource(new ByteArrayInputStream(tt.getBytes())));
		NodeList n = doc.getElementsByTagName("s");
		System.out.println(n.getLength()+"长度");
		for (int i = 0; i < n.getLength(); i++) {
			Element ee = (Element)n.item(i);
			String number = ee.getAttribute("number");
			String index = ee.getAttribute("index");
			String dna = sectionDna.get(i);
			boolean f = true;
			int numberIndex = number.length();
			String numberTemp = number;
			if(!number.contains(".")){
				SectionTemp st = new SectionTemp();
				st.setDna(dna);
				st.setId(index);
				map.put(number, st);
				ss.add(st);
			}else{
				for (int j = 0; (j < map.size())&&f; j++) {
					while((numberIndex = number.lastIndexOf(".", numberIndex))>0){
						number = number.substring(0, numberIndex);
						if(map.containsKey(number)){
							SectionTemp stFather = map.get(number);
							SectionTemp stChild = new SectionTemp();
							stChild.setId(index);
							stChild.setDna(dna);
							stFather.addChild(stChild);
							map.put(numberTemp, stChild);
							f = false;
							break;
						}else{
							throw new RuntimeException("没有找到父Section");
						}
					}
				}
			}
		}
		String docContent = JacksonUtils.obj2json(ss).substring(1, JacksonUtils.obj2json(ss).length()-1);
		Doc d = new Doc();
		d.setDid(title.getDid());
		d.setContent(docContent);
		d.setDna(title.getDna());
		hibernate.save(d);
		System.err.println("success");
	}
	
}
