package demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import classes.FolderCreator;

/**
 * @author Juan Jovel (www.github.com/JuanJovel)
 */
public class FolderCreatorDriver {

    static FolderCreator fc;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean flag = true;
        while (flag) {
            FolderCreator userPath = absolutePathSelector();
            if (userPath != null) {
                fc = userPath;
                fc.createParentFolder();
                successMessage(fc.getFolderPath());
                System.out.println();

                System.out.print(
                    "Would you like to create any subfolders? (Y or N) ");
                String option = sc.nextLine();
                System.out.println();
                if (option.equalsIgnoreCase("Y")) {
                    subFolderHandler();
                }

                System.out.print(
                    "Would you like to create more folders? (Y or N) ");
                String exit = sc.nextLine();
                System.out.println();
                if (exit.equalsIgnoreCase("N")) {
                    System.out.println("GOODBYE!");
                    try {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    flag = false;
                }

            }
            else {
                System.out.println("Selected path was not correct, try again.");
                System.out.println();
            }
            

        }
        
        System.exit(0);

    }


    private static FolderCreator absolutePathSelector() {
        System.out.print("Enter the path for the parent folder: ");
        String path = sc.nextLine();
        System.out.println();
        System.out.print("Enter the parent folder's name: ");
        String folderName = sc.nextLine();
        System.out.println();
        if (pathCorrectChecker(path)) {
            FolderCreator fc = new FolderCreator(path, folderName);
            return fc;
        }
        return null;

    }


    private static boolean pathCorrectChecker(String path) {
        System.out.println("ATTENTION:");
        System.out.println(
            "A Windows Explorer window will open with the selected path highlighted");
        System.out.println();
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e1) {
            System.out.println("FATAL: THREAD WAS INTERRUPTED!");
            e1.printStackTrace();
        }

        try {
            Runtime.getRuntime().exec("explorer.exe /select," + path);
        }
        catch (IOException e) {
            System.out.println("FATAL: INVALID PATH");
            e.printStackTrace();
        }

        System.out.print(
            "Parent folder will be created in path shown, is this correct? (Y or N) ");
        String selected = sc.nextLine();
        System.out.println();
        return selected.equalsIgnoreCase("Y");
    }


    private static void subFolderHandler() {
        System.out.println("Enter a name for subfolder: (-1 to stop)");
        boolean sentinel = true;
        ArrayList<String> subs = new ArrayList<>();
        while (sentinel) {
            String subName = sc.nextLine();
            if (subName.equals("-1")) {
                sentinel = false;
            }
            else {
                subs.add(subName);
            }

        }

        for (String subName : subs) {
            fc.createChildFolder(subName);
        }
        
        System.out.println();
        System.out.println(subs.size() + " subfolders created");

    }


    private static void successMessage(String path) {
        System.out.println("Folder successfully created at: " + path);
    }
}
