package BusinessLogic;
import java.util.List;

import DataAccess.GrabacionesDAO;
import DataAccess.DTO.GrabacionesDTO;

public class GrabacionesBL {
        private GrabacionesDTO Grabaciones;
        private GrabacionesDAO gDao = new GrabacionesDAO();
    
        public GrabacionesBL(){}
    
        public List<GrabacionesDTO> getAll() throws Exception{
            List<GrabacionesDTO> lst = gDao.readAll();
            for (GrabacionesDTO GrabacionesDTO : lst) 
                GrabacionesDTO.setGrabacion(GrabacionesDTO.getGrabacion().toUpperCase());
            return lst;
        }
        public GrabacionesDTO getBy(int IdGrabaciones) throws Exception{
            Grabaciones = gDao.readBy(IdGrabaciones);
            return Grabaciones;
        }
        public boolean add(GrabacionesDTO GrabacionesDTO) throws Exception{   
            return gDao.create(GrabacionesDTO);
        }
        public boolean update(GrabacionesDTO GrabacionesDTO) throws Exception{
            return gDao.update(GrabacionesDTO);
        }
        public boolean delete(int IdGrabaciones) throws Exception{
            return gDao.delete(IdGrabaciones);
        }
}
