package com.example.demo;

import java.io.*;
import java.util.*;

/**
 * This class is to create and input accounts, it also reads from text file, writes to text file and clear text file.
 *
 * @author Richard Gan Soon Ching-modified
 */
public class Account implements Comparable<Account> {
    private long score;
    private String userName ;
    private static ArrayList<Account> accounts = new ArrayList<>();

    /**
     * Account contructor
     * @param userName game username
     * @param score game score
     */
    public Account(String userName, long score){
        this.userName = userName;
        this.score = score;
    }


    /**
     * getter for accounts
     *
     * @return ArrayList for Account
     */
    public static ArrayList<Account> getAccounts(){
        return accounts;
    }

    /**
     * Compares score
     * @param o the object to be compared.
     * @return score difference
     */
    @Override
    public int compareTo(Account o) {
        return Long.compare(o.getScore(), score);
    }

    /**
     * setter for score
     * @param score game score
     */
    public void setScore(long score) {
        this.score = score;
    }

    /**
     * getter for score
     * @return score
     */
    public long getScore() {
        return score;
    }

    /**
     * getter for userName
     * @return userName
     */
    public String getUserName() {
        return userName;
    }


    /**
     * check if there are duplicate accounts in list
     * @param userName gamme username
     * @return account if there exist a duplicate, null if not
     */
    static Account accountHaveBeenExist(String userName){
        for(Account account : accounts){
            if(account.getUserName().equals(userName)){
                return account;
            }
        }
        return null;

    }

    /**
     * Helps turn array list into string
     *
     * @return String
     */
    public String toString()
    {
        return this.userName + " " + this.score;
    }

    /**A
     * Makes acccount and adds to ArrayList
     * @param userName game username
     * @param score game score
     * @return account
     */
    static Account makeNewAccount(String userName, long score){
        Account account = new Account(userName, score);
        accounts.add(account);

        return account;
    }

    /**
     * Clear Text file
     */
    public static void clearFile()
    {
        try{
            FileWriter fw = new FileWriter("src/main/resources/com/example/demo/Leaderboard.txt", false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        }catch(Exception exception){
            System.out.println("Exception have been caught");
        }
    }

    /**
     * Read from text file to ArrayList
     */
    public static void readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/example/demo/Leaderboard.txt"));

            String lineText;
            while ((lineText = br.readLine()) != null) {
                String[] line = lineText.split(" ");
                accounts.add(new Account(line[0], Long.parseLong(line[1])));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("File Read Error");
        }

    }

    /**
     * Sort ArrayList and write to file, then clears ArrayList
     */
    public static void write2File(){
        try{
            FileWriter fw = new FileWriter("src/main/resources/com/example/demo/Leaderboard.txt", true);

            Collections.sort(accounts);

            for(Account accounts: accounts){
                fw.write(accounts + System.lineSeparator());
            }
            accounts.clear();// after writing clear the arraylist so there would be duplicate info
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    }
