package exparcialg1.demo.constantes;

import exparcialg1.demo.Entity.PedidosEntity;
import exparcialg1.demo.Entity.ProductosEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class PedhasProdID implements Serializable {

    @ManyToOne
    @JoinColumn(name = "codpedido")
    private PedidosEntity codpedido;
    @ManyToOne
    @JoinColumn(name = "codproducto")
    private ProductosEntity codproducto;



    public PedhasProdID(){

    }

    public PedhasProdID(PedidosEntity x, ProductosEntity y){
        this.setCodpedido(x);
        this.setCodproducto(y);
    }

    public PedidosEntity getCodpedido() {
        return codpedido;
    }

    public void setCodpedido(PedidosEntity codpedido) {
        this.codpedido = codpedido;
    }

    public ProductosEntity getCodproducto() {
        return codproducto;
    }

    public void setCodproducto(ProductosEntity codproducto) {
        this.codproducto = codproducto;
    }
}
