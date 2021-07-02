 package br.com.medicosapi.medicos.api.beneficiarios;
 
public enum sexoEnum{
    
    Masculino("Masculino"),
    Feminino("Feminino");
     
    private String genero;
    
    sexoEnum(String sexo){
        this.genero = genero;
    }
    
    public String getGenero(){
        return genero;
    }
    
    public void setGenero(String genero){
        this.genero = genero;
    }
}
