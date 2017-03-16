import java.lang.ref.WeakReference;

public class GCperformance {

    public void gc() {
        Object object = new Object();
        WeakReference<Object> ref = new WeakReference<Object>(object);
        object = null;
        while (ref.get() != null) {
            System.gc();
        }
    }

}

