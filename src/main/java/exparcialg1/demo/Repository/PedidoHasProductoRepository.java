package exparcialg1.demo.Repository;

import exparcialg1.demo.Entity.PedidohasproductoEntity;
import exparcialg1.demo.constantes.PedhasProdID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoHasProductoRepository extends JpaRepository<PedidohasproductoEntity, PedhasProdID> {

    @Query(value="SELECT codpedido,codproducto\n" +
            "FROM donpepe.pedidohasproductos\n" +
            "WHERE codproducto =?1",nativeQuery = true)
    List<PedidohasproductoEntity> buscarPedidosPorProducto(String codproducto);



}
