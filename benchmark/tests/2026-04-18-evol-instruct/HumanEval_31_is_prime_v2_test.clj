(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (is_prime_in_range 11 13)))
  (is (= false (is_prime_in_range 10 13)))
  (is (= true (is_prime_in_range 2 2)))
  (is (= false (is_prime_in_range nil 7)))
  (is (= false (is_prime_in_range 17 11))))

(run-test test-variation)
