package com.gba.contatosbackend.models;

import javax.persistence.*;

@Entity
@Table(name = "contatos")
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "data_nascimento")
    private String dataNascimento;

    @Column(name = "img_path", nullable = true, length = 255)
    private String imgPath;

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Contato() {
    }

    public Contato(String nome, String email, String telefone, String dataNascimento, String imgPath) {
        super();
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.imgPath = imgPath;
    }

    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Transient
    public String getImgPath() {
        if (imgPath == null) return "https://whova.com/wp-content/uploads/2020/10/Untitled-design-18.png";
        return ("/profilePics/" + id + "/" + imgPath);
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
