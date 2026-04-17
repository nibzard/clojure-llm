# Containerized Clojure executor for clj-bench evaluation
FROM clojure:temurin-11-tools-deps

# Set working directory
WORKDIR /app

# Install clj-kondo (static analysis tool)
RUN wget -q https://github.com/clj-kondo/clj-kondo/releases/download/v2024.03.13/clj-kondo-linux-amd64 -O /usr/local/bin/clj-kondo && \
    chmod +x /usr/local/bin/clj-kondo

# Install babashka (native Clojure script runner for fast tasks)
RUN wget -q https://github.com/babashka/babashka/releases/download/v1.3.188/babashka-1.3.188-linux-amd64-static.tar.gz -O /tmp/babashka.tar.gz && \
    tar -xzf /tmp/babashka.tar.gz -C /tmp && \
    mv /tmp/bb /usr/local/bin/bb && \
    rm /tmp/babashka.tar.gz

# Copy project files
COPY deps.edn .
COPY src ./src
COPY benchmark ./benchmark

# Pre-download dependencies for faster startup
RUN clojure -M -e "(println \"Dependencies ready\")"

# Set up entry point
ENTRYPOINT ["clojure", "-M:bench"]
CMD ["-h"]
