import DAO.studentsDAO;
import Model.Students;
import com.mysql.cj.exceptions.StreamingNotifiable;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static studentsDAO sDAO = new studentsDAO();
    public static void Menu(){
        System.out.println("        === QUẢN LÝ THÔNG TIN SINH VIÊN ===        ");
        System.out.println("    1. IN DANH SÁCH SINH VIÊN THEO BẢNG"            );
        System.out.println("    2. NHẬP 1 SINH VIÊN MỚI"                        );
        System.out.println("    3. XÓA 1 SINH VIÊN THEO MÃ"                     );
        System.out.println("    4. CẬP NHẬT THÔNG TIN SINH VIÊN"                );
        System.out.println("    5. TÌM 1 SINH VIÊN THEO HỌ TÊN HOẶC MÃ"         );
        System.out.println("    6. SẮP XẾP SINH VIÊN THEO ĐIỂM SỐ TĂNG DẦN"     );
        System.out.println("    7. IN RA TẤT CẢ SINH VIÊN NỮN CÓ GPA > 2.5"     );
        System.out.println("    8. SẮP XẾP SINH VIÊN THEO HỌ TÊN"               );
        System.out.println("===================================================");
    }

    public static void option1(){
        List<Students> studentsList = sDAO.getAll();
        System.out.printf("%-20s %-20s %-20s %-20s", "MSV", "Tên sinh viên", "Giới tính", "Địa chỉ");
        System.out.println();
        for (int i = 0; i < studentsList.size(); i++) {
            Students students = studentsList.get(i);
            System.out.printf("%-20s %-20s %-20d %-20s\n", students.getId(), students.getFull_name(), students.getGender(), students.getAddress());
        }
    }

    public static void option2(Scanner in){
        Students students = new Students();
        System.out.println("Nhập ID:");
        String id = in.nextLine();
        students.setId(id);

        System.out.println("Nhập tên:");
        String ten = in.nextLine();
        students.setFull_name(ten);

        System.out.println("Nhập giới tính:");
        int gender = Integer.parseInt(in.nextLine());
        students.setGender(gender);

        System.out.println("Nhập sinh nhật:");
        String bd = in.nextLine();
        students.setBirthday(bd);

        System.out.println("Nhập địa chỉ:");
        String add = in.nextLine();
        students.setAddress(add);

        System.out.println("Nhập số đt:");
        String sdt = in.nextLine();
        students.setPhone(sdt);

        System.out.println("Nhập email:");
        String email = in.nextLine();
        students.setEmail(email);

        System.out.println("Nhập gpa:");
        double gpa = Double.parseDouble(in.nextLine());
        students.setGpa(gpa);

        sDAO.insert(students);
        System.out.println("Done");
    }
    public static void option3(Scanner in){
        System.out.println("Nhập ID muốn xóa:");
        String id_dlt = in.nextLine();
        sDAO.delete(id_dlt);
        System.out.println("Done");
    }

    public static void option4(Scanner in) throws SQLException {
        Students students = new Students();
        System.out.println("Nhập id muốn cập nhật:");
        String id_ud = in.nextLine();

        System.out.println("Nhập tên muốn cập nhật:");
        String newName = in.nextLine();
        students.setFull_name(newName);

        System.out.println("Nhập giới tính muốn cập nhật:");
        int newGender = Integer.parseInt(in.nextLine());
        students.setGender(newGender);

        System.out.println("Nhập sinh nhật muốn cập nhật:");
        String newbd = in.nextLine();
        students.setBirthday(newbd);

        System.out.println("Nhập dịa chỉ muốn cập nhật:");
        String newad = in.nextLine();
        students.setAddress(newad);

        System.out.println("Nhập sdt muốn cập nhật:");
        String newPhone = in.nextLine();
        students.setPhone(newPhone);

        System.out.println("Nhập email muốn cập nhật:");
        String newEmail = in.nextLine();
        students.setEmail(newEmail);

        System.out.println("Nhập GPA muốn cập nhật:");
        double newGPA = Double.parseDouble(in.nextLine());
        students.setGpa(newGPA);

        sDAO.update(students, id_ud);
        System.out.println("Done");
    }
    public static void option5(Scanner in){
        System.out.println("Nhập tên");
        String ten = in.nextLine();
        System.out.println(sDAO.getByName(ten));
    }

    private static void option8() {
        List<Students> studentsList3 = sDAO.getStudentSapXepName();
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s", "STT","Mã sinh viên", "Họ tên", "Giới tính", "Địa chỉ", "Điểm GPA");
        System.out.println();
        for (int i = 0; i < studentsList3.size(); i++) {
            Students stud = studentsList3.get(i);
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n", (i+1), stud.getId(), stud.getFull_name(), stud.getGender(), stud.getAddress(),stud.getGpa());
        };
    }

    public static void main(String[] args) throws SQLException {
        int option = -1;
        Scanner in = new Scanner(System.in);
        Students students = new Students();
        do {
            Menu();
            System.out.print("Nhập lựa chọn: ");
            try {
                option = Integer.parseInt(in.nextLine());
            }catch (NumberFormatException e) {
                System.out.println("Nhâp sai định dạng");
            }
            if (option < 1 || option > 8) {
                System.out.println("Vui lòng nhập lại");
                continue;
            }
            switch (option){
                case 1:
                    option1();
                    break;
                case 2:
                    option2(in);
                    break;
                case 3:
                    option3(in);
                    break;
                case 4:
                    option4(in);
                    break;
                case 5:
                    option5(in);
                    break;
                case 6:
                    option8();
                    break;
                case 7:
                case 8:
            }
        }while (option != 0);
    }
}
