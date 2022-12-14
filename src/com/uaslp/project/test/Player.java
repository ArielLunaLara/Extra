package com.uaslp.project.test;

public class Player {
    private String Nombre;
    private  int Tiempo;
    private String score;

    public Player(String score) {
        this.score = score;
    }
    public Player (String nombre){
        this.Nombre=nombre;
    }
    public Player(int tiempo){

        this.Tiempo = tiempo;
    }
}
