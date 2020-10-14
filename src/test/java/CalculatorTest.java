
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setup() {
        calculator = new Calculator();
    }

    @Nested
    class firstTest {
        @Test
        @DisplayName("3+2 == 5 ?")
        void testSum() {
            assertEquals(5, calculator.sum(3, 2));
        }
    }

    @Test
    @Disabled
    void disabledTest() {
        calculator.notImpl(3, 2);
    }

    @TestFactory
    Stream<DynamicTest> severalTests() {
        return Stream.generate(Math::random)
                .limit(100)
                .mapToInt(v -> (int) (v * 1000))
                .mapToObj(v -> dynamicTest("Test sqr for value" + v, () ->
                        assertEquals(calculator.sqr(v), v * v)));
    }

    @Test
    void divideByZero() {
        assertThrows(Exception.class, () -> calculator.div(2, 0));
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 12, 24})
    void testAddTen(int a) {
        assertEquals(calculator.addTen(a), a + 10);
    }

    @ParameterizedTest
    @CsvSource({"7,1", "5,2"})
    void testSub(int a, int b) {
        assertEquals(calculator.sub(a, b), a - b);
    }

    @ParameterizedTest
    @MethodSource("getArgs")
    void testMul(int a, int b) {
        assertEquals(calculator.mul(a, b), a * b);
    }

    static Stream<Arguments> getArgs() {
        return Stream.of(Arguments.of(0, 2), Arguments.of(9, 18));
    }

    @AfterEach
    void done() {
        calculator.close();
    }
}
