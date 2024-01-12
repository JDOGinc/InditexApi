Para resolver este caso práctico he decidido implementar 4 modelos en el dominio Category, Product, Stock y Shirt. Estos 4 modelos vienen dados por el enunciado que nos idica que se nos da un _"listado de **productos** que se muestra en una **categoría** de **camisetas**"_. 

CATEGORY

Aunque no se nos da más información sobre que propiedades tiene una categoría, decido implementarla con propiedades comunes porque da contexto y es necesaria para la lógica de negocio de la empresa. Este modelo contiene su propio id, un nombre, una descripción y una lista de productos.

PRODUCT - STOCK

Product es nuestro modelo pricipal, contiene toda la información de un producto sacada del listado que se nos da para el ejercicio, hace de Aggregate Root de Stock ya que controla el acceso a este. Stock es una clase Inner static, básicamente es agregación que no tiene existencia propia sin un producto y que no se puede instanciar de forma independiente. Stock contiene el valor de ventas por unidades y un Map que representa el stock que hay por tallas, para las tallas se usa un enum ya que a la larga nos permite agregar más tallas de forma sencilla. La clase Producto tiene sus constructores como protected porque esta clase es padre de todos los tipos de productos que existen y son los hijos los encargados del proceso de implementación. 

SHIRT

Este modelo extiende de Product, pese a no tener más información sobre características específicas de esta clase sobre su padre, añadí la propiedad type ya que en el nombre del producto siempre se hace referencia al tipo, SHIRT o T-SHIRT. Esta clase tiene los constructores privados ya que utiliza Factory Methods. 


ARQUITECURA Y DISEÑO

Todo el proyecto trata de ajustarse a la arquitectura Hexagonal y principios DDD. Para ello se deja todo lo que sea lógica de negocio en el dominio, en este caso el servicio de Category que es el encargado de ordenar la lista de productos perteneciente a una categoría y el servicio de Product que es el encargado de calcular la puntuación de un producto según los criterios de ordenación. Como el servicio de Category necesita implementar el servicio de Product se ha decidido hace el servicio de Product una interfaz para no romper el principio de inversión de dependencias. 

BASE DE DATOS

Para probar el funcionamiento se incluye en el proyecto un archivo llamado MongoCollection.json que se puede importar en mongoDb para hacer las pruebas
