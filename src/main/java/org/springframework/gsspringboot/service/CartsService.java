package org.springframework.gsspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.gsspringboot.model.Cart;
import org.springframework.gsspringboot.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartsServise")
public class CartsService {
    private CartRepository cartRepository;

    @Autowired
    public CartsService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void saveNewCart(Cart cart){
        cartRepository.save(cart);
    }
    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }
}
