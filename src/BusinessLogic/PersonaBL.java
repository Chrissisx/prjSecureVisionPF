package BusinessLogic;
    import java.util.List;

    import DataAccess.PersonaDAO;
    import DataAccess.DTO.PersonaDTO;
    
    public class PersonaBL {
        private PersonaDTO Persona;
        private PersonaDAO pDao = new PersonaDAO();
    
        public PersonaBL(){}
    
        public List<PersonaDTO> getAll() throws Exception{
            List<PersonaDTO> lst = pDao.readAll();
            for (PersonaDTO PersonaDTO : lst) 
                PersonaDTO.setNombre(PersonaDTO.getNombre().toUpperCase());
            return lst;
        }
        public PersonaDTO getBy(int idPersona) throws Exception{
            Persona = pDao.readBy(idPersona);
            return Persona;
        }
        public boolean add(PersonaDTO PersonaDTO) throws Exception{   
            return pDao.create(PersonaDTO);
        }
        public boolean update(PersonaDTO PersonaDTO) throws Exception{
            return pDao.update(PersonaDTO);
        }
        public boolean delete(int idPersona) throws Exception{
            return pDao.delete(idPersona);
        }
    }

