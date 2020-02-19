package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {
    String filename;
    HashMap<String, Account> IbanAndAccountMap;
    public BudgetPlannerImporter(String filename) {
        this.filename = filename;
        IbanAndAccountMap = new HashMap<>();

    }

   public List<Account> readFile(){
       Path path = Paths.get(filename);
       List<Account> accounts = new ArrayList<>();
       try(BufferedReader reader = Files.newBufferedReader(path)){
           String line = reader.readLine(); //Account name,Account IBAN,Counteraccount IBAN,Transaction date,Amount,Currency,Detail
           while (line != null){
               line = reader.readLine();
               String[] lines = line.split(",");
               Account account = createAccount(lines);
               Payment payment = createPayment(lines);
               if (IbanAndAccountMap.containsKey(account.getIBAN())){
                   IbanAndAccountMap.get(account.getIBAN()).getPayments().add(payment);
               }else {
                   account.getPayments().add(payment);
                   IbanAndAccountMap.put(account.getIBAN(), account);

               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       return accounts;
   }

    private Payment createPayment(String[] lines) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd hh:mm:ss zzz yyyy", Locale.ENGLISH);

        return new Payment(LocalDateTime.parse(lines[3], formatter), Float.parseFloat(lines[4]), lines[5], lines[6]);
    }

    private Account createAccount(String[] lines) {

        return new Account(lines[1], lines[0]);

    }

}
