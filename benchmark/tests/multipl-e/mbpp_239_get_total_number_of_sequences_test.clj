(require '[clojure.test :refer [deftest is run-test]])

(def candidate get_total_number_of_sequences)

(deftest test-humaneval

  (is (= (candidate 10 4) 4))
  (is (= (candidate 5 2) 6))
  (is (= (candidate 16 3) 84))
)

(run-test test-humaneval)