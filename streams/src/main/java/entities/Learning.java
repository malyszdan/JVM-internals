package entities;

import java.util.Arrays;
import java.util.List;

/**
 * Created by daniel on 27.04.17.
 */
public class Learning {
    public static void main(String[] args) {
        List<Integer> test = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

        for (Integer i : test) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }

        test.stream()
                .filter(integer -> integer % 2 == 0).
                forEach(integer -> System.out.println(integer));


    }
}
