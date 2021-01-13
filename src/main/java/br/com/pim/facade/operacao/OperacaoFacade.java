/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.facade.operacao;

import br.com.pim.model.common.TipoCriptoativo;
import br.com.pim.model.common.TipoOperacao;
import br.com.pim.model.investimento.Operacao;
import br.com.pim.model.user.Usuario;
import java.util.List;


public interface OperacaoFacade {

    List<Operacao> getAllOperacoesCadastrados(Usuario currentUser);
    
    void createOperacao(Operacao operacao);
    
    List<TipoOperacao> getAllTipoOperacao();
    
    List<Operacao> getOperacoes();
            
    List<TipoCriptoativo> getAllTipoCriptoativo();
    
}
