
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
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JLabel standardLabel = new JLabel(" = Standard", SwingConstants.LEFT);
        standardLabel.setOpaque(true);
        standardLabel.setBackground(Color.WHITE);
        standardLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(standardLabel, BorderLayout.WEST);

        JTextField display = new JTextField("0");
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(display, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
            "C", "CE", "log", "ln",
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };
        
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setFocusPainted(false);
            button.addActionListener(new ButtonClickListener(display));
            panel.add(button);
        }
        frame.add(panel, BorderLayout.SOUTH);
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
                case "log":
                    currentInput = String.valueOf(Math.log10(Double.parseDouble(currentInput)));
                    break;
                case "ln":
                    currentInput = String.valueOf(Math.log(Double.parseDouble(currentInput)));
                    break;
                case "/":
                case "*":
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
                case "*":
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
            currentInput = String.valueOf(result);
        }
    }
}
