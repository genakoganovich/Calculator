package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

class CalculatorPanel extends JPanel {
    private JButton display;
    private JPanel buttonPanel;
    private int firstOperand;
    private int secondOperand;
    private String operator = "+";
    private int result;

    CalculatorPanel() {
        setLayout(new BorderLayout());
        display = createDisplay();
        add(display, BorderLayout.NORTH);
        buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.CENTER);
        
    }
    private JButton createDisplay() {
        JButton display = new JButton("0");
        display.setEnabled(false);
        return display;
    }
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        ActionListener buttonListener = new ButtonListener();
        panel.setLayout(new GridLayout(4, 4));
        String[] labels = new String[]{
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };
        for (String s : labels) {
            JButton button = new JButton(s);
            button.addActionListener(buttonListener);
            panel.add(button);
        }
        return panel;
    }
    private class ButtonListener implements ActionListener {
        private HashMap<String, State> states;
        private State currentState;

        ButtonListener() {
            states = new HashMap<>();
            states.put("Start", new Start());
            states.put("FirstOperand", new FirstOperand());
            states.put("Operator", new Operator());
            states.put("SecondOperand", new SecondOperand());
            setCurrentState("Start");
        }
        void setCurrentState(String stateName) {
            currentState = states.get(stateName);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            currentState.handle(this, ((JButton)e.getSource()).getText());
        }
    }
    private class State {
        void handle(ButtonListener context, String label) {
            display.setText(display.getText() + label);
        }
    }
    private class Start extends State {
        @Override
        void handle(ButtonListener context, String label) {
            if(Character.isDigit(label.charAt(0))) {
                display.setText(label);
                context.setCurrentState("FirstOperand");
            } else {
                display.setText("");
                context.setCurrentState("Start");
            }
        }
    }
    private class FirstOperand extends State {
        @Override
        void handle(ButtonListener context, String label) {
            if(Character.isDigit(label.charAt(0))) {
                display.setText(display.getText() + label);
            } else if (label.equals(".") || label.equals("=")){

            } else {
                operator = label;
                firstOperand = Integer.parseInt(display.getText());
                context.setCurrentState("Operator");
            }
        }
    }
    private class Operator extends State {
        @Override
        void handle(ButtonListener context, String label) {
            if(Character.isDigit(label.charAt(0))) {
                display.setText(label);
                context.setCurrentState("SecondOperand");
            }
        }
    }
    private class SecondOperand extends State {
        @Override
        void handle(ButtonListener context, String label) {
            if(Character.isDigit(label.charAt(0))) {
                display.setText(display.getText() + label);
            } else if (label.equals("=")) {
                secondOperand = Integer.parseInt(display.getText());
                switch (operator) {
                    case "+":
                        result = firstOperand + secondOperand;
                        break;
                    case "-":
                        result = firstOperand - secondOperand;
                        break;
                    case "*":
                        result = firstOperand * secondOperand;
                        break;
                    default:
                        result = firstOperand / secondOperand;
                        break;
                }

                display.setText(Integer.toString(result));
                context.setCurrentState("Start");
            }
        }
    }
}
