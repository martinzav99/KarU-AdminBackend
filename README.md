# KarU - Proyecto Profesional
El **objetivo** de este proyecto es **crear una pagina web de compra/venta de autos** usados para la empresa ficticia "KarU".

Ademas , **se requería** utilizar el marco de trabajo Safe y como **metodologia agil scrum**.

Para lograrlo se decidio dividir el total de desarrolladores en 4 grupos, facilitando asi aplicar la **arquitectura de microservicios** donde cada grupo
usaba la base de datos y lenguaje de programacion que gusten.

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

_[!] cada actividad muestra lo desarrollado por el correspondiente grupo, ya sea en forma de vista en el sitio web o como funcionalidad_
### De estado
Usado para mostrar los estados por los que pasa el vehiculo, ya sea durante el proceso de compra o venta.

<img src="/images/estadoVehiculo.png" width="604" height="337">

**Definiciones de algunos estados:**

- espera revisión legal:  El vehiculo todavía no tiene cargada la documentación o está incompleta. 
- espera revisión técnica:  El vehículo todavía no fue revisado.
- espera decisión final: El equipo comercial todavía no se confirmó la compra. 
- en reparacion: El vehiculo adquirido no esta en perfectas condiciones, cuando se repare pasara a estar disponible.
- reservado: El vehiculo antes de su venta puede ser reservado, si luego cancelan la reserva vuelve a estar disponible.

_[!]Destacamos que a partir del estado disponible pertenecería al proceso de venta._

## Demo :rocket:
https://karu-web-back.onrender.com/ (BBDD Fuera de Servicio)
ACLARACION
Lo primero que queremos aclarar es que como en el flujo interactúan diversos roles, cada uno con sus diferentes vistas; en la demo estaremos accediendo con el rol de IT, un desarrollador, que tiene acceso a todas las funcionalidades del sistema para ahorrar tiempo de la demo.

0:25
Primero un cliente accede a la funcionalidad de vender mi auto, aquí lo primero que debe hacer es llenar un formulario con sus datos personales. Los datos más importantes son el DNI porque será usado por los diferentes equipos para identificar a un cliente, y el mail porque posteriormente será necesario para que reciba instrucciones de como llevar a cabo la compra.

0:47
Una vez terminado de cargar sus datos, se lo redirige a una pantalla para cargar los datos del vehíiculo. Aquí ingresa datos como su patente, el kilometraje, la marca y modelo de vehículo con año y motor. Y por último indica si el vehículo es importado o nacional, y si tiene gnc o no.

1:00
A continuación lo redirige a una pantalla para que el cliente pueda sacar un turno para realizar una revisión técnica. Ingresa su patente, selecciona el taller donde quiere realizar la revisión, y por último selecciona la fecha y horario.
Una vez saca el turno, se le envía un mail con instrucciones indicándole que tiene que presentarse media hora antes del turno en el área de administración de una sucursal de la empresa y llevar la documentación correspondiente para que la revisen.

1:45
Así que el día de la revisión, el cliente se acerca a administración y un empleado administrativo carga este formulario, controlando las multas del vehículo y revisando si la verificación policial, el grabado de autopartes y la vtv están en orden.
Si todo está bien, ahora el cliente puede acercarse al taller para hacer la revisión técnica.
La revisión es un proceso largo y tomaría mucho tiempo para la demo así que vamos a continuar la demo con otro vehículo simulando que se le realizó la revisión técnica.

2:10
Esta es la pantalla de compras que mostró el grupo 3, donde se puede aceptar o rechazar una compra.
Normalmente cuando se termina de revisar un vehículo, se calculan los gastos de reparación, y con ese valor ya se puede generar una cotización de compra del vehículo, el cual se la pasamos a un vendedor, el cual decide si aceptarla o rechazarla. 
Vamos a simular que el vendedor aceptó la compra, y vamos a continuar con el vehículo SKG701.

2:25
En circunstancias normales, un vendedor le pide los datos bancarios al cliente y le realiza una transferencia, pero en este caso vamos a simular que se realiza esa transacción con una integración realizada con el grupo 2. Así que apretamos en comprar y se realiza el pago al cliente.

3:22
Una vez comprado, el vehículo puede que necesite reparaciones o puede estar en perfecto estado, en este caso el vehículo está en perfecto estado, así que está listo para que se cree su publicación de venta. Para ello vamos a habilitar vehículo, donde llenamos 3 imágenes del vehículo y seleccionamos la sucursal donde se va a vender.
Una vez llenados los datos se crea la publicación.

3:46
Así que ahora vamos a la sección para comprar un auto, y vemos que el nuevo vehículo ha sido publicado exitosamente.

[![Alt text](/images/demo.png)](https://www.youtube.com/watch?v=ImLtY6LBlNU)
## Integrantes :black_nib:
