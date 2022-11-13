package com.gmail.ahfonscs.invasores;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;









public class Window
  extends JFrame
  implements ActionListener
{
  public static final int LARGURA = 650;
  public static final int ALTURA = 400;
  private final Ambiente ambiente = new Ambiente();
  
  private final JMenuBar menuBar = new JMenuBar();
  private final JMenu menu1 = new JMenu("Op\u00E7\u00F5es");
  private final JMenu menu2 = new JMenu("Sobre");
  private final JMenuItem menuItem1 = new JMenuItem("Op\u00E7\u00F5es");
  private final JMenuItem menuItem2 = new JMenuItem("Sobre");
  private final JMenuItem menuItem3 = new JMenuItem("Ajuda");
  
  public Window() {
    configurarJanela();
    addComponentes();
  }

  
  private void configurarJanela() {
    setTitle("Invasores!");
    setSize(650, 400);
    setLocationRelativeTo((Component)null);
    setDefaultCloseOperation(3);
    setResizable(false);
    setVisible(true);
  }


  
  private void addComponentes() {
    add(this.ambiente);
    this.ambiente.setFocusable(true);
    
    setJMenuBar(this.menuBar);
    
    this.menu1.add(this.menuItem1);
    this.menu2.add(this.menuItem2);
    this.menu2.add(this.menuItem3);
    this.menuBar.add(this.menu1);
    this.menuBar.add(this.menu2);
    
    this.menuItem1.addActionListener(this);
    this.menuItem2.addActionListener(this);
    this.menuItem3.addActionListener(this);
    
    this.ambiente.animar();
  }



  
  public void actionPerformed(ActionEvent e) {
    if (!e.getSource().equals(this.menuItem1))
    {
      if (e.getSource().equals(this.menuItem2)) {
        JOptionPane.showMessageDialog(null, "Jogo feito por Alexandre Fonseca", "Sobre", 1);
      } else if (e.getSource().equals(this.menuItem3)) {
        JOptionPane.showMessageDialog(null, "Acabe com os inimigos antes que eles acabem com voc\u00EA!", "Ajuda", 1);
      } 
    }
  }
}
