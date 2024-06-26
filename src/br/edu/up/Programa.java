package br.edu.up;

import br.edu.up.daos.GerenciadorDeArquivos;

public class Programa {

    public static void main(String[] args) {
        GerenciadorDeArquivos gerenciador = new GerenciadorDeArquivos();
        boolean sucesso = gerenciador.processarArquivos("src/br/edu/up/resources/pessoas.csv",
                "src/br/edu/up/resources/enderecos.csv", "src/br/edu/up/resources/PessoasComEndereco.csv");

        if (sucesso) {
            System.out.println("Arquivos processados com sucesso!");
        } else {
            System.out.println("Houve um erro ao processar os arquivos.");
        }

    }
}
