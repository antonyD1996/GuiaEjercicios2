package sv.edu.unab.Guia2.Formularios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import sv.edu.unab.Guia2.Dominio.Personal;

public class FormularioClasico {
    public JPanel pnlroot;
    private JTextField txtNombre;
    private JTextField txtApellidoPaterno;
    private JTextField txtApellidoMaterno;
    private JFormattedTextField ftxFechaNacimiento;
    private JComboBox cboxSexo;
    private JTable tblPersonal;
    private JTextField txtTelefono;
    private JButton btnAgregar;
    private JButton btnEditar;
    private JButton btnGuardar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JLabel lblEdadPromedio;
    private JComboBox cboxFiltros;
    private JTextField txtParametro;
    private JButton btnAplicarFiltro;

    ArrayList<Personal> listado=new ArrayList<>();
    ArrayList<Personal> Filtrado=new ArrayList<>();
     String Codigo;
     String Nombre;
     String ApellidoPaterno;
     String ApellidoMaterno;
     Integer Telefono;
     LocalDate FechaNacimiento;
     Character Sexo;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public FormularioClasico(){

//        txtNombre.setText("Antony David");
//        txtApellidoPaterno.setText("Duarte");
//        txtApellidoMaterno.setText("Perlera");
//        txtTelefono.setText("70079032");
//        ftxFechaNacimiento.setValue("05/11/1996");
//        cboxSexo.setSelectedItem(0);
        EdadPromedio();
        initcomponents();
        try{
            MaskFormatter mascara=new MaskFormatter("##/##/####");
            mascara.setPlaceholderCharacter('_');
            ftxFechaNacimiento.setFormatterFactory(new DefaultFormatterFactory(mascara));
        }catch(ParseException e){
            e.printStackTrace();
        }

        tblPersonal.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int i=tblPersonal.getSelectedRow();
                Codigo =(tblPersonal.getValueAt(i,0).toString());
                Nombre=(tblPersonal.getValueAt(i,1).toString());
                ApellidoPaterno=(tblPersonal.getValueAt(i,2).toString());
                ApellidoMaterno=(tblPersonal.getValueAt(i,3).toString());
                Telefono=Integer.parseInt(tblPersonal.getValueAt(i,4).toString());
                FechaNacimiento=LocalDate.parse(tblPersonal.getValueAt(i,5).toString());
                Sexo=(tblPersonal.getValueAt(i,7).toString().charAt(0));

            }
        });


        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Personal p=new Personal();
                p.setCodigo(String.valueOf(new Random().nextInt()));
                p.setNombre(txtNombre.getText());
                p.setApellidoPaterno(txtApellidoPaterno.getText());
                p.setApellidoMaterno(txtApellidoMaterno.getText());
                p.setTelefono(Integer.parseInt(txtTelefono.getText()));
                p.setFechaNacimiento(LocalDate.parse(ftxFechaNacimiento.getText(),dtf));
                p.setSexo(cboxSexo.getSelectedItem().toString().charAt(0));
                listado.add(p);
                Actualizar();
                limpiarcomponentes();
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNombre.setText(Nombre);
                txtApellidoPaterno.setText(ApellidoPaterno);
                txtApellidoMaterno.setText(ApellidoMaterno);
                txtTelefono.setText(String.valueOf(Telefono));
                ftxFechaNacimiento.setText(FechaNacimiento.format(dtf));
                if (Sexo.equals('M')){
                    cboxSexo.setSelectedItem(0);
                }
                else{
                    cboxSexo.setSelectedItem(1);
                }
