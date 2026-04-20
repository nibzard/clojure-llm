(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (vector_match_three [:x :a :b :b :b :y])))
  (is (= false (vector_match_three [:a :b :b])))
  (is (= false (vector_match_three nil))))

(run-test test-variation)
