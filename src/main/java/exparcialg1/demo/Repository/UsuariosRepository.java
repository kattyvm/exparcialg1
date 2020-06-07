package exparcialg1.demo.Repository;

import exparcialg1.demo.Dtos.UsuarioQueGastoMasDto;
import exparcialg1.demo.Entity.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
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

    //List<UsuariosEntity> findUsuariosEntityByCorreo(String correo);

    @Query(value="CALL saveGestor(?, ?, ?, ?, ?, ?, ?, ?)",
            nativeQuery = true)
    List<UsuariosEntity> saveGestor(int id, String nombre, String apellido, int dni, String correo, String pass, Boolean activ, int rol);

    @Query(value="SELECT * FROM (SELECT u.nombre, u.apellido, u.dni, u.correo ,sum(p.preciototal) as totalgastado\n" +
            "FROM pedidos p\n" +
            "INNER JOIN usuarios u ON u.idusuarios = p.idusuarios\n" +
            "group by p.idusuarios) subQuery\n" +
            "WHERE totalgastado = (SELECT max(totalgastado) FROM (SELECT u.nombre, u.apellido, u.dni, u.correo ,sum(p.preciototal) as totalgastado\n" +
            "FROM pedidos p\n" +
            "INNER JOIN usuarios u ON u.idusuarios = p.idusuarios\n" +
            "group by p.idusuarios) subQuery)",nativeQuery = true)
    List<UsuarioQueGastoMasDto> obtenerUsuarioQueGastoMas();

}
