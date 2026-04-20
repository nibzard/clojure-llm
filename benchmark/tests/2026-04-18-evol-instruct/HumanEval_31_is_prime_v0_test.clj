(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [] (prime-factors 1)))
  (is (= [2] (prime-factors 2)))
  (is (= [2 2 3] (prime-factors 12)))
  (is (= [97] (prime-factors 97))))

(run-test test-variation)
