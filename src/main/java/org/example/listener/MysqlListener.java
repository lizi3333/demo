package org.example.listener;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebListener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener
public class MysqlListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            Enumeration<Driver> divers= DriverManager.getDrivers();
            while(divers.hasMoreElements())
                DriverManager.deregisterDriver(divers.nextElement());
            AbandonedConnectionCleanupThread.checkedShutdown();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
