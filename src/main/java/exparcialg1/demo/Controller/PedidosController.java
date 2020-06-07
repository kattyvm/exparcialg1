package exparcialg1.demo.Controller;

import exparcialg1.demo.Entity.PedidohasproductoEntity;
import exparcialg1.demo.Entity.PedidosEntity;
import exparcialg1.demo.Entity.ProductosEntity;
import exparcialg1.demo.Entity.UsuariosEntity;
import exparcialg1.demo.Repository.PedidoHasProductoRepository;
import exparcialg1.demo.Repository.PedidosRepository;
import exparcialg1.demo.Repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
    public String productosPorPedido(HttpSession session, Model model) {
        UsuariosEntity usuario = (UsuariosEntity) session.getAttribute("usuario");
        List<PedidosEntity> listaMisPedidos = pedidosRepository.buscarPorUsuario(usuario.getIdusuarios());

        ArrayList<PedidohasproductoEntity> listacompleta = new ArrayList<>();
        for (PedidosEntity pedido : listaMisPedidos) {

            List<PedidohasproductoEntity> pedidohasproductoEntities1 = pedidoHasProductoRepository.buscarProductos(pedido.getCodpedido());

            for (PedidohasproductoEntity phprod : pedidohasproductoEntities1) {
                listacompleta.add(phprod);
            }

        }

        if (listacompleta.size()==0) {
            model.addAttribute("msgEmpty", "No tienes pedidos aún");
        }
        model.addAttribute("listaProductosPorPedidos", listacompleta);
        model.addAttribute("listaMisPedidos", listaMisPedidos);
        return "donpepe/misPedidos";
    }


    @PostMapping(value="buscarPedidoPorCod")
    public String buscarPedidoPorCod(HttpSession session,@RequestParam("buscador") String buscador, Model model){
        UsuariosEntity usuario = (UsuariosEntity) session.getAttribute("usuario");
        List<PedidosEntity> listaMisPedidos = pedidosRepository.buscarPedidoPorCodigoPedido(buscador,usuario.getIdusuarios());

        ArrayList<PedidohasproductoEntity> listacompleta2 = new ArrayList<>();
        for (PedidosEntity pedido : listaMisPedidos) {
            List<PedidohasproductoEntity> pedidohasproductoEntities1= pedidoHasProductoRepository.buscarProductos(pedido.getCodpedido());

            for (PedidohasproductoEntity phprod :pedidohasproductoEntities1){
                listacompleta2.add(phprod);
            }

        }
        if (listacompleta2.size()==0) {
            model.addAttribute("msgEmpty", "No se ha encontrado la busqueda");
        }
        model.addAttribute("listaProductosPorPedidos",listacompleta2);
        model.addAttribute("listaMisPedidos",listaMisPedidos);
        return "donpepe/misPedidos";
    }

}
