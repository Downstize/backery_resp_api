package com.example.springdatabasicdemo.services;
import com.example.springdatabasicdemo.dtos.ClientDto;
import com.example.springdatabasicdemo.dtos.OrderDto;
import java.util.List;
import java.util.Optional;

public interface ClientService<ID> {

    ClientDto registerNewClient(ClientDto client);

    void deleteClientByName(ClientDto client);

    void deleteClient(Long id);

    void deleteClientBySurname(ClientDto client);

    Optional<ClientDto> findClient(ID id);

    List<ClientDto> getAllClients();

//    List<ClientDto> findClientByOrder(OrderDto orders, Integer orderId);

    void changeClientName(ClientDto client, String client_name);

    void changeClientSurname(ClientDto client, String client_surname);

    

    List<OrderDto> getAllClientOrders(ID id, OrderDto orders);

    List<OrderDto> getAllClientOrders(ClientDto client, OrderDto orders);
}
