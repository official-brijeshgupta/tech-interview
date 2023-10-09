Overvidw 

Create 2 services with 1 Pod each: 
1. MongoDb
2. Mongo Express 

Steps 
1. Create a service 
2. Create a deployment 
3. We would also need to create a configMap and secret to store database credentials.



Request Flow 
Browser -> Mongo Express (External Service) -> Mongo Express (Pod) -> MongoDb (Internal Service) -> MongoDb (Pod) 


Setup 
Create secret 
- Create the secret: it is required if we want to reference the secret in the deployment file. 
command 
```
kubectl apply -f mongodb-secret.yaml
kubectl get secret
output:
NAME             TYPE     DATA   AGE
mongodb-secret   Opaque   2      12s
```
Create deployment 
command
```
kubectl apply -f mongodb-deployment.yaml
kubectl get deployment
output:
NAME                 READY   UP-TO-DATE   AVAILABLE   AGE
mongodb-deployment   1/1     1            1           85s

```

Create the servise using the same file 
```
kubectl apply -f mongodb-deployment.yaml
deployment.apps/mongodb-deployment unchanged
service/mongodb-service created

kubectl get service
output:
NAME              TYPE        CLUSTER-IP     EXTERNAL-IP   PORT(S)     AGE
kubernetes        ClusterIP   10.96.0.1      <none>        443/TCP     20h
mongodb-service   ClusterIP   10.99.82.156   <none>        27017/TCP   57s

```

check if service is attached with right Pod
```
kubectl describe service mongodb-service
output: 
Name:              mongodb-service
Namespace:         default
Labels:            <none>
Annotations:       <none>
Selector:          app=mongodb
Type:              ClusterIP
IP Family Policy:  SingleStack
IP Families:       IPv4
IP:                10.99.82.156
IPs:               10.99.82.156
Port:              <unset>  27017/TCP
TargetPort:        27017/TCP
Endpoints:         10.244.0.12:27017             <- this should be the IP address of one of the Pod
Session Affinity:  None
Events:            <none>

kubectl get pod -o wide
output: 
NAME                                  READY   STATUS    RESTARTS   AGE   IP            NODE       NOMINATED NODE   READINESS GATES
mongodb-deployment-65758d59c6-xzf5r   1/1     Running   0          13m   10.244.0.12   minikube   <none>           <none>
```

create a config map to store normal configuraiton that we will use in mongo-express 
```
kubectl apply -f mongo-express-configmap.yaml
output:
configmap/mongo-express-configmap created

kubectl get configmap
output:
NAME                      DATA   AGE
kube-root-ca.crt          1      21h
mongo-express-configmap   1      82s
```

create the deployment for mongo-express
```
kubectl apply -f mongo-express.yaml
output:
deployment.apps/mongo-express-deployment created

kubectl get deployment
output:
NAME                       READY   UP-TO-DATE   AVAILABLE   AGE
mongo-express-deployment   1/1     1            1           111s
mongodb-deployment         1/1     1            1           71m

kubectl get pods
output:
NAME                                       READY   STATUS    RESTARTS   AGE
mongo-express-deployment-6b974f98b-4pbfw   1/1     Running   0          97s
mongodb-deployment-65758d59c6-xzf5r        1/1     Running   0          71m

kubectl logs mongo-express-deployment-6b974f98b-4pbfw
output:
Welcome to mongo-express
------------------------


(node:7) [MONGODB DRIVER] Warning: Current Server Discovery and Monitoring engine is deprecated, and will be removed in a future version. To use the new Server Discover and Monitoring engine, pass option { useUnifiedTopology: true } to the MongoClient constructor.
Mongo Express server listening at http://0.0.0.0:8081
Server is open to allow connections from anyone (0.0.0.0)
basicAuth credentials are "admin:pass", it is recommended you change this in your config.js!
```

Now in order to access the mongo-express from browser, we would need an expternal service. 

Create an external service for mongo-express
We use the same file to add service using a yaml file seperator --- (triple dash)

```
kubectl apply -f mongo-express.yaml
output:
deployment.apps/mongo-express-deployment unchanged
service/mongo-express-service created

kubectl get service
output:
NAME                    TYPE           CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
kubernetes              ClusterIP      10.96.0.1        <none>        443/TCP          21h
mongo-express-service   LoadBalancer   10.103.141.119   <pending>     8081:30000/TCP   99s
mongodb-service         ClusterIP      10.99.82.156     <none>        27017/TCP        72m
```

Now we the external ip columns show pending, which should have been assigned to mongo-express-service. But things work little differently in mimikube, 
we need to use the following commands to assign the external IP

```
minikube service mongo-express-service
output: 
|-----------|-----------------------|-------------|---------------------------|
| NAMESPACE |         NAME          | TARGET PORT |            URL            |
|-----------|-----------------------|-------------|---------------------------|
| default   | mongo-express-service |        8081 | http://192.168.49.2:30000 |
|-----------|-----------------------|-------------|---------------------------|
🏃  Starting tunnel for service mongo-express-service.
|-----------|-----------------------|-------------|------------------------|
| NAMESPACE |         NAME          | TARGET PORT |          URL           |
|-----------|-----------------------|-------------|------------------------|
| default   | mongo-express-service |             | http://127.0.0.1:58969 |
|-----------|-----------------------|-------------|------------------------|
🎉  Opening service default/mongo-express-service in default browser...
❗  Because you are using a Docker driver on darwin, the terminal needs to be open to run it.
```
This would be url with which we can access mongo-express - http://127.0.0.1:58969 

![image](https://github.com/brijeshguptakol/interview-prep/assets/13299369/24442063-18ad-4c71-a157-b79d5c58c14b)


Question : 
- Why a tunnel is opened in this case probably because of using docker's virtualization rather then hyperkit hyperwiser 

Once we create a database from browser, the flow of information happenes like this :

![image](https://github.com/brijeshguptakol/interview-prep/assets/13299369/e75d69fb-8807-451a-ab14-a8e1cca6c770)

Here the services are acting as Load balancers.
 

