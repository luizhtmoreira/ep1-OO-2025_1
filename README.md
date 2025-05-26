# Sistema Acadêmico - FCTE

## Descrição do Projeto

Desenvolvimento de um sistema acadêmico para gerenciar alunos, disciplinas, professores, turmas, avaliações e frequência, utilizando os conceitos de orientação a objetos (herança, polimorfismo e encapsulamento) e persistência de dados em arquivos.

O enunciado do trabalho pode ser encontrado aqui:
- [Trabalho 1 - Sistema Acadêmico](https://github.com/lboaventura25/OO-T06_2025.1_UnB_FCTE/blob/main/trabalhos/ep1/README.md)

## Dados do Aluno

- **Nome completo:** Luiz Henrique Tomaz Moreira
- **Matrícula:** 242028735
- **Curso:** Engenharias
- **Turma:** T06

---

## Instruções para Compilação e Execução

1. **Compilação:**  
   Clone o repositório na sua máquina. Recomendo usar o vs code. Use o comando git clone https://github.com/luizhtmoreira/ep1-OO-2025_1

2. **Execução:**  
   Execute o programa com o botão run java

3. **Estrutura de Pastas:**  
   Projeto ep1 eu/
├── dados/
├── entities/
│           ├── Aluno.java
│           ├── AlunoEspecial.java
│           ├── AlunoNormal.java
│           ├── Avaliacao.java
│           ├── Disciplina.java
│           ├── Frequencia.java
│           ├── Matricula.java
│           ├── Professor.java
│           └── Turma.java
├── fotos/
|           ├─── FotoBoletimAluno.png
|           ├─── FotoCadastroAluno.png
|           └─── FotoMenuInicial.png   
└── program/
           ├─── Main.java
           └─── Persistencia.java  

3. **Versão do JAVA utilizada:**  
   [Descrever aqui como versão do JAVA utilizada no projeto. Sugestão: `java 21`]

---

## Vídeo de Demonstração

- [Inserir o link para o vídeo no YouTube/Drive aqui]

---

## Prints da Execução

1. Menu Principal:  
   ![Inserir Print 1](https://github.com/luizhtmoreira/ep1-OO-2025_1/blob/main/fotos/FotoMenuInicial.png)

2. Cadastro de Aluno:  
   ![Inserir Print 2](https://github.com/luizhtmoreira/ep1-OO-2025_1/blob/main/fotos/FotoCadastroAluno.png)

3. Relatório de Frequência/Notas:  
   ![Inserir Print 3](https://github.com/luizhtmoreira/ep1-OO-2025_1/blob/main/fotos/FotoBoletimAluno.png)

---

## Principais Funcionalidades Implementadas

- [✓] Cadastro, listagem, matrícula e trancamento de alunos (Normais e Especiais)
- [✓] Cadastro de disciplinas e criação de turmas (presenciais e remotas)
- [✓] Matrícula de alunos em turmas, respeitando vagas e pré-requisitos
- [✓] Lançamento de notas e controle de presença
- [✓] Cálculo de média final e verificação de aprovação/reprovação
- [✓] Relatórios de desempenho acadêmico por aluno, turma e disciplina
- [✓] Persistência de dados em arquivos (.txt ou .csv)
- [✓] Tratamento de duplicidade de matrículas
- [✓] Uso de herança, polimorfismo e encapsulamento

---

## Observações (Extras ou Dificuldades)

- [Espaço para o aluno comentar qualquer funcionalidade extra que implementou, dificuldades enfrentadas, ou considerações importantes.]

---

## Contato

- lht.unb@gmail.com
