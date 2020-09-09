package com.datasource.controller;

import com.datasource.entity.Product;
import com.datasource.service.ProductService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    @Path("/products")
    @Produces("application/json")
    public Response getProduct(){
        List<Product> prod = productService.getProduct();
        return Response.ok(prod).build();
    }

    @GET
    @Path("/product/{id}")
    @Produces("application/json")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(productService.getProductById(id)).build();
    }

    @GET
    @Path("/products/{sector}")
    @Produces("application/json")
    public Response find(@PathParam("sector") String sector) {
        return Response.ok(productService.getProductBySector(sector)).build();
    }

    @POST
    @Path("/add/products")
    @Produces("application/json")
    public Response addProduct(Product product){
        return Response.ok(productService.addProduct(product)).build();
    }


    @PUT
    @Path("/edit/products/{id}")
    @Produces("application/json")
    public Response EditProduct(@PathParam("id") Long id,Product product){
        return Response.ok(productService.updateProduct(id,product)).build();
    }

    @DELETE
    @Path("/delete/product/{id}")
    @Produces("application/json")
    public Response deleteProduct(@PathParam("id") Long id){
        return Response.ok(productService.deleteProductByid(id) ?
                "Producto eliminado exitosamente" :
                "Error liminando el producto")
                .build();
    }


}
