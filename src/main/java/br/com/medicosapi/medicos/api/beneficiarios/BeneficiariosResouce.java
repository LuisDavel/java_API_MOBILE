package br.com.medicosapi.medicos.api.beneficiarios;
 
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

@Path("beneficiarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless

public class BeneficiariosResouce {
    
    @PersistenceContext(unitName = "MedicosPU")
    private EntityManager entityManager; 
    private long totalDias;
    private LocalDate dt_aniv;
    private LocalDate dt_hoje;
    
    public BeneficiariosResouce() {
    
    }
     
    //buscar beneficiarios
    @GET
    @Produces(MediaType.APPLICATION_JSON)
        public List<Beneficioario> getBeneficiario(){
            return entityManager.createQuery("SELECT a FROM Beneficioario a", Beneficioario.class).getResultList();
        }
    //listar beneficiarios
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response getBeneficiario(@PathParam("id") int id){
        Beneficioario beneficioario = findBeneficiario(id);
        
        if(beneficioario == null){
            return beneficiarioNotFoundResponse();
        }
         
        return Response.ok(beneficioario).build();
    } 
    
    // adicionar beneficiarios
    @POST 
     public Response addBeneficiario(Beneficioario beneficioario){
          
        dt_aniv = LocalDate.parse(beneficioario.getDt_nascimento().toString(), DateTimeFormatter.BASIC_ISO_DATE.ISO_LOCAL_DATE);
        dt_hoje = LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ISO_LOCAL_DATE);

        totalDias = ChronoUnit.DAYS.between(dt_aniv, dt_hoje); 
          
        if(totalDias < 4380){ //deve ser maior que 12 anos
             return beneficiarioIdadeResponse();
        } 
         
        if(totalDias > 43800){ //deve ser menor que 120 anos
             return beneficiarioIdadeResponse();
        } 
        
        entityManager.persist(beneficioario); 
        return Response.status(Status.CREATED).entity(beneficioario).build();
     }
     
     //deletar beneficiarios
    @DELETE
     @Path("{id}")
     public Response remove(@PathParam("id") int id){ 
        Beneficioario beneficioario = findBeneficiario(id);
        
        if(beneficioario == null){
            return beneficiarioNotFoundResponse();
        }
        
        entityManager.remove(beneficioario);
        return Response.noContent().build();
        
     }
     
     //editar beneficiarios
     @PUT 
      @Path("{id}") 
      public Response update(@PathParam("id") int id, Beneficioario beneficioarioAtualizado){
            Beneficioario beneficioario = findBeneficiario(id);

            if(beneficioario == null){
                return beneficiarioNotFoundResponse();
            }
             
        dt_aniv = LocalDate.parse(beneficioario.getDt_nascimento().toString(), DateTimeFormatter.BASIC_ISO_DATE.ISO_LOCAL_DATE);
        dt_hoje = LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ISO_LOCAL_DATE);

        totalDias = ChronoUnit.DAYS.between(dt_aniv, dt_hoje); 
          
        if(totalDias < 4380){ //deve ser maior que 12 anos
             return beneficiarioIdadeResponse();
        } 
         
        if(totalDias > 43800){ //deve ser menor que 120 anos
             return beneficiarioIdadeResponse();
        } 
        
        if(beneficioario.getCd_carteira() < 100){
             return beneficiarioCarteiraResponse();
        }
        
        beneficioarioAtualizado.setId(id);
        entityManager.merge(beneficioarioAtualizado); 
        return Response.ok(beneficioario).build();
      }
      
    //localizar beneficiarios
    public Beneficioario findBeneficiario(int id){
        return entityManager.find(Beneficioario.class, id);
    }
    
    //mensagem de beneficiarios não encontrado
    public Response beneficiarioNotFoundResponse(){
        return Response.status(Response.Status.NOT_FOUND).entity("Beneficiário não encontrado").build();
    }
    
    //mensagem para validação de idade 
    public Response beneficiarioIdadeResponse(){
        return Response.status(Response.Status.EXPECTATION_FAILED).entity("Beneficiário deve ter mais que 12 anos e menos do que 120").build();
    }
    
    //mensagem para validação da carteira, deve ser > 100 
    public Response beneficiarioCarteiraResponse(){
        return Response.status(Response.Status.EXPECTATION_FAILED).entity("Código da carteira inválido").build();
    }
    
}
