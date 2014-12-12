package yo.hoo.rmi.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
 * MainServer.java 
 * 版权所有(C) 2012  
 * 创建:cuiran 2012-08-08 11:44:07 
 */

/** 
 * TODO 
 * @author cuiran 
 * @version TODO 
 */
public class MainServer {

	/** 
	 * TODO 
	 * @param args 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub  
		System.out.println("rmi服务端启动");
		new ClassPathXmlApplicationContext("beans.xml");
		System.out.println("rmi服务端启动完成。。。");
	}

}