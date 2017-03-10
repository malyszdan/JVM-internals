import org.junit.Assert;
import org.junit.Test;

public class ConvertorJSONTest {

    @Test
    public void parseFromStudentClass() throws Exception{
        //given
        Student sut = new Student(1, "Daniel", "Malyszczuk");
        String expected = new String("{\"surname\":Malyszczuk,\"id\":\"1\",\"name\":\"Daniel\"}");

        //when
        ConvertorJSON convertorJSON = new ConvertorJSON(sut);
        String actual = convertorJSON.toJSON();

        //then
        System.out.print(actual);
        Assert.assertTrue(expected.equals(actual));
    }
}