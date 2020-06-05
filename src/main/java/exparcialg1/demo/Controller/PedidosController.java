package exparcialg1.demo.Controller;

import exparcialg1.demo.Repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/pedido")
public class PedidosController {

    @Autowired
    PedidosRepository pedidosRepository;
}
