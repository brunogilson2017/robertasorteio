package br.com.robertasorteio.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

@ApplicationScoped
public class EntityManagerProducer {
	
	private EntityManagerFactory factory;
	
	public EntityManagerProducer() {
		factory = Persistence.createEntityManagerFactory("SorteioPU");
	}
	
	@Produces
	@RequestScoped
	public Session createEntityManager() {
		return (Session) factory.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes Session manager) {
		if (manager.isOpen())
			manager.close();
	}

}