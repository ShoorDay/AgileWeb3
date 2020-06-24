import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandPokersTest {

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

    static List<Arguments> vsData() {
        return List.of(
                // 题中用例
                Arguments.arguments("2H 3D 5S 9C KD", "2C 3H 4S 8C AH", false),
                Arguments.arguments("2H 4S 4C 2D 4H", "2S 8S AS QS 3S", false),
                Arguments.arguments("2H 3H 5H 9H KH", "2C 3H 4S 5C 6H", true),
                Arguments.arguments("2H 3D 5S 9C KD", "2D 3H 5C 9S KH", null),
                // 其他用例
                // 同花顺
                Arguments.arguments("2H 3H 5H 4H 6H", "TC JC QC KC AC", false), // vs 同花顺
                Arguments.arguments("2H 3H 5H 6H 4H", "2C 3C 5C 6C 4C", null),  // vs 同花顺
                Arguments.arguments("2H 3H 5H 6H 4H", "2S 8S AS QS 3S", true),  // vs 同花
                Arguments.arguments("2H 3H 5H 6H 4H", "7C 8C 9H TC JS", true),  // vs 顺子
                Arguments.arguments("2H 3H 5H 6H 4H", "8H 8S 8C 6H 7D", true),  // vs 三条
                Arguments.arguments("2H 3H 5H 6H 4H", "7C 7C KH KC 4S", true),  // vs 两对
                Arguments.arguments("2H 3H 5H 6H 4H", "2S 8C TS KH KS", true),  // vs 对子
                Arguments.arguments("2H 3H 5H 6H 4H", "8H JS KC 9H 7D", true),  // vs 散牌
                // 同花
                Arguments.arguments("7H 2H AH KH QH", "2S 8S AS QS 3S", true),  // vs 同花
                Arguments.arguments("7H 2H AH KH QH", "7S AS KS QS 2S", null),  // vs 同花
                Arguments.arguments("6H 7H 8H TH QH", "7C 8C 9H TC JS", true),  // vs 顺子
                Arguments.arguments("7H 2H AH KH QH", "8H 8S 8C 6H 7C", true),  // vs 三条
                Arguments.arguments("7H 2H AH KH QH", "JC JD KS KC 4S", true),  // vs 两对
                Arguments.arguments("7H 2H AH KH QH", "2S 8C TS KD KS", true),  // vs 对子
                Arguments.arguments("7H 2H AH KH QH", "8H JS KC 9H 7C", true),  // vs 散牌
                // 顺子
                Arguments.arguments("7C 8C 9H TC JS", "QC 8C 9H TD JS", false), // vs 顺子
                Arguments.arguments("7C 8C 9H TC JS", "7C 8C 9H TD JS", null),  // vs 顺子
                Arguments.arguments("7C 8C 9H TC JS", "7H 7S 8C 6H 7D", true),  // vs 三条
                Arguments.arguments("7C 8C 9H TC JS", "JC JD KH 2C KS", true),  // vs 两对
                Arguments.arguments("7C 8C 9H TC JS", "2S 8C TS KH KS", true),  // vs 对子
                Arguments.arguments("7C 8C 9H TC JS", "8H JS KC 9H 7D", true),  // vs 散牌
                // 三条
                Arguments.arguments("JC JD KH JS KS", "8H 8S 8C 6H 7D", true),  // vs 三条
                Arguments.arguments("JC JD KH JS KS", "QS QC 8H QD 3S", false), // vs 三条
                Arguments.arguments("JC JD KH JS KS", "TC TD KD KC 4S", true),  // vs 两对
                Arguments.arguments("JC JD KD JS KS", "2S 8C TS KH KS", true),  // vs 对子
                Arguments.arguments("JC JD KH JS KS", "8H JS KC 9H 7D", true),  // vs 散牌
                // 两对
                Arguments.arguments("JC JS KH QC KS", "TC TC KD KC 4S", true),  // vs 两对
                Arguments.arguments("JC JS AH QC AS", "TC TC KD KC 4S", true),  // vs 两对
                Arguments.arguments("JC JS KD QC KS", "JD JH KH KC QS", null),  // vs 两对
                Arguments.arguments("3C QC KD QC 3S", "2S 8C TS KH KS", true),  // vs 对子
                Arguments.arguments("JC JD KH QC KS", "8H JS KC 9H 7D", true),  // vs 散牌
                // 对子
                Arguments.arguments("JC JS 8H TC 6S", "2S 8C TS KH KS", false), // vs 对子
                Arguments.arguments("JC JS 8H TD 9S", "JS 8C TS JH 7S", true),  // vs 对子
                Arguments.arguments("JC JD 8H TC 6S", "6S 8C TS JH JS", null),  // vs 对子
                Arguments.arguments("TC JC 5H 7D 5S", "8H JS KC 9H 7S", true),  // vs 散牌
                // 散牌
                Arguments.arguments("TC JC 5H 7D 4S", "8H JS KC 9H 7H", false), // vs 散牌
                Arguments.arguments("TC JC 5H 7D KS", "8H JS KC 9H 7H", true),  // vs 散牌
                Arguments.arguments("9C JC 7H 8D KS", "8H JS KC 9H 7D", null)   // vs 散牌
        );
    }

}