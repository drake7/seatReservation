package com.example.studentpractice.web;

import com.example.studentpractice.entities.Customer;
import com.example.studentpractice.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@SessionAttributes({"a", "e"})
@Controller
@AllArgsConstructor
public class CustomerController {

    private CustomerRepository customerRepository;

    @GetMapping(path = "/index")
    public String customer(Model model) {

        Customer customerSolo=new Customer();
        model.addAttribute("customer",customerSolo);


        List<Customer> customers;
            customers = customerRepository.findAll();
            System.out.println("Customer Size "+customers.size());
        model.addAttribute("listCustomers", customers);
        return "customers";
    }



    @GetMapping("/delete")
    public String delete(Long id) {
        customerRepository.deleteById(id);
        return "redirect:/index";
    }


    @PostMapping(path = "/save")
    public String save(Model model, Customer customer, BindingResult
            bindingResult, ModelMap mm, HttpSession session) {

        System.out.print(model.toString());
        System.out.print("Seat code is "+ model.getAttribute("seatno"));
        if (bindingResult.hasErrors()) {
            return "template1";
        } else {
            customerRepository.save(customer);
            return "redirect:index";

    }
    }
}
