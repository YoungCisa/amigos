package com.unitec.amigos;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControladorUsuario {
    @Autowired RepositorioUsuario repousuario;

    @PostMapping("/Usuario")
    public Estatus guardar (@RequestBody String json)throws Exception{
        ObjectMapper mapper= new ObjectMapper();
        Usuario u=mapper.readValue(json,Usuario.class);
        repousuario.save(u);
        Estatus estatus= new Estatus();
        estatus.setSucces(true);
        estatus.setMensaje("Usuario guardado con exito");
        return estatus;
    }

@GetMapping("/usuario/{id}")
    public Usuario obtenerPorID(@PathVariable String id){
    Usuario u =repousuario.findById(id).get();
return u;
}
@GetMapping("/usuario")
    public List<Usuario> buscarTodos(){
        return repousuario.findAll();
}
@PutMapping("/usuario")
public Estatus actualizar (@RequestBody String json)throws Exception{
        ObjectMapper mapper=new ObjectMapper();
        Usuario u = mapper.readValue(json, Usuario.class);
        Estatus e = new Estatus();
        if( repousuario.findById(u.getEmail()).isPresent()){
            repousuario.save(u);
            e.setMensaje("Usuario se actualizo con exito");
            e.setSucces(true);

    } else {
            e.setMensaje("Este usuario no se actualizara");
            e.setSucces(false);

    }
        return e;
}

}
