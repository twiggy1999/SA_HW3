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

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * How to test the hypermedia-based {@link EmployeeController} with everything else mocked out.
 *
 * @author Greg Turnquist
 */
@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class EmployeeControllerTests {

	@Autowired private MockMvc mvc;

	@MockBean private StudentRepository repository;

	@Test
	public void getShouldFetchAHalDocument() throws Exception {

		given(repository.findAll()).willReturn( //
				Arrays.asList( //
						new Student("0","Frodo", "Female", "Paris","1999-03-11","ComputerScience","171860030"), //
						new Student("1","Bilbo", "Male", "London","1999-04-26","PE","171860031")));

		mvc.perform(get("/students").accept(MediaTypes.HAL_JSON_VALUE)) //
				.andDo(print()) //
				.andExpect(status().isOk()) //
				.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
				.andExpect(jsonPath("$._embedded.students[0].id", is(1)))
				.andExpect(jsonPath("$._embedded.students[0].name", is("Frodo")))
				.andExpect(jsonPath("$._embedded.students[0]._links.self.href", is("http://localhost/students/1")))
				.andExpect(jsonPath("$._embedded.students[0]._links.employees.href", is("http://localhost/students")))
				.andExpect(jsonPath("$._embedded.students[1].id", is(2)))
				.andExpect(jsonPath("$._embedded.students[1].name", is("Bilbo")))
				.andExpect(jsonPath("$._embedded.students[1]._links.self.href", is("http://localhost/students/2")))
				.andExpect(jsonPath("$._embedded.students[1]._links.students.href", is("http://localhost/students")))
				.andExpect(jsonPath("$._links.self.href", is("http://localhost/students"))) //
				.andReturn();
	}
}
