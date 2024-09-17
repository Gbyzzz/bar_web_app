package com.gbyzzz.bar_search.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.gbyzzz.bar_search.entity.type.PGByteArrayType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "images")
@JsonIgnoreProperties({"hibernateLazyInitializer"})

public class CocktailImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @Basic
    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @Basic
    @Column(name = "filename", length = 45, nullable = false)
    private String filename;

    @Basic
    @Column(name = "content_type", length = 45, nullable = false)
    private String contentType;

    @Basic
    @Column(name = "size", nullable = false)
    private Long size;

    @Lob
    @Type(PGByteArrayType.class)
    @Column(name = "bytes", nullable = false)
    private byte[] bytes;

    public CocktailImage() {
    }

    public CocktailImage(Long imageId, String name, String filename, String contentType,
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
        CocktailImage cocktailImage = (CocktailImage) o;
        return Objects.equals(imageId, cocktailImage.imageId) && Objects.equals(name, cocktailImage.name) && Objects.equals(filename, cocktailImage.filename) && Objects.equals(contentType, cocktailImage.contentType) && Objects.equals(size, cocktailImage.size) && Arrays.equals(bytes, cocktailImage.bytes);
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
