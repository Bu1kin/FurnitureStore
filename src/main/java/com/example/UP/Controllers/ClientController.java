package com.example.UP.Controllers;

import com.example.UP.Models.Client;
import com.example.UP.Repositories.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
//@PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER', 'DELIVERY')")
public class ClientController {
    @Autowired
    ClientRepo clientRepo;

    @GetMapping("/client")
    public String main(Model model){
        Iterable<Client> listClient = clientRepo.findAll();
        model.addAttribute("listClient", listClient);
        return "/Client/index";
    }

    @PostMapping("/client/add")
    public String clientAdd(@RequestParam String email,
                            @RequestParam String surname,
                            @RequestParam String name,
                            @RequestParam String middleName,
                            @RequestParam String address,
                            @RequestParam String phone,
                            Model model){

        Client client = new Client(email, surname, name, middleName, address, phone);
        clientRepo.save(client);
        return ("redirect:/client");
    }

    @GetMapping("/client/filter-direct")
    public String clientFilterDirect(@RequestParam String searchName,
                                     Model model){
        List<Client> client = clientRepo.findBySurname(searchName);
        model.addAttribute("client", client);
        return "/Client/filter";
    }

    @GetMapping("/client/filter-contains")
    public String clientFilterContains(@RequestParam String searchName,
                                       Model model){
        List<Client> client = clientRepo.findBySurnameContaining(searchName);
        model.addAttribute("client", client);
        return "/Client/filter";
    }

    @GetMapping("/client/details/{id}")
    public String clientDetails(Model model, @PathVariable Long id) {
        Client client = clientRepo.findById(id).orElseThrow();
        model.addAttribute("client", client);
        return ("/Client/details");
    }

    @GetMapping("/client/del/{id}")
    public String clientDelete(@PathVariable Long id) {
        clientRepo.deleteById(id);
        return("redirect:/client");
    }

//    @GetMapping("/client/edit/{id}")
//    public String clientEdit(Model model, @PathVariable Long id, Client client_) {
//        Client client = clientRepo.findById(id).orElseThrow();
//        model.addAttribute("client", client);
//        return("/Client/edit");
//    }

    @PostMapping("/client/edit/{id}")
    public String clientEdit(Client client) {
        clientRepo.save(client);
        return("redirect:/client");
    }
}