//                JOptionPane.showMessageDialog(null,
////                        "\nNombre: "+Nombre+
////                        "\nApellido P: "+ApellidoPaterno+
////                        "\nApellido M: "+ApellidoMaterno+
////                        "\nTelefono: "+Telefono+
////                        "\nFechaN: "+FechaNacimiento+
////                        "\nSexo: "+Sexo);
            }
        });
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=0;i<listado.size();i++){
                    if (Codigo.equals(listado.get(i).getCodigo())){
                        listado.get(i).setNombre(txtNombre.getText());
                        listado.get(i).setApellidoPaterno(txtApellidoPaterno.getText());
                        listado.get(i).setApellidoMaterno(txtApellidoMaterno.getText());
                        listado.get(i).setTelefono(Integer.valueOf(txtTelefono.getText()));
                        listado.get(i).setFechaNacimiento(LocalDate.parse(ftxFechaNacimiento.getText(),dtf));
                        listado.get(i).setSexo(cboxSexo.getSelectedItem().toString().charAt(0));
                    }
                }
                Actualizar();
                limpiarcomponentes();
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0; i<listado.size(); i++){
                    if(Codigo==listado.get(i).getCodigo()){
                        listado.remove(i);
                    }
                    Actualizar();
                }
            }
        });
        btnAplicarFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(cboxFiltros.getSelectedIndex()){
                    case 0:{
                        for (int i=0;i<listado.size();i++){
                            if (txtParametro.getText().equals(listado.get(i).getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS))){
                                Personal per=new Personal();
                                per.setCodigo(listado.get(i).getCodigo());
                                per.setNombre(listado.get(i).getNombre());
                                per.setApellidoPaterno(listado.get(i).getApellidoPaterno());
                                per.setApellidoMaterno(listado.get(i).getApellidoMaterno());
                                per.setTelefono(listado.get(i).getTelefono());
                                per.setFechaNacimiento(listado.get(i).getFechaNacimiento());
                                per.setSexo(listado.get(i).getSexo());
                                Filtrado.add(per);
                            }
                        }
                    }break;
                }
            }
        });
    }
    public void initcomponents(){

        listado.add(new Personal(String.valueOf(new Random().nextInt()), "Karla Michelle", "Rivera", "Aleman", 70079032,LocalDate.of(1980,8,12),'F'));
        listado.add(new Personal(String.valueOf(new Random().nextInt()), "Ronald Gabriel", "Ramos", "Gutierres", 78787878,LocalDate.of(1975,6,15),'M'));
        Actualizar();

    }
    public void Actualizar(){
        String matriz[][] = new String[listado.size()][8];
        for(int i = 0; i<listado.size(); i++){
            matriz[i][0]= listado.get(i).getCodigo();
            matriz[i][1]= listado.get(i).getNombre();
            matriz[i][2]= listado.get(i).getApellidoPaterno();
            matriz[i][3]= listado.get(i).getApellidoMaterno();
            matriz[i][4]= Integer.toString(listado.get(i).getTelefono());
            matriz[i][5]= String.valueOf(listado.get(i).getFechaNacimiento());
            matriz[i][6] = String.valueOf(listado.get(i).getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS));
            matriz[i][7]= String.valueOf(listado.get(i).getSexo().toString().charAt(0));
        }
        tblPersonal.setModel(new javax.swing.table.DefaultTableModel(matriz, new String[]
                {"Codigo","Nombre","Apellido P","Apellido M","Telefono","FechaN","Edad","Sexo"}));

        EdadPromedio();

    }
    public void FiltroEdad(){
        String matriz[][] = new String[Filtrado.size()][8];
        for(int i = 0; i<Filtrado.size(); i++){
            matriz[i][0]= Filtrado.get(i).getCodigo();
            matriz[i][1]= Filtrado.get(i).getNombre();
            matriz[i][2]= Filtrado.get(i).getApellidoPaterno();
            matriz[i][3]= Filtrado.get(i).getApellidoMaterno();
            matriz[i][4]= Integer.toString(Filtrado.get(i).getTelefono());
            matriz[i][5]= String.valueOf(Filtrado.get(i).getFechaNacimiento());
            matriz[i][6] = String.valueOf(Filtrado.get(i).getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS));
            matriz[i][7]= String.valueOf(Filtrado.get(i).getSexo().toString().charAt(0));
        }
        tblPersonal.setModel(new javax.swing.table.DefaultTableModel(matriz, new String[]
                {"Codigo","Nombre","Apellido P","Apellido M","Telefono","FechaN","Edad","Sexo"}));

        EdadPromedio();

    }
    private void EdadPromedio(){
        double t=0;
        double p=0;
        if (tblPersonal.getRowCount()>0){
            for (int i=0;i<tblPersonal.getRowCount();i++){
                p=Double.parseDouble(tblPersonal.getValueAt(i,6).toString());
                t+=p;

        }
            double prom=Math.round(t/tblPersonal.getRowCount());
            lblEdadPromedio.setText(String.valueOf(prom));
    }
    }
    private void limpiarcomponentes(){
        txtNombre.setText(null);
        txtApellidoPaterno.setText(null);
        txtApellidoMaterno.setText(null);
        ftxFechaNacimiento.setText(null);
        cboxSexo.setSelectedItem(0);
    }

}
