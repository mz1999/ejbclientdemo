package com.apusic.samples.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import java.io.Serializable;

@Stateless
public class StorageServiceBean implements StorageService, Serializable {

    @PostConstruct
    public void init() {
        System.out.println("[StorageServiceBean.init]");

    }

    @PreDestroy
    public void cleanup() {
        System.out.println("[StorageServiceBean.cleanup]");
    }

    public void deduct(String commodityCode, int count) {
        System.out.println("[StorageServiceBean]commodityCode:" + commodityCode + ", count:" + count);
    }
}
