package exparcialg1.demo.Controller;

import exparcialg1.demo.Entity.UsuariosEntity;
import exparcialg1.demo.Repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    UsuariosRepository usuariosRepository;

    @GetMapping(value = {"/listaGestores","/"})
    public String listarGestores(Model model) {
        List<UsuariosEntity> listaGestor = usuariosRepository.findUsuariosEntityByRol_idroles(2);
        model.addAttribute("lista", listaGestor);
        return "admin/listarGestores";
    }



}
