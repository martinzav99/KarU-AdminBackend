# KarU - Proyecto Profesional
El objetivo de este proyecto es crear una pagina web de compra/venta de autos usados para la empresa ficticia "KarU".
Ademas , se requeria utilizar el marco de trabajo Safe y como metodologia agil scrum.
Para lograrlo se decidio dividir el total de desarrolladores en 4 grupos, facilitando asi aplicar la arquitectura de microservicios donde cada grupo
usaba la base de datos y lenguaje de programacion que gusten.

## Alcance
El proyecto se dividio en 3 modulos: comercio, administracion, tecnico
Cabe desatacar que las tareas de administracion se dividio en 2 grupos. 
A nuestro equipo se hizo encargo del analisis crediticio de los clientes y ajustes de precios masivos e individuales de los vehiculos publicados.
Por otro lado, tambien seriamos los encargados de manejar los datos de los empleados(login, registro,TFA) y los vehiculos(carga y control del proceso de compra/venta).

## Indicadores
A lo largo del proyecto se realizaron diferentes entregas donde se mostraba el avance del proyecto. 
En particular, fue muy importante utilizar varios indicadores.

El primero es el burndown chart, indicador que se utiliza para ver visualmente las horas restantes del proyecto.
Como se puede ver, empezamos el proyecto con 694 horas estimadas e íbamos avanzando bien, hasta que en el segundo sprint se nos agregaron funcionalidades, por lo cual se ve el pico de subida en la imagen. Y de la misma forma, sucedió lo mismo en el pico que se ve al final del tercer sprint.
[alt text](/images/burndownchart.png)

El siguiente gráfico es el indicador de bugs abiertos vs cerrados, usado para mostrar la cantidad de bugs encontrados y solucionados cada día.
Como se puede ver, durante el primer sprint no se realizó mucho testing, por lo cual al final del segundo, al testear las funcionalidades desarrolladas en los primeros dos sprints, se dió el periodo donde se encontró la mayor cantidad de bugs.
Como la mayoría de bugs encontrados eran no críticos, se prefirió darle foco en desarrollar nuevas funcionalidades en los sprints 3 y 4, dejando el arreglo de bugs para el final, en lo cual se pudo arreglar la gran mayoría de bugs.
[alt text](/images/bugs.png)

Y el último indicador utilizado es el indicador de nivel de riesgo, el cual se utiliza para conocer bajo qué tanto peligro se encuentra el proyecto en un determinado momento.
En nuestro caso lo calculamos sumando los 5 riesgos de mayor exposición que se encuentran en la matriz de riesgos.
Como se puede ver, los dos picos de subida que se ven en el gráfico se deben primero al problema que tuvimos que todavía no encontrábamos forma de hostear el backend del sistema, lo cual al encontrar Railway bajó el nivel de riesgo, y el segundo pico fue por los problemas de integración del front mencionados anteriormente.
Al final del proyecto se solucionaron la mayoría de los riesgos, por lo cual el nivel de riesgo acabó siendo bajo.
[alt text](/images/riesgo.png)

## Diagramas

Bueno, a continuación voy a presentar los diagramas.
El primero es el flujo NORMAL de compra de un vehículo representado en un diagrama de actividad.
El objetivo de este gráfico es mostrar las interacciones entre los módulos o grupos al momento de realizar la compra de un vehículo.
Como primer paso un usuario que desee vender su vehículo cargará sus datos personales y de contacto en una pantalla creada por el módulo comercial. Estos datos serán utilizados más adelante para hacer envío de información …
Cuando termina de cargar los datos, será redirigido a una pantalla creada por el grupo de administración, en esta deberá cargar los datos relacionados con el vehículo como la patente, modelo o tipo de combustible.
Al finalizar pasará a la última pantalla , en este caso creada por el grupo técnico, donde deberá sacar un turno para la revisión técnica de su vehículo.
Para hacer la entrega de la documentación requerida de un vehículo el usuario deberá acercarse personalmente a una sucursal, donde deberá presentar los documentos al personal administrativo, donde este será encargado de cargarlos en el sistema.
A continuación, se realizará la revisión técnica en sí y luego a partir de los datos obtenidos previamente se genera una cotización de compra donde en caso de llegar a un acuerdo, un personal de ventas confirmará la compra del vehículo y se utilizará la funcionalidad creada por el grupo 2 para simular el pago bancario.
Una vez realizado todo este proceso se tiene en cuenta el estado del vehículo, si su puntaje técnico es del 100%, es decir en perfecto estado, quiere decir que el vehículo está disponible para su habilitación (poner en venta)
En caso de no estarlo, se deberá sacar turno para reparación y luego de la misma poder habilitar el vehículo para su venta
[alt text](/images/flujoDeVenta.png)

El siguiente diagrama es una diagrama de estados por los que pasa el vehículo, este está en gran parte relacionado al diagrama anterior, excepto que a partir del estado disponible pertenecería al flujo de venta.
Bueno rápidamente podemos mencionar el estado de espera revisión legal, que es cuando todavía no se cargó la documentación o está incompleta. espera revisión técnica cuando espera que el vehículo todavía no fue revisado, espera decisión final, cuando todavía no se confirmó la compra por parte del grupo comercial. Cuando se toma la decisión pasa a rechazado o aceptado.
Pasará a comprado directamente si el auto estaba en perfecto estado y si no, pasará por reparación.
Por último cuando el auto es habilitado, pasa a disponible donde podrá ser reservado y luego vendido o en caso de que cancelen la reserva volver a estar disponible o que se venda directamente.
[alt text](/images/estadoVehiculo.png)

## Demo
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
## Integrantes
