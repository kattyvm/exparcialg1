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
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String productosPorConfirmar(HttpSession session, Model model){
        UsuariosEntity usuario = (UsuariosEntity) session.getAttribute("usuario");
        List<PedidosEntity> listaMisPedidos = pedidosRepository.buscarPorUsuario(usuario.getIdusuarios());

        ArrayList<PedidohasproductoEntity> listacompleta=new ArrayList<>();
        for (PedidosEntity pedido : listaMisPedidos) {
            List<PedidohasproductoEntity> pedidohasproductoEntities1= pedidoHasProductoRepository.buscarProductos(pedido.getCodpedido());

            for (PedidohasproductoEntity phprod :pedidohasproductoEntities1){
                listacompleta.add(phprod);
            }

        }

        model.addAttribute("listaProductosPorPedidos",listacompleta);
        model.addAttribute("listaMisPedidos",listaMisPedidos);
        return "donpepe/misPedidos";
    }


}
