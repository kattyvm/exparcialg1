package exparcialg1.demo.Repository;

import exparcialg1.demo.Entity.PedidosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosRepository extends JpaRepository <PedidosEntity, String> {
}
