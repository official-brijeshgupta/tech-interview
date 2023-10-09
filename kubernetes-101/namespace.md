## Namespace 
A namespace is a logical boundary or virtual cluster that allows you to create a segregated and isolated environment within a Kubernetes cluster. It provides a way to organize and separate resources into distinct groups based on different criteria, such as teams, projects, environments, or applications.

Here are some key points about namespaces in Kubernetes:

- **Isolation and Resource Segregation**: Namespaces provide isolation between resources within a cluster. Resources created within a namespace are scoped to that namespace and are not directly visible or accessible to resources in other namespaces. This segregation helps prevent naming conflicts and allows different teams or applications to coexist within the same cluster.
- **Logical Clusters**: Namespaces act as virtual clusters within a physical Kubernetes cluster. Each namespace has its own set of resources, including pods, services, deployments, and other Kubernetes objects. It provides a way to create multiple logical clusters within a shared infrastructure.
- **Resource Quotas**: Namespaces allow you to set resource quotas to limit the amount of compute resources (CPU, memory) and storage that can be consumed by resources within the namespace. This helps in resource allocation and prevents one namespace from consuming excessive resources and affecting the stability and performance of other namespaces.
- **Access Control**: Kubernetes supports role-based access control (RBAC) at the namespace level. You can define different roles and permissions for users or groups at the namespace level, allowing fine-grained access control and security within the cluster. This enables different teams or users to have distinct access rights and privileges within their respective namespaces.
- **Easy Management**: By organizing resources into namespaces, it becomes easier to manage and operate large, complex deployments. You can apply operations, updates, or configuration changes to specific namespaces, affecting only the resources within that namespace. Namespaces provide a logical grouping that simplifies tasks such as monitoring, troubleshooting, and managing applications.
- **Default Namespace**: By default, Kubernetes creates a namespace called default if you don't specify a namespace when creating resources. It is the namespace where resources are created if no other namespace is specified explicitly.
- **Namespace Scopes**: Kubernetes provides two additional namespace scopes: kube-system and kube-public. The kube-system namespace is reserved for Kubernetes system resources and components, such as kube-dns, kube-proxy, and kube-scheduler. The kube-public namespace is readable by all users and can be used for resources that need to be accessible publicly across the cluster.

To specify the namespace when creating or accessing resources, you can use the -n or --namespace flag with kubectl commands.

```
kubectl get namespace
output: 
NAME              STATUS   AGE
default           Active   21h
kube-node-lease   Active   21h
kube-public       Active   21h
kube-system       Active   21h
```

create a new namespace 
```
kubectl create namespace my-namespace
output : 
namespace/my-namespace created
```

Note:
What cannot be shared across namespaces
- ConfigMap
- Secret

What can be shared 
- services


Some resources is not bound to namespace 
- volume
- persistent volume 
- node

If we need to check resopurces in other namespaces we need to spacify in all command 
```
kubectl get service -n my-namespace
output : 
No resources found in my-namespace namespace.
```

Now it could be hard to keep changing namespaces. We could use a tool to switch active namespaces - **kubens**
```
brew install kubectx

kubens
**default**   <- this is highlighted
kube-node-lease
kube-public
kube-system
my-namespace
```

to change active namespace 
```
kubens my-namespace
output : 
Context "minikube" modified.
Active namespace is "my-namespace".
```

