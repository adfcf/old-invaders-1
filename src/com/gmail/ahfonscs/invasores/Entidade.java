package com.gmail.ahfonscs.invasores;

import java.awt.Color;
import java.awt.Graphics;













public abstract class Entidade
{
  private final int largura;
  private final int altura;
  private int x;
  private int y;
  private boolean ativo;
  private boolean emColisao;
  private Color cor;
  
  public Entidade(int largura, int altura, int x, int y, Color cor) {
    this.largura = largura;
    this.altura = altura;
    this.cor = cor;
    this.x = x;
    this.y = y;
    
    this.ativo = true;
  }

  
  public abstract void atualizar();
  
  public abstract void desenhar(Graphics paramGraphics);
  
  public int getLargura() {
    return this.largura;
  }
  
  public int getAltura() {
    return this.altura;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public boolean isAtivo() {
    return this.ativo;
  }
  
  public boolean isEmColisao() {
    return this.emColisao;
  }
  
  public Color getCor() {
    return this.cor;
  }
  
  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }
  
  protected void setX(int x) {
    this.x = x;
  }
  
  protected void setY(int y) {
    this.y = y;
  }
  
  protected void setCor(Color cor) {
    this.cor = cor;
  }
}
