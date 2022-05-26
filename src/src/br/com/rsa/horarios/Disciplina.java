package src.br.com.rsa.horarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Cada disciplina possui um nome, um professor e uma ou mais turmas associadas.
 *
 * @author felipe
 *
 */
public class Disciplina implements Serializable{

    private String nome;
    private Professor professor;
    private List<Turma> turmas;
    private int aulas;

    public Disciplina(String nome, Professor professor, Turma turma) {
        this(nome, professor, new ArrayList<Turma>());
        this.turmas.add(turma);
    }

    public Disciplina(String nome, Professor professor, List<Turma> turmas) {
        this.nome = nome;
        this.professor = professor;
        this.turmas = turmas;
        this.aulas = 1;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getAulas(){
        return aulas;
    }
    
    public void setAulas(int aulas){
        this.aulas = aulas;
    }

    @Override
    public String toString() {
        // return nome + " (" + professor.toString() + ") " + turmas;
        return nome;
    }

    /**
     * Poderá ter choque se o professor for o mesmo ou se alguma das turmas for
     * a mesma.
     *
     * @param d a disciplina a ser comparada.
     * @return true se houver choque e false caso contrário.
     */
    public boolean choque(Disciplina d) {
        if (this.professor.equals(d.professor)) {
            return true;
        } else {
            for (Turma t : this.turmas) {
                if (d.turmas.contains(t)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Duas disciplinas são iguais se tiverem o mesmo professor e a(s) mesma(s)
     * turma(s).
     *
     * @param disc a disciplina a ser comparada.
     * @return true se os professores e turmas forem os mesmos e false caso
     * contrário.
     */
    public boolean equals(Disciplina disc) {
        boolean mesmoProfessor = this.professor.equals(disc.professor);
        boolean mesmaTurma = this.turmas.containsAll(disc.turmas) && disc.turmas.containsAll(this.turmas);
        return mesmoProfessor && mesmaTurma;
    }
}
