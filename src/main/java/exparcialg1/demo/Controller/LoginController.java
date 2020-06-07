package exparcialg1.demo.Controller;

import exparcialg1.demo.Entity.RolesEntity;
import exparcialg1.demo.Entity.UsuariosEntity;
import exparcialg1.demo.Repository.UsuariosRepository;
import exparcialg1.demo.constantes.ProductoCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Controller
public class LoginController {

    @Autowired
    UsuariosRepository usuariosRepository;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "login/loginForm";
    }

    @GetMapping("/redirectByRole")
    public String redirectByRole(Authentication auth, HttpSession session) {
        String rol = "";
        for (GrantedAuthority role : auth.getAuthorities()) {
            rol = role.getAuthority();
            break;
        }

        String username = auth.getName();
        UsuariosEntity usuario = usuariosRepository.findUsuariosEntityByCorreo(username);
        session.setAttribute("usuario", usuario);
        ArrayList<ProductoCarrito> cart = new ArrayList<>();
        session.setAttribute("cart", cart);
        session.setAttribute("numcart", 0);

        if (rol.equals("Administrador")) {
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/register")
    public String registrarUsuario(@ModelAttribute("usuario") UsuariosEntity usuarios) {
        return "login/registerForm";
    }

    @PostMapping("/saveUsuario")
    public String guardarUsuario(@ModelAttribute("usuario") @Valid UsuariosEntity usuarios,
                                 BindingResult bindingResult,
                                 RedirectAttributes attr,
                                 @RequestParam("dni") int dni,
                                 @RequestParam("dominio") int dominio,
                                 @RequestParam("pass") String pass,
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
        if (usuarios.getDni() != 0) {
            if (usuarios.getDni() < 10000000) {
                bindingResult.rejectValue("dni", "error.user", "Ingrese un DNI válido.");
            }
            if (usuarios.getDni() > 99999999) {
                bindingResult.rejectValue("dni", "error.user", "Ingrese un DNI válido.");
            }
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
        List<UsuariosEntity> listUsuarios = usuariosRepository.findAll();
        for (UsuariosEntity usu : listUsuarios) {
            if (usu.getDni() == usuarios.getDni()) {
                if (usuarios.getIdusuarios() == 0) {
                    bindingResult.rejectValue("dni", "error.user", "Este DNI ya existe.");
                } else {
                    if (!(usu.getDni() == usuarios.getDni())) {
                        bindingResult.rejectValue("dni", "error.user", "Este DNI ya existe.");
                    }
                }
            }
            if (usu.getCorreo().equals(usuarios.getCorreo())) {
                if (usuarios.getIdusuarios() == 0) {
                    bindingResult.rejectValue("correo", "error.user", "Este correo ya existe.");
                } else {
                    if (usu.getIdusuarios() != usuarios.getIdusuarios()) {
                        bindingResult.rejectValue("correo", "error.user", "Este correo ya existe.");
                    }
                }
            }
        }

        if (!usuarios.getPwd().isEmpty()) {
            if (!usuarios.getPwd().equals(pass)) {
                bindingResult.rejectValue("pwd", "error.user", "Las contraseñas no coinciden.");
            }
            if (usuarios.getPwd().trim().length() == 0) {
                bindingResult.rejectValue("pwd", "error.user", "Ingrese una contraseña.");
            }
            if (usuarios.getPwd().trim().length() < 8) {
                bindingResult.rejectValue("pwd", "error.user", "Ingrese una contraseña mayor a 8 caracteres.");
            }
            if (usuarios.getPwd().trim().length() > 10) {
                bindingResult.rejectValue("pwd", "error.user", "Ingrese una contraseña menor a 10 caracteres.");
            }
            if (Pattern.compile("[ ]").matcher(usuarios.getPwd()).find()) {
                bindingResult.rejectValue("pwd", "error.user", "La contraseña no puede contener espacios");
            }

            String nom = usuarios.getPwd();
            String[] parts = nom.split("[0-9]", 2);
            String part1 = parts[0];
            String part2 = parts[1];
            //if (!Pattern.compile("[0-9]").matcher(part1).find() || !Pattern.compile("[0-9]").matcher(part2).find()) {
            //    bindingResult.rejectValue("pwd", "error.user", "Debe contener al menos 2 números");
            //}
            if (part1.trim().length() < 6) {
                bindingResult.rejectValue("pwd", "error.user", "Alguno de los primeros 6 carateres no pueden ser números");
            }
            if (part1.trim().length() > 6) {
                bindingResult.rejectValue("pwd", "error.user", "Solo los primeros 6 caracteres son no númericos");
            }
            if (part1.trim().length() == 6) {
                if (part2.trim().length() == 1) {
                    if (!Pattern.compile("[0-9]").matcher(part2).find()) {
                        bindingResult.rejectValue("pwd", "error.user", "Debe contener al menos 2 números");
                    }
                }
                if (part2.trim().length() == 2) {
                    if (!Pattern.compile("[10-99]").matcher(part2).find()) {
                        bindingResult.rejectValue("pwd", "error.user", "Debe contener al menos 2 números");
                    }
                }
                if (part2.trim().length() == 3) {
                    if (!Pattern.compile("[10-999]").matcher(part2).find()) {
                        bindingResult.rejectValue("pwd", "error.user", "Debe contener al menos 2 números");
                    }
                }
            }
        }

        if (bindingResult.hasErrors()) {
            return "/login/registerForm";
        } else if (usuarios.getIdusuarios() == 0) {

            if (dominio == 1) {
                String mail = usuarios.getCorreo();
                usuarios.setCorreo(mail + "@pucp.edu.pe");
            } else if (dominio == 2) {
                String mail = usuarios.getCorreo();
                usuarios.setCorreo(mail + "@pucp.pe");
            }

            RolesEntity roles = new RolesEntity();
            roles.setIdroles(3);
            usuarios.setRol(roles);
            usuarios.setEnabled(true);
            String passEncrypt = new BCryptPasswordEncoder().encode(usuarios.getPwd());
            usuarios.setPwd(passEncrypt);
            attr.addFlashAttribute("msg", "¡Usted se ha registrado exitosamente! Ahora Inicie Sesión");

            usuariosRepository.save(usuarios);
        }
        //no funciona pero el query sí
        //usuariosRepository.saveGestor(usuarios.getIdusuarios(),usuarios.getNombre(),usuarios.getApellido(),
        //        usuarios.getDni(),usuarios.getCorreo(),usuarios.getPwd(), usuarios.getEnabled(),usuarios.getRol().getIdroles());

        return "redirect:/loginForm";
    }

    @GetMapping("/forgotPassword")
    public String olvideContraseña(@ModelAttribute("usuario") UsuariosEntity usuarios) {
        return "/login/forgetForm";
    }

    @PostMapping("/recuperarContraseña")
    public String recuperarContraseña(@ModelAttribute("usuario") @Valid UsuariosEntity usuarios,
                                      BindingResult bindingResult,
                                      RedirectAttributes attr,
                                      @RequestParam("recuperarcorreo") String mail,
                                      Model model) {

        List<UsuariosEntity> listUsuarios = usuariosRepository.findAll();
        for (UsuariosEntity usu : listUsuarios) {
            if (usu.getCorreo() != usuarios.getCorreo()) {
                bindingResult.rejectValue("correo", "error.user", "Este correo no se está registrado.");
            }
        }

        if (bindingResult.hasErrors()) {
            return "/login/forgetForm";
        } else if (usuarios.getCorreo().equals(mail)) {
            UsuariosEntity email = usuariosRepository.findUsuariosEntityByCorreo(mail);
            String pass = GeneratePwd.getAlphaString(8) + GeneratePwd.getAlphaNumeric(2);
            attr.addAttribute("msg", "Tu nueva contraseña es " + pass);
            String passEncrypt = new BCryptPasswordEncoder().encode(pass);

            email.setPwd(passEncrypt);
            usuariosRepository.save(email);
        }
        //UsuariosEntity email = usuariosRepository.findUsuariosEntityByCorreo(mail);

        return "redirect:/loginForm";
    }


}
