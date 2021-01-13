/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.facade.contrato;

import br.com.pim.model.common.TipoContrato;
import br.com.pim.model.user.Usuario;
import br.com.pim.model.user.UsuarioContrato;
import java.util.List;


public interface ContratoFacade {

    List<UsuarioContrato> getAllContratosCadastrados(Usuario currentUser);

    List<TipoContrato> getAllTipoContrato();

    void deleteContrato(long id);
    
    UsuarioContrato createUpdate(UsuarioContrato contrato);
}
