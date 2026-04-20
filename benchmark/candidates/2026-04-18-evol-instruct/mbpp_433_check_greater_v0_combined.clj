(defn all-greater?
  "Return true if every element in the vector is greater than the threshold.
  
  Examples:
  (all-greater? [5 6 7] 4) => true
  (all-greater? [1 5 7] 4) => false
  (all-greater? [] 10) => true"
  [v threshold]
  (every? #(> % threshold) v))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (all-greater? [5 6 7] 4)))
  (is (= false (all-greater? [1 5 7] 4)))
  (is (= true (all-greater? [] 10))))

(run-test test-variation)
