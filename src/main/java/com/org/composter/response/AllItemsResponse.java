package com.org.composter.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllItemsResponse {
    private long itemId;
    private String sname;
    private String scontact;
    private String iname;
    private String icost;
    private String idp;
    private String iweight;
    private String iimg;
}
