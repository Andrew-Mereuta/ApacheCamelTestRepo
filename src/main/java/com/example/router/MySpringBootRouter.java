package com.example.router;

import com.example.entities.Employee;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class MySpringBootRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .port(8080)
                .host("localhost")
                .bindingMode(RestBindingMode.auto);

        rest()
                .get("/employees")
                    .consumes("application/json")
                    .produces("application/json")
                    .to("bean:employeeBean?method=getEmployees")
                .post("/employees")
                    .type(Employee.class)
                    .consumes("application/json")
                    .produces("application/json")
                    .to("bean:employeeBean?method=createEmployee")
                .get("/employees/{id}")
                    .consumes("application/json")
                    .produces("application/json")
                    .to("bean:employeeBean?method=getEmployee(${header.id})")
                .patch("/employees/{id}")
                    .consumes("application/json")
                    .produces("application/json")
                    .to("bean:employeeBean?method=patchEmployee(${header.id},${header.name})")
                .delete("/employees/{id}")
                    .consumes("application/json")
                    .produces("application/json")
                    .to("bean:employeeBean?method=deleteEmployee(${header.id})");
    }

}
