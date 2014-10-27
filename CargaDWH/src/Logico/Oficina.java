package Logico;

public class Oficina {
    
    String direccion;
    int numero_empleados;
    long id_oficina;

    public long getId_oficina() {
        return id_oficina;
    }

    public void setId_oficina(long id_oficina) {
        this.id_oficina = id_oficina;
    }

    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumero_empleados() {
        return numero_empleados;
    }

    public void setNumero_empleados(int numero_empleados) {
        this.numero_empleados = numero_empleados;
    }
    
}
