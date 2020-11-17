package com.org.composter.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewItemRequest {
    private String userid;
    private String itemname;
    private long itemcost;
    private byte[] image;
    private String itemweight;
    private Date date;
    private String lat;
    private String lng;
}
