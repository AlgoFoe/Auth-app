package com.example.loginsignupapp;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    private static final String FILE_NAME = "users.txt";
    private static final String TAG = "FileHelper";

    public static boolean saveUser(Context context, User user) {
        try {
            List<User> existingUsers = getUsers(context);
            for (User existingUser : existingUsers) {
                if (existingUser.getUsername().equals(user.getUsername())) {
                    Log.d(TAG, "User already exists: " + user.getUsername());
                    return false;
                }
            }

            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND);
            fos.write((user.toString() + "\n").getBytes());
            fos.close();
            Log.d(TAG, "User saved: " + user.getUsername());
            return true;
        } catch (IOException e) {
            Log.e(TAG, "Error saving user: " + e.getMessage());
            return false;
        }
    }

    public static List<User> getUsers(Context context) {
        List<User> users = new ArrayList<>();

        try {
            FileInputStream fis = context.openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                User user = User.fromString(line);
                if (user != null) {
                    users.add(user);
                }
            }

            br.close();
            isr.close();
            fis.close();
        } catch (IOException e) {
            Log.e(TAG, "Error reading users: " + e.getMessage());
        }

        return users;
    }

    public static User findUserByUsername(Context context, String username) {
        List<User> users = getUsers(context);
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static boolean authenticateUser(Context context, String username, String password) {
        User user = findUserByUsername(context, username);
        return user != null && user.getPassword().equals(password);
    }
}