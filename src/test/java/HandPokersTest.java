import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandPokersTest {

    static List<Arguments> vsData() {
        return List.of(
                // 题中用例
                Arguments.arguments("2H 3D 5S 9C KD", "2C 3H 4S 8C AH", false),
                Arguments.arguments("2H 4S 4C 2D 4H", "2S 8S AS QS 3S", false),
                Arguments.arguments("2H 3H 5H 9H KH", "2C 3H 4S 5C 6H", true),
                Arguments.arguments("2H 3D 5S 9C KD", "2D 3H 5C 9S KH", null)
                // 其他用例
//                Arguments.arguments("2H 3H 4H 5H 6H", "TC JC QC KC AC", true),
//                Arguments.arguments("3H 2H KH 5H 9H", "4S 2C 5C 6H 3H", true),
//                Arguments.arguments("2H 3D 5S 9C KD", "3H 4S 5C 6H 7D", false)
        );
    }

    @ParameterizedTest
    @MethodSource("vsData")
    public void vs(String black, String white, Boolean expected) {
        HandPokers blackPokers = null;
        HandPokers whitePokers = null;
        try {
            blackPokers = new HandPokers(black);
        } catch (Exception e) {
            assert false : "无法正确解析：" + black;
        }
        try {
            whitePokers = new HandPokers(white);
        } catch (Exception e) {
            assert false : "无法正确解析：" + white;
        }
        assertEquals(expected, blackPokers.vs(whitePokers), black + "\n      VS\n" + white + "\n");
    }

}