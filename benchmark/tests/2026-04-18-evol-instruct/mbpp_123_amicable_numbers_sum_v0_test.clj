(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '([220 284]) (amicable_pairs 300)))
  (is (= '() (amicable_pairs nil)))
  (is (= '() (amicable_pairs -5))))

(run-test test-variation)
