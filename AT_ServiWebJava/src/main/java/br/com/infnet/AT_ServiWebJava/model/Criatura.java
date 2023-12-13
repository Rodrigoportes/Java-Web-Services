package br.com.infnet.AT_ServiWebJava.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Criatura {
    @JsonProperty("name")
    private String name;

    @JsonProperty("common_locations")
    private List<String> commonLocations;

    @JsonProperty("id")
    private int id;

}

