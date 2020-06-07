package exparcialg1.demo.Repository;

import exparcialg1.demo.Dtos.CantProdVendidosDto;
import exparcialg1.demo.Dtos.ProductoMasCaroDto;
import exparcialg1.demo.Dtos.ProductoMasVendidoDto;
import exparcialg1.demo.Dtos.ProductoMenosVendidoDto;
import exparcialg1.demo.Entity.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductosRepository extends JpaRepository<ProductosEntity,String> {

    @Query(value="SELECT codproducto, nombre, preciounitario, stock FROM productos\n" +
            "WHERE preciounitario = (SELECT max(preciounitario) FROM productos)", nativeQuery=true)
    List<ProductoMasCaroDto> obtenerProdMasCaro();

    @Query(value="SELECT * FROM (SELECT p.codproducto, p.nombre, p.preciounitario, p.stock, sum(php.cantidad) as cantidadvendida\n" +
            "FROM pedidohasproductos php\n" +
            "INNER JOIN productos p ON php.codproducto = p.codproducto\n" +
            "group by php.codproducto) subQuery\n" +
            "WHERE cantidadvendida = (SELECT max(cantidadvendida) FROM\n" +
            "(SELECT p.codproducto, p.nombre,p.preciounitario, p.stock, sum(php.cantidad) as cantidadvendida\n" +
            "FROM pedidohasproductos php\n" +
            "INNER JOIN productos p ON php.codproducto = p.codproducto\n" +
            "group by php.codproducto) subQuery)", nativeQuery = true)
    List<ProductoMasVendidoDto> obtenerProductoMasVendido();

    @Query(value="SELECT * FROM (SELECT p.codproducto, p.nombre, p.preciounitario, p.stock, sum(php.cantidad) as cantidadvendida\n" +
            "FROM pedidohasproductos php\n" +
            "INNER JOIN productos p ON php.codproducto = p.codproducto\n" +
            "group by php.codproducto) subQuery\n" +
            "WHERE cantidadvendida = (SELECT min(cantidadvendida) FROM\n" +
            "(SELECT p.codproducto, p.nombre,p.preciounitario, p.stock, sum(php.cantidad) as cantidadvendida\n" +
            "FROM pedidohasproductos php\n" +
            "INNER JOIN productos p ON php.codproducto = p.codproducto\n" +
            "group by php.codproducto) subQuery)",nativeQuery = true)
    List<ProductoMenosVendidoDto> obtenerProductoMenosVendido();

    @Query(value="SELECT sum(cantidad) as cantproductosvendidos FROM pedidohasproductos\n" +
            "\n", nativeQuery = true)
    int obtenerCantprodvendidos();



    @Query(value="SELECT * FROM productos \n" +
            "    WHERE codproducto LIKE ?1 \n" +
            "    OR nombre LIKE ?1 ", nativeQuery = true)
    List<ProductosEntity> buscarProductosEntitiesByCodproductoOrNombre(String buscador);



}
