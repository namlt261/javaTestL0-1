package service;

import entyti.Student;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static entyti.Student.AcademicAbility.*;

public class Service {
    ArrayList<Student> students = new ArrayList<>(50);

    public void addStudent() throws ParseException {
        System.out.print("Nhập số sinh viên được thêm vào: ");
        int studentNumber = -1;
        do {
            studentNumber = new Scanner(System.in).nextInt();
            if (studentNumber < 0) System.out.println("Nhập sai! Mời nhập lại.");
        } while (studentNumber < 0);
        for (int i = 0; i < studentNumber; i++) {
            System.out.println("nhập thông tin sinh viên thứ " + (i + 1));
            Student student = new Student();
            String mssv;
            Student checkDublicateMssv = null;
            do {
                System.out.print("Nhập mã số sinh viên: ");
                mssv = new Scanner(System.in).nextLine();
                if (mssv.length() != 10) {
                    System.out.println("Nhập sai! Mời nhập lại.");
                } else {
                    checkDublicateMssv = findPersonByMssv(mssv, students);
                    if (checkDublicateMssv != null) {
                        System.out.print(" mã số sinh viên đã tồn tại: ");
                    } else {
                        break;
                    }
                }
            } while (mssv.length() != 10);

//            truyền thông tin nhập vào
            student.inputStudent(mssv);
//            kiểm tra học lực
            float check = student.getMediumScore();
            if (check < 3)
                student.setAcademicAbility(KEM);
            if (check >= 3 && check < 5)
                student.setAcademicAbility(YEU);
            if (check >= 3 && check < 6.5)
                student.setAcademicAbility(TRUNGBINH);
            if (check >= 6.5 && check < 7.5)
                student.setAcademicAbility(KHA);
            if (check >= 7.5 && check < 9)
                student.setAcademicAbility(GIOI);
            if (check >= 9)
                student.setAcademicAbility(XUATSAC);

//            lưu đối tượng vào mảng
            students.add(student);
            System.out.println("\nThông tin của các học sinh:");
        }
        showStudentInput();
    }


    public static Student findPersonByMssv(String inputMssv, ArrayList<Student> students) {
        for (Student student1 : students) {
            if (student1 == null)
                break;
            if (student1.getMSSV().equalsIgnoreCase(inputMssv)) {
                return student1;
            }
        }
        return null;
    }

    //    hàm hiển thị danh sách sinh viên chi tiết
    public void showStudent() {
        System.out.println("Danh sách sinh viên ");
        for (entyti.Student student : students) {
            System.out.println("No " + (student.toString()) + " : ");
        }
    }

    //    hàm hiển thị danh sách sinh viên tối dãn thông tin khi nhập xong
    public void showStudentInput() {
        for (int j = 0; j < students.size(); j++) {
            if (students == null) break;
            Student student1 = students.get(j);
            System.out.println("Học sinh thứ " + (j + 1) + ":");
            System.out.println("  Mã số sinh viên: " + student1.getMSSV());
            System.out.println("  Họ tên: " + student1.getFullName());
            System.out.println("  Ngày sinh: " + student1.getBirthDay());
            System.out.println("  Điểm trung bình: " + student1.getMediumScore());
        }
    }

    //hàm tìm kiếm theo id
    public void searchStudentId() {
        int id = 0;
        do {
            try {
                System.out.println("Nhập id sinh viên cần tìm: ");
                Scanner scanner = new Scanner(System.in);
                id = scanner.nextInt();
            } catch (Exception e) {
                System.out.println(" id phải là kiểu số: ");
            }
        } while (id == 0);
        Student result = findPersonById(id, students);
        if (result != null) {
            System.out.println("Tìm thấy sinh viên với id = " + id + ": " + result);
        } else {
            System.out.println("Không tìm thấy sinh viên với id = " + id);
        }
    }

    public static Student findPersonById(int id, ArrayList<Student> students) {
        for (Student student1 : students) {
            if (student1 == null)
                break;
            if (student1.getId() == id) {
                return student1;
            }
        }
        return null;
    }

