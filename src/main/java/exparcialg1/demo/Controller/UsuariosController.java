package exparcialg1.demo.Controller;

import exparcialg1.demo.Entity.ProductosEntity;
import exparcialg1.demo.Repository.ProductosRepository;
import exparcialg1.demo.Repository.UsuariosRepository;
import exparcialg1.demo.constantes.ProductoCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/usuario")
public class UsuariosController {

    @Autowired
    UsuariosRepository usuariosRepository;
    @Autowired
    ProductosRepository productosRepository;

    @GetMapping(value = {"/"})
    public String index(Model m) {
        return "redirect:/";
    }
    @GetMapping(value = {"/addCart"})
    public String addCart(@RequestParam("id") String id, Model m, HttpSession session, RedirectAttributes att) {

        ArrayList<ProductoCarrito> cart = (ArrayList<ProductoCarrito>) session.getAttribute("cart");
        int numcart= (int) session.getAttribute("numcart");
        if (id!=null) {
            Optional<ProductosEntity> opt = productosRepository.findById(id);
            if (opt.isPresent()) {
                ProductosEntity prodTienda = opt.get();
                if (prodTienda.getStock() != 0) {
                    Boolean found = false;
                    for (ProductoCarrito prodCart : cart) {
                        if (prodCart.getProductos() == prodTienda) {
                            prodCart.setCantidad(prodCart.getCantidad() + 1);
                            found = true;
                        }
                    }
                    if (!found) {
                        ProductoCarrito prodCart = new ProductoCarrito();
                        prodCart.setProductos(prodTienda);
                        prodCart.setCantidad(1);
                        prodCart.setAvailable(true);
                        cart.add(prodCart);
                    }
                    numcart += 1;
                    session.setAttribute("cart", cart);
                    session.setAttribute("numcart", numcart);
                    att.addFlashAttribute("msg2", "Producto añadido al carrito!");
                } else {
                    att.addFlashAttribute("msg1", "Ups! No hay stock del producto seleccionado");
                }
            } else {
                att.addFlashAttribute("msg1", "Ups! No existe el producto seleccionado");
            }
        } else {
            att.addFlashAttribute("msg1", "Ups! No existe el producto seleccionado");
        }


        return "redirect:/";
    }
    @GetMapping(value = {"/myCart"})
    public String viewCart(HttpSession session) {
        return "redirect:/";
    }


}
