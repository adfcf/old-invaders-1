package com.gmail.ahfonscs.invasores;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;


public class Ambiente
  extends JPanel
{
  private final BufferedImage buffer = new BufferedImage(650, 400, 1);
  private final Jogador jogador = new Jogador(30, 30, 325, 300, new Color(0, 150, 215));
  
  private final ArrayList<Projetil> projeteis = new ArrayList<>();
  private final ArrayList<Invaders> invasores = new ArrayList<>();
  
  private boolean animado;
  
  private boolean pausado;
  
  private boolean projeteisAuto;
  public static final int DIFICULDADE = 10;
  public static final int TAXA_DE_CORTE = 100;
  public static boolean derrotaInimiga = false;
  
  public Ambiente() {
    inicializarAmbiente();
  }

  
  private void inicializarAmbiente() {
    this.animado = false;
    this.pausado = false;
    
    for (int c = 0; c < 10; c++) {
      this.invasores.add(new Invaders(25, 20, Util.retorneValor(5, 630), 
            Util.retorneValorNeg(125, 50), new Color(190, 15, 25)));
    }
    
    addKeyListener(new KeyListener()
        {
          public void keyTyped(KeyEvent e) {}



          
          public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 27) {
              Ambiente.this.pause();
            }
            if (e.getKeyCode() == 82) {
              Ambiente.this.recomecar();
            }
            if (e.getKeyCode() == 65) {
              Ambiente.this.projeteisAuto = !Ambiente.this.projeteisAuto;
            }
            
            Ambiente.this.identificarTecla(e.getKeyCode(), true);
          }

          
          public void keyReleased(KeyEvent e) {
            Ambiente.this.identificarTecla(e.getKeyCode(), false);
          }
        });
  }

  
  private void identificarTecla(int tecla, boolean ativa) {
    switch (tecla) {
      
      case 38:
        this.jogador.setDirecao(Jogador.Direcao.UP, ativa);
        break;
      case 40:
        this.jogador.setDirecao(Jogador.Direcao.DOWN, ativa);
        break;
      case 37:
        this.jogador.setDirecao(Jogador.Direcao.LEFT, ativa);
        break;
      case 39:
        this.jogador.setDirecao(Jogador.Direcao.RIGHT, ativa);
        break;
    } 
    
    if (32 == tecla && ativa && this.jogador.isAtivo()) {
      this.projeteis.add(new Projetil(10, 5, this.jogador.getX() + this.jogador.getLargura() / 2, this.jogador.getY(), new Color(0, 0, 0)));
    }
  }

  
  public void animar() {
    long nextUpdate = 0L;
    this.animado = true;
    
    while (this.animado) {
      if (System.currentTimeMillis() >= nextUpdate) {
        if (!this.pausado) update(); 
        repaint();
        
        nextUpdate = System.currentTimeMillis() + 30L;
      } 
    } 
  }
  
  private void update() {
    this.jogador.atualizar();
    int c;
    for (c = 0; c < this.projeteis.size(); c++) {
      
      ((Projetil)this.projeteis.get(c)).atualizar();
      
      if (!((Projetil)this.projeteis.get(c)).isAtivo()) {
        this.projeteis.remove(c);
      }
    } 
    
    for (c = 0; c < this.invasores.size(); c++) {
      
      ((Invaders)this.invasores.get(c)).atualizar();
      detectarColisao(this.jogador, this.invasores.get(c));
      
      if (!((Invaders)this.invasores.get(c)).isAtivo()) {
        this.invasores.remove(c);
        
        if (!derrotaInimiga) {
          this.invasores.add(new Invaders(25, 20, Util.retorneValor(5, 620), Util.retorneValorNeg(-20, 5), new Color(190, 15, 25)));
        } else {
          this.invasores.add(new Invaders(25, 20, Util.retorneValor(5, 620), Util.retorneValor(400, 450), new Color(190, 15, 25)));
        } 
      } 
    } 
    
    for (c = 0; c < this.projeteis.size(); c++) {
      for (int d = 0; d < this.invasores.size(); d++) {
        detectarColisao(this.projeteis.get(c), this.invasores.get(d));
      }
    } 
    
    if (this.jogador.getScore() >= 100) {
      gameOver();
      derrotaInimiga = true;
    } 
    
    if (this.jogador.getVidas() <= 0) {
      this.jogador.setAtivo(false);
      gameOver();
    } 
    
    if (this.jogador.isAtivo() && this.projeteisAuto) {
      this.projeteis.add(new Projetil(10, 5, this.jogador.getX() + this.jogador.getLargura() / 2, this.jogador.getY(), new Color(0, 0, 0)));
    }
  }

  
  private void detectarColisao(Entidade entidade1, Entidade entidade2) {
    if (!entidade1.isAtivo() || !entidade2.isAtivo())
      return; 
    int paX = entidade1.getX() + entidade1.getLargura();
    int paY = entidade1.getY() + entidade1.getAltura();
    int extremox = entidade2.getX() + entidade2.getLargura();
    int extremoy = entidade2.getY() + entidade2.getAltura();
    
    if (paX >= entidade2.getX() && entidade1.getX() <= extremox && paY >= entidade2
      .getY() && entidade1.getY() <= extremoy) {
      
      if (entidade1 instanceof Projetil && entidade2 instanceof Invaders) {
        entidade1.setAtivo(false);
        entidade2.setAtivo(false);
        if (!derrotaInimiga) this.jogador.setScore(this.jogador.getScore() + 1);
      
      } 
      if (entidade1 instanceof Jogador && entidade2 instanceof Invaders) {
        entidade2.setAtivo(false);
        if (!derrotaInimiga) this.jogador.setVidas(this.jogador.getVidas() - 1);
      
      } 
    } 
  }

  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    Graphics bf = this.buffer.getGraphics();
    bf.setColor(Color.LIGHT_GRAY);
    bf.fillRect(0, 0, getWidth(), getHeight());
    
    this.jogador.desenhar(bf);
    int c;
    for (c = 0; c < this.projeteis.size(); c++) {
      ((Projetil)this.projeteis.get(c)).desenhar(bf);
    }
    
    for (c = 0; c < this.invasores.size(); c++) {
      ((Invaders)this.invasores.get(c)).desenhar(bf);
    }
    
    g.drawImage(this.buffer, 0, 0, this);
  }

  
  private void gameOver() {
    this.pausado = false;
    repaint();
  }

  
  private void recomecar() {
    if (derrotaInimiga) {
      derrotaInimiga = false;
    }
    
    this.jogador.setVidas(3);
    this.jogador.setScore(0);
    this.jogador.setAtivo(true);
  }
  
  private void pause() {
    this.pausado = !this.pausado;
  }
}
