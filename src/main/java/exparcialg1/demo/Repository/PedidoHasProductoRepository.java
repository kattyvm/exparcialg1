package exparcialg1.demo.Repository;

import exparcialg1.demo.Entity.PedidohasproductoEntity;
import exparcialg1.demo.constantes.PedhasProdID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoHasProductoRepository extends JpaRepository<PedidohasproductoEntity, PedhasProdID> {
}
