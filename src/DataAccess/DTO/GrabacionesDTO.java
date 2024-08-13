package DataAccess.DTO;

public class GrabacionesDTO {
    private Integer IdGrabaciones    ;                 
    private String Grabacion         ;           
    private String Estado            ;                     
    private String fechaCreacion     ;            


    public GrabacionesDTO(){}
    public GrabacionesDTO(Integer IdGrabaciones, String Grabacion, String Estado,
            String fechaCreacion, String fechaModifica) {
        this.IdGrabaciones = IdGrabaciones;
        this.Grabacion = Grabacion;
        this.Estado = Estado;
        this.fechaCreacion = fechaCreacion;
    }
    public GrabacionesDTO(String Grabacion) {
        this.Grabacion = Grabacion;
    }
    public Integer getIdGrabaciones() {
        return IdGrabaciones;
    }
    public void setIdGrabaciones(Integer IdGrabaciones) {
        this.IdGrabaciones= IdGrabaciones;
    }
    public String getGrabacion() {
        return Grabacion;
    }
    public void setGrabacion(String Grabacion) {
        this.Grabacion = Grabacion;
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
 
    @Override
    public String toString(){
        return  "  \n" + getClass().getName()
                + "\n IdGrabaciones      "+ getIdGrabaciones()
                + "\n Grabacion          "+ getGrabacion()
                + "\n Estado             "+ getEstado()
                + "\n fechaCreacion      "+ getFechaCreacion();
    }
}
