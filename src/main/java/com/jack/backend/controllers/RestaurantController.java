package com.jack.backend.controllers;

import com.jack.backend.models.Product;
import com.jack.backend.models.Restaurant;
import com.jack.backend.models.RestaurantOrder;
import com.jack.backend.service.ProductService;
import com.jack.backend.service.RestaurantOrderService;
import com.jack.backend.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantService restaurantService;
    private ProductService productService;
    private RestaurantOrderService restaurantOrderService;

    @Autowired
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setRestaurantOrderService(RestaurantOrderService service) {
        this.restaurantOrderService = service;
    }

    @GetMapping("/")
    public List<Restaurant> getAll() {
        return restaurantService.queryAll();
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable("id") Long id) {
        Restaurant restaurant = restaurantService.query(id);
        return restaurant;
    }

    @GetMapping("/{id}/products")
    public List<Product> getAllProducts(@PathVariable("id") Long id) {
        return productService.queryAllByRestaurantId(id);
    }

    @GetMapping("/{id}/orders")
    public List<RestaurantOrder> getOrdersByRestaurantId(@PathVariable("id") Long id) {
        return restaurantOrderService.queryOrdersByRestaurantId(id);
    }

    @GetMapping("/{id}/orders/{status}")
    public List<RestaurantOrder> getOrdersByRestaurantIdAndStatus(
            @PathVariable("id") Long id,
            @PathVariable("status") String status) {
        return restaurantOrderService.queryOrdersByRestaurantIdAndStatus(id, status);
    }

    @GetMapping("/login")
    public Restaurant login(@RequestParam("username") String username,
                            @RequestParam("password") String password) {
        Restaurant restaurant = restaurantService.login(username, password);
        if (null != restaurant) {
            restaurant.setPassword("xxx");
        }
        return restaurant;
    }

    @PostMapping("/register")
    public Restaurant register(@RequestBody Restaurant restaurant) {
        int result = restaurantService.register(restaurant);
        return restaurant;
    }

    @PutMapping("/")
    public int updateRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.update(restaurant);
    }

    @PostMapping("/orders")
    public int createNewOrder(@RequestBody RestaurantOrder order) {
        return restaurantOrderService.saveOrder(order);
    }
}
