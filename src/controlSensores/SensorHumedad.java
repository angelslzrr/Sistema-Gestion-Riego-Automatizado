package controlSensores;

import controlRiegoAutomatizado.Region;

public class SensorHumedad extends Sensor{
	
	public SensorHumedad(String id,Region region) {
		super(id,region);
	}
	@Override
	public void realizarMedicion() {
		lecturaActual=random.nextDouble()*100;
	}
}
