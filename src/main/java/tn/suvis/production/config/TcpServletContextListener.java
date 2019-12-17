package tn.suvis.production.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TcpServletContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("démarage du server tcp.................");
		TcpServer server=new TcpServer();
		System.out.println("server tcp démarer avec succès");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
