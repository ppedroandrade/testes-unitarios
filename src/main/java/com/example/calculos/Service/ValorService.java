package com.example.calculos.Service;

import com.example.calculos.DTO.ValoresDTO;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ValorService {
    public List<Double> cadastra(ValoresDTO valores) {
        List<Double> valoresList = valores.getValores();
        if (valoresList == null || valoresList.size() < 20) {
            throw new RuntimeException("A lista deve conter no mínimo 20 valores");
        }
        return valoresList;
    }

    public List<Double> getAll() {
        List<Double> valores = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            valores.add(i * 4.0 - 3.0);
        }
        return valores;
    }

    public double calcularSoma(List<Double> valores) {
        double soma = 0.0;

        for (double valor : valores) {
            soma += valor;
        }

        return soma;
    }

    public double maxValue(List<Double> valores) {
        double max = Double.NEGATIVE_INFINITY;
        for (Double valor : valores) {
            if (valor > max) {
                max = valor;
            }
        }
        return max;
    }

    public double minValue(List<Double> valores) {
        double min = Double.POSITIVE_INFINITY;
        for (Double valor : valores) {
            if (valor < min) {
                min = valor;
            }
        }
        return min;
    }

    public double media(List<Double> valores) {
        double total = 0;

        for (Double valor : valores) {
            total += valor;
        }

        return total / valores.size();
    }

    public double desvioPadrao(List<Double> valores) {
        double media = media(valores);
        double somaQuadrados = 0;

        for (Double valor : valores) {
            double diferenca = valor - media;
            somaQuadrados += diferenca * diferenca;
        }

        double variancia = somaQuadrados / valores.size();
        double dp = Math.sqrt(variancia);
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedDesvioPadrao = df.format(dp).replace(',', '.');
        return Double.parseDouble(formattedDesvioPadrao);
    }

    public double mediana(List<Double> valores) {
        Collections.sort(valores);

        int tamanho = valores.size();

        if (tamanho % 2 != 0) {
            int indiceMediana = tamanho / 2;
            return valores.get(indiceMediana);
        } else {
            int indiceMediana1 = (tamanho - 1) / 2;
            int indiceMediana2 = tamanho / 2;
            double mediana1 = valores.get(indiceMediana1);
            double mediana2 = valores.get(indiceMediana2);
            return (mediana1 + mediana2) / 2;
        }
    }
}
