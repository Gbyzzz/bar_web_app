package com.gbyzzz.bar_spring.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "image_id")
    private Long imageId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "filename")
    private String filename;

    @Basic
    @Column(name = "content_type")
    private String contentType;

    @Basic
    @Column(name = "size")
    private Long size;

    @Basic
    @Column(name = "bytes")
    private byte[] bytes;
}
