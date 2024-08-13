package DataAccess.DTO;

public class PersonaDTO {
    private Integer idPersona    ;           
    private Integer idRegistro   ;           
    private String Nombre        ;           
    private String Apellido      ; 
    private String Estado        ;                     
    private String fechaCreacion ;            
    private String fechaModifica ;

    public PersonaDTO(){}
    public PersonaDTO(Integer idPersona, Integer idRegistro, String Nombre, String Apellido, String Estado,
            String fechaCreacion, String fechaModifica) {
        this.idPersona = idPersona;
        this.idRegistro = idRegistro;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Estado = Estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaModifica = fechaModifica;
    }
    public Integer getIdPersona() {
        return idPersona;
    }
    public void setIdPersona(Integer idPersona) {
        this.idPersona= idPersona;
    }
    public Integer getIdRegistro() {
        return idRegistro;
    }
    public void setIdRegidRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    public String getApellido() {
        return Apellido;
    }
    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }
    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    public String getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public String getFechaModifica() {
        return fechaModifica;
    }
    public void setFechaModifica(String fechaModifica) {
        this.fechaModifica = fechaModifica;
    }  
    
    @Override
    public String toString(){
        return  "  \n" + getClass().getName()
                + "\n idPersona      "+ getIdPersona()
                + "\n idRegistro     "+ getIdRegistro()
                + "\n Nombre         "+ getNombre()
                + "\n Apellido       "+ getApellido()
                + "\n Estado         "+ getEstado()
                + "\n fechaCreacion  "+ getFechaCreacion()
                + "\n fechaModifica  "+ getFechaModifica();
    }
    
}
