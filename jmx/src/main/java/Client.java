import jmx.PowerMonitorMBean;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;


/**
 * Created by daniel on 12.05.17.
 */
public class Client {

    public static void main(String[] args) throws Exception {


        JMXServiceURL url =
                new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:9999/jmxrmi");
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);


        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

        ObjectName mxbeanName =
                new ObjectName("jmx:type=Power");

        PowerMonitorMBean monitorMBeanProxy = JMX.newMXBeanProxy(mbsc, mxbeanName, PowerMonitorMBean.class);

        monitorMBeanProxy.setResult(500.0);

        jmxc.close();



    }


}
