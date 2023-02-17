package org.example;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void readAllProducts(String connect) {
        List<Product> products= ProductDAO.getInstance(connect).readAll();
        for (Product product : products) {
            System.out.println(product.toString());
        }
        System.out.println();
    }

    public static void readAProductById(String connect) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product id: ");
        int id = scanner.nextInt();
        Product product = ProductDAO.getInstance(connect).read(id);

        if (product != null) {
            System.out.println(product.toString());
        } else {
            System.out.println("This product does not exist");
        }
        System.out.println();
    }

    public static void addANewProduct(String connect) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter product color: ");
        String color = scanner.nextLine();

        ProductDAO.getInstance(connect).add(new Product(name, price, color));
        System.out.println("Add product success");
        System.out.println();
    }

    public static void updateAProduct(String connect) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter product color: ");
        String color = scanner.nextLine();

        ProductDAO.getInstance(connect).update(new Product(id, name, price, color));
        System.out.println("Update success");
        System.out.println();
    }

    public static void deleteAProductById(String connect) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product id: ");
        int id = scanner.nextInt();

        ProductDAO.getInstance(connect).delete(id);
        System.out.println("Delete success");
        System.out.println();
    }

    public static void show(String connect) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("1. Read all products");
            System.out.println("2. Read a product by id");
            System.out.println("3. Add a new product");
            System.out.println("4. Update a product");
            System.out.println("5. Delete a product by id");
            System.out.println("6. Exit\n");

            System.out.print("Your choice: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> readAllProducts(connect);
                case 2 -> readAProductById(connect);
                case 3 -> addANewProduct(connect);
                case 4 -> updateAProduct(connect);
                case 5 -> deleteAProductById(connect);
                case 6 -> {
                    System.out.println("End the program");
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        show(args[0]);
    }
}