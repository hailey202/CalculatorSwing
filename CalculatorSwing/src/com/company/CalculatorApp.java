package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp {
    private JFrame frame;
    private JPanel panel;
    private JTextField display;
    private String currentInput;
    private double result;
    private String operator;

    public CalculatorApp() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);

        currentInput = "";
        result = 0.0;
        operator = "";

        frame.add(display, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        createButtons();

        frame.setVisible(true);
    }

    private void createButtons() {
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener(label));
            panel.add(button);
        }
    }

    private class ButtonClickListener implements ActionListener {
        String buttonLabel;

        public ButtonClickListener(String label) {
            buttonLabel = label;
        }

        public void actionPerformed(ActionEvent e) {
            if (buttonLabel.matches("[0-9]")) {
                currentInput += buttonLabel;
                display.setText(currentInput);
            } else if (buttonLabel.equals("C")) {
                currentInput = "";
                result = 0.0;
                operator = "";
                display.setText("");
            } else if (buttonLabel.matches("[+\\-*/]")) {
                if (!currentInput.isEmpty()) {
                    operator = buttonLabel;
                    result = Double.parseDouble(currentInput);
                    currentInput = "";
                }
            } else if (buttonLabel.equals("=")) {
                if (!currentInput.isEmpty() && !operator.isEmpty()) {
                    double secondOperand = Double.parseDouble(currentInput);
                    switch (operator) {
                        case "+":
                            result += secondOperand;
                            break;
                        case "-":
                            result -= secondOperand;
                            break;
                        case "*":
                            result *= secondOperand;
                            break;
                        case "/":
                            if (secondOperand != 0.0) {
                                result /= secondOperand;
                            } else {
                                display.setText("Error");
                                return;
                            }
                            break;
                    }
                    display.setText(String.valueOf(result));
                    currentInput = "";
                    operator = "";
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculatorApp();
            }
        });
    }
}






