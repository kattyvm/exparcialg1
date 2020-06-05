package exparcialg1.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="pedidohasproductos")
public class PedidohasproductoEntity {

    @Id
    private String codpedido;
    private String codproducto;
    private int cantidad;
    private BigDecimal subtotal;


}
