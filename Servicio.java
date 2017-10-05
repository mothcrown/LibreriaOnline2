package libreriaonline2;

/**
 *
 * @author mothcrown
 */
public class Servicio {
    static double precioSuscripcionMensual = 30;
    static double precioSuscripcionTrimestral = 75;
    static double precioSuscripcionAnual = 180;
    static double miembroPremium = 50;
    static double miembroOro = 20;
    static double miembroPlata = 10;
    
    public double getPreciosServicio(String tipo) {
        double descuento = 0;
        switch(tipo) {
            case "mensual":     descuento = precioSuscripcionMensual;
                                break;
            case "trimestral":  descuento = precioSuscripcionTrimestral;
                                break;
            case "anual":       descuento = precioSuscripcionAnual;
                                break;
        }      
        return descuento;
    }
    
    public double getPreciosMembresia(String tipo) {
        double descuento = 0;
        switch(tipo) {
            case "Premium": descuento = miembroPremium;
                            break;
            case "Oro":     descuento = miembroOro;
                            break;
            case "Plata":   descuento = miembroPlata;
                            break;
        }      
        return descuento;
    }
}
