//Vinnicius Oliveira Rodrigues e Giselle Leandro S. De Araujo
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class FormPessoa extends JFrame {
    protected JTextField campoNumero;
    protected JTextField campoNome;
    protected JTextField campoIdade;
    protected JTextField campoSexo; // JTextField para o sexo
    protected JButton botaoOK;
    protected JButton botaoLimpar;
    protected JButton botaoMostrar;
    protected JButton botaoSair;

    protected List<Pessoa> pessoas; // Lista para armazenar as instâncias de Pessoa
    protected static int numeroPessoa = 0; // Contador para o número de pessoas criadas

    public FormPessoa() {
        pessoas = new ArrayList<>(); // Inicialize a lista

        setTitle("Formulário de Pessoa");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Painel para os campos
        JPanel campoPanel = new JPanel(new GridLayout(4, 2));
        campoNumero = new JTextField(5);
        campoNome = new JTextField(20);
        campoIdade = new JTextField(3);
        campoSexo = new JTextField(1); // JTextField para o sexo

        campoNumero.setEditable(false); // Torna o campo não editável

        // Configura o valor inicial do campo "Número" com o número atual de pessoas
        campoNumero.setText(Integer.toString(numeroPessoa));

        campoPanel.add(new JLabel("Número:"));
        campoPanel.add(campoNumero);
        campoPanel.add(new JLabel("Nome:"));
        campoPanel.add(campoNome);
        campoPanel.add(new JLabel("Idade:"));
        campoPanel.add(campoIdade);
        campoPanel.add(new JLabel("Sexo:"));
        campoPanel.add(campoSexo); // Adiciona o JTextField para o sexo

        mainPanel.add(campoPanel, BorderLayout.NORTH);

        // Painel para os botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        botaoOK = new JButton("OK");
        botaoLimpar = new JButton("Limpar");
        botaoMostrar = new JButton("Mostrar");
        botaoSair = new JButton("Sair");

        buttonPanel.add(botaoOK);
        buttonPanel.add(botaoLimpar);
        buttonPanel.add(botaoMostrar);
        buttonPanel.add(botaoSair);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        botaoOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtenha os dados dos campos
                String nome = campoNome.getText();
                String sexo = campoSexo.getText().toUpperCase(); // Obtenha o texto do JTextField e converta para maiúsculas
                int idade = Integer.parseInt(campoIdade.getText());

                // Validação para garantir que o sexo seja "M" ou "F"
                if (!sexo.equals("M") && !sexo.equals("F")) {
                    JOptionPane.showMessageDialog(FormPessoa.this, "Por favor, insira 'M' ou 'F' para o sexo.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Crie uma nova instância de Pessoa e adicione à lista
                Pessoa novaPessoa = new Pessoa(nome, sexo.charAt(0), idade); // Converta o sexo em char
                pessoas.add(novaPessoa);

                // Atualize o contador de número de pessoas
                numeroPessoa++;

                // Atualize o campo "Número" com o novo valor
                campoNumero.setText(Integer.toString(numeroPessoa));

                JOptionPane.showMessageDialog(FormPessoa.this, "Dados transferidos com sucesso.");
            }
        });

        botaoLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implemente a lógica para limpar os campos aqui
                campoNome.setText("");
                campoIdade.setText("");
                campoSexo.setText("");
            }
        });

        botaoMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exiba os dados de todas as pessoas registradas
                StringBuilder mensagem = new StringBuilder();
                for (Pessoa pessoa : pessoas) {
                    mensagem.append("Nome: ").append(pessoa.getNome()).append("\n");
                    mensagem.append("Sexo: ").append(pessoa.getSexo()).append("\n");
                    mensagem.append("Idade: ").append(pessoa.getIdade()).append("\n\n");
                }
                JOptionPane.showMessageDialog(FormPessoa.this, mensagem.toString());
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Adicione um KeyListener para o campo "Sexo" para aceitar apenas "M" ou "F"
        campoSexo.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c != 'M' && c != 'F') {
                    e.consume(); // Ignore caracteres diferentes de 'M' e 'F'
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FormPessoa().setVisible(true);
            }
        });
    }
}
