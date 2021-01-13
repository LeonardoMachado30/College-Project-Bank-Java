/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.model.common;

import br.com.pim.model.basic.NamedBasicEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "banco")
public class Banco extends NamedBasicEntity implements Serializable {

    private static final long serialVersionUID = 90040L;

}
