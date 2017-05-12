package service;

import jmx.Power;
import jmx.PowerMonitor;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.management.*;

import java.lang.management.ManagementFactory;

import static spark.Spark.*;

public class RestService {
    final static Logger logger =    Logger.getLogger(RestService.class);

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        Logger.getRootLogger().setLevel(Level.INFO);
        BasicConfigurator.configure();
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        Power power = new Power();
        ObjectName name = new ObjectName("jmx:type=Power");

        PowerMonitor powerMonitor = new PowerMonitor(power);


        mBeanServer.registerMBean(powerMonitor, name);

        get("/potega/:exp", (req, res) -> {
            power.setBase(NumberUtils.toDouble(req.params("exp"), 0.0d));
            logger.info(String.format("get %s",  power.getExponential()));

            return power.power();
        });
    }
}
