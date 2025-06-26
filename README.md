# Guía Técnica - OPEN Project `si729pc2u`

## 🧱 Proyecto

- **Nombre del Proyecto:** `si729pc2u`
- **Package Base:** `com.intellectsoft.platform.u`

---

## 📦 Dependencias

Asegúrate de tener las siguientes dependencias en tu `pom.xml`:

```xml
<dependencies>
    <!-- Spring Boot y herramientas básicas -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>

    <!-- MySQL Driver -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
    </dependency>

    <!-- Validaciones -->
    <dependency>
        <groupId>jakarta.validation</groupId>
        <artifactId>jakarta.validation-api</artifactId>
    </dependency>

    <!-- Swagger / OpenAPI -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.8.8</version>
    </dependency>

    <!-- Pluralización para nombres de tablas -->
    <dependency>
        <groupId>io.github.encryptorcode</groupId>
        <artifactId>pluralize</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
