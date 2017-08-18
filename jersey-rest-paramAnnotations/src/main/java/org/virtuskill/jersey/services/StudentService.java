package org.virtuskill.jersey.services;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.virtuskill.jersey.domain.Student;
import org.virtuskill.jersey.domain.StudentRequestBean;
import org.virtuskill.jersey.repository.StudentDAO;

@Path("/student")
public class StudentService {

	StudentDAO studentDAO = new StudentDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// @QueryParam
	// http://localhost:8080/jersey-rest-paramAnnotations/webapi/student?section=VI
	// http://localhost:8080/jersey-rest-paramAnnotations/webapi/student?school=Stanford
	public List<Student> getStudentDetails(@QueryParam(value = "school") String school,
			@QueryParam(value = "section") String section) {
		if (!checkNullStringAndEmpty(school)) {
			return studentDAO.getStudentBySchool(school);
		}
		if (!checkNullStringAndEmpty(section)) {
			return studentDAO.getStudentBySection(section);
		}
		return studentDAO.getAllStudentDetails();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	// @PathParam
	// http://localhost:8080/jersey-rest-paramAnnotations/webapi/student/102
	public Student getStudent(@PathParam(value = "id") long id) {
		return studentDAO.getStudent(id);
	}

	@GET
	@Path("/matrix")
	@Produces(MediaType.APPLICATION_JSON)
	// @MatrixParam
	// http://localhost:8080/jersey-rest-paramAnnotations/webapi/student/matrix;school=Stanford
	public List<Student> getStudentDetailsByMatrixParam(@MatrixParam(value = "school") String school) {
		if (!checkNullStringAndEmpty(school)) {
			return studentDAO.getStudentBySchool(school);
		}
		return studentDAO.getAllStudentDetails();
	}

	@GET
	@Path("/header")
	// http://localhost:8080/jersey-rest-paramAnnotations/webapi/student/header
	// Open PostMan and type in a custom Header with name "simpleHeaderParam"
	public String getHeaderParam(@HeaderParam(value = "simpleHeaderParam") String headerVar) {
		return "Returning from getHeaderParam - " + headerVar;
	}

	@GET
	@Path("/cookie")
	// http://localhost:8080/jersey-rest-paramAnnotations/webapi/student/cookie
	// Open PostMan and type in a custom cookie with name "simpleCookieParam"
	public String getCookieParam(@CookieParam(value = "simpleCookieParam") String cookieVar) {
		return "Returning from getCookieParam - " + cookieVar;
	}

	@GET
	@Path("/context")
	// http://localhost:8080/jersey-rest-paramAnnotations/webapi/student/context
	public String getCotext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		String uriInfo_path = "Returning from getCotext - " + uriInfo.getAbsolutePath().toString();
		String temp = headers.getCookies().toString();
		return uriInfo_path + ", All Cookies - " + temp;
	}

	public static boolean checkNullStringAndEmpty(String strValue) {
		boolean bValue = true;
		if (strValue != null && (!(strValue.equalsIgnoreCase("null") || strValue.equalsIgnoreCase("")))) {
			bValue = false;
		}
		return bValue;
	}

	@GET
	@Path("/beanParam")
	@Produces(MediaType.APPLICATION_JSON)
	// @QueryParam
	// http://localhost:8080/jersey-rest-paramAnnotations/webapi/student/beanParam?section=VI
	// http://localhost:8080/jersey-rest-paramAnnotations/webapi/student/beanParam?school=Stanford
	public List<Student> getStudentDetailsByBeanParam(@BeanParam StudentRequestBean studentBeanObj) {
		if (!checkNullStringAndEmpty(studentBeanObj.getSchool())) {
			return studentDAO.getStudentBySchool(studentBeanObj.getSchool());
		}
		if (!checkNullStringAndEmpty(studentBeanObj.getSection())) {
			return studentDAO.getStudentBySection(studentBeanObj.getSection());
		}
		return studentDAO.getAllStudentDetails();
	}
}
