package services;

import models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class GestionClientes {
    private List<Cliente> clientes;

    public GestionClientes() {
        this.clientes = new ArrayList<>();
    }

    public void registrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscarClientePorDocumento(String documento) {
        for (Cliente cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }
        return null;
    }
}