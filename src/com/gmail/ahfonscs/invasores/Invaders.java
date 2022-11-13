package com.gmail.ahfonscs.invasores;

import java.awt.Color;
import java.awt.Graphics;

public class Invaders
  extends Entidade
{
  public static final int VELOCIDADE = 4;
  
  public Invaders(int largura, int altura, int x, int y, Color cor) {
    super(largura, altura, x, y, cor);
  }


  
  public void atualizar() {
    if (Ambiente.derrotaInimiga) {
      retrosceder();
      
      return;
    } 
    setY(getY() + 4);
    
    if (getY() >= 400) {
      setAtivo(false);
    }
  }


  
  private void retrosceder() {
    setY(getY() - 4);
    
    if (getY() < 0) {
      setAtivo(false);
    }
  }


  
  public void desenhar(Graphics g) {
    if (isAtivo()) {
      g.setColor(getCor());
      g.fillRect(getX(), getY(), getLargura(), getAltura());
    } 
  }
}
