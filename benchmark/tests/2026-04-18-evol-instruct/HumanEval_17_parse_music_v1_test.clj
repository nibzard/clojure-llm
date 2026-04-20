(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 3 4] (parse_csv_numbers "1, 2,3, 4")))
  (is (= [10 20 30] (parse_csv_numbers "10,, 20 , ,30")))
  (is (= [] (parse_csv_numbers nil))))

(run-test test-variation)
