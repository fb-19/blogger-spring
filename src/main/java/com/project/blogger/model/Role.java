package com.project.blogger.model;

import com.project.blogger.model.enums.Roles;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role {

  @Id
  @SequenceGenerator(name =  "roles_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "roles_id_seq", strategy = GenerationType.SEQUENCE)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private Roles name;

}
