# K8s

## What is Kubernetes
- Open source container orchestration tool
- Developed by Google and now maintained by the Cloud Native Computing Foundation (CNCF).
- Helps manage container application in different deployment environments be it physical machines, virtual machines, cloud or hybrid

## Containers
Containers are lightweight, isolated units of software that package applications and their dependencies, allowing them to run consistently across different computing environments.

The main goal of Kubernetes is to abstract the underlying infrastructure and provide a unified API to manage and control containerized applications. It allows you to define the desired state of your application, including the number of replicas, resource requirements, networking, and storage configurations. Kubernetes then takes care of deploying and managing the containers to ensure that the desired state is maintained, even in the face of failures or changes in demand.


Some key features of Kubernetes include:
1. Automatic scaling: Kubernetes can automatically scale the number of running containers based on defined metrics, such as CPU usage or incoming network traffic.
2. Load balancing: It can distribute incoming network traffic across multiple containers to ensure optimal performance and high availability.
3. Service discovery and DNS: Kubernetes provides a built-in service discovery mechanism, allowing containers to locate and communicate with each other using DNS names.
4. Self-healing: If a container fails or becomes unresponsive, Kubernetes automatically restarts or replaces it to maintain the desired state.
5. Rolling updates and rollbacks: Kubernetes supports rolling updates, allowing you to update your application without any downtime. If an update causes issues, you can easily rollback to a previous version.
6. Storage orchestration: It provides a flexible way to manage persistent storage for containers, allowing you to dynamically provision and attach storage volumes to your applications.


## Different components of K8s
1. Master Components:
    * API Server: The central management point and the front-end for the Kubernetes control plane. It exposes the Kubernetes API, which is used for managing and controlling the cluster.
    * Controller Manager: Manages various controllers that handle cluster-level functions, such as replication, scaling, and managing endpoints. It detects when a pod dies and calls scheduler to restart the pod.
    * Scheduler: Responsible for assigning pods to nodes based on resource requirements, quality of service, and other policies. It decides which node has more resources and calls Kubectl to create the new pod.
    * etcd: A distributed key-value store that stores the cluster's configuration data, serving as the Kubernetes cluster's backend. Application data is not stored here. Cluster state information to help master Process to communicate with worker node.
2. Node Components (worker):
    * Kubelet: The primary agent running on each node, responsible for communicating with the API server and managing pods and containers on the node.
    * kube-proxy: Manages network communication between services and individual pods by configuring network routing rules.
    * Container Runtime: The underlying software that runs containers, such as Docker, containerd, or cri-o. It is responsible for pulling container images and executing them.
3. Add-on Components:
    * DNS: Provides DNS-based service discovery for pods and enables communication between them using their DNS names.
    * Dashboard: A web-based user interface that allows users to manage and monitor the Kubernetes cluster.
    * Ingress Controller: Manages the inbound network traffic to services within the cluster, allowing external access to services.
    * Metrics Server: Collects and serves cluster-wide resource utilization data, such as CPU and memory usage.
    * Heapster (deprecated): Previously used for cluster-wide monitoring and performance analysis, it has been superseded by the Metrics Server and is being phased out.
    * Secret : Used to store secret data and is base64 encoded
    * Service : is part of the networking layer and provides a stable network endpoint to access a set of pods. Services act as load balancers, distributing network traffic to the pods based on labels and selectors. The IPs of the service do not change while Pods may be replaced when they die and eventually may have a totally new IP address.

### ConfigMap
ConfigMap is a resource object that is used to store configuration data that can be consumed by containers running in pods. It decouples configuration from application code, allowing you to manage configuration settings separately and make changes to them without modifying the container images.
ConfigMaps are useful for storing non-sensitive configuration data, such as environment variables, command-line arguments, configuration files, or any other key-value pairs that applications need at runtime.
Here are some key aspects of ConfigMaps:
1. Data Storage: ConfigMaps store configuration data as key-value pairs. You can create a ConfigMap from literal values or by providing a configuration file or directory.
2. Configuration Injection: ConfigMaps can be injected into pods as environment variables or as mounted volumes. This allows applications to access the configuration data during runtime.
3. Environment Variables: ConfigMaps can be consumed as environment variables within a container. Each key in the ConfigMap is converted to an environment variable, and its corresponding value is set for the container.
4. Volume Mounts: ConfigMaps can be mounted as files or directories into containers. The data from the ConfigMap is made available to the container as files, which can be read by the application.
5. Updates and Rollouts: ConfigMaps can be updated independently of the pods or containers using them. When a ConfigMap is updated, any pods referencing it can be restarted or have their configuration reloaded automatically, ensuring they use the latest configuration values.
   ConfigMaps provide a flexible and scalable way to manage configuration data in a Kubernetes environment. They promote separation of concerns by allowing configuration to be decoupled from application code, facilitating easier configuration management and updates.
   To create a ConfigMap, you can use the kubectl command-line tool or define it in a YAML manifest file. The ConfigMap can then be referenced in pod specifications to inject configuration data into containers.
   Overall, ConfigMaps enhance the portability and maintainability of applications in Kubernetes by allowing you to manage configuration independently and make configuration changes without modifying the application code or container images.


