package controlRiegoAutomatizado;

public class EstrategiaRiegoEficiente implements EstrategiaRiego{
	
	@Override
	public void aplicar() {
		System.out.println("...Cultivo analizado!\n --> Aplicando algoritmos de optimización...");
		System.out.println("Iniciando riego eficiente...");
		System.out.println("--- RIEGO EFICIENTE COMPLETADO ---");
	}
	@Override
	public String getNombreEstrategia() {
		return "Riego Eficiente";
	}
}


