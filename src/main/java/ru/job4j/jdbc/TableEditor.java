package ru.job4j.jdbc;


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException {
        connection = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"), properties.getProperty("password"));
    }

    private void makeStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "create table if not exists %s();",
                tableName
        );
        makeStatement(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "drop table %s;",
                tableName
        );
        makeStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "alter table %s add column %s %s;",
                tableName,
                columnName,
                type
        );
        makeStatement(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "alter table %s drop column %s;",
                tableName,
                columnName
        );
        makeStatement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "alter table %s rename column %s to %s;",
                tableName,
                columnName,
                newColumnName
        );
        makeStatement(sql);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        String dbPropertiesFile = "app.properties";
        String tableName = "new_db";
        Properties properties = new Properties();

        try (BufferedReader read = new BufferedReader(new FileReader(dbPropertiesFile))) {
            properties.load(read);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TableEditor tableEditor = new TableEditor(properties);

        tableEditor.createTable(tableName);
        System.out.println(getTableScheme(tableEditor.connection, tableName));

        tableEditor.addColumn(tableName, "price", "money");
        System.out.println(getTableScheme(tableEditor.connection, tableName));

        tableEditor.renameColumn(tableName, "price", "newprice");
        System.out.println(getTableScheme(tableEditor.connection, tableName));

        tableEditor.dropColumn(tableName, "newprice");
        System.out.println(getTableScheme(tableEditor.connection, tableName));

        tableEditor.dropTable(tableName);

        tableEditor.close();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
