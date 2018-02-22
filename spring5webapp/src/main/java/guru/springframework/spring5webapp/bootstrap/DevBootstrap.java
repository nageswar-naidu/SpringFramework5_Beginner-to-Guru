package guru.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{

	private AuthorRepository authorRepository;
	private PublisherRepository publisherRepository;
	private BookRepository bookRepository;
	
	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		initData ();
	}
	
	private void initData () {
		
		// Eric
		Author eric = new Author("Eric","Evans");
		Publisher publisherA = new Publisher("Marper Collins", "C/Alcala,47");
		Book ddd = new Book("Domain Driven Design","1234",publisherA);
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		
		authorRepository.save(eric);
		publisherRepository.save(publisherA);
		bookRepository.save(ddd);
		
		
		// Rod
		Author rod = new Author("Rod","Johnson");
		Publisher publisherB = new Publisher("Worx", "C/Serrano,12");
		Book noEJB = new Book("J2EE Development without EJB","23444",publisherB);
		rod.getBooks().add(noEJB);
		
		authorRepository.save(rod);
		publisherRepository.save(publisherB);
		bookRepository.save(noEJB);
		
	}
}
