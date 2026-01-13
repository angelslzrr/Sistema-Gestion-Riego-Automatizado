package controlRiegoAutomatizado;

import java.util.ArrayList;
import controlSensores.*;   

public class Cultivo {
    private String tipo;
    private String region;      // Nombre de la región (Texto)
    private String estacion;
    private EstrategiaRiego estrategia;
    
    // Declaramos la lista de sensores
    private ArrayList<Sensor> misSensores;

    public Cultivo(String tipo, String region, String estacion) {
        this.tipo = tipo;
        this.region = region;
        this.estacion = estacion;
        
        // Inicializamos la lista
        this.misSensores = new ArrayList<>();
        
        // Instalamos los sensores automáticamente al crear el cultivo
        instalarSensores();
    }

    // Convierte el texto de la región al Enum y crea los sensores
    private void instalarSensores() {
        // 1. Buscamos qué Region (Enum) coincide con el texto (String)
        Region regionEnumSeleccionada = null;
        
        // Recorremos todas las regiones posibles para encontrar la correcta
        for (Region r : Region.values()) {
            if (r.getNombre().equalsIgnoreCase(this.region)) {
                regionEnumSeleccionada = r;
                break;
            }
        }
        


        // 2. Creamos los sensores pasándoles la región encontrada
        // Generamos IDs aleatorios simples
        misSensores.add(new SensorTemperatura("TEMP-001", regionEnumSeleccionada));
        misSensores.add(new SensorHumedad("HUM-001", regionEnumSeleccionada));
    }

    public void setEstrategia(EstrategiaRiego estrategia) {
        this.estrategia = estrategia;
    }

    public void ejecutarRiego() {
        System.out.println("\n--- Analizando cultivo: " + tipo + " en " + region + " ---");
        
        // Antes de regar, los sensores deben medir
        System.out.println("   (Sensores analizando el entorno...)");
        for(Sensor s : misSensores) {
            s.realizarMedicion(); // Generando el número aleatorio
        }

        if (this.estrategia != null) {
            estrategia.aplicar();
        }
    }

    public String getTipo() {
        return tipo;
    }
    public String getRegion() {
        return region;
    }
    public String getEstacion() {
        return estacion;
    }

    // Métodos para obtener los datos
    public double getTemperaturaActual() {
        for (Sensor s : misSensores) {
            if (s instanceof SensorTemperatura) {
                return s.obtenerDatos(); 
            }
        }
        return 0.0;
    }

    public double getHumedadActual() {
        for (Sensor s : misSensores) {
            if (s instanceof SensorHumedad) {
                return s.obtenerDatos();
            }
        }
        return 0.0;
    }
    public String obtenerInformeRiego() {
        double temp = getTemperaturaActual();
        double humed = getHumedadActual();
        String estrat = (estrategia != null) ? estrategia.getNombreEstrategia() : "Sin estrategia";
        
        return "=== REPORTE DE RIEGO ===\n\n" +
               "Cultivo: " + tipo + "\n" +
               "Región: " + region + "\n" +
               "Estación: " + estacion + "\n" +
               "--------------------------------\n" +
               "CONDICIONES DETECTADAS:\n" +
               "   -> Temperatura: " + String.format("%.2f", temp) + " °C\n" +
               "   -> Humedad Suelo: " + String.format("%.2f", humed) + " %\n" +
               "--------------------------------\n" +
               "ACCIÓN TOMADA:\n" +
               "   -> " + estrat + "\n\n" +
               "Estado: Riego Ejecutado Exitosamente.";
    }
    public String getNombreEstrategia() {
        if (this.estrategia != null) {
            return this.estrategia.getNombreEstrategia();
        }
        return "Sin Asignar";
    }
}

