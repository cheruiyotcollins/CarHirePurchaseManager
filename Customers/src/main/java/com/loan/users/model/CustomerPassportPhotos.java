package com.loan.users.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "customer_passport_photos")
public class CustomerPassportPhotos implements Serializable {
    @Id
        private Long photoId;
    @Lob
    private Byte[] photo;
}
