package exparcialg1.demo.Entity;

import exparcialg1.demo.constantes.PedhasProdID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="pedidohasproductos")
public class PedidohasproductoEntity {

    @EmbeddedId
    private PedhasProdID id;
    private int cantidad;
    private BigDecimal subtotal;

    public PedhasProdID getId() {
        return id;
    }

    public void setId(PedhasProdID id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
