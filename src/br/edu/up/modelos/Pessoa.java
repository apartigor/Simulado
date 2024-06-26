package br.edu.up.modelos;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {
    private int codigo;
    private String nome;
    private List<Endereco> enderecos;

    public Pessoa(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.enderecos = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void addEndereco(String rua, String cidade) {
        this.enderecos.add(new Endereco(rua, cidade));
    }

    public List<String> toCSV() {
        List<String> linhaCSV = new ArrayList<>();
        for (Endereco endereco : enderecos) {
            linhaCSV.add(codigo + ";" + nome + ";" + endereco.getRua() + ";" + endereco.getCidade());
        }
        return linhaCSV;
    }

    private static class Endereco {
        private String rua;
        private String cidade;

        public Endereco(String rua, String cidade) {
            this.rua = rua;
            this.cidade = cidade;
        }

        public String getRua() {
            return rua;
        }

        public String getCidade() {
            return cidade;
        }
    }
}
