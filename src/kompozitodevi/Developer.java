package kompozitodevi;

public class Developer implements Employee{

    private String name;
    private double salary;
    
    public Developer(String name, double salary){
        this.name = name;
        this.salary = salary;
    }
    
    @Override
    public void add(Employee emp) {
        //Bu tekil objedir o yuzden bu metot bu sınıf için geçerli değildir.
    }

    @Override
    public void remove(Employee emp) {
        //Bu tekil objedir o yuzden bu metot bu sınıf için geçerli değildir.
    }

    @Override
    public Employee getChild(int i) {
        //Bu tekil objedir o yuzden bu metot bu sınıf için geçerli değildir.
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void print() {
        System.out.println("----------------");
        System.out.println("Name = "+getName());
        System.out.println("Salary = "+getSalary());
        System.out.println("----------------");
    }
}
