package ru.itis.dao;

import com.google.common.base.Splitter;
import ru.itis.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoFileBasedImpl implements UsersDao {

    private BufferedReader fileReader;
    private BufferedWriter fileWriter;
    private String inputPath;
    private String outputPath;

    private static final String EOL = System.getProperty("line.separator");

    public UsersDaoFileBasedImpl(String filePath, String inFileName, String outFileName) {
        inputPath = filePath + inFileName;
        outputPath = filePath + outFileName;
        openOutStream();
        openInStream();
    }

    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try {
            openInStream();
            String currentLine = fileReader.readLine();
            while (currentLine != null) {
                User currentUser = parseStringAsUser(currentLine);
                result.add(currentUser);
                currentLine = fileReader.readLine();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("SomeError " + e.getMessage());
        }
        return result;
    }

    @Override
    public User get(int userId) {
        List<User> allUsers = getAll();
        for (User user : allUsers) {
            if (user.getId() == userId) {
                return user;
            }
        }

        return null;
    }

    @Override
    public void save(User user) {
        String content = "";

        for (User u : getAll()) {
            if (u.getId() == user.getId()) {
                u = user;
            }
            content += ((u.getId() == user.getId()) ? user.toString() : u.toString()) + EOL;
        }

        try {
            openOutStream();
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File writing error: " + e.getMessage());
        }
    }

    @Override
    public void delete(int userId) {
        String content = "";
        for (User user : getAll()){
            content += ((user.getId() != userId) ? user.toString() + EOL : "");
            System.out.println(user);
        }
        openOutStream();
        try {
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private User parseStringAsUser(String line) {
        Splitter splitter = Splitter.on(" ");

        List<String> lines = splitter.splitToList(line);

        int id = Integer.parseInt(lines.get(0));
        String name = lines.get(1);
        String password = lines.get(2);
        int age = Integer.parseInt(lines.get(3));

        return new User(id, name, password, age);
    }

    private void openInStream(){
        try {
            fileReader = new BufferedReader(new FileReader(inputPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void openOutStream(){
        try {
            fileWriter = new BufferedWriter(new FileWriter(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
