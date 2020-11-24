package loader;

import entities.Institution;
import entities.Publication;
import entities.Researcher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Loader {

    public static List<Researcher> loadResearchers(Scanner sc) {
        List<Researcher> list = new ArrayList<Researcher>();
        boolean done = false;
        while (!done) {
            System.out.println("Researcher's name (q to finish): ");
            String name = sc.nextLine();
            done = name.equals("q");
            if (!done) {
                System.out.println("Researcher's number of reads: ");
                int reads = sc.nextInt();
                System.out.println("Researcher's number of citations: ");
                int citations = sc.nextInt();
                sc.nextLine();
                list.add(new Researcher(name, reads, citations));
            }
        }
        return list;
    }

    public static ArrayList<Institution> loadInstitution(Scanner sc) {
        ArrayList<Institution> list = new ArrayList<>();
        boolean done = false;
        while (!done) {
            System.out.println("Institution's name (q to finish): ");
            String name = sc.nextLine();
            done = name.equals("q");
            if (!done) {
                System.out.println("Institution location: ");
                String location = sc.nextLine();
                System.out.println("Institution department: ");
                String department = sc.nextLine();
                list.add(new Institution(name, location, department));
            }
        }
        return list;
    }

    public static ArrayList<Publication> loadPublication(Scanner sc) {
        ArrayList<Publication> list = new ArrayList<>();
        ArrayList<Researcher> researchers = new ArrayList<>();
        boolean done = false;
        while (!done) {
            System.out.println("Publication's name (q to finish): ");
            String name = sc.nextLine();
            done = name.equals("q");
            if (!done) {
                while (true) {
                    System.out.println("Publication's authors (q to finish): ");
                    name = sc.nextLine();
                    if (name.equals("q")) break;
                    researchers.add(new Researcher(sc.nextLine()));
                }
                list.add(new Publication(name, researchers));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read information from user and save it
        List<Institution> institutionList = loadInstitution(sc);
        List<Researcher> researcherList = loadResearchers(sc);
        List<Publication> publicationList = loadPublication(sc);

        //Initialize stuff
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Hibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        //load and print institutions stored in the database
        entityTransaction.begin();
        for (Institution institution : institutionList) {
            entityManager.persist(institution);
        }
        entityTransaction.commit();
        for (Institution institution : institutionList) {
            System.out.println(institution);
        }

        //load and print researchers stored in the database
        entityTransaction.begin();
        for (Researcher researcher : researcherList) {
            entityManager.persist(researcher);
        }
        entityTransaction.commit();
        for (Researcher researcher : researcherList) {
            System.out.println(researcher);
        }

        //load and print publications stored in the database
        entityTransaction.begin();
        for (Publication publication : publicationList) {
            entityManager.persist(publication);
        }
        entityTransaction.commit();
        for (Publication publication : publicationList) {
            System.out.println(publication);
        }

        //close stuff
        sc.close();
        entityManager.close();
        entityManagerFactory.close();
    }
}
