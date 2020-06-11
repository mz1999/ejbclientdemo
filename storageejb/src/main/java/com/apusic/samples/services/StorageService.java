package com.apusic.samples.services;

import javax.ejb.Remote;

@Remote
public interface StorageService {

    public void deduct(String commodityCode, int count);

}
