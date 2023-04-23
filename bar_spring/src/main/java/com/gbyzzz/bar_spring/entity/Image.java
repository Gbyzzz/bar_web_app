package com.gbyzzz.bar_spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "images")
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

    public Image() {
    }

    public Image(Long imageId, String name, String filename, String contentType,
                 Long size, byte[] bytes) {
        this.imageId = imageId;
        this.name = name;
        this.filename = filename;
        this.contentType = contentType;
        this.size = size;
        this.bytes = bytes;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(imageId, image.imageId) && Objects.equals(name, image.name) && Objects.equals(filename, image.filename) && Objects.equals(contentType, image.contentType) && Objects.equals(size, image.size) && Arrays.equals(bytes, image.bytes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(imageId, name, filename, contentType, size);
        result = 31 * result + Arrays.hashCode(bytes);
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId=" + imageId +
                ", name='" + name + '\'' +
                ", filename='" + filename + '\'' +
                ", contentType='" + contentType + '\'' +
                ", size=" + size +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }
}
