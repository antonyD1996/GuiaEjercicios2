package sv.edu.unab.Guia2.Formularios;

import sv.edu.unab.Guia2.Dominio.Personal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;


public class FormularioStreamAPI {
    public JPanel pnlroot;
    private JComboBox cboxFiltros;
    private JTextField txtParametro;
    private JButton btnAplicarFiltro;
    private JTextField txtParametro2;
    private JComboBox cboxFiltroSexo;
    private JButton btnFiltroSexo;
    private JButton btnEliminarFiltros;
    private JTable tblPersonal;
    private JTextField txtNombre;
    private JTextField txtApellidoMaterno;
    private JTextField txtApellidoPaterno;
    private JFormattedTextField ftxFechaNacimiento;
    private JTextField txtTelefono;
    private JComboBox cboxSexo;
    private JLabel lblMayor;
    private JLabel lblMenor;
    private JLabel lblEdadPromedio;
    private JButton btnBuscar;
    private JButton btnEditar;
    private JButton btnGuardar;
    private JButton btnEliminar;
    private JButton btnCancelar;
    private JButton btnAgregar;

    List<Personal> listadoModel, filtradoModel;
    String Codigo;
    String Nombre;
    String ApellidoPaterno;
    String ApellidoMaterno;
    Integer Telefono;
    LocalDate FechaNacimiento;
    Character Sexo;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public FormularioStreamAPI() {
        txtNombre.setText("Antony David");
        txtApellidoPaterno.setText("Duarte");
        txtApellidoMaterno.setText("Perlera");
        txtTelefono.setText("70079032");
        ftxFechaNacimiento.setValue("05/11/1996");
        cboxSexo.setSelectedItem(0);
        initcomponents();
    }
    public void initcomponents(){

        tblPersonal.setFillsViewportHeight(true);
        if (listadoModel==null){
            listadoModel=new ArrayList<>();
        }
        listadoModel.add(new Personal(String.valueOf(new Random().nextInt()), "Karla Michelle", "Rivera", "Aleman", 70079032, LocalDate.of(1980,8,12),'F'));
        listadoModel.add(new Personal(String.valueOf(new Random().nextInt()), "Ronald Gabriel", "Ramos", "Gutierres", 78787878,LocalDate.of(1975,6,15),'M'));
        listadoModel.add(new Personal(String.valueOf(new Random().nextInt()), "Rene Josias", "Quijada", "Rosales", 75487845,LocalDate.of(1975,1,15),'M'));
        listadoModel.add(new Personal(String.valueOf(new Random().nextInt()), "German Jesus", "Valle", "Arrue", 70451245,LocalDate.of(1971,2,15),'M'));
        listadoModel.add(new Personal(String.valueOf(new Random().nextInt()), "Emerson David", "Duarte", "Guillen", 79621545,LocalDate.of(1972,3,8),'M'));
        listadoModel.add(new Personal(String.valueOf(new Random().nextInt()), "Antony Jacobo", "Valle", "Gomez", 70215487,LocalDate.of(1973,4,15),'M'));
        listadoModel.add(new Personal(String.valueOf(new Random().nextInt()), "Josselyn Adele", "Arrue", "Gutierres", 79564512,LocalDate.of(1974,5,5),'F'));
        listadoModel.add(new Personal(String.valueOf(new Random().nextInt()), "Andrea Nicole", "Cabanias", "Menjivar", 70707070,LocalDate.of(1975,6,15),'F'));
        listadoModel.add(new Personal(String.valueOf(new Random().nextInt()), "Reina Carolina", "Serrano", "Luna", 71717171,LocalDate.of(1976,7,2),'F'));
        listadoModel.add(new Personal(String.valueOf(new Random().nextInt()), "Gabriela Angela", "Alvarenga", "Alas", 75757575,LocalDate.of(1977,8,1),'F'));
        listadoModel.add(new Personal(String.valueOf(new Random().nextInt()), "Melvin Otoniel", "Recinos", "Oliva", 74747474,LocalDate.of(1978,9,15),'M'));
        listadoModel.add(new Personal(String.valueOf(new Random().nextInt()), "Freddy Pastor", "Lopez", "Cartagena", 72757848,LocalDate.of(1979,10,20),'M'));
        listadoModel.add(new Personal(String.valueOf(new Random().nextInt()), "Jose Rosalio", "Serrano", "Gutierres", 78888888,LocalDate.of(1980,11,15),'M'));
        listadoModel.add(new Personal(String.valueOf(new Random().nextInt()), "Nelson Romeo", "Hercules", "Orellana", 71234567,LocalDate.of(1976,12,31),'M'));
        ActualizarDatos(listadoModel);

        btnAgregar.addActionListener(e->{
            Personal p=new Personal();
            p.setCodigo(String.valueOf(new Random().nextInt()));
            p.setNombre(txtNombre.getText());
            p.setApellidoPaterno(txtApellidoPaterno.getText());
            p.setApellidoMaterno(txtApellidoMaterno.getText());
            p.setTelefono(Integer.parseInt(txtTelefono.getText()));
            p.setFechaNacimiento(LocalDate.parse(ftxFechaNacimiento.getText(),dtf));
            p.setSexo(cboxSexo.getSelectedItem().toString().toUpperCase().charAt(0));
            listadoModel.add(p);
            ActualizarDatos(listadoModel);
            Limpiar();
        });
        btnEditar.addActionListener(e->{
            txtNombre.setText(Nombre);
            txtApellidoPaterno.setText(ApellidoPaterno);
            txtApellidoMaterno.setText(ApellidoMaterno);
            txtTelefono.setText(String.valueOf(Telefono));
            ftxFechaNacimiento.setText(FechaNacimiento.format(dtf));

        });
        btnGuardar.addActionListener(e->{
            listadoModel.stream().forEach(p->{
                if (p.getCodigo().equals(Codigo))
                {
                    p.setNombre(txtNombre.getText());
                    p.setApellidoPaterno(txtApellidoPaterno.getText());
                    p.setApellidoMaterno(txtApellidoMaterno.getText());
                    p.setTelefono(Integer.valueOf(txtTelefono.getText()));
                    p.setFechaNacimiento(LocalDate.parse(ftxFechaNacimiento.getText(),dtf));
                    p.setSexo(cboxSexo.getSelectedItem().toString().charAt(0));
                }
                Limpiar();
                ActualizarDatos(listadoModel);
            });
        });
        btnEliminar.addActionListener(e->{
        listadoModel.removeIf(p->p.getCodigo().equals(Codigo));
        ActualizarDatos(listadoModel);
        });
        btnAplicarFiltro.addActionListener(e->{
            switch (cboxFiltros.getSelectedIndex()){
                case 0:{//igual a
                   List<Personal> igual=listadoModel.stream().filter(m->{
                       boolean respuesta=false;
                       Long edad=m.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
                       if (edad.equals(Long.valueOf(txtParametro.getText()))){
                           respuesta=true;
                       }
                       return  respuesta;
                   }).collect(Collectors.toList());
                   ActualizarDatos(igual);
                }break;
                case 1:{//mayor a
                    List<Personal> mayorA=listadoModel.stream().filter(m->{
                        boolean respuesta=false;
                        Long edad=m.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
                        if (edad>(Long.valueOf(txtParametro.getText()))){
                            respuesta=true;
                        }
                        return  respuesta;
                    }).collect(Collectors.toList());
                    ActualizarDatos(mayorA);
                }break;
                case 2:{//mayor o igual a
                    List<Personal> mayorIgualA=listadoModel.stream().filter(m->{
                        boolean respuesta=false;
                        Long edad=m.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
                        if (edad>=(Long.valueOf(txtParametro.getText()))){
                            respuesta=true;
                        }
                        return  respuesta;
                    }).collect(Collectors.toList());
                    ActualizarDatos(mayorIgualA);
                }break;
                case 3:{//menor a
                    List<Personal> menorA=listadoModel.stream().filter(m->{
                        boolean respuesta=false;
                        Long edad=m.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
                        if (edad<(Long.valueOf(txtParametro.getText()))){
                            respuesta=true;
                        }
                        return  respuesta;
                    }).collect(Collectors.toList());
                    ActualizarDatos(menorA);
                }break;
                case 4:{//menor o igual a
                    List<Personal> menorIgualA=listadoModel.stream().filter(m->{
                    boolean respuesta=false;
                    Long edad=m.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
                    if (edad<=(Long.valueOf(txtParametro.getText()))){
                        respuesta=true;
                    }
                    return  respuesta;
                }).collect(Collectors.toList());
                ActualizarDatos(menorIgualA);
                }break;
                case 5:{//entre
                    List<Personal> menorA=listadoModel.stream().filter(m->{
                        boolean respuesta=false;
                        Long edad=m.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
                        if (edad>=(Long.valueOf(txtParametro.getText()))&&edad<=(Long.valueOf(txtParametro2.getText()))){
                            respuesta=true;
                        }
                        return  respuesta;
                    }).collect(Collectors.toList());
                    ActualizarDatos(menorA);
                }break;
            }
        });
        tblPersonal.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int i=tblPersonal.getSelectedRow();
                Codigo =(tblPersonal.getValueAt(i,0).toString());
                Nombre=(tblPersonal.getValueAt(i,1).toString());
                ApellidoPaterno=(tblPersonal.getValueAt(i,2).toString());
                ApellidoMaterno=(tblPersonal.getValueAt(i,3).toString());
                Telefono=Integer.parseInt(tblPersonal.getValueAt(i,4).toString());
                FechaNacimiento=LocalDate.parse(tblPersonal.getValueAt(i,5).toString(),dtf);
                Sexo=(tblPersonal.getValueAt(i,7).toString().charAt(0));

            }
        });

        try{
            MaskFormatter mascara=new MaskFormatter("##/##/####");
            mascara.setPlaceholderCharacter('_');
            ftxFechaNacimiento.setFormatterFactory(new DefaultFormatterFactory(mascara));
        }catch(ParseException e){
            e.printStackTrace();
        }

    }
    private void ActualizarDatos(List<Personal> listado){
        Personal edadMenor=listado.stream().min((e1,e2)->{
            Long edad1=e1.getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS);
            Long edad2=e2.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
            return edad1.compareTo(edad2);
        }).get();
        Personal edadMayor=listado.stream().max((e1,e2)->{
            Long edad1=e1.getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS);
            Long edad2=e2.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
            return edad1.compareTo(edad2);
        }).get();
        lblMayor.setText(edadMayor.getNombre()+" "+edadMayor.getApellidoPaterno()+" "+edadMayor.getApellidoMaterno());
        lblMenor.setText(edadMenor.getNombre()+" "+edadMenor.getApellidoPaterno()+" "+edadMenor.getApellidoMaterno());

        BigDecimal edadPromedio=listado.stream()
                .map(e->{
                    Long edad=e.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
                    return new BigDecimal(edad);
                })
                .reduce((e1,e2)->(e1.add(e2).divide(new BigDecimal(2)))).get();
        edadPromedio.setScale(2,BigDecimal.ROUND_HALF_UP);

        lblEdadPromedio.setText(edadPromedio.toString());

        DefaultTableModel modelo=new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido P");
        modelo.addColumn("Apellido M");
        modelo.addColumn("Telefono");
        modelo.addColumn("Fecha N");
        modelo.addColumn("Edad");
        modelo.addColumn("Sexo");

        listado.stream().forEach(p->{
            modelo.addRow(new Object[]{
                    p.getCodigo(),
                    p.getNombre(),
                    p.getApellidoPaterno(),
                    p.getApellidoMaterno(),
                    p.getTelefono(),
                    p.getFechaNacimiento().format(dtf),
                    p.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS),
                    p.getSexo()

            });
        });
        tblPersonal.setModel(modelo);
    }
    private void Limpiar(){
        txtNombre.setText(null);
        txtApellidoPaterno.setText(null);
        txtApellidoMaterno.setText(null);
        ftxFechaNacimiento.setText(null);
        txtTelefono.setText(null);
        cboxSexo.setSelectedItem(0);
    }


}
