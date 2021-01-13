/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.facade.conta;

import br.com.pim.model.common.Banco;
import br.com.pim.model.user.Usuario;
import br.com.pim.model.user.UsuarioCartoesCadastrados;
import java.util.List;


public interface CartaoFacade {

    List<UsuarioCartoesCadastrados> getAllCartoesCadastrados(Usuario currentUser);

    List<Banco> getBancoList();

    void deleteCartaoCadastrado(long id);
    
    UsuarioCartoesCadastrados createUpdate(UsuarioCartoesCadastrados contrato);
}
