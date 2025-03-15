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
    public void testDivisionByZero() {
        Calculator calculator = new Calculator();
        calculator.inputNumber("10");
        calculator.setOperator("/");
        calculator.inputNumber("0");
        calculator.calculate();
        assertEquals("Error", calculator.getDisplay(), "Division by zero should display Error");
    }

    @Test
    public void testNegativeSquareRoot() {
        Calculator calculator = new Calculator();
        calculator.inputNumber("-9");
        double result = calculator.squareRoot();
        assertEquals(0, result, "Square root of negative number should return 0 (error case)");
    }

    @Test
    public void testHistory() {
        Calculator calculator = new Calculator();
        calculator.inputNumber("5");
        calculator.setOperator("+");
        calculator.inputNumber("3");
        calculator.calculate();
        assertTrue(calculator.getHistory().contains("8"), "History should contain result 8");
    }
}
