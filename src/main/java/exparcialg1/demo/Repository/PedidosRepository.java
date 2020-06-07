package exparcialg1.demo.Repository;

import exparcialg1.demo.Entity.PedidosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PedidosRepository extends JpaRepository <PedidosEntity, String> {

    @Query(value="SELECT sum(preciototal) as totalfacturado FROM pedidos",nativeQuery = true)
    BigDecimal obtenerTotalFacturado();

    @Query(value="SELECT count(codpedido) as cantidadcompras FROM pedidos",nativeQuery = true)
    int obtenerCantCompras();

    @Query(value="SELECT * FROM pedidos\n" +
            "WHERE idusuarios=?1",nativeQuery = true)
    List<PedidosEntity> buscarPorUsuario(int usuario);

    @Query(value="SELECT * \n" +
            "FROM pedidos\n" +
            "WHERE codpedido=?1 and idusuarios=?2", nativeQuery =true)
    List<PedidosEntity> buscarPedidoPorCodigoPedido(String codpedido, int idusuario);

}
