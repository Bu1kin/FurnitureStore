package com.example.UP.Controller;

import com.example.UP.API.APISupplierController;
import com.example.UP.Models.Supplier;
import com.example.UP.Securing.MessageResponse;
import com.example.UP.Services.SupplierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(APISupplierController.class)
class APISupplierControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private DataSource dataSource;
    @MockBean
    private SupplierService supplierService;

    @Test
    void givenSupplierObject_whenCreateSupplier_thenReturnSuccess() throws Exception{
        Supplier supplier = new Supplier(1L, "supplier@mail.ru", "УХ ДАНЯ", "qwePro", "+7(925)244-22-22");

        when(supplierService.createSupplier(supplier)).thenReturn(new MessageResponse("Создано"));

        mockMvc.perform(post("/api/supplier/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)
                .content(objectMapper.writeValueAsString(new MessageResponse("Создано"))))
                .andExpect(status().isCreated());
    }

    @Test
    void givenSupplierObject_whenCreateSupplier_thenReturn415() throws Exception{
        Supplier supplier = new Supplier(1L, null, null, null, null);

        when(supplierService.createSupplier(supplier)).thenReturn(new MessageResponse("Создано"));

        mockMvc.perform(post("/api/supplier/add")
                        .accept(MediaType.ALL)
                        .content(objectMapper.writeValueAsString(supplier)))
                .andExpect(status().isCreated());
    }

    @Test
    void givenSupplierObject_whenGetAllSuppliers_thenReturnSuccess() throws Exception{
        Supplier supplier1 = new Supplier(1L, "supplier1@mail.ru", "УХ ДАНЯ1", "qwePro1", "+7(111)111-11-11");
        Supplier supplier2 = new Supplier(2L, "supplier2@mail.ru", "УХ ДАНЯ2", "qwePro2", "+7(222)222-22-22");

        when(supplierService.getAllSuppliers()).thenReturn(Arrays.asList(supplier1, supplier2));

        mockMvc.perform(get("/api/supplier/suppliers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$[*].email", containsInAnyOrder("supplier1@mail.ru", "supplier2@mail.ru")))
                .andExpect(jsonPath("$[*].nameSupplier", containsInAnyOrder("УХ ДАНЯ1", "УХ ДАНЯ2")))
                .andExpect(jsonPath("$[*].address", containsInAnyOrder("qwePro1", "qwePro2")))
                .andExpect(jsonPath("$[*].phone", containsInAnyOrder("+7(111)111-11-11", "+7(222)222-22-22")));

    }

    @Test
    void givenSupplierObject_whenGetAllSuppliers_thenReturnNotMatched() throws Exception{
        Supplier supplier1 = new Supplier(1L, "supplier1@mail.ru", "УХ ДАНЯ1", "qwePro1", "+7(111)111-11-11");
        Supplier supplier2 = new Supplier(2L, "supplier2@mail.ru", "УХ ДАНЯ2", "qwePro2", "+7(222)222-22-22");

        when(supplierService.getAllSuppliers()).thenReturn(Arrays.asList(supplier1, supplier2));

        mockMvc.perform(get("/api/supplier/suppliers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$[*].email", containsInAnyOrder("supplier1@mail.ru", "supplier2@mail.ru")))
                .andExpect(jsonPath("$[*].nameSupplier", containsInAnyOrder("УХ ДАНЯ", "УХ ДАНЯ2")))
                .andExpect(jsonPath("$[*].address", containsInAnyOrder("qwePro1", "qwePro2")))
                .andExpect(jsonPath("$[*].phone", containsInAnyOrder("+7(111)111-11-11", "+7(222)222-22-22")));

    }

    @Test
    void givenSupplierObject_whenGetSupplierById_thenReturnSuccess() throws Exception{
        Supplier supplier1 = new Supplier(1L, "supplier1@mail.ru", "УХ ДАНЯ1", "qwePro1", "+7(111)111-11-11");

        when(supplierService.getASingleSupplier(anyLong())).thenReturn(Optional.of(supplier1).get());

        mockMvc.perform(get("/api/supplier/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.email", equalTo("supplier1@mail.ru")))
                .andExpect(jsonPath("$.nameSupplier", equalTo("УХ ДАНЯ1")))
                .andExpect(jsonPath("$.address", equalTo("qwePro1")))
                .andExpect(jsonPath("$.phone", equalTo("+7(111)111-11-11")));
    }

    @Test
    void givenSupplierObject_whenGetSupplierById_thenReturnNotFound() throws Exception{
        Supplier supplier1 = new Supplier(2L, "supplier1@mail.ru", "УХ ДАНЯ1", "qwePro1", "+7(111)111-11-11");

        when(supplierService.getASingleSupplier(1L)).thenReturn(Optional.of(supplier1).get());

        mockMvc.perform(get("/api/supplier/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.email", equalTo("supplier1@mail.ru")))
                .andExpect(jsonPath("$.nameSupplier", equalTo("УХ ДАНЯ1")))
                .andExpect(jsonPath("$.address", equalTo("qwePro1")))
                .andExpect(jsonPath("$.phone", equalTo("+7(111)111-11-11")));
    }

    @Test
    void givenSupplierObject_whenDeleteSupplier_thenReturnSuccess() throws Exception{
        willDoNothing().given(supplierService).deleteSupplier(1L);

        mockMvc.perform(delete("/api/supplier/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
