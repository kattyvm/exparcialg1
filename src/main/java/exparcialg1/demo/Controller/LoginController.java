package exparcialg1.demo.Controller;

import exparcialg1.demo.Entity.UsuariosEntity;
import exparcialg1.demo.Repository.UsuariosRepository;
import exparcialg1.demo.constantes.ProductoCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class LoginController {

    @Autowired
    UsuariosRepository usuariosRepository;

    @GetMapping("/loginForm")
    public String loginForm(){
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
        ArrayList<ProductoCarrito> cart =new ArrayList<>();
        session.setAttribute("cart",cart);
        session.setAttribute("numcart",0);

        if (rol.equals("Administrador")) {
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }

}
