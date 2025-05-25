package program;

import entities.*;
import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        boolean executando = true;
        while(executando){
            System.out.println("\n=== SISTEMA ACADÊMICO FCTE ===");
            System.out.println("1. Modo Aluno");
            System.out.println("2. Modo Disciplina/Turma");
            System.out.println("3. Modo Avaliação/Frequência");
            System.out.println("4. Salvar e Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
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
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void modoAluno() {
        boolean voltar = false;
        while(!voltar){
            System.out.println("\n=== MODO ALUNO ===");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Editar Aluno");
            System.out.println("3. Listar Alunos");
            System.out.println("4. Matricular em Disciplina");
            System.out.println("5. Trancar Disciplina");
            System.out.println("6. Adicionar Disciplina Concluída");
            System.out.println("7. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
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
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
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
            return; 
        }
        
        List<String> disciplinas = aluno.getDisciplinasMatriculadas();
        if (disciplinas.isEmpty()) {
            System.out.println("Aluno não está matriculado em nenhuma disciplina.");
            return;
        }

        System.out.println("\nDisciplinas matriculadas:");
        for (String codigo : disciplinas){
            Disciplina disciplina = Disciplina.buscarPorCodigo(codigo);
            System.out.println(codigo + " - " + (disciplina != null ? disciplina.getNome() : "desconhecida"));
        }

        System.out.print("\nCódigo da disciplina para trancar: ");
        String codigoDisciplina = scanner.nextLine();
        
        if (!disciplinas.contains(codigoDisciplina)) {
            System.out.println("Aluno não está matriculado nesta disciplina!");
            return;
        }
        
        aluno.trancarDisciplina(codigoDisciplina);
        
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
            System.out.println(disciplina.getCodigo() + " - " + disciplina.getNome());
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

    private static void modoDisciplinaTurma() {
        boolean voltar = false;
        while(!voltar){
            System.out.println("\n=== MODO DISCIPLINA/TURMA ===");
            System.out.println("1. Cadastrar Disciplina");
            System.out.println("2. Cadastrar Turma");
            System.out.println("3. Listar Disciplinas");
            System.out.println("4. Listar Turmas");
            System.out.println("5. Adicionar Pré-requisito");
            System.out.println("6. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (opcao) {
                case 1:
                    cadastrarDisciplina();
                    break;
                case 2:
                    cadastrarTurma();
                    break;
                case 3:
                    listarDisciplinas();
                    break;
                case 4:
                    listarTurmas();
                    break;
                case 5:
                    adicionarPreRequisito();
                    break;
                case 6:
                    System.out.println("Retornando ao menu principal...");
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }    

    private static void cadastrarDisciplina(){
        System.out.println("\n---CADASTRAR DISCIPLINA---");
        System.out.print("Código da disciplina: ");
        String codigo = scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Carga horária: ");
        int cargaHoraria = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Número de turmas: ");
        int turmas = scanner.nextInt();
        scanner.nextLine();

        try{
            Disciplina disciplina = new Disciplina(codigo, nome, cargaHoraria, turmas);
            Disciplina.cadastrar(disciplina);
            System.out.println("Disciplina cadastrada!");
        } catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }

    }

    private static void cadastrarTurma(){
        System.out.println("\n=== CADASTRAR TURMA ===");
        System.out.print("Código da disciplina: ");
        String codigoDisciplina = scanner.nextLine();
        
        Disciplina disciplina = Disciplina.buscarPorCodigo(codigoDisciplina);
        if (disciplina == null) {
            System.out.println("Disciplina não encontrada!");
            return;
        }

        System.out.print("Nome do professor: ");
        String nomeProfessor = scanner.nextLine();
        Professor professor = new Professor(nomeProfessor);
        
        System.out.print("Número da turma: ");
        String numeroTurma = scanner.nextLine();
        
        System.out.print("Semestre: ");
        int semestre = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Sala (deixe em branco para turma remota): ");
        String sala = scanner.nextLine();
        
        System.out.print("Horário: ");
        String horario = scanner.nextLine();
        
        System.out.print("Capacidade: ");
        int capacidade = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Tipo (1-Presencial / 2-Remota): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Forma de avaliação (1-Média Simples / 2-Média Ponderada): ");
        int avaliacao = scanner.nextInt();
        scanner.nextLine();
        
        try {
            Turma.TipoTurma tipoTurma = tipo == 1 ? Turma.TipoTurma.PRESENCIAL : Turma.TipoTurma.REMOTA;
            Turma.TipoAvaliacao tipoAvaliacao = avaliacao == 1 ? Turma.TipoAvaliacao.MEDIA_SIMPLES : Turma.TipoAvaliacao.MEDIA_PONDERADA;
            
            Turma turma = new Turma(numeroTurma, codigoDisciplina, semestre, sala.isEmpty() ? null : sala, horario, capacidade, tipoTurma, tipoAvaliacao, professor);
            
            Turma.cadastrar(turma);
            System.out.println("Turma cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarDisciplinas() {
        System.out.println("\n=== LISTA DE DISCIPLINAS ===");
        
        List<Disciplina> disciplinas = Disciplina.getTodasDisciplinas();
        
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }
        
        for (Disciplina disciplina : disciplinas) {
            System.out.println("\nCódigo: " + disciplina.getCodigo());
            System.out.println("Nome: " + disciplina.getNome());
            System.out.println("Carga Horária: " + disciplina.getTotalAulas() + " horas");
            System.out.println("Turmas: " + disciplina.getTurmas());
            System.out.println("Pré-requisitos: " + disciplina.getPreRequisitos());
        }
    }

    private static void listarTurmas() {
        System.out.println("\n=== LISTA DE TURMAS ===");
        List<Turma> turmas = Turma.getTodasTurmas();
        
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
            return;
        }
        
        for (Turma turma : turmas) {
            Disciplina disciplina = Disciplina.buscarPorCodigo(turma.getCodigoDisciplina());
            System.out.println("\nTurma: " + turma.getNumeroTurma());
            System.out.println("Disciplina: " + (disciplina != null ? disciplina.getNome() : "Desconhecida"));
            System.out.println("Semestre: " + turma.getSemestre());
            System.out.println("Sala: " + (turma.getSala() != null ? turma.getSala() : "Remota"));
            System.out.println("Horário: " + turma.getHorario());
            System.out.println("Capacidade: " + turma.getCapacidade() + " (Matriculados: " + turma.getMatriculas().size() + ")");
            System.out.println("Tipo: " + turma.getTipo());
            System.out.println("Avaliação: " + turma.getTipoAvaliacao());
        }
    }    

    private static void adicionarPreRequisito() {
        System.out.println("\n=== ADICIONAR PRÉ-REQUISITO ===");
        System.out.print("Código da disciplina: ");
        String codigoDisciplina = scanner.nextLine();
        
        Disciplina disciplina = Disciplina.buscarPorCodigo(codigoDisciplina);
        if (disciplina == null) {
            System.out.println("Disciplina não encontrada!");
            return;
        }
        
        System.out.print("Código da disciplina pré-requisito: ");
        String codigoPreRequisito = scanner.nextLine();
        
        Disciplina preRequisito = Disciplina.buscarPorCodigo(codigoPreRequisito);
        if (preRequisito == null) {
            System.out.println("Disciplina pré-requisito não encontrada!");
            return;
        }
        
        disciplina.addPreRequisito(codigoPreRequisito);
        System.out.println("Pré-requisito adicionado com sucesso!");
    }

    private static void modoAvaliacaoFrequencia(){
        boolean voltar = false;
        while(!voltar) {
            System.out.println("\n=== MODO AVALIAÇÃO/FREQUÊNCIA ===");
            System.out.println("1. Lançar Notas");
            System.out.println("2. Lançar Frequência");
            System.out.println("3. Gerar Relatórios");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (opcao) {
                case 1:
                    lancarNotas();
                    break;
                case 2:
                    lancarFrequencia();
                    break;
                case 3:
                    gerarRelatorios();
                    break;
                case 4:
                    System.out.println("Retornando ao menu principal...");
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void lancarNotas() {
        System.out.println("\n=== LANÇAR NOTAS ===");
        System.out.print("Número da turma: ");
        String numeroTurma = scanner.nextLine();
        
        Turma turma = Turma.buscarPorNumero(numeroTurma);
        if (turma == null) {
            System.out.println("Turma não encontrada!");
            return;
        }
        
        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();
        
        if (!turma.getAlunosMatriculados().contains(matricula)) {
            System.out.println("Aluno não está matriculado nesta turma!");
            return;
        }
    
        Aluno aluno = Aluno.buscarAlunoPorMatricula(matricula);
        
        if (aluno instanceof AlunoEspecial) {
            System.out.println("Alunos especiais não recebem notas, apenas frequência!");
            return;
        }
    
        Avaliacao avaliacao = new Avaliacao(matricula, numeroTurma);
        
        Turma.TipoAvaliacao tipo = turma.getTipoAvaliacao();
        
        System.out.print("Nota P1: ");
        avaliacao.setP1(scanner.nextDouble());
        
        System.out.print("Nota P2: ");
        avaliacao.setP2(scanner.nextDouble());
        
        System.out.print("Nota P3: ");
        avaliacao.setP3(scanner.nextDouble());
        
        if (tipo == Turma.TipoAvaliacao.MEDIA_PONDERADA) {
            System.out.print("Nota Listas: ");
            avaliacao.setListas(scanner.nextDouble());
            
            System.out.print("Nota Seminário: ");
            avaliacao.setSeminario(scanner.nextDouble());
        }
        scanner.nextLine(); 
        
        aluno.adicionarAvaliacao(avaliacao);
        System.out.println("Notas lançadas e salvas com sucesso!");
    }
    
    private static void lancarFrequencia() {
        System.out.println("\n=== LANÇAR FREQUÊNCIA ===");
        System.out.print("Número da turma: ");
        String numeroTurma = scanner.nextLine();
        
        Turma turma = Turma.buscarPorNumero(numeroTurma);
        if (turma == null) {
            System.out.println("Turma não encontrada!");
            return;
        }
        
        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();

        Aluno aluno = Aluno.buscarAlunoPorMatricula(matricula);
    
        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }
        
        if (!turma.getAlunosMatriculados().contains(matricula)) {
            System.out.println("Aluno não está matriculado nesta turma!");
            return;
        }
        
        Frequencia frequencia = new Frequencia(matricula, turma);
        
        System.out.print("Total de presenças (de " + frequencia.getTotalAulas() + " aulas): ");
        int presencas = scanner.nextInt();
        scanner.nextLine();
        
        try {
            frequencia.setPresencas(presencas);
            aluno.adicionarFrequencia(frequencia); 
            System.out.println("Frequência lançada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void gerarRelatorios() {
        System.out.println("\n=== GERAR RELATÓRIOS ===");
        System.out.println("1. Relatório por Turma");
        System.out.println("2. Relatório por Professor");
        System.out.println("3. Boletim por Aluno");
        System.out.print("Escolha uma opção: ");
        
        int opcao = scanner.nextInt();
        scanner.nextLine();
        
        switch (opcao) {
            case 1:
                relatorioPorTurma();
                break;
            case 2:
                relatorioPorProfessor();
                break;
            case 3:
                boletimPorAluno();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void salvarDados(){}
}