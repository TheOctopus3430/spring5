/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.test.context.junit.jupiter.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.SpringJUnitJupiterTestSuite;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * Integration tests which demonstrate use of the Spring MVC Test Framework and
 * the Spring TestContext Framework with JUnit Jupiter and the
 * {@link SpringExtension} (via a custom
 * {@link SpringJUnitWebConfig @SpringJUnitWebConfig} composed annotation).
 *
 * <p>Note how the {@link #springMvcTest(WebApplicationContext)} test method
 * has the {@link WebApplicationContext} injected as a method parameter.
 * This allows the {@link MockMvc} instance to be configured local to the
 * test method without any fields in the test class.
 *
 * <p>To run these tests in an IDE that does not have built-in support for the JUnit
 * Platform, simply run {@link SpringJUnitJupiterTestSuite} as a JUnit 4 test.
 *
 * @author Sam Brannen
 * @since 5.0
 * @see SpringExtension
 * @see SpringJUnitWebConfig
 * @see org.springframework.test.context.junit.jupiter.web.MultipleWebRequestsSpringExtensionTestCase
 * @see org.springframework.test.context.junit.jupiter.SpringExtensionTests
 * @see org.springframework.test.context.junit.jupiter.ComposedSpringExtensionTests
 */
@SpringJUnitWebConfig(WebConfig.class)
@DisplayName("Web SpringExtension Tests")
class WebSpringExtensionTestCase {

	@Test
	void springMvcTest(WebApplicationContext wac) throws Exception {
		webAppContextSetup(wac).build()
			.perform(get("/person/42").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", is("Dilbert")));
	}

}
