package tn.suvis.production.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TcpServletContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("d�marage du server tcp.................");
		TcpServer server=new TcpServer();
		System.out.println("server tcp d�marer avec succ�s");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
