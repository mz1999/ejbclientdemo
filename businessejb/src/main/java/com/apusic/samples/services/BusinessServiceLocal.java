package com.apusic.samples.services;

import com.apusic.samples.models.Response;

import javax.ejb.Local;
import javax.ws.rs.*;
import java.util.List;

@Local
@Path("business")
public interface BusinessServiceLocal extends BusinessService {

    @POST
    @Path("/createOrder")
    @Produces("application/json")
    @Override
    public Response createOrder(@FormParam("userId") String userId,
                                @FormParam("commodityCode") String commodityCode,
                                @FormParam("count") int count);

    @GET
    @Path("/getUserList")
    @Produces("application/json")
    @Override
    public List<String> getUserList(@QueryParam("start") String start,
                                    @QueryParam("end") String end);
}
