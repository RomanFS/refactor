package com.company.calc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Main extends Frame {

    public boolean setClear = true;
    double number, memValue;
    char op;

    String[] digitButtonText = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", "+/-", "."};
    String[] operatorButtonText = {"/", "sqrt", "*", "%", "-", "1/X", "+", "="};
    String[] memoryButtonText = {"MC", "MR", "MS", "M+"};
    String[] specialButtonText = {"Backspc", "C", "CE"};

    MyDigitButton[] digitButton = new MyDigitButton[digitButtonText.length];
    MyOperatorButton[] operatorButton = new MyOperatorButton[operatorButtonText.length];
    MyMemoryButton[] memoryButton = new MyMemoryButton[memoryButtonText.length];
    MySpecialButton[] specialButton = new MySpecialButton[specialButtonText.length];

    Label displayLabel = new Label("0", Label.RIGHT);
    Label memLabel = new Label(" ", Label.RIGHT);

    final int FRAME_WIDTH = 325, FRAME_HEIGHT = 325;
    final int HEIGHT = 30, WIDTH = 30, H_SPACE = 10, V_SPACE = 10;
    final int TOPX = 30, TOPY = 50;

    Main(String frameText) {
        super(frameText);

        int tempX = TOPX, y = TOPY;
        displayLabel.setBounds(tempX, y, 240, HEIGHT);
        displayLabel.setBackground(Color.BLUE);
        displayLabel.setForeground(Color.WHITE);
        add(displayLabel);

        memLabel.setBounds(TOPX, TOPY + HEIGHT + V_SPACE, WIDTH, HEIGHT);
        add(memLabel);


        y = TOPY + 2 * (HEIGHT + V_SPACE);
        for (int i = 0; i < memoryButton.length; i++) {
            memoryButton[i] = new MyMemoryButton(tempX, y, WIDTH, HEIGHT, memoryButtonText[i], this);
            memoryButton[i].setForeground(Color.RED);
            y += HEIGHT + V_SPACE;
        }


        tempX = TOPX + (WIDTH + H_SPACE);
        y = TOPY + (HEIGHT + V_SPACE);
        for (int i = 0; i < specialButton.length; i++) {
            specialButton[i] = new MySpecialButton(tempX, y, WIDTH * 2, HEIGHT, specialButtonText[i], this);
            specialButton[i].setForeground(Color.RED);
            tempX = tempX + 2 * WIDTH + H_SPACE;
        }


        int digitX = TOPX + WIDTH + H_SPACE;
        int digitY = TOPY + 2 * (HEIGHT + V_SPACE);
        tempX = digitX;
        y = digitY;
        for (int i = 0; i < digitButton.length; i++) {
            digitButton[i] = new MyDigitButton(tempX, y, WIDTH, HEIGHT, digitButtonText[i], this);
            digitButton[i].setForeground(Color.BLUE);
            tempX += WIDTH + H_SPACE;
            if ((i + 1) % 3 == 0) {
                tempX = digitX;
                y += HEIGHT + V_SPACE;
            }
        }


        int opsX = digitX + 2 * (WIDTH + H_SPACE) + H_SPACE;
        int opsY = digitY;
        tempX = opsX;
        y = opsY;
        for (int i = 0; i < operatorButton.length; i++) {
            tempX += WIDTH + H_SPACE;
            operatorButton[i] = new MyOperatorButton(tempX, y, WIDTH, HEIGHT, operatorButtonText[i], this);
            operatorButton[i].setForeground(Color.RED);
            if ((i + 1) % 2 == 0) {
                tempX = opsX;
                y += HEIGHT + V_SPACE;
            }
        }

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }
        });

        setLayout(null);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main("Calculator - JavaTpoint");
    }
}

class MyDigitButton extends Button implements ActionListener {
    Main cl;


    MyDigitButton(int x, int y, int width, int height, String cap, Main clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        this.cl.add(this);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent ev) {
        String tempText = ((MyDigitButton) ev.getSource()).getLabel();

        if (tempText.equals(".")) {
            if (cl.setClear) {
                cl.displayLabel.setText("0.");
                cl.setClear = false;
            } else {
                boolean dotInTheLine = false;

                for (int i = 0; i < cl.displayLabel.getText().length(); i++) {
                    if (cl.displayLabel.getText().charAt(i) == '.') {
                        dotInTheLine = true;
                    }
                }

                if (dotInTheLine) {
                    cl.displayLabel.setText(cl.displayLabel.getText() + ".");
                }
            }
            return;
        }

        int index;
        try {
            index = Integer.parseInt(tempText);
        } catch (NumberFormatException e) {
            return;
        }

        if (index == 0 && cl.displayLabel.getText().equals("0")) return;

        if (cl.setClear) {
            cl.displayLabel.setText("" + index);
            cl.setClear = false;
        } else
            cl.displayLabel.setText(cl.displayLabel.getText() + index);
    }
}

