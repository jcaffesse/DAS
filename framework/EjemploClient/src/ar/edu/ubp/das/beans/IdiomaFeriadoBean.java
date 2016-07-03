package ar.edu.ubp.das.beans;


public class IdiomaFeriadoBean implements Bean {

	private String idioma;
	private String descIdioma;
	private String feriado;
	private String nomFeriado;
	
	public String getIdioma() {
		return idioma;
	}
	
	public String getDescIdioma() {
		return descIdioma;
	}
	
	public String getFeriado() {
		return feriado;
	}
	
	public String getNomFeriado() {
		return nomFeriado;
	}
	
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	public void setDescIdioma(String descIdioma) {
		this.descIdioma = descIdioma;
	}
	
	public void setFeriado(String feriado) {
		this.feriado = feriado;
	}

	public void setNomFeriado(String nomFeriado) {
		this.nomFeriado = nomFeriado;
	}

	@Override
	public String toString() {
		return "IdiomaFeriadoBean [idioma=" + idioma + ", descIdioma="
				+ descIdioma + ", feriado=" + feriado + ", nomFeriado="
				+ nomFeriado + "]";
	}

	@Override
	public int compareTo(Bean bean) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
