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
    HashMap<String, Account> IbanAndAccountMap;
    public BudgetPlannerImporter() {

        IbanAndAccountMap = new HashMap<>();

    }

   public List<Account> readFile(String filePath){
       Path path = Paths.get(filePath);
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

    public Payment createPayment(String[] lines) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        return new Payment(LocalDateTime.parse(lines[3], formatter), Float.parseFloat(lines[4]), lines[5], lines[6]);
    }

    public Account createAccount(String[] lines) {

        return new Account(lines[1], lines[0]);

    }

}
