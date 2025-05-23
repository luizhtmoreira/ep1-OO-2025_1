package entities;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private static List<Turma> todasTurmas = new ArrayList<>();

    private String numeroTurma;
    private String codigoDisciplina;
    private int semestre;
    private String sala;
    private String horario;
    private int capacidade;
    private TipoTurma tipo;
    private TipoAvaliacao tipoAvaliacao;
    private Professor professor;
    private List<String> matriculas = new ArrayList<>();

    public Turma(String numeroTurma, String codigoDisciplina, int semestre, String sala, String horario, int capacidade, TipoTurma tipo, TipoAvaliacao tipoAvaliacao, Professor professor) {
        this.numeroTurma = numeroTurma;
        this.codigoDisciplina = codigoDisciplina;
        this.semestre = semestre;
        this.sala = sala;
        this.horario = horario;
        this.capacidade = capacidade;
        this.tipo = tipo;
        this.tipoAvaliacao = tipoAvaliacao;
        this.professor = professor;
    }

    public static void cadastrar(Turma turma) {
        todasTurmas.add(turma);
    }

    public static Turma buscarPorNumero(String numeroTurma) {
        for (Turma turma : todasTurmas) {
            if (turma.numeroTurma.equals(numeroTurma)) {
                return turma; 
            }
        }
        return null; 
    }
    
    public boolean temVagas() {
        return matriculas.size() < capacidade;
    }

    public boolean matricularAluno(String matriculaAluno) {
        if (temVagas()) {
            matriculas.add(matriculaAluno);
            return true;
        }
        return false;
    }    

    public List<String> getAlunosMatriculados() {
        return new ArrayList<>(matriculas);
    }

    public static List<Turma> getTodasTurmas() {
        return todasTurmas;
    }

    public String getNumeroTurma() {
        return numeroTurma;
    }

    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public int getSemestre() {
        return semestre;
    }

    public String getSala() {
        return sala;
    }

    public String getHorario() {
        return horario;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public TipoTurma getTipo() {
        return tipo;
    }

    public TipoAvaliacao getTipoAvaliacao() {
        return tipoAvaliacao;
    }

    public List<String> getMatriculas() {
        return matriculas;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public enum TipoAvaliacao { MEDIA_SIMPLES, MEDIA_PONDERADA }
    public enum TipoTurma { PRESENCIAL, REMOTA }

}