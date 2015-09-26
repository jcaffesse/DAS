package ar.edu.ubp.das.beans;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FeriadoBean implements Bean {

	private Date   feriado;
	private String descFeriado;
	private String tipoFeriado;
	
	public Date getFeriado() {
		return feriado;
	}
	
	public String getDescFeriado() {
		return descFeriado;
	}
	
	public String getTipoFeriado() {
		return tipoFeriado;
	}
	
	@XmlElement
	public void setFeriado(Date feriado) {
		this.feriado = feriado;
	}
	
	@XmlElement
	public void setDescFeriado(String descFeriado) {
		this.descFeriado = descFeriado;
	}

	@XmlElement
	public void setTipoFeriado(String tipoFeriado) {
		this.tipoFeriado = tipoFeriado;
	}
	
	@Override
	public String toString() {
		return "FeriadoBean [feriado=" + feriado + ", descFeriado="
				+ descFeriado + ", tipoFeriado=" + tipoFeriado + "]";
	}

	@Override
	public int compareTo(Bean bean) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
