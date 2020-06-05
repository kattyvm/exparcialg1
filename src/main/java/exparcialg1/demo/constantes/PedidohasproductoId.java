package exparcialg1.demo.constantes;

import exparcialg1.demo.Entity.PedidosEntity;
import exparcialg1.demo.Entity.ProductosEntity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PedidohasproductoId{

    @ManyToOne
    @JoinColumn(name = "codpedido")
    private PedidosEntity pedido;
    @ManyToOne
    @JoinColumn(name = "codproducto")
    private ProductosEntity producto;







}
