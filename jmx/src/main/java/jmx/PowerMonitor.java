package jmx;

import org.apache.commons.lang3.Range;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class PowerMonitor extends NotificationBroadcasterSupport implements PowerMonitorMBean {
    Range<Double> firstRange = Range.between(1.0, 5.0);
    Range<Double> secondRange = Range.between(6.0, 10.0);
    private Power power;
    private int sequenceNumber = 0;

    public PowerMonitor(Power power) {
        this.power = power;
    }

    @Override
    public void setExponential(double exponential) {
        power.setExponential(exponential);
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{AttributeChangeNotification.ATTRIBUTE_CHANGE};

        String name = AttributeChangeNotification.class.getName();

        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name,
                "DESCRIPTION");

        return new MBeanNotificationInfo[]{info};
    }


    @Override
    public void setResult(double result) {
        double oldResult = power.getResult();
        power.setResult(result);
        sendNotification(new AttributeChangeNotification(this,
                sequenceNumber++, System.currentTimeMillis(),
                msgProducer(result), "result", "double", oldResult, result));
    }

    @Override
    public double power() {

        sendNotification(notificationConstructor());
        return power.power();
    }

    private String msgProducer(double result) {
        String msg = "$JAKA wartość potęgi";
        if (firstRange.contains(result)) {
            msg = msg.replace("$JAKA", "Niska");
        } else if (secondRange.contains(result)) {
            msg = msg.replace("$JAKA", "Średnia");
        } else {
            msg = msg.replace("$JAKA", "Wysoka");
        }
        return msg;
    }

    private Notification notificationConstructor() {
        double oldResult = power.getResult();
        power.power();

        double result = power.getResult();

        return new AttributeChangeNotification(this,
                sequenceNumber++, System.currentTimeMillis(),
                msgProducer(result), "result", "double", oldResult, power.getResult());
    }
}
