package DAO;

import Model.Students;
import MyConnection.MyConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class studentsDAO {
    public List<Students> getAll() {
        List<Students> studentsList = new ArrayList<>();
        final String sql = "SELECT * FROM `students`";
        try {
            Connection connection = MyConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Students students = new Students();
                students.setId(resultSet.getString("id"));
                students.setFull_name(resultSet.getString("full_name"));
                students.setGender(resultSet.getInt("gender"));
                students.setBirthday(resultSet.getString("birthday"));
                students.setAddress(resultSet.getString("address"));
                students.setPhone(resultSet.getString("phone"));
                students.setEmail(resultSet.getString("email"));
                students.setGpa(resultSet.getDouble("gpa"));
                studentsList.add(students);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentsList;
    }

    public Students getById(String id) {
        final String sql = String.format("SELECT * From `students` WHERE id = '%s' ", id);
        Students students = null;
        try {
            Connection connection = MyConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                students = new Students();
                students.setId(resultSet.getString("id"));
                students.setFull_name(resultSet.getString("full_name"));
                students.setGender(resultSet.getInt("gender"));
                students.setBirthday(resultSet.getString("birthday"));
                students.setAddress(resultSet.getString("address"));
                students.setPhone(resultSet.getString("phone"));
                students.setEmail(resultSet.getString("email"));
                students.setGpa(resultSet.getDouble("gpa"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public void insert(Students students) {
        final String sql = String.format("INSERT INTO `students` VALUES ('%s', '%s', '%d', '%s', '%s', '%s', '%s', '%f')",
                students.getId(), students.getFull_name(), students.getGender(), students.getBirthday(),
                students.getAddress(), students.getPhone(), students.getEmail(), students.getGpa());
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Thêm thất bại");
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        Students stdCheck = getById(id);
        if (stdCheck == null) {
            System.out.println("Sinh viên không tồn tại");
            return;
        }
        final String sql =String.format( "DELETE FROM `students` WHERE  `id` like '%s' ", id );
        try {
            Connection connection = MyConnection.getConnection();
            Statement statement = connection.createStatement();
            long rs = statement.executeUpdate(sql);
            if (rs == 0) {
                System.out.println("Xóa thất bại");
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Students students, String id) throws SQLException {
        Students stdCheck = getById(id);
        if (stdCheck == null) {
            System.out.println("Không có người dùng");
            return;
        }
        final String sql = String.format("UPDATE `students` SET `full_name`='%s',`gender`='%d',`birthday`='%s',`address`='%s',`phone`='%s', `email`='%s',`gpa`='%f' WHERE `id` = '%s'",
                students.getFull_name(), students.getGender(), students.getBirthday(), students.getAddress(), students.getPhone(), students.getEmail(), students.getGpa(), id);
        try {
            Connection connection = MyConnection.getConnection();
            Statement statement = connection.createStatement();
            long rs = statement.executeUpdate(sql);
            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Students getByName(String name) {
        Students s = null;
        final String sql = String.format("Select * from `students` where full_name like '%s'", name);
        try {
            Connection connection = MyConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                s = new Students();
                s.setId(resultSet.getString("id"));
                s.setFull_name(resultSet.getString("full_name"));
                s.setGender(resultSet.getInt("gender"));
                s.setBirthday(resultSet.getString("birthday"));
                s.setAddress(resultSet.getString("address"));
                s.setPhone(resultSet.getString("phone"));
                s.setEmail(resultSet.getString("email"));
                s.setGpa(resultSet.getDouble("gpa"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
    public List<Students> getStudentSapXepName() {
        List<Students> studentsList = new ArrayList<>();
        try {
            Connection conn = MyConnection.getConnection();
            final String sql = "SELECT * FROM bkacad.students order by full_name asc";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Students students = new Students();
                students.setId(rs.getString("id"));
                students.setFull_name(rs.getString("full_name"));
                students.setGender(rs.getInt("gender"));
                students.setBirthday(rs.getString("ngay_sinh"));
                students.setAddress(rs.getString("dia_chi"));
                students.setPhone(rs.getString("phone_number"));
                students.setEmail(rs.getString("email"));
                students.setGpa(rs.getDouble("diem_gpa"));
                studentsList.add(students);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentsList;
    }
    public Students getStudentByID(String id) {
        Students students = null;
        final String sql = String.format("Select * from `students` where full_name like '%s'", id);
        try {
            Connection connection = MyConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                students = new Students();
                students.setId(resultSet.getString("id"));
                students.setFull_name(resultSet.getString("full_name"));
                students.setGender(resultSet.getInt("gender"));
                students.setBirthday(resultSet.getString("ngay_sinh"));
                students.setAddress(resultSet.getString("dia_chi"));
                students.setPhone(resultSet.getString("phone_number"));
                students.setEmail(resultSet.getString("email"));
                students.setGpa(resultSet.getDouble("diem_gpa"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}