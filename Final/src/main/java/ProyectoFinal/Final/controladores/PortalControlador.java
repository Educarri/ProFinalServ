package ProyectoFinal.Final.controladores;

import ProyectoFinal.Final.entidades.Proveedor;
import ProyectoFinal.Final.entidades.Usuario;
import ProyectoFinal.Final.enumeraciones.Rol;
import ProyectoFinal.Final.servicios.ProveedorService;
import ProyectoFinal.Final.servicios.UsuarioService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/") //configura que url escuchara este controlador usando la / en este caso
public class PortalControlador {

    @Autowired
    UsuarioService usuServ;

    @Autowired
    ProveedorService proServ;

    //MODIFICO LA PAGINA DE INICIO PARA QUE SE VEAN LOS PROVEEDORES APENAS ACCEDES A LA PAGINA PRINCIPAL
    /* 
    @GetMapping("/") //cuando en la url ponga la / se ejecutara este metodo en este caso, al principio apenas
    //abro la pagina
    public String index() {
        return "index.html";
    }
     */
    @GetMapping("/")
    public String listarProveedores(ModelMap modelo) {
        List<Proveedor> proveedores = proServ.listarProveedores();
        modelo.addAttribute("proveedores", proveedores);

        return "index.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {

        if (error != null) {
            modelo.put("error", "Usuario o contraseña invalidos.");
        }
        return "login.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN' , 'ROLE_PROVEEDOR')")
    @GetMapping("/inicio")
    public String inicio(HttpSession sesion) {
        Usuario logeado = (Usuario) sesion.getAttribute("usuarioSesion");

        if (logeado.getRol().toString().equals("ADMIN")) {

            return "redirect:/admin/dashboard";
        } else if (logeado.getRol().toString().equals("PROVEEDOR")) {
            return "inicioProveedor.html";
        } else if(logeado.getRol().toString().equals("USER")){
            return "inicio.html";//seria para el cliente
        }      
        return "login.html"; 
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_PROVEDOR')")
    @GetMapping("/perfil")
    public String perfil(ModelMap modelo, HttpSession sesion) {
        Usuario user = (Usuario) sesion.getAttribute("usuarioSesion");

        if (user != null) {
            if (user.getRol().equals(Rol.USER)) {
                modelo.put("user", user);
                return "cliente_Modificar";
            } else if(user.getRol().equals(Rol.PROVEEDOR)) {
                modelo.put("user", user);
                return "proveedor_modificar";
            } else if(user.getRol().equals(Rol.ADMIN)){
                 modelo.put("user", user);
                return "admin_modificar";
            }
        } else {
            modelo.put("error", "Usuario no encontrado");       
        }
        return "/perfil"; 
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @PostMapping("/perfil/{id}")
    public String actualizar(@PathVariable String id,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam Long dni,
            @RequestParam String correo,
            @RequestParam Integer telefono,
            @RequestParam String direccion,
            @RequestParam String password, ModelMap modelo) {

        try {
            usuServ.actualizarUsuario(id, nombre, apellido, dni, correo, telefono, direccion, password);
            modelo.put("exito", "Usuario modificado correctamente.");
            return "inicio";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "usuario_Modificar";
        }
    }

}
