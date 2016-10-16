package com.tmitri.splattask.endpoint;

import com.tmitri.splattask.webservice.AccountServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * Created by Дмитрий on 15.10.2016.
 */
//Для простоты тестирования, я решил использовать Publisher для Jax-WS вместо того, чтобы разворачивать на Tomcat
public class AccountServicePublisher {
    public static void main(String args[]) {

        Endpoint.publish("http://localhost:8000/wss/account_service", new AccountServiceImpl());
    }
}
