package tn.suvis.production.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.util.TestingUtilities;
import org.springframework.integration.samples.tcpclientserver.Main;
import org.springframework.integration.test.util.SocketUtils;

public class TcpServer {

	public TcpServer() {
		start();
	}

	public void start() {
		final GenericXmlApplicationContext context = Main.setupContext();
		// final SimpleGateway gateway = context.getBean(SimpleGateway.class);
		final AbstractServerConnectionFactory crLfServer = context.getBean(AbstractServerConnectionFactory.class);

		System.out.println("Waiting for server to accept connections...");
		TestingUtilities.waitListening(crLfServer, 10000L);
		System.out.println("running.\n\n");
	}

	public static GenericXmlApplicationContext setupContext() {
		final GenericXmlApplicationContext context = new GenericXmlApplicationContext();

		System.out.print("Detect open server socket...");
		int availableServerSocket = SocketUtils.findAvailableServerSocket(5678);

		final Map<String, Object> sockets = new HashMap<String, Object>();
		sockets.put("availableServerSocket", availableServerSocket);

		final MapPropertySource propertySource = new MapPropertySource("sockets", sockets);

		context.getEnvironment().getPropertySources().addLast(propertySource);

		System.out.println("using port " + context.getEnvironment().getProperty("availableServerSocket"));

		context.load("classpath:META-INF/spring/integration/tcpClientServerDemo-context.xml");
		context.registerShutdownHook();
		context.refresh();

		return context;
	}

}
