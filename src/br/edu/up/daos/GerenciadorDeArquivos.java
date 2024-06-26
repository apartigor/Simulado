package br.edu.up.daos;

import br.edu.up.modelos.Pessoa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeArquivos {

    public boolean processarArquivos(String pessoasFile, String enderecosFile, String outputFile) {
        try {
            List<Pessoa> pessoas = lerPessoas(pessoasFile);
            associarEnderecos(pessoas, enderecosFile);
            escreverPessoasComEnderecos(outputFile, pessoas);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<Pessoa> lerPessoas(String pessoasFile) throws IOException {
        List<Pessoa> pessoas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pessoasFile))) {
            String line;
            br.readLine(); // Pular cabeçalho
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                int codigo = Integer.parseInt(values[0].trim());
                String nome = values[1];
                pessoas.add(new Pessoa(codigo, nome));
            }
        }
        return pessoas;
    }

    private void associarEnderecos(List<Pessoa> pessoas, String enderecosFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(enderecosFile))) {
            String line;
            br.readLine(); // Pular cabeçalho
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                String rua = values[0];
                String cidade = values[1];
                int codigo = Integer.parseInt(values[2].trim());

                for (Pessoa pessoa : pessoas) {
                    if (pessoa.getCodigo() == codigo) {
                        pessoa.addEndereco(rua, cidade);
                    }
                }
            }
        }
    }

    private void escreverPessoasComEnderecos(String outputFile, List<Pessoa> pessoas) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("codigo;nome;rua;cidade");
            bw.newLine();
            for (Pessoa pessoa : pessoas) {
                for (String csvLine : pessoa.toCSV()) {
                    bw.write(csvLine);
                    bw.newLine();
                }
            }
        }
    }
}
