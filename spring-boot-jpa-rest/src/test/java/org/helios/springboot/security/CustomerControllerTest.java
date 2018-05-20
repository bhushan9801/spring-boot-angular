package org.helios.springboot.security;

import org.helios.springboot.domain.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testAuditWith100Updates() {
        String email = "bbanner@usa.net";
//        TestRestTemplate testRestTemplate = restTemplate.withBasicAuth("bhushan", "password");
        TestRestTemplate testRestTemplate = restTemplate;
        for (int index = 0; index < 10; index++) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
            ResponseEntity<Customer> responseEntity = testRestTemplate
                    .exchange("/customers/byEmail/" + email, HttpMethod.GET, new HttpEntity<Void>(headers), Customer.class);
            Assert.assertNotNull(responseEntity);
            Assert.assertNotNull(responseEntity.getBody());

            Customer customer = responseEntity.getBody();
            String nextEmail = nextEmailValue(index);
            customer.setEmail(nextEmail);
            ResponseEntity<String> exchange = testRestTemplate
                    .exchange("/customers", HttpMethod.PUT, new ResponseEntity<>(customer, headers, HttpStatus.OK), String.class);
            System.out.println("Status Code : " + exchange.getStatusCode().toString());
            System.out.println("Response Body : " + exchange.getBody());
            email = nextEmail;
        }

    }

    private String nextEmailValue(int index) {
        return "bbanner" + Integer.toString(index) + "@usa.net";
    }
}
