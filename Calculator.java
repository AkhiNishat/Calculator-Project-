
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
