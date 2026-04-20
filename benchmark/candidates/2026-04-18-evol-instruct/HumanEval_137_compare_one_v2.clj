(defn normalize-kw-map
  "Create a function that takes a map with keys that may be strings, symbols, or keywords,
and returns a new map with all keys converted to keywords.

If two different input keys normalize to the same keyword, keep the value from the last one encountered.

Examples:
  >>> (normalize-kw-map {\"a\" 1, 'b 2, :c 3})
  {:a 1, :b 2, :c 3}
  >>> (normalize-kw-map {\"name\" \"Ada\", :name \"Grace\"})
  {:name \"Grace\"}
  >>> (normalize-kw-map nil)
  {}

  Note: preserve values as-is; only normalize the keys."
  [m]
  (reduce-kv
    (fn [acc k v]
      (assoc acc
             (cond
               (keyword? k) k
               (symbol? k) (keyword (name k))
               (string? k) (keyword k)
               :else (keyword (str k)))
             v))
    {}
    (or m {})))