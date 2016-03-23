package com.aeg.ims.interceptor;

import com.aeg.ims.model.User;
import groovy.util.logging.Log4j2;
import lombok.extern.log4j.Log4j;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Auditable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class AuditLogInterceptor extends EmptyInterceptor{

    private int updates;

    //interceptor for updates
    public boolean onFlushDirty(Object entity,
                                Serializable id,
                                Object[] currentState,
                                Object[] previousState,
                                String[] propertyNames,
                                Type[] types) {


        if ( entity instanceof Auditable) {
            updates++;
            for ( int i=0; i < propertyNames.length; i++ ) {
                if ( "lastUpdateTimestamp".equals( propertyNames[i] ) ) {
                    currentState[i] = new Date();
                    return true;
                }
            }
        }
        return false;
    }


}
