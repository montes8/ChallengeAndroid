# Mobilechallenget


# Android studio electric patron de aquitectura MVVM, acquitectura clean 
# patrones de diseño factory, observer entre otros
# Al no encontrar un servicio gratuito cree uno temporalmente para esta demo 


# ADICIONAL SCREEM HOME y MAP
# utilize corountinas, hit, room y la libreria de navegacion de compose

# HOME -> HomeScreen, DetailScreen, MapScreen  (Screem)

# en la primera vista consumo un servicio y muestro la lista en un reciclerView con la 
# foto del plato y la foto

# al dar click en el item de la receta lleva al detalle del la receta ->
# en esa vista del detalle obtengo el dato de la receta haciendo una peticion a la
# base de datos con el id que obtengo del screen anterior, opcion de click en boton par aver mapa


# al dar click nos lleva al mapa en el cual obtengo un listado de las recetas de la base de datos 
# y lo grafico en el mapa 


# HOME y MAP (Activitys) la navegacion se pasa de un activity a otro 

# hay un boton flotante que tambien nos lleva a un mapa el cual lo cree en una actividad diferente a la del home


# agrege ci para subir el proyecto en github y validar que todo es test este correcto
# https://github.com/montes8/Mobilechallenget