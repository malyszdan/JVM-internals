import org.junit.Assert;
import org.junit.AssumptionViolatedException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.junit.Assert.*;


public class GCperformanceTest {

    @Rule
    public JUnitStopWatch jUnitStopWatch = new JUnitStopWatch();

    @Test
    public void testGC() throws Exception {
        //given
        GCperformance sut = new GCperformance();

        //when
        sut.gc();
    }

}