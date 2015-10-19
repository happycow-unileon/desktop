HappyCow
=================================
HappyCow es un programa pensado para realizar evaluaciones del bienestar animal vacuno en granjas.

La última versión compilada se puede encontrar en la carpeta Release

Compilar HappyCow desde netbeans
------------------------------------
Crear un proyecto en Netbeans, descargar el código fuente en otra carpeta y copiar la carpeta nbproject y el fichero build.xml en la carpeta del código fuente. Abrir el proyecto desde Netbeans.

Para una correcta generación del jar, con sus librerías dentro y el splashscreeen, se necesita:
+ Incluir las carpetas involucradas (resources) como carpetas fuentes en netbeans
+ Asegurarse de tener en el manifest.mf la línea 
SplashScreen-Image: images/logo.png
+ En el fichero build.xml de netbeans añadir dentro de las etiquetas project
ATENCIÓN: falta una etiqueta target que github no muestra bien!, leer el readme en modo Raw
<target name="-post-jar">
    <property name="store.jar.name" value="HappyCow"/>
 
    <property name="store.dir" value="dist"/>
    <property name="store.jar" value="${store.dir}/${store.jar.name}.jar"/>
 
    <echo message="Packaging ${application.title} into a single JAR at ${store.jar}"/>
 
    <jar destfile="${store.dir}/temp_final.jar" filesetmanifest="skip">
        <zipgroupfileset dir="dist" includes="*.jar"/>
        <zipgroupfileset dir="dist/lib" includes="*.jar"/>
 
        <manifest>
            <attribute name="Main-Class" value="${main.class}"/>
            <attribute name="SplashScreen-Image" value="images/logo.png" />
        </manifest>
    </jar>
 
    <zip destfile="${store.jar}">
        <zipfileset src="${store.dir}/temp_final.jar"
        excludes="META-INF/*.SF, META-INF/*.DSA, META-INF/*.RSA"/>
    </zip>
 
    <delete file="${store.dir}/temp_final.jar"/>
    <delete dir="${store.dir}/lib"/>
    <delete file="${store.dir}/README.TXT"/>

</target>

