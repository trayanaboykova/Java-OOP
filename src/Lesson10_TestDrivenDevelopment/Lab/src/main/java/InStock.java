package Lesson09_TestDrivenDevelopment.Lab.src.main.java;

import java.util.Iterator;

public class InStock implements ProductStock {

    @Override
    public int getCount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Product product) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(Product product) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void changeQuantity(String product, int quantity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Product find(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Product findByLabel(String label) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Product> iterator() {
        throw new UnsupportedOperationException();
    }
}
