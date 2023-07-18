package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.OrderDto;
import com.example.springdatabasicdemo.dtos.ClientDto;
import com.example.springdatabasicdemo.dtos.ClientFindClientByIdDto;
import com.example.springdatabasicdemo.dtos.ClientDeleteClientByIdDto;
import com.example.springdatabasicdemo.dtos.ClientDeleteClientByNameDto;
import com.example.springdatabasicdemo.dtos.ClientDeleteClientBySurnameDto;
import com.example.springdatabasicdemo.dtos.ClientChangeClientByNameDto;
import com.example.springdatabasicdemo.dtos.ClientChangeClientBySurnameDto;
import com.example.springdatabasicdemo.dtos.ClientGetAllClientOrdersByIdDto;
import com.example.springdatabasicdemo.dtos.ClientGetAllClientOrdersByOrdersDto;
import com.example.springdatabasicdemo.services.ClientService;
import com.example.springdatabasicdemo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/clients")
    Iterable<ClientDto> all() {
        return clientService.getAllClients();
    }

    @PostMapping("/reg/client")
    ClientDto newClient(@RequestBody ClientDto newClient) {  return clientService.registerNewClient(newClient); } //Test this

    @GetMapping("/find/client")
    ClientDto one(@RequestBody ClientFindClientByIdDto clientFind) throws Throwable {
        return (ClientDto) clientService.findClient(clientFind.getId()).orElseThrow(() -> new ClientNotFoundException(clientFind.getId()));
    }

    @DeleteMapping("/delete/client/id")
    void deleteClientById(@RequestBody Long id) {
        clientService.deleteClient(id);
    }

    @DeleteMapping("/delete/client/name")
    void deleteClientByName(@RequestBody ClientDeleteClientByNameDto clientDelName) {
        ClientDto client = new ClientDto();
        client.setClient_name(clientDelName.getName());
        clientService.deleteClientByName(client);
    }

    @DeleteMapping("/delete/client/surname")
    void deleteClientBySurname(@RequestBody ClientDeleteClientBySurnameDto clientDelSurn) {
        ClientDto client = new ClientDto();
        client.setClient_surname(clientDelSurn.getSurame());
        clientService.deleteClientBySurname(client);
    }

//    @GetMapping("/clients/by-order/{orderId}")
//    public List<ClientDto> findClientByOrder(@PathVariable Integer orderId) {
//        OrderDto order = new OrderDto();
//        order.setId(orderId);
//        return clientService.findClientByOrder(order, orderId);
//    }

    @PutMapping("/change/client/name")
    public void changeClientName(@RequestBody ClientChangeClientByNameDto clientChangeName) {
        Optional<ClientDto> client = clientService.findClient(clientChangeName.getName());
        ClientDto clientDto = client.orElseThrow(() -> new ClientNotFoundNameException(clientChangeName.getName()));
        clientService.changeClientName(clientDto, clientChangeName.getName());
    }

    @PutMapping("/change/client/surname")
    public void changeClientSurname(@RequestBody ClientChangeClientBySurnameDto clientChangeSurname) {
        Optional<ClientDto> client = clientService.findClient(clientChangeSurname.getSurname());
        ClientDto clientDto = client.orElseThrow(() -> new ClientNotFoundSurnameException(clientChangeSurname.getSurname()));
        clientService.changeClientSurname(clientDto, clientChangeSurname.getSurname());
    }

    @GetMapping("/client/orders")
    public List<OrderDto> getAllClientOrders(@RequestBody ClientGetAllClientOrdersByIdDto clientOrdersById, @RequestBody ClientGetAllClientOrdersByOrdersDto clientOrdersByOrders) {
        Optional<ClientDto> client = clientService.findClient(clientOrdersById.getId());
        ClientDto clientDto = client.orElseThrow(() -> new ClientNotFoundException(clientOrdersById.getId()));
        List<OrderDto> clientOrders = orderService.findOrdersByClient(clientDto);
        return clientOrders;
    }

//     @GetMapping("/clients/orders")
//     public List<OrderDto> getAllClientOrders(@RequestBody Long id) {
//         Optional<ClientDto> client = clientService.findClient(id);
//         if (client.isPresent()) {
//             OrderDto orders = new OrderDto();
//             return clientService.getAllClientOrders(id, orders);
//         } else {
//             throw new ClientNotFoundException(id);
//         }
//     }

//     @GetMapping("/clients/{id}/orders/all")
//     public List<OrderDto> getAllClientOrders(@RequestBody ClientDto client, OrderDto orders) {
//         return clientService.getAllClientOrders(client, orders);
//     }

}
