package ProyectoFinal.Final.servicios;

import ProyectoFinal.Final.entidades.Imagen;
import ProyectoFinal.Final.excepciones.miException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ProyectoFinal.Final.repositorios.ImagenRepositorio;


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
                if(id != null){
                    Optional<Imagen> respuesta = imgRepo.findById(id);
                    
                    if(respuesta.isPresent()){
                        imagen =  respuesta.get();
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

}
