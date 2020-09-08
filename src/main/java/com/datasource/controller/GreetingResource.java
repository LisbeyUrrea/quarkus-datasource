package com.datasource.controller;

import com.datasource.entity.Product;
import com.datasource.service.ProductService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/init")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @Inject
    private ProductService productService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello, this is begin with Quarkus!!!";
    }

    @POST
    @Path("/add/products")
    @Produces("application/json")
    public Response addProduct(Product product){
        return Response.ok(productService.addProduct(product)).build();
    }

    @GET
    @Path("/products")
    @Produces("application/json")
    public Response getProduct(){
        List<Product> prod = productService.getProduct();
        return Response.ok(prod).build();
    }

    @GET
    @Path("/{sector}")
    public Response find(@PathParam("sector") String sector) {
        return productService.getProductBySector(sector)
                .map(u -> Response.ok(u))
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }
}
