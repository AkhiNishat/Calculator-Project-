import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    @Test
    public void testNumberInput() {
        Calculator calculator = new Calculator();
        calculator.inputNumber("5");
        assertEquals("5", calculator.getDisplay());

        calculator.inputNumber("8");
        assertEquals("58", calculator.getDisplay());
    }

    @Test
    public void testAddition() {
        Calculator calculator = new Calculator();
        calculator.inputNumber("5");
        calculator.setOperator("+");
        calculator.inputNumber("3");
        calculator.calculate();
        assertEquals("8", calculator.getDisplay());
    }

    @Test
    public void testSquare() {
        Calculator calculator = new Calculator();
        calculator.inputNumber("4");
        double result = calculator.square();
        assertEquals(16.0, result, "Square of 4 should be 16");
    }

    @Test
    public void testReciprocal() {
        Calculator calculator = new Calculator();
        calculator.inputNumber("5");
        double result = calculator.reciprocal();
        assertEquals(0.2, result, 0.0001, "Reciprocal of 5 should be 0.2");
    }

    @Test
    public void testSquareRoot() {
        Calculator calculator = new Calculator();
        calculator.inputNumber("25");
        double result = calculator.squareRoot();
        assertEquals(5.0, result, "Square root of 25 should be 5");
    }

    @Test
    public void testClearEntry() {
        Calculator calculator = new Calculator();
        calculator.inputNumber("123");
        calculator.clearEntry();
        assertEquals("0", calculator.getDisplay(), "Display should reset to 0");
    }
}
