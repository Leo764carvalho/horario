/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rsa.horarios.geracao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.br.com.rsa.horarios.Aula;
import src.br.com.rsa.horarios.Curso;
import src.br.com.rsa.horarios.DiasDaSemana;
import src.br.com.rsa.horarios.Disciplina;
import src.br.com.rsa.horarios.Horario;
import src.br.com.rsa.horarios.Professor;
import src.br.com.rsa.horarios.Turma;
import src.br.com.rsa.horarios.coloracao.CoresInsuficientesException;
import src.br.com.rsa.horarios.coloracao.GrafoColorido;

/**
 *
 * @author felip
 */
public class HorarioSingleton implements Serializable {

    private static HorarioSingleton singleton = null;

    private final List<Curso> cursos;
    private final List<Turma> turmas;
    private final List<Professor> professores;
    private final List<Disciplina> disciplinas;
    private final List<Horario> turnoTarde;
    private final List<Horario> turnoNoite;
    private Map<Aula, Horario> coloracao;

    private HorarioSingleton() {
        this.cursos = new ArrayList<>();
        this.turmas = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
        this.turnoTarde = turnoTarde();
        this.turnoNoite = turnoNoite();
        coloracao = null;
    }

    public static HorarioSingleton getInstance() {
        if (singleton == null) {
            try {
                FileInputStream file = new FileInputStream("horario.dat");
                ObjectInputStream in = new ObjectInputStream(file);

                singleton = (HorarioSingleton) in.readObject();
            } catch (FileNotFoundException ex) {
                singleton = new HorarioSingleton();
            } catch (Exception ex) {
                Logger.getLogger(HorarioSingleton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return singleton;
    }

    public void save() throws IOException {
        FileOutputStream file = new FileOutputStream("horario.dat");
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(HorarioSingleton.getInstance());
        out.flush();
        file.flush();
        out.close();
        file.close();
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public List<Horario> getTurnoTarde() {
        return turnoTarde;
    }

    public List<Horario> getTurnoNoite() {
        return turnoNoite;
    }

    private List<Horario> turnoTarde() {
        ArrayList<Horario> turno = new ArrayList<>();

        turno.add(new Horario("Segunda, 14:00", 0, DiasDaSemana.SEGUNDA));
        turno.add(new Horario("Segunda, 16:00", 1, DiasDaSemana.SEGUNDA));
        turno.add(new Horario("Ter?a, 14:00", 0, DiasDaSemana.TERCA));
        turno.add(new Horario("Ter?a, 16:00", 1, DiasDaSemana.TERCA));
        turno.add(new Horario("Quarta, 14:00", 0, DiasDaSemana.QUARTA));
        turno.add(new Horario("Quarta, 16:00", 1, DiasDaSemana.QUARTA));
        turno.add(new Horario("Quinta, 14:00", 0, DiasDaSemana.QUINTA));
        turno.add(new Horario("Quinta, 16:00", 1, DiasDaSemana.QUINTA));
        turno.add(new Horario("Sexta, 14:00", 0, DiasDaSemana.SEXTA));
        turno.add(new Horario("Sexta, 16:00", 1, DiasDaSemana.SEXTA));

        return turno;
    }

    private List<Horario> turnoNoite() {
        ArrayList<Horario> turno = new ArrayList<>();

        turno.add(new Horario("Segunda, 18:00", 2, DiasDaSemana.SEGUNDA));
        turno.add(new Horario("Segunda, 20:00", 3, DiasDaSemana.SEGUNDA));
        turno.add(new Horario("Ter?a, 18:00", 2, DiasDaSemana.TERCA));
        turno.add(new Horario("Ter?a, 20:00", 3, DiasDaSemana.TERCA));
        turno.add(new Horario("Quarta, 18:00", 2, DiasDaSemana.QUARTA));
        turno.add(new Horario("Quarta, 20:00", 3, DiasDaSemana.QUARTA));
        turno.add(new Horario("Quinta, 18:00", 2, DiasDaSemana.QUINTA));
        turno.add(new Horario("Quinta, 20:00", 3, DiasDaSemana.QUINTA));
        turno.add(new Horario("Sexta, 18:00", 2, DiasDaSemana.SEXTA));
        turno.add(new Horario("Sexta, 20:00", 3, DiasDaSemana.SEXTA));

        return turno;
    }

    public Map<Aula, Horario> getColoracao() {
        return coloracao;
    }

    public void colore(Curso curso) throws CoresInsuficientesException, IOException {

        curso.getAulas().clear();

        for (Disciplina disc : curso.getDisciplinas()) {
            if (disc.getProfessor() != null && disc.getTurmas() != null && !disc.getTurmas().isEmpty()) {
                curso.addAulas(disc, disc.getAulas());
            }
        }

        GrafoColorido<Aula, Horario> grafo = new GrafoColorido<>(curso.getAulas());
        coloracao = grafo.colorir();
    }
}