class MyOperatorButton extends Button implements ActionListener {
    Main cl;

    MyOperatorButton(int x, int y, int width, int height, String cap, Main clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        this.cl.add(this);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent ev) {
        String opText = ((MyOperatorButton) ev.getSource()).getLabel();

        cl.setClear = true;
        double temp = Double.parseDouble(cl.displayLabel.getText());

        if (opText.equals("1/x")) {
            try {
                double tempd = 1 / temp;
                String resText = "" + tempd;
                if (resText.lastIndexOf(".0") > 0)
                    resText = resText.substring(0, resText.length() - 2);
                cl.displayLabel.setText(resText);
            } catch (ArithmeticException excp) {
                cl.displayLabel.setText("Divide by 0.");
            }
            return;
        }
        if (opText.equals("sqrt")) {
            try {
                double tempd = Math.sqrt(temp);

                String resText = "" + tempd;
                if (resText.lastIndexOf(".0") > 0)
                    resText = resText.substring(0, resText.length() - 2);
                cl.displayLabel.setText(resText);
            } catch (ArithmeticException excp) {
                cl.displayLabel.setText("Divide by 0.");
            }
            return;
        }
        if (!opText.equals("=")) {
            cl.number = temp;
            cl.op = opText.charAt(0);
            return;
        }

        switch (cl.op) {
            case '+':
                temp += cl.number;
                break;
            case '-':
                temp = cl.number - temp;
                break;
            case '*':
                temp *= cl.number;
                break;
            case '%':
                try {
                    temp = cl.number % temp;
                } catch (ArithmeticException excp) {
                    cl.displayLabel.setText("Divide by 0.");
                    return;
                }
                break;
            case '/':
                try {
                    temp = cl.number / temp;
                } catch (ArithmeticException excp) {
                    cl.displayLabel.setText("Divide by 0.");
                    return;
                }
                break;
        }

        String resText = "" + temp;
        if (resText.lastIndexOf(".0") > 0)
            resText = resText.substring(0, resText.length() - 2);

        cl.displayLabel.setText(resText);

    }
}

class MyMemoryButton extends Button implements ActionListener {
    Main cl;


    MyMemoryButton(int x, int y, int width, int height, String cap, Main clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        this.cl.add(this);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent ev) {
        char memop = ((MyMemoryButton) ev.getSource()).getLabel().charAt(1);

        cl.setClear = true;
        double temp = Double.parseDouble(cl.displayLabel.getText());

        switch (memop) {
            case 'C':
                cl.memLabel.setText(" ");
                cl.memValue = 0.0;
                break;
            case 'R':
                String resText = "" + cl.memValue;
                if (resText.lastIndexOf(".0") > 0)
                    resText = resText.substring(0, resText.length() - 2);
                cl.displayLabel.setText(resText);
                break;
            case 'S':
                cl.memValue = 0.0;
            case '+':
                cl.memValue += temp;
                if (cl.displayLabel.getText().equals("0") || cl.displayLabel.getText().equals("0.0"))
                    cl.memLabel.setText(" ");
                else
                    cl.memLabel.setText("M");
                break;
        }
    }
}

class MySpecialButton extends Button implements ActionListener {
    Main cl;

    MySpecialButton(int x, int y, int width, int height, String cap, Main clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        this.cl.add(this);
        addActionListener(this);
    }


    public void actionPerformed(ActionEvent ev) {
        String opText = ((MySpecialButton) ev.getSource()).getLabel();

        if (opText.equals("Backspc")) {
            String tempText = "";

            for (int i = 0; i < cl.displayLabel.getText().length() - 1; i++) {
                tempText += cl.displayLabel.getText().charAt(i);
            }

            if (tempText.equals(""))
                cl.displayLabel.setText("0");
            else
                cl.displayLabel.setText(tempText);
            return;
        }

        if (opText.equals("C")) {
            cl.number = 0.0;
            cl.op = ' ';
            cl.memValue = 0.0;
            cl.memLabel.setText(" ");
        }


        cl.displayLabel.setText("0");
        cl.setClear = true;
    }
}
  