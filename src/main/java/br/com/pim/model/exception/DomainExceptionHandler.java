package br.com.pim.model.exception;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.eclipse.persistence.exceptions.ExceptionHandler;

public class DomainExceptionHandler implements ExceptionHandler, Serializable {

    private static final long serialVersionUID = 8132852302551338128L;
    private static final Logger LOG = Logger.getLogger(DomainExceptionHandler.class);

    @Override
    public Object handleException(RuntimeException re) {
        LOG.info("[Domain] Error while performing JPA operation.", re);
        throw re;
    }
}
