package sv.edu.unab.Guia2.Dominio;

import java.time.LocalDate;
import java.util.StringJoiner;

public class Personal {
    private String codigo;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int telefono;
    private LocalDate fechaNacimiento;
    private Character sexo;

    public Personal() {
    }

    public Personal(String codigo) {
        this.codigo = codigo;
    }

    public Personal(String codigo, String nombre, String apellidoPaterno, String apellidoMaterno, int telefono, LocalDate fechaNacimiento, Character sexo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personal)) return false;

        Personal personal = (Personal) o;

        return codigo.equals(personal.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner("")
                .add(codigo)
                .toString();
    }

    public String toString1() {
        return new StringJoiner(", ", Personal.class.getSimpleName() + "[", "]")
                .add("codigo='" + codigo + "'")
                .add("nombre='" + nombre + "'")
                .add("apellidoPaterno='" + apellidoPaterno + "'")
                .add("apellidoMaterno='" + apellidoMaterno + "'")
                .add("telefono=" + telefono)
                .add("fechaNacimiento=" + fechaNacimiento)
                .add("sexo=" + sexo)
                .toString();
    }
}
