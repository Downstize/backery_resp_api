package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findClientById(Integer id);

    List<Client> findClientByName(String client_name);

    List<Client> findClientBySurname(String client_surname);

    List<Client> findClientByAddress(String address);

    void deleteClientById(Long id);

    void deleteClientByName(String client_name);

    void deleteClientBySurname(String client_surname);

    void deleteClientByAddress(String address);
}
