package com.gmail.ahfonscs.invasores;

import java.util.Random;










public class Util
{
  public static int retorneValor(int de, int a) {
    Random randomico = new Random();
    
    int valor = de + randomico.nextInt(a);
    return valor;
  }


  
  public static int retorneValorNeg(int a, int de) {
    de = Math.abs(de);
    a = Math.abs(a);
    
    Random randomico = new Random();
    
    int valor = de + randomico.nextInt(a);
    return valor - valor * 2;
  }
}
