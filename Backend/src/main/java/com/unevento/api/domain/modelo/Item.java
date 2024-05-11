package com.unevento.api.domain.modelo;

import java.math.BigDecimal;

public class Item {
    private String id;
    private String title;
    private String description;
    private String pictureUrl;
    private String categoryId;
    private int quantity;
    private BigDecimal unitPrice;
    private String currencyId;

    // Constructor público
    public Item(String id, String title, String description, String pictureUrl, String categoryId, int quantity, BigDecimal unitPrice, String currencyId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.categoryId = categoryId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.currencyId = currencyId;
    }

    public static ItemBuilder builder() {
        return new ItemBuilder();
    }

    // Métodos de acceso (getters)
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    // Clase interna estática ItemBuilder
    public static class ItemBuilder {
        private String id;
        private String title;
        private String description;
        private String pictureUrl;
        private String categoryId;
        private int quantity;
        private BigDecimal unitPrice;
        private String currencyId;

        // Métodos para configurar los atributos del ItemBuilder
        public ItemBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ItemBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ItemBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ItemBuilder pictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
            return this;
        }

        public ItemBuilder categoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public ItemBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public ItemBuilder unitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public ItemBuilder currencyId(String currencyId) {
            this.currencyId = currencyId;
            return this;
        }

        // Método para construir un objeto Item con los atributos configurados en el ItemBuilder
        public Item build() {
            return new Item(id, title, description, pictureUrl, categoryId, quantity, unitPrice, currencyId);
        }
    }


}
