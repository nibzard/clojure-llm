(defn rotate-first-last
  "Given a vector, return a new vector with the first and last elements swapped.

Examples:
(rotate-first-last [1 2 3 4]) => [4 2 3 1]
(rotate-first-last [\"a\"]) => [\"a\"]
(rotate-first-last []) => []
(rotate-first-last nil) => []"
  [v]
  (let [v (vec (or v []))
        n (count v)]
    (cond
      (zero? n) []
      (= 1 n) v
      :else (assoc v 0 (peek v) (dec n) (first v)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [4 2 3 1] (rotate-first-last [1 2 3 4]))))

(run-test test-variation)
