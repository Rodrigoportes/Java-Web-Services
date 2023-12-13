package br.com.infnet.AT_ServiWebJava;
import br.com.infnet.AT_ServiWebJava.exception.ResourceNotFoundException;
import br.com.infnet.AT_ServiWebJava.util.CriaturaUtil;
import lombok.Data;
import lombok.extern.java.Log;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.infnet.AT_ServiWebJava.model.Criatura;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Log
@SpringBootTest
@Data
public class CriaturaUtilTests {
    @Test
    @DisplayName("Teste Requisição")
    public void deveretornaroscriaturas() {
        CriaturaUtil criaturaUtil = new CriaturaUtil();
        Criatura criatura = criaturaUtil.getCriaturaById(1);
        assertEquals("horse", criatura.getName());
    }

    @Test
    @DisplayName("Deve retornar uma exceção para uma criatura inexistente")
    public void testaCriaturaInexistente(){
        CriaturaUtil criaturaUtil = new CriaturaUtil();
        assertThrows(ResourceNotFoundException.class, () -> {
            Criatura criatura = criaturaUtil.getCriaturaById(-1);
        });
    }

}
