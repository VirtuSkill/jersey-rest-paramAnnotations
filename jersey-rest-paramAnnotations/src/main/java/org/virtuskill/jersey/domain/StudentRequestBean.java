package org.virtuskill.jersey.domain;

import javax.ws.rs.QueryParam;

public class StudentRequestBean {
	@QueryParam(value = "school")
	String school;
	@QueryParam(value = "section")
	String section;

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

}
