package com.devdavi.cardview.model;

public class Post {
    private String username;
    private String postagem;
    private int imagem;

    public Post() {
    }

    public Post(String username, String postagem, int imagem) {
        this.username = username;
        this.postagem = postagem;
        this.imagem = imagem;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPostagem() {
        return postagem;
    }

    public void setPostagem(String postagem) {
        this.postagem = postagem;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
