
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private double memory = 0;
    private double result = 0;
    private String currentInput = "0";
    private String operator = ""; 

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }

    public Calculator() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());