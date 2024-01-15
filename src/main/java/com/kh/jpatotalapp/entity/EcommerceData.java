package com.kh.jpatotalapp.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.List;

@Data
@Document(indexName = "kibana_sample_data_ecommerce")
public class EcommerceData {

    @Id
    private String id;

    @Field(name = "category", type = FieldType.Keyword)
    private List<String> category;

    @Field(name = "currency", type = FieldType.Keyword)
    private String currency;

    @Field(name = "customer_first_name", type = FieldType.Keyword)
    private String customerFirstName;

    @Field(name = "customer_full_name", type = FieldType.Keyword)
    private String customerFullName;

    @Field(name = "customer_gender", type = FieldType.Keyword)
    private String customerGender;

    @Field(name = "customer_id", type = FieldType.Integer)
    private Integer customerId;

    @Field(name = "customer_last_name", type = FieldType.Keyword)
    private String customerLastName;

    @Field(name = "customer_phone", type = FieldType.Keyword)
    private String customerPhone;

    @Field(name = "day_of_week", type = FieldType.Keyword)
    private String dayOfWeek;

    @Field(name = "day_of_week_i", type = FieldType.Integer)
    private Integer dayOfWeekI;

    @Field(name = "email", type = FieldType.Keyword)
    private String email;

    @Field(name = "manufacturer", type = FieldType.Keyword)
    private List<String> manufacturer;

    @Field(name = "order_date", type = FieldType.Keyword)
    private String orderDate;

    @Field(name = "order_id", type = FieldType.Long)
    private Long orderId;

    @Field(name = "products", type = FieldType.Nested)
    private List<Product> products;

    @Field(name = "taxful_total_price", type = FieldType.Double)
    private Double taxfulTotalPrice;

    @Field(name = "taxless_total_price", type = FieldType.Double)
    private Double taxlessTotalPrice;

    @Field(name = "total_quantity", type = FieldType.Integer)
    private Integer totalQuantity;

    @Field(name = "total_unique_products", type = FieldType.Integer)
    private Integer totalUniqueProducts;

    @Field(name = "type", type = FieldType.Keyword)
    private String type;

    @Field(name = "user", type = FieldType.Keyword)
    private String user;

    @Field(name = "geoip", type = FieldType.Object)
    private GeoIp geoip;

    @Field(name = "event", type = FieldType.Object)
    private Event event;

    @Data
    public static class Product {
        @Field(name = "base_price", type = FieldType.Double)
        private Double basePrice;

        @Field(name = "discount_percentage", type = FieldType.Integer)
        private Integer discountPercentage;

        @Field(name = "quantity", type = FieldType.Integer)
        private Integer quantity;

        @Field(name = "manufacturer", type = FieldType.Keyword)
        private String manufacturer;

        @Field(name = "tax_amount", type = FieldType.Double)
        private Double taxAmount;

        @Field(name = "product_id", type = FieldType.Integer)
        private Integer productId;

        @Field(name = "category", type = FieldType.Keyword)
        private String category;

        @Field(name = "sku", type = FieldType.Keyword)
        private String sku;

        @Field(name = "taxless_price", type = FieldType.Double)
        private Double taxlessPrice;

        @Field(name = "unit_discount_amount", type = FieldType.Double)
        private Double unitDiscountAmount;

        @Field(name = "min_price", type = FieldType.Double)
        private Double minPrice;

        @Field(name = "_id", type = FieldType.Keyword)
        private String id;

        @Field(name = "discount_amount", type = FieldType.Double)
        private Double discountAmount;

        @Field(name = "created_on", type = FieldType.Keyword)
        private String createdOn;

        @Field(name = "product_name", type = FieldType.Keyword)
        private String productName;

        @Field(name = "price", type = FieldType.Double)
        private Double price;

        @Field(name = "taxful_price", type = FieldType.Double)
        private Double taxfulPrice;

        @Field(name = "base_unit_price", type = FieldType.Double)
        private Double baseUnitPrice;
    }

    @Data
    public static class GeoIp {
        @Field(name = "country_iso_code", type = FieldType.Keyword)
        private String countryIsoCode;

        @Field(name = "location", type = FieldType.Object)
        private Location location;

        @Field(name = "region_name", type = FieldType.Keyword)
        private String regionName;

        @Field(name = "continent_name", type = FieldType.Keyword)
        private String continentName;

        @Field(name = "city_name", type = FieldType.Keyword)
        private String cityName;
    }

    @Data
    public static class Location {
        @Field(name = "lon", type = FieldType.Double)
        private Double lon;

        @Field(name = "lat", type = FieldType.Double)
        private Double lat;
    }

    @Data
    public static class Event {
        @Field(name = "dataset", type = FieldType.Keyword)
        private String dataset;
    }
}