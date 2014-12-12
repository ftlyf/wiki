/** 
 * TestRmi.java 
 * 版权所有(C) 2012  
 * 创建:cuiran 2012-08-08 11:38:06 
 */
package yo.hoo.rmi.client;

import yo.hoo.rmi.IRmiServer;
import yo.hoo.support.rmi.exception.UnFindRmiServiceException;
import yo.hoo.support.rmi.factory.RmiProxyFactory;

/** 
 * TODO 
 * @author cuiran 
 * @version TODO 
 */
public class TestRmi {
	public static void main(String[] arg) {
		System.out.println("rmi客户端开始调用");
		while (true) {
			try {
				RmiProxyFactory.getBean(IRmiServer.class).test();
				System.out.println("Call IRmiServer success!");
			} catch (UnFindRmiServiceException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		System.out.println("rmi客户端调用结束");
	}

}