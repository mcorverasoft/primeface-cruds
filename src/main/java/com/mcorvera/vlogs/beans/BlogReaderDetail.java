/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
