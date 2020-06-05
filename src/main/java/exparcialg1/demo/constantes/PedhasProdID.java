package exparcialg1.demo.constantes;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PedhasProdID implements Serializable {


    @Column(name = "codpedido")
    private String codpedido;
    @Column(name = "codproducto")
    private String codproducto;



    public PedhasProdID(){

    }

    public PedhasProdID(String x, String y){
        this.setCodpedido(x);
        this.setCodproducto(y);
    }

    public String getCodpedido() {
        return codpedido;
    }

    public void setCodpedido(String codpedido) {
        this.codpedido = codpedido;
    }

    public String getCodproducto() {
        return codproducto;
    }

    public void setCodproducto(String codproducto) {
        this.codproducto = codproducto;
    }
}
