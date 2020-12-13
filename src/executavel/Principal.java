package executavel;

import javax.swing.JOptionPane;

import controle.ControleFrutas;
import modelo.Fruta;

public class Principal {

	public static void main(String[] args) {

		ControleFrutas frutas = new ControleFrutas();

		String menu = "[1] Adicionar\n[2] Excluir\n[3] Listar\n[4] Info\n"
				+ "[5] Ler Frutas.txt\n[6] Salvar Frutas.txt\n"
				+ "[7] Salvar Frutas.obj\n[8] Ler Frutas.obj\n[0] Sair\n";

		int op = 1;

		while (op != 0) {

			try {
				op = Integer.parseInt(JOptionPane.showInputDialog(null, menu, "Menu", 3));
				switch (op) {
				case 1:
					JOptionPane.showMessageDialog(null, "Tenha certeza de carregar (ler) um arquivo antes!", "ATENÇÃO",
							1);
					JOptionPane.showMessageDialog(null, cad(frutas) ? "Fruta cadastrada!" : "Fruta não cadastrada!");
					break;
				case 2:
					JOptionPane.showMessageDialog(null, deleta(frutas) ? "Fruta deletada!" : "Falha ao deletar!");
					break;
				case 3:
					lista(frutas);
					break;
				case 4:
					info(frutas);
					break;
				case 5:
					frutas.readTxt();
					JOptionPane.showMessageDialog(null, "Frutas.txt carregado!");
					break;
				case 6:
					salvarTxt(frutas);
					break;
				case 7:
					salvarObj(frutas);
					break;
				case 8:
					frutas.readObj();
					JOptionPane.showMessageDialog(null, "Frutas.obj carregado!");
					break;
				default:
					break;
				}

			} catch (Exception e) {
				Object[] options = { "sim", "não" };
				int r = JOptionPane.showOptionDialog(null, "Deseja sair?", "Fechar aplicação",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
				if (r == 0)
					break;
			}
		}
	}

	public static boolean cad(ControleFrutas frutas) {
		String title = "Cadastrar Fruta";
		try {
			String nome = JOptionPane.showInputDialog(null, "Nome", title, 3);
			double peso = Double.parseDouble(JOptionPane.showInputDialog(null, "Peso médio", title, 3));
			double cal = Double.parseDouble(JOptionPane.showInputDialog(null, "Calorias", title, 3));
			Fruta fruta = new Fruta(nome, peso, cal);
			return frutas.add(fruta);
		} catch (Exception e) {
			return false;
		}
	}

	public static void lista(ControleFrutas frutas) {
		try {
			StringBuilder r = new StringBuilder();
			int tam = frutas.list().length;
			for (int i = 0; i < tam; i++) {
				r.append(i + ": " + frutas.list()[i] + "\n");
			}
			JOptionPane.showMessageDialog(null, "Lista de Frutas/Posição\n" + r.toString());
			return;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lista vazia!");
		}
	}

	public static boolean deleta(ControleFrutas frutas) {
		try {
			int position = Integer.parseInt(JOptionPane.showInputDialog(null, "Posição", "Excluir Fruta", 3));
			return frutas.delete(position);
		} catch (Exception e) {
			return false;
		}
	}

	public static void info(ControleFrutas frutas) {
		try {
			int position = Integer.parseInt(JOptionPane.showInputDialog(null, "Posição", "Info Fruta", 3));
			JOptionPane.showMessageDialog(null, frutas.inf(position).toString());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Posição inválida!");
		}
	}

	public static void salvarTxt(ControleFrutas frutas) {
		try {
			Object[] options = { "carregar", "continuar" };
			int r = JOptionPane.showOptionDialog(null, "Tenha certeza de carregar um arquivo antes!", "[!] ATENÇÃO [!]",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
			if (r == 0)
				return;
			frutas.saveTxt();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro no carregamento!");
		}
	}

	public static void salvarObj(ControleFrutas frutas) {
		try {
			Object[] options = { "carregar", "continuar" };
			int r = JOptionPane.showOptionDialog(null, "Tenha certeza de carregar um arquivo antes!", "[!] ATENÇÃO [!]",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
			if (r == 0)
				return;
			frutas.saveObj();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro no carregamento!");
		}
	}
}
