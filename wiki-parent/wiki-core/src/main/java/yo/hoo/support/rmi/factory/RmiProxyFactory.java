package yo.hoo.support.rmi.factory;

import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import yo.hoo.support.rmi.exception.UnFindRmiServiceException;

public final class RmiProxyFactory {

    @SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls) throws UnFindRmiServiceException {
		RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
        try {
			factoryBean.setServiceInterface(cls);
			factoryBean.setServiceUrl("rmi://127.0.0.1:9000/IRmiServer");
			factoryBean.afterPropertiesSet();
			T bean = (T) factoryBean.getObject();
			return bean;
		} catch (Exception e) {
			throw new UnFindRmiServiceException(factoryBean.getServiceUrl());
		}
	}

}
