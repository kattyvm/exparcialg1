package exparcialg1.demo.Repository;

import exparcialg1.demo.Entity.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends JpaRepository<ProductosEntity,String> {
}
