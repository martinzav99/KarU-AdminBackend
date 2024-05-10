# KarU - Proyecto Profesional
El **objetivo** de este proyecto es **crear una pagina web de compra/venta de autos** usados para la empresa ficticia "KarU".

Ademas , **se requería** utilizar el marco de trabajo Safe y como **metodologia agil scrum**.

Para lograrlo se decidio dividir el total de desarrolladores en 4 grupos, facilitando asi aplicar la **arquitectura de microservicios** donde cada grupo
usaba la base de datos y lenguaje de programacion que gusten.

https://karu-web-back.onrender.com/ _(BBDD fuera de servicio)_

## Alcance :mag:
El proyecto se dividio en **3 modulos: Comercio, Administración, Técnico**.

Cabe desatacar que las tareas de administracion se reapartieron entre 2 equipos. 

Nuestro equipo fue el encargado de: 
- Generar analisis crediticio de los clientes
- Ajustar de forma masiva e individuales los precios de los vehiculos publicados.
- Manejar los datos de los empleados(login, registro,TFA)
- Manejar los datos de los vehiculos(carga y control del proceso de compra/venta).

## Indicadores :chart_with_upwards_trend:
Durante el transcurso del proyecto se realizaron diferentes entregas donde se mostraba el avance del proyecto.

### Burndown Chart

Usado para mostrar el avance del proyecto en horas.
En este caso podemos ver que **se llego una estimacion de 694 horas para todo el proyecto** y que durante el segundo y tercer sprint se agregaron cambios en las funcionalidades y es por esa razon que se ven picos que demuestran el uso extra de horas.

<img src="/images/burndownchart.png" width="604" height="337">

### Bugs abiertos vs cerrados

Usado para mostrar la cantidad de bugs encontrados y solucionados cada día.

Como se puede ver, durante el primer sprint no se realizó mucho testing, por lo cual **al final del segundo**, al testear las funcionalidades desarrolladas en los primeros dos sprints, **se dió el periodo donde se encontró la mayor cantidad de bugs.**

Debido a que **la mayoria de estos bugs fueron calificados como no críticos**, se prefirió darle foco en desarrollar nuevas funcionalidades en los sprints 3 y 4, dejando el arreglo de bugs no criticos para el final.

<img src="/images/bugs.png" width="604" height="337">

### Nivel de riesgo

Usado para conocer bajo qué tanto peligro se encuentra el proyecto en un determinado momento.

En nuestro caso **lo calculamos sumando los 5 riesgos de mayor exposición** que se encuentran en la matriz de riesgos(documentacion privada).

Los dos picos de subida que se ven en el gráfico se deben primero al problema de encontrar un host para el backend del sistema, y el segundo por **problemas de integración del front** con los otros equipos de desarrollo.

**Al final** del proyecto **se solucionaron la mayoría de los riesgos**, por lo cual el nivel de riesgo termino bajando.

<img src="/images/riesgo.png" width="604" height="337">

## Diagramas :book:

### De actividad
Usado para representar el proceso NORMAL de compra de un vehículo.

**El objetivo** de este gráfico **es mostrar las interacciones entre los** módulos o **grupos al momento de realizar la compra** de un vehículo.

<img src="/images/flujoDeCompra.png" width="604" height="337">

1. **Un usuario** que desee vender su vehículo **cargará** sus **datos de contacto, datos del vehiculo y** debera **sacar un turno para la revision tecnica** a travez del sitio web de KarU.
2. **La documentacion** requerida **del vehiculo se hará de manera presencial** al personal administrativo de una sucursal el dia del turno, donde este será encargado de cargarlos en el sistema.
3. A continuación, **se realizará la revisión técnica** en sí **y luego** a partir de los datos obtenidos previamente **se genera una cotización de compra** donde en caso de llegar a un acuerdo **, un personal de ventas confirmará la compra del vehículo** y se utilizará la funcionalidad para simular el pago bancario.
4. Una vez realizado todo este proceso **se tiene en cuenta el estado del vehículo**, si su puntaje técnico es del 100%, es decir en perfecto estado, el vehiculo se pone a la venta.
**En caso de no estarlo**, se deberá **sacar turno para reparación** y luego de la misma poder habilitar el vehículo para su venta

_[*] cada actividad muestra lo desarrollado por el correspondiente grupo, ya sea en forma de vista en el sitio web o como funcionalidad_
### De estado
Usado para mostrar los estados por los que pasa el vehiculo, ya sea durante el proceso de compra o venta.

<img src="/images/estadoVehiculo.png" width="604" height="337">

**Definiciones de algunos estados:**

- Espera revisión legal: el vehiculo todavía no tiene cargada la documentación o está incompleta. 
- Espera revisión técnica: el vehículo todavía no fue revisado.
- Espera decisión final: el equipo comercial todavía no se confirmó la compra. 
- En reparacion: el vehiculo adquirido no esta en perfectas condiciones, cuando se repare pasara a estar disponible.
- Reservado: el vehiculo antes de su venta puede ser reservado, si luego cancelan la reserva vuelve a estar disponible.

_[*]Destacamos que a partir del estado disponible pertenecería al proceso de venta._

## Demo :rocket:

HACER CLICK EN LA IAMGEN PARA VER LA DEMO :)

**Vistas del cliente que desea vender su auto** del 0:00 al 1:20

**Vista del personal administrativo** del 1:20 al 1:50 

**Vista del personal de Comercio** del 2:00 al 3:30

**Vista del cliente que desea comprar un auto** 3:35 al 3:51

[<img src="/images/demo.png" width="604" height="337">](https://www.youtube.com/watch?v=ImLtY6LBlNU){:target="_blank"}

_[*] En la demo estaremos accediendo con el rol de desarrollador, que tiene acceso a todas las funcionalidades y vistas del sistema para ahorrar tiempo._

_[*] La revisión tecnica tomaría mucho tiempo para la demo así que continuamos con otro vehículo al que ya se le hizo una._

_[*] Normalmente deberiamos tener los datos bancarios del cliente y realizar una transferencia. En este caso vamos a simular que se realiza esa transacción con una integración realizada con el grupo 2._

## Integrantes :black_nib:
