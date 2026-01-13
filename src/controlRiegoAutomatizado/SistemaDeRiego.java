package controlRiegoAutomatizado;

import java.util.ArrayList;

public class SistemaDeRiego {
	private GeneradorDeEstrategias generador;
	private ArrayList<Cultivo> listaCultivos;
	
	public SistemaDeRiego() {
		generador=new GeneradorDeEstrategias();
		listaCultivos=new ArrayList<>();
	}
	public void agregarCultivo(String tipo, String region, String estacion) {
		Cultivo cultivoNuevo=new Cultivo(tipo, region, estacion);
		EstrategiaRiego estrategiaEspecifica=generador.definirEstrategia(cultivoNuevo);
		cultivoNuevo.setEstrategia(estrategiaEspecifica);
		listaCultivos.add(cultivoNuevo);
	}
	public void iniciarRiegoParaTodos() {
		for(Cultivo c:listaCultivos) {
			c.ejecutarRiego();
		}
	}
}

