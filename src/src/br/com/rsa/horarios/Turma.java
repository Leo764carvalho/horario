package src.br.com.rsa.horarios;

import java.io.Serializable;

/**
 * Uma turma é um grupo de alunos. Cada disciplina pode ter mais de uma turma.
 *
 * @author felipe
 *
 */
public class Turma implements Serializable {

    private String nome;

    private String sala;

    private int turno;

    public Turma(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

}
