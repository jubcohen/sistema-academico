# (mini) Sistema Acadêmico em Java com Interface Swing

Este repositório contém um projeto acadêmico desenvolvido como parte da disciplina **Projeto de Programas**, no curso de Engenharia da Computação da UEA.

## Objetivo

Criar um sistema simples de cadastro e matrícula de alunos, turmas e disciplinas usando Java e a biblioteca Swing para interface gráfica. O foco principal foi aprender na prática os seguintes conceitos:

- Programação orientada a objetos
- Criação de interfaces gráficas com `JFrame`, `JPanel`, `JTabbedPane`, etc.
- Manipulação de listas (`ArrayList`)
- Comunicação entre objetos (`Turma` contendo `Disciplina`, `Aluno`)
- Representação UML de classes, sequência, caso de uso e objetos

## Estrutura dos arquivos

| Arquivo | Descrição |
|--------|-----------|
| `Pessoa.java` | Contém as classes `Pessoa`, `Aluno`, `Disciplina` e `Turma` |
| `SistemaAcademicoView.java` | Interface gráfica usando expressões lambda |
| `SistemaAcademicoView_sem_lambda.java` | Versão equivalente, usando código tradicional sem lambda |

## Observação

Uma das minhas descobertas nesse processo foi o uso de **expressões lambda em Java**, algo que inicialmente era novo para mim, mas se mostrou muito útil para simplificar ações de interface. Ao mesmo tempo, mantive uma versão **sem lambda**, para reforçar o meu entendimento.

Outro ponto importante foi conhecer melhor o Swing. No começo, eu ainda não sabia os componentes e layouts, mas conforme fui pesquisando, implementei no sistema.

---

Feito com 💻 por Julia Cohen
