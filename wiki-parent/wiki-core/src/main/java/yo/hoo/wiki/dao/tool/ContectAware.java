package yo.hoo.wiki.dao.tool;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class ContectAware {
	private SpringContextHelper helper;
    private SpringContextHelper2 helper2;
    ApplicationContext context;
    public ContectAware() {
        context = new ClassPathXmlApplicationContext("beans.xml");
        helper = (SpringContextHelper) context.getBean("springContextHelper");
        helper2 = (SpringContextHelper2) context
                .getBean("springContextHelper2");
    }
    public static HibernateTemplate getHibernate() {
    	ContectAware t = new ContectAware();
    	HibernateTemplate hibernateTemplate = (HibernateTemplate) t.helper.getBean("hibernateTemplate");
    	return hibernateTemplate;
	}
}
