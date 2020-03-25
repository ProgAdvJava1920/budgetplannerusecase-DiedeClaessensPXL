package be.pxl.student;

public class BudgetPlanner {
    public static void main(String[] args) {
         private static void getPayments(String name){
                EntityManagerFactory entityManagerFactory = null;
                EntityManager entityManager = null;
                try {
                    entityManagerFactory = Persistence.createEntityManagerFactory("paymentsdb_pu");
                    entityManager = entityManagerFactory.createEntityManager();

                    TypedQuery<Payment> query = entityManager.createQuery("SELECT payment from Payment payment", Payment.class);
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
         private static void importCsv(){
        LOGGER.debug("Started reading file");
        BudgetPlannerImporter importer = new BudgetPlannerImporter();
        LOGGER.debug("Stopped reading file");

        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("paymentsdb_pu");
            entityManager = entityManagerFactory.createEntityManager();

            importer.importCvs(Paths.get("./src/main/resources/account_payments_v2.csv"), entityManager);
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


}
