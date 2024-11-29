package data.utilities;

public class SQLQueries {
    public static final String GET_VISITOR_ID = "select Visitor_id from Visitors where Name = ?";
    public static final String INSERT_ANIMAL = "insert into Animals (Name,Species,Age,Habitat_ID) values (?,?,?,?)";
    public static final String INSERT_ANIMAL_VISIT = "insert into Animal_Visits (Animal_ID,Visitor_ID,Visit_Date) values (?,?,?)    ";
    public static final String INSERT_VISITOR = "insert into Visitors (Name,Email,Tickets) values (?,?,?)";
    public static final String GET_ANIMAL_BY_NAME = "select * from Animals where Species = ?";
    public static final String DELETE_ANIMAL = "delete from Animals where Name = ?";
    public static final String DELETE_ANIMAL_VISITORS = "delete from Animal_Visits where Animal_ID = (select Animal_ID from Animals where Name = ?)";
    public static final String GET_ANIMAL_VISITS = "select * from Animal_Visits av join Animals a on av.Animal_ID = a.Animal_ID where a.Name = ?";
    public static final String GET_VISITOR_NAME = "select v.Name from Visitors v join Animal_Visits av on v.Visitor_ID = av.Visitor_ID where v.Visitor_ID = ? ";

    private SQLQueries() {
    }
}
