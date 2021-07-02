package br.com.medicosapi.medicos.api.prontuarios;

import br.com.medicosapi.medicos.api.beneficiarios.Beneficioario;
import br.com.medicosapi.medicos.api.medicos.Medicos;
import br.com.medicosapi.medicos.api.itens.Itens;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table; 
 
@Entity 
@Table(name = "prontuarios", schema = "public")
public class Prontuarios implements Serializable {
     
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) 
    private int id;
             
    @GeneratedValue(strategy = GenerationType.SEQUENCE) 
    private int codigoguia;
            
    @ManyToMany(fetch = FetchType.EAGER) 
    @JoinTable(
        name = "pront_benef",
        joinColumns = @JoinColumn(name = "id_pront"),
        inverseJoinColumns = @JoinColumn(name = "id_benef"),
        foreignKey = @ForeignKey(name = "fk_beneficiario"),
        inverseForeignKey = @ForeignKey(name = "fk_prontuario")
    )
    
     private List<Beneficioario> beneficioarios = new ArrayList<>();
    
     public List<Beneficioario> getMedicos() {
        return beneficioarios;
    }
     
    public void setMedico(List<Beneficioario> beneficiario ) {
        this.beneficioarios  = beneficiario;
    } 
     
    @Column(nullable = false)
    private double valor;
              
    @Column(nullable = false)
    private String descricao;
              
    @Column(nullable = false)
    private Medicos medico;
    
    @Column(nullable = false)
    private Itens item;
    
    @Column(nullable = false)
    private LocalDate dt_cadastro; 
 

    public Prontuarios() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigoguia() {
        return codigoguia;
    }

    public void setCodigoguia(int codigoguia) {
        this.codigoguia = codigoguia;
    }
  
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Medicos getMedico() {
        return medico;
    }

    public void setMedico(Medicos medico) {
        this.medico = medico;
    }

    public Itens getItem() {
        return item;
    }

    public void setItem(Itens item) {
        this.item = item;
    }

    public LocalDate getDt_cadastro() {
        return dt_cadastro;
    }

    public void setDt_cadastro(LocalDate dt_cadastro) {
        this.dt_cadastro = dt_cadastro;
    }
    
    
}
     