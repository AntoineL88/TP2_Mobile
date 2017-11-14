package com.example.e1363135.autopartsmobile.Fournisseurs;

public class Suppliers {
    public String _id;
    public String affichageSuppliers;

    public Suppliers(String _id, String name, String address_street, String address_city, String address_province, String address_postal_code) {
        this._id = _id;
        this.affichageSuppliers = "Nom: " + name + ", Rue: " + address_street + ", Ville: " + address_city + ", Province: " + address_province + ", Code Postal: " + address_postal_code;
    }

    @Override
    public String toString() {
        return affichageSuppliers;
    }
}
