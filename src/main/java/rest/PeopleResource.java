package rest;
/*
 * author paepke
 * version 1.0
 */

import com.google.gson.Gson;
import entity.Person;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/people")
public class PeopleResource {
    private static Gson gson = new Gson();
    private static List<Person> persons = Person.getInitialPersonList();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response get() {
        return Response.ok(gson.toJson(persons)).build();
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("name") String name) {
        Person personFound = null;
        for (Person person: persons) {
            if(person.getName().contains(name)) {
                personFound = person;
            }
        }
        return Response.ok(gson.toJson(personFound)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String json) {
        Person newPerson = gson.fromJson(json,Person.class);
        persons.add(newPerson);
        return Response.ok(newPerson).build();
    }

    @PUT
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("name") String name, String json) {
        Person updatedPerson = gson.fromJson(json, Person.class);
        for(Person person: persons) {
            if(person.getName().equals(name)) {
                person.setName(updatedPerson.getName());
            }
        }
        return Response.ok(updatedPerson).build();
    }

    @DELETE
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("name") String name) {
        Person found = null;
        for(Person person : persons) {
            if(person.getName().equals(name)) {
                found = person;
            }
        }
        if(found != null) persons.remove(found);
        return Response.ok(found).build();
    }
}
