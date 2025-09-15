package com.platzy.pizza.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemId implements Serializable{
    private Integer idOrder;
    private Integer idItem;

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        OrderItemId that = (OrderItemId) obj;
        return this.idOrder.equals(that.idOrder) && this.idItem.equals(that.idItem);
    }

    @Override
    public int hashCode() { return Objects.hash(idOrder, idItem); }

}
