/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.hateoas.examples;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Domain object representing a company employee. Project Lombok keeps actual code at a minimum. {@code @Data} -
 * Generates getters, setters, toString, hash, and equals functions {@code @Entity} - JPA annotation to flag this class
 * for DB persistence {@code @NoArgsConstructor} - Create a constructor with no args to support JPA
 * {@code @AllArgsConstructor} - Create a constructor with all args to support testing
 * {@code @JsonIgnoreProperties(ignoreUnknow=true)} When converting JSON to Java, ignore any unrecognized attributes.
 * This is critical for REST because it encourages adding new fields in later versions that won't break. It also allows
 * things like _links to be ignore as well, meaning HAL documents can be fetched and later posted to the server without
 * adjustment.
 *
 * @author Greg Turnquist
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
class Student {

	@Id 
	@GeneratedValue private Long id;
	private String name;
	private String sex;
	private String birthDate;
	private String birthPlace;
	private String department;
	private String sno;

	/**
	 * Useful constructor when id is not yet known.
	 * 
	 * 
	 * @param name
	 * @param sex
	 * @param birthDate
	 * @param birthPlace
	 * @param department
	 * @param sno
	 * 
	 * 
	 */
	Student(String name, String sex, String birthDate,String birthPlace,String department,String sno) {

		
		this.name = name;
		this.sex = sex;
		this.birthDate = birthDate;
		this.birthPlace=birthPlace;
		this.department=department;
		this.sno=sno;
	}
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

