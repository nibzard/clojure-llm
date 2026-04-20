(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (next_power_of_2 0)))
  (is (= 1 (next_power_of_2 1)))
  (is (= 8 (next_power_of_2 5)))
  (is (= 16 (next_power_of_2 16)))
  (is (= 1024 (next_power_of_2 1000))))

(run-test test-variation)
