package exparcialg1.demo.Dtos;

import java.math.BigDecimal;

public interface ProductoMasVendidoDto {

    String getCodproducto();
    String getNombre();
    BigDecimal getPreciounitario();
    int getStock();
    int getCantidadvendida();

}
