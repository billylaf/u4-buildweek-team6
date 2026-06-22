package team6;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4-buildweek-team6-pu");

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
