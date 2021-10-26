import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class NewPerson {

    public static void main(String[] args) throws SQLException {
        Scanner personfirstnameInput = new Scanner(System.in);
        System.out.println("What's the firstname?");
        String firstname = personfirstnameInput.nextLine();


        Scanner personLastnameInput = new Scanner(System.in);
        System.out.println("What's the lastname?");
        String lastname = personLastnameInput.nextLine();


        Scanner personAgeInput = new Scanner(System.in);
        System.out.println("What's the age?");
        Long age = Long.valueOf(personAgeInput.nextLine());


        Connection connection = MakeConnection.getConnection();
        /* To avoid SQL injection attack, we use prepared statements with ? */
        String sql = "INSERT INTO persons (firstname, lastname, age) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, firstname);
        statement.setString(2, lastname);
        statement.setLong(3, age);

        System.out.println("Person included.");
        statement.execute();

        personfirstnameInput.close();
        personLastnameInput.close();
        personAgeInput.close();
    }
}
