          A highly structured, modular authentication backend built on Spring Boot 3, JPA, and JWT security.

## ⚡ Context
This is a robust, dedicated backend service designed exclusively for handling user authentication and authorization using modern token-based security standards.

This project is structured using the **Ports and Adapters** (or Hexagonal) architectural pattern, ensuring that the core business logic (the Domain) remains decoupled from external frameworks, databases, and security implementations. This separation of concerns guarantees high testability and flexibility for future modifications.
1.  **Domain Isolation:** Core business entities (`User`, `Role`) and business rules live entirely within the `domain` layer.
2.  **Interface Driven:** Input and output operations are defined by interfaces (`RegisterUser`, `LoginUser` as inbound ports; `UserRepository` as an outbound port).
3.  **Pluggable Infrastructure:** Concrete implementations (JPA for persistence, Spring Security for web context, JJWT for token generation) are treated as adapters that fulfill the contract defined by the ports.

This project built using **Java 17** and the **Spring Boot** framework. The architecture is explicitly divided into application layers following the Ports and Adapters model:

*   **Application Layer (`application/service`):** Contains the `AuthService`, which orchestrates the business flow using the domain models and interacting with the ports.
*   **Domain Layer (`domain/model` and `domain/port`):** Defines the core entities (`User`, `Role`) and the contracts (Ports) for all use cases (Inbound) and external dependencies (Outbound).
*   **Infrastructure Layer (`infrastructure/`):** Houses all the adapters for web (controllers, DTOs), security (JWT, Spring Security configuration), and persistence (JPA entities and repositories).

### 🔒 Enterprise-Grade Security via JWT

The service is built around JSON Web Tokens (JWT) for stateless authentication.

*   🔐 **Stateless Session Management:** The inclusion of `JwtProvider.java` and `JwtFilter.java` verifies a dedicated, standardized approach to generating, validating, and managing JWTs, eliminating the need for traditional server-side session management.
*   ⚙️ **Custom Security Configuration:** Leveraging `SecurityConfig.java` allows for granular control over access rules, endpoint protection, and integration of the JWT filter chain, guaranteeing that only authenticated and authorized requests reach the core business logic.

### 🎯 (Ports & Adapters)

The project structure is meticulously crafted for longevity and maintainability.

*   🧱 **Decoupled Business Logic:** The separation of domain models (`User.java`) from persistence entities (`UserEntity.java`) ensures that database schema changes do not necessitate changes to the core business rules or service implementation.
*   🔗 **Interface-Driven Development:** The use of Inbound Ports (`RegisterUser.java`, `LoginUser.java`) and Outbound Ports (`UserRepository.java`) forces clear boundaries, allowing infrastructure components (like persistence) to be swapped out without altering the application services.
