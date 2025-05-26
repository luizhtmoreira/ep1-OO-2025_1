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

    private static void carregarTurmas() {
        if (!new File(ARQ_TURMAS).exists()) return;
        
        try (BufferedReader br = new BufferedReader(new FileReader(ARQ_TURMAS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Turma turma = new Turma(
                    dados[0], dados[1], 
                    Integer.parseInt(dados[2]), 
                    dados[3].equals("null") ? null : dados[3], 
                    dados[4], 
                    Integer.parseInt(dados[5]), 
                    Turma.TipoTurma.valueOf(dados[6]), 
                    Turma.TipoAvaliacao.valueOf(dados[7]), 
                    new Professor(dados[8])
                );
                Turma.cadastrar(turma);
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar turmas: " + e.getMessage());
        }
    }

    private static void carregarAvaliacoes() {
        if (!new File(ARQ_AVALIACOES).exists()) return;
        
        try (BufferedReader br = new BufferedReader(new FileReader(ARQ_AVALIACOES))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Avaliacao avaliacao = new Avaliacao(dados[0], dados[1]);
                
                avaliacao.setP1(Double.parseDouble(dados[2]));
                avaliacao.setP2(Double.parseDouble(dados[3]));
                avaliacao.setP3(Double.parseDouble(dados[4]));
                avaliacao.setListas(Double.parseDouble(dados[5]));
                avaliacao.setSeminario(Double.parseDouble(dados[6]));
                
                Aluno aluno = Aluno.buscarAlunoPorMatricula(dados[0]);
                if (aluno != null) aluno.adicionarAvaliacao(avaliacao);
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar avaliações: " + e.getMessage());
        }
    }

    private static void carregarFrequencias() {
        if (!new File(ARQ_FREQUENCIAS).exists()) return;
        
        try (BufferedReader br = new BufferedReader(new FileReader(ARQ_FREQUENCIAS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Frequencia frequencia = new Frequencia(
                    dados[0], 
                    Turma.buscarPorNumero(dados[1])
                );
                frequencia.setPresencas(Integer.parseInt(dados[2]));
                
                Aluno aluno = Aluno.buscarAlunoPorMatricula(dados[0]);
                if (aluno != null) aluno.adicionarFrequencia(frequencia);
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar frequências: " + e.getMessage());
        }
    }
}