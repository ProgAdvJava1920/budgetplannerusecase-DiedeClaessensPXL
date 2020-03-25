package be.pxl.student;

import be.pxl.student.entity.PaymentJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class BudgetPlanner {
    public static void main(String[] args) {

                EntityManagerFactory entityManagerFactory = null;
                EntityManager entityManager = null;
                try {
                    entityManagerFactory = Persistence.createEntityManagerFactory("PaymentsJPA");
                    entityManager = entityManagerFactory.createEntityManager();
                    PaymentJPA payment = entityManager.find(PaymentJPA.class, 1L);
                }
                finally {
                    if (entityManager != null) {
                        entityManager.close();
                    }
                    if (entityManagerFactory != null) {
                        entityManagerFactory.close();
                    }
                }



    }


}
