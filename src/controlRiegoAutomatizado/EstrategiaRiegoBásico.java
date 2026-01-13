package controlRiegoAutomatizado;

public class EstrategiaRiegoBásico implements EstrategiaRiego{

	@Override
	public void aplicar() {
		System.out.println("...Cultivo analizado! Iniciando riego básico...");
		System.out.println("--- RIEGO BÁSICO COMPLETADO ---");
	}
	@Override
	public String getNombreEstrategia() {
		return "Riego Básico";
	}
}

