(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (is_decimal? "12.34")))
  (is (= true (is_decimal? -7.89)))
  (is (= false (is_decimal? "12.3")))
  (is (= false (is_decimal? 12)))
  (is (= false (is_decimal? nil))))

(run-test test-variation)
