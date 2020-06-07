package exparcialg1.demo.Controller;

import exparcialg1.demo.Entity.PedidosEntity;
import exparcialg1.demo.Entity.ProductosEntity;
import exparcialg1.demo.Repository.PedidosRepository;
import exparcialg1.demo.Repository.ProductosRepository;
import exparcialg1.demo.Repository.UsuariosRepository;
import exparcialg1.demo.constantes.ProductoCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/usuario")
public class UsuariosController {

    @Autowired
    UsuariosRepository usuariosRepository;
    @Autowired
    ProductosRepository productosRepository;
    @Autowired
    PedidosRepository pedidosRepository;

    @GetMapping(value = {"/"})
    public String index(Model m) {
        return "redirect:/";
    }

    @GetMapping(value = {"/addCart"})
    public String addCart(@RequestParam("id") String id, Model m, HttpSession session, RedirectAttributes att) {

        ArrayList<ProductoCarrito> cart = (ArrayList<ProductoCarrito>) session.getAttribute("cart");
        int numcart = (int) session.getAttribute("numcart");
        if (id != null) {
            Optional<ProductosEntity> opt = productosRepository.findById(id);
            if (opt.isPresent()) {
                ProductosEntity prodTienda = opt.get();
                if (prodTienda.getStock() != 0) {
                    Boolean found = false;
                    int i = 0;
                    for (ProductoCarrito prodCart : cart) {
                        if (prodCart.getProductos().getCodproducto().equals(prodTienda.getCodproducto())) {
                            prodCart.setCantidad(prodCart.getCantidad() + 1);
                            cart.set(i, prodCart);
                            found = true;

                        }

                        i++;
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
    public String viewCart(HttpSession session, Model m) {
        ArrayList<ProductoCarrito> cart = (ArrayList<ProductoCarrito>) session.getAttribute("cart");
        if (cart.size()==0) {
            boolean validCart = validateCart(cart, session, m);
            m.addAttribute("path", "A0006I.jpg");


            BigDecimal total = new BigDecimal(0);

            for (ProductoCarrito prodCart : cart) {
                BigDecimal cant = new BigDecimal(prodCart.getCantidad());
                total = total.add(prodCart.getProductos().getPreciounitario().multiply(cant));
            }
            m.addAttribute("total", total);
        }



        return "donpepe/myCart";
    }


    private boolean validateCart(ArrayList<ProductoCarrito> cart, HttpSession session, Model m) {
        int i = 0;
        boolean valid = true;
        for (ProductoCarrito prodCart : cart) {
            Optional<ProductosEntity> opt = productosRepository.findById(prodCart.getProductos().getCodproducto());
            if (!opt.isPresent()) {
                prodCart.setAvailable(false);
                cart.set(i, prodCart);
                m.addAttribute("msgerror", "Ups! Parece que algunos de tus productos ya no estan disponibles");
                valid = false;
            } else {
                ProductosEntity prodTienda = opt.get();
                if (prodTienda.getStock() < prodCart.getCantidad()) {
                    prodCart.setAvailable(false);
                    cart.set(i, prodCart);
                    m.addAttribute("msgerror", "Ups! Parece que algunos de tus productos no tienen suficiente stock");
                    valid = false;
                }
            }
            i++;
        }
        session.setAttribute("cart", cart);
        return valid;
    }

    @GetMapping(value = {"/reviewCart"})
    public String reviewCart(HttpSession session, RedirectAttributes att) {
        ArrayList<ProductoCarrito> cart = (ArrayList<ProductoCarrito>) session.getAttribute("cart");
        Iterator itr = cart.iterator();
        while (itr.hasNext()) {
            ProductoCarrito prodCart = (ProductoCarrito) itr.next();
            if (!prodCart.getAvailable()) {
                itr.remove();
            }
        }
        session.setAttribute("cart", cart);
        int numcart = updateCantidad(cart);
        session.setAttribute("numcart", numcart);
        att.addFlashAttribute("msgsuccess", "Productos borrados exitosamente");
        return "redirect:/usuario/myCart";
    }

    @GetMapping(value = {"/checkout"})
    public String checkout() {

        return "donpepe/credit";
    }

    @GetMapping(value = {"/borrar"})
    public String borrar(@RequestParam("id") String id, RedirectAttributes att, HttpSession session) {
        ArrayList<ProductoCarrito> cart = (ArrayList<ProductoCarrito>) session.getAttribute("cart");
        Iterator itr = cart.iterator();
        while (itr.hasNext()) {
            ProductoCarrito prodCart = (ProductoCarrito) itr.next();
            if (prodCart.getProductos().getCodproducto().equals(id)) {
                prodCart.setCantidad(prodCart.getCantidad() - 1);
                if (prodCart.getCantidad() == 0) {
                    itr.remove();
                }
            }
        }
        session.setAttribute("cart", cart);
        att.addFlashAttribute("msgsuccess", "Producto borrado exitosamente");
        return "redirect:/usuario/myCart";
    }


    @PostMapping(value = {"/pay"})
    public String pay(@RequestParam("ccstr") String ccstr, RedirectAttributes att, HttpSession session, Model m) {
        att.addFlashAttribute("ccstr", ccstr);


        try {

            if (ccstr.length() > 16) {
                throw new Exception("");
            } else {
                BigInteger i = new BigInteger(ccstr);

                boolean valid = validateCC(ccstr);
                if (!valid) {
                    throw new Exception("");
                }

            }
            ArrayList<ProductoCarrito> cart = (ArrayList<ProductoCarrito>) session.getAttribute("cart");
            boolean validCart = validateCart(cart, session, m);
            if (!validCart) {
                return "redirect:/usuario/myCart";
            }

            PedidosEntity pedidosEntity = null;

            LocalDateTime today = LocalDateTime.now();
            String year = String.valueOf(today.getYear());
            String month = String.valueOf(today.getMonth());
            String day = String.valueOf(today.getDayOfMonth());


            List<PedidosEntity> pedidosList = pedidosRepository.findAll();
            int mayor=0;


            for (PedidosEntity ped : pedidosList){
                StringBuilder strNum = new StringBuilder();
                char[] codarray = ped.getCodpedido().toCharArray();
                for(int i=10;i<codarray.length;i++) {
                    strNum.append(codarray[i]);
                }
                int codnum = Integer.parseInt(strNum.toString());
                if (codnum>mayor){
                    mayor=codnum;
                }
            }

            int codpednum=mayor+1;
            String codpedstr=String.valueOf(codpednum);
            String codped= "PE"+day+month+year+codpedstr;
            System.out.println(codped);
            System.out.println(codped);
            System.out.println(codped);
            System.out.println(codped);
            System.out.println(codped);
            System.out.println(codped);
            System.out.println(codped);
            System.out.println(codped);
            System.out.println(codped);
            System.out.println(codped);
            System.out.println(codped);
            System.out.println(codped);
            System.out.println(codped);
        } catch (Exception e) {
            att.addFlashAttribute("msgerror", "Ups! Parece que la tarjeta no es válida");
            return "redirect:/usuario/checkout";
        }
        return "redirect:/";
    }


    private boolean validateCC(String ccstr) {
        char[] ccarray = ccstr.toCharArray();
        int[] numberarray = new int[16];
        int i = ccarray.length - 1;

        for (char c : ccarray) {
            numberarray[i] = c - '0';
            i--;
        }
        for (int i2 = 0; i2 < numberarray.length / 2; i2++) {
            numberarray[i2 * 2 + 1] *= 2;

        }
        int sum = 0;
        for (int i3 = 1; i3 < numberarray.length; i3++) {
            if (numberarray[i3] > 9) {
                numberarray[i3] -= 9;
            }
            sum += numberarray[i3];
        }
        Boolean valid = false;
        int validator = (10 - sum % 10) % 10;
        if (validator == numberarray[0]) {
            valid = true;
        }
        return valid;
    }

    private int updateCantidad(ArrayList<ProductoCarrito> cart) {
        int num = 0;
        for (ProductoCarrito prodCart : cart) {
            num += prodCart.getCantidad();
        }
        return num;
    }


}
