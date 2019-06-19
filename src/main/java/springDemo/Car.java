package springDemo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Getter
@Setter
@ToString
@Component
public class Car implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {
    private String color;
    private String brand;
    private Integer maxSpeed;

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Set BeanFactory...");
        this.beanFactory = beanFactory;

    }

    @Override
    public void setBeanName(String s) {
        System.out.println("Set BeanName : " + s);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Destroy Bean...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("After PropertiesSet...");
    }

    @PostConstruct
    public void myInit() {
        System.out.println("My Init...");
    }

    @PreDestroy
    public void myDestroy() {
        System.out.println("My destroy");
    }
}
