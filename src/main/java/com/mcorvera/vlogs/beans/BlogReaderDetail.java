package com.mcorvera.vlogs.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author miltoncorvera
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogReaderDetail {
    private Integer id;
    private String name;
    private Integer readerId;
    private String readerName;
    private Integer blogId;
    private String blogTitle;
    private Integer userId;
    private String userCode;
}
