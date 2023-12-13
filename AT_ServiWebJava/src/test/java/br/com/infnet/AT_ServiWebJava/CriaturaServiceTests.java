package br.com.infnet.AT_ServiWebJava;

import br.com.infnet.AT_ServiWebJava.model.Criatura;
import br.com.infnet.AT_ServiWebJava.service.CriaturaService;
import lombok.extern.java.Log;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest@Log
public class CriaturaServiceTests {
    @Autowired
    CriaturaService criaturaService;

    List<String> commonLocations = Arrays.asList("asdasd", "asdasdas", "asdasd");

    @Test
    @DisplayName("Deve inicializar corretamente a lista de criaturas")
    public void deveInicializarLista() {

        CriaturaService criaturaService = new  CriaturaService();
        Map<Integer, Criatura> criatura = criaturaService.aplicaCriaturas();

        assertEquals(20, criatura.size());
        log.info("A criatura é:" + criatura);

    }

    @Test
    @DisplayName("Deve deve retornar todas as criaturas")
    public void deveRetornarTodasCriaturas() {

        List<Criatura> criaturas = criaturaService.getAllCriaturas();
        assertEquals(20, criaturas.size());

    }

    @Test
    @DisplayName("Deve deve retornar uma criatura pelo id")
    public void deveRetornarCriaturasId() {

        Criatura criatura = criaturaService.getById(5);
        assertEquals(criatura.getName(),"stalhorse");
    }

    @Test
    @DisplayName("Deve deve remover uma criatura pelo id")
    public void deveRemoverCriaturasId() {
            criaturaService.deleteById(1);
            List<Criatura> criaturas = criaturaService.getAllCriaturas();
            assertEquals(19, criaturas.size());
    }
    @Test
    @DisplayName("Atualiza Criatura pelo id")
    public void deveAtualizarCriaturasId() {
        Criatura criatura = new Criatura("laluco", commonLocations, 1);
        criaturaService.update(1,criatura);
        Criatura atualizada = criaturaService.getById(1);
        assertEquals("laluco", atualizada.getName());
    }
    @Test
    @DisplayName("Deve inserir uma criatura")
    public void testaInseri(){
        Criatura novacriat = Criatura.builder()
                .name("Marmota")
                .commonLocations(commonLocations)
                .build();
        criaturaService.create(novacriat);
        List<Criatura> all = criaturaService.getAllCriaturas();
        Criatura retornado = criaturaService.getById(21);
        assertEquals(21, all.size());
        assertEquals("Marmota", retornado.getName());
        assertEquals(21, retornado.getId());
    }

}
