package com.example.ecobit.Services;

import com.example.ecobit.Model.Sale;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SaleService {
    @GET("api/ecobit/sale")
    Call<Sale> sale(@Query("saleId") String saleId);

    @GET("api/ecobit/sale")
    Call<List<Sale>> getSales();
}
