# Reactive

[Customer Service](./Customer-Service/Readme.md)

## How To?
* Build Jar: `./gradlew`

## Handy endpoints
* Checking Routes on Zuul: `/routes`
* Eureka Service Discovery: `http://localhost:8761`

## Docker endpoints for services
Config Service: `curl -i -vvv http://localhost:8888/cargo-service/native/local`
Service Gateway (Eureka): `http://localhost:8761` 

## Design
### Goals & Requirements
* M1: Online Operations
    - [ ] R16: Each booking must be a secured booking so when a customer logs-in, they must be issued a token (Use JWT?)
    - [ ] R1: Book a Cargo
        - [ ] SR1.1: Failed payments for customers with pricing policy should be put in a retry queue.
    - [ ] R2: Cancel a cargo.
    - [ ] R3: What is the current status of my cargo?
    - [ ] R4: Which carriers were used for the cargo?
    - [ ] R5: Which cargo’s were being sent between 2 locations.
    - [ ] R7: Change destination of a booked cargo.
    - [ ] R10: Changing a clients pricing policy.
    - [ ] R11: For a given cargo, retrieve the detailed itinerary for it which highlights the different legs of the journey and for each leg, which carrier would be serving that.
    - [ ] R12: Send invoices to customers automatically when payment is made
    - [ ] R13: Register Customer via email
    - [ ] R14: Register Customer via Google Auth
    - [ ] R15: Register Customer via Facebook Auth
* Admin Operations
    - [ ] R6: Which cargo took longest to reach its destination?
    - [ ] R8: What was the total sale from booking cargo’s for a given time frame?
    - [ ] R9: Which clients have booked the most number of cargos?
* S5: Async Operations
    - [ ] R1: Taking payment for cargo’s using queuing.
    - [ ] R2: Receiving key events for a cargo from different carriers about their leg journeys, loading/unloading and delivery etc.
    - [ ] R3: Calculating quotes from various providers to get the best routes. (Routing Service)
* S6: Non-Functional requirements
    * R3: TPS for each endpoint.
        * Booking: 100 TPS
        * Cancellation: 10 TPS
        * Status: 300 TPS
    * R1: System Metrics (To be published using some tool)
        - [ ] SR1: CPU Usage
        - [ ] SR2: Memory Usage
        - [ ] SR3: Thread Pool Usage
        - [ ] SR4: Circuit breaker metrics
        - [ ] SR5: Open File Handles
        - [ ] SR6: Disk Usage
        - [ ] SR7: JVM Stats
            - [ ] SR8: GC Pauses
            - [ ] SR9: Heap Usa
    * R2: Business Analytics (Using Kafka for last 30 days -- See kafka streams aggregation/hopping window)
        - [ ] R1: Most booked destinations.
        - [ ] R2: Daily/Monthly booked cargo.
        - [ ] R3: Number of Failed payments.
        - [ ] R4: Number of bookings
        - [ ] R5: Number of cancellations
* S7: Throttling
    - [ ] R1: Throttle get status call based on pricing policy of the customer.

### Design Considerations
*
### High Level System Design
### System APIs
### Database Schema
### Data Size Estimation
### Component Design
### Monitoring
### Security
### Capacity Estimation & Constraints
### Infrastructure Design
### Load Balancing
