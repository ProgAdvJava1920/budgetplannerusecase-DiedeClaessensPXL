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
                    entityManagerFactory = Persistence.createEntityManagerFactory("paymentsdb_pu");
                    entityManager = entityManagerFactory.createEntityManager();

                    TypedQuery<PaymentJPA> query = entityManager.createQuery("SELECT payment from PaymentJPA payment", PaymentJPA.class);
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
