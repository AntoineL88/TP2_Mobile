package com.example.e1363135.autopartsmobile;

public class Products {
    public String _id;
    public String produitAfficher;

    public Products(String _id, String code, String name, int price, String supplier) {
        this._id = _id;
        this.produitAfficher = code + " " + " " + name + " " + price + " " + supplier;
    }

    @Override
    public String toString() {
        return produitAfficher;
    }
}
