import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    @Test
    public void testNumberInput() {
        Calculator calculator = new Calculator();
        calculator.inputNumber("5");
        assertEquals("5", calculator.getDisplay(), "Display should show '5'");
        
        calculator.inputNumber("8");
        assertEquals("58", calculator.getDisplay(), "Display should show '58'");
    }
}
