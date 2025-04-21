package projetoacademico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// classe da interface grafica principal do sistema
public class SistemaAcademicoView extends JFrame {

    // listas que armazenam os dados do sistema
    private List<Aluno> alunos = new ArrayList<>(); // estrutura dinâmica que guarda alunos
    private List<Disciplina> disciplinas = new ArrayList<>(); // usada em combos e lista
    private List<Turma> turmas = new ArrayList<>(); // armazena turmas criadas

    public SistemaAcademicoView() {
        setTitle("Sistema Acadêmico");
        setDefaultCloseOperation(EXIT_ON_CLOSE); // fechar aplicacao quando clicar no xl
        setSize(700, 500); // tam janela

        JTabbedPane tabs = new JTabbedPane(); // cria container com abas

        tabs.add("Aluno", painelAluno());
        tabs.add("Disciplina", painelDisciplina());
        tabs.add("Turma", painelTurma());
        tabs.add("Matrícula", painelMatricula());
        tabs.add("Resumo", painelResumo());

        add(tabs); // adiciona o conj na pagina principal
    }

    // painel de cadastro de aluno
    private JPanel painelAluno() {
        JPanel p = new JPanel(new GridLayout(4, 2, 6, 6));
        JTextField nome = new JTextField();
        JTextField cpf = new JTextField();
        JTextField mat = new JTextField();
        JButton salvar = new JButton("Salvar");

        p.add(new JLabel("Nome")); p.add(nome);
        p.add(new JLabel("CPF")); p.add(cpf);
        p.add(new JLabel("Matrícula")); p.add(mat);
        p.add(new JLabel()); p.add(salvar);

        // acao do btn salvar
        salvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alunos.add(new Aluno(nome.getText(), cpf.getText(), mat.getText()));
                JOptionPane.showMessageDialog(SistemaAcademicoView.this, "Aluno cadastrado");
                nome.setText(""); cpf.setText(""); mat.setText(""); // reseta
            }
        });

        return p;
    }

    // painel de cadastro de disciplina
    private JPanel painelDisciplina() {
        JPanel p = new JPanel(new GridLayout(3, 2));
        JTextField cod = new JTextField();
        JTextField nom = new JTextField();
        JButton salvar = new JButton("Salvar");

        p.add(new JLabel("Código")); p.add(cod);
        p.add(new JLabel("Nome")); p.add(nom);
        p.add(new JLabel()); p.add(salvar);

        salvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                disciplinas.add(new Disciplina(cod.getText(), nom.getText()));
                JOptionPane.showMessageDialog(SistemaAcademicoView.this, "Disciplina cadastrada");
                cod.setText(""); nom.setText("");
            }
        });

        return p;
    }

    // painel para criar turma e adicionar disciplinas
    private JPanel painelTurma() {
        JPanel p = new JPanel(new BorderLayout());

        JPanel topo = new JPanel(new FlowLayout());
        JTextField cod = new JTextField(5);
        JButton criar = new JButton("Criar");
        topo.add(new JLabel("Código da Turma")); topo.add(cod); topo.add(criar);

        DefaultListModel<Disciplina> modelo = new DefaultListModel<>();
        JList<Disciplina> lista = new JList<>(modelo);
        JScrollPane scroll = new JScrollPane(lista);

        JButton addDisc = new JButton("Adicionar Disciplina");

        JPanel centro = new JPanel(new BorderLayout());
        centro.add(scroll, BorderLayout.CENTER);
        centro.add(addDisc, BorderLayout.SOUTH);

        p.add(topo, BorderLayout.NORTH);
        p.add(centro, BorderLayout.CENTER);

        criar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                turmas.add(new Turma(cod.getText())); // cria e guarda
                JOptionPane.showMessageDialog(SistemaAcademicoView.this, "Turma criada");
                cod.setText("");
            }
        });

        addDisc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (turmas.isEmpty()) {
                    JOptionPane.showMessageDialog(SistemaAcademicoView.this, "Crie uma turma antes");
                    return;
                }

                Turma t = turmas.get(turmas.size() - 1);

                Disciplina d = (Disciplina) JOptionPane.showInputDialog(
                        SistemaAcademicoView.this, "Selecione", "Disciplinas",
                        JOptionPane.PLAIN_MESSAGE, null, disciplinas.toArray(), null
                );

                if (d != null) {
                    if (t.adicionarDisciplina(d)) modelo.addElement(d); //EXIBIR na lista
                    else JOptionPane.showMessageDialog(SistemaAcademicoView.this, "Turma já possui 5 disciplinas");
                }
            }
        });

        return p;
    }

    //painel para matricular aluno
    private JPanel painelMatricula() {
        JPanel p = new JPanel(new GridLayout(4, 1));

        JComboBox<Aluno> comboAluno = new JComboBox<>();
        JComboBox<Turma> comboTurma = new JComboBox<>();
        JButton atualizar = new JButton("Atualizar");
        JButton matricular = new JButton("Matricular");

        p.add(comboAluno); p.add(comboTurma); p.add(atualizar); p.add(matricular);

        atualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comboAluno.removeAllItems();
                for (Aluno aluno : alunos) {
                    comboAluno.addItem(aluno);
                }
                comboTurma.removeAllItems();
                for (Turma turma : turmas) {
                    comboTurma.addItem(turma);
                }
            }
        });

        matricular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Aluno a = (Aluno) comboAluno.getSelectedItem();
                Turma t = (Turma) comboTurma.getSelectedItem();
                if (a != null && t != null) {
                    t.matricularAluno(a);
                    JOptionPane.showMessageDialog(SistemaAcademicoView.this, "Aluno matriculado");
                }
            }
        });

        return p;
    }

    private JPanel painelResumo() {
        JPanel p = new JPanel(new BorderLayout());

        DefaultListModel<Turma> modeloTurmas = new DefaultListModel<>();
        JList<Turma> lstTurmas = new JList<>(modeloTurmas);
        JScrollPane scrTurmas = new JScrollPane(lstTurmas);

        DefaultListModel<Aluno> modeloAlunos = new DefaultListModel<>();
        JList<Aluno> lstAlunos = new JList<>(modeloAlunos);
        JScrollPane scrAlunos = new JScrollPane(lstAlunos);

        DefaultListModel<Disciplina> modeloDiscs = new DefaultListModel<>();
        JList<Disciplina> lstDiscs = new JList<>(modeloDiscs);
        JScrollPane scrDiscs = new JScrollPane(lstDiscs);

        JButton atualizar = new JButton("Atualizar");

        lstTurmas.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    modeloAlunos.clear();
                    modeloDiscs.clear();
                    Turma t = lstTurmas.getSelectedValue();
                    if (t != null) {
                        for (Aluno aluno : t.getAlunos()) {
                            modeloAlunos.addElement(aluno);
                        }
                        for (Disciplina disc : t.getDisciplinas()) {
                            modeloDiscs.addElement(disc);
                        }
                    }
                }
            }
        });

        atualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modeloTurmas.clear();
                for (Turma turma : turmas) {
                    modeloTurmas.addElement(turma);
                }
                modeloAlunos.clear();
                modeloDiscs.clear();
            }
        });

        JPanel right = new JPanel(new GridLayout(1, 2));
        right.add(scrAlunos);
        right.add(scrDiscs);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrTurmas, right);
        split.setResizeWeight(0.3);

        p.add(split, BorderLayout.CENTER);
        p.add(atualizar, BorderLayout.SOUTH);

        return p;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SistemaAcademicoView().setVisible(true);
            }
        });
    }
}