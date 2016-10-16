package com.tmitri.splattask.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.transaction.Transactional;

/**
 * Created by Дмитрий on 15.10.2016.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface AccountService {
    @WebMethod
    Long getAmount(Integer id);
    @WebMethod
    void addAmount(Integer id, Long value);
}
