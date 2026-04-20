(defn sanitize-keys
  "Given a map of keyword keys to values, return a new map where:
  - keys with nil values are removed
  - remaining keys are converted to strings
  - if a key's name contains spaces, replace spaces with underscores
  - if the map is nil, return an empty map

  Examples:
  >>> (sanitize-keys {:first name \"Ada\" :last name nil :city \"New York\"})
  {\"first_name\" \"Ada\" \"city\" \"New_York\"}
  >>> (sanitize-keys nil)
  {}
  >>> (sanitize-keys {:a nil :b 2})
  {\"b\" 2}"
  [m]
  (if (nil? m)
    {}
    (into {}
          (for [[k v] m
                :when (some? v)]
            [(-> k name (clojure.string/replace " " "_")) v]))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {"first_name" "Ada" "city" "New_York"}
         (sanitize-keys {:first name "Ada" :last name nil :city "New York"}))))

(run-test test-variation)
