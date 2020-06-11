package com.apusic.samples.services;

import com.apusic.samples.models.Response;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.apusic.samples.utils.Utils.getRandomNumberUsingNextInt;
import static com.apusic.samples.utils.Utils.randomAlphabeticString;

@Stateless
public class BusinessServiceBean implements BusinessService,
        BusinessServiceLocal, BusinessServiceRemote, Serializable {

    @EJB
    private StorageService storageService;

    @EJB
    private OrderService orderService;

    @PostConstruct
    public void init() {
        System.out.println("[BusinessServiceBean.init]" + storageService.getClass());
        System.out.println("[BusinessServiceBean.init]" + orderService.getClass());
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("[BusinessServiceBean.cleanup]");
    }


    @Override
    public Response createOrder(String userId, String commodityCode, int count) {
        System.out.println("[BusinessServiceBean]userId: " + userId + ", commodityCode: " + commodityCode + ", count: " + count);
        orderService.create(userId, commodityCode, count);
        storageService.deduct(commodityCode, count);
        return new Response(200, userId, commodityCode, count, System.currentTimeMillis());
    }

    @Override
    public List<String> getUserList(String start, String end) {
        System.out.println("[BusinessServiceBean]start: " + start + ", end: " + end);
        List<String> ret = new ArrayList<String>();
        ret.add(start);
        int count = getRandomNumberUsingNextInt(1, 100);
        for (int i = 0; i < count; i++) {
            ret.add(randomAlphabeticString(10));
        }
        ret.add(end);
        return ret;
    }
}
