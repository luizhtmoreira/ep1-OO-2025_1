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

    private static void editarAluno(){
        System.out.println("\n---EDITAR ALUNO---");
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        Aluno aluno = Aluno.buscarAlunoPorMatricula(matricula);

        if(aluno == null){
            System.out.println("Aluno não encontrado");
            return; //volta para o menu do modo Aluno
        }

        System.out.println("\nDados atuais:");
        System.out.println("Nome: " + aluno.getNome());
        System.out.println("Curso: " + aluno.getCurso());

        System.out.print("\nNovo nome: ");
        String novoNome = scanner.nextLine();
        aluno.setNome(novoNome);

        System.out.print("Novo curso: ");
        String novoCurso = scanner.nextLine();
        aluno.setCurso(novoCurso);

        System.out.println("\nDados de aluno atualizados!");
    }
    

    private static void listarAlunos(){
        System.out.println("\n---LISTA DE ALUNOS---");
        List<Aluno> alunos = Aluno.listarTodos();

        if (alunos.isEmpty()){
            System.out.println("Nenhum aluno cadastrado.");
            return; //early return 
        }

        for(Aluno aluno : alunos){
            System.out.println("\nMatrícula: " + aluno.getMatricula());
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Curso: " + aluno.getCurso());
            System.out.println("Tipo: " + (aluno instanceof AlunoEspecial ? "Especial" : "Normal"));
            System.out.println("Disciplinas matriculadas: " + aluno.getDisciplinasMatriculadas());
            System.out.println("Disciplinas concluídas: " + aluno.getDisciplinasConcluidas());
        }
    }

    private static void matricularAluno(){
        System.out.println("\n---MATRICULAR ALUNO---");
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        Aluno aluno = Aluno.buscarAlunoPorMatricula(matricula);

        if(aluno == null){
            System.out.println("Aluno não encontrado");
            return; //volta para o menu do modo Aluno
        }

        System.out.println("\nTurmas disponíveis:");
        List<Turma> turmas = Turma.getTodasTurmas();

        for(Turma turma : turmas){
            Disciplina disciplina = Disciplina.buscarPorCodigo(turma.getCodigoDisciplina());
            System.out.println("\nTurma: " + turma.getNumeroTurma());
            System.out.println("Disciplina: " + (disciplina != null ? disciplina.getNome() : "Desconhecida")); 
            System.out.println("Horário: " + turma.getHorario());
            System.out.println("Vagas disponíveis: " + (turma.getCapacidade() - turma.getMatriculas().size()));
        }

        System.out.print("\nNúmero da turma para matricular: ");
        String numeroTurma = scanner.nextLine();
        
        Turma turma = Turma.buscarPorNumero(numeroTurma);
        if (turma == null) {
            System.out.println("Turma não encontrada!");
            return;
        }

        Disciplina disciplina = Disciplina.buscarPorCodigo(turma.getCodigoDisciplina());
        if (disciplina != null && !aluno.verificarPreRequisitos(disciplina.getPreRequisitos())) {
            System.out.println("Aluno não possui os pré-requisitos necessários!");
            return;
        }
                
        try {
            aluno.matricularDisciplina(disciplina.getCodigo()); 
            
            if (turma.matricularAluno(aluno.getMatricula())) {
                System.out.println("Aluno matriculado com sucesso!");
            } else {
                System.out.println("Não há vagas disponíveis nesta turma!");
                aluno.trancarDisciplina(disciplina.getCodigo());
            }
        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void trancarDisciplina(){
        System.out.println("\n---TRANCAR DISCIPLINA---");
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        Aluno aluno = Aluno.buscarAlunoPorMatricula(matricula);

        if(aluno == null){
            System.out.println("Aluno não encontrado");
            return; //volta para o menu do modo Aluno
        }
        
        List<String> disciplinas = aluno.getDisciplinasMatriculadas();
        if (disciplinas.isEmpty()) {
            System.out.println("Aluno não está matriculado em nenhuma disciplina.");
            return;
        }

        System.out.println("\nDisciplinas matriculadas:");
        for (String codigo : disciplinas){
            Disciplina disciplina = Disciplina.buscarPorCodigo(codigo);
            System.out.println(codigo + "-" + (disciplina != null ? disciplina.getNome() : "desconhecida"));
        }

        System.out.print("\nCódigo da disciplina para trancar: ");
        String codigoDisciplina = scanner.nextLine();
        
        if (!disciplinas.contains(codigoDisciplina)) {
            System.out.println("Aluno não está matriculado nesta disciplina!");
            return;
        }
        
        aluno.trancarDisciplina(codigoDisciplina);
        
        // Remover de todas as turmas da disciplina
        for (Turma turma : Turma.getTodasTurmas()) {
            if (turma.getCodigoDisciplina().equals(codigoDisciplina)) {
                turma.getAlunosMatriculados().remove(aluno.getMatricula());
            }
        }
        
        System.out.println("Disciplina trancada com sucesso!");
    }        

    private static void addDisciplinaConcluida(){
        System.out.println("\n---ADICIONAR DISCIPLINA CONCLUÍDA---");
        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();
        
        Aluno aluno = Aluno.buscarAlunoPorMatricula(matricula);

        if(aluno == null){
            System.out.println("Aluno não encontrado");
            return; 
        }

        List<Disciplina> disciplinas = Disciplina.getTodasDisciplinas();

        if(disciplinas.isEmpty()){
            System.out.println("\nNenhuma disciplina cadastrada.");
            return;
        }

        System.out.println("Disciplinas cadastradas:");
        for(Disciplina disciplina : disciplinas){
            System.out.println(disciplina.getCodigo() + "-" + disciplina.getNome());
        }

        System.out.print("Código da disciplina para adicionar: ");
        String codigoDisciplina = scanner.nextLine();

        Disciplina disciplina = Disciplina.buscarPorCodigo(codigoDisciplina);
        if (disciplina == null){
            System.out.println("Disciplina não encontrada");
            return;
        }

        aluno.adicionarDisciplinaConcluida(codigoDisciplina);

        System.out.println("Disciplina adicionada ao histórico");
    }



}
