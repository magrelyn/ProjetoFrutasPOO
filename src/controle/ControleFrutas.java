package controle;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import modelo.Fruta;

public class ControleFrutas {

	private Fruta[] fruta;
	private int tam;

	public ControleFrutas() {
		super();
		this.fruta = new Fruta[10];
		this.tam = 0;
	}
	
	public void reset() {
		for (int i = 0; i < fruta.length; i++) {
			fruta[i] = null;
		}
		this.tam = 0;	
	}

	public boolean add(Fruta fruta) {
		for (int i = 0; i < this.fruta.length; i++) {
			if (this.fruta[i] == null) {
				this.fruta[i] = fruta;
				this.tam++;
				return true;
			}
		}
		return false;
	}

	public boolean delete(int position) {
		if (position <= this.tam && position > -1) {
			this.fruta[position] = null;
			this.tam--;
			return true;
		}
		return false;
	}

	public String[] list() {
		if (tam == 0) {
			return null;
		}
		String l[] = new String[this.tam];
		int cont = 0;
		for (int i = 0; i < this.fruta.length; i++) {
			if (this.fruta[i] != null) {
				l[cont] = this.fruta[i].toString();
				cont++;
			}
			if(cont == tam)
				break;
		}
		return l;
	}

	public Fruta inf(int position) {
		return ((position <= this.tam && position > -1) ? this.fruta[position] : null);
	}

	public void saveObj() {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/files/Frutas.obj"));
			for (int i = 0; i < this.fruta.length; i++) {
				if (this.fruta[i] != null)
					output.writeObject(this.fruta[i]);
			}
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("\nArquivo não encontrado!\n");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void readObj() {
		this.reset();
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/files/Frutas.obj"));
			while (true) { // o final do arquivo gera exceção EOFException
				// ler o registro como objeto
				Fruta f = (Fruta) input.readObject(); // casting
				this.add(f); 
			}	
		} catch (EOFException e) {
			return;
		} catch (FileNotFoundException e) {
			System.out.println("\nArquivo não encontrado!\n");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("\nErro no casting!\n");
		}
	}

	public void saveTxt() {
		try {
			PrintWriter out = new PrintWriter(new FileWriter("src/files/Frutas.txt"));
			for (int i = 0; i < this.fruta.length; i++) {
				if (this.fruta[i] != null)
					out.print(this.fruta[i].getNome() + "\t" + this.fruta[i].getPesoMedio() + "\t"
							+ this.fruta[i].getCalorias() + "\n");
			}
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void readTxt() {
		this.reset();
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/files/Frutas.txt"));
			String linha = in.readLine();
			while (linha != null) {

				String[] textoSeparado = linha.split("\t");

				String nome = textoSeparado[0];
				double peso = Double.parseDouble(textoSeparado[1]);
				double cal = Double.parseDouble(textoSeparado[2]);

				Fruta fruta = new Fruta(nome, peso, cal);
				
				this.add(fruta);
				
				linha = in.readLine();
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
