package com.techgen.client;

import java.util.List;

import com.techgen.entity.Guide;
import com.techgen.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class Client {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("student-guide");

			entityManager = entityManagerFactory.createEntityManager();

			EntityTransaction transaction = entityManager.getTransaction();

			// getGuide(entityManager, transaction);
			// getGuideNames(entityManager, transaction);
			// getGuideSalaryIs5000(entityManager, transaction);
			// getGuideNamesAndSalary(entityManager, transaction);
			// persistGuideStudent(entityManager, transaction);
			// getGuideUsingNamedParameter(entityManager, transaction);
			// getGuideUsingWildCard(entityManager, transaction);
			// getGuideUsingNativeQuery(entityManager, transaction);
			// getGuideUsingAggregateCount(entityManager, transaction);
			// persistStudent(entityManager, transaction);
			// persistGuide(entityManager, transaction);
			// getStudentInnerJoinGuide(entityManager, transaction);
			// getStudentLeftOuterJoinGuide(entityManager, transaction);
			// getStudentRightOuterJoinGuide(entityManager, transaction);
			// getGuideInnerJoinStudent(entityManager, transaction);
			// getStudentToGuide(entityManager, transaction);
			// getStudentsWhoHaveNoGuide(entityManager, transaction);
			// getGuidesWhoHaveNoStudents(entityManager, transaction);
			// getGuidesWhoHaveStudentNameStartWithA(entityManager, transaction);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
			if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
				entityManagerFactory.close();
			}
		}
	}

	private static void getGuideUsingAggregateCount(EntityManager entityManager, EntityTransaction transaction) {
		transaction.begin();
		Query query = entityManager.createQuery("select count(guide) from Guide as guide");
		Long count = (Long) query.getSingleResult();
		System.out.println(count);
		transaction.commit();
	}

	private static void getGuide(EntityManager entityManager, EntityTransaction transaction) {
		transaction.begin();
		Query query = entityManager.createQuery("select guide from Guide as guide");
		List<Guide> guides = query.getResultList();
		for (Guide guide : guides) {
			System.out.println(guide);
		}
		transaction.commit();
	}

	private static void getGuideNames(EntityManager entityManager, EntityTransaction transaction) {
		transaction.begin();
		Query query = entityManager.createQuery("select guide.name from Guide as guide");
		List<String> guideNames = query.getResultList();
		for (String name : guideNames) {
			System.out.println(name);
		}
		transaction.commit();
	}

	private static void getGuideSalaryIs5000(EntityManager entityManager, EntityTransaction transaction) {
		transaction.begin();
		Query query = entityManager.createQuery("select guide from Guide as guide where guide.salary = 5000");
		List<Guide> guides = query.getResultList();
		for (Guide guide : guides) {
			System.out.println(guide);
		}
		transaction.commit();
	}

	private static void getGuideNamesAndSalary(EntityManager entityManager, EntityTransaction transaction) {
		transaction.begin();
		Query query = entityManager.createQuery("select guide.name, guide.salary from Guide as guide");
		List<Object[]> guideNamesAndSalarys = query.getResultList();
		for (Object[] guide : guideNamesAndSalarys) {
			System.out.println(guide[0] + "  " + guide[1]);
		}
		transaction.commit();
	}

	private static void getGuideUsingNamedParameter(EntityManager entityManager, EntityTransaction transaction) {
		transaction.begin();
		Query query = entityManager.createQuery("select guide from Guide as guide where guide.name = :name");
		query.setParameter("name", "John");
		Guide guide = (Guide) query.getSingleResult();
		System.out.println(guide);
		transaction.commit();
	}

	private static void getGuideUsingWildCard(EntityManager entityManager, EntityTransaction transaction) {
		transaction.begin();
		Query query = entityManager.createQuery("select guide from Guide as guide where guide.name like '%o%'");
		List<Guide> guides = query.getResultList();
		for (Guide guide : guides) {
			System.out.println(guide);
		}
		transaction.commit();
	}

	private static void getGuideUsingNativeQuery(EntityManager entityManager, EntityTransaction transaction) {
		transaction.begin();
		Query query = entityManager.createNativeQuery("select * from guide", Guide.class);
		List<Guide> guides = query.getResultList();
		for (Guide guide : guides) {
			System.out.println(guide);
		}
		transaction.commit();
	}

	private static void persistGuideStudent(EntityManager entityManager, EntityTransaction transaction) {
		transaction.begin();

		Guide guide1 = new Guide("Mike Lawson", 1000, "2000MO");
		Guide guide2 = new Guide("Ian Lamb", 2000, "2000MI");

		Student student1 = new Student("2014A", "Amy Gill", guide2);
		Student student2 = new Student("2014J", "John Smith", guide2);
		Student student3 = new Student("2014B", "Bruce Lee", null);

		guide2.addStudent(student1);
		guide2.addStudent(student2);

		entityManager.persist(guide1);
		entityManager.persist(guide2);
		entityManager.persist(student3);

		transaction.commit();
	}

	private static void persistStudent(EntityManager entityManager, EntityTransaction transaction) {
		transaction.begin();
		Student student1 = new Student("IUIDU-99", "Vinay");
		entityManager.persist(student1);
		transaction.commit();
	}

	private static void persistGuide(EntityManager entityManager, EntityTransaction transaction) {
		transaction.begin();
		Guide guide1 = new Guide("Mike Lawson", 1000, "2000MO");
		entityManager.persist(guide1);
		transaction.commit();
	}

	private static void getStudentInnerJoinGuide(EntityManager entityManager, EntityTransaction transaction) {
		transaction.begin();
		Query query = entityManager.createQuery("select student from Student student join student.guide guide ");
		List<Student> students = query.getResultList();
		for (Student student : students) {
			System.out.println(student);
		}
		transaction.commit();
	}

	private static void getStudentLeftOuterJoinGuide(EntityManager entityManager, EntityTransaction transaction) {
		transaction.begin();
		Query query = entityManager.createQuery("select student from Student student left join student.guide guide ");
		List<Student> students = query.getResultList();
		for (Student student : students) {
			System.out.println(student);
		}
		transaction.commit();
	}

	private static void getStudentRightOuterJoinGuide(EntityManager entityManager, EntityTransaction transaction) {
		transaction.begin();
		Query query = entityManager.createQuery("select student from Student student right join student.guide guide ");
		List<Student> students = query.getResultList();
		for (Student student : students) {
			System.out.println(student);
		}
		transaction.commit();
	}

	private static void getGuideInnerJoinStudent(EntityManager entityManager, EntityTransaction transaction) {
		transaction.begin();
		Query query = entityManager.createQuery("select guide from Guide guide join fetch guide.students student ");
		List<Guide> guides = query.getResultList();
		for (Guide guide : guides) {
			System.out.println(guide);
		}
		transaction.commit();
	}

	private static void getStudentToGuide(EntityManager entityManager, EntityTransaction transaction) {
		transaction.begin();
		Query query = entityManager.createQuery("select student from Student student where student.guide.id = 1 ");
		List<Student> students = query.getResultList();
		for (Student student : students) {
			System.out.println(student);
		}
		transaction.commit();
	}

	private static void getStudentsWhoHaveNoGuide(EntityManager entityManager, EntityTransaction transaction) {
		transaction.begin();
		Query query = entityManager.createQuery(
				"select student.name, student.enrollmentId from Student student where student.guide.id = null ");
		List<Object[]> students = query.getResultList();
		for (Object[] student : students) {
			System.out.println("name " + student[0] + " enrollmentId " + student[1]);
		}
		transaction.commit();
	}

	private static void getGuidesWhoHaveNoStudents(EntityManager entityManager, EntityTransaction transaction) {
		transaction.begin();
		Query query = entityManager.createQuery(
				"select guide.name, guide.staffId, student.name from Student student right join student.guide guide where student.guide.id is null");
		List<Object[]> guides = query.getResultList();
		for (Object[] guide : guides) {
			System.out.println("name " + guide[0] + " enrollmentId " + guide[1] + " studentName " + guide[2]);
		}
		transaction.commit();
	}

	private static void getGuidesWhoHaveStudentNameStartWithA(EntityManager entityManager,
			EntityTransaction transaction) {
		transaction.begin();
		Query query = entityManager.createQuery(
				"select guide.name, guide.staffId, student.name from Guide guide join guide.students student where student.name like 'A%' ");
		List<Object[]> guides = query.getResultList();
		for (Object[] guide : guides) {
			System.out.println("name " + guide[0] + " enrollmentId " + guide[1] + " studentName " + guide[2]);
		}
		transaction.commit();
	}
}
