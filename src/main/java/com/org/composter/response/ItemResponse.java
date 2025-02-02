package com.org.composter.response;

import com.org.composter.model.Items;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {
    private Items items;
    private String name;
    private String contact;
}
