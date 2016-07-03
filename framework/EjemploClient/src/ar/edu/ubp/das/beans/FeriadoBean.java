package ar.edu.ubp.das.beans;

public class FeriadoBean implements Bean {

	private String feriado;
	private String descFeriado;
	private String tipoFeriado;
	
	public String getFeriado() {
		return feriado;
	}
	
	public String getDescFeriado() {
		return descFeriado;
	}
	
	public String getTipoFeriado() {
		return tipoFeriado;
	}
	
	public void setFeriado(String feriado) {
		this.feriado = feriado;
	}
	
	public void setDescFeriado(String descFeriado) {
		this.descFeriado = descFeriado;
	}

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
