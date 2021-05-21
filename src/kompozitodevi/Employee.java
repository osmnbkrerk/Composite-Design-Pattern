package kompozitodevi;

public interface Employee {

    public void add(Employee emp);
    public void remove(Employee emp);
    public Employee getChild(int i);
    public String getName();
    public double getSalary();
    public void print();
    
}
