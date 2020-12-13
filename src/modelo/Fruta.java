package modelo;

public class Fruta implements java.io.Serializable {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2360160195603917465L;
	private String nome;
	private double pesoMedio;
	private double calorias;
	
	public Fruta(String nome, double pesoMedio, double calorias) {
		super();
		this.setNome(nome);
		this.setPesoMedio(pesoMedio);
		this.setCalorias(calorias);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPesoMedio() {
		return pesoMedio;
	}

	public void setPesoMedio(double pesoMedio) {
		this.pesoMedio = pesoMedio;
	}

	public double getCalorias() {
		return calorias;
	}

	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}

	@Override
	public String toString() {
		return "Nome: " + nome + ", Peso Médio: " + pesoMedio + ", Calorias: " + calorias;
	}

}
