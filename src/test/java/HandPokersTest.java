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
                Arguments.arguments("2H 3D 5S 9C KD", "2C 3H 4S 8C AH", false),
                Arguments.arguments("2H 4S 4C 2D 4H", "2S 8S AS QS 3S", false),
                Arguments.arguments("2H 3H 5H 9H KH", "2C 3H 4S 5C 6H", true),
                Arguments.arguments("2H 3D 5S 9C KD", "2D 3H 5C 9S KH", null)
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
            assert false : "不能正确解析：" + black;
        }
        try {
            whitePokers = new HandPokers(white);
        } catch (Exception e) {
            assert false : "不能正确解析：" + white;
        }
        assertEquals(expected, blackPokers.vs(whitePokers), "胜负判断错误");
    }

}