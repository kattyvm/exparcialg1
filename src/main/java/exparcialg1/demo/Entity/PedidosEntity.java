package exparcialg1.demo.Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="pedidos")
public class PedidosEntity {

    @Id
    @Column(nullable = false)
    private String codpedido;
    @ManyToOne
    @JoinColumn(name="idusuarios", nullable = false)
    private UsuariosEntity usuario;
    @Column(nullable = false)
    private BigDecimal preciototal;
    @Column(nullable = false)
    private LocalDateTime fecha;

    public String getCodpedido() {
        return codpedido;
    }

    public void setCodpedido(String codpedido) {
        this.codpedido = codpedido;
    }

    public UsuariosEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuariosEntity usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getPreciototal() {
        return preciototal;
    }

    public void setPreciototal(BigDecimal preciototal) {
        this.preciototal = preciototal;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
