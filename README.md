Proyecto JAVA + thymeleaf que muestra el uso y guardado de valores en cookies.

La funcionalidad principal se encuentra en triangulocontroller.java: 

     /**
     * Muestra el formulario para calcular el área y perímetro de un triángulo.     
     * Si hay datos en las cookies, los muestra en el formulario.
     *
     * @param session   La sesión HTTP para almacenar el nombre del usuario.
     * @param base      El valor de la base del triángulo desde la cookie.
     * @param altura    El valor de la altura del triángulo desde la cookie.
     * @param area      El área del triángulo desde la cookie.
     * @param perimetro El perímetro del triángulo desde la cookie.
     * @param model     El modelo para pasar datos a la vista.
     * @return La vista del formulario.
     */
