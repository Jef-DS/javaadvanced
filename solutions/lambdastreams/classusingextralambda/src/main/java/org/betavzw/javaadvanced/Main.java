package org.betavzw.javaadvanced;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

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
        int index = menus.size() + 1;
        Supplier<String> sup = () -> (index) + ". " + title;
        Menu m = new Menu(sup, handler);
        menus.add(m);
    }
    public void display(){
        System.out.println("Choose item: ");
        for (int i= 0; i< menus.size();i++){
            System.out.println(menus.get(i).getTitle());
        }
    }
    public void executeMenu(int index){
        menus.get(index).runMenu();
    }
}
class Menu{
    private Supplier<String> titleCreator;
    private Executor executor;

    public Menu(Supplier<String> titleCreator, Executor executor) {
        this.titleCreator = titleCreator;
        this.executor = executor;
    }

    public String getTitle() {
        return titleCreator.get();
    }

    public void runMenu() {
        executor.execute();
    }
}
