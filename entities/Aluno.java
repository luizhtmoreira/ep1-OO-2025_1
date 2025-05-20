package entities;

import java.util.ArrayList;
import java.util.List;


public abstract class Aluno {
    private static List<Aluno> todosAlunos = new ArrayList<>();

    private String nome;
    private String matricula;
    private String curso;
    private List<String> disciplinasMatriculadas = new ArrayList<>();
    private List<String> disciplinasConcluidas = new ArrayList<>();

    public Aluno(String nome, String matricula, String curso) {
        if(matricula == null || !matricula.matches("\\d{9}")){
            throw new IllegalArgumentException("Matrícula deve ter exatamente 9 dígitos");
        }
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
    }
    
    public static void cadastrar(Aluno aluno) {
        if (todosAlunos.stream().anyMatch(a -> a.matricula.equals(aluno.matricula))) {
            throw new IllegalArgumentException("Matrícula já existe!");
        }
        todosAlunos.add(aluno);
    }
    
    public static List<Aluno> listarTodos() {
        return new ArrayList<>(todosAlunos);
    }

    public static Aluno buscarAlunoPorMatricula(String matricula){
        for(Aluno aluno : todosAlunos){
            if (aluno.getMatricula().equals(matricula))
            return aluno;
            }
        return null;
    }

    public abstract void matricularDisciplina(String codigoDisciplina);
    public abstract void trancarDisciplina(String codigoDisciplina);

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }    

    public List<String> getDisciplinasMatriculadas() {
        return disciplinasMatriculadas;
    }

    public List<String> getDisciplinasConcluidas() {
        return disciplinasConcluidas;
    }    

    public void adicionarDisciplinaConcluida(String codigoDisciplina) {
        if (!disciplinasConcluidas.contains(codigoDisciplina)) {
            disciplinasConcluidas.add(codigoDisciplina);
        }
    }

    public boolean verificarPreRequisitos(List<String> preRequisitos) {
        return disciplinasConcluidas.containsAll(preRequisitos);
    }
}
