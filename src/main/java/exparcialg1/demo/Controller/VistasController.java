package exparcialg1.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/vistas"})
public class VistasController {

    @GetMapping(value = {"/{page}"})
    public String index(@PathVariable String page, Model m){
        int num=1;
        m.addAttribute("num",num);
        return "donpepe/" +page;
    }
}
