package entities;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private static List<Disciplina> todasDisciplinas = new ArrayList<>();
    
    private String codigo;
    private String nome;
    private int cargaHoraria;
    private int turmas;
    private List<String> preRequisitos = new ArrayList<>();

    public Disciplina(String codigo, String nome, int cargaHoraria, int turmas) {
        if (!codigo.matches("[A-Z]{3}\\d{4}")) {
            throw new IllegalArgumentException("Código deve ter 3 letras e 4 números (ex: FGA0158)");
        }
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser positiva");
        }
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.turmas = turmas;
    }

    public static void cadastrar(Disciplina disciplina) {
        if (todasDisciplinas.stream().anyMatch(d -> d.codigo.equals(disciplina.codigo))) {
            throw new IllegalArgumentException("Código já existe!");
        }
        todasDisciplinas.add(disciplina);
    }

    public static Disciplina buscarPorCodigo(String codigo) {
        for(Disciplina disciplina : todasDisciplinas){
            if (disciplina.getCodigo().equals(codigo))
            return disciplina;                
        }
        return null;
    }
    
    public void addPreRequisito(String codigoDisciplina) {
        preRequisitos.add(codigoDisciplina);
    }
    
    public String getCodigo() { 
        return codigo; 
    }
    public List<String> getPreRequisitos() { 
        return new ArrayList<>(preRequisitos); 
    }

    public int getTotalAulas() {
        return this.cargaHoraria;
    }
    
    public String getNome() {
        return this.nome;
    }

    public int getTurmas() {
        return this.turmas;
    }

    public static List<Disciplina> getTodasDisciplinas(){
        return todasDisciplinas;
    }

}    