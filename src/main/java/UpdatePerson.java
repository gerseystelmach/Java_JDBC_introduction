import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdatePerson {

    public static void main(String[] args) throws SQLException {

        Scanner input = new Scanner (System.in);
        System.out.println("What is the Person's firstname?");
        String firstname = input.nextLine();
        System.out.println("What is the Person's lastname?");
        String lastname = input.nextLine();

        String selectSql = "SELECT firstname, lastname, age FROM persons WHERE firstname = ? AND lastname = ?";

        Connection connection = MakeConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(selectSql);
        statement.setString(1, firstname);
        statement.setString(2, lastname);

        ResultSet result = statement.executeQuery();

        String updateSQL = "UPDATE persons SET firstname = ?, lastname = ?, age = ? WHERE firstname = ? AND lastname = ?";
        /* If a person with the name and lastname was found, I can update it. */
        if (result.next()) {
            /* Taking the columns 1, 2, 3 from the select query */
            Person person = new Person(result.getString(1), result.getString(2), result.getLong(3));
            System.out.println("Person found: " + person.getFirstname() + " " + person.getLastname() + " " + person.getAge());

            System.out.println("Insert the new firstname");
            String newFirstname = input.nextLine();

            System.out.println("Insert the new lastname");
            String newLastname = input.nextLine();

            System.out.println("Insert the new age");
            Long newAge = input.nextLong();


            statement.close();
            statement = connection.prepareStatement(updateSQL);
            statement.setString(1, newFirstname);
            statement.setString(2, newLastname);
            statement.setLong(3, newAge);
            statement.setString(4, person.getFirstname());
            statement.setString(5, person.getLastname() );
            statement.execute();

            System.out.println(person.getFirstname() + " record was modified.");

        }
        connection.close();
        input.close();

    }
}
