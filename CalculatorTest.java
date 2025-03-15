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
        assertEquals("8", calculator.getDisplay(), "5 + 3 should be 8");
    }

    @Test
    public void testSubtraction() {
        Calculator calculator = new Calculator();
        calculator.inputNumber("10");
        calculator.setOperator("-");
        calculator.inputNumber("4");
        calculator.calculate();
        assertEquals("6", calculator.getDisplay(), "10 - 4 should be 6");
    }

    @Test
    public void testMultiplication() {
        Calculator calculator = new Calculator();
        calculator.inputNumber("6");
        calculator.setOperator("*");
        calculator.inputNumber("7");
        calculator.calculate();
        assertEquals("42", calculator.getDisplay(), "6 * 7 should be 42");
    }

    @Test
    public void testDivision() {
        Calculator calculator = new Calculator();
        calculator.inputNumber("20");
        calculator.setOperator("/");
        calculator.inputNumber("5");
        calculator.calculate();
        assertEquals("4", calculator.getDisplay(), "20 / 5 should be 4");
    }
}
