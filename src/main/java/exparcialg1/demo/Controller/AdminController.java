package exparcialg1.demo.Controller;

import exparcialg1.demo.Entity.RolesEntity;
import exparcialg1.demo.Entity.UsuariosEntity;
import exparcialg1.demo.Repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    UsuariosRepository usuariosRepository;

    @GetMapping(value = {"/listGestor", "/"})
    public String listarGestores(Model model) {
        List<UsuariosEntity> listaGestor = usuariosRepository.findUsuariosEntityByRol_idroles(2);
        model.addAttribute("lista", listaGestor);
        return "admin/listarGestores";
    }

    @GetMapping("/formGestor")
    public String formularioGestor(@ModelAttribute("gestor") UsuariosEntity usuarios) {
        return "admin/formGestores";
    }

    @PostMapping("/saveGestor")
    public String guardarGestor(@ModelAttribute("gestor") @Valid UsuariosEntity usuarios,
                                BindingResult bindingResult,
                                RedirectAttributes attr,
                                Model model) {


        if (bindingResult.hasErrors()) {
            return "admin/formGestores";
        } else if (usuarios.getIdusuarios() == 0) {
            RolesEntity roles = new RolesEntity();
            roles.setIdroles(2);
            usuarios.setRol(roles);
            usuarios.setEnabled(false);
            //String pwd = "abcdef";
            //usuarios.setPwd(pwd);
            attr.addFlashAttribute("msg", "Gestor creado exitosamente");

        } else {
            attr.addFlashAttribute("msg", "Gestor actualizado exitosamente");
            //usuarios.setPwd(usuarios.getPwd());
            Optional<UsuariosEntity> opt = usuariosRepository.findById(usuarios.getIdusuarios());
            usuarios.setEnabled(opt.get().getEnabled());
            usuarios.setRol(opt.get().getRol());
        }
        usuariosRepository.save(usuarios);
        return "redirect:/admin/listGestor";
    }

    @GetMapping("/editGestor")
    public String editarGestores(@ModelAttribute("gestor") UsuariosEntity usuariosEntity,
                                 @RequestParam("id") int id,
                                 Model model) {
        Optional<UsuariosEntity> opt = usuariosRepository.findById(id);
        if (opt.isPresent()) {
            usuariosEntity = opt.get();
            model.addAttribute("gestor", usuariosEntity);
            return "admin/formGestores";
        } else {
            return "redirect:/admin/listGestor";
        }
    }

    @GetMapping("/deleteGestor")
    public String borrarGestor(@RequestParam("id") int id,
                             RedirectAttributes attr) {
        Optional<UsuariosEntity> opt = usuariosRepository.findById(id);
        if(opt.isPresent()){
            usuariosRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Gestor exitosamente eliminado");
        }
        return "redirect:/admin/listGestor";
    }


}
