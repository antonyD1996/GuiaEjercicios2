package sv.edu.unab.Guia2.Main;

import sv.edu.unab.Guia2.Formularios.FormularioClasico;
import sv.edu.unab.Guia2.Formularios.FormularioStreamAPI;

import javax.swing.*;

public class Main2 {
    public static void main(String[] args) {
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        JFrame frm=new JFrame("Administracion de Personal");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setContentPane(new FormularioStreamAPI().pnlroot);
        frm.setLocationRelativeTo(null);
        frm.pack();
        frm.setVisible(true);
        frm.setBounds((ancho / 2) - (frm.getWidth()/ 2), (alto / 2) - (frm.getHeight() / 2), frm.getWidth(), frm.getHeight());
    }
}
