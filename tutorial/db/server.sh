java -classpath ../src/main/webapp/WEB-INF/lib/hsqldb.jar org.hsqldb.Server -database test

java -jar ../WebContent/WEB-INF/lib/hsqldb.jar --inlinerc url=jdbc:hsqldb:hsql://localhost,user=sa,password= create_products.sql load_data.sql

