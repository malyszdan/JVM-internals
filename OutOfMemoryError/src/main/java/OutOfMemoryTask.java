import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OutOfMemoryTask {
    final List out = new ArrayList<Long[]>();

    public void outOfMemory() {
        while (true) {
            Random random = new Random();
            out.add(new long[random.nextInt(Integer.MAX_VALUE)]);
        }
    }

}