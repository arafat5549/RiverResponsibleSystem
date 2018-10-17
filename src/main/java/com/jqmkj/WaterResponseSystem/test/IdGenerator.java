package com.jqmkj.WaterResponseSystem.test;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.AbstractUUIDGenerator;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;



public class IdGenerator implements Configurable, IdentifierGenerator {

    public String dataCenterID;
    public String idLength;

//    public void configure(Type type, Properties params, Dialect d) {
//        this.dataCenterID = params.getProperty("dataCenterID");
//        this.idLength = params.getProperty("idLength");
//    }
//
//    public Serializable generate(SessionImplementor session, Object obj) {
//        return dataCenterID + idLength;
//    }

    @Override
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {
        this.dataCenterID = properties.getProperty("dataCenterID");
        this.idLength = properties.getProperty("idLength");
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return dataCenterID + idLength;
    }
}


