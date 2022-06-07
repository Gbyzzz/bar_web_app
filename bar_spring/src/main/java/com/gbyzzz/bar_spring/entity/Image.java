package com.gbyzzz.bar_spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})

public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(name = "bytes")
    private byte[] bytes;
}
