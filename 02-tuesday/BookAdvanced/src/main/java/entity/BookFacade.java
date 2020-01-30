/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author oscar
 */
public class BookFacade {

    EntityManagerFactory emf;

    public BookFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Book> getAllBooks() {
        EntityManager em = getEntityManager();
        TypedQuery q = em.createQuery("SELECT b FROM Book b", Book.class);
        return q.getResultList();
    }

    public Book addBook(String author, String title, int year) {
        Book book = new Book(author, title, year);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
            return book;
        } finally {
            em.close();

        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        BookFacade facade = new BookFacade(emf);
        Book b1 = facade.addBook("John","12341",1999);
        Book b2 = facade.addBook("Ulrich","Oh Long Johnson", 1992);

    }

}
