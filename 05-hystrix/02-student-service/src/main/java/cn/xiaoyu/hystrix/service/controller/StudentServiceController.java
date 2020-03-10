package cn.xiaoyu.hystrix.service.controller;

import cn.xiaoyu.hystrix.service.domain.Student;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class StudentServiceController {
    private static Map<String, List<Student>> schoolDB;

    static {
        schoolDB = new HashMap<>();
        List<Student> students = new ArrayList<>();
        students.add(new Student("BoBo", "Class I"));
        students.add(new Student("Jack", "Class II"));
        schoolDB.put("abcschool", students);

        students = new ArrayList<>();
        students.add(new Student("Daniel", "Class III"));
        students.add(new Student("William", "Class V"));
        schoolDB.put("xyzschool", students);
    }

    @GetMapping()
    @RequestMapping(value = "/getStudentBySchool/{schoolName}", method = RequestMethod.GET)
    public List<Student> getStudents(@PathVariable String schoolName) {
        System.out.println("Getting student for " + schoolName);

        randomlyRunLong();

        List<Student> studentList = schoolDB.get(schoolName);
        if (studentList == null) {
            studentList = new ArrayList<>();
            Student student = new Student("Not Found", "N/A");
            studentList.add(student);
        }

        return studentList;
    }

    private void randomlyRunLong() {
        Random rand = new Random();
        int randomNum = rand.nextInt(3) + 1;
        if (randomNum == 3) sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
