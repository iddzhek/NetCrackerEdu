package com.nc.edu.ta.artemryabtsev.pr7;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonSimpleParser {

    public Root parser(){

        Root root = new Root();

        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("users.json")){
            JSONObject rootJsonObject = (JSONObject) parser.parse(reader);

            JSONArray userJsonArray = (JSONArray) rootJsonObject.get("users");

            List<Users> usersList = new ArrayList<>();

            for(Object usersObject: userJsonArray){
                JSONObject usersJsonObject = (JSONObject) usersObject;

                String nameUsers = (String) usersJsonObject.get("Name");
                String passwordUsers = (String) usersJsonObject.get("Password");
                String confirmPasswordUsers = (String) usersJsonObject.get("ConfirmPassword");
                String emailUsers = (String) usersJsonObject.get("Email");

                Users users = new Users(nameUsers, passwordUsers, confirmPasswordUsers, emailUsers);
                usersList.add(users);
            }

            root.setUsers(usersList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return root;
    }
}
