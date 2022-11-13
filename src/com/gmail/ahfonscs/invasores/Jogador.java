package com.gmail.ahfonscs.invasores;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;










public class Jogador
  extends Entidade
{
  public static final int VELOCIDADE = 9;
  private final Font fonteVencedor = new Font("Arial", 1, 26);
  private final Font fontePerdedor = new Font("Arial", 1, 26);
  private final Font fontePontuacao = new Font("Arial", 0, 18);
  
  private final boolean[][] direcoes = new boolean[4][2];
  private int score = 0;
  private int vidas = 3;
  
  public Jogador(int largura, int altura, int x, int y, Color cor) {
    super(largura, altura, x, y, cor);
  }


  
  public void atualizar() {
    this.direcoes[0][1] = (getY() <= 1);
    this.direcoes[2][1] = (getX() <= 1);
    this.direcoes[1][1] = (getY() + getAltura() * 2 >= 400);
    this.direcoes[3][1] = (getX() + getLargura() * 1.25D >= 650.0D);
    
    if (this.direcoes[0][0] && !this.direcoes[0][1]) {
      setY(getY() - 9);
    }
    if (this.direcoes[1][0] && !this.direcoes[1][1]) {
      setY(getY() + 9);
    }
    if (this.direcoes[2][0] && !this.direcoes[2][1]) {
      setX(getX() - 9);
    }
    if (this.direcoes[3][0] && !this.direcoes[3][1]) {
      setX(getX() + 9);
    }
  }



  
  public void desenhar(Graphics g) {
    if (isAtivo()) {
      
      g.setColor(getCor());
      g.setFont(this.fontePontuacao);
      g.fillRect(getX(), getY(), getLargura(), getAltura());
      g.drawString("Pontos: " + getScore() + "/" + 'd', 10, 20);
      g.drawString("Vidas: " + getVidas(), 565, 20);
      
      if (isAtivo() && getScore() >= 100) {
        g.setColor(Color.BLACK);
        g.setFont(this.fonteVencedor);
        g.drawString("Parab\u00E9ns! Voc\u00EA venceu!", 175, 200);
      } 
    } else {
      
      g.setColor(Color.BLACK);
      g.setFont(this.fontePerdedor);
      g.drawString("Fim de Jogo!", 240, 200);
    } 
  }


  
  public void setDirecao(Direcao direcao, boolean estado) {
    switch (direcao) {
      case UP:
        this.direcoes[0][0] = estado;
        break;
      case DOWN:
        this.direcoes[1][0] = estado;
        break;
      case LEFT:
        this.direcoes[2][0] = estado;
        break;
      case RIGHT:
        this.direcoes[3][0] = estado;
        break;
    } 
  }
  
  public int getScore() {
    return this.score;
  }
  
  public void setScore(int score) {
    this.score = score;
  }
  
  public int getVidas() {
    return this.vidas;
  }
  
  public void setVidas(int vidas) {
    this.vidas = vidas;
  }
  
  public enum Direcao {
    UP, DOWN, LEFT, RIGHT;
  }
}
