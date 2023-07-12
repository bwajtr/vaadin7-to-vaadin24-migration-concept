# Vaadin7 to Vaadin24 Migration concept

* The main goal here is to provide a way how to make it possible to use Vaadin 7 data layer classes from the `com.vaadin.v7.data` package with the Vaadin 24 compoments
* Based on the Martin Vysny's idea of mimicking the Vaadin 7 Component API in Vaadin 24 environment
* You can see the final usage of the created classes in `MainView` class, the aim here was to make it easy for Dragonfly to migrate to the new classes
* The concept here is to:

1. keep the service layer providing the data basically untouched 
- in this project it's represented by the `DataService` class
- the `DataService` class compiles thanks to this dependency in pom.xml (it excludes everything but tha main `vaadin-compatibility-server` artifact, that contains the `com.vaadin.v7.data` package):
```xml
<dependency>
    <groupId>com.vaadin</groupId>
    <artifactId>vaadin-compatibility-server</artifactId>
    <version>8.9.2</version>
    <exclusions>
        <exclusion>
            <groupId>*</groupId>
            <artifactId>*</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

2. Provide subclasses of the Vaadin 24 components that mimic the Vaadin 7 components API
- in this project it's represented by classes in the `org.vaadin.example.featurepack` package: V7Label, V7TextField
- the implementations are not fully completed, but the main idea is to
 - subclass the Vaadin 24 component
 - add `Binder<Property>` to the subclass, bind to the `com.vaadin.v7.data.Property` to mimic the Vaadin 7 behavior
 - add missing methods from the Vaadin7 component counterpart and use the Binder to provide the missing functionality (like validations, conversions)
 - for the case of the V7Grid add a constructor that takes the `IndexedContainer` as a parameter, use it as a datasource and on top of that implement "Vaadin7" behavior: automatic column creation, filtering, sorting etc. 