    //hàm cập nhật thông tin sinh viên
    public void updateInformationStudent() throws ParseException {
        System.out.println("Nhập mã id cần tìm: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        Student result = findPersonById(id, students);
        if (result == null) {
            System.out.println("sinh viên không tồn tại");
        } else {
            String mssv;
            do {
                System.out.print("Nhập mã số sinh viên: ");
                mssv = new Scanner(System.in).nextLine();
                if (mssv.length() != 10) {
                    System.out.println("Nhập sai! Mời nhập lại.");
                } else {
                    if (Objects.equals(mssv, result.getMSSV())) {
                        break;
                    }
                    Student rs = findPersonByMssv(mssv, students);
                    if (rs != null) {
                        System.out.print(" mã số sinh viên đã tồn tại: ");
                    } else {
                        break;
                    }
                }
            } while (mssv.length() != 10);
            result.inputStudent(mssv);
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getId() == id) {
                    students.set(i, result);
                    break;
                }
            }
        }
    }

    //   xoá một sinh viên
    public void deleteStudent() {
        boolean loop = false;
        do {
            try {
                System.out.print("Nhập id sinh viên cần xoá");
                Scanner scanner = new Scanner(System.in);
                int id = scanner.nextInt();
                // tìm kiếm đối tượng có tồn tại trong mảng hay không
                Student result = findPersonById(id, students);
                if (result == null) {
                    System.out.println("sinh viên không tồn tại");
                    loop = true;
                    System.out.println("Nhập sai! Mời nhập lại.");
                } else {
                    loop = false;
                }

                // Tìm kiếm đối tượng có thuộc tính id tương ứng với giá trị nhập từ bàn phím và xoá nó
                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getId() == id) {
                        students.remove(i);
                        System.out.println("Đã xoá đối tượng có id là " + id);
                        break;
                    }
                }
            } catch (Exception e) {
                loop = true;
                System.out.println(" id phải là số nguyên và lớn hơn 0 ");
            }
        } while (loop);

    }

    //
    public void showStudentsByPercentage() {
        sortStudentsByPercentage(students);
    }

    public void showErcentagePointAverageOfStudents() {
        studentPercentageCalculation(students);
    }

    public static void sortStudentsByPercentage(ArrayList<Student> students) {
        Map<Student.AcademicAbility, Integer> countMap = new HashMap<>();
        countMap.put(KEM, 0);
        countMap.put(YEU, 0);
        countMap.put(TRUNGBINH, 0);
        countMap.put(KHA, 0);
        countMap.put(GIOI, 0);
        countMap.put(XUATSAC, 0);
//      dùng vòng for lặp một lượt mảng,
        for (entyti.Student student : students) {
//      tạo một đối tượng AcademicAbility, khi AcademicAbility tại i thì lấy ra cặp ky và values tại i của đối tựơng
            Student.AcademicAbility academicAbility = student.getAcademicAbility();
//      lấy values theo key
            int count = countMap.get(academicAbility) + 1;
//      put lại values theo key vào map
            countMap.put(academicAbility, count);
        }
//      tính %
        for (Map.Entry<Student.AcademicAbility, Integer> entry : countMap.entrySet()) {
            Integer value = entry.getValue();
            int percent = (value * 100) / students.size();
            entry.setValue(percent);
        }
//      thuật toán sắp xếp bằng Comparator, dùng Collections.sort để sắp xếp theo quy luật
        List<Map.Entry<Student.AcademicAbility, Integer>> list = new ArrayList<>(countMap.entrySet());

        Comparator<Map.Entry<Student.AcademicAbility, Integer>> comparator = new Comparator<Map.Entry<Student.AcademicAbility, Integer>>() {
            @Override
            public int compare(Map.Entry<Student.AcademicAbility, Integer> o1, Map.Entry<Student.AcademicAbility, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        };
        list.sort(comparator);
//      lưu lại vào map mới
        Map<Student.AcademicAbility, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Student.AcademicAbility, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
//
        for (Map.Entry<Student.AcademicAbility, Integer> entry : sortedMap.entrySet()) {
            Student.AcademicAbility name = entry.getKey();
            int percent = entry.getValue();
            String output = String.format("Xếp loại: , có: ", name, percent + "% \n");
            System.out.println(output);
        }
    }

    //    number of point types = số lượng loại điểm,percentagePointMap=phần trăm loại điểm
    public static void studentPercentageCalculation(ArrayList<Student> students) {
        Map<Float, Integer> numberOfPointTypesMap = new HashMap<>();
        for (Student student : students) {
            Float point = student.getMediumScore();
            if (numberOfPointTypesMap.containsKey(point)) {
                Integer quantityPoint = numberOfPointTypesMap.get(point);
                numberOfPointTypesMap.put(point, quantityPoint + 1);
            } else {
                numberOfPointTypesMap.put(point, 1);
            }
        }
        HashMap<Float, Float> percentagePointMap = new HashMap<>();
        for (Float point : numberOfPointTypesMap.keySet()) {
            float percentPoint = ((float) numberOfPointTypesMap.get(point) / students.size()) * 100;
            percentagePointMap.put(point, percentPoint);
        }
        for (Map.Entry<Float, Float> entry : percentagePointMap.entrySet()) {
            Float name = entry.getKey();
            Float percent = entry.getValue();
            String output = String.format("Điểm: , có: ", name, percent + "% \n");
            System.out.println(output);
        }
    }


    public void showStudentsListAlikeRank() {
        academicAbilityStudents(students);
    }

    public void academicAbilityStudents(ArrayList<Student> students) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhập vào học lực(viết hoa ký tự: ");
            Student.AcademicAbility rank = Student.AcademicAbility.valueOf(scanner.nextLine());
            ArrayList<Student> listStudentOfAcademicAbility = findStudentsByAcademicAbility(students, rank);
            if (listStudentOfAcademicAbility.size() > 0) {
                System.out.println("Danh sách sinh viên có học lực " + rank + ":");
                for (Student student : listStudentOfAcademicAbility) {
                    System.out.println(student.getFullName()
                            + "-" + student.getMSSV()
                            + "-" + student.getMediumScore()
                            + "-" + student.getAcademicAbility());
                }
            } else {
                System.out.println("không tìm thấy sinh viên nào lực " + rank);
            }
        } catch (Exception e) {
            System.out.println("Nhập sai mời nhập lại  ");
        }

    }

    public static ArrayList<Student> findStudentsByAcademicAbility(ArrayList<Student> students, Student.AcademicAbility rank) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getAcademicAbility().equals(rank)) {
                result.add(student);
            }
        }
        return result;
    }


    // Hàm để lưu trữ danh sách sinh viên vào tệp tin
    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Student student : students) {
                writer.write(student.getId()
                        + "," + student.getId()
                        + "," + student.getMSSV()
                        + "," + student.getFullName()
                        + "," + student.getBirthDay()
                        + "," + student.getAddress()
                        + "," + student.getHeight()
                        + "," + student.getWeight()
                        + "," + student.getSchool()
                        + "," + student.getCollegeStartYear()
                        + "," + student.getMediumScore()
                        + "," + student.getAcademicAbility());
                writer.newLine();
            }
            System.out.println("Danh sach sinh vien da duoc luu tru vao file " + fileName);
        } catch (IOException e) {
            System.out.println("Loi khi ghi vao file " + fileName + ": " + e.getMessage());
        }
    }

    public void saveStudentList() {
        saveToFile("students.txt");
    }
}
