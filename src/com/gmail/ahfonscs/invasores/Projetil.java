package com.gmail.ahfonscs.invasores;

import java.awt.Color;
import java.awt.Graphics;









public class Projetil
  extends Entidade
{
  public static final int VELOCIDADE = 12;
  
  public Projetil(int largura, int altura, int x, int y, Color cor) {
    super(largura, altura, x, y, cor);
  }


  
  public void atualizar() {
    setY(getY() - 12);
    
    if (getY() <= 0) {
      setAtivo(false);
    }
  }


  
  public void desenhar(Graphics g) {
    if (isAtivo()) {
      g.setColor(getCor());
      g.fillRect(getX(), getY(), getAltura(), getLargura());
    } 
  }
}
