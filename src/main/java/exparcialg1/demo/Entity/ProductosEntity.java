package exparcialg1.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import javax.validation.constraints.*;

@Entity
@Table(name="productos")
public class ProductosEntity {

    @Id
    @Column(nullable = false)
    private String codproducto;
    @Size(max = 40, message = "Ingrese máximo 40 caracteres.")
    @NotBlank(message = "Ingrese un nombre.")
    @Column(nullable = false)
    private String nombre;
    @Size(max = 255, message = "Ingrese máximo 255 caracteres.")
    @NotBlank(message = "Ingrese una descripcion.")
    @Column(nullable = false)
    private String descripcion;
    @Digits(integer = 4, fraction = 2, message = "Ingrese un precio válido.")
    @NotNull(message = "Ingrese un precio.")
    @Column(nullable = false)
    private BigDecimal preciounitario;
    private String foto;
    @Digits(integer = 11, fraction = 0, message = "Ingrese un numero entero de stock.")
    @Positive(message = "Ingrese un stock válido.")
    @NotNull(message = "Ingrese un stock.")
    @Column(nullable = false)
    private int stock;

    public String getCodproducto() {
        return codproducto;
    }

    public void setCodproducto(String codproducto) {
        this.codproducto = codproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPreciounitario() {
        return preciounitario;
    }

    public void setPreciounitario(BigDecimal preciounitario) {
        this.preciounitario = preciounitario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
