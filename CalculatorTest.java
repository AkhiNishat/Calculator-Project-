import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {
    private Calculator calculator;

    @BeforeAll
    static void initAll() {
        System.out.println("Starting Calculator Tests...");
    }

    @BeforeEach
    void init() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @CsvSource({
            "5, 3, +, 8",
            "10, 4, -, 6",
            "6, 7, X, 42",
            "20, 5, /, 4"
    })
    void testOperations(double a, double b, String operator, double expected) {
        calculator.performOperation(a, "+");
        assertEquals(expected, calculator.performOperation(b, operator));
    }
    @Test
    void testDivisionByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> calculator.performOperation(0, "/"));
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {2, 4, 9, 16})
    void testSquare(double num) {
        assertEquals(num * num, calculator.applyUnaryOperation(num, "x^2"));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1, 2, 4, 5})
    void testReciprocal(double num) {
        assertEquals(1 / num, calculator.applyUnaryOperation(num, "1/x"), 0.0001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {4, 9, 16, 25})
    void testSquareRoot(double num) {
        assertEquals(Math.sqrt(num), calculator.applyUnaryOperation(num, "√"));
    }

    @Test
    void testSquareRootNegativeNumber() {
        Exception exception = assertThrows(ArithmeticException.class, () -> calculator.applyUnaryOperation(-4, "√"));
        assertEquals("Cannot calculate square root of a negative number", exception.getMessage());
    }

    static Stream<Arguments> complexTestCases() {
        return Stream.of(
                Arguments.of(10, 5, "+", 15),
                Arguments.of(50, 20, "-", 30),
                Arguments.of(3, 3, "X", 9),
                Arguments.of(100, 10, "/", 10)
        );
    }

    @ParameterizedTest
    @MethodSource("complexTestCases")
    void testComplexOperations(double a, double b, String operator, double expected) {
        calculator.performOperation(a, "+");
        assertEquals(expected, calculator.performOperation(b, operator));
    }
    @Test
    void testReset() {
        calculator.performOperation(10, "+");
        calculator.reset();
        assertEquals(0, calculator.getResult());
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test completed.");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("All tests finished.");
    }
}
