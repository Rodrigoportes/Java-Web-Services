package br.com.infnet.AT_ServiWebJava.service;
import br.com.infnet.AT_ServiWebJava.exception.ResourceNotFoundException;
import br.com.infnet.AT_ServiWebJava.util.CriaturaUtil;
import br.com.infnet.AT_ServiWebJava.model.Criatura;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service @Getter
public class CriaturaService {
    private Map<Integer, Criatura> listaC = aplicaCriaturas();
    private int lastId = 20;
    public Map<Integer,Criatura> aplicaCriaturas() {
        Map<Integer, Criatura> listaCriaturas = new HashMap<>();
        for (int i = 1; i < 21; i++) {
            Criatura criatura = CriaturaUtil.getCriaturaById(i);
            listaCriaturas.put(criatura.getId(), criatura);
        }
        return listaCriaturas;
    }

    public List<Criatura> getAllCriaturas() {
        return listaC.values().stream().toList();
    }

    public Criatura getById(int id) {
        Criatura criaturas = listaC.get(id);
        if(criaturas == null) throw new ResourceNotFoundException("Criatura não localizada");
        return criaturas;
    }

    public void deleteById(int id){
        if(clienteNaoExiste(id)) throw new ResourceNotFoundException("Criatura inexistente");
        listaC.remove(id);
    }

    public void update(int id, Criatura criatura){
        if(clienteNaoExiste(id)) throw new ResourceNotFoundException("Criatura inexistente");
        criatura.setId(id);
        listaC.replace(id,criatura);
    }

    public int getLastId(){
        return this.lastId;
    }

    public void incrementLastId(){
        this.lastId++;
    }

    public void create(Criatura criatura) {
        int id = ++this.lastId;
        criatura.setId(id);
        listaC.put(criatura.getId(),criatura);
    }

    private boolean clienteNaoExiste(int id) {
        return !listaC.containsKey(id);
    }

    public List<Criatura> filterByName(String name, int size) {
        List<Criatura> all = getAllCriaturas();
        return all.stream()
                .filter(criatura -> criatura.getName().startsWith(name))
                .limit(size)
                .collect(Collectors.toList());
    }
}
