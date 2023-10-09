# Basic System Concepts
## SLAs & SLOs:
A Service Level Agreement is a formal agreement between a service provider and its customers or users. It defines the expected level of service quality, performance, and availability that the provider will deliver. SLAs typically include specific metrics, targets, and responsibilities to ensure that the agreed-upon service standards are met. SLAs are legally binding contracts and often cover aspects like uptime, response time, resolution time, and customer support.

A Service Level Objective is a measurable target or goal set by the service provider to achieve a specific level of performance, reliability, or availability. SLOs are usually derived from the overall SLA and define the performance parameters that the provider aims to meet. SLOs are typically more granular and specific, focusing on individual components or aspects of a service. They help define the acceptable range of performance and serve as benchmarks to assess whether the service is meeting the desired objectives.

In summary, SLA is the formal agreement that outlines the overall service standards and obligations between a service provider and its customers, while SLOs are specific performance targets or objectives derived from the SLA that help measure the performance and availability of the service. SLOs are used as a basis for monitoring and evaluating the service's performance against the agreed-upon standards defined in the SLA.

## Latency and Response Time: 
Latency refers to the time it takes for a packet of data to travel from the source to the destination, typically measured in milliseconds (ms). It represents the delay or the amount of time it takes for data to make a round trip between two points. Latency is primarily affected by factors such as the distance between the source and destination, network congestion, and the speed of the transmission medium (e.g., fiber optics, copper cables).

Response time, on the other hand, measures the total time it takes for a system to respond to a request or perform a specific operation. It includes the time spent on processing, queuing, transmitting, and receiving data. Response time is typically measured in milliseconds (ms) or seconds (s), and it reflects the end-to-end time experienced by the user or the client making the request.

In summary, the key difference between latency and response time is:
Latency: It focuses on the time delay in transmitting data between two points. It measures the round-trip time or the time it takes for data to travel from the source to the destination and back.
Response Time: It represents the total time it takes for a system or application to respond to a request or complete an operation. It encompasses all the processing, queuing, and transmission time involved in fulfilling the request.

## Queueing delays
Queueing delays, also known as queuing latency or queuing delay, refer to the time delay experienced by packets or requests while waiting in a queue for processing or transmission. Queueing delays occur in various systems where requests or packets arrive at a faster rate than they can be served or transmitted. An effect sometimes known as head-of-line blocking. Even if those subsequent requests are fast to process on the server, the client will see a slow overall response time due to the time waiting for the prior request to complete.


# Proxies and Load Balancers 

A proxy and a reverse proxy are both intermediary servers that sit between clients and servers, facilitating communication between them. However, they differ in their roles and the direction of the requests they handle.

## Proxy:
- A proxy server acts as an intermediary between a client and a server. When a client makes a request to access a resource (such as a web page or a file) from a server, the request is first sent to the proxy server.
- The proxy server then forwards the request to the destination server on behalf of the client. It retrieves the response from the server and sends it back to the client. From the client's perspective, it appears as though the response is coming directly from the proxy server.
- Proxies can be used for various purposes, including caching content, improving security by filtering requests, anonymizing client requests by hiding their IP addresses, or optimizing network traffic by compressing or combining requests.
- The client is typically configured to use a proxy server explicitly, either by specifying the proxy settings in the application or operating system configuration.
  
## Reverse Proxy:
- A reverse proxy server sits between clients and servers and handles requests on behalf of servers. Unlike a regular proxy, the reverse proxy appears as the server to the clients, while forwarding requests to the appropriate backend servers.
- When a client sends a request to a reverse proxy, it is the reverse proxy that receives the request and decides which backend server should handle it. The reverse proxy then forwards the request to the selected server and relays the response back to the client.
- Reverse proxies are often used to distribute incoming client requests across multiple servers, improving scalability and handling high traffic loads. They can perform load balancing, distribute requests based on various algorithms, and provide fault tolerance by redirecting requests to healthy servers in case of failures.
- Additionally, reverse proxies can provide features such as SSL/TLS termination, caching, request/response manipulation, security filtering, and protection against certain types of attacks (e.g., DDoS protection).
- Clients are typically unaware of the backend servers and only interact with the reverse proxy, which acts as a single entry point for the application or service.

To summarize, a proxy acts on behalf of clients, forwarding their requests to servers, while a reverse proxy acts on behalf of servers, handling incoming requests from clients and distributing them to the appropriate backend servers.

