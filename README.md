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
```

---

## 🗂️ Estructura del Proyecto

```
src/main/java/com/intellectsoft/platform/u20221g120tipo1/
├── Application.java  (@EnableJpaAuditing)
├── shared/
│   ├── domain/model/aggregates/AuditableAbstractAggregateRoot.java
│   ├── domain/model/valueobjects/WebAddress.java
│   └── infrastructure/persistence/jpa/configuration/strategy/
│       └── SnakeCaseWithPluralizedTablePhysicalNamingStrategy.java
│
├── portfolio/
│   ├── domain/
│   │   ├── model/
│   │   │   ├── aggregates/
│   │   │   │   └── WebApplication.java
│   │   │   ├── valueobjects/
│   │   │   │   └── BackendStack, FrontendStack, CloudPlatform, etc.
│   │   │   ├── commands/
│   │   │   │   └── CreateWebApplicationCommand.java
│   │   └── services/
│   │       └── WebApplicationCommandService.java
│   │
│   ├── infrastructure/persistence/jpa/
│   │   ├── converters/
│   │   │   ├── BackendStackConverter.java
│   │   │   ├── FrontendStackConverter.java
│   │   │   └── CloudPlatformConverter.java
│   │   └── repositories/
│   │       └── WebApplicationRepository.java
│   │
│   ├── application/internal/commandservices/
│   │   └── WebApplicationCommandServiceImpl.java
│   │
│   └── interfaces/rest/
│       ├── controller/
│       │   └── WebApplicationController.java
│       ├── resources/
│       │   ├── CreateWebApplicationResource.java
│       │   └── WebApplicationResource.java
│       └── transform/
│           ├── CreateWebApplicationCommandFromResourceAssembler.java
│           └── WebApplicationResourceFromEntityAssembler.java
```

---

## ⚙️ Configuración `application.properties`

```properties
# Puerto
server.port=8080

# Configuración de errores
server.error.include-message=always
server.error.include-stacktrace=never
```

---

## 🧾 DTOs

### `CreateWebApplicationResource` (Request DTO)

- **Propósito:** Entrada del cliente para crear una Web Application.
- **Uso:** Request body del endpoint POST.
- **Incluye:** Solo los campos que el cliente puede proporcionar.
- **No incluye:**
  - `id`
  - `clientId` (viene del path)
  - Campos de auditoría (`createdAt`, `updatedAt`)

---

### `WebApplicationResource` (Response DTO)

- **Propósito:** Representación completa de una Web Application.
- **Uso:** Response del endpoint POST (o GET).
- **Incluye:**
  - `id` generado por el sistema
  - `clientId`
  - Campos de auditoría (`createdAt`, `updatedAt`)

---

## ✅ Validaciones

| Validación                        | ¿Dónde debe ir?                          |
|----------------------------------|------------------------------------------|
| name, description, percentage    | **Dominio**                              |
| launchDate > ahora               | **Application** (usa reloj del sistema)  |
| Verificar unicidad de URLs       | **Application** (requiere repositorio)   |
| Validar formato de URL           | **Dominio** (si no requiere la BD)       |
| Validar existencia de cliente    | **Application** (requiere la BD)         |

---

## 📝 Notas Extra

- Los `valueobjects` que sean `record` deben tener `getters` explícitos si necesitas compatibilidad con frameworks como JPA.
- Los `enums` deben tener anotación `@Getter` de Lombok si se acceden como objetos.

---

## ✅ Anotaciones importantes

```java
// En Application.java
@EnableJpaAuditing
```

```java
// En el aggregate
@Convert(converter = FrontendStackConverter.class)
@Column(name = "frontend_stack", nullable = false)
private FrontendStack frontendStack;
```
