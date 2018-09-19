package com.slxt.rs.util;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.SQLServerDialect;

public class ImageSQLServerDialect extends SQLServerDialect{
	 public ImageSQLServerDialect () {
	        super();
	        registerHibernateType(Types.LONGVARBINARY, Hibernate.BLOB.getName());
	    }

}
