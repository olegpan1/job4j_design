package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties properties;
    private String dump;

    public ImportDB(Properties properties, String dump) {
        this.properties = properties;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(s -> {
                String[] split = s.split(";");
                users.add(new User(split[0], split[1]));
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.username"),
                properties.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = connection.prepareStatement(
                        "insert into users(name , email) values (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream("./app.properties")) {
            properties.load(in);
        }
        ImportDB db = new ImportDB(properties, "./dump.txt");
        db.save(db.load());
    }
}
