import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class OutOfMemoryTaskTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void shouldReturnOutOfMemoryError() throws Exception {
        thrown.expect(OutOfMemoryError.class);
        OutOfMemoryTask sut = new OutOfMemoryTask();
        sut.outOfMemory();

    }
}