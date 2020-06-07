package exparcialg1.demo.Repository;

import exparcialg1.demo.Entity.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosEntity,Integer> {

    public UsuariosEntity findUsuariosEntityByCorreo(String correo);

    List<UsuariosEntity> findUsuariosEntityByRol_idroles(int rol);

    @Query(value="SELECT idusuarios, nombre, apellido, dni, SUBSTRING_INDEX(correo, '@', 1) as correo, pwd, enabled, idroles " +
            "FROM usuarios WHERE idusuarios = ?",
            nativeQuery = true)
    public Optional<UsuariosEntity> obtenerUsuarioCorreo(int id);

    //List<UsuariosEntity> findUsuariosEntityByDni(int dni);

    @Query(value="CALL saveGestor(?, ?, ?, ?, ?, ?, ?)",
            nativeQuery = true)
    public String saveGestor(int id, String nombre, String apellido, int dni, String correo, Boolean activ, int rol);


}
