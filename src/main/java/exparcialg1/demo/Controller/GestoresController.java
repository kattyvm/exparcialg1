package exparcialg1.demo.Controller;

import exparcialg1.demo.Repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/gestor")
public class GestoresController {

    @Autowired
    UsuariosRepository usuariosRepository;
}
