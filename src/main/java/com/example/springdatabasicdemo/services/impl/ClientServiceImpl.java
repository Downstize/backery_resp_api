package com.example.springdatabasicdemo.services.impl;
import com.example.springdatabasicdemo.dtos.OrderDto;
import com.example.springdatabasicdemo.dtos.ClientDto;
import com.example.springdatabasicdemo.models.Client;
import com.example.springdatabasicdemo.models.Order;
import com.example.springdatabasicdemo.repositories.ClientRepository;
import com.example.springdatabasicdemo.repositories.OrderRepository;
import com.example.springdatabasicdemo.services.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService<Integer> {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ClientDto registerNewClient(ClientDto client) {
        Client c = modelMapper.map(client,Client.class);
        return modelMapper.map(clientRepository.save(c),ClientDto.class);
    }

    @Override
    public void deleteClientByName(ClientDto client) {
        clientRepository.deleteClientByName(client.getClient_name());
    }

    @Override
    public void deleteClient(Long integer) {
        clientRepository.deleteClientById(integer);
    }

    @Override
    public void deleteClientBySurname(ClientDto client) {
        clientRepository.deleteClientBySurname(client.getClient_surname());

    }

    @Override
    public Optional<ClientDto> findClient(Integer integer) {
        return Optional.ofNullable(modelMapper.map(clientRepository.findClientById(integer), ClientDto.class));
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream().map((c) -> modelMapper.map(c, ClientDto.class)).collect(Collectors.toList());
    }


    @Override
    public void changeClientName(ClientDto client, String client_name) {
        Client c = modelMapper.map(client.getClient_name(), Client.class);
        c.setClient_name(client_name);
        clientRepository.save(c);
    }

    @Override
    public void changeClientSurname(ClientDto client, String client_surname) {
        Client c = modelMapper.map(client.getClient_surname(), Client.class);
        c.setClient_surname(client_surname);
        clientRepository.save(c);
    }


    @Override
    public List<OrderDto> getAllClientOrders(ClientDto client, OrderDto orders) {
        Client c = modelMapper.map(client, Client.class);
        List<Order> order = orderRepository.findOrderByClient(c);
        return order.stream().map(product -> modelMapper.map(orders, OrderDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getAllClientOrders(Integer integer, OrderDto orders) {
        Client client = clientRepository.getById(integer);
        if (client != null)
         {
            List<Order> clientOrders = orderRepository.findOrderByClient(client);
            return clientOrders.stream().map(order -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
         } else {
                   return Collections.emptyList();
               }
    }
}
