package entities;

public class AlunoEspecial extends Aluno{

    public AlunoEspecial(String nome, String matricula, String curso){
        super(nome, matricula, curso);
    }

    @Override
    public void matricularDisciplina(String codigoDisciplina){
        if (getDisciplinasMatriculadas().size() >= 2) {
            throw new IllegalStateException("Aluno especial não pode se matricular em mais de 2 disciplinas");
        }
        getDisciplinasMatriculadas().add(codigoDisciplina);
    }

    @Override
    public void trancarDisciplina(String codigoDisciplina){
        getDisciplinasMatriculadas().remove(codigoDisciplina);
    }
}
