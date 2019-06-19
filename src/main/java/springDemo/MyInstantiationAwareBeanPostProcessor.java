package springDemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;

@Component
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if("car".equalsIgnoreCase(beanName)) {
            System.out.println("postProcessBeforeInstantiation...");
        }
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if("car".equalsIgnoreCase(beanName)){
            System.out.println("postProcessAfterInstantiation...");
        }
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if("car".equalsIgnoreCase(beanName)) {
            System.out.println("postProcessProperties...");
        }
        return super.postProcessProperties(pvs, bean, beanName);
    }
}
