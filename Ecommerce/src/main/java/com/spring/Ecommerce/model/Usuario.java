package com.spring.Ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    public Usuario() {
    }
    /*
* Criar os seguintes atributos:
* -> nome, senha, email e funao do tipo String
* -> crieo os 2 construtores com parametros e sem parmetros
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
    private String nome, senha, email, funcao;

    public Long getId() {
        return id;
    }

    public Usuario(Long id, String nome, String senha, String email, String funcao) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.funcao = funcao;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
