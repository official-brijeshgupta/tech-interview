Yaml Config file in K8s


Majorly there are 3 parts of the configuration file :
1. Metadata
2. Specification
3. Status : the status field is not typically defined directly. Instead, it is a field that gets populated by the Kubernetes control plane based on the current state and status of the resource within the cluster. The status field provides information about the actual state of the resource, such as the number of available replicas, the status of pods, and other relevant information.  This information about the cluster data comes from etcd

1. API Version: The apiVersion field specifies the version of the Kubernetes API being used. It ensures compatibility and determines the available features and options for the resource.
2. Kind: The kind field defines the type of resource being defined in the configuration file, such as
    1. Deployment,
    2. Service,
    3. Pod,
    4. ConfigMap
3. Metadata: The metadata section contains information about the resource, including the name, labels, and annotations. Labels are key-value pairs used for organizing and selecting resources, while annotations provide additional metadata about the resource.
    1. name: Specifies the name of the resource. For example: name: my-deployment.
    2. labels: Key-value pairs used for organizing and selecting resources
    3. annotations:  Additional metadata about the resource
4. Spec: The spec section contains the desired state of the resource being created. It includes various parameters and configurations specific to the resource type. For example, in a Deployment, the spec section defines the replica count, container template, volumes, and other deployment-related settings.
5. Selectors: Selectors are used to match resources based on labels. They are typically used in conjunction with other resource types, such as Service or Ingress, to determine which pods or services should be associated with each other.
6. Containers: In resource types like Deployment or Pod, you'll find the containers section. It specifies the containers to be created and run within the pods. Each container includes properties such as the container image, ports, environment variables, volume mounts, and other configurations.
7. Volumes: The volumes section defines the volumes to be used by the pods or containers. Volumes provide a way to persist data or share data between containers within a pod.
8. Service Type: In a Service resource, the spec section includes the type field, which determines the type of service to be created, such as ClusterIP, NodePort, or LoadBalancer. It defines how the service is exposed and accessible within or outside the cluster.
9. Ingress Rules: In an Ingress resource, the spec section includes the rules field, which defines the routing rules for incoming traffic. It specifies the hostnames, paths, and backend services to which requests should be directed.
10. template: The templae section acts as a blueprint or specification for creating and managing pods based on the defined template. When a resource (such as a Deployment or ReplicaSet) is created or updated using the configuration file, the Kubernetes control plane uses the information in the template section to instantiate pods according to the defined specifications.

￼

￼


File format : yaml
- Yaml is very strict about indentation. Can you any yaml validator to fix indentation.
- Normally stored as part of codebase  



