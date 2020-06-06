package exparcialg1.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/usuario"})
public class RegistradoController {
    @GetMapping(value = {"/"})
    public String index(Model m){
        return "";
    }
}
