package yo.hoo.support.rmi.factory;

import java.rmi.RemoteException;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.remoting.rmi.RmiServiceExporter;

import yo.hoo.support.rmi.stereotype.RmiService;

public class RmiServiceBeanPostProcessorAdapter extends InstantiationAwareBeanPostProcessorAdapter implements
		PriorityOrdered {

	private int order = Ordered.LOWEST_PRECEDENCE - 1;
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Object resultBean = bean;
		if(bean.getClass().isAnnotationPresent(RmiService.class)){
			RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
            rmiServiceExporter.setServiceInterface(bean.getClass().getAnnotation(RmiService.class).serviceInterface());
            rmiServiceExporter.setServiceName(rmiServiceExporter.getServiceInterface().getSimpleName());
            rmiServiceExporter.setRegistryPort(9000);
            rmiServiceExporter.setService(bean);
            try {
                rmiServiceExporter.afterPropertiesSet();
            } catch (RemoteException remoteException) {
                throw new FatalBeanException("Exception initializing RmiServiceExporter", remoteException);
            }
            resultBean = rmiServiceExporter;
		}
		return resultBean;
	}

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }
	
}
