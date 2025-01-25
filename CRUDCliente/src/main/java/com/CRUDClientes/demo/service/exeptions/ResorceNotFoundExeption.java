package com.CRUDClientes.demo.service.exeptions;

public class ResorceNotFoundExeption extends RuntimeException {

    public ResorceNotFoundExeption(String msg) {
        super(msg);
    }

}
