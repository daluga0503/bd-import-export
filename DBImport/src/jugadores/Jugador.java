package jugadores;

public class Jugador {
    private long id;
    private String nombre;
    private String apellidos;
    private String posicion;
    private long dorsal;
    
    public Jugador(String jugadorText){
        String[] values = jugadorText.split(";");
        this.id = Integer.parseInt(values[0]);
        this.nombre = values[1];
        this.apellidos = values[2];
        this.dorsal = Integer.parseInt(values[3]);
        this.posicion = values[4];
        
    }

    public Jugador(long id, String nombre, String apellido, String posicion, long dorsal) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellido;
        this.posicion = posicion;
        this.dorsal = dorsal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellido) {
        this.apellidos = apellido;
    }
    
    public long getDorsal() {
        return dorsal;
    }

    public void setDorsal(long dorsal) {
        this.dorsal = dorsal;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    @Override
    public boolean equals(Object obj) {
       return obj!=null && 
            obj.getClass() == this.getClass() && 
            ((Jugador)obj).getId() == this.id;
    }

    @Override
    public String toString() {
        return String.format(
            "id: %d, nombre: %s, apellido: %s, posicion: %s, dorsal: %d", 
            id, nombre, apellidos, posicion, dorsal);
    }

    public String serialize(){
        return String.format("%d;%s;%s;%d;%s\n", id, nombre, apellidos, dorsal, posicion);
    }
}
