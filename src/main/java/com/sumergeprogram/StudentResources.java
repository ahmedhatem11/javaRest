package com.sumergeprogram;


import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RequestScoped
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Path("students")
public class StudentResources {

    private static final Logger LOGGER = Logger.getLogger(StudentResources.class.getName());

    private static StudentCollection students = new StudentCollection();

    @Context
    HttpServletRequest request;


    @GET
    public Response getAllStudents() {
        try {
            return Response.ok().
                    entity(students.getStudents()).
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e.getClass() + ": " + e.getMessage()).
                    build();
        }
    }

    @GET
    @Path("{id}")
    public Response getStudent(@PathParam("id") int id) {
        try {
            return Response.ok().
                    entity(students.getStudentbyID(id)).
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e.getClass() + ": " + e.getMessage()).
                    build();
        }
    }


    @GET
    @Path("name")
    public Response getStudentbyName(@QueryParam("name") String name) {
        try {
            System.out.println("Name is " + name);
            return Response.ok().
                    entity(students.getStudentsbyname(name)).
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e.getClass() + ": " + e.getMessage()).
                    build();
        }
    }

    @GET
    @Path("email")
    public Response getStudentbyEmail(@QueryParam("email") String email) {
        try {
            System.out.println("email is " + email);
            return Response.ok().
                    entity(students.getStudentsbyemail(email)).
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e.getClass() + ": " + e.getMessage()).
                    build();
        }
    }

    @GET
    @Path("address")
    public Response getStudentbyaddress(@QueryParam("address") String address) {
        try {
            System.out.println("address is " + address);
            return Response.ok().
                    entity(students.getStudentsbyaddress(address)).
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e.getClass() + ": " + e.getMessage()).
                    build();
        }
    }


    @POST
    public Response addStudent(Student student) {
        try {
            int id = students.addStudent(student.getName(),student.getEmail(),student.getAddress());
            URI uri = new URI(request.getRequestURI() + id);
            return Response.created(uri).entity(students).
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e.getClass() + ": " + e.getMessage()).
                    build();
        }
    }


    @PUT
    public Response editStudent(Student student) {
        try {
            if (students.getStudentbyID(student.getId()) == null)
                throw new IllegalArgumentException("Can't edit student since it does not exist in the database");

            if(student.getName() != null){
                students.updateStudentName(student.getId(), student.getName());
            }
            if(student.getEmail() != null){
                students.updateStudentemail(student.getId(), student.getEmail());
            }
            if(student.getAddress() != null){
                students.updateStudentaddress(student.getId(), student.getAddress());
            }
            return Response.ok().
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e.getClass() + ": " + e.getMessage()).
                    build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteStudent(@PathParam("id") int id) {
        try {
            students.deleteStudent(id);
            return Response.ok().
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e.getClass() + ": " + e.getMessage()).
                    build();
        }
    }
}
