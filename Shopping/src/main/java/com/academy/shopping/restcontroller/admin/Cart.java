package com.academy.shopping.restcontroller.admin;

import com.academy.shopping.model.domain.Product;

import lombok.Data;

@Data
public class Cart extends Product {
	private int quantity;
}
