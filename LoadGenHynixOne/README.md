# LoadGenMongoDB
MongoDB Load generator

#### Usage

Edit command.properites

`````
# Possible Option TI = Thread Insert
mongodb.command = TI
# CRUD start from "mongodb.start" to "mongodb.end" 1 ~ 10 (10 times)
mongodb.start = 1
mongodb.end = 10
# MongoDB Address and option
mongodb.endpoint=serverless.abcd.mongodb.net/?retryWrites=true&w=1
mongodb.user=myuser
mongodb.pass=mypassword
mongodb.driver=mongodb+srv://
sleepmi=0
mongodb.print=true
# Target Database & Collection
mongodb.collection=tracefull
mongodb.database=hynix

# Find Option
findloop=1
threadcount=20
pagepercount=30

# CID Key
mongodb.cid=cid06

#Insert One or Many (O/M)
hynix.OneMany=O

#Insert Partial or Full (P/F)
hynix.fullPartial=F
``````

#### Java Operation


````
java -jar MongoDB-samples-1.0.3.jar command.properties

````

