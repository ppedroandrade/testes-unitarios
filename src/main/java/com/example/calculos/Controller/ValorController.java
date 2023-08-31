package com.example.calculos.Controller;

import com.example.calculos.DTO.ValoresDTO;
import com.example.calculos.Service.ValorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/valor")
public class ValorController {
    @Autowired
    ValorService valorService;

    @GetMapping("/list")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(valorService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final ValoresDTO valores) {
        try {
            valorService.cadastra(valores);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro " + e.getMessage());
        }
        return ResponseEntity.ok("Media: " + valorService.media(valores.getValores()) + "\n" +
                "Desvio padrão: " + valorService.desvioPadrao(valores.getValores()) + "\n" +
                "Mediana: " + valorService.mediana(valores.getValores()) + "\n" +
                "Quantidade de dados recebidos: " + valores.getValores().size() + "\n" +
                "Soma dos "
        );
    }
}
