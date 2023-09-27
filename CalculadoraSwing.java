//Vinnicius Oliveira Rodrigues e Giselle Leandro S. De Araujo
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class CalculadoraSwing extends JFrame implements ActionListener {
    private JTextField textField;
    private BigDecimal num1, num2, resultado;
    private String operacao;
    private boolean novoNumero = true; // Indica se o próximo dígito é um novo número

    public CalculadoraSwing() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        textField = new JTextField();
        textField.setEditable(false);
        panel.add(textField);

        for (int i = 0; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(this);
            panel.add(button);
        }

        String[] operacoes = {"+", "-", "*", "/"};
        for (String operacao : operacoes) {
            JButton button = new JButton(operacao);
            button.addActionListener(this);
            panel.add(button);
        }

        JButton igualButton = new JButton("=");
        igualButton.addActionListener(this);
        JButton decimalButton = new JButton(".");
        decimalButton.addActionListener(this);
        JButton clearButton = new JButton("C");
        clearButton.addActionListener(this);

        panel.add(igualButton);
        panel.add(decimalButton);
        panel.add(clearButton);

        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            if (Character.isDigit(command.charAt(0))) {
                if (novoNumero) {
                    textField.setText(command);
                    novoNumero = false;
                } else {
                    textField.setText(textField.getText() + command);
                }
            } else if (command.equals(".")) {
                if (!textField.getText().contains(".")) {
                    textField.setText(textField.getText() + ".");
                }
            } else if (command.equals("C")) {
                textField.setText("");
                num1 = num2 = resultado = BigDecimal.ZERO;
                operacao = "";
                novoNumero = true;
            } else if (command.equals("=")) {
                num2 = new BigDecimal(textField.getText());

                switch (operacao) {
                    case "+":
                        resultado = num1.add(num2);
                        break;
                    case "-":
                        resultado = num1.subtract(num2);
                        break;
                    case "*":
                        resultado = num1.multiply(num2);
                        break;
                    case "/":
                        if (num2.compareTo(BigDecimal.ZERO) != 0) {
                            resultado = num1.divide(num2, 10, BigDecimal.ROUND_HALF_UP);
                        } else {
                            throw new ArithmeticException("Divisão por zero não permitida.");
                        }
                        break;
                }

                textField.setText(resultado.stripTrailingZeros().toPlainString());
                novoNumero = true;
            } else {
                num1 = new BigDecimal(textField.getText());
                operacao = command;
                novoNumero = true;
            }
        } catch (NumberFormatException ex) {
            textField.setText("Entrada inválida");
        } catch (ArithmeticException ex) {
            textField.setText("Erro: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraSwing());
    }
}