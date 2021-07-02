package br.com.medicosapi.medicos.api.itens;

import br.com.medicosapi.medicos.api.medicos.Medicos;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table; 
 
@Entity 
@Table(name = "itens", schema = "public")
public class Itens implements Serializable {
     
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) 
    private int id;
             
    @Column(nullable = false)
    private String nome;
             
    @Column(nullable = false)
    private String descricao;
    
    @Column(nullable = false)
    private double valor;
    
    @ManyToMany(fetch = FetchType.EAGER) 
    @JoinTable(
        name = "medico_iten",
        joinColumns = @JoinColumn(name = "id_item"),
        inverseJoinColumns = @JoinColumn(name = "id_medico"),
        foreignKey = @ForeignKey(name = "fk_item"),
        inverseForeignKey = @ForeignKey(name = "fk_medico")
    )
     
    private List<Medicos> medicos = new ArrayList<>();
    
     public List<Medicos> getMedicos() {
        return medicos;
    }
     
    public void setMedico(List<Medicos> medicos ) {
        this.medicos  = medicos;
    } 
    
    @Column(nullable = false)
    private LocalDate dt_criacao;

    public Itens() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
 
    public LocalDate getDt_criacao() {
        return dt_criacao;
    }

    public void setDt_criacao(LocalDate dt_criacao) {
        this.dt_criacao = dt_criacao;
    }
     
}
     