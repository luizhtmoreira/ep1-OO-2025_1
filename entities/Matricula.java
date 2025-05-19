package entities;

public class Matricula {
    private String matriculaAluno;
    private String numeroTurma;
    private boolean trancada;

    public Matricula(String matriculaAluno, String numeroTurma) {
        this.matriculaAluno = matriculaAluno;
        this.numeroTurma = numeroTurma;
        this.trancada = false;
    }

    public String getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(String matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public String getNumeroTurma() {
        return numeroTurma;
    }

    public void setNumeroTurma(String numeroTurma) {
        this.numeroTurma = numeroTurma;
    }

    public boolean isTrancada() {
        return trancada;
    }

    public void setTrancada(boolean trancada) {
        this.trancada = trancada;
    }
}