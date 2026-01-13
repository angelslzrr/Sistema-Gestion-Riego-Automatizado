package controlSensores;

import controlRiegoAutomatizado.Region;

public class SensorTemperatura extends Sensor{
	
	public SensorTemperatura(String id, Region region) {
		super(id,region);
	}
	@Override
	public void realizarMedicion() {
		double min=region.getTempMin();
		double max=region.getTempMax();
		double diferencia=max-min;
		double temperaturaDetectada=(random.nextDouble()*diferencia)+min;
		this.lecturaActual=temperaturaDetectada;
	}
}
