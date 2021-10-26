import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConsultAllPersons {

    public static void main(String[] args) throws SQLException {
        Connection connection = MakeConnection.getConnection();
        String sql = "SELECT * FROM persons";

        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(sql);

        ArrayList<Person> showAllPersons = new ArrayList<>();

        while(result.next()) {
            String firstname = result.getString("firstname");
            String lastname = result.getString("lastname");
            Long age = result.getLong("age");
            showAllPersons.add(new Person(firstname, lastname, age));
        }

        for (Person person : showAllPersons) {
            System.out.println(person.getFirstname() + " " +  person.getLastname() + " " + person.getAge());
        }
        statement.close();
    }

}
