package controlSensores;

import controlRiegoAutomatizado.Region;

import java.util.Random;

public abstract class Sensor {
	protected String id;
	protected Random random;
	protected double lecturaActual;
	protected Region region;
	
	public Sensor(String id,Region region) {
		this.id=id;
		this.region=region;
		this.random= new Random();
	}
	public abstract void realizarMedicion();
	public double obtenerDatos() {
		return lecturaActual;
	}
}
