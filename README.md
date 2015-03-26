happycow
========
Chicos, Ni se os ocurra subir nada específico de netbeans, eclipse y similares, eh? quiero decir, nada de las carpetas dist y build, nada de nbproject.properties ni .git, nada de .sqlite también.

Para una correcta generación del jar, con sus librerías dentro y el splashscreeen, en el fichero build.xml de netbeans añadir dentro de las etiquetas project

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


Como detalle, añadir que si no sale el splashscreen, asegurarse de que en el manifest aparezca la línea:
SplashScreen-Image: images/logo.png
