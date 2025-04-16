# Simple Login/Signup App

## How it works

### File Storage System

I am using a simple text file called `users.txt` to store all user information, so like:

- When a user signs up, their information (username, email, password) is saved as a new line in `users.txt`
- When a user tries to log in, the app checks if their username and password match what's in the file

### The Logout Process

1. User clicks the logout button
2. The app takes them back to the login screen
3. They'll need to enter their username and password again to get back in

Each line in the file looks like:
```
username,email,password
```
