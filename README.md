# Proyecto Precios - Prueba Técnica

Este proyecto implementa un sistema para gestionar precios de productos con operaciones CRUD y un endpoint personalizado. Se usa **Spring Boot** con **H2 Database** en memoria.

## Acceso a la Base de Datos

- **URL H2**: [http://localhost:8080/h2-console/login.jsp](http://localhost:8080/h2-console/login.jsp?jsessionid=337b906458df041b324c1f5123ed93dd)
- **Usuario**: `admin`
- **Contraseña**: (vacía)

## Endpoints con Postman

### 1. Registrar nuevo precio (POST)
- **URL**: `http://localhost:8080/savePrice`
- **Cuerpo**:
   ```json
   {
       "brandId": 1,
       "startDate": "2020-06-14T00:00:00",
       "endDate": "2020-12-31T23:59:59",
       "productId": 35455,
       "priority": 0,
       "price": 50.50,
       "curr": "EUR"
   }
   
### 2. Query de todos los precios (GET)
- **URL**: `http://localhost:8080/identificarProducto`
  
### 3. Actualizar precio (PUT)
- **URL**: `http://localhost:8080/identificarProducto`

### 4. Eliminar precio (DELETE)
- **URL**: `http://localhost:8080/deletePrice/6`

### 5. Obtener precios aplicables (GET)
- **URL**: `http://localhost:8080/getApplicablePrices?date=2020-06-14-16.00.00&productId=35455&brandId=1`

## Tecnologías
- **Spring Boot**
- **H2 Database (en memoria)**
- **Mockito (Pruebas)**

## Desarrollo
- **IDE**: `IntelliJ IDEA`
- **Comprobaciones**: `PostMan`
