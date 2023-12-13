package br.com.infnet.AT_ServiWebJava.controller;

import br.com.infnet.AT_ServiWebJava.exception.ResourceNotFoundException;
import br.com.infnet.AT_ServiWebJava.model.Criatura;
import br.com.infnet.AT_ServiWebJava.model.ResponsePayload;
import br.com.infnet.AT_ServiWebJava.service.CriaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/criatura")
public class CriaturaController {
    @Autowired
    CriaturaService criaturaService;

    @GetMapping
    public List<Criatura> getAll(@RequestParam(required = false, defaultValue = "50")int size,
                                 @RequestParam(required = false)Optional<String> name){

        List<Criatura> criaturas = criaturaService.getAllCriaturas();

        if(name.isPresent()){
            return criaturaService.filterByName(name.get(), size);
        }else{
            return criaturas.subList(0, Math.min(size, criaturas.size()));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable int id){
        try {
            Criatura criatura = criaturaService.getById(id);
            return ResponseEntity.ok(criatura);
        }catch (ResourceNotFoundException ex){
            ResponsePayload responsePayload = new ResponsePayload(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responsePayload);
        }
    }

    @PostMapping
    public ResponseEntity<ResponsePayload> create(@RequestBody Criatura criatura){
        criaturaService.create(criatura);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponsePayload("Criatura cadastrada com sucesso!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePayload> delete(@PathVariable int id){
        try{
            criaturaService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new ResponsePayload("Criatura deletada com sucesso!"));
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponsePayload(ex.getMessage()));
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePayload> update(@PathVariable int id, @RequestBody Criatura atualizada){
        try{
            criaturaService.update(id, atualizada);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new ResponsePayload("Criatura alterada com sucesso!"));
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponsePayload(ex.getMessage()));
        }

    }


}
