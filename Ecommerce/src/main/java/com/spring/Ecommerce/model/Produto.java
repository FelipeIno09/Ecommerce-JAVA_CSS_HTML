package com.spring.Ecommerce.model;

import jakarta.persistence.*;

@Entity
public class Produto {
    /*
     * Criar os seguintes atributos:
     * -> nome do tipo string
     * -> precoDolar Double
     * ->
     * -> Crie o did do tipo long
     * -> crie os 2 construtores com parametros e sem parmetros
     * -> Crie os metodos Getter & steers
     * -> Transforma a classe model em tabel do BD:
     *       -> Anotação @Entity
     *       ->@Id
     *       ->Autoincremente do Id
     *
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double precoDolar;
    @Lob//cria o tamanho padrão de arquivo byte no sql
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] imagem;

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public Produto(byte[] imagem) {
        this.imagem = imagem;
    }

    public Produto() {
    }

    public Produto(Long id, String nome, double precoDolar) {
        this.id = id;
        this.nome = nome;
        this.precoDolar = precoDolar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoDolar() {
        return precoDolar;
    }

    public void setPrecoDolar(double precoDolar) {
        this.precoDolar = precoDolar;
    }
}
