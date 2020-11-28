package com.org.composter.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String image;
    private String itemweight;
    //@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private long date;
    private String lat;
    private String lng;
}
