import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;


public class GCperformanceTest {
    public RepeatRule repeatRule;
    public JUnitStopWatch jUnitStopWatch;


    @Rule
    public TestRule chain = RuleChain.outerRule(jUnitStopWatch = new JUnitStopWatch())
            .around(repeatRule = new RepeatRule());

    @Test
    @Repeat(times = 100)
    public void gcPerformanceWithFixedAllocation() throws Exception {
        //given
        int n = 10000000;
        GCperformance sut = new GCperformance(n);

        //when
        sut.gc();
    }

}