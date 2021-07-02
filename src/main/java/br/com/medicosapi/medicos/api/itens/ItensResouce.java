package br.com.medicosapi.medicos.api.itens;
  
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

@Path("itens")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless

public class ItensResouce {
    
    @PersistenceContext(unitName = "MedicosPU")
    private EntityManager entityManager;
    
    public ItensResouce() { 
    }
     
    //buscar itens
    @GET
    @Produces(MediaType.APPLICATION_JSON)
        public List<Itens> getItens(){
            return entityManager.createQuery("SELECT a FROM Itens a", Itens.class).getResultList();
        }
        
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON) 
    //listar item
    public Response getItens(@PathParam("id") int id){
        Itens itens = findItem(id);
        
        if(itens == null){
            return itemNotFoundResponse();
        }
         
        return Response.ok(itens).build();
    } 
    
    //salvar item
    @POST 
     public Response addItem(Itens itens){
         
         if( itens.getValor() < 1){
             return valorMenorResponse();
         }
         
          entityManager.persist(itens); 
          return Response.status(Status.CREATED).entity(itens).build();
     }
     
    //deletar item
    @DELETE
     @Path("{id}")
     public Response remove(@PathParam("id") int id){ 
        Itens itens = findItem(id);
        
        if(itens == null){
            return itemNotFoundResponse();
        }
        
        entityManager.remove(itens);
        return Response.noContent().build();
        
     }
     
    //editar item
     @PUT 
      @Path("{id}") 
      public Response update(@PathParam("id") int id, Itens itemAtualizado){
            Itens itens = findItem(id);

            if(itens == null){
                return itemNotFoundResponse();
            }
            
        itemAtualizado.setId(id);
        entityManager.merge(itemAtualizado); 
        return Response.ok(itens).build();
      }
      
    //procutatr item
    public Itens findItem(int id){
        return entityManager.find(Itens.class, id);
    }
     
    //mensagem de item nao encontrado para edição e exclusão
    public Response itemNotFoundResponse(){
        return Response.status(Response.Status.NOT_FOUND).entity("Item não encontrado").build();
    }
    
    //mensagem de valor menor que um
    public Response valorMenorResponse(){
        return Response.status(Response.Status.NOT_FOUND).entity(" Valor deve ser maior que R$ 1,00. ").build();
    }
    
}
