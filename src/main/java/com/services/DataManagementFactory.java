package com.services;

import jakarta.servlet.ServletContext;

import java.sql.SQLException;

public class DataManagementFactory {
    public static IDataManagement getFactory(String type, ServletContext context) throws Exception {

        if (type.equals("context")) {
            return ContextDataManagement.getInstance(context);
        }
        return MySQLDataManagement.getInstance();
    }
}
