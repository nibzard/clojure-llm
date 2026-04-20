(defn unique-preserving-last
  "Return a vector of the distinct values in `coll`, keeping their last
  occurrence order.

  Examples:
  (unique-preserving-last [1 2 3 2 4 1]) ;=> [3 2 4 1]
  (unique-preserving-last nil) ;=> []
  (unique-preserving-last '(\"a\" \"b\" \"a\" \"c\" \"b\")) ;=> [\"a\" \"c\" \"b\"]"
  [coll]
  (let [v (vec (or coll []))
        seen (atom #{})
        out (transient [])]
    (doseq [x (reverse v)]
      (when-not (contains? @seen x)
        (swap! seen conj x)
        (conj! out x)))
    (persistent! (reduce conj [] (reverse (persistent! out))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [3 2 4 1] (unique-preserving-last [1 2 3 2 4 1])))
  (is (= [] (unique-preserving-last nil)))
  (is (= ["a" "c" "b"] (unique-preserving-last '("a" "b" "a" "c" "b")))))

(run-test test-variation)
