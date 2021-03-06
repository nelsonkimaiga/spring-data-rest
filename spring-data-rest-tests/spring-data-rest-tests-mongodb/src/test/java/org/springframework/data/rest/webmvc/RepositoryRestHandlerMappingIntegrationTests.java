/*
 * Copyright 2015-2019 the original author or authors.
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
package org.springframework.data.rest.webmvc;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.tests.AbstractControllerIntegrationTests;
import org.springframework.data.rest.tests.mongodb.MongoDbRepositoryConfig;
import org.springframework.data.rest.webmvc.support.DelegatingHandlerMapping;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;

/**
 * Integration tests for {@link BasePathAwareHandlerMapping}.
 *
 * @author Oliver Gierke
 * @soundtrack Elephants Crossing - Echo (Irrelephant)
 */
@ContextConfiguration(classes = MongoDbRepositoryConfig.class)
public class RepositoryRestHandlerMappingIntegrationTests extends AbstractControllerIntegrationTests {

	@Autowired DelegatingHandlerMapping mapping;

	@Test // DATAREST-617
	public void usesMethodsWithoutProducesClauseForGeneralJsonRequests() throws Exception {

		MockHttpServletRequest mockRequest = new MockHttpServletRequest("GET", "/users");
		mockRequest.addHeader("Accept", "application/*+json");

		HandlerExecutionChain chain = mapping.getHandler(mockRequest);

		assertThat(chain).isNotNull();

		Object handler = chain.getHandler();
		assertThat(handler).isInstanceOf(HandlerMethod.class);

		HandlerMethod method = (HandlerMethod) handler;
		assertThat(method.getMethod().getDeclaringClass()).isAssignableFrom(RepositoryEntityController.class);
		assertThat(method.getMethod().getName()).isEqualTo("getCollectionResource");
	}
}
