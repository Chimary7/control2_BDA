# Instrucciones detalladas para configurar y desplegar la aplicación. 

## Para ejecutar el *back-end*:

1. Crear una base de datos en la aplicación pgAdmin4.

2. Modificar la URL, usuario, contraseña y nombre de la base de datos de PostgreSQL dentro de los archivos `application.properties` y `Sql2oConfig.java`

> [!note]
> En la URL, se debe cambiar el puerto a la base de datos creada en pgAdmin4.
> El nombre de la base de datos creada en pgAdmin4 y en application.properties deben ser las mismas.

3. Ejecutar el *backend*.

---

Para la ejecución de la base de datos, con el objetivo de modificar la base desde Postman es necesario realizar los siguientes cambios en los siguientes archivos.

Es necesario hacer la siguiente modificación dentro del archivo SecurityConfig.java
```
public class SecurityConfig {
    @Autowired
    private CorsConfigurationSource corsConfigurationSource;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .cors(cors ->cors.configurationSource(corsConfigurationSource))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/usuario/**", "/tarea/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
```
Este cambio es necesario debido al uso de cookies dentro del código.
---

Para hacer utilización del postman se adjuntan ejemplos en el link: https://imgur.com/a/urKATmb
**Para ejecutar el *front-end***:
1. Abrir una sesión del terminal con el directorio situado en la carpeta del front-end.
2. Ejecutar los siguientes comandos:
```
npm install
npm install axios (instalar manualmente axios para asegurar su instalación)
npm run dev 
```
3.  Acceder a la URL `http://localhost:5173/`, 

> [!note]
> * Una tarea puede contar con los estados: “pendiente”, “próximo a vencer”, “vencido”, y “completada”
