/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.model.user;

import br.com.pim.model.basic.DatedBasicEntity;
import br.com.pim.model.common.TipoContrato;
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
@Table(name = "contrato")
public class UsuarioContrato extends DatedBasicEntity implements Serializable {

    private static final long serialVersionUID = 90070L;    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "inclusao")
    private Date inclusao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "encerramento")
    private Date encerramento;
    
    @JoinColumn(name = "tipo_contrato_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoContrato tipoContrato;
    
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Date getInclusao() {
        return inclusao;
    }

    public void setInclusao(Date inclusao) {
        this.inclusao = inclusao;
    }

    public Date getEncerramento() {
        return encerramento;
    }

    public void setEncerramento(Date encerramento) {
        this.encerramento = encerramento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.inclusao);
        hash = 29 * hash + Objects.hashCode(this.encerramento);
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
        final UsuarioContrato other = (UsuarioContrato) obj;
        if (!Objects.equals(this.inclusao, other.inclusao)) {
            return false;
        }
        if (!Objects.equals(this.encerramento, other.encerramento)) {
            return false;
        }
        return true;
    }
    
}
