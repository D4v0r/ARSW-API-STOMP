# Web Sockets

> Davor Javier Cortés Cardozo
>
> Arquitectura de Software (ARWS)
>
> Laboratorio 7

## Descripción

Este ejercicio se basa en la documentación oficial de SpringBoot, para el manejo de WebSockets con STOMP.
En este repositorio se encuentra una aplicación SpringBoot que está configurado como Broker de mensajes, de forma similar a lo mostrado en la siguiente figura:

![](https://camo.githubusercontent.com/425a74c8cbbf00ff3de4320a2e909652edeaae22/68747470733a2f2f646f63732e737072696e672e696f2f737072696e672f646f63732f63757272656e742f737072696e672d6672616d65776f726b2d7265666572656e63652f696d616765732f6d6573736167652d666c6f772d73696d706c652d62726f6b65722e706e67)

En este caso, el manejador de mensajes asociado a "/app" aún no está configurado, pero sí lo está el broker '/topic'. Como mensaje, se usarán localizaciones de pantalla, pues se espera que esta aplicación permita propagar eventos de compra de asientos seleccionando en el canvas el asiento deseado. Este proyecto parte como continuación a el proyecto de compra/reserva de tickets.

## Diagrama de Actividades

![](https://cdn.discordapp.com/attachments/740467446867296298/765404707492724756/Activity_Diagram0.png)


## Comenzando

### Instalación

Primero se debe clonar el repositorio para ello inserte el siguiente comando en la línea de comandos:

```
https://github.com/D4v0r/ARSW-Heavy-Client
```

### Compilación y Pruebas

En la consola de comandos ingresar el siguiente comando para compilar el proyecto y ejecutar las pruebas:

```
mvn package
```

### Ejecución
Para ejecutar el servicio web en el localhost:8080 debe usar el siguiente comando:

```
mvn spring-boot:run
```

## LICENCIA 
Este proyecto está licenciado bajo la GNU General Public License v3.0, para más información ver la [LICENCIA](https://github.com/D4v0r/ARSW-API-STOMP/blob/master/LICENSE.txt).
