package com.coderscampus.assignment3;

// Java imports
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class UserService {

    static User[] database = new User[21];
    static Scanner userInput = new Scanner(System.in);

    public void retrieveUser() {

        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader("users.txt"));

            String line;
            int i = 0;

            while ((line = fileReader.readLine()) != null) {

                String[] dataArray = line.split(", ");
                if (dataArray[3].equals("super_user")) {
                    database[i] = new SuperUser(dataArray[0], dataArray[1], dataArray[2], dataArray[3]);
                } else if (dataArray[3].equals("normal_user")){
                    database[i] = new NormalUser(dataArray[0], dataArray[1], dataArray[2], dataArray[3]);
                } else {
                    System.out.println("Error");
                }
                i++;

            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("There was an I/O exception");
            e.printStackTrace();
        } finally {
            try {
                System.out.println("Closing file reader");
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Arrays.sort(database);

    }

    public void validateLogin() {

        User loggedInUser = null;

        String inputEmail;
        String inputPassword;

        int exitChoice = 4;
        int optionChoice = 0;
        int loginAttempt = 0;
        
        final int MAX_LOGIN_ATTEMPT = 5;

        while (loggedInUser == null && loginAttempt < MAX_LOGIN_ATTEMPT) {
            System.out.println("Enter your email");
            inputEmail = userInput.nextLine();
            System.out.println("Enter your password");
            inputPassword = userInput.nextLine();

            loggedInUser = getUser(inputEmail, inputPassword);

            if (loggedInUser == null) {
                System.out.println("Invalid login, please try again.");
                loginAttempt++;
                if (loginAttempt >= MAX_LOGIN_ATTEMPT) {
                    System.out.println("Too many failed login attempts, you are now locked out.");
                }
            }
        }
        if (loggedInUser != null) {
            while (optionChoice != exitChoice) {
                displayChoiceMenu(loggedInUser);
                optionChoice = Integer.parseInt(userInput.nextLine());
                if (optionChoice == 0 && "super_user".equals(loggedInUser.getRole())) {
                    System.out.println("Please select a valid email to login: ");
                    //Finish below - currently just an output of the input
                    String input = userInput.nextLine();
                            System.out.println(input);

                } else if (optionChoice == 1) {
                    updateEmail(loggedInUser);
                } else if (optionChoice == 2) {
                    updatePassword(loggedInUser);
                } else if (optionChoice == 3) {
                    updateName(loggedInUser);
                } else if (optionChoice == 4) {
                    System.out.println("Exiting");
                } else {
                    System.out.println("Invalid input, try again");
                }
                writeUser();
            }
        }

        userInput.close();

    }

    private void writeUser () {

        /*Needs work. Ran into an issue before where I edited the text file myself, deleting a line
        which caused the Array.sort to throw an error. Due to the nature on how the writer works
        it deleted the files, and wasn't able to re-write
        - Will look into this at a later date
         */

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
            Arrays.sort(database);

            for (User user : database) {
                writer.write(output(user));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("There was an I/O exception");
            e.printStackTrace();
        }
    }


    private User getUser(String email, String password) {
        for (User user : database) {
            if (user.getEmail().equalsIgnoreCase(email) &&
                    user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }


    private void displayChoiceMenu (User loggedInUser) {
        System.out.println("Welcome, " + loggedInUser.getName());
        System.out.println("----------------------");
        System.out.println("Please select an option");
        if ("super_user".equals(loggedInUser.getRole())) {
            System.out.println("0: Log in as another user");
        }
        System.out.println("1: Update username");
        System.out.println("2: Update password");
        System.out.println("3: Update name");
        System.out.println("4: Exit");
    }

    private void updateEmail(User loggedInUser) {
        System.out.println("Please enter a new email: ");
        String email = userInput.nextLine();
        loggedInUser.setEmail(email);
    }

    private void updatePassword(User loggedInUser) {
        System.out.println("Please enter a new password: ");
        String password = userInput.nextLine();
        loggedInUser.setPassword(password);
    }

    private void updateName(User loggedInUser) {
        System.out.println("Please enter your name: ");
        String name = userInput.nextLine();
        loggedInUser.setName(name);
    }

    public String output(User o) {
        return o.getEmail() + ", " + o.getPassword() + ", " + o.getName() + ", " + o.getRole() + "\n";
    }

}
