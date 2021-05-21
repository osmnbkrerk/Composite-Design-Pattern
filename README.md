Kompozit Tasarım Deseni 

Osman Bekir Erik 
 

Composite tasarım kalıbı tekil component ve birbirinden farklı componentler grubunun hiyerarşik bir yapıda benzer şekilde hareket etmesini yani kendi içlerinde birbirlerinden farklı olan bir grup nesnenin sanki tek bir bütün nesneymiş gibi kullanılmasını bileşik kalıp sağlar.

 

Netbeans’e sonradan eklenebilen bir plugin olan easyUML’i kullanarak hazırladığım bir diyagram.  












 
Kompozit tasarım deseninin genel olarak gösterimi bu şekildedir.  
Leaf,  Tekil objemizi temsil eder. Bizim örneğimizdeki Developer adlı sınıfa tekabül ediyor.  
Composite, Component’ların bir araya gelerek oluşturuğu sınıftır. Yani Manager adlı sınıfımız Composite class’tır. 
Component ise hem composite hem de component class’ının türeyeceği arayüzdür. İstenilirse soyut bir sınıf olarak da tanımlanabilir.  Fakat biz örneğimizde Employee adında arayüz oluşturduk. 



public interface Employee {

 	public void add(Employee emp);
 	public void remove(Employee emp);
 	public Employee getChild(int i);
 	public String getName();
 	public double getSalary();
 	public void print();

}

Employee adlı arayüzümüzde yeni bir çalışan eklemek için add(), çalışanı çıkarmak için remove(), i’ninci çalışanı return etmek için getChild(), çalışanın adını göstermek için getName(), maaşını göstermek için getSalary(), çalışanların özelliklerini ekrana çıktı olarak vermek için ise print() fonksiyonlarını oluşturduk. 

Employee arayüzüne implement edecek sınıflardan 1 tanesi Leaf diğeri ise Composite sınıfı olacak. 











Manager sınıfımıza geçelim. Öncelikle paketimize Çalışanları tutmak için ArrayList’i ve List’i import ediyoruz. Iterator’ü ise bir düğüm oluşturmuşuz gibi calisan.sonraki  yani bir sonra eklenilen çalışanı oluşturup ekrana print etmemizi sağlıyor. Patron -> Personel -> İşçi vs vs 

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Manager implements Employee {

  private String name;
  private double salary;

  public Manager(String name, double salary) {
    this.name = name;
    this.salary = salary;
  }

  List < Employee > employees = new ArrayList < Employee > ();

  @Override
  public void add(Employee emp) {
    employees.add(emp);
  }

  @Override
  public void remove(Employee emp) {
    employees.remove(emp);
  }

  @Override
  public Employee getChild(int i) {
    return employees.get(i);
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
    System.out.println( "----------------" );
    System.out.println( "Name = " + getName() );
    System.out.println( "Salary = " + getSalary() );
    System.out.println( "----------------" );

    Iterator < Employee > empIterator = employees.iterator();
    while (empIterator.hasNext()) {
      Employee emp = empIterator.next();
      emp.print();
    }
  }
}
Manager sınıfımızda çalışanımızın maaşı ve ismi gibi özellikler bulunmakta. Bunları sınıfın kurucu metodunda set ediyoruz. Çalışanları bir listede tutmak için employees adında ArrayList oluşturuyoruz.  
Arayüzden gelen metotlarımızı override ederek bu metotlara oluşturduğumuz listeye ekleme çıkarma işlemlerini sağlatıyoruz. 
Print() metodunda ise Çalışanımızın ismini maaşını ekranda göstererek Listemizin içinde gezinmek için listemizin fonksiyonu olan iterator()’ü empIterator adlı değişkenimize atıyoruz. 

Tıpkı bağlı listedeki gibi döngümüzde empIterator.hasNext(), yani Veri Yapıları dersinde gördüğümüz while(gecici.sonraki) döngüsüne benzer bir şekilde listemizin içinde geziniyoruz. Döngünün içinde çalışanları sırasıyla print ediyoruz.


















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
        System.out.println( "----------------" );
        System.out.println( "Name = " +getName());
        System.out.println( "Salary = " +getSalary());
        System.out.println( "----------------" );
    }
}


Gelelim Developer Sınıfımıza.  Bu da aynı Manager sınıfımız gibi name, salary gibi özelliklerine sahip ve ayrıca Employee arayüzümüze implement ediyor. Fakat implement etmesine ragmen bu sınıf artık bizim tekil objemiz(Leaf node) olduğu için arayüzden gelen bazı fonksiyonları kullanamayacak. 
Bunlar add(), remove(), getChild() fonksiyonlarıdır. 
Bu sınıfta yapılan tek iş çalışanın adını ve maaşını göstermek.


Artık Client oluşturmanın vakti geldi geçti bile. Test sınıfımızı oluşturalım.

public class CompositePatternTest {
    public static void main(String[] args){
    
    Employee emp1 = new Developer( "John" , 10000);
    Employee emp2 = new Developer( "David" , 15000);
    
    Employee manager1 = new Manager( "Daniel" , 25000);
    manager1.add(emp1);
    manager1.add(emp2);
    
    Employee emp3 = new Developer( "Michael" , 20000);
    
    Manager generalManager = new Manager( "Mark" , 50000);
    generalManager.add(emp3);
    generalManager.add(manager1);
    generalManager.print();
        
    }

CompositePatternTest adlı test sınıfımızın main metodunda, emp1 adında bir developer oluşturuyoruz. İsmi John maaşı ise 10000.  emp2 adlı bir developer daha oluşturuyoruz. 

Bu oluşturduğumuz çalışanları manager1’in altına koymak için manager1.add(emp1) diyoruz. Aynı şekilde emp2 yi de manager1’in altına ekliyoruz. 

Emp3 adında bir developer daha oluşturuyoruz. generalManager adlı çalışanımız emp3 ve manager1’i altına ekliyor ve sonuç olarak şöyle bir yapı ortaya çıkıyor. Biliyorum yazarak anlatınca çok açıklayıcı olmuyor bazen resimlerle de desteklemek gerekiyor. 

 
Buna benzer bir yapının ortaya çıktığını söyleyebiliriz.



Ekran çıktımız  ise şu şekilde; 
----------------

Name = Mark
Salary = 50000.0

----------------
----------------

Name = Michael
Salary = 20000.0

----------------
----------------

Name = Daniel
Salary = 25000.0

----------------
----------------

Name = John
Salary = 10000.0

----------------
----------------

Name = David
Salary = 15000.0

----------------
