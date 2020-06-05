package exparcialg1.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/",""})
public class MainController {

    @GetMapping(value = {"/{page}"})
    public String index(@PathVariable String page){
        return page;
    }
}
