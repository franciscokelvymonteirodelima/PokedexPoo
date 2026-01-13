package model.batalha;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controlador implements KeyListener{

    Gerenciador g;
    public boolean cima, baixo, esquerda, direita, ataque;

    public Controlador(Gerenciador g){
        this.g = g;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) cima = true;
        if(code == KeyEvent.VK_S) baixo = true;
        if(code == KeyEvent.VK_A) esquerda = true;
        if(code == KeyEvent.VK_D) direita = true;
        if(code == KeyEvent.VK_T) ataque = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) cima = false;
        if(code == KeyEvent.VK_S) baixo = false;
        if(code == KeyEvent.VK_A) esquerda = false;
        if(code == KeyEvent.VK_D) direita = false;
        if(code == KeyEvent.VK_T) ataque = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
