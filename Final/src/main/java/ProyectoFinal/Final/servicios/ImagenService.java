package ProyectoFinal.Final.servicios;

import ProyectoFinal.Final.entidades.Imagen;
import ProyectoFinal.Final.excepciones.miException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ProyectoFinal.Final.repositorios.ImagenRepositorio;
import java.io.IOException;
import java.nio.file.Files;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Service
public class ImagenService {

    @Autowired
    ImagenRepositorio imgRepo;

    public Imagen guardar(MultipartFile archivo) throws miException {

        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return imgRepo.save(imagen);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    public Imagen modificarImagen(MultipartFile archivo, String id) throws miException {

        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();
                if (id != null) {
                    Optional<Imagen> respuesta = imgRepo.findById(id);

                    if (respuesta.isPresent()) {
                        imagen = respuesta.get();
                    }
                }

                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return imgRepo.save(imagen);

            } catch (Exception e) {
                System.err.println(e.getMessage());

            }
        }
        return null;

    }

    public Imagen obtenerImagenPorDefecto() {
        try {
            // Ruta relativa al directorio de recursos
            String rutaImagenPredeterminada = "static/img/perfilDefecto.jpg";

            Resource resource = new ClassPathResource(rutaImagenPredeterminada);
            byte[] contenidoImagen = Files.readAllBytes(resource.getFile().toPath());

            Imagen imagen = new Imagen();
            imagen.setMime("image/jpeg"); 
            imagen.setNombre("perfilDefecto.jpg");
            imagen.setContenido(contenidoImagen);

            return imagen;

        } catch (IOException e) {
       
            e.printStackTrace(); 
        }

        return null; 
    }

}