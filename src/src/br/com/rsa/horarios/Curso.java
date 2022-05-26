package src.br.com.rsa.horarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Um curso contém uma lista de aulas e uma lista de horarios disponíveis. No
 * caso de cursos que funcionam em dois períodos, deve-se criar dois cursos
 * separadamente.
 *
 * @author felipe
 *
 */
public class Curso implements Serializable {

    private String nome;

    private List<Aula> aulas;

    private List<Horario> horarios;

    private List<Disciplina> disciplinas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    /**
     * Retorna os horarios correspondentes a determinado dia da semana
     *
     * @param dia O dia da semana
     * @return Uma lista de horarios do dia correspondentes
     */
    public List<Horario> getHorarios(DiasDaSemana dia) {
        List<Horario> lista = new ArrayList<Horario>();

        for (Horario h : horarios) {
            if (h.getDia().equals(dia)) {
                lista.add(h);
            }
        }

        return lista;
    }

    /**
     * Retorna a lista de turmas do curso.
     *
     * @return A lista de turmas do curso.
     */
    public List<Turma> getTurmas() {
        List<Turma> turmas = new ArrayList<Turma>();

        for (Aula a : aulas) {
            List<Turma> aux = a.getDisciplina().getTurmas();
            for (Turma t : aux) {
                if (!turmas.contains(t)) {
                    turmas.add(t);
                }
            }
        }

        return turmas;
    }

    public Curso(String nome, List<Aula> aulas, List<Horario> horarios) {
        super();
        this.nome = nome;
        this.aulas = aulas;
        this.horarios = horarios;
        this.disciplinas = new ArrayList<>();
    }

    public Curso(String nome, List<Horario> horarios) {
        this(nome, new ArrayList<Aula>(), horarios);
    }

    /**
     * Adiciona um determinado número e aulas de uma disciplina.
     *
     * @param disciplina A disciplina correspondente às aulas.
     * @param número O número de aulas a serem adicionadas.
     * @param horarios Os horarios das aulas.
     */
    public void addAulas(Disciplina disciplina, int número, List<Horario> horarios) {

        for (int i = 0; i < número; i++) {
            aulas.add(new Aula(disciplina, horarios));
        }
        for (Horario h : horarios) {
            if (!this.horarios.contains(h)) {
                this.horarios.add(h);
            }
        }
    }

    /**
     * Adiciona um determinado número e aulas de uma disciplina, usando o
     * horario padrão do curso.
     *
     * @param disciplina A disciplina das aulas.
     * @param número O número de aulas a serem adicionadas.
     */
    public void addAulas(Disciplina disciplina, int número) {
        addAulas(disciplina, número, horarios);
    }

    /**
     * Imprime o horario das aulas do curso com base na coloracao.
     *
     * @param coloracao A coloracao.
     */
    public void imprimeHorario(Map<Aula, Horario> coloracao) {
        for (int i = 0; i < nome.length(); i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.println(nome);
        for (int i = 0; i < nome.length(); i++) {
            System.out.print("=");
        }
        System.out.println();
        for (Turma t : getTurmas()) {
            imprimeHorario(t, coloracao);
        }
    }

    /**
     * Imprime o horario das aulas de uma turma com base na coloracao.
     *
     * @param turma A turma.
     * @param coloracao A coloracao.
     */
    public void imprimeHorario(Turma turma, Map<Aula, Horario> coloracao) {
        System.out.println(turma);
        for (Horario c : horarios) {
            System.out.println(c.getNome());
            for (Entry<Aula, Horario> par : coloracao.entrySet()) {
                if (par.getValue().equals(c)) {
                    if (par.getKey().getDisciplina().getTurmas().contains(turma)) {
                        System.out.println(par.getKey());
                    }
                }
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return this.nome;
    }

}
