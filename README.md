# FOROHUB  
Esta es una api que permite crear, visualizar, actualizar y eliminar un topico asociado a un usuario  
Para que estas operaciones esten disponibles, el usuario debe estar logeado en la base de datos.  

### BASE DE DATOS  
- La configuracion de la base de datos se hizo a través de Docker Compose. Usted puede visualizar el archivo  
`docker-compose.yml`. Además se crea automaticamente la base de datos.
- Para levantar la base de datos, debe tener instalado docker y docker compose  
- Luego con el comando `docker-compose up` la base de datos comenzara a crearse
- Con el comando `docker ps` puede verificar los contenedores activos actuales  
![Vista del comando descrito](./img/vistaDockerPs.png)

### ENTIDADES
1. Usuario  
Cuenta con los siguientes atributos:
    - id: Integer
    - correo: String
    - contrasena: String
2. Topico  
Cuenta con los siguientes atributos:
    - id: Integer
    - titulo: String
    - fechaCreacion: LocalDateTime, formato "dd/MM/yyyy HH:mm"
    - Estado: ENUM(ACTIVO, INACTIVO)
    - autor: Usuario
    - curso: String

### ENDPOINTS
- `GET: /topico`, permite listar los topicos que hay en la base de datos
- `GET: /topico/id`, permite encontrar un topico por ID
- `POST: /topico`, permite crear un nuevo topico, los atributos a enviar son: "titulo":String, "fechaCreacion":String con formato dd/MM/yyyy HH:mm, "usuarioId":Integer, "curso":String
- `PUT: /topico`, permite actualizar un topico, los atributos actualizables son: "titulo":String, "fechaCreacion":String con formato dd/MM/yyyy HH:mm, "usuarioId":Integer, "curso":String  
Adicionalmente debe enviar "id":Integer, para escpecificar el identificador del topico que desea actualizar
- `DELETE /topico/id`, permite eliminar un topico a partir de su Id o identificador
- `POST /login`, recibe las credenciales del usuario: "correo":String, "contrasena":String  
Devuelve token para usarse en los endpoints  

### AUTENTICACION Y AUTORIZACION
Aclarar que para usar los metodos es importante que primero registre un usuario en la base de datos de la siguiente manera  
![Insertando un usuario en la base de datos](./img/insercionDB.png)  
La contraseña Hasheada ha sido `123456`

### DEMOSTRACION
1. Listando topicos sin enviar token
![Obtenido lista sin enviar token](./img/sinTokenGetAll.png)
Respuesta
![Respuesta](./img/sinTokenGetAll2.png)
2. Generando token
![Login](./img/login.png)
Token devuelto
![Token devuelto](./img/tokenDevuelto.png)
3. Listar topicos usando el token creado
![Token en campo](./img/tokenEnZona.png)
![lista de topicos](./img/listaTopicos.png)
![lista en Base de datos](./img/registroListadoBD.png)
4. Crear topico usando el token generado
![Creando topico](./img/creandoTopico.png)
![Listar topico](./img/listarTopico2.png)
5. Visualizar topico por Id uasndo el token generado
![Topico por Id](./img/verTopicoId.png)
6. Actualizar un topico
![topico actualizado](./img/topicoActualizado.png)
![topico actualizazo DB](./img/topicoActualizado2.png)
7. Eliminar un topico
![topico eliminado](./img/topicoEliminado.png)
![topico eliminado DB](./img/topicoEliminado2.png)

### GESTION DE ERRORES
1. En caso intente eliminar un topico que no existe en la base de datos
![topico no encontrado](./img/topicoNotFound.png)
2. En caso intente ver un topico con id inexistente
![not found](./img/getNotFound.png)
3. En caso envie un topico con atributos incompletos para ser creado
![not created](./img/topicoCreacionFallida.png)
