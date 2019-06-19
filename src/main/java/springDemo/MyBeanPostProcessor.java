package springDemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("car".equalsIgnoreCase(beanName)){
            Car car = (Car)bean;
            if (car.getColor() == null) {
                car.setColor("Black");
                System.out.println("postProcessBeforeInitialization and set color to black");
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if("car".equalsIgnoreCase(beanName)) {
            Car car = (Car)bean;
            if (car.getMaxSpeed() != null && car.getMaxSpeed() >= 200) {
                car.setMaxSpeed(200);
                System.out.println("postProcessAfterInitialization and adjust max speed to 200");
            }
        }
        return null;
    }
}
