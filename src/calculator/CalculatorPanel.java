package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CalculatorPanel extends JPanel {
    private JButton display;
    private JPanel buttonPanel;

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
        @Override
        public void actionPerformed(ActionEvent e) {
            String label = ((JButton)e.getSource()).getText();
            display.setText(display.getText() + label);
        }
    }
    private class State {

    }
}
