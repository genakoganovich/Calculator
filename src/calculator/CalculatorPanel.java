package calculator;

import javax.swing.*;
import java.awt.*;

class CalculatorPanel extends JPanel {
    private JComponent display;
    private JPanel buttonPanel;

    CalculatorPanel() {
        setLayout(new BorderLayout());
        display = createDisplay();
        add(display, BorderLayout.NORTH);
        buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.CENTER);
        
    }
    private JComponent createDisplay() {
        JComponent display = new JButton("0");
        display.setEnabled(false);
        return display;
    }
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        String[] labels = new String[]{
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };
        for (String s : labels) {
            panel.add(new JButton(s));
        }
        return panel;
    }
}
