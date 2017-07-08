package calculator;

import javax.swing.*;

class CalculatorFrame extends JFrame {
    CalculatorFrame() {
        add(new CalculatorPanel());
        pack();
    }
}
