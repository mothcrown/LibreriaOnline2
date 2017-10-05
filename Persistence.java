package libreriaonline2;

import java.util.*;
/**
 *
 * @author mothcrown
 */
public interface Persistence {
    void abrir(String filename);
    void cerrar();
    void guarda(Object object);
    void borra(Object object);
    List<Object> listar();
}
