# Start Colima with custom resources
colima start --cpu 4 --memory 4 --disk 16

# --cpu 4     → allocate 4 CPUs
# --memory 6  → allocate 6 GiB of RAM
# --disk 60   → allocate 60 GiB of disk space
# --fresh     → removes all cache (start from scratch)

# Start fresh (remove all containers)
docker container prune

docker stop zookeeper
docker rm zookeeper

# Using default docker-compose.yml in current directory
docker-compose up              # attached mode
docker-compose up -d          # detached mode

# Using a specific file
docker-compose -f custom-file.yml up       # attached
docker-compose -f custom-file.yml up -d    # detached

# Using multiple compose files (e.g., base + override)
docker-compose -f docker-compose.yml -f override.yml up       # attached
docker-compose -f docker-compose.yml -f override.yml up -d    # detached


Service	        URL
Kafka UI	    http://localhost:9090
Prometheus	    http://localhost:9091
Grafana	        http://localhost:3000
Schema Registry	http://localhost:8081
Kafka Broker	PLAINTEXT_HOST://localhost:9092
Sonar Server    http://localhost:9000/


# Sonar
mvn clean verify

mvn clean verify sonar:sonar \
-Dsonar.projectKey=eos-kafka-service \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.login=your_generated_token \
-Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml \
-Dsonar.coverage.inclusions=**/service/**/*.java


