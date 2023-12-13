package br.com.infnet.AT_ServiWebJava.util;
import br.com.infnet.AT_ServiWebJava.exception.ResourceNotFoundException;
import br.com.infnet.AT_ServiWebJava.model.Criatura;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.java.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Log
public class CriaturaUtil {
    public static Criatura getCriaturaById(int id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .version(HttpClient.Version.HTTP_2)
                    .uri(new URI("https://botw-compendium.herokuapp.com/api/v3/compendium/entry/" + id))
                    .build();
            HttpClient criatura = HttpClient.newBuilder().build();
            HttpResponse<String> response = criatura.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 404) {
                throw new ResourceNotFoundException("Criatura não encontrada");
            }

            ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
            JsonNode jsonNode = mapper.readTree(response.body());
            JsonNode dataNode = jsonNode.get("data");
            log.info(String.valueOf(response.statusCode()));
            return mapper.treeToValue(dataNode, Criatura.class);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


