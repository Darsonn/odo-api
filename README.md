# Odo API - Vehicle Shift Tracking System

[![Java](https://img.shields.io/badge/Java-Supported-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Supported-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Flyway](https://img.shields.io/badge/Database-Flyway%20Migrations-red.svg)](https://flywaydb.org/)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue.svg)](https://www.docker.com/)

Odo API to wydajna usługa RESTful zaprojektowana do zarządzania flotą pojazdów oraz czasem pracy kierowców. Aplikacja pozwala na ewidencję użytkowników, pojazdów oraz precyzyjne śledzenie rozpoczęcia i zakończenia poszczególnych zmian za kierownicą (Shift Tracking). 

Projekt symuluje rzeczywiste systemy logistyczne i jest zbudowany z naciskiem na najlepsze praktyki inżynierii oprogramowania (wzorzec DTO, wersjonowanie bazy danych, centralna obsługa wyjątków).

## 🚀 Główne funkcjonalności

- **Zarządzanie Zmianami (Shift Tracking):** Dedykowane operacje biznesowe do rozpoczynania (`StartShiftRequest`) i kończenia zmian (`EndShiftRequest`), łączące kierowcę z odpowiednim pojazdem.
- **Zarządzanie Flotą i Użytkownikami (CRUD):** Pełna obsługa rejestrów pojazdów (`Vehicle`) oraz pracowników (`User`).
- **Wzorzec DTO (Data Transfer Object):** Ścisła separacja logiki prezentacji od warstwy dostępu do danych. Encje bazodanowe nigdy nie są eksponowane na zewnątrz API.
- **Wersjonowanie Schematu Bazy Danych:** Implementacja skryptów migracyjnych SQL (np. `V1__init_schema.sql`) zapewniająca przewidywalność struktury bazy danych.
- **Globalna Obsługa Błędów:** Spójne odpowiedzi błędów API (np. dla `ResourceAlreadyExistsException`) zarządzane centralnie przez `GlobalExceptionHandler`.

## 🛠️ Stack technologiczny

- **Java**
- **Spring Boot** (Web, Data JPA)
- **Relacyjna Baza Danych** (inicjalizowana przez skrypty SQL)
- **Migracje DB** (wsparcie dla Flyway/Liquibase bazujące na `/db/migration`)
- **Docker** (zawiera `Dockerfile` dla łatwego wdrożenia)
- **Maven** (budowanie z użyciem `mvnw`)

## ⚙️ Wymagania i uruchomienie lokalne

Aplikację można uruchomić lokalnie przy użyciu wbudowanego wrappera Maven.

1. **Sklonuj repozytorium**
   ```bash
   git clone https://github.com/Darsonn/odo-api.git
   cd odo-api
   ```

2. **Konfiguracja Bazy Danych**
   Upewnij się, że Twoja relacyjna baza danych jest uruchomiona, a odpowiednie poświadczenia (URL, użytkownik, hasło) zostały zaktualizowane w pliku `src/main/resources/application.properties`. Struktura bazy danych zostanie automatycznie wygenerowana przy starcie aplikacji dzięki skryptom migracyjnym.

3. **Uruchomienie przez Maven Wrapper**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Uruchomienie za pomocą Dockera**
   Możesz również zbudować i uruchomić projekt jako kontener:
   ```bash
   docker build -t odo-api .
   docker run -p 8080:8080 odo-api
   ```

## 🏗️ Struktura Architektoniczna

Kod źródłowy został podzielony według standardów Domain-Driven / N-Tier:
- `controller/` - Wystawia bezpieczne i przejrzyste endpointy HTTP.
- `service/` - Enkapsuluje złożoną logikę biznesową (np. walidacje przed startem zmiany).
- `repository/` - Interfejsy komunikujące się z bazą danych (Spring Data JPA).
- `model/entity/` - Modele odwzorowujące tabele w bazie danych.
- `model/dto/` - Obiekty transferowe, określające kształt requestów i response'ów.
- `exception/` - Logika globalnego przechwytywania wyjątków.
