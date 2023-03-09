import service.Service;
import java.text.ParseException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws ParseException {
        Service service = new Service();
        int choice = -1;

        // Đăng ký một ShutdownHook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Chương trình đã kết thúc");
            // Gọi hàm khác ở đây
            service.saveStudentList();
        }));
        // Điều kiện để kết thúc chương trình
        boolean isRunning = true;

        do {
            System.out.println("\n\n==================PHẦN MỀM QUẢN LÝ SINH VIÊN==================\n\n");
            System.out.println("1. Nhập thông tin sinh viên mới");
            System.out.println("2. In ra danh sách sinh viên");
            System.out.println("3. Tìm kiếm sinh viên theo MSSV");
            System.out.println("4. Cập nhật thông tin sinh viên");
            System.out.println("5. Xoá sinh viên ");
            System.out.println("6. Hiển thị % học lực từ cao đến thấp của các sinh viên");
            System.out.println("7. Hiển thị % điểm trung bình của các sinh viên trong danh sách");
            System.out.println("8. Hiển thị danh sách sinh viên cùng học lực");
            System.out.println("9. Lưu lại danh sách sinh viên trong file");
            System.out.println("0. Exit");
            System.out.println("------------------------------------------------------------------------------");
            System.out.print(" Hãy chọn thao tác thực hiện: ");
            do {
                choice = new Scanner(System.in).nextInt();
                if (choice < 0 || choice > 9) System.out.println("Thao tác không hợp lệ. Xin mời chọ lại thao tác ");
            } while (choice < 0 || choice > 9);
            switch (choice) {
                case 1:
                    service.addStudent();
                    break;
                case 2:
                    service.showStudent();
                    break;
                case 3:
                    service.searchStudentId();
                    break;
                case 4:
                    service.updateInformationStudent();
                    break;
                case 5:
                    service.deleteStudent();
                    break;
                case 6:
                    service.showStudentsByPercentage();
                    break;
                case 7:
                    service.showErcentagePointAverageOfStudents();
                    break;
                case 8:
                    service.showStudentsListAlikeRank();
                    break;
                case 9:
                    service.saveStudentList();
                    break;
                case 0:
                    System.out.println("Exit");
                    break;
            }
        } while (choice != 0);
    }
}
