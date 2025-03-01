import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private double memory = 0;
    private double result = 0;
    private String currentInput = "0";
    private String operator = "";
    private final List<String> history = new ArrayList<>();
    private JTextArea historyArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }

    public Calculator() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JLabel standardLabel = new JLabel("Standard", SwingConstants.LEFT);
        standardLabel.setOpaque(true);
        standardLabel.setBackground(Color.WHITE);
        standardLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(standardLabel, BorderLayout.WEST);
        
        JTextField display = new JTextField("0");
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        topPanel.add(display, BorderLayout.SOUTH);
        
        frame.add(topPanel, BorderLayout.NORTH);
        
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(6, 4, 5, 5));
        String[] buttons = {
            "MC", "MR", "M+", "M-", "MS", "M▼",
            "%", "CE", "C", "×", "1/x", "x^2", "√", "/",
            "7", "8", "9", "X",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "±", "0", ".", "="
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setFocusPainted(false);
            button.addActionListener(new ButtonClickListener(display));
            buttonPanel.add(button);
        }
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane historyPane = new JScrollPane(historyArea);
        historyPane.setPreferredSize(new Dimension(150, 0));
        
        JPanel historyPanel = new JPanel(new BorderLayout());
        JLabel historyLabel = new JLabel("History", SwingConstants.LEFT);
        historyLabel.setFont(new Font("Arial", Font.BOLD, 14));
        historyPanel.add(historyLabel, BorderLayout.NORTH);
        historyPanel.add(historyPane, BorderLayout.CENTER);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(historyPanel, BorderLayout.EAST);
        
        frame.setVisible(true);
    }
    
    private class ButtonClickListener implements ActionListener {
        private JTextField display;

        public ButtonClickListener(JTextField display) {
            this.display = display;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "C":
                    currentInput = "0";
                    result = 0;
                    operator = "";
                    break;
                case "CE":
                    currentInput = "0";
                    break;
                case "=":
                    performCalculation();
                    operator = "";
                    break;
                case "x^2":
                    currentInput = String.valueOf(Math.pow(Double.parseDouble(currentInput), 2));
                    break;
                case "√":
                    currentInput = String.valueOf(Math.sqrt(Double.parseDouble(currentInput)));
                    break;
                case "1/x":
                    currentInput = String.valueOf(1 / Double.parseDouble(currentInput));
                    break;
                case "/":
                case "X":
                case "-":
                case "+":
                    handleOperator(command);
                    break;
                default:
                    if (currentInput.equals("0")) {
                        currentInput = command;
                    } else {
                        currentInput += command;
                    }
                    break;
            }
            display.setText(currentInput);
        }

        private void handleOperator(String newOperator) {
            if (!operator.isEmpty()) {
                performCalculation();
            }
            operator = newOperator;
            result = Double.parseDouble(currentInput);
            currentInput = "0";
        }

        private void performCalculation() {
            double input = Double.parseDouble(currentInput);
            switch (operator) {
                case "+":
                    result += input;
                    break;
                case "-":
                    result -= input;
                    break;
                case "X":
                    result *= input;
                    break;
                case "/":
                    if (input != 0) {
                        result /= input;
                    } else {
                        currentInput = "Error";
                        display.setText(currentInput);
                        return;
                    }
                    break;
                default:
                    break;
            }
            history.add(result+"");
            updateHistory();
            currentInput = String.valueOf(result);
        }
    }

    private void updateHistory() {
        StringBuilder sb = new StringBuilder();
        for (String entry : history) {
            sb.append(entry).append("\n");
        }
        historyArea.setText(sb.toString());
    }
}
