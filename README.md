# Using default docker-compose.yml in current directory
docker-compose up              # attached mode
docker-compose up -d          # detached mode

# Using a specific file
docker-compose -f custom-file.yml up       # attached
docker-compose -f custom-file.yml up -d    # detached

# Using multiple compose files (e.g., base + override)
docker-compose -f docker-compose.yml -f override.yml up       # attached
docker-compose -f docker-compose.yml -f override.yml up -d    # detached
