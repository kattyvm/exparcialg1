package exparcialg1.demo.Dtos;

import java.math.BigDecimal;

public interface ProductoMenosVendidoDto {

    String getCodproducto();
    String getNombre();
    BigDecimal getPreciounitario();
    int getStock();
    int getCantidadvendida();

}
