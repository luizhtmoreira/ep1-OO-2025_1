package program;

import entities.*;
import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        int opcao;
        do {
            System.out.println("\n=== SISTEMA ACADÊMICO FCTE ===");
            System.out.println("1. Modo Aluno");
            System.out.println("2. Modo Disciplina/Turma");
            System.out.println("3. Modo Avaliação/Frequência");
            System.out.println("4. Salvar e Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    modoAluno();
                    break;
                case 2:
                    modoDisciplinaTurma();
                    break;
                case 3:
                    modoAvaliacaoFrequencia();
                    break;
                case 4:
                    salvarDados();
                    System.out.println("Dados salvos. Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }
    
    private static void modoAluno() {
        int opcao;
        do {
            System.out.println("\n=== MODO ALUNO ===");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Editar Aluno");
            System.out.println("3. Listar Alunos");
            System.out.println("4. Matricular em Disciplina");
            System.out.println("5. Trancar Disciplina");
            System.out.println("6. Adicionar Disciplina Concluída");
            System.out.println("7. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    editarAluno();
                    break;
                case 3:
                    listarAlunos();
                    break;
                case 4:
                    matricularAluno();
                    break;
                case 5:
                    trancarDisciplina();
                    break;
                case 6:
                    addDisciplinaConcluida();
                    break;
                case 7:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 7);
    }

    private static void cadastrarAluno(){
        System.out.println("\n---CADASTRAR ALUNO---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Matrícula (9 dígitos): ");
        String matricula = scanner.nextLine();

        System.out.print("Curso: ");
        String curso = scanner.nextLine();

        System.out.print("Tipo (1 = normal / 2 = especial): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        try{
            Aluno aluno;
            if (tipo == 1){
                aluno = new AlunoNormal(nome, matricula, curso);
            }
            else if (tipo == 2){
                aluno = new AlunoEspecial(nome, matricula, curso);
            }
            else{
                System.out.println("Tipo inválido!");
                return;
            }

            Aluno.cadastrar(aluno);
            System.out.println("Aluno cadastrado!");
        } catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
}
