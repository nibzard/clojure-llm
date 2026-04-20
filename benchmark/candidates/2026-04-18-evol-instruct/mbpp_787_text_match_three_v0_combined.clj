(defn vector_match_three
  "Return true if the vector contains an :a element immediately followed by three :b elements.

Examples:
  (vector_match_three [:x :a :b :b :b :y]) => true
  (vector_match_three [:a :b :b]) => false
  (vector_match_three nil) => false"
  [v]
  (boolean
    (some #(= [:a :b :b :b] (take 4 %))
          (partition 4 1 (or v [])))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (vector_match_three [:x :a :b :b :b :y])))
  (is (= false (vector_match_three [:a :b :b])))
  (is (= false (vector_match_three nil))))

(run-test test-variation)
