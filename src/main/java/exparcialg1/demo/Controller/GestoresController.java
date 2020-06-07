package exparcialg1.demo.Controller;

import exparcialg1.demo.Entity.PedidohasproductoEntity;
import exparcialg1.demo.Entity.ProductosEntity;
import exparcialg1.demo.Repository.PedidoHasProductoRepository;
import exparcialg1.demo.Repository.PedidosRepository;
import exparcialg1.demo.Repository.ProductosRepository;
import exparcialg1.demo.Repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "/gestor")
public class GestoresController {

    @Autowired
    UsuariosRepository usuariosRepository;
    @Autowired
    ProductosRepository productosRepository;
    @Autowired
    PedidosRepository pedidosRepository;
    @Autowired
    PedidoHasProductoRepository pedidoHasProductoRepository;

    @GetMapping(value = {"listaProdDispPaginaPrincipal", "/"})
    public String listaProdDispGestion(Model model) {
        model.addAttribute("listaProductosDisponibles", productosRepository.findAll());
        return "donpepe/index";
    }

    @GetMapping(value = "listaProdGestion")
    public String listaProdDispPaginaPrincipal(Model model) {
        model.addAttribute("listaProductos", productosRepository.findAll());
        return "gestor/listaProductos";
    }

    @GetMapping(value = {"/formulario"})
    public String formulario(@ModelAttribute("producto") ProductosEntity producto) {

        return "gestor/formulario";
    }

    @GetMapping("/editar")
    public String editarProducto(@ModelAttribute("producto") ProductosEntity producto,
                                 Model model, @RequestParam("id") String id) {

        Optional<ProductosEntity> optProducto = productosRepository.findById(id);
        if (optProducto.isPresent()) {
            producto = optProducto.get();
            model.addAttribute("producto", producto);
            return "gestor/formulario";
        } else {
            return "redirect:/gestor/listaProdGestion";
        }
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") @Valid ProductosEntity producto,
                                  BindingResult bindingResult, RedirectAttributes attr, Model model, @RequestParam("foto1") MultipartFile foto1) {
        if (!producto.getNombre().isEmpty()) {
            if (Pattern.compile("[0-9]").matcher(producto.getNombre()).find()) {
                bindingResult.rejectValue("nombre", "error.user", "No se permiten valores numéricos.");
            }
            if (producto.getNombre().trim().length() == 0) {
                bindingResult.rejectValue("nombre", "error.user", "Ingrese color válido.");
            }
        }

        //Generacion del codigo
        String nom = producto.getNombre();
        char[] nomArreglo = nom.toCharArray();
        String primero = (Character.toString(nomArreglo[0])).toUpperCase();
        String ultimo = (Character.toString(nomArreglo[nomArreglo.length - 1])).toUpperCase();
        List<ProductosEntity> listProductos = productosRepository.findAll();
        int ultNum = listProductos.size() + 1;
        String cad = Integer.toString(ultNum);
        while (cad.length() < 4) {
            cad = "0" + cad;
        }
        String codProducto =primero + cad + ultimo;

        for (ProductosEntity prodTienda : listProductos) {

            if (prodTienda.getNombre().equals(producto.getNombre())) {
                if (producto.getCodproducto() == null) {
                    bindingResult.rejectValue("nombre", "error.user", "El nombre de este producto ya existe.");
                } else {
                    if (!prodTienda.getCodproducto().equals(producto.getCodproducto())) {
                        bindingResult.rejectValue("nombre", "error.user", "El nombre de este producto ya existe.");
                    }
                }
            }

        }

        if (ultimo.equals(" ") || primero.equals(" ")) {
            bindingResult.rejectValue("nombre", "error.user", "No se permiten espacios al inicio o al final del nombre.");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("msg", "Error al ingresar los datos, verifique que los datos sean validos.");
            return "gestor/formulario";
        } else {
            Optional<ProductosEntity> optProducto = productosRepository.findById(producto.getCodproducto());
            if (optProducto.isPresent()) {
                attr.addFlashAttribute("msg", "Producto actualizado exitosamente");
            } else {
                producto.setCodproducto(codProducto);
                try {
                    String fotoname=codProducto + ".jpg";
                    String path = "src/main/resources/images/" +fotoname;

                    ByteArrayInputStream bis = new ByteArrayInputStream(foto1.getBytes());
                    BufferedImage bImage2 = ImageIO.read(bis);
                    ImageIO.write(bImage2, "jpg", new File(path));
                    producto.setFoto(fotoname);
                    model.addAttribute("msg", "Archivo subido");
                } catch (Exception e) {
                    model.addAttribute("msg", "Error con foto");
                    bindingResult.rejectValue("foto", "error.user", "Error con foto");
                    return "gestor/formulario";
                }
                attr.addFlashAttribute("msg", "Producto creado exitosamente");
            }
            productosRepository.save(producto);
            return "redirect:/gestor/listaProdGestion";
        }
    }

    @GetMapping("/borrar")
    public String borrarProducto( @ModelAttribute("producto") ProductosEntity producto, Model model,
                                  @RequestParam("id") String id, RedirectAttributes attr) {

        List<PedidohasproductoEntity> listaPedidosPorProducto = pedidoHasProductoRepository.buscarPedidosPorProducto(id);
        if(listaPedidosPorProducto.isEmpty()){
            productosRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Producto borrado exitosamente");
        } else{
            attr.addFlashAttribute("msgNoBorrado", "No se puede borrar un producto que ya ha sido comprado por un usuario");
        }
        return "redirect:/gestor/listaProdGestion";
    }

    //Estadisticas
    @GetMapping("estadisticas")
    public String estadisticas(Model model){
        model.addAttribute("cantidadCompras",pedidosRepository.obtenerCantCompras());
        model.addAttribute("totalFacturado",pedidosRepository.obtenerTotalFacturado());
        model.addAttribute("cantidadProductosVendidos",productosRepository.obtenerCantprodvendidos());
        model.addAttribute("listaProductoMasVendido",productosRepository.obtenerProductoMasVendido());
        model.addAttribute("listaProductoMenosVendido",productosRepository.obtenerProductoMenosVendido());
        model.addAttribute("listaProductoMasCaro",productosRepository.obtenerProdMasCaro());
        model.addAttribute("listaUsuarioQueGastoMas",usuariosRepository.obtenerUsuarioQueGastoMas());
        return "gestor/estadisticas";
    }



}
