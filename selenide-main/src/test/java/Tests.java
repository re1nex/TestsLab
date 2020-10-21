import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class Tests {
    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests().succeededTests();

    @TestFactory
    Stream<DynamicTest> severalTests() {
        return Stream.generate(Math::random)
                .limit(10)
                .mapToInt(v -> (int) (v * 1000))
                .mapToObj(v -> dynamicTest("Test sqr for value" + v, () ->
                        new MailRuSearchPage()
                                .openPage()
                                .search(String.valueOf(v))));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ok", "vk"})
    void searchSizeTest(String request) {
        new MailRuSearchPage()
                .openPage()
                .search(request)
                .checkSize(11);

    }

    @ParameterizedTest
    @DisplayName("Search Test")
    @MethodSource("getArgs")
    void searchTest(String request, String results) {
        new MailRuSearchPage()
                .openPage()
                .search(request)
                .resultEquals(results);

    }

    static Stream<Arguments> getArgs() {
        return Stream.of(Arguments.of("vk", "vk.com"), Arguments.of("ok", "ok.ru"));
    }


}
