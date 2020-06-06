package exparcialg1.demo.Controller;

import exparcialg1.demo.Entity.ProductosEntity;
import exparcialg1.demo.Repository.ProductosRepository;
import exparcialg1.demo.Repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/gestor")
public class GestoresController {

    @Autowired
    UsuariosRepository usuariosRepository;
    @Autowired
    ProductosRepository productosRepository;

    @GetMapping(value ="listaProdDispPaginaPrincipal")
    public String listaProdDispGestion(Model model){
        model.addAttribute("listaProductosDisponibles",productosRepository.findAll());
        return "donpepe/index";
    }

    @GetMapping(value ="listaProdGestion")
    public String listaProdDispPaginaPrincipal(Model model){
        model.addAttribute("listaProductos",productosRepository.findAll());
        return "gestor/listaProductos";
    }

    @GetMapping(value = {"/nuevo"})
    public String form(@ModelAttribute("producto") ProductosEntity producto, Model model) {
        return "gestor/formulario";
    }

    @GetMapping("/editar")
    public String editarProducto(@ModelAttribute("producto") ProductosEntity producto,
                                      Model model, @RequestParam("id") String id) {

        Optional<ProductosEntity> optProducto = productosRepository.findById(id);
        if (optProducto.isPresent()) {
            producto= optProducto.get();
            model.addAttribute("producto", producto);
            return "gestor/formulario";
        } else {
            return "redirect:/listaProdGestion";
        }
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") @Valid ProductosEntity producto,
                          BindingResult bindingResult, @RequestParam("type") int type,
                          RedirectAttributes attr, Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("formtype",Integer.toString(type));
            model.addAttribute("listaProductosDisponibles", productosRepository.findAll());
            model.addAttribute("msg", "ERROR");
            return "gestor/listaProductos";
        }
        else {
            Optional<ProductosEntity> optProducto = productosRepository.findById(producto.getCodproducto());
            if (optProducto.isPresent()) {
                attr.addFlashAttribute("msg", "Producto actualizado exitosamente");
            }
            else {
                attr.addFlashAttribute("msg", "Producto creado exitosamente");
            }
            productosRepository.save(producto);
            return "redirect:/gestor/listaProdDispGestion";
        }
    }


}
