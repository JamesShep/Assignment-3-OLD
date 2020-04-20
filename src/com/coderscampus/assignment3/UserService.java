package com.coderscampus.assignment3;

// Java imports
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UserService {

    User[] database = new User[20];

    public User createUser(String email, String password, String name, String role) {

        User user = new User();

        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setRole(role);
        return user;
    }

    public void retrieveUser() {

        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader("users.txt"));

            String line;
            int i = 0;

            while ((line = fileReader.readLine()) != null) {

                String[] dataArray = line.split(", ");

                String email = dataArray[0];
                String password = dataArray[1];
                String name = dataArray[2];
                String role = dataArray[3];

                database[i] = createUser(email, password, name, role);
                i++;

/*                if (dataArray[3].equals("super_user")) {
                    database[i] = new SuperUser(dataArray[0], dataArray[1], dataArray[2], dataArray[3]);
                } else if (dataArray[3].equals("normal_user")){
                    database[i] = new NormalUser(dataArray[0], dataArray[1], dataArray[2], dataArray[3]);
                } else {
                    System.out.println("Error");
                }
                i++;
                */
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
    }

    public void validateLogin() {

        String inputEmail;
        String inputPassword;
        String verifySuperUser = "super_user";

        boolean superUser = false;
        boolean normalUser = false;

        int loginAttempt = 0;
        int optionChoice = 0;

        final int MAX_LOGIN_ATTEMPT = 5;
        boolean successfulLogin = false;

        Scanner userInput = new Scanner(System.in);

        while (loginAttempt < MAX_LOGIN_ATTEMPT && !successfulLogin) {

            System.out.println("Enter your email:");
            inputEmail = userInput.next();
            System.out.println("Enter your password:");
            inputPassword = userInput.next();

            for (User user : database) {
                if (inputEmail.equalsIgnoreCase(user.getEmail()) &&
                        inputPassword.equals(user.getPassword())) {
                    System.out.println("Welcome " + user.getName());
                    if (verifySuperUser.equals(user.getRole())) {
                        superUser = true;
                    } else {
                        normalUser = true;
                    }
                    System.out.println(user.getRole());
                    successfulLogin = true;
                }
            }
            if (!successfulLogin) {
                loginAttempt++;
                if (loginAttempt != MAX_LOGIN_ATTEMPT) {
                    System.out.println("Invalid login, please try again.");
                }
            }
        }
        if (loginAttempt >= MAX_LOGIN_ATTEMPT) {
            System.out.println("Too many failed login attempts, you are now locked out.");
        }

        //Make the below an options menu through methods
        if (successfulLogin) {
            if (superUser) {
                System.out.println("-----------------------");
                System.out.println("Please select an option");
                System.out.println("1: Log in as another user");
                System.out.println("2: Update password");
                System.out.println("3: Update name");
                System.out.println("4: Exit");
                optionChoice = userInput.nextInt();



/*                while (optionChoice != 4) {
                    switch (optionChoice) {
                        case 0:
                            System.out.println("Log in as another user");
                        case 1:
                            System.out.println("Update username");
                        case 2:
                            System.out.println("Update password");
                        case 3:
                            System.out.println("Update name");
                        case 4:
                            System.out.println("Exit");
                            System.exit(0);
                    }
                    }*/

                }
            } else {
                System.out.println("----------------------");
                System.out.println("Please select an option");
                System.out.println("1: Log in as another user");
                System.out.println("2: Update password");
                System.out.println("3: Update name");
                System.out.println("4: Exit");
                optionChoice = userInput.nextInt();
            } userInput.close();
    }
}
