/** 
 * RmiServerImpl.java 
 * 版权所有(C) 2012  
 * 创建:cuiran 2012-08-08 11:13:24 
 */  
package yo.hoo.rmi.server;  
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import yo.hoo.rmi.CopyOfIRmiServer;
import yo.hoo.support.rmi.stereotype.RmiService;
  
/** 
 * TODO 
 * @author cuiran 
 * @version TODO 
 */  
@RmiService(serviceInterface = CopyOfIRmiServer.class)
public class CopyOfRmiServerImpl implements CopyOfIRmiServer {  
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
  
    /* (non-Javadoc) 
     * @see com.cayden.rmi.IRmiServer#test() 
     */  
    @Override  
    public boolean test() {  
      
        System.out.println("调用了我--服务端 O(∩_∩)O哈！" + hibernateTemplate);  
          
        return true;  
    }  
  
}  