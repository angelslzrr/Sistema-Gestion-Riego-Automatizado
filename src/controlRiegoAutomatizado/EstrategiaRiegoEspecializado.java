package controlRiegoAutomatizado;

public class EstrategiaRiegoEspecializado implements EstrategiaRiego {
	
	@Override
	public void aplicar() {
		System.out.println("...Cultivo analizado!");
		System.out.println("--> Utilizando datos de sensores de humedad...\n--> Aplicando algoritmos de optimización...");
		System.out.println("Iniciando riego especializado...");
		System.out.println("--- RIEGO ESPECIALIZADO COMPLETADO ---");
	}
	@Override
	public String getNombreEstrategia() {
		return "Riego Especializado";
	}
}

