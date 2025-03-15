import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;

import java.util.List;

class CalculatorTest {
    private Calculator calculator;
    private JTextField mockDisplay;
    private JTextArea mockHistoryArea;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        
        
        mockDisplay = mock(JTextField.class);
        mockHistoryArea = mock(JTextArea.class);
        
        
        calculator.display = mockDisplay;
        calculator.historyArea = mockHistoryArea;
    }

    @Test
    void testInputNumber() {
        calculator.inputNumber("5");
        assertEquals("5", calculator.getCurrentInput());
        verify(mockDisplay).setText("5");
    }

    @Test
    void testSetOperator() {
        calculator.inputNumber("10");
        calculator.setOperator("+");
        assertEquals("+", calculator.operator);
        assertTrue(calculator.newInput);
    }

    @Test
    void testAddition() {
        calculator.inputNumber("5");
        calculator.setOperator("+");
        calculator.inputNumber("3");
        int result = calculator.calculate();
        assertEquals(8, result);
        verify(mockDisplay).setText("8");
    }

    @Test
    void testSubtraction() {
        calculator.inputNumber("10");
        calculator.setOperator("-");
        calculator.inputNumber("4");
        int result = calculator.calculate();
        assertEquals(6, result);
        verify(mockDisplay).setText("6");
    }

    @Test
    void testMultiplication() {
        calculator.inputNumber("6");
        calculator.setOperator("*");
        calculator.inputNumber("7");
        int result = calculator.calculate();
        assertEquals(42, result);
        verify(mockDisplay).setText("42");
    }

    @Test
    void testDivision() {
        calculator.inputNumber("20");
        calculator.setOperator("/");
        calculator.inputNumber("4");
        int result = calculator.calculate();
        assertEquals(5, result);
        verify(mockDisplay).setText("5");
    }

    @Test
    void testDivisionByZero() {
        calculator.inputNumber("10");
        calculator.setOperator("/");
        calculator.inputNumber("0");
        calculator.calculate();
        verify(mockDisplay).setText("Error");
    }

    @Test
    void testSquare() {
        calculator.inputNumber("4");
        double result = calculator.square();
        assertEquals(16, result);
        verify(mockHistoryArea).setText(contains("4² = 16"));
    }

    @Test
    void testClearEntry() {
        calculator.inputNumber("9");
        calculator.clearEntry();
        assertEquals("", calculator.getCurrentInput());
        verify(mockDisplay).setText("0");
    }

    @Test
    void testClearAll() {
        calculator.inputNumber("9");
        calculator.setOperator("+");
        calculator.inputNumber("1");
        calculator.calculate();
        calculator.clearAll();
        
        assertEquals("", calculator.getCurrentInput());
        assertEquals("", calculator.operator);
        verify(mockDisplay).setText("0");
        verify(mockHistoryArea).setText("");
    }

    @Test
    void testHistoryTracking() {
        calculator.inputNumber("5");
        calculator.setOperator("+");
        calculator.inputNumber("5");
        calculator.calculate();

        List<String> history = calculator.getHistory();
        assertTrue(history.contains("10.0"));
        verify(mockHistoryArea).setText(contains("10.0"));
    }
}
