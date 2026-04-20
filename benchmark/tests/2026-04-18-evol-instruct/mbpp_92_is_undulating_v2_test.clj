(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (is_undulating-seq [1 2 1 2 1])))
  (is (= true (is_undulating-seq ["a" "b" "a" "b"])))
  (is (= false (is_undulating-seq [1 1 2 1])))
  (is (= false (is_undulating-seq [])))
  (is (= false (is_undulating-seq [42]))))

(run-test test-variation)
