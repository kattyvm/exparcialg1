package exparcialg1.demo.Controller;

import exparcialg1.demo.Repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/producto")
public class ProductosController {

    @Autowired
    ProductosRepository productosRepository;
}
