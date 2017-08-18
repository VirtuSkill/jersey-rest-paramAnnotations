package org.virtuskill.jersey.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.virtuskill.jersey.domain.Student;

public class StudentDAO {

	private static HashMap<Long, Student> students = new HashMap<Long, Student>();

	static {
		Student jack = new Student(100, "Jack", "III", "Stanford");
		Student john = new Student(101, "John", "III", "Hartford");
		Student mary = new Student(102, "Mary", "III", "Hartford");
		Student sofia = new Student(103, "Sofia", "VI", "Stanford");
		Student mark = new Student(104, "Mark", "VI", "Hartford");
		Student sam = new Student(105, "Sam", "VI", "Hartford");
		Student mike = new Student(106, "Mike", "V", "Stanford");

		students.put(jack.getId(), jack);
		students.put(john.getId(), john);
		students.put(mary.getId(), mary);
		students.put(sofia.getId(), sofia);
		students.put(mark.getId(), mark);
		students.put(sam.getId(), sam);
		students.put(mike.getId(), mike);
	}

	public List<Student> getAllStudentDetails() {
		return new ArrayList<Student>(students.values());
	}

	public Student getStudent(long id) {
		Student obj = students.get(id);
		return obj;
	}

	public List<Student> getStudentBySchool(String school) {
		List<Student> listOfStudents = new ArrayList<Student>();
		for (long key : students.keySet()) {
			Student obj = students.get(key);
			if (obj.getSchool().equalsIgnoreCase(school)) {
				listOfStudents.add(obj);
			}
		}
		return listOfStudents;
	}

	public List<Student> getStudentBySection(String section) {
		List<Student> listOfStudents = new ArrayList<Student>();
		for (long key : students.keySet()) {
			Student obj = students.get(key);
			if (obj.getSection().equalsIgnoreCase(section)) {
				listOfStudents.add(obj);
			}
		}
		return listOfStudents;
	}
}
