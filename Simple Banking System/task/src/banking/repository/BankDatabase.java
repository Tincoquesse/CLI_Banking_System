package banking.repository;

import org.sqlite.SQLiteDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.concurrent.ThreadLocalRandom;


public class BankDatabase {

    SQLiteDataSource dataSource = new SQLiteDataSource();

    public BankDatabase(String string) {

        String url = "jdbc:sqlite:" + string;
        dataSource.setUrl(url);
        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {

                statement.executeUpdate("CREATE TABLE IF NOT EXISTS card (\n" +
                        "id INTEGER PRIMARY KEY,\n" +
                        "number TEXT NOT NULL\n," +
                        "pin TEXT NOT NULL,\n" +
                        "balance INTEGER DEFAULT 0)");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveCardToDatabase(long number, int pin) {
        long id = ThreadLocalRandom.current().nextLong(10000,99999);

        String query = "INSERT INTO card VALUES " +
                "(" + id + ", " + number + ", " + pin + ", 0)";

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate(query);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long getPinFromDatabase(Long cardNumber) {

        String query = "SELECT pin from card WHERE number = '" + cardNumber + "'";

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                return resultSet.getLong("pin");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getBalanceFromUserAccount(Long cardNumber) {
        int balance = 0;
        String query = "SELECT balance from card WHERE number = " + cardNumber + "";

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                balance =  resultSet.getInt("balance");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    public void addIncome(Integer income, Long cardNumber) {

        String query = "UPDATE card SET balance = balance + " + income + " WHERE number = " + cardNumber + "";

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isCardNumberExist(String cardNumber) {

        Boolean flag = false;
        String query = "SELECT * FROM card WHERE number = "+ cardNumber+ "";

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                ResultSet rs = statement.executeQuery(query);
                    if (rs.next()){
                        flag = true;
                    }else {
                        flag =false;
                    }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void updateSendersBalance(int amount, Long cardNumber) {

        String query = "UPDATE card SET balance = balance - " + amount + " WHERE number = " + cardNumber + "";

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeAccount(Long cardNumber) {

        String query = "DELETE FROM card WHERE number = " + cardNumber + "";

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
