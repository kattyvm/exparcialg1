package exparcialg1.demo.Controller;

import exparcialg1.demo.Entity.ProductosEntity;
import exparcialg1.demo.Repository.ProductosRepository;
import exparcialg1.demo.constantes.ProductoCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/"})
public class MainController {
    @Autowired
    ProductosRepository productosRepository;


    @GetMapping(value = {"/"})
    public String index(Model m) {
        List<ProductosEntity> listProd = productosRepository.findAll();

        m.addAttribute("path", "A0006I.jpg");
        m.addAttribute("listProd", listProd);
        return "donpepe/pgPrincipal";
    }
    @GetMapping(value = {"/detalles"})
    public String detalles(@RequestParam("id") String id,Model m) {

        Optional<ProductosEntity> opt = productosRepository.findById(id);
        if (!opt.isPresent()) {
            return "redirect:/";
        }
        ProductosEntity prod= opt.get();
        m.addAttribute("path", "A0006I.jpg");
        m.addAttribute("prod", prod);

        return "donpepe/detalles";
    }



    @PostMapping(value = {"/save"})
    public String save(@RequestParam("file") MultipartFile file, ModelMap map) {
        int num = 4;
        map.addAttribute("num", num);
        map.addAttribute("file", file);
        Optional<ProductosEntity> opt = productosRepository.findById("A0006I");
        ProductosEntity prod = opt.get();
        /*try {
            prod.setFoto(file.getBytes());
            productosRepository.save(prod);
            map.addAttribute("msg", "Archivo subido");
        } catch (Exception e) {
            map.addAttribute("msg", "Error con archivo");
        }*/

        try {
            String path = "src/main/resources/images/" + prod.getCodproducto() + ".jpg";
            /*BufferedImage bImage = ImageIO.read(new File("path"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            byte[] data = bos.toByteArray();*/
            ByteArrayInputStream bis = new ByteArrayInputStream(file.getBytes());
            BufferedImage bImage2 = ImageIO.read(bis);
            ImageIO.write(bImage2, "jpg", new File(path));
            map.addAttribute("msg", "Archivo subido");
        } catch (Exception e) {
            map.addAttribute("msg", "Error con archivo");
        }
        map.addAttribute("path", "A0006I.jpg");
        return "donpepe/pgPrincipal";

    }
}
