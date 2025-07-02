package streamAPI.task2.service;

import streamAPI.task2.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductAnalyzer {
    private final List<Product> products;

    public ProductAnalyzer(List<Product> products) {
        this.products = products;
    }

    public List<Product> getAllProducts() {
        return products.stream().toList();
    }

    public  List<Product> getShortNamedProducts() {
        return products.stream().filter(p->p.getName().length()<5)
                .collect(Collectors.toList());
    }

    //кол-во вхождений продукта
    public long countProductOccurrences(String productName) {
        return products.stream().filter(p->p.getName().equalsIgnoreCase(productName)).count();
    }

    //на опред.букву
    public List<Product> getProductsStartigLetter(char letter) {
        return products.stream().filter(p->p.getName().toLowerCase().startsWith(
                String.valueOf(letter).toLowerCase()
        )).collect(Collectors.toList());
    }

    //из категории молоко, ну я так понял, обязательно milk, ну окей
    public List<Product> getMilkProducts() {
        return products.stream().filter(
                p->p.getCategory().equalsIgnoreCase("Молоко"))
                .collect(Collectors.toList());
    }
}
