import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GCperformance implements Runnable {

    private int size;
    private boolean randomized;
    private List<Object> objectsArrayList;

    public GCperformance(int size) {
        this.size = size;
        this.randomized = false;
    }

    public GCperformance(int size, boolean randomized) {
        this.size = size;
        this.randomized = randomized;
    }

    public void gc() {
        if (!randomized) {
            this.objectsArrayList = new ArrayList<>(size);
        } else {
            Random random = new Random();
            this.objectsArrayList = new ArrayList<>(random.nextInt(size));
        }
        for (Object object : objectsArrayList) {
            WeakReference<Object> ref = new WeakReference<>(object);
            while (ref.get() != null) {
                System.gc();
            }
        }
    }

    @Override
    public void run() {
        gc();
    }
}

