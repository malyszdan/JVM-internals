import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class GCperformanceTest {
    private final static int TIMES = 50;
    private final static int SIZE = 1000000;
    private static final Logger logger = Logger.getLogger("");
    private static List<Long> resultsOfgcPerformanceWithFixedAllocation = new ArrayList<Long>(TIMES);
    private static List<Long> resultsOfgcPerformanceWithRandomAllocation = new ArrayList<Long>();
    private static List<Long> resultsOfgcPerformanceWithFixedAllocationMultiThread = new ArrayList<Long>(TIMES);
    private static List<Long> resultsOfgcPerformanceWithRandomAllocationMultiThread = new ArrayList<Long>();
    public RepeatRule repeatRule;
    public JUnitStopWatch jUnitStopWatch;

    @Rule
    public TestRule chain = RuleChain.outerRule(jUnitStopWatch = new JUnitStopWatch())
            .around(repeatRule = new RepeatRule());



    private static void resultGenerator(List<Long> results) {
        long min = Collections.min(results);
        long max = Collections.max(results);
        Double average = results.stream().mapToDouble(Long::intValue).average().getAsDouble();
        logger.info(String.format("| %s | %s | %s |", min, max, average));
    }

    @Test
    @Repeat(times = TIMES)
    public void gcPerformanceWithFixedAllocation() throws Exception {
        GCperformance sut = new GCperformance(SIZE);

        sut.gc();
        long time = jUnitStopWatch.runtime(TimeUnit.MILLISECONDS);

        resultsOfgcPerformanceWithFixedAllocation.add(time);

    }

    @Test
    @Repeat(times = TIMES)
    public void gcPerformanceWithRandomAllocation() throws Exception {
        GCperformance sut = new GCperformance(SIZE, true);

        sut.gc();
        long time = jUnitStopWatch.runtime(TimeUnit.MILLISECONDS);

        resultsOfgcPerformanceWithRandomAllocation.add(time);

    }
    @Test
    @Repeat(times = TIMES)
    public void gcPerformanceWithFixedAllocationMultithread() throws Exception {
        GCperformance sut = new GCperformance(SIZE);
        Thread thread = new Thread(sut);

        thread.start();
        long time = jUnitStopWatch.runtime(TimeUnit.MILLISECONDS);

        resultsOfgcPerformanceWithFixedAllocationMultiThread.add(time);

    }

    @Test
    @Repeat(times = TIMES)
    public void gcPerformanceWithRandomAllocationMultithread() throws Exception {
        GCperformance sut = new GCperformance(SIZE, true);
        Thread thread = new Thread(sut);


        thread.start();
        long time = jUnitStopWatch.runtime(TimeUnit.MILLISECONDS);

        resultsOfgcPerformanceWithRandomAllocationMultiThread.add(time);

    }

    @AfterClass
    public static void endUp() throws Exception {
        resultGenerator(resultsOfgcPerformanceWithFixedAllocation);
        resultGenerator(resultsOfgcPerformanceWithRandomAllocation);
        resultGenerator(resultsOfgcPerformanceWithFixedAllocationMultiThread);
        resultGenerator(resultsOfgcPerformanceWithRandomAllocationMultiThread);
    }

}