package com.example.studentpractice.web;

import com.example.studentpractice.entities.Customer;
import com.example.studentpractice.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class CustomerControllerTest {


    Customer customer;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    View mockView;

    @InjectMocks
    CustomerController customerController;


    @Test
    void customer() {
    }

    @Test
    void delete() {
    }

    @Test
    void save() {
    }

    @BeforeEach
    void setup() throws ParseException {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("Jamas");

        String sDate1 = "2012/11/11";
        Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);
        customer.setTddate(date1);
        customer.setName("Jamas");
        customer.setSeatno("1A");
        MockitoAnnotations.openMocks(this);
        mockMvc = standaloneSetup(customerController).setSingleView(mockView).build();
    }


    //this is a working test case
    @Test
    public void findAll_ListView() throws Exception {
        List<Customer> list = new ArrayList<Customer>(); list.add(customer);
        list.add(customer);
        when (customerRepository.findAll()).thenReturn(list);
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(model().attribute( "listCustomers", list))
                .andExpect(view().name ( "customers"))
                .andExpect(model().attribute( "listCustomers", hasSize (2)));

        verify(customerRepository, Mockito.times(1)).findAll();
        verifyNoMoreInteractions (customerRepository);
    }

}