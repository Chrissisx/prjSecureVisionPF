package DataAccess.DTO;

public class RegistroDTO {          
    private Integer idRegistro;           
    private String Username;           
    private String Password; 
    private String Estado;                     
    private String fechaCreacion;            
    private String fechaModifica;

    public RegistroDTO(){}

    public RegistroDTO(Integer idRegistro, String Username, String Password, String Estado,
                       String fechaCreacion, String fechaModifica) {
        this.idRegistro = idRegistro;
        this.Username = Username;
        this.Password = Password;
        this.Estado = Estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaModifica = fechaModifica;
    }

    public RegistroDTO(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
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
    public String toString() {
        return  "  \n" + getClass().getName()
                + "\n idRegistro     " + getIdRegistro()
                + "\n Username       " + getUsername()
                + "\n Password       " + getPassword()
                + "\n Estado         " + getEstado()
                + "\n fechaCreacion  " + getFechaCreacion()
                + "\n fechaModifica  " + getFechaModifica();
    }
}
