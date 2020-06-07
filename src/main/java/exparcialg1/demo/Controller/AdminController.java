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
import java.util.regex.Pattern;

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
                                @RequestParam("dni") int dni,
                                @RequestParam("dominio") int dominio,
                                Model model) {

        if (!usuarios.getNombre().isEmpty()) {
            if (Pattern.compile("[0-9]").matcher(usuarios.getNombre()).find()) {
                bindingResult.rejectValue("nombre", "error.user", "No ingrese valores numéricos.");
            }
            if (usuarios.getNombre().trim().length() == 0) {
                bindingResult.rejectValue("nombre", "error.user", "Ingrese un nombre válido.");
            }
        }
        if (!usuarios.getApellido().isEmpty()) {
            if (Pattern.compile("[0-9]").matcher(usuarios.getApellido()).find()) {
                bindingResult.rejectValue("apellido", "error.user", "No ingrese valores numéricos.");
            }
            if (usuarios.getApellido().trim().length() == 0) {
                bindingResult.rejectValue("apellido", "error.user", "Ingrese un apellido válido.");
            }
        }
        //List<UsuariosEntity> validarDni = usuariosRepository.findAll();
        if (usuarios.getDni()!=0) {
            if (usuarios.getDni() < 10000000) {
                bindingResult.rejectValue("dni", "error.user", "Ingrese un DNI válido.");
            }
            if (usuarios.getDni() > 99999999) {
                bindingResult.rejectValue("dni", "error.user", "Ingrese un DNI válido.");
            }
            //if(validarDni.contains(usuarios.getDni())){
            //    bindingResult.rejectValue("dni","error.user","Este DNI ya existe");
            //}
        }
        if (!usuarios.getCorreo().isEmpty()) {
            if (Pattern.compile("[@]").matcher(usuarios.getCorreo()).find()) {
                bindingResult.rejectValue("correo", "error.user", "Ingrese un correo válido.");
            }
            if (Pattern.compile("[ ]").matcher(usuarios.getCorreo()).find()) {
                bindingResult.rejectValue("correo", "error.user", "Ingrese un correo válido.");
            }
            if (usuarios.getCorreo().trim().length() == 0) {
                bindingResult.rejectValue("correo", "error.user", "Ingrese un correo válido.");
            }
        }


        if (bindingResult.hasErrors()) {
            return "admin/formGestores";
        } else if (usuarios.getIdusuarios() == 0) {

            if (dominio==1) {
                String mail = usuarios.getCorreo();
                usuarios.setCorreo(mail + "@pucp.edu.pe");
            } else if (dominio==2) {
                String mail = usuarios.getCorreo();
                usuarios.setCorreo(mail + "@pucp.pe");
            }

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

            if (dominio==1) {
                String mail = usuarios.getCorreo();
                usuarios.setCorreo(mail + "@pucp.edu.pe");
            } else if (dominio==2) {
                String mail = usuarios.getCorreo();
                usuarios.setCorreo(mail + "@pucp.pe");
            }

            usuarios.setEnabled(opt.get().getEnabled());
            usuarios.setRol(opt.get().getRol());
        }
        usuariosRepository.save(usuarios);
        //no funciona pero el query sí
        //usuariosRepository.saveGestor(usuarios.getIdusuarios(),usuarios.getNombre(),usuarios.getApellido(),
        //        usuarios.getDni(),usuarios.getCorreo(),usuarios.getEnabled(),usuarios.getRol().getIdroles());
        return "redirect:/admin/listGestor";
    }

    @GetMapping("/editGestor")
    public String editarGestores(@ModelAttribute("gestor") UsuariosEntity usuariosEntity,
                                 @RequestParam("id") int id,
                                 Model model) {
        Optional<UsuariosEntity> opt = usuariosRepository.obtenerUsuarioCorreo(id);
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
