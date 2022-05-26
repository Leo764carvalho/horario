package src.br.com.rsa.horarios;

import java.io.Serializable;
import src.br.com.rsa.horarios.coloracao.Cor;

/**
 * Implementa a classe abstrata Cor. No programa de Hor?rios, cada Horario
 * corresponde a uma cor.
 *
 * @author felipe
 *
 */
public class Horario extends Cor implements Serializable {

    private int hora;
    private DiasDaSemana dia;

    public Horario() {
        this(null, 0, null);
    }

    public Horario(String nome, int hora, DiasDaSemana dia) {
        super(nome);
        this.hora = hora;
        this.dia = dia;
    }

    public DiasDaSemana getDia() {
        return dia;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public void setDia(DiasDaSemana dia) {
        this.dia = dia;
    }

    public boolean choque(Cor c) {
        if (c instanceof Horario) {
            Horario h = (Horario) c;

            if (this.dia == null || h.dia == null) {
                return false;
            } else {
                int diferença = Math.abs(this.hora - h.hora);
                // Causara choque se forem Hor?rios consecutivos do mesmo dia
                return (this.dia.equals(h.dia)) && (diferença <= 1);
            }
        } else {
            return false;
        }
    }

    public boolean desejavelAoColorir(Cor cor) {
        if (cor instanceof Horario) {
            Horario h = (Horario) cor;
            return this.dia.equals(h.dia);
        } else {
            return false;
        }
    }
}
