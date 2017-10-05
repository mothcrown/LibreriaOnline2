package libreriaonline2;

import java.io.Serializable;

/**
 *  Nada especial que ver. Por cierto, no he puesto ning�n m�todo para a�adir
 *  m�s libros. Deber�a de haberlo hecho. Hmmm.
 *
 * @author mothcrown
 */
public class Libro implements Serializable{
    private String titulo;
    private String autor;
    private double precio;
    
    public Libro(String titulo, String autor, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
    }
    
    public String getTitulo() {
       return titulo;
   }
   
   public void setTitulo(String titulo) {
       this.titulo = titulo;
   }
   
   public String getAutor() {
       return autor;
   }
   
   public void setAutor(String autor) {
       this.autor = autor;
   }
   
   public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
