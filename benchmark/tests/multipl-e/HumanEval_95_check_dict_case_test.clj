(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_dict_case)

(deftest test-humaneval

  (is (= (candidate {"p" "pineapple" "b" "banana"}) true))
  (is (= (candidate {"p" "pineapple" "A" "banana" "B" "banana"}) false))
  (is (= (candidate {"p" "pineapple" "5" "banana" "a" "apple"}) false))
  (is (= (candidate {"Name" "John" "Age" "36" "City" "Houston"}) false))
  (is (= (candidate {"STATE" "NC" "ZIP" "12345"}) true))
  (is (= (candidate {"fruit" "Orange" "taste" "Sweet"}) true))
  (is (= (candidate {}) false))
)

(run-test test-humaneval)