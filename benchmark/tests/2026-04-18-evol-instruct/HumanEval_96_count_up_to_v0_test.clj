(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [] (count_up_to nil)))
  (is (= [] (count_up_to -5)))
  (is (= [2 3 5 7] (count_up_to 10)))
  (is (= [] (count_up_to 2)))
  (is (= [2 3 5 7 11 13 17 19] (count_up_to 20))))

(run-test test-variation)
