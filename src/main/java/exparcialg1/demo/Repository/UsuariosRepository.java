package exparcialg1.demo.Repository;

import exparcialg1.demo.Entity.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosEntity,Integer> {

    public UsuariosEntity findUsuariosEntityByCorreo(String correo);

    List<UsuariosEntity> findUsuariosEntityByRol_idroles(int rol);


}
