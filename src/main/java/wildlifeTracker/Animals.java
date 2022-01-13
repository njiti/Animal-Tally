package wildlifeTracker;
import org.sql2o.*;



public abstract class Animals {
    public int id;
    public String name;
    public boolean endangered;
    private Animals newAnimal;


    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object otherAnimal){
        if(otherAnimal instanceof Animals){
            return this.getName().equals(newAnimal.getName()) &&
                    this.getId()==(newAnimal.getId());
        } else {
            return false;
        }
    }
    public void save(){
        if (name.equals("") ) {
            throw new IllegalArgumentException("You must Enter Name.");
        }
        try(Connection connect = DB.sql2o.open()){
            String sql = "INSERT INTO animals (name, endangered) VALUES (:name, :endangered);";
            this.id = (int) connect.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("endangered", this.endangered)
                    .executeUpdate()
                    .getKey();
        }
    }
}
