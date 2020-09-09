package com.datasource;

import com.datasource.entity.Product;
import com.datasource.service.ProductService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class GreetingResourceTest {

    @Inject
    ProductService service;

    @Test
    public void testGetProductEndpoint() {

        long catidad = Arrays.stream(given()
                .when().get("/init/products")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Product[].class))
                .count();

        assertEquals(4, catidad);

    }

    @Test
    public void testGetProductEndpointV2() {

        List<Map<String, Object>> products = get("/init/products").as(new TypeRef<List<Map<String, Object>>>() {});

        assertThat(products, hasSize(4));
        assertThat(products.get(0).get("name"), equalTo("samsung galaxy s20"));
        assertThat(products.get(0).get("sector"), equalTo("Tecnologia"));
        assertThat(products.get(0).get("brand"), equalTo("Samsung"));
        assertThat(products.get(0).get("price"), equalTo(120.0));

        assertThat(products.get(1).get("name"), equalTo("Iphone 11"));
        assertThat(products.get(1).get("sector"), equalTo("Tecnologia"));
        assertThat(products.get(1).get("brand"), equalTo("Iphone"));
        assertThat(products.get(1).get("price"), equalTo(130.0));

        assertThat(products.get(2).get("name"), equalTo("Xiaomi mi band 5"));
        assertThat(products.get(2).get("sector"), equalTo("Tecnologia"));
        assertThat(products.get(2).get("brand"), equalTo("Xiaomi"));
        assertThat(products.get(2).get("price"), equalTo(140.0));

        assertThat(products.get(3).get("name"), equalTo("Huawei P30"));
        assertThat(products.get(3).get("sector"), equalTo("Tecnologia"));
        assertThat(products.get(3).get("brand"), equalTo("Huawei"));
        assertThat(products.get(3).get("price"), equalTo(150.0));

    }

    @Test
    public void testGetProductEndpointvResponseTime() {

        given()
              .when().get("/init/products")
              .then().time(lessThan(1000L));


}

    @Test
    public void testFindByIdEndpoint() {

        given()
                .pathParam("id", 4)
                .when().get("/init/product/{id}")
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo("Xiaomi mi band 5"))
                .body("sector", equalTo("Tecnologia"))
                .body("brand", equalTo("Xiaomi"))
                .body("price", equalTo(140.0f));

    }

}