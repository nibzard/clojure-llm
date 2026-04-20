(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [] (interleave-with-index [])))
  (is (= [[:a 0] [:b 1] [:c 2]] (interleave-with-index [:a :b :c]))))

(run-test test-variation)
