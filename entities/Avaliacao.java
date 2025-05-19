package entities;

import entities.Turma.TipoAvaliacao;

public class Avaliacao {
    private String matriculaAluno;
    private String codigoTurma;
    private Double p1, p2, p3, listas, seminario;
    
    public Avaliacao(String matriculaAluno, String codigoTurma) {
        this.matriculaAluno = matriculaAluno;
        this.codigoTurma = codigoTurma;
    }
    
    public double calcularMedia(TipoAvaliacao tipo) {
        if (tipo == TipoAvaliacao.MEDIA_SIMPLES) {
            return (p1 + p2 + p3) / 3;
        } else {
            return (p1 + (p2 * 2) + (p3 * 3) + listas + seminario) / 8;
        }
    }

    public String getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(String matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(String codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    public Double getP1() {
        return p1;
    }

    public void setP1(Double p1) {
        this.p1 = p1;
    }

    public Double getP2() {
        return p2;
    }

    public void setP2(Double p2) {
        this.p2 = p2;
    }

    public Double getP3() {
        return p3;
    }

    public void setP3(Double p3) {
        this.p3 = p3;
    }

    public Double getListas() {
        return listas;
    }

    public void setListas(Double listas) {
        this.listas = listas;
    }

    public Double getSeminario() {
        return seminario;
    }

    public void setSeminario(Double seminario) {
        this.seminario = seminario;
    }
}