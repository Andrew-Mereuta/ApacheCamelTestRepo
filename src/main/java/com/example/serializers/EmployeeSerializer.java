package com.example.serializers;

import com.example.entities.Employee;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;


public class EmployeeSerializer extends StdSerializer<Employee> {


    protected EmployeeSerializer(Class<Employee> t) {
        super(t);
    }

    protected EmployeeSerializer() {
        this(null);
    }

    @Override
    public void serialize(Employee employee, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField("name", employee.getName());
        jgen.writeStringField("position", employee.getPosition());
        jgen.writeStringField("date", employee.getDate().toString());
        jgen.writeEndObject();
    }
}
