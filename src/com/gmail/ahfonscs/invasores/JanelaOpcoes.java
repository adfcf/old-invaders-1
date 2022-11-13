package com.gmail.ahfonscs.invasores;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;







public class JanelaOpcoes
  extends JFrame
  implements ActionListener
{
  private final JLabel label1 = new JLabel("Dificuldade: ");
  private final JTextField field1 = new JTextField(10);
  private final JColorChooser chooser = new JColorChooser();
  private final JButton botao1 = new JButton("OK");
  
  public static boolean aberto = false;

  
  public JanelaOpcoes() {
    configurar();
    addElementos();
  }


  
  private void configurar() {
    setTitle("Op\u00E7\u00F5es");
    setSize(650, 275);
    setLocationRelativeTo((Component)null);
    setDefaultCloseOperation(1);
    setResizable(false);
  }


  
  private void addElementos() {
    setLayout(new FlowLayout());
    
    add(this.label1);
    add(this.field1);
    add(this.botao1);
    add(this.chooser);
    
    this.botao1.addActionListener(this);
    
    aberto = true;
  }



  
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == this.botao1) {
      
      int dif = Integer.parseInt(this.field1.getText());
      Color corSelecionada = this.chooser.getColor();
      
      aberto = false;
      dispose();
    } 
  }
}
