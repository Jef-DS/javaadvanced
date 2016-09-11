package org.betavzw.javaadvanced;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Jef on 2/09/2016.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuList menus = new MenuList();
        menus.addMenu("Say Hello", () -> System.out.println("Hello"));
        menus.addMenu("Say Goodbye", () -> System.out.println("Goodbye"));
        menus.addMenu("Say Yes", () -> System.out.println("Yes"));
        menus.addMenu("Say No", () -> System.out.println("No"));
        menus.addMenu("Exit", () -> System.exit(0));
        while(true) {
            menus.display();
            System.out.print("Your choice: ");
            int index = Integer.parseInt(scanner.nextLine());
            menus.executeMenu(index - 1);
        }

    }
}
@FunctionalInterface
interface Executor{
    void execute();
}

class MenuList{
    private List<Menu> menus = new ArrayList<>();
    public void addMenu(String title, Executor handler) {
        Menu m = new Menu(title, handler);
        menus.add(m);
    }
    public void display(){
        System.out.println("Choose item: ");
        for (int i= 0; i< menus.size();i++){
            System.out.printf("\t%d. %s\n", i+1, menus.get(i).getTitle());
        }
    }
    public void executeMenu(int index){
        menus.get(index).runMenu();
    }
}
class Menu{
    private String title;
    private Executor executor;

    public Menu(String title, Executor executor) {
        this.title = title;
        this.executor = executor;
    }

    public String getTitle() {
        return title;
    }

    public void runMenu() {
        executor.execute();
    }
}