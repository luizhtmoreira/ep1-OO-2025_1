package entities;

public class Frequencia {
    private String matriculaAluno;
    private String codigoTurma;
    private int totalAulas;
    private int aulasPresentes;

    public Frequencia(String matriculaAluno, Turma turma) {
        Disciplina disciplina = Disciplina.buscarPorCodigo(turma.getCodigoDisciplina());
        this.totalAulas = disciplina.getTotalAulas();
        this.matriculaAluno = matriculaAluno;
        this.codigoTurma = turma.getNumeroTurma();
        this.aulasPresentes = 0;
    }    

public void setPresencas(int totalPresencas) {
        if (totalPresencas < 0 || totalPresencas > totalAulas) {
            throw new IllegalArgumentException("Presenças devem estar entre 0 e " + totalAulas);
        }
        this.aulasPresentes = totalPresencas;
    }

    public boolean isAprovado() {
        return ((double) aulasPresentes / totalAulas) >= 0.75;
    }

    public int getTotalAulas() { 
        return totalAulas; 
    }

    public int getAulasPresente() { 
        return aulasPresentes; 
    }

    public String getMatriculaAluno() { 
        return matriculaAluno; 
    }

    public String getCodigoTurma() { 
        return codigoTurma; 
    }    
}