package entities;

public class AlunoNormal extends Aluno{

    public AlunoNormal(String nome, String matricula, String curso){
        super(nome, matricula, curso);
    }

    @Override
    public void matricularDisciplina(String codigoDisciplina){
        getDisciplinasMatriculadas().add(codigoDisciplina);
    }

    @Override
    public void trancarDisciplina(String codigoDisciplina){
        getDisciplinasMatriculadas().remove(codigoDisciplina);
    }
}