### Deployment
A Deployment serves as a blueprint or specification for creating and updating the pods running your application. It abstracts the details of managing individual pods and provides an interface to manage the desired number of replicas, rolling updates, and rollback functionality.
Here are some key features and concepts related to Deployments:
1. Pod Template: A Deployment specifies a pod template that defines the desired state of the pods, including the container image, resource requirements, environment variables, and other configuration details.
2. Replica Set: Behind the scenes, a Deployment creates and manages a Replica Set, which is responsible for ensuring the desired number of pod replicas are running at all times. The Replica Set works with the Deployment to scale the number of replicas up or down based on defined criteria.
3. Rolling Updates: Deployments support rolling updates, which allow you to update your application without downtime. When a new version of the pod template is defined, the Deployment gradually replaces the old pods with new ones, ensuring a smooth transition and maintaining availability.
4. Rollback: If an update causes issues or unexpected behavior, Deployments provide a rollback mechanism. You can easily rollback to a previous known stable version by specifying the revision or a specific desired state.
5. Scaling: Deployments allow you to scale the number of replicas dynamically. You can scale up or down based on demand, adjusting the number of pods running your application.
   By using Deployments, you define the desired state of your application and let Kubernetes handle the process of managing and maintaining that state. Deployments abstract away the complexity of managing individual pods and provide a high-level interface to manage the rollout, scaling, and updating of your application.
   Overall, a Deployment in Kubernetes acts as a blueprint or specification for managing the lifecycle of your application, ensuring availability, scalability, and flexibility.



### Statefulset
StatefulSet is a higher-level resource object that is designed to manage stateful applications. While Deployments are suitable for stateless applications, StatefulSets provide additional capabilities for managing stateful applications that require stable network identities and persistent storage.
StatefulSets are used to manage the deployment and scaling of a set of pods, similar to Deployments. However, they introduce the following key characteristics:
1. Stable Network Identifiers: Each pod in a StatefulSet has a stable and unique network identity. It is assigned a stable hostname that follows a predictable naming convention, incorporating an ordinal index. This allows applications to have stable network communication and configuration based on the pod's identity.
2. Ordered and Controlled Pod Creation: StatefulSets manage the creation and scaling of pods in a predictable and ordered manner. Each pod is created one at a time, ensuring that there is a defined order in which they are deployed. This is important for applications that rely on ordered initialization, inter-pod coordination, or data replication.
3. Persistent Storage: StatefulSets provide built-in support for managing persistent storage for each pod. Each pod in the StatefulSet can have its own unique persistent volume claim (PVC) to ensure data persistence across pod rescheduling or scaling operations. This is crucial for stateful applications that require data to be preserved even if pods are restarted or moved between nodes.
4. Headless Service: StatefulSets automatically create a headless service, which allows each pod to have its own unique DNS record. This enables direct communication with individual pods using their stable network identities.
   StatefulSets are commonly used for applications that have specific requirements for data persistence, ordered pod deployment, and stable network identities. Examples include databases, distributed storage systems, and other stateful applications.


### Data storage (Volumes)
Kubernetes provides several storage-related features and abstractions, such as Persistent Volumes (PVs) and Persistent Volume Claims (PVCs), to facilitate storage management. Here's how it works:
1. Persistent Volumes (PVs): A Persistent Volume is a piece of network-attached storage (NAS), such as a disk or a network file system (NFS), that is provisioned by an administrator or a storage provider. PVs have a lifecycle independent of any individual pod (container). They can be created and managed separately from the pods that use them.
2. Persistent Volume Claims (PVCs): A Persistent Volume Claim is a request made by a pod for a specific amount and type of storage. It acts as a request for a PV with specific characteristics (e.g., storage capacity, access mode, etc.). A PVC is created by a user (developer) and is bound to a suitable PV based on the requested specifications.
3. Storage Classes: Storage Classes are used to define different types or tiers of storage offered by a cloud provider or storage vendor. They allow you to specify the characteristics and availability of the underlying storage, such as performance, redundancy, and backup policies. Storage Classes are used when provisioning PVs dynamically.

