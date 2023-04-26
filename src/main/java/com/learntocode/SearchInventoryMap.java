package com.learntocode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class SearchInventoryMap {
    // key is id, value is a product object
    static HashMap<Integer, Product> inventory = new HashMap<Integer, Product>();


    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        // this method loads product objects into inventory
        loadInventory();

        //loop
        boolean search = true;
        while (search) {
            System.out.println("What item # are you interested in? ");
            int id = myScanner.nextInt();

            Product matchedProduct = inventory.get(id);
            if (matchedProduct == null) {
                System.out.println("We don't carry that product");
            } else {
                System.out.printf("We carry %s and the price is $%.2f%n",
                        matchedProduct.getName(), matchedProduct.getPrice());
            }

            System.out.println("Do you want to go again? (yes or no): ");
            String answer = myScanner.nextLine();
            search = answer.equalsIgnoreCase("yes");




        }
    }

    public static void loadInventory(){
        String fileName = "inventory.csv";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line=reader.readLine()) !=null) {
                String[] tokens = line.split("\\|");
                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                float price = Float.parseFloat(tokens[2]);
                inventory.put(id, new Product(id, name, price));

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
