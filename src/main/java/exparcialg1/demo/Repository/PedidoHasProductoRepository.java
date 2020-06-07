package exparcialg1.demo.Repository;

import exparcialg1.demo.Entity.PedidohasproductoEntity;
import exparcialg1.demo.constantes.PedhasProdID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoHasProductoRepository extends JpaRepository<PedidohasproductoEntity, PedhasProdID> {

    @Query(value="SELECT * FROM pedidohasproductos\n" +
            "WHERE codpedido=?1",nativeQuery=true)
    List<PedidohasproductoEntity> buscarProductos(String codproducto);

}
