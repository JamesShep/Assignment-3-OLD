In this assignment we'll be extending the functionality of Assignment #3.

We will be adding an additional field to our User object to include a "role" (as well as username, password and name)






User

  - String username
  - String password
  - String name
  - String role  // new field to add to our User class







The role that's assignment to a User will be either: normal_user or super_user

Based on the User's role (when successfully logged in), they will have different actions they can perform.

Hint: You should use Object Oriented principles (including inheritance) when designing this functionality. Remember the "has a" vs "is a" test that you can perform to detect possible inheritance in your design.

Normal User

When logged in as a Normal User, you should be able to update any of the fields that belong to this particular user instance, except for their role.

For example, you should prompt this logged in user with the option to update their username, password or name.

When the user updates their information, you should output this information back to the users.txt file and make sure that the updated information is still properly sorted. To learn how to properly sort the file, see the below section entitled "How to Sort the File".

Click here to download the new users.txt file

Super User

When logged in as a Super User, you should have all the same options as a Normal User.

You will also have the ability to login as any other User by specifying a valid username. When logged in as another user, you will be able to access all the options that a Normal User has access to.

Note: Once you've switched to another user's account, you're not expected to be able to "exit" back into the super user's account. In other words, if you choose the "exit" option, the program will terminate.

How to Sort the File

The file should sort Super Users before Normal Users.

The file should also sort usernames in alphabetical order (ascending order).

Helpful Hints

When using a Scanner, I'd recommend only declaring it / instantiating it once and using that one instance throughout the entire application. Also, you should only close the scanner right before the program terminates.

I've also noticed issues when switching between the types of scanner reads, so reading a line of text, then switching to reading an int, then trying to switch back to reading a line causes issues. I'd recommend sticking with only reading lines.

Example Workflow

The example workflow given below shows this path:

1) A super user logs in with the correct credentials
2) This super user chooses to login as a normal user
3) The super user types in a valid username of another user
4) The super user is now logged in as another user
5) The name property of the normal user is updated
6) The user exits

Enter your email:

a@a.com

Enter your password:

asdfasdf

Welcome: Amber Parker

----------

Please choose from the following options:

(0) Log in as another user

(1) Update username

(2) Update password

(3) Update name

(4) Exit

0

Which user would you like to login as? (Type in a valid username)

biglou@live.com

Welcome: Shirley Salazar

----------

Please choose from the following options:

(1) Update username

(2) Update password

(3) Update name

(4) Exit

3

Please type in your new name:

Shirley Temple

Welcome: Shirley Temple

----------

Please choose from the following options:

(1) Update username

(2) Update password

(3) Update name

(4) Exit

4