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
        carregarDisciplinas(); 
        carregarAlunos();
        carregarTurmas();
        carregarAvaliacoes();
        carregarFrequencias();
    }

    private static void carregarAlunos() {
        if (!new File(ARQ_ALUNOS).exists()) return;
        
        try (BufferedReader br = new BufferedReader(new FileReader(ARQ_ALUNOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";", -1);
                
                if (dados.length < 6) {
                    System.out.println("Linha inválida em alunos.txt: " + linha);
                    continue;
                }

                String tipo = dados[3];
                Aluno aluno = tipo.equals("NORMAL") ? 
                    new AlunoNormal(dados[0], dados[1], dados[2]) :
                    new AlunoEspecial(dados[0], dados[1], dados[2]);

                if (!dados[4].isEmpty()) {
                    aluno.getDisciplinasMatriculadas().addAll(
                        Arrays.asList(dados[4].split(","))
                    );
                }

                if (!dados[5].isEmpty()) {
                    aluno.getDisciplinasConcluidas().addAll(
                        Arrays.asList(dados[5].split(","))
                    );
                }

                if (Aluno.buscarAlunoPorMatricula(aluno.getMatricula()) == null) {
                    Aluno.cadastrar(aluno);
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO ao carregar alunos: " + e.getMessage());
        }
    }

    private static void carregarDisciplinas() {
        if (!new File(ARQ_DISCIPLINAS).exists()) return;
        
        try (BufferedReader br = new BufferedReader(new FileReader(ARQ_DISCIPLINAS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";", -1);
                
                if (dados.length < 5) {
                    System.out.println("Linha inválida em disciplinas.txt: " + linha);
                    continue;
                }

                Disciplina disciplina = new Disciplina(
                    dados[0], 
                    dados[1], 
                    Integer.parseInt(dados[2]), 
                    Integer.parseInt(dados[3])
                );

                if (!dados[4].isEmpty()) {
                    Arrays.asList(dados[4].split(","))
                        .forEach(disciplina::addPreRequisito);
                }

                if (Disciplina.buscarPorCodigo(disciplina.getCodigo()) == null) {
                    Disciplina.cadastrar(disciplina);
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO ao carregar disciplinas: " + e.getMessage());
        }
    }
}