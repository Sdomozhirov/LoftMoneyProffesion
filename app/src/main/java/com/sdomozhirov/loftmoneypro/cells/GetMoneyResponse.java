package com.sdomozhirov.loftmoneypro.cells;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMoneyResponse {

    @SerializedName("status") private String status;
    @SerializedName("data") private List<Item> moneyItemsList;

    public String getStatus() {
        return status;
    }

    public List<Item> getMoneyItemsList() {
        return moneyItemsList;
    }

}
