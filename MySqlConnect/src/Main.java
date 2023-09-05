import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            System.out.println("Successful");
            String url = "jdbc:mysql://127.0.0.1:3306/PersonDbs";
            String UserName = "root";
            String Psw = "root";
            try (Connection connection = DriverManager.getConnection(url, UserName, Psw)) {
                System.out.println("Connection MySqlDB complete");
                Statement statement = connection.createStatement();
                //statement.executeQuery(); //Select   +
                //statement.execute();
                //statement.executeUpdate();
                 //Update, Insert +, Delete +, Create +, Drop

                String SQL = "insert Persons(name, surname, age) values('Takeshi', 'Kitano', 86),('Takeshi', 'Kitano', 86) " ;
                int value = statement.executeUpdate(SQL);
                System.out.println(value + " Addet");

                SQL = "SELECT * FROM Students" ;
                ResultSet resultSet = statement.executeQuery(SQL);
                while (resultSet.next()){
                   System.out.println(resultSet.getInt(1) + "  :  " + resultSet.getString(2)+ "  :  " + resultSet.getString(3)+ "  :  " + resultSet.getInt(4) );
                        System.out.println(resultSet.getInt("Id") + "  :  " + resultSet.getString("Firstname")+ "  :  " + resultSet.getString("Lastname")+ "  :  " + resultSet.getInt("Age") );

                };

                String sqlScript = "CREATE TABLE Cars (Id INTEGER AUTO_INCREMENT PRIMARY KEY, Mark VARCHAR(30), Model VARCHAR(30), Year INTEGER )" ;
                int createdTables = statement.executeUpdate(sqlScript);

                System.out.println("Created " + createdTables + " rows" );


                sqlScript = "insert into Cars (Mark,Model,Year) values ('BMW','X5','2022'),('BMW','M5','2019'),('Audi','Q5','2023'),('Nissan','Patrol','2020'),('Lada','Granta','2025')" ;
                int result = statement.executeUpdate(sqlScript);

                System.out.println("Inserted " + result + " rows" );


                sqlScript = "delete from Cars";
                result = statement.executeUpdate(sqlScript);

                System.out.println("Deleted " + result + " rows");


                sqlScript = "delete from Cars where Year < 2022 or Model not like '%5%'";
                result = statement.executeUpdate(sqlScript);

                System.out.println("Deleted " + result + " rows");


                sqlScript = "UPDATE Cars SET Year = 2023 where Year <= 2022";
                result = statement.executeUpdate(sqlScript);

                System.out.println("Update " + result + " rows");

                sqlScript = "drop TABLE Cars";
                result = statement.executeUpdate(sqlScript);

                System.out.println("Drop " + result + " rows");



            }
        } catch (Exception ex) {
            System.out.println("Connection false");
        }
    }
}