## HAProxy
- HAProxy is a high-performance TCP/HTTP load balancer and proxy server. It excels at distributing incoming traffic across multiple servers to optimize resource utilization and ensure high availability.
- It operates at the transport layer (Layer 4) of the OSI model, making routing decisions based on IP addresses and port numbers.
- HAProxy supports multiple load balancing algorithms, including round-robin, least connections, source IP hashing, and more. It can handle a large number of concurrent connections efficiently.
- It provides advanced features such as session persistence, health checks, SSL/TLS termination, content-based routing, and request/response manipulation using ACLs (Access Control Lists).
- HAProxy is known for its performance, stability, and low resource consumption, making it suitable for high-traffic websites and critical applications.

## NGINX:
- NGINX is a popular web server, reverse proxy, and load balancer. It can handle both static and dynamic content, making it suitable for a wide range of use cases.
- NGINX operates at the application layer (Layer 7) of the OSI model, which allows it to make routing decisions based on more than just IP addresses and port numbers. It can inspect HTTP headers and perform content-based routing.
- NGINX offers advanced features such as load balancing, caching, SSL/TLS termination, URL rewriting, and request/response manipulation using rewrite rules or modules.
- It has gained popularity as a high-performance web server due to its efficient event-driven architecture, capable of handling a large number of concurrent connections with low memory footprint.
- NGINX is often used as a reverse proxy in front of application servers (e.g., Node.js, Ruby, Python) or as a load balancer for distributing traffic across multiple servers.

While both HAProxy and NGINX can perform load balancing and act as reverse proxies, they have slightly different strengths and use cases. HAProxy is primarily focused on high-performance load balancing at the transport layer, while NGINX offers a more comprehensive web server solution with support for additional features and content-based routing at the application layer. The choice between the two depends on the specific requirements of your application and the level of flexibility and performance needed.



# HTTP

## what is REST
REST stands for Representational State Transfer. It is an architectural style for designing networked applications, primarily web services, that allows different systems to communicate over the internet.

REST is based on a set of principles and constraints that guide the design and interaction of components in a distributed system. These principles emphasize simplicity, scalability, and the use of standard web protocols. The key principles of REST include:

- Stateless Communication: The server does not maintain any client state between requests. Each request from the client must contain all the necessary information to process it. This allows for scalability and reliability in distributed systems.
- Client-Server Architecture: The system is divided into a client (which initiates requests) and a server (which responds to requests). The client and server are independent of each other, allowing them to evolve separately.
- Uniform Interface: The interaction between clients and servers is standardized through a uniform interface that defines a set of constraints and conventions. This promotes simplicity, modularity, and the ability to evolve components independently.
- Resource-Based: Resources are at the core of REST. Each resource is identified by a unique URI (Uniform Resource Identifier) and can be accessed and manipulated using standard HTTP methods, such as GET, POST, PUT, and DELETE.
- Representation-Oriented: Resources are represented in a specific format, such as XML, JSON, or HTML. Clients interact with resources by exchanging representations of the resource's state.
- Hypermedia as the Engine of Application State (HATEOAS): Resources contain links to related resources, enabling clients to discover and navigate the application's capabilities dynamically.

## HTTP Verbs

- GET: The GET method is used to retrieve a representation of a resource or a collection of resources. It is safe and idempotent, meaning it should not modify the server state. GET requests are typically used for reading data from a server.
- POST: The POST method is used to submit data to be processed by the server. It is commonly used to create new resources or trigger actions that result in the creation of resources. POST requests may modify the server state and are not idempotent.
- PUT: The PUT method is used to update or replace a resource with the provided representation. It is typically used when the client wants to update an existing resource entirely. PUT requests are idempotent, meaning that multiple identical requests should have the same effect as a single request.
- PATCH: The PATCH method is used to apply partial modifications to a resource. It is similar to the PUT method but is used when the client wants to update specific fields or properties of a resource without replacing the entire representation.
- DELETE: The DELETE method is used to delete a specified resource. It is used to remove a resource from the server. DELETE requests are idempotent, meaning that multiple identical requests should have the same effect as a single request.
- HEAD: The HEAD method is similar to the GET method but retrieves only the response headers, without the actual response body. It is used to retrieve metadata or check the status of a resource without fetching the entire content.
- OPTIONS: The OPTIONS method is used to retrieve the supported HTTP methods, headers, and other capabilities of a server for a given resource. It helps clients determine the available actions or methods that can be performed on a resource.



