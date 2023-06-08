package com.example.demo;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Student {
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getApproved() {
		return approved;
	}
	public void setApproved(int approved) {
		this.approved = approved;
	}
	@Id
	private String id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String dob;
	@NotEmpty
	private String department;
	private int approved;
	@PrePersist
	public void ensureId() {
		this.id = Objects.nonNull(this.id) ? UUID.randomUUID().toString() : this.id;
	}
	
}
