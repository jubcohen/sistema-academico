package projetoacademico;

import java.util.ArrayList;
import java.util.List;

// classe abstrata base com nome e cpf
public abstract class Pessoa {
    protected String nome;
    protected String cpf;

    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {return nome;}
    public String getCpf() {return cpf;}
}

// aluno herda de pessoa e tem matricula e turma

class Aluno extends Pessoa {
    private String matricula;
    private Turma turma;

    public Aluno(String nome, String cpf, String matricula) {
        super(nome, cpf);
        this.matricula = matricula;
    }

    public String getMatricula() { return matricula; }
    public Turma  getTurma()     { return turma;     }
    public void   setTurma(Turma turma) { this.turma = turma; }

    @Override
    public String toString() { return nome + " (" + matricula + ")"; }
}



class Disciplina {
    private String codigo;
    private String nome;

    public Disciplina(String codigo, String nome) {
        this.codigo = codigo;
        this.nome   = nome;
    }

    public String getCodigo() {return codigo;}
    public String getNome() {return nome;}

    @Override
    public String toString() {return codigo + " - " + nome;}
}

// turma tem codigo, lista de disciplinas e lista de alunos

class Turma {
    private String codigo;
    private List<Disciplina> disciplinas = new ArrayList<>();
    private List<Aluno> alunos = new ArrayList<>();

    public Turma(String codigo) {this.codigo = codigo;}

    public boolean adicionarDisciplina(Disciplina d) {
        if (disciplinas.size() < 5) return disciplinas.add(d);
        return false;
    }

    public void matricularAluno(Aluno a) {
        alunos.add(a);
        a.setTurma(this);
    }

    public List<Disciplina> getDisciplinas() {return disciplinas; }
    public List<Aluno> getAlunos(){return alunos;}
    public String getCodigo() {return codigo;}

    @Override
    public String toString() {return "Turma " + codigo;}
}
