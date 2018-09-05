package sv.edu.unab.Guia2.Main;

import javax.swing.*;
import sv.edu.unab.Guia2.*;
import sv.edu.unab.Guia2.Formularios.FormularioClasico;

public class Main1 {
    public static void main(String[] args) {

        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        JFrame frm=new JFrame("Administracion de Personal");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setContentPane(new FormularioClasico().pnlroot);
        frm.setLocationRelativeTo(null);
        frm.pack();
        frm.setVisible(true);
        frm.setBounds((ancho / 2) - (frm.getWidth()/ 2)*(1), (alto / 2) - (frm.getHeight() / 2)*(1), frm.getWidth()*(1), frm.getHeight()*(1));

    }
}
