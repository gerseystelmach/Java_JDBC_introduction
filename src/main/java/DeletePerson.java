import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeletePerson {

    public static void main(String[] args) throws SQLException {

        Scanner input = new Scanner(System.in);

        System.out.println("What's the firstname of the person you'd like to delete?");
        String firstname = input.nextLine();

        System.out.println("What's the lastname of the person you'd like to delete?");
        String lastname = input.nextLine();

        Connection connection = MakeConnection.getConnection();

        String deleteSql = "DELETE FROM persons WHERE firstname = ? AND lastname = ?";

        PreparedStatement statement = connection.prepareStatement(deleteSql);
        statement.setString(1, firstname);
        statement.setString(2, lastname);

        if (statement.executeUpdate() > 0) {
            System.out.println(firstname + " " + lastname + " was deleted from db.");
        } else {
            System.out.println("Attention: " + firstname + " " + lastname + " was not deleted.");
        }

        connection.close();
        input.close();
    }
}
