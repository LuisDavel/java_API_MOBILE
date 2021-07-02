package br.com.medicosapi.medicos.api.medicos;
 
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity 
@Table(name = "medicos", schema = "public")

public class Medicos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
     
    @Column(nullable = false)
    private String cpf; 
     
    @Column(nullable = false)
    private String nome; 
     
    @Column(nullable = false)
    private int crm; 
    
    @Column(nullable = false)
    private int cbo;  
    
    @Column(nullable = false)
    private LocalDate dt_cadastro;  

    public Medicos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCrm() {
        return crm;
    }

    public void setCrm(int crm) {
        this.crm = crm;
    }

    public int getCbo() {
        return cbo;
    }

    public void setCbo(int cbo) {
        this.cbo = cbo;
    }

    public LocalDate getDt_cadastro() {
        return dt_cadastro;
    }

    public void setDt_cadastro(LocalDate dt_cadastro) {
        this.dt_cadastro = dt_cadastro;
    }
 
    
}
