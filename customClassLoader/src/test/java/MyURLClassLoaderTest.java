import org.hamcrest.CoreMatchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;

import static org.junit.Assert.*;

public class MyURLClassLoaderTest {

    IHello h1, h2;

    @BeforeClass
    public static void setUpService() throws Exception{
        ServiceExample serviceExample = new ServiceExample();
        serviceExample.main(null);
    }

    @AfterClass
    public static void shutDownService() throws Exception{
        Spark.stop();
    }

    @Test
    public void objectShouldBeLoadedByMyClassLoader() throws Exception{

        h2 = new Hello();
        assertThat(h2.getClass().getClassLoader().toString(), CoreMatchers.containsString("AppClassLoader"));

        h1 = HelloFactory.newInstance();
        assertThat(h1.getClass().getClassLoader().toString(), CoreMatchers.containsString(MyURLClassLoader.class.getName()));
    }

    @Test
    public void helloShouldBeLoadedFromService() throws Exception{
        URL[] classLoaderUrls = new URL[]{new URL(ServiceExample.URL)};
        MyURLClassLoader myURLClassLoader = new MyURLClassLoader(classLoaderUrls);

        Class<?> beanClass = myURLClassLoader.loadClass("Hello");
        Constructor<?> constructor = beanClass.getConstructor();
        Object beanObj = constructor.newInstance();

        Method method2 = beanClass.getMethod("sayHello");
        assertThat((String) method2.invoke(beanObj), CoreMatchers.equalTo(" I am loaded from Service"));
    }
}