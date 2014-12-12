import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class Main {
	private String sectionText;
	private List<String> anchorList = new ArrayList<String>();
	private List<String> sectionContent = new ArrayList<String>();
	private List<String> jsonContentList = new ArrayList<String>();
	private List<String> jsonSectionObject = new ArrayList<String>();
	public static void main(String[] args) throws Exception{
		Main m = new Main();
		System.out.println(m.getSectionJson());
		/*
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("C://test"),"UTF-8"));  
		String title = br.readLine();//一次读入一行，直到读入null为文件结束  
		int i = 1;
		Document doc = null; 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
		DocumentBuilder builder = null;  
        builder = factory.newDocumentBuilder();
		while(title!=null){ 
		      title = br.readLine(); //接着读下一行  
		      System.err.println((i+1)+":"+title);
		      if(title!=null){
		    	  doc = builder.parse("E://titleXML/"+(i++)+".xml"); 
		  		  //doc = builder.parse(new InputSource(new ByteArrayInputStream( new String(conn.getInputStream().toString().getBytes("iso8859-1"),"GBK").getBytes() ))); 
		  		  NodeList nl = doc.getElementsByTagName("s");
		  		  if(nl.getLength()>0){
		  			  Element e = (Element)nl.item(0);
		  			  System.out.println(i+"的第一个元素的line值:"+e.getAttribute("line"));
		  		  }
		  		  System.out.println(i+"段落的大小是："+nl.getLength());
		      }
		}
		*/
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
	
	//获取json对象
	public String getSectionJson() throws Exception{
		Document doc = null; 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
		DocumentBuilder builder = null;  
        builder = factory.newDocumentBuilder();
        doc = builder.parse("E://titleXML/446.xml"); 
		//doc = builder.parse(new InputSource(new ByteArrayInputStream( new String(conn.getInputStream().toString().getBytes("iso8859-1"),"GBK").getBytes() ))); 
		//获取text中的字符串值
        NodeList nl = doc.getElementsByTagName("text");
		Element e = (Element)nl.item(0);
		sectionText = e.getFirstChild().getNodeValue();
		
		//获取段落的anchor值
		nl = doc.getElementsByTagName("s");
		for (int i = 0; i < nl.getLength(); i++) {
			e = (Element)nl.item(i);
			anchorList.add(e.getAttribute("anchor"));
		}
		//打印
		for (int i = 0; i < anchorList.size(); i++) {
			int start = 0;
			int end = 0;
			//开始获取每一段的内容
			if((i+1)<anchorList.size()){
				start = sectionText.indexOf("id=\""+anchorList.get(i)+"\"")-30;
				end = sectionText.indexOf("id=\""+anchorList.get(i+1)+"\"")-30;
			}else{
				start = sectionText.indexOf("id=\""+anchorList.get(i)+"\"")-30;
				end = sectionText.length();
			}
			sectionContent.add(sectionText.substring(start, end));
		}
		
		
		Map<String, Section> map = new HashMap<String, Section>();
		List<Section> ss = new ArrayList<Section>();
		
		//获取每段的json字符串,未加children
		for (int i = 0; i < nl.getLength(); i++) {
			e = (Element)nl.item(i);
			String jsonContent = "{";
			String toclevel = e.getAttribute("toclevel");
			String level = e.getAttribute("level");
			String line = e.getAttribute("line");
			String number = e.getAttribute("number");
			String index = e.getAttribute("index");
			String fromtitle = e.getAttribute("fromtitle");
			String byteoffset = e.getAttribute("byteoffset");
			String anchor = e.getAttribute("anchor");
			String content = sectionContent.get(i);
			
			boolean f = true;
			int numberIndex = number.length();
			String numberTemp = number;
			if(!number.contains(".")){
				Section section = new Section();
				section.setName(line);
				section.setContent(content);
				map.put(number, section);
				ss.add(section);
			}else{
				for (int j = 0; (j < map.size())&&f; j++) {
					while((numberIndex = number.lastIndexOf(".", numberIndex))>0){
						number = number.substring(0, numberIndex);
						if(map.containsKey(number)){
							Section section = map.get(number);
							Section c = new Section();
							c.setName(line);
							c.setContent(content);
							section.addChild(c);
							map.put(numberTemp, c);
							f = false;
							break;
						}else{
							throw new RuntimeException("没有找到父Section");
						}
					}
				}
			}
		}

		System.out.println("======begin=========");
		System.out.println(JacksonUtils.obj2json(ss));
		System.out.println("======end=========");
	 return JacksonUtils.obj2json(ss);
	}
}