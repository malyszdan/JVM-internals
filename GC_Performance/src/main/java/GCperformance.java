import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class GCperformance {

    private int size;

    public GCperformance(int size) {
        this.size = size;
    }

    public void gc() {
        ArrayList<Object> objectsArrayList = new ArrayList<>(size);
        for (Object object : objectsArrayList) {
            WeakReference<Object> ref = new WeakReference<>(object);
            while (ref.get() != null) {
                System.gc();
            }
        }
    }
}

