package ar.edu.ubp.das.beans;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IdiomaFeriadoBean implements Bean {

	private String idioma;
	private String descIdioma;
	private Date   feriado;
	private String nomFeriado;
	
	public String getIdioma() {
		return idioma;
	}
	
	public String getDescIdioma() {
		return descIdioma;
	}
	
	public Date getFeriado() {
		return feriado;
	}
	
	public String getNomFeriado() {
		return nomFeriado;
	}
	
	@XmlElement
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	@XmlElement
	public void setDescIdioma(String descIdioma) {
		this.descIdioma = descIdioma;
	}
	
	@XmlElement
	public void setFeriado(Date feriado) {
		this.feriado = feriado;
	}

	@XmlElement
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
