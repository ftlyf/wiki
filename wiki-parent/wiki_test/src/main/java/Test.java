import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import testTool.ContectAware;

public class Test {
	public static void main(String[] args) {
		String s = "";
		HibernateTemplate hibernate = ContectAware.getHibernate();
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("C://zhwiki-latest-all-titles-in-ns0"),"UTF-8"));  
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
		    	  t.setDna("dna");
		    	  t.setMappingid(i);
		    	  t.setSummary("概要简介");
		    	  t.setTime(new Date());
		    	  t.setTitle(s);
		    	  hibernate.save(t);
		      }
		      i++;
		      //运行太快看不到效果，可以加隔断时间
		      //Thread.sleep(100);
		}
	}
}
