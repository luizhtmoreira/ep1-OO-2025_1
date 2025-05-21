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
    public TipoTurma tipo;
    private TipoAvaliacao tipoAvaliacao;
    private List<String> matriculas = new ArrayList<>();

    public Turma(String numeroTurma, String codigoDisciplina, int semestre, String sala, String horario, int capacidade, TipoTurma tipo, TipoAvaliacao tipoAvaliacao) {
        this.numeroTurma = numeroTurma;
        this.codigoDisciplina = codigoDisciplina;
        this.semestre = semestre;
        this.sala = sala;
        this.horario = horario;
        this.capacidade = capacidade;
        this.tipo = tipo;
        this.tipoAvaliacao = tipoAvaliacao;
    }

    public static void cadastrar(Turma turma) {
        todasTurmas.add(turma);
    }

    public static Turma buscarPorNumero(String numeroTurma) {
        return todasTurmas.stream()
                .filter(t -> t.numeroTurma.equals(numeroTurma))
                .findFirst()
                .orElse(null);
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

    public enum TipoAvaliacao { MEDIA_SIMPLES, MEDIA_PONDERADA }
    public enum TipoTurma { PRESENCIAL, REMOTA }

}