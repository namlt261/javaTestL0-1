package entyti;

import java.util.Date;

public   class Person {
//    sử dụng một biến static và một phương thức tĩnh để tăng giá trị của biến static đó mỗi khi tạo một đối tượng mới

    protected static int count = 0;
    protected int id;
    protected String fullName;
    protected Date birthDay;
    protected String address;
    protected float height;
    protected float weight;


    public Person() {
        count++;
        this.id = count;
    }

    public int getId() {
        return id;
    }
    public String getFullName() {
        return fullName;
    }
    public Date getBirthDay() {
        return birthDay;
    }

    public String getAddress() {
        return address;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDay=" + birthDay +
                ", address='" + address + '\'' +
                ", height=" + height + "cm" +
                ", weight=" + weight + "kg" +
                '}';
    }
}
