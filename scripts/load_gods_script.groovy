def runScript() {
    import org.apache.tinkerpop.gremlin.driver.Client
    import org.apache.tinkerpop.gremlin.driver.Cluster

    def environmentVariables = System.getenv();
    def gremlinHost = environmentVariables['GREMLIN_HOST'] ?: 'localhost'
    def gremlinPort = environmentVariables['GREMLIN_PORT'] ?: '8182'

    println "Connecting to Gremlin Server at $gremlinHost:$gremlinPort"

    Cluster cluster = Cluster.build(gremlinHost).port(Integer.parseInt(gremlinPort)).create()
    Client client = cluster.connect()

    // Fetch vertex count - returns List<Result>
    def results = client.submit('g.V().count()').all().get()
    if (results.isEmpty()) {
        throw new RuntimeException("Failed to retrieve vertex count.")
    }
    
    // Extract the value from the Result object
    def vertexCount = results[0].getLong() // getLong() converts the underlying number to a Long
    println "Vertex count: $vertexCount"
    
    // Load the graph only if it's empty
    if (vertexCount == 0) {
        println "Loading the graph as it's empty..."
        client.submit('GraphOfTheGodsFactory.load(graph)').all().get()
        println "Data loaded successfully!"
    } else {
        println "Graph already contains data. Skipping load."
    }
    
    // Close the connection
    client.close()
    cluster.close()
}

runScript()