Here's an example to illustrate the storage orchestration process in Kubernetes:
1. An administrator defines a Persistent Volume (PV) by specifying the storage type, size, access mode, and other parameters.
2. A developer creates a Persistent Volume Claim (PVC) in their application's deployment configuration, specifying the desired storage requirements.
3. Kubernetes matches the PVC with an available PV that satisfies the requested specifications. If a suitable PV is found, it is bound to the PVC.
4. The developer's pod (container) can now mount the PVC as a volume and use it for storing data. The pod can read and write data to the volume, and the data will persist even if the pod is restarted or moved to a different node.
5. If the requested storage is not available, Kubernetes can dynamically provision a new PV based on a defined Storage Class. The new PV is then bound to the PVC, and the pod can use it for storage.



## Minikube
Minikube is a tool that enables you to run a single-node Kubernetes cluster locally on your machine. It is designed to facilitate development and testing of applications on Kubernetes without the need for a full-scale production cluster. Minikube creates a lightweight and self-contained Kubernetes environment that mimics the behavior of a larger cluster.

## Kubectl
kubectl is a command-line tool used to interact with a Kubernetes cluster. It is the primary interface for managing and controlling Kubernetes resources and performing various administrative tasks.
kubectl connects to the Kubernetes API server and sends commands to manage and control the cluster. It uses the configuration information stored in the kubeconfig file to determine the target cluster and authentication details.


## Installation :
Step 1 : Install Hypervisor
brew install hyperkit  # this will not work for Mac m1 since its not supported.

Since Hyperkit is not available for Apple Silicon, you can use an alternative virtualization solution like Docker Desktop's built-in support for Apple Silicon.

Step 2 : Install minikube
Once hyperkit / docker desktop is install, install minikube. minikube has kubectl as a dependency. So it will also install Kubectl by default.

brew install minikube

Commands

To start a minikube cluster
minikube start —vm-driver=hyperkit        # Specify the hypervisor to use  
minikube start —vm-driver=docker         # Specify the hypervisor to use   for M1

Get status of nodes
Kubectl get nodes

Minikube status

Use Full commands
- kubectl get services : Get the list of services
- kubectl get pods : get the list of pods
-

- Pods are smallest unit but we cannot directly create pods. We will be creating deployment which is an abstraction over Pods
  kubectl create deployment Name —image=image [—dry-run] [options]

Here image is the docker image which we want to deploy. It could be the docker image of the app that we want to deploy

Create a pod with nginx image  
kubectl create deployment nginx-deploy2 --image=nginx

kubectl get pods
NAME                             				READY   STATUS         RESTARTS   AGE
nginx-deploy2-5ccd9b797c-sjr6m   		1/1     Running       		 0         10s


kubectl get replicaset


Edit deployment
kubectl edit deployment NAME

Its a vim editor, press I to edit, change something say image version and press esc + :wq to quit and write the file.

kubectl get pods

NAME                             				READY   STATUS                   RESTARTS   AGE
nginx-deploy-7fd9ff4679-srsfr    	0/1     	 ImagePullBackOff    0          11m
nginx-deploy2-5ccd9b797c-sjr6m   1/1     	 Running                     0          11m
nginx-deploy2-66b6ccdfb9-7jhtt   	0/1     	 ContainerCreating   0          4s

Old pod is getting destroyed once the new pod is up with the new configuration.  We do not need to do anything, just change the deployment, and everything else is taken care by the Kubernetes.

kubectl delete deployment NAME

To get all the stage changes that happened in the pod  
kubectl describe pod NAME.  —NAME - deployment name


To get logs from the Pod
kubectl logs  NAME —— name of the pod like mongo-deploy-7545bc9f55-zdv9k


To get into the terminal of the mongo db container
kubectl exec -it POD-NAME — bin/bash
kubectl exec -it mongo-deploy-7545bc9f55-zdv9k -- bin/bash


Delete the pod , basically you need to delete the deployment
kubectl delete deployment mongo-deploy


To create deployment from a file
Kubectl apply -f config-file.yaml

— apply command will execute the config and do as it is mentioned there



Sample nginc-deployment.yaml
Commands

touch nginc-deployment.yaml
Vim nginc-deployment.yaml
Press i to edit and then paste the below content
Press esc + :wq to save and exit

kind: Deployment
metadata:
name: nginx-deployment
labels:
app: nginx
spec:
replicas: 1
selector:
matchLabels:
app: nginx
template:
metadata:
labels:
app: nginx
spec:
containers:
- name: nginx
image: nginx:1.16
ports:
- containerPort: 80



kubectl apply -f nginc-deployment.yaml

kubectl get pods

NAME                                READY   STATUS    RESTARTS   AGE
nginx-deployment-68d9b6c666-xx6zm   1/1     Running   0          9s


If we update the config file, Kubernetes know that the deployment was already created, so it just updates the deployment as needed




Get more information about pods
Kubectl get pods -o wide








References
- Chat GPT
- https://kubernetes.io/docs/concepts/overview/components/
- https://www.youtube.com/watch?v=X48VuDVv0do 
