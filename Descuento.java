package libreriaonline2;

/**
 * No s� si me he pasado aqu� con lo de los switch, pero para m� ten�a sentido!
 * 
 * @author mothcrown
 */
public class Descuento {
    static double descuentoServiciosPremium = 0.2;
    static double descuentoServiciosOro = 0.15;
    static double descuentoServiciosPlata = 0.1;
    static double descuentoProductosPremium = 0.1;
    static double descuentoProductosOro = 0.1;
    static double descuentoProductosPlata = 0.1;
    
    public double getDescuentoServicios(String tipo) {
        double descuento = 0;
        switch(tipo) {
            case "Premium": descuento = descuentoServiciosPremium;
                            break;
            case "Oro":     descuento = descuentoServiciosOro;
                            break;
            case "Plata":   descuento = descuentoServiciosPlata;
                            break;
        }      
        return descuento;
    }
    
    public double getDescuentoProductos(String tipo) {
        double descuento = 0;
        switch(tipo) {
            case "Premium": descuento = descuentoProductosPremium;
                            break;
            case "Oro":     descuento = descuentoProductosOro;
                            break;
            case "Plata":   descuento = descuentoProductosPlata;
                            break;
        }      
        return descuento;
    }
}
