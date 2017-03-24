import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class dateFormat {
    private final static String PATTERN = "yyyy-MM-dd";
    private final static int THREADS = 30;
    private final static int ATTEMPTS = 30;
    static final DateFormat format = new SimpleDateFormat(PATTERN);

    public static void main(String[] args) {
        ArrayList<Date> unsafeResults = new ArrayList<>(ATTEMPTS);
        ArrayList<Date> safeResults = new ArrayList<>(ATTEMPTS);


        ExecutorService pool = Executors.newFixedThreadPool(THREADS);
        for (int i = 0; i < ATTEMPTS; i++) {
            Runnable unsafe = () -> {
                {
                    try {
                        unsafeResults.add(format.parse("2017-03-24"));
                    } catch (Exception e) {
                    }
                }
            };
            Runnable safe = () -> {
                {
                    synchronized (format) {
                        try {
                            safeResults.add(format.parse("2017-03-24"));
                        } catch (Exception e) {
                        }
                    }
                }
            };
            pool.execute(unsafe);
            pool.execute(safe);
        }
        pool.shutdown();

        for (int i = 0; i < unsafeResults.size() - 1; i++) {
            try {
                Writer writer = new FileWriter("result.txt", true);
                writer.write(safeResults.get(i).toString() + " | " + unsafeResults.get(i).toString() + "\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}




