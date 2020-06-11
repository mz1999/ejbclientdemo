package com.apusic.samples.services;

import com.apusic.samples.models.Response;

import java.util.List;


public interface BusinessService {

    public Response createOrder(String userId, String commodityCode, int count);

    public List<String> getUserList(String start, String end);
}
