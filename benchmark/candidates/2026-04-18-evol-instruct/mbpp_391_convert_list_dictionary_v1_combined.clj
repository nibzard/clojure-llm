(defn zip-to-map
  "Return a map by zipping keys and values, ignoring any extra items in either sequence.

  Examples:
  (zip-to-map [:a :b :c] [1 2])
  ;=> {:a 1, :b 2}

  (zip-to-map nil [1 2])
  ;=> {}

  (zip-to-map (range) [10 20 30])
  ;=> {0 10, 1 20, 2 30}"
  [ks vs]
  (into {} (map vector ks vs)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 1, :b 2} (zip-to-map [:a :b :c] [1 2])))
  (is (= {} (zip-to-map nil [1 2])))
  (is (= {0 10, 1 20, 2 30} (zip-to-map (range) [10 20 30]))))

(run-test test-variation)
