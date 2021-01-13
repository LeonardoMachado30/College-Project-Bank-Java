/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.model.user;

import br.com.pim.model.basic.NamedBasicEntity;
import br.com.pim.model.common.Banco;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "cartoes_cadastrados")
public class UsuarioCartoesCadastrados extends NamedBasicEntity implements Serializable {

    private static final long serialVersionUID = 90060L;
    
    @Column(name = "agencia")
    private Long agencia;
    @Column(name = "conta")
    private Long conta;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiracao")
    private Date expiracao;
    
    @JoinColumn(name = "banco_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Banco banco;
    
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Long getAgencia() {
        return agencia;
    }

    public void setAgencia(Long agencia) {
        this.agencia = agencia;
    }

    public Long getConta() {
        return conta;
    }

    public void setConta(Long conta) {
        this.conta = conta;
    }

    public Date getExpiracao() {
        return expiracao;
    }

    public void setExpiracao(Date expiracao) {
        this.expiracao = expiracao;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.agencia);
        hash = 53 * hash + Objects.hashCode(this.conta);
        hash = 53 * hash + Objects.hashCode(this.expiracao);
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
        final UsuarioCartoesCadastrados other = (UsuarioCartoesCadastrados) obj;
        if (!Objects.equals(this.agencia, other.agencia)) {
            return false;
        }
        if (!Objects.equals(this.conta, other.conta)) {
            return false;
        }
        if (!Objects.equals(this.expiracao, other.expiracao)) {
            return false;
        }
        return true;
    }
    
}
