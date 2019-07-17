package com.sumergeprogram;

import java.util.*;

public class StudentCollection {

    private static ArrayList<Student> students;
    int count = 0;

    public StudentCollection() {
        students = new ArrayList<Student>();
        addStudent("Ahmed", "ahmed@gmail.com", "El-Shorouk");
        addStudent("Hatem", "hatem@gmail.com", "El-Shorouk");
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public int addStudent(String name, String email, String address){
        count++;
        students.add(new Student(count,name,email, address));
        return count;
    }

    public void deleteStudent(int id){
        for(int i=0;i<students.size();i++) {
            if (students.get(i).getId() == id) {
                students.remove(students.get(i));
                break;
            }
        }
    }

    public String getStudentbyID(int id){
        for(int i=0;i<students.size();i++) {
            if (students.get(i).getId() == id) {
                return students.get(i).toString();
            }
        }
        return null;
    }

    public String getStudentsbyname(String name){

        String foundStudents = "";
        for(int i=0;i<students.size();i++) {
            if (students.get(i).getName().equals(name)) {
                foundStudents += students.get(i).toString() + "\n";
            }
        }
        return foundStudents;
    }

    public String getStudentsbyemail(String email){

        String foundStudents = "";

        for(int i=0;i<students.size();i++) {
            if (students.get(i).getEmail().equals(email)) {
                foundStudents += students.get(i).toString() + "\n";
            }
        }
        return foundStudents;
    }

    public String getStudentsbyaddress(String address){

        String foundStudents = "";

        for(int i=0;i<students.size();i++) {
            if (students.get(i).getAddress().equals(address)) {
                foundStudents += students.get(i).toString() + "\n";
            }
        }
        return foundStudents;
    }

    public void updateStudentName(int id, String name){
        for(int i=0;i<students.size();i++) {
            if (students.get(i).getId() == id) {
                students.get(i).setName(name);
            }
        }
    }

    public void updateStudentemail(int id, String email){
        for(int i=0;i<students.size();i++) {
            if (students.get(i).getId() == id) {
                students.get(i).setEmail(email);
            }
        }
    }

    public void updateStudentaddress(int id, String address){
        for(int i=0;i<students.size();i++) {
            if (students.get(i).getId() == id) {
                students.get(i).setAddress(address);
            }
        }
    }

//    public static void main(String[] args) {
//        StudentCollection s = new StudentCollection();
//        ArrayList a = s.getStudents();
//        for(int i=0;i<a.size();i++) {
//            System.out.println(a.get(i).toString());
//        }
//
//
//    }

}
