package controlRiegoAutomatizado;

public class GeneradorDeEstrategias {
    
    private static final String DEMANDA_ALTA = "ALTA";
    private static final String DEMANDA_MEDIA = "MEDIA";
    private static final String DEMANDA_BAJA = "BAJA";
    
    private static final String ESTRES_ALTO = "ALTO";
    private static final String ESTRES_MEDIO = "MEDIO";
    private static final String ESTRES_BAJO = "BAJO";

    public EstrategiaRiego definirEstrategia(Cultivo cult) {
        
        // Evaluamos qué tipo de planta es (Demanda Hídrica)
        String demandaHidrica = evaluarDemandaHidrica(cult.getTipo());
        
        // Evaluamos el clima actual con los sensores (Estrés Ambiental)
        // --> Leemos los sensores directamente del cultivo
        double temperatura = cult.getTemperaturaActual();
        double humedad = cult.getHumedadActual();
        String nivelEstres = evaluarEstresAmbiental(temperatura, humedad);
        
        System.out.println("   [Análisis] Planta: " + demandaHidrica + " | Clima: " + nivelEstres);

        // ÁRBOL DE DECISIÓN
        
        // RAMA 1: CULTIVOS DE ALTA DEMANDA (Maíz, Arroz)
        if (demandaHidrica.equals(DEMANDA_ALTA)) {
            if (nivelEstres.equals(ESTRES_ALTO) || nivelEstres.equals(ESTRES_MEDIO)) {
                return new EstrategiaRiegoEspecializado(); // Necesita mucho cuidado
            } else {
                return new EstrategiaRiegoEficiente(); // Clima tranquilo, riego normal optimizado
            }
        } 
        
        // RAMA 2: CULTIVOS DE DEMANDA MEDIA (Papa, Tomate)
        else if (demandaHidrica.equals(DEMANDA_MEDIA)) {
            if (nivelEstres.equals(ESTRES_ALTO)) {
                return new EstrategiaRiegoEspecializado(); // Mucho calor daña la papa
            } else if (nivelEstres.equals(ESTRES_MEDIO)) {
                return new EstrategiaRiegoEficiente();
            } else {
                return new EstrategiaRiegoBásico(); // Clima fresco, riego simple basta
            }
        } 
        
        // RAMA 3: CULTIVOS DE BAJA DEMANDA (Suculentas, Tuna)
        else { 
            if (nivelEstres.equals(ESTRES_ALTO)) {
                return new EstrategiaRiegoEficiente(); // Incluso las suculentas sufren con calor extremo
            } else {
                return new EstrategiaRiegoBásico(); // Casi no necesitan agua
            }
        }
    }

    // --- MÉTODOS AUXILIARES (Lógica) ---

    private String evaluarDemandaHidrica(String nombreCultivo) {
        // Convertimos a minúsculas para evitar errores
        String nombre = nombreCultivo.toLowerCase();
        
        if (nombre.contains("arroz") || nombre.contains("maiz") || nombre.contains("maíz") || nombre.contains("caña")) {
            return DEMANDA_ALTA;
        } 
        else if (nombre.contains("cactus") || nombre.contains("tuna") || nombre.contains("suculenta")) {
            return DEMANDA_BAJA;
        } 
        else {
            return DEMANDA_MEDIA; // Papa, Tomate, Zanahoria, etc.
        }
    }

    private String evaluarEstresAmbiental(double temp, double hum) {
        // Lógica de agronomía básica:
        // Calor (>25°C) y sequedad (<40%) generan ALTO ESTRÉS hídrico.
        
        if (temp > 25 && hum < 40) {
            return ESTRES_ALTO; // Mucho calor y seco (Costa Verano)
        }
        else if (temp < 15 || hum > 80) {
            return ESTRES_BAJO; // Frío o muy húmedo (Invierno / Selva Lluviosa)
        }
        else {
            return ESTRES_MEDIO; // Condiciones normales
        }
    }
}