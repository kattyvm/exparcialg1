package exparcialg1.demo.Controller;

import exparcialg1.demo.Entity.UsuariosEntity;
import exparcialg1.demo.Repository.PedidoHasProductoRepository;
import exparcialg1.demo.Repository.PedidosRepository;
import exparcialg1.demo.Repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/pedidos")
public class PedidosController {

    @Autowired
    PedidosRepository pedidosRepository;
    @Autowired
    ProductosRepository productosRepository;
    @Autowired
    PedidoHasProductoRepository pedidoHasProductoRepository;

    @GetMapping("/")
    public String productosPorConfirmar(HttpSession session, Model model){

        UsuariosEntity usuario = (UsuariosEntity) session.getAttribute("usuario");
        model.addAttribute("listaProductosPorPedido",pedidoHasProductoRepository.findAll());
        model.addAttribute("listaProductos",productosRepository.findAll());
        model.addAttribute("listaMisPedidos",pedidosRepository.buscarPorUsuario(usuario.getIdusuarios()));
        return "donpepe/misPedidos";
    }


}
