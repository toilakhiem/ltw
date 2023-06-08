package com.example.demo;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Student {
  @Id
  private String id;
  @NotEmpty(message = "Name is not empty")
  private String name;
  @NotEmpty(message = "dob is not empty")
  private String dob;
  @NotEmpty(message = "department is not empty")
  private String department;
  private int approved;

  @PrePersist
  public void ensureId() {
    this.id = Objects.isNull(this.id) ? UUID.randomUUID().toString() : this.id;
  }

}
