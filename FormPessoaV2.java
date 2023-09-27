//Vinnicius Oliveira Rodrigues e Giselle Leandro S. De Araujo
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FormPessoaV2 extends JFrame {
    protected JTextField campoNumero;
    protected JTextField campoNome;
    protected JTextField campoIdade;
    protected JComboBox<String> comboBoxSexo; // JComboBox para selecionar o sexo
    protected JButton botaoOK;
    protected JButton botaoLimpar;
    protected JButton botaoMostrar;
    protected JButton botaoSair;

    protected List<Pessoa> pessoas; // Lista para armazenar as instâncias de Pessoa
    protected static int numeroPessoa = 0; // Contador para o número de pessoas criadas

    public FormPessoaV2() {
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
        comboBoxSexo = new JComboBox<>(new String[]{"M", "F"}); // JComboBox para selecionar o sexo

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
        campoPanel.add(comboBoxSexo); // Adiciona o JComboBox para escolher o sexo

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
                String sexo = (String) comboBoxSexo.getSelectedItem(); // Obtenha o sexo do JComboBox
                int idade = Integer.parseInt(campoIdade.getText());

                // Crie uma nova instância de Pessoa e adicione à lista
                Pessoa novaPessoa = new Pessoa(nome, sexo.charAt(0), idade); // Converta o sexo em char
                pessoas.add(novaPessoa);

                // Atualize o contador de número de pessoas
                numeroPessoa++;

                // Atualize o campo "Número" com o novo valor
                campoNumero.setText(Integer.toString(numeroPessoa));

                JOptionPane.showMessageDialog(FormPessoaV2.this, "Dados transferidos com sucesso.");
            }
        });

        botaoLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implemente a lógica para limpar os campos aqui
                campoNome.setText("");
                campoIdade.setText("");
                comboBoxSexo.setSelectedIndex(0); // Defina o sexo de volta para a primeira opção (M)
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
                JOptionPane.showMessageDialog(FormPessoaV2.this, mensagem.toString());
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FormPessoaV2().setVisible(true);
            }
        });
    }
}
