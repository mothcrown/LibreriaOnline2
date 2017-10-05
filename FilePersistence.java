package libreriaonline2;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Paths;

/**
 *
 * @author mothcrown
 */
public class FilePersistence implements Persistence{
   public List<Object> listaObjetos;
   public String fichero;
   
   @Override
   public void abrir(String fichero) {
        this.fichero = fichero;
        List<Object> lista = new ArrayList<>();
        String archivo = Paths.get(System.getProperty("java.io.tmpdir"), fichero).toString();
        File file = new File(archivo);
        if (file.exists()) {
            FileInputStream finput = null;
            ObjectInputStream oinput = null;
        
            try {
                finput = new FileInputStream(archivo);
                oinput = new ObjectInputStream(finput);
                lista = (List<Object>)oinput.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (finput != null)
                        finput.close();
                    if (oinput != null)
                        oinput.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            listaObjetos = new ArrayList<>(lista);
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
   }
   
   @Override
   public void cerrar() {
       /*
       *        ?! Esto no lo entendí. Te preguntaré en cuanto pueda!
       */
   }
   
   @Override
   public void guarda(Object objeto) {
        listaObjetos.add(objeto);
        FileOutputStream foutput = null;
        ObjectOutputStream ooutput = null;
        String archivo = Paths.get(System.getProperty("java.io.tmpdir"), fichero).toString();
        
        try {
            foutput = new FileOutputStream(archivo);
            ooutput = new ObjectOutputStream(foutput);
            ooutput.writeObject(listaObjetos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (foutput != null)
                    foutput.close();
                if (ooutput != null)
                    ooutput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
   }
   
   @Override
   public void borra(Object objeto) {
        listaObjetos.remove(objeto);
        FileOutputStream foutput = null;
        ObjectOutputStream ooutput = null;
        String archivo = Paths.get(System.getProperty("java.io.tmpdir"), fichero).toString();

        try {
            foutput = new FileOutputStream(archivo);
            ooutput = new ObjectOutputStream(foutput);
            ooutput.writeObject(listaObjetos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (foutput != null)
                    foutput.close();
                if (ooutput != null)
                    ooutput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
   }
   
   @Override
   public List<Object> listar() {
       return listaObjetos;
   }
   
}
