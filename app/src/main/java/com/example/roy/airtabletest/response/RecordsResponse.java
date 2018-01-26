package com.example.roy.airtabletest.response;

import com.example.roy.model.Records;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by roy on 2018/1/9.
 */

public class RecordsResponse {

    @SerializedName("records")
    private List<Records> records;

    public RecordsResponse(List<Records> records) {
        this.records = records;
    }

    public List<Records> getRecords() {
        return records;
    }
}
