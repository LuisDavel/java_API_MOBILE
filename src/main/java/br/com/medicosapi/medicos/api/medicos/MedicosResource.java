    package br.com.medicosapi.medicos.api.medicos;

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
 
@Path("medicos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless 

public class MedicosResource { 
      @PersistenceContext(unitName = "MedicosPU")
      private EntityManager entityManager; 
      
    public MedicosResource(){

    }

    //buscar medicos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Medicos> getMedicos(){
        return entityManager.createQuery("SELECT a FROM Medicos a", Medicos.class).getResultList();
    }

    //listar medicos
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedico(@PathParam("id") int id){
       Medicos medico = findMedico(id);
        if(medico == null){
            return medicosNotFoundResponse();
        }  
        return Response.ok(medico).build();
    }
 
    //adicionar medicos
    @POST 
    public Response addMedico(Medicos medico){
        
        //crm tem que ser maior que 1000
        if(medico.getCrm() < 1000 ){
            return CRMResponse();
        } 
        
        //cbo tem que ser maior que 2011115
        if(medico.getCbo() < 201115 ){
            return CBOResponse();
        }
        
        //crm tem que ser menor que 251605
        if(medico.getCbo() > 251605 ){
            return CBOResponse();
        }
                
        entityManager.persist(medico); 
        return Response.status(Status.CREATED).entity(medico).build();
    }

    //excluir medicos
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") int id){ 
        Medicos medico = findMedico(id);
        if(medico == null){
            return medicosNotFoundResponse();
        }
        entityManager.remove(medico);
         return Response.noContent().build();
    }

    //editar medicos
    @PUT
    @Path("{id}") 
    public Response update(@PathParam("id") int id, Medicos medicoAtualizado){
        Medicos medico = findMedico(id);

        if(medico == null){
            return medicosNotFoundResponse();
        }
        
        //crm tem que ser maior que 1000
        if(medicoAtualizado.getCrm() < 1000 ){
            return CRMResponse();
        }
        
        //cbo tem que ser maior que 2011115
        if(medicoAtualizado.getCbo() < 201115 ){
            return CBOResponse();
        }
        
        //crm tem que ser menor que 251605
        if(medicoAtualizado.getCbo() > 251605 ){
            return CBOResponse();
        }
        
        medicoAtualizado.setId(id);
        entityManager.merge(medicoAtualizado); 
        return Response.ok(medico).build();
    }           

    //localiza medicos
    public Medicos findMedico(int id){
        return entityManager.find(Medicos.class, id);
    }
 
    //mensagem para crm > 1000
    public Response CRMResponse(){
        return Response.status(Status.EXPECTATION_FAILED).entity("CRM inválido").build();
    }
    
     //mensagem para CBO fora da lista de médicos
    public Response CBOResponse(){
        return Response.status(Status.EXPECTATION_FAILED).entity("CBO inválido").build();
    }
    
    //mensagem para medico nao encontrado
    public Response medicosNotFoundResponse(){
        return Response.status(Status.NOT_FOUND).entity("Médico não encontrado").build();
    }
      
}
