
package br.com.medicosapi.medicos.api.prontuarios;
  
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status;

@Path("prontuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless

public class ProntuariosResouce {
    
    @PersistenceContext(unitName = "MedicosPU")
    private EntityManager entityManager;
    
    public ProntuariosResouce() {
    
    }
    
    //buscar prontuarios
    @GET
    @Produces(MediaType.APPLICATION_JSON)
        public List<Prontuarios> getProntuarios(){
            return entityManager.createQuery("SELECT a FROM Prontuarios a", Prontuarios.class).getResultList();
        }
     
    //listar prontuarios   
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProntuario(@PathParam("id") int id){
        Prontuarios prontuario = findProntuario(id);
        
        if(prontuario == null){
            return prontuarioNotFoundResponse();
        }
         
        return Response.ok(prontuario).build();
    } 
    
    //adicionar prontuarios
    @POST 
     public Response addProntuario(Prontuarios prontuario){
          entityManager.persist(prontuario); 
          return Response.status(Status.CREATED).entity(prontuario).build();
     }
     
     //deletar prontuarios
    @DELETE
     @Path("{id}")
     public Response remove(@PathParam("id") int id){ 
        Prontuarios prontuario = findProntuario(id);
        if(prontuario == null){
            return prontuarioNotFoundResponse();
        }  
        entityManager.remove(prontuario);
        return Response.noContent().build(); 
     }
     
     //editar prontuarios
     @PUT 
     @Path("{id}") 
     public Response update(@PathParam("id") int id, Prontuarios prontuarioAtualizado){
            Prontuarios prontuario = findProntuario(id);

            if(prontuario == null){
                return prontuarioNotFoundResponse();
            }
            
        prontuarioAtualizado.setId(id);
        entityManager.merge(prontuarioAtualizado); 
        return Response.ok(prontuario).build();
      }
      
    //localizar prontuarios
    public Prontuarios findProntuario(int id){
        return entityManager.find(Prontuarios.class, id);
    }
     
    //mensagem para prontuario nao encontrado
    public Response prontuarioNotFoundResponse(){
        return Response.status(Response.Status.NOT_FOUND).entity("Prontuário não encontrado").build();
    }
    
}
