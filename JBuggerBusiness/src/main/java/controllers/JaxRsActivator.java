package controllers;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class JaxRsActivator extends Application{

	public JaxRsActivator() {
		System.out.println("creating an instance of  " + this.getClass().getSimpleName());
	}
}
