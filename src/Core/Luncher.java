package Core;

import java.util.Scanner;

public class Luncher {
    public static void lunchApp() {
        Container app = Container.getInstance();

        Scanner scanner = new Scanner(System.in);

        String command;
        Commands commands = (Commands) app.resolve("commands");

        do {
            System.out.print("Enter your command: ");
            command = scanner.next();
            try {
                commands.resolve(command);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while(!command.equals("quit"));
    }
}
