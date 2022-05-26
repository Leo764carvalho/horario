package src.br.com.rsa.horarios.main;

import br.com.rsa.horarios.principal.tela_Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import src.br.com.rsa.horarios.Aula;
import src.br.com.rsa.horarios.Curso;
import src.br.com.rsa.horarios.DiasDaSemana;
import src.br.com.rsa.horarios.Disciplina;
import src.br.com.rsa.horarios.Horario;
import src.br.com.rsa.horarios.Professor;
import src.br.com.rsa.horarios.Turma;
import src.br.com.rsa.horarios.coloracao.CoresInsuficientesException;
import src.br.com.rsa.horarios.coloracao.GrafoColorido;

public class Main {
    
    public static void main(String[] args){
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tela_Principal().setVisible(true);
            }
        });
    }
    
	public static void mano(String[] args) {
		System.out.println(
				"Digite o n�mero dos cursos para os quais voc� deseja gerar o Horario. Digite 0 para gerar o Horario");
		System.out.println(
				"1 - Computa��o 2017\n2 - Servi�o Social\n3 - Computa��o 2018\n4 - Direito (Tarde)\n5 - Direito (Noite)");

		Scanner in = new Scanner(System.in);

		List<Curso> cursos = new ArrayList<Curso>();
		int i;
		do {
			i = in.nextInt();
			switch (i) {
			case 1:
				cursos.add(cursoComputacao2017(HorarioNoite()));
				break;
			case 2:
				cursos.add(cursoServicoSocial());
				break;
			case 3:
				cursos.add(cursoComputacao2018());
				break;
			case 4:
				cursos.add(cursoDireito(HorarioTarde()));
				break;
			case 5:
				cursos.add(cursoDireito(HorarioNoite()));
				break;
			case 6:
				cursos.add(cursoComputacao2017(HorarioTarde()));
				break;
			default:
				i = 0;
			}
		} while (i != 0);

		in.close();

		long inicio = System.currentTimeMillis();

		teste(cursos);

		long fim = System.currentTimeMillis();

		System.out.println("Conclu�do em " + (fim - inicio) + "ms");
	}

	private static void teste(List<Curso> cursos) {

		if (cursos != null && cursos.size() > 0) {

			Map<Aula, Horario> coloracao;
			try {
				GrafoColorido<Aula, Horario> grafo = new GrafoColorido<Aula, Horario>(cursos.get(0).getAulas());
				for (int i = 1; i < cursos.size(); i++) {
					grafo.addN�s(cursos.get(i).getAulas());
				}
				grafo.colorir();
				coloracao = grafo.getcoloracao();

				for (Curso c : cursos) {
					c.imprimeHorario(coloracao);
				}
			} catch (CoresInsuficientesException e) {
				System.err.println("Ocorreu um erro: " + e.getMessage());
			}
		}
	}

	private static Curso cursoServicoSocial() {

		Curso curso = new Curso("Servi?o Social", HorarioNoite());

		// Horarios especiais
		List<Horario> HorarioEstagio = new ArrayList<Horario>();
		HorarioEstagio.add(new Horario("Qua.0", 3, null));
		HorarioEstagio.add(new Horario("Qui.0", 3, null));

		// Professores
		Professor marcos_ant�nio = new Professor("Marcos Ant�nio");
		Professor mayara = new Professor("Mayara");
		Professor maria_merc�s = new Professor("Maria Merc�s");
		Professor evilasio = new Professor("Evilasio");
		Professor maria_jos� = new Professor("Maria Jos�");
		Professor ortiz = new Professor("Ortiz");
		Professor marciana = new Professor("Marciana");
		Professor leda = new Professor("Leda");
		Professor �ngela = new Professor("�ngela");
		Professor rem�dios = new Professor("Rem�dios");
		Professor jackeline = new Professor("Jackeline");
		Professor mardila = new Professor("Mardila");
		Professor socorro = new Professor("Socorro");

		// Turmas
		List<Turma> turma1 = new ArrayList<Turma>();
		List<Turma> turma3 = new ArrayList<Turma>();
		List<Turma> turma5 = new ArrayList<Turma>();
		List<Turma> turma7 = new ArrayList<Turma>();
		List<Turma> turma8 = new ArrayList<Turma>();
		List<Turma> turma1_3 = new ArrayList<Turma>();
		turma1.add(new Turma("Servi�o Social 1�P"));
		turma3.add(new Turma("Servi�o Social 3�P"));
		turma5.add(new Turma("Servi�o Social 5�P"));
		turma7.add(new Turma("Servi�o Social 7�P"));
		turma8.add(new Turma("Servi�o Social 8�P"));
		turma1_3.add(turma1.get(0));
		turma1_3.add(turma3.get(0));

		// Disciplinas
		Disciplina tSocI = new Disciplina("Teorias Sociol�gicas I", maria_merc�s, turma1);
		Disciplina tFilI = new Disciplina("Teorias Filos�ficas I", marcos_ant�nio, turma1);
		Disciplina metodologia = new Disciplina("Metodologia", mayara, turma1);
		Disciplina psicologiaGeral = new Disciplina("Psicologia Geral", evilasio, turma1);
		curso.addAulas(tSocI, 2);
		curso.addAulas(tFilI, 2);
		curso.addAulas(metodologia, 2);
		curso.addAulas(psicologiaGeral, 2);

		Disciplina quest�o = new Disciplina("Quest�o Social", mardila, turma1_3);
		Disciplina libras = new Disciplina("LIBRAS", socorro, turma1_3);
		curso.addAulas(quest�o, 1);
		curso.addAulas(libras, 1);

		Disciplina acumula��o = new Disciplina("Acumula��o Capitalista", leda, turma3);
		Disciplina direito = new Disciplina("Direito e Legisla��o", ortiz, turma3);
		Disciplina psicologiaSocial = new Disciplina("Psicologia Social", evilasio, turma3);
		Disciplina ci�nciaPol�tica = new Disciplina("Ci�ncia Pol�tica", maria_jos�, turma3);
		curso.addAulas(acumula��o, 2);
		curso.addAulas(direito, 2);
		curso.addAulas(psicologiaSocial, 2);
		curso.addAulas(ci�nciaPol�tica, 2);

		Disciplina processoI = new Disciplina("Processo de Trabalho I", �ngela, turma5);
		Disciplina administra��o = new Disciplina("Administra��o", leda, turma5);
		Disciplina funtamentosII = new Disciplina("Fundamentos II", rem�dios, turma5);
		Disciplina planejamento = new Disciplina("Planejamento Social", marciana, turma5);
		Disciplina pol�ticaII = new Disciplina("Pol�tica Social II", jackeline, turma5);
		curso.addAulas(processoI, 2);
		curso.addAulas(administra��o, 2);
		curso.addAulas(funtamentosII, 2);
		curso.addAulas(planejamento, 2);
		curso.addAulas(pol�ticaII, 2);

		Disciplina �tica = new Disciplina("�tica", marciana, turma7);
		Disciplina processoIII = new Disciplina("Processo de Trabalho III", �ngela, turma7);
		Disciplina pesquisaII = new Disciplina("Pesquisa Social II", rem�dios, turma7);
		Disciplina estagioI = new Disciplina("Estagio I", mardila, turma7);
		curso.addAulas(�tica, 2);
		curso.addAulas(processoIII, 2);
		curso.addAulas(pesquisaII, 2);
		curso.addAulas(estagioI, 1, HorarioEstagio);

		Disciplina seminario = new Disciplina("Seminario de Praticas", jackeline, turma8);
		Disciplina estagioII = new Disciplina("Estagio II", mardila, turma8);
		Disciplina tcc = new Disciplina("TCC", jackeline, turma8);
		curso.addAulas(seminario, 1);
		curso.addAulas(estagioII, 1, HorarioEstagio);
		curso.addAulas(tcc, 1);

		return curso;
	}

	private static Curso cursoComputacao2017(List<Horario> Horarios) {

		Curso curso = new Curso("Computa��o", Horarios);

		List<Horario> sexta = curso.getHorarios(DiasDaSemana.SEXTA);

		for (Horario h : sexta)
			giovane.addIndesejavel(h);

		// Turmas
		List<Turma> turma1 = new ArrayList<Turma>();
		List<Turma> turma2 = new ArrayList<Turma>();
		turma1.add(new Turma("Computa??o 3�P"));
		turma2.add(new Turma("Computa??o 6�P"));

		// Disciplinas (3� Per�odo)
		Disciplina lpi = new Disciplina("LPI", felipe, turma1);
		Disciplina estruturas = new Disciplina("Estruturas de Dados", anatiel, turma1);
		Disciplina calculo = new Disciplina("Calculo", ant�nio, turma1);
		Disciplina geometria = new Disciplina("Geometria", ant�nio, turma1);
		Disciplina engenharia = new Disciplina("Engenharia de Software", �der, turma1);

		// Aulas (3� Per�odo)
		curso.addAulas(lpi, 2);
		curso.addAulas(estruturas, 2);
		curso.addAulas(calculo, 2);
		curso.addAulas(geometria, 2);
		curso.addAulas(engenharia, 2);

		// Disciplinas (6� Per�odo)
		Disciplina lpiv = new Disciplina("LPIV", giovane, turma2);
		Disciplina ihc = new Disciplina("IHC", giovane, turma2);
		Disciplina redes = new Disciplina("Redes", anatiel, turma2);
		Disciplina circuitos = new Disciplina("Circuitos", �der, turma2);

		// Aulas (6� Per�odo)
		curso.addAulas(lpiv, 2);
		curso.addAulas(ihc, 2);
		curso.addAulas(redes, 3);
		curso.addAulas(circuitos, 2);

		return curso;
	}

	private static Curso cursoComputacao2018() {

		Curso curso = new Curso("Computa��o", HorarioNoite());

		// Professores
		Professor felipe = new Professor("Felipe");
		Professor �der = new Professor("�der");
		Professor anatiel = new Professor("Anatiel");
		Professor ant�nio = new Professor("Ant�nio");
		Professor giovane = new Professor("Giovane");

		// Turmas
		List<Turma> turma1 = new ArrayList<Turma>();
		List<Turma> turma2 = new ArrayList<Turma>();
		turma1.add(new Turma("Computa��o 4�P"));
		turma2.add(new Turma("Computa��o 7�P"));

		// Horarios
		List<Horario> sexta = curso.getHorarios(DiasDaSemana.SEXTA);

		// Disciplinas (4� Per�odo)
		Disciplina lpii = new Disciplina("Linguagens de Programa��o II", giovane, turma1);
		Disciplina estat�stica = new Disciplina("Probabilidade e Estat�stica", ant�nio, turma1);
		Disciplina bd = new Disciplina("Banco de Dados", giovane, turma1);
		Disciplina grafos = new Disciplina("Teoria dos Grafos", felipe, turma1);
		Disciplina calculo = new Disciplina("Calculo Num�rico", ant�nio, turma1);

		// Aulas (4� Per�odo)
		curso.addAulas(lpii, 2);
		curso.addAulas(estat�stica, 2);
		curso.addAulas(bd, 2);
		curso.addAulas(grafos, 2);
		curso.addAulas(calculo, 2);

		// Disciplinas (7� Per�odo)
		Disciplina ia = new Disciplina("Intelig�ncia Artificial", anatiel, turma2);
		Disciplina analise = new Disciplina("analise e Projeto de Sistemas", anatiel, turma2);
		Disciplina tcci = new Disciplina("TCC I", �der, turma2);
		Disciplina algoritmos = new Disciplina("analise e Projeto de Algoritmos", felipe, turma2);

		// Aulas (6� Per�odo)
		curso.addAulas(ia, 2);
		curso.addAulas(analise, 2);
		curso.addAulas(tcci, 2);
		curso.addAulas(algoritmos, 2);

		// Horarios indesejaveis
		giovane.addIndesejavel(sexta.get(0));
		giovane.addIndesejavel(sexta.get(1));

		return curso;
	}

	// Professores Computa��o
	private static Professor felipe = new Professor("Felipe");
	private static Professor �der = new Professor("�der");
	private static Professor anatiel = new Professor("Anatiel");
	private static Professor ant�nio = new Professor("Ant�nio");
	private static Professor giovane = new Professor("Giovane");

	// Professores Direito
	private static Professor aline = new Professor("Aline Hip�lito");
	private static Professor merces = new Professor("Merces");
	private static Professor mariaJos� = new Professor("Maria Jos�");
	private static Professor marinalva = new Professor("Marinalva");
	private static Professor hidelmar = new Professor("Hidelmar Fontes");
	private static Professor marco = new Professor("Marco Ant�nio");
	private static Professor mafra = new Professor("Mafra");
	private static Professor mariaCarmo = new Professor("Maria do Carmo");
	private static Professor fatima = new Professor("Fatima Miranda");
	private static Professor fabr�cio = new Professor("Fabr�cio Bezerra");
	private static Professor xavier = new Professor("Xavier");
	private static Professor �rica = new Professor("�rica");
	private static Professor herval = new Professor("Herval Ribeiro");
	private static Professor eloise = new Professor("Eloise");
	private static Professor aleksandro = new Professor("Aleksandro");
	private static Professor anaKarla = new Professor("Ana Karla");
	private static Professor adriano = new Professor("Adriano");
	private static Professor andreya = new Professor("Andreya");
	private static Professor elves = new Professor("Elves");
	private static Professor rodrigo = new Professor("Rodrigo");
	private static Professor francisca = new Professor("Francisca");
	private static Professor cristiane = new Professor("Cristiane");

	private static Curso cursoDireito(List<Horario> Horarios) {

		Curso curso = new Curso("Direito", Horarios);

		// Turmas
		List<Turma> turma1 = new ArrayList<Turma>();
		List<Turma> turma2 = new ArrayList<Turma>();
		List<Turma> turma3 = new ArrayList<Turma>();
		List<Turma> turma4 = new ArrayList<Turma>();
		List<Turma> turma5 = new ArrayList<Turma>();
		List<Turma> turma6 = new ArrayList<Turma>();
		List<Turma> turma7 = new ArrayList<Turma>();
		List<Turma> turma8 = new ArrayList<Turma>();
		List<Turma> turma9 = new ArrayList<Turma>();
		List<Turma> turma10 = new ArrayList<Turma>();

		turma1.add(new Turma("Direito 1�NM"));
		turma2.add(new Turma("Direito 2�NM"));
		turma3.add(new Turma("Direito 3�NM"));
		turma4.add(new Turma("Direito 4�NM"));
		turma5.add(new Turma("Direito 5�NM"));
		turma6.add(new Turma("Direito 6�NM"));
		turma7.add(new Turma("Direito 7�NM"));
		turma8.add(new Turma("Direito 8�NM"));
		turma9.add(new Turma("Direito 9�NM"));
		turma10.add(new Turma("Direito 10�NM"));

		// Disciplinas (1� Per�odo)
		Disciplina introdu��o = new Disciplina("Introdu��o ao Direito", aline, turma1);
		Disciplina sociologia = new Disciplina("Sociologia Geral e Jur�dica", merces, turma1);
		Disciplina metodologia = new Disciplina("Metodologia de Pesquisa", mariaJos�, turma1);
		Disciplina portugu�s = new Disciplina("Portugu�s Jur�dico", marinalva, turma1);
		Disciplina economia = new Disciplina("Economia Pol�tica", hidelmar, turma1);
		Disciplina filosofia = new Disciplina("Filosofia Geral e Jur�dica", marco, turma1);
		Disciplina antropologia = new Disciplina("Antropologia", mafra, turma1);

		// Aulas (1� Per�odo)
		curso.addAulas(introdu��o, 2);
		curso.addAulas(sociologia, 2);
		curso.addAulas(metodologia, 1);
		curso.addAulas(portugu�s, 1);
		curso.addAulas(economia, 1);
		curso.addAulas(filosofia, 2);
		curso.addAulas(antropologia, 1);

		// Disciplinas (2� Per�odo)
		Disciplina hermenutica = new Disciplina("Hermenutica Jur�dica", mariaCarmo, turma2);
		Disciplina direitoConsI = new Disciplina("Direito Constitucional I", fatima, turma2);
		Disciplina tgp = new Disciplina("T.G.P.", fabr�cio, turma2);
		Disciplina tge = new Disciplina("T.G.E.", xavier, turma2);
		Disciplina psicologiaJur�dica = new Disciplina("Psicologia Jur�dica", �rica, turma2);
		Disciplina cidadania = new Disciplina("Cidadania e Direitos Humanos", mariaJos�, turma2);

		// Aulas (2� Per�odo)
		curso.addAulas(hermenutica, 2);
		curso.addAulas(direitoConsI, 2);
		curso.addAulas(tgp, 2);
		curso.addAulas(tge, 2);
		curso.addAulas(psicologiaJur�dica, 1);
		curso.addAulas(cidadania, 1);

		// Disciplinas (3� Per�odo)
		Disciplina direitoPenalI = new Disciplina("Direito Penal I", herval, turma3);
		Disciplina direitoConsII = new Disciplina("Direito Constitucional II", eloise, turma3);
		Disciplina direitoTrabI = new Disciplina("Direito do Trabalho I", anaKarla, turma3);
		Disciplina direitoCivilI = new Disciplina("Direito Civil I", aleksandro, turma3);
		Disciplina resolu��o = new Disciplina("Resolu��o de Conflitos", mariaCarmo, turma3);
		Disciplina criminologia = new Disciplina("Criminologia", adriano, turma3);

		// Aulas (3� Per�odo)
		curso.addAulas(direitoPenalI, 2);
		curso.addAulas(direitoConsII, 2);
		curso.addAulas(direitoTrabI, 2);
		curso.addAulas(direitoCivilI, 2);
		curso.addAulas(resolu��o, 1);
		curso.addAulas(criminologia, 1);

		// Disciplinas (4� Per�odo)
		Disciplina direitoAdmI = new Disciplina("Direito Administrativo I", andreya, turma4);
		Disciplina direitoPenalII = new Disciplina("Direito Penal II", elves, turma4);
		Disciplina procCivilI = new Disciplina("Proc. Civil I", rodrigo, turma4);
		Disciplina direitoCivilII = new Disciplina("Direito Civil II", francisca, turma4);
		Disciplina direitoAmbiental = new Disciplina("Direito Ambiental", xavier, turma4);
		Disciplina direitoTrabII = new Disciplina("Direito do Trabalho II", cristiane, turma4);

		// Aulas (4� Per�odo)
		curso.addAulas(direitoAdmI, 2);
		curso.addAulas(direitoPenalII, 2);
		curso.addAulas(procCivilI, 2);
		curso.addAulas(direitoCivilII, 2);
		curso.addAulas(direitoAmbiental, 1);
		curso.addAulas(direitoTrabII, 1);

		return curso;
	}

	public static List<Horario> HorarioTarde() {
		List<Horario> cores = new ArrayList<Horario>();
		cores.add(new Horario("Segunda, 14:00", 0, DiasDaSemana.SEGUNDA));
		cores.add(new Horario("Segunda, 16:00", 1, DiasDaSemana.SEGUNDA));
		cores.add(new Horario("Ter�a, 14:00", 0, DiasDaSemana.TERCA));
		cores.add(new Horario("Ter�a, 16:00", 1, DiasDaSemana.TERCA));
		cores.add(new Horario("Quarta, 14:00", 0, DiasDaSemana.QUARTA));
		cores.add(new Horario("Quarta, 16:00", 1, DiasDaSemana.QUARTA));
		cores.add(new Horario("Quinta, 14:00", 0, DiasDaSemana.QUINTA));
		cores.add(new Horario("Quinta, 16:00", 1, DiasDaSemana.QUINTA));
		cores.add(new Horario("Sexta, 14:00", 0, DiasDaSemana.SEXTA));
		cores.add(new Horario("Sexta, 16:00", 1, DiasDaSemana.SEXTA));

		return cores;
	}

	public static List<Horario> HorarioNoite() {
		List<Horario> cores = new ArrayList<Horario>();
		cores.add(new Horario("Segunda, 18:00", 2, DiasDaSemana.SEGUNDA));
		cores.add(new Horario("Segunda, 20:00", 3, DiasDaSemana.SEGUNDA));
		cores.add(new Horario("Ter�a, 18:00", 2, DiasDaSemana.TERCA));
		cores.add(new Horario("Ter�a, 20:00", 3, DiasDaSemana.TERCA));
		cores.add(new Horario("Quarta, 18:00", 2, DiasDaSemana.QUARTA));
		cores.add(new Horario("Quarta, 20:00", 3, DiasDaSemana.QUARTA));
		cores.add(new Horario("Quinta, 18:00", 2, DiasDaSemana.QUINTA));
		cores.add(new Horario("Quinta, 20:00", 3, DiasDaSemana.QUINTA));
		cores.add(new Horario("Sexta, 18:00", 2, DiasDaSemana.SEXTA));
		cores.add(new Horario("Sexta, 20:00", 3, DiasDaSemana.SEXTA));

		return cores;
	}
}