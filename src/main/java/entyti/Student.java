package entyti;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Student extends Person {
    private String MSSV;
    private String school;
    private int collegeStartYear;
    private float mediumScore;
    private AcademicAbility academicAbility;

    public enum AcademicAbility {
        KEM, YEU, TRUNGBINH, KHA, GIOI, XUATSAC
    }

    public Student() {
        super();
    }

    @Override
    public String toString() {
        return "Student:" +
                "id=" + id +
                ", fullName='" + fullName + "\n" +
                ", birthDay=" + birthDay + "\n" +
                ", address='" + address + "\n" +
                ", height=" + height + "cm" + "\n" +
                ", weight=" + weight + "kg" + "\n" +
                ", MSSV='" + MSSV + "\n" +
                ", school='" + school + "\n" +
                ", collegeStartYear='" + collegeStartYear + "\n" +
                ", mediumScore=" + mediumScore + "\n" +
                ", academicAbility=" + academicAbility +
                ".";
    }

    public String getMSSV() {
        return MSSV;
    }

    public float getMediumScore() {
        return mediumScore;
    }

    public String getSchool() {
        return school;
    }

    public int getCollegeStartYear() {
        return collegeStartYear;
    }

    public AcademicAbility getAcademicAbility() {
        return academicAbility;
    }

    public void setAcademicAbility(AcademicAbility academicAbility) {
        this.academicAbility = academicAbility;
    }


    // hàm xữ lý đầu vào sinh viên
    public void inputStudent(String mssv) {
//      thêm MSSV
        this.MSSV = mssv;
//      thêm tên
        String fullNames;
        do {
            System.out.print("Nhập tên sinh viên: ");
            fullNames = new Scanner(System.in).nextLine();
            if (fullNames.isBlank()) System.out.println("Vui lòng nhập tên sinh viên");
            if (fullNames.length() > 100) System.out.println("Nhập sai! Mời nhập lại.");
        } while (fullNames.length() > 100 || fullNames.isBlank());
        this.fullName = fullNames;
//      thêm ngày sinh
        do {
            try {
                System.out.print("Nhập ngày sinh sinh viên:" + "dạng dd/MM/yyyy");
                String dateString = new Scanner(System.in).nextLine();
                String dateFormat = "dd/MM/yyyy";
                Date dateNew = new SimpleDateFormat(dateFormat).parse(dateString);
                if (dateNew == null) {
                    System.out.println("Nhập sai! Mời nhập lại.");
                } else {
                    this.birthDay = dateNew;
                }
            } catch (Exception e) {
                System.out.println(" Dữ liệu không hợp lệ, vui lòng nhập đúng yêu cầu: ngày<31 tháng<12 năm<3000");
            }


        } while (this.birthDay == null);


//      thêm địa chỉ
        do {
            System.out.print("Nhập địa chỉ: ");
            this.address = new Scanner(System.in).nextLine();
            if (this.address.isBlank()) System.out.println("Vui lòng nhập địa chỉ sinh viên");
            if (this.address.length() > 300) System.out.println("Nhập sai! Mời nhập lại.");
        } while (this.address.length() > 300);
//      thêm chiều cao
        do {
            try {
                System.out.print("Nhập chiều cao sinh viên: ");
                this.height = Float.parseFloat(new Scanner(System.in).nextLine());
            } catch (Exception e) {
                System.out.println(" chiều cao phải là kiểu số : ");
            }
        } while (this.height == 0);
//      thêm cân nặng
        do {
            try {
                System.out.print("Nhập cân nặng sinh viên: ");
                this.weight = Float.parseFloat(new Scanner(System.in).nextLine());
            } catch (Exception e) {
                System.out.println(" cân nặng phải là kiểu số : ");
            }
        } while (this.weight == 0);
//     thêm tên trường đang học
        do {
            System.out.print("Nhập tên trường đang theo học: ");
            this.school = new Scanner(System.in).nextLine();
            if (this.school.isBlank()) System.out.println("Vui lòng nhập trường sinh viên đang theo học");
            if (this.school.length() > 200) System.out.println("Nhập sai! Mời nhập lại.");
        } while (this.school.length() > 200);
//      thêm năm bắt đàu theo học
        do {
            try {
                System.out.print("Nhập năm bắt đầu theo học: ");
                Scanner scanner = new Scanner(System.in);
                this.collegeStartYear = scanner.nextInt();
            } catch (Exception e) {
                System.out.println(" năm phải là kiểu số và > 1900: ");
            }
        } while (this.collegeStartYear < 1900);

//       thêm điểm trung bình
        boolean loop;
        do {
            try {
                System.out.print("Nhập điểm trung bình ");
                this.mediumScore = Float.parseFloat(new Scanner(System.in).nextLine());
                if (this.mediumScore > 10 || this.mediumScore < 0) {
                    loop = true;
                    System.out.println("Nhập sai! Mời nhập lại.");
                } else {
                    loop = false;
                }
            } catch (Exception e) {
                loop = true;
                System.out.println(" điểm phải là kiểu số và điểm từ 0.0 đến 10 : ");
            }
        } while (loop);
    }


}


