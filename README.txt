TRIVIAL PATRIMONIO INDUSTRIAL MÁLAGA

Equipo: Mara, Álvaro, Daniel, Pablo del Pino.

Descripción del problema:
Se nos ha encargado hacer una aplicación con filosofía Android en Java, la cual consistirá en un quiz o un juego trivial sobre la Industria de Málaga. Estará orientada a un público de alumnado de primaria y secundaria. El objetivo es entretener y poner a prueba los conocimientos del usuario.

Funcionalidad de la app:
Nuestra aplicación tendrá una pantalla de inicio con un botón que al ser pulsado comenzará el test, y otro botón que nos llevará a una pantalla con información sobre nosotros, los desarrolladores.
El test constará de una pregunta, una imagen, una serie de respuestas a elegir y un temporizador. Cuando se elija una respuesta o se termine el tiempo, no se podrá contestar otra vez y habrá que pasar a la siguiente pregunta pulsando un botón.
Si la respuesta es correcta, la pantalla se mostrará en verde y se sumarán una serie de puntos en base al tiempo que se tarde, y en caso de que se acabe el tiempo o la respuesta sea incorrecta, saldrá en rojo. Y, consecutivamente, se pasará a la siguiente pregunta. Al terminar el test, se mostrará la puntuación final.

Sistema de puntuación:
Los puntos ganados por cada pregunta dependerán del tiempo que se tarde en responder dicha pregunta. Suponiendo que el usuario tuviera 15 segundos para responder, si responde en los primeros 5 segundos, recibirá la  máxima puntuación (1000 pts por pregunta).
A partir de los 10 segundos, por cada segundo que tarde recibirá 100 pts menos, es decir, si tarda 4 segundos recibe 600 pts (10 segundos iniciales - 4 segundos = 6 segundos), si tarda 5 segundos recibirá 500 pts y así sucesivamente hasta que se termine el tiempo.



Detalle del reparto de tareas:

Sergio Soriano:
	Se ha encargado de pulir la clase GameViewHolder.java del package Control junto con Results_activity.java del package View.
  	GameViewHolder.java 
  	Results_activity.java 

Daniel Sanchez Medialdea:
	Se ha encargado de todos los xml del proyectos junto con la clase Game del package Model.
    Game
    activity_conocenos.xml
    activity_game.xml
    activity_main.xml
    activity_results.xml
    rv_main.xml


Mara Gambero:
	Se ha encargado aparte de revisar todo, de hacer las clases:
    GameActivity
    GameRVAdapter
    GameViewModel


Alvaro Millan:
	Se ha encargado de hacer la clase MainActivity y la clase conocenosActivity.
    MainActivity 
    conocenosActivity


Pablo Del Pino:
	Se ha responsabilizado de hacer las clases Question y Answer en el package Model.
    Question
    Answer
