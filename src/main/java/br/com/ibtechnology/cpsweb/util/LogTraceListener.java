package br.com.ibtechnology.cpsweb.util;

import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import br.com.ibtechnology.cpsweb.business.UserController;
import br.com.ibtechnology.cpsweb.model.entities.BaseEntities;
import br.com.ibtechnology.cpsweb.model.entities.LogTrace;
import br.com.ibtechnology.cpsweb.model.entities.UserEntity;
import br.com.ibtechnology.cpsweb.model.enums.TransactionType;
import br.com.ibtechnology.cpsweb.model.repositories.IlogTraceRepository;

@Named(value = "logTraceListener")
@SessionScoped
public class LogTraceListener extends BaseBeans {

	private static final long serialVersionUID = -5191719517008349116L;

	@Autowired
    IlogTraceRepository logTraceService;

	@Autowired
    UserController userService;

    @PostRemove
    void postDelete(BaseEntities<Long> e) {
        createLog(TransactionType.DELETE, e);
    }

    @PostPersist
    void postPersist(BaseEntities<Long> e) {
        createLog(TransactionType.CREATE, e);
    }

    @PostUpdate
    void postUpdate(BaseEntities<Long> e) {
        createLog(TransactionType.UPDATE, e);
    }

    private void createLog(TransactionType transactionType, BaseEntities<Long> e) {
        /*
         * OBSERVAÇÃO 1.
         */
//        if (logTraceService == null) {
//        	FacesContext ctx = FacesContext.getCurrentInstance();
//
//            logTraceService = (IlogTraceRepository)ctx.getBean("logTraceService");
//        }

        /*
         * OBSERVAÇÃO 2.
         */
        UserEntity user = userService.getAuthenticatedUser();

        LogTrace logTrace = new LogTrace();
        /*
         * OBSERVAÇÃO 3.
         */
        String entityName = e.getClass().getAnnotation(Table.class).name();
        if (entityName == null || entityName.isEmpty()) {
            entityName = e.getClass().getSimpleName();
        }

        logTrace.setTransactionType(transactionType);
        logTrace.setEntityName(entityName);
        logTrace.setRegistryId(e.getId());
        logTrace.setExecutedBy(user);
        logTrace.setOperationDate(new Date());

        logTraceService.save(logTrace);
    }
}