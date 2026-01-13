package controlRiegoAutomatizado;

public enum Region {
	COSTA("Costa o chala",15.0,28.0),
	YUNGA("Yunga",20.0,25.0),
	QUECHUA("Quechua",11.0,16.0),
	SUNI("Suni",7.0,10.0),
	PUNA("Puna",0.0,7.0),
	JANCA("Janca",-20.0,0.0),
	RUPARUPA("Rupa Rupa",22.0,25.0),
	OMAGUA("Omagua",24.0,32.0);
	
	private final String nombre;
	private final double tempMin;
	private final double tempMax;
	
	private Region(String nombre, double tempMin,double tempMax) {
		this.nombre=nombre;
		this.tempMin=tempMin;
		this.tempMax=tempMax;
	}
	public String getNombre() {
		return nombre;
	}
	public double getTempMin() {
		return tempMin;
	}
	public double getTempMax() {
		return tempMax;
	}
	public static Region buscarPorNombre(String texto) {
		for(Region r:Region.values()) {
			if(r.getNombre().equalsIgnoreCase(texto)) {
				return r;
			}
		}
		return null;
	}
}
