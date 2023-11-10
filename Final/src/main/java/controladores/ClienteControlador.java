/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import servicios.ClienteService;


@Controller
@RequestMapping("/cliente")
public class ClienteControlador {
    
      @Autowired
      private ClienteService cliService;
      
    @GetMapping("/registrar")
    public String registrar() {
        return "autor_form.html";
    }

      
    
}
