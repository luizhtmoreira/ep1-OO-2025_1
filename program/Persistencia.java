package program;


import entities.*;
import java.io.*;
import java.util.*;

public class Persistencia {
    
    private static final String DIRETORIO = "dados/";
    private static final String ARQ_ALUNOS = DIRETORIO + "alunos.txt";
    private static final String ARQ_DISCIPLINAS = DIRETORIO + "disciplinas.txt";
    private static final String ARQ_TURMAS = DIRETORIO + "turmas.txt";
    private static final String ARQ_AVALIACOES = DIRETORIO + "avaliacoes.txt";
    private static final String ARQ_FREQUENCIAS = DIRETORIO + "frequencias.txt";

    public static void carregarTodosDados() {
        new File(DIRETORIO).mkdirs();
        
        carregarAlunos();
        carregarDisciplinas();
        carregarTurmas();
        carregarAvaliacoes();
        carregarFrequencias();
    }

    private static void carregarAlunos() {
        if (!new File(ARQ_ALUNOS).exists()) return;
        
        try (BufferedReader br = new BufferedReader(new FileReader(ARQ_ALUNOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Aluno aluno = dados[3].equals("NORMAL") ? 
                    new AlunoNormal(dados[0], dados[1], dados[2]) :
                    new AlunoEspecial(dados[0], dados[1], dados[2]);
                
                if (!dados[4].isEmpty()) 
                    aluno.getDisciplinasMatriculadas().addAll(Arrays.asList(dados[4].split(",")));
                if (!dados[5].isEmpty())
                    aluno.getDisciplinasConcluidas().addAll(Arrays.asList(dados[5].split(",")));
                
                Aluno.cadastrar(aluno);
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
        }
    }
}