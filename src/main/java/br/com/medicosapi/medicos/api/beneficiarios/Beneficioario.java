package br.com.medicosapi.medicos.api.beneficiarios;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table; 
 
@Entity 
@Table(name = "beneficiario", schema = "public")
public class Beneficioario implements Serializable {
     
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) 
    private int id;
            
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String cpf;
      
    @Enumerated(EnumType.STRING)
    private sexoEnum sexo;
    
    @Column(nullable = false)
    private String endereco;
    
    @Column(nullable = false)
    private String conjugue;
    
    @Column(nullable = false)
    private int cd_carteira;
    
    @Column(nullable = false)
    private LocalDate dt_nascimento;
    
    @Column(nullable = false)
    private LocalDate dt_cadastro;

    public Beneficioario() {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public sexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(sexoEnum sexo) {
        this.sexo = sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getConjugue() {
        return conjugue;
    }

    public void setConjugue(String conjugue) {
        this.conjugue = conjugue;
    }

    public int getCd_carteira() {
        return cd_carteira;
    }

    public void setCd_carteira(int cd_carteira) {
        this.cd_carteira = cd_carteira;
    }

    public LocalDate getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(LocalDate dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    public LocalDate getDt_cadastro() {
        return dt_cadastro;
    }

    public void setDt_cadastro(LocalDate dt_cadastro) {
        this.dt_cadastro = dt_cadastro;
    }
      
}
     