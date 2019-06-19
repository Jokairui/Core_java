import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import springDemo.Car;
import springDemo.MyBeanPostProcessor;
import springDemo.MyInstantiationAwareBeanPostProcessor;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDateTime;

public class LocalTest {

    @Test
    public void test() throws UnsupportedEncodingException {
        String a = "啊";
        System.out.println(a.length());
        System.out.println(a.getBytes("UTF-8").length);
    }

    @Test
    public void testSpring() {
        Resource resource = new ClassPathResource("beans.xml");
        BeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader((DefaultListableBeanFactory)beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        ((ConfigurableBeanFactory) beanFactory).addBeanPostProcessor(new MyBeanPostProcessor());
        ((ConfigurableBeanFactory) beanFactory).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        Car car1 = (Car)beanFactory.getBean("car");
        System.out.println(car1.toString());
        car1.setColor("Red");

        Car car2 = (Car)beanFactory.getBean("car");
        System.out.println(car2 == car1);
        ((DefaultListableBeanFactory)beanFactory).destroySingletons();
    }

    @Test
    public void testApplicationContext() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Car car = (Car)applicationContext.getBean("car");
        System.out.println(car);
    }
}
