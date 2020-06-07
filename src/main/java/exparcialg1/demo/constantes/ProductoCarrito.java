package exparcialg1.demo.constantes;

import exparcialg1.demo.Entity.ProductosEntity;

import java.io.Serializable;

public class ProductoCarrito implements Serializable {
    private ProductosEntity productos;
    private int cantidad;
    private boolean available;

    public ProductosEntity getProductos() {
        return productos;
    }

    public void setProductos(ProductosEntity productos) {
        this.productos = productos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
