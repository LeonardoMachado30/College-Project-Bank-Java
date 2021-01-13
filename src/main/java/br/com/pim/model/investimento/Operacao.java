/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.model.investimento;

import br.com.pim.model.basic.DatedBasicEntity;
import br.com.pim.model.common.TipoCriptoativo;
import br.com.pim.model.common.TipoOperacao;
import br.com.pim.model.user.Usuario;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "operacoes")
public class Operacao extends DatedBasicEntity implements Serializable {

    private static final long serialVersionUID = 90090L;    
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "valor")
    private Double valor;
    
    @JoinColumn(name = "tipo_criptoativo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoCriptoativo tipoCriptoativo;
    
    @JoinColumn(name = "tipo_operacao_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoOperacao tipoOperacao;
    
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoCriptoativo getTipoCriptoativo() {
        return tipoCriptoativo;
    }

    public void setTipoCriptoativo(TipoCriptoativo tipoCriptoativo) {
        this.tipoCriptoativo = tipoCriptoativo;
    }

    public TipoOperacao getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(TipoOperacao tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.descricao);
        hash = 89 * hash + Objects.hashCode(this.valor);
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
        final Operacao other = (Operacao) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        return true;
    }    
}
