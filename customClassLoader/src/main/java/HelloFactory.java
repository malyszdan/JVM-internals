import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;

public class HelloFactory {
    public static IHello newInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        URL[] urls = ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs();

        ClassLoader myCL = new MyURLClassLoader(urls);

        return (IHello) myCL.loadClass("Hello").newInstance();
    }
}

class MyURLClassLoader extends URLClassLoader {


    public MyURLClassLoader(URL[] urls) {
        super(urls);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {

        if (name.startsWith("Hello")) {
            return findClass(name);
        }

        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] bytes = null;
        try {
            bytes = loadClassData(name);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Class<?> result = defineClass(name, bytes, 0, bytes.length);
        return result;

    }

    private byte[] loadClassData(String name) throws IOException, InterruptedException {
        Thread.sleep(5000);
        String url = ServiceExample.URL;

        URL myUrl = new URL(url);
        URLConnection connection = myUrl.openConnection();
        InputStream input = connection.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int data = input.read();

        while (data != -1) {
            buffer.write(data);
            data = input.read();
        }

        input.close();

        byte[] classData = buffer.toByteArray();
        return classData;
    }


}


