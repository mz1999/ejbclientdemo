package com.apusic.samples;

import com.apusic.samples.models.Response;
import com.apusic.samples.services.BusinessService;

import javax.ejb.EJB;

public class BusinessClient {

    @EJB(lookup = "java:global/business-ear/business-ejb/BusinessServiceBean!com.apusic.samples.services.BusinessServiceRemote")
    private static BusinessService businessService;

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: BusinessClient userId commodityCode count");
            System.exit(1);
        }
        BusinessClient client = new BusinessClient();
        Response response = client.createOrder(args[0], args[1], Integer.parseInt(args[2]));
        System.out.println(response);
    }

    public Response createOrder(String userId, String commodityCode, int count) {
        return businessService.createOrder(userId, commodityCode, count);
    }

}
