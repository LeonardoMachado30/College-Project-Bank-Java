/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.model.user;

import br.com.pim.model.basic.DatedBasicEntity;
import br.com.pim.model.investimento.Operacao;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "cliente")
public class Usuario extends DatedBasicEntity implements Serializable {

    private static final long serialVersionUID = 90030L;

    @Column(name = "cpf")
    private long cpf;
    @Column(name = "nome")
    private String nome;
    @Column(name = "celular")
    private String celular;
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String senha;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_nascimento")
    private Date dataNascimento;
    @Column(name = "administrador")
    private boolean administrador;
    @Column(name = "saldo_carteira")
    private double saldoCarteira;
    
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true, mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<UsuarioCartoesCadastrados> cartoesCadastradosList;
    
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true, mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<UsuarioContrato> contratoList;
    
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true, mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Operacao> operacaoList;

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public List<UsuarioCartoesCadastrados> getCartoesCadastradosList() {
        return cartoesCadastradosList;
    }

    public void setCartoesCadastradosList(List<UsuarioCartoesCadastrados> cartoesCadastradosList) {
        this.cartoesCadastradosList = cartoesCadastradosList;
    }

    public List<UsuarioContrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<UsuarioContrato> contratoList) {
        this.contratoList = contratoList;
    }

    public List<Operacao> getOperacaoList() {
        return operacaoList;
    }

    public void setOperacaoList(List<Operacao> operacaoList) {
        this.operacaoList = operacaoList;
    }

    public double getSaldoCarteira() {
        return saldoCarteira;
    }

    public void setSaldoCarteira(double saldoCarteira) {
        this.saldoCarteira = saldoCarteira;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.cpf ^ (this.cpf >>> 32));
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.celular);
        hash = 79 * hash + Objects.hashCode(this.endereco);
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.senha);
        hash = 79 * hash + Objects.hashCode(this.dataNascimento);
        hash = 79 * hash + Objects.hashCode(this.saldoCarteira);
        hash = 79 * hash + (this.administrador ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.cpf != other.cpf) {
            return false;
        }
        if (!Objects.equals(this.celular, other.celular)) {
            return false;
        }
        if (this.administrador != other.administrador) {
            return false;
        }
        if (this.saldoCarteira != other.saldoCarteira) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.dataNascimento, other.dataNascimento)) {
            return false;
        }
        return true;
    }